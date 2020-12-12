package com.eylmz.master.sonar.client.service;

import com.eylmz.master.sonar.client.dto.sonar.ComponentResult;
import com.eylmz.master.sonar.client.dto.sonar.IssueResult;

public interface ISonarService {
    IssueResult getIssues(String id, String types);

    ComponentResult getComponents(String component, String metricKeys);
}
