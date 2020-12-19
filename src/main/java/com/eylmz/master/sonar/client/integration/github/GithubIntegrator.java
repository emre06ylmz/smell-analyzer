package com.eylmz.master.sonar.client.integration.github;

import com.eylmz.master.sonar.client.exception.GithubException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.egit.github.core.*;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.client.PageIterator;
import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.service.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Component
public class GithubIntegrator {

    private static final Logger logger = LogManager.getLogger(GithubIntegrator.class);

    @Value("${sonar.project.autoConnectEnabled:false}")
    public boolean autoConnectEnabled;
    @Value("${sonar.project.projectOwner}")
    public String projectOwner;
    @Value("${sonar.project.projectName}")
    public String projectName;
    @Value("${sonar.project.token}")
    public String token;

    protected GitHubClient gitHubClient;
    protected RepositoryService repositoryService;
    protected Repository repository;
    protected CommitService commitService;
    protected UserService userService;
    protected IssueService issueService;
    protected PullRequestService pullRequestService;
    protected EventService eventService;

    @PostConstruct
    private void postConstruct() {
        if (this.autoConnectEnabled) {
            this.connect(this.projectOwner, this.projectName);
        }
    }

    public void connect(String projectOwner, String projectName) {
        try {
            this.projectOwner = projectOwner;
            this.projectName = projectName;

            this.gitHubClient = new GitHubClient();
            this.gitHubClient.setOAuth2Token(token);

            this.repositoryService = new RepositoryService(this.gitHubClient);
            this.repository = repositoryService.getRepository(this.projectOwner, this.projectName);

            this.commitService = new CommitService(this.gitHubClient);

            this.userService = new UserService(this.gitHubClient);
            this.issueService = new IssueService(this.gitHubClient);

            this.pullRequestService = new PullRequestService(this.gitHubClient);
            eventService = new EventService(this.gitHubClient);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Collection<RepositoryCommit> getCommits() throws GithubException {
        Collection<RepositoryCommit> repositoryCommits;
        try {
            repositoryCommits = this.commitService.getCommits(repository);
        } catch (Exception e) {
            throw new GithubException("getCommits error", e);
        }
        return repositoryCommits;
    }

    public List<Contributor> getContributors() throws GithubException {
        List<Contributor> contributors;
        try {
            contributors = this.repositoryService.getContributors(repository, false);
        } catch (Exception e) {
            throw new GithubException("getContributors error", e);
        }
        return contributors;
    }


    public RepositoryCommit getCommit(String sha) throws GithubException {
        RepositoryCommit repositoryCommit;
        try {
            repositoryCommit = this.commitService.getCommit(repository, sha);
        } catch (IOException e) {
            logger.error(e);
            throw new GithubException("getCommit error", e);
        }
        return repositoryCommit;
    }

    public User getUser(String login) throws GithubException {
        User user;
        try {
            user = this.userService.getUser(login);
        } catch (IOException e) {
            logger.error(e);
            throw new GithubException("getUser error", e);
        }
        return user;
    }

    public Collection<Issue> getIssues() {
        Map<String, String> pageIssueParams = new HashMap<>();
        PageIterator<Issue> issueIterator = this.issueService.pageIssues(this.projectOwner, this.projectName,
                pageIssueParams);
        return issueIterator.next();
    }

    public Collection<PullRequest> getPullRequests() {
        PageIterator<PullRequest> pRIterator = this.pullRequestService.pagePullRequests(this.repository, IssueService.FILTER_CREATED);
        return pRIterator.next();
    }

    public List<CommitFile> getFilesOfPullRequest(PullRequest pullRequest) {
        List<CommitFile> commitFiles = new ArrayList<>();
        try {
            commitFiles = this.pullRequestService.getFiles(this.repository, pullRequest.getNumber());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return commitFiles;
    }

    public Collection<Event> getUserEvents(String user) {
        PageIterator<Event> pRIterator = this.eventService.pageUserEvents(user);
        return pRIterator.next();
    }

}
