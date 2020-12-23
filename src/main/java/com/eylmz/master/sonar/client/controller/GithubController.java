package com.eylmz.master.sonar.client.controller;

import com.eylmz.master.sonar.client.dto.Project;
import com.eylmz.master.sonar.client.dto.github.*;
import com.eylmz.master.sonar.client.exception.GithubException;
import com.eylmz.master.sonar.client.filter.RequestResponseLoggingFilter;
import com.eylmz.master.sonar.client.integration.github.GithubIntegrator;
import com.eylmz.master.sonar.client.integration.shell.ShellIntegrator;
import com.eylmz.master.sonar.client.service.*;
import com.google.common.collect.Iterators;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.egit.github.core.client.PageIterator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequiredArgsConstructor
@RequestMapping("/github")
@Api(value = "Github Api documentation")
public class GithubController {

    @Autowired
    private final GithubIntegrator githubIntegrator;

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private final IContributorService contributorService;

    @Autowired
    private final IUserService userService;

    @Autowired
    private final IIssueService issueService;

    @Autowired
    private final IPullRequestService pullRequestService;

    @Autowired
    private final IProjectService projectService;

    @Autowired
    private final IRepositoryCommitService repositoryCommitService;

    @Autowired
    private final IEventService eventService;

    @Autowired
    private final ShellIntegrator shellIntegrator;

    @Autowired
    private final ISonarService sonarService;

    @Value("${sonar.project.projectUuid}")
    public String projectUuid;

    private static final Logger logger = LogManager.getLogger(GithubController.class);

    @GetMapping("/shell")
    public void shell() throws IOException, InterruptedException {
        this.shellIntegrator.runCommand();
    }

    @GetMapping("/summary")
    public List<Summary> getSUmmary() throws IOException, InterruptedException {

        String prj1Uuid = "AXZin23VCl93wm5yKTEJ";
        String prj2Uuid = "AXZipb1ICl93wm5yKTEQ";

        Summary summary1 = new Summary();
        summary1.setProjectName("mockito");

        summary1.setCommitCount(this.repositoryCommitService.listRepositoryCommits(prj1Uuid).size());
        summary1.setIssueCount(this.issueService.listIssues(prj1Uuid).size());
        summary1.setSmellCount(this.sonarService.listIssues(prj1Uuid).size());
        summary1.setPullRequestCount(this.pullRequestService.listPullRequests(prj1Uuid).size());

        List<SummaryDeveloper> developers = new ArrayList<>();
        this.userService.listUsers(prj1Uuid).forEach(developer -> {
            SummaryDeveloper summaryDeveloper = new SummaryDeveloper();
            summaryDeveloper.setName(developer.getName());
            summaryDeveloper.setActivityCount(developer.getEventCount());
            summaryDeveloper.setExperience(developer.getFollowers());
            developers.add(summaryDeveloper);
        });
        summary1.setDevelopers(developers);


        Summary summary2 = new Summary();
        summary2.setProjectName("rxjava");

        summary2.setCommitCount(this.repositoryCommitService.listRepositoryCommits(prj2Uuid).size());
        summary2.setIssueCount(this.issueService.listIssues(prj2Uuid).size());
        summary2.setSmellCount(this.sonarService.listIssues(prj2Uuid).size());
        summary2.setPullRequestCount(this.pullRequestService.listPullRequests(prj2Uuid).size());

        List<SummaryDeveloper> developers2 = new ArrayList<>();
        this.userService.listUsers(prj2Uuid).forEach(developer -> {
            SummaryDeveloper summaryDeveloper2 = new SummaryDeveloper();
            summaryDeveloper2.setName(developer.getName());
            summaryDeveloper2.setActivityCount(developer.getEventCount());
            summaryDeveloper2.setExperience(developer.getFollowers());
            developers2.add(summaryDeveloper2);
        });
        summary2.setDevelopers(developers2);

        List<Summary> summaryList = new ArrayList<>();
        summaryList.add(summary1);
        summaryList.add(summary2);

        return summaryList;
    }

    @GetMapping("/getRepo")
    public void getRepo(@RequestParam(name = "projectOwner") String projectOwner, @RequestParam(name = "projectName") String projectName) throws GithubException, IOException {

        this.githubIntegrator.connect(projectOwner, projectName);

        // get and save all issues
        List<Issue> issues = this.githubIntegrator.getIssues().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        for (Issue issue : issues) {
            issue.setUuid(projectUuid);
            issueService.addIssue(issue);
        }

        // get and save all pull requests
        List<PullRequest> pullRequests = this.githubIntegrator.getPullRequests().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        for (PullRequest pullRequest : pullRequests) {
            pullRequest.setUuid(projectUuid);
            pullRequestService.addPullRequest(pullRequest);
        }

        //get and save all contributors and users and user events
        List<Contributor> contributors = this.githubIntegrator.getContributors().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        for (Contributor contributor : contributors) {
            contributorService.addContributor(contributor);

            User user = this.convertToDto(githubIntegrator.getUser(contributor.getLogin()));
            user.setUuid(projectUuid);
            userService.addUser(user);

            /*
            List<Event> events = this.githubIntegrator.getUserEvents(user.getLogin()).stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());

            for (Event event : events) {
                event.setUser(this.getUser(user.getLogin()));
                eventService.addEvent(event);
            }
            */
        }

        //get and save all repository commit
        List<RepositoryCommit> commits = this.githubIntegrator.getCommits().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        for (RepositoryCommit commit : commits) {
            User author = commit.getAuthor();
            if(author != null){
                commit.setAuthor(this.userService.getUser(author.getLogin()));
            }

            User user = commit.getCommitter();
            if(user != null){
                commit.setCommitter(this.userService.getUser(user.getLogin()));
            }

            this.repositoryCommitService.addRepositoryCommit(commit);
        }

    }



    @GetMapping("/getCommits")
    public Collection<RepositoryCommit> getCommits() throws GithubException {
        List<com.eylmz.master.sonar.client.dto.github.RepositoryCommit> commits = this.githubIntegrator.getCommits().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        for (RepositoryCommit commit : commits) {
            commit.setUuid(projectUuid);
            commit.setCommitter(null);
            commit.setAuthor(null);
            this.repositoryCommitService.addRepositoryCommit(commit);
        }

        return commits;
    }

    @GetMapping("/getUserEvents")
    public Collection<Event> getUserEvents(@RequestParam(name = "user") String user) {
        List<Event> events = new ArrayList<>();
        PageIterator<org.eclipse.egit.github.core.event.Event> eventIterator = this.githubIntegrator.getUserEvents(user);

        while (eventIterator.hasNext()) {
            for (Event event : eventIterator.next().stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList())) {
                event.setUser(this.getUser(user));
                //eventService.addEvent(event);
                events.add(event);
                logger.info(event.getCreatedAt());
            }
        }

        return events;
    }

    @GetMapping("/getIssues")
    public Collection<Issue> getIssues() throws IOException {
        List<Issue> issues = this.githubIntegrator.getIssues().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        for (Issue issue : issues) {
            issue.setUuid(projectUuid);
            issueService.addIssue(issue);
        }

        return issues;
    }

    @GetMapping("/getPullRequests")
    public Collection<PullRequest> getPullRequests() throws IOException {
        List<PullRequest> pullRequests = this.githubIntegrator.getPullRequests().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        for (PullRequest pullRequest : pullRequests) {
            pullRequest.setUuid(projectUuid);
            pullRequestService.addPullRequest(pullRequest);
        }

        return pullRequests;
    }

    @GetMapping("/getContributors")
    public List<Contributor> getContributors() {
        try {
            List<Contributor> contributors = this.githubIntegrator.getContributors().stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());

            for (Contributor contributor : contributors) {
                contributorService.addContributor(contributor);
            }

            return contributors;
        } catch (GithubException e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "contributors not found"
            );
        }
    }

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        try {
            List<User> userList = new ArrayList<>();
            List<org.eclipse.egit.github.core.Contributor> contributors = this.githubIntegrator.getContributors();
            for (org.eclipse.egit.github.core.Contributor contributor : contributors) {
                User user = this.convertToDto(githubIntegrator.getUser(contributor.getLogin()));

                int eventCount = 0;
                PageIterator<org.eclipse.egit.github.core.event.Event> eventIterator = this.githubIntegrator.getUserEvents(user.getLogin());
                while (eventIterator.hasNext()) {
                    eventCount += eventIterator.next().size();
                }

                user.setEventCount(eventCount);
                user.setUuid(projectUuid);
                userService.addUser(user);
                userList.add(user);
            }
            return userList;
        } catch (GithubException e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "users not found"
            );
        }
    }

    @GetMapping("/getUser")
    public User getUser(@RequestParam(name = "login", required = false) String login) {
        try {
            return this.convertToDto(githubIntegrator.getUser(login));
        } catch (GithubException e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "user not found"
            );
        }
    }

    @GetMapping("/addProject")
    public void addProject(@RequestParam(name = "name", required = false) String name, @RequestParam(name = "description", required = false) String description) {
        try {
            Project projectDTO = new Project();
            projectDTO.setName(name);
            projectDTO.setDescription(description);

            this.projectService.addProject(projectDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "project not added"
            );
        }
    }

    private Contributor convertToDto(org.eclipse.egit.github.core.Contributor contributor) {
        return this.modelMapper.map(contributor, Contributor.class);
    }

    private User convertToDto(org.eclipse.egit.github.core.User user) {
        return this.modelMapper.map(user, User.class);
    }

    private Issue convertToDto(org.eclipse.egit.github.core.Issue issue) {
        return this.modelMapper.map(issue, Issue.class);
    }

    private PullRequest convertToDto(org.eclipse.egit.github.core.PullRequest pullRequest) {
        return this.modelMapper.map(pullRequest, PullRequest.class);
    }

    private Event convertToDto(org.eclipse.egit.github.core.event.Event event) {
        return this.modelMapper.map(event, Event.class);
    }

    private RepositoryCommit convertToDto(org.eclipse.egit.github.core.RepositoryCommit repositoryCommit) {
        return this.modelMapper.map(repositoryCommit, RepositoryCommit.class);
    }

}
