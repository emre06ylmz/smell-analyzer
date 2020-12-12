package com.eylmz.master.sonar.client.service;

import com.eylmz.master.sonar.client.exception.GithubException;
import com.eylmz.master.sonar.client.webclient.GithubApiClient;
import lombok.RequiredArgsConstructor;
import org.eclipse.egit.github.core.Contributor;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GithubApiService implements IGithubApiService {

    @Autowired
    private final GithubApiClient githubApiClient;

    @Override
    public Collection<RepositoryCommit> getCommits(String sha, String path) throws GithubException {
        return this.githubApiClient.getCommits(sha, path);
    }

    @Override
    public void connect(String projectOwner, String projectName) {
        this.githubApiClient.connect(projectOwner, projectName);
    }

    @Override
    public List<Contributor> getContributors() throws GithubException {
        return this.githubApiClient.getContributors();
    }

    @Override
    public User getUser(String login) throws GithubException {
        return this.githubApiClient.getUser(login);
    }


}
