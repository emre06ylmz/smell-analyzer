package com.eylmz.master.sonar.client.service;

import com.eylmz.master.sonar.client.dto.github.Issue;
import java.util.List;

public interface IIssueService {

    void addIssue(Issue issue);
    List<Issue> listIssues(String uuid);
}
