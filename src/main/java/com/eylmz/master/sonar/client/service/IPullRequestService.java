package com.eylmz.master.sonar.client.service;

import com.eylmz.master.sonar.client.dto.github.PullRequest;

public interface IPullRequestService {

    void addPullRequest(PullRequest pullRequest);

}
