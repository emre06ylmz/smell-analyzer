package com.eylmz.master.sonar.client.service;

import com.eylmz.master.sonar.client.dto.github.PullRequest;
import com.eylmz.master.sonar.client.repository.IPullRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PullRequestService implements IPullRequestService{

    @Autowired
    private IPullRequestRepository pullRequestRepository;

    @Override
    public void addPullRequest(PullRequest pullRequest) {
        this.pullRequestRepository.save(pullRequest);
    }

}
