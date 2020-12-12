package com.eylmz.master.sonar.client.webclient;

import com.eylmz.master.sonar.client.exception.GithubException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.egit.github.core.Contributor;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.client.PageIterator;
import org.eclipse.egit.github.core.service.CommitService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.eclipse.egit.github.core.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class GithubApiClient {

    private static final Logger logger = LogManager.getLogger(GithubApiClient .class);
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
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Collection<RepositoryCommit> getCommits(String sha, String path) throws GithubException {
        PageIterator<RepositoryCommit> repositoryCommitsIterator;
        Collection<RepositoryCommit> repositoryCommits;
        try {
            repositoryCommitsIterator = this.commitService.pageCommits(repository, sha, path);
            repositoryCommits = repositoryCommitsIterator.next();
        } catch (Exception e) {
            throw new GithubException("getCommits error", e);
        }
        return repositoryCommits;
    }

    public List<Contributor> getContributors() throws GithubException {
        List<Contributor> contributors ;
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

}
