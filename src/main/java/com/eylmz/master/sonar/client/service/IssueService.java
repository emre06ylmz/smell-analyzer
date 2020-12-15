package com.eylmz.master.sonar.client.service;

import com.eylmz.master.sonar.client.dto.github.Issue;
import com.eylmz.master.sonar.client.repository.IIssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssueService implements IIssueService{

    @Autowired
    private IIssueRepository issueRepository;

    @Override
    public void addIssue(Issue issue) {
        issueRepository.save(issue);
    }
}
