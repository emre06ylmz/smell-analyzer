package com.eylmz.master.sonar.client.controller;

import com.eylmz.master.sonar.client.dto.Project;
import com.eylmz.master.sonar.client.dto.github.Contributor;
import com.eylmz.master.sonar.client.dto.github.Issue;
import com.eylmz.master.sonar.client.dto.github.PullRequest;
import com.eylmz.master.sonar.client.dto.github.User;
import com.eylmz.master.sonar.client.exception.GithubException;
import com.eylmz.master.sonar.client.integration.github.GithubIntegrator;
import com.eylmz.master.sonar.client.integration.shell.ShellIntegrator;
import com.eylmz.master.sonar.client.service.IProjectService;
import com.eylmz.master.sonar.client.service.IContributorService;
import com.eylmz.master.sonar.client.service.IIssueService;
import com.eylmz.master.sonar.client.service.IPullRequestService;
import com.eylmz.master.sonar.client.service.IUserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
    private final ShellIntegrator shellIntegrator;

    @Value("${sonar.project.projectUuid}")
    public String projectUuid;

    @GetMapping("/shell")
    public void shell() {
        this.shellIntegrator.runCommand();
    }

    @GetMapping("/getCommits")
    public Collection<RepositoryCommit> getCommits(@RequestParam(name = "sha", required = false) String sha, @RequestParam(name = "path", required = false) String path) {
        try {
            return githubIntegrator.getCommits(sha, path);
        } catch (GithubException e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "commits not found"
            );
        }
    }

    @GetMapping("/getIssues")
    public Collection<Issue> getIssues() {
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
    public Collection<PullRequest> getPullRequests() {
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

}
