package com.eylmz.master.sonar.client.service;

import com.eylmz.master.sonar.client.dto.github.PullRequest;

import java.util.List;

public interface IPullRequestService {

    void addPullRequest(PullRequest pullRequest);
    List<PullRequest> listPullRequests(String uuid);
}
