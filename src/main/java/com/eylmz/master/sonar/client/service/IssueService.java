package com.eylmz.master.sonar.client.service;

import com.eylmz.master.sonar.client.dto.github.Issue;
import com.eylmz.master.sonar.client.repository.IIssueRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueService implements IIssueService{

    private static final Logger logger = LogManager.getLogger(IssueService.class);

    @Autowired
    private IIssueRepository issueRepository;

    @Override
    public void addIssue(Issue issue) {
        logger.info("issue added: {0}", issue.getId());
        issueRepository.save(issue);
    }

    @Override
    public List<Issue> listIssues(String uuid) {
        return issueRepository.findIssuesByUuid(uuid);
    }
}
