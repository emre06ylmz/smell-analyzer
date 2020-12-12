package com.eylmz.master.sonar.client.service;

import com.eylmz.master.sonar.client.exception.GithubException;
import org.eclipse.egit.github.core.Contributor;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.User;

import java.util.Collection;
import java.util.List;

public interface IGithubApiService {

    Collection<RepositoryCommit> getCommits(String sha, String path) throws GithubException;

    void connect(String projectOwner, String projectName);

    List<Contributor> getContributors() throws GithubException;

    User getUser(String login) throws GithubException;
}
