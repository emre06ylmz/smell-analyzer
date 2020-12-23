package com.eylmz.master.sonar.client.service;

import com.eylmz.master.sonar.client.dto.sonar.IssueSonar;
import com.eylmz.master.sonar.client.repository.IIssueRepositorySonar;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SonarService implements ISonarService{

    private static final Logger logger = LogManager.getLogger(SonarService.class);

    @Autowired
    private IIssueRepositorySonar issueRepositorySonar;

    @Override
    public void addIssue(IssueSonar issue) {
        logger.info("issue added: {0}", issue.getKee());
        issueRepositorySonar.save(issue);
    }

    @Override
    public List<IssueSonar> listIssues(String projectUuid) {
        return issueRepositorySonar.findIssuesByProject_uuid(projectUuid);
    }
}
