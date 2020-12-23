package com.eylmz.master.sonar.client.service;

import com.eylmz.master.sonar.client.dto.github.PullRequest;
import com.eylmz.master.sonar.client.repository.IPullRequestRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PullRequestService implements IPullRequestService{

    private static final Logger logger = LogManager.getLogger(PullRequestService.class);

    @Autowired
    private IPullRequestRepository pullRequestRepository;

    @Override
    public void addPullRequest(PullRequest pullRequest) {
        logger.info("pullRequest added: {0}", pullRequest.getId());
        this.pullRequestRepository.save(pullRequest);
    }

    @Override
    public List<PullRequest> listPullRequests(String uuid) {
        return pullRequestRepository.findPullRequestByUuid(uuid);
    }

}
