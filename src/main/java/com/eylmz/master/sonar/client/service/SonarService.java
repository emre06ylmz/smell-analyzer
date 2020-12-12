package com.eylmz.master.sonar.client.service;

import com.eylmz.master.sonar.client.dto.sonar.ComponentResult;
import com.eylmz.master.sonar.client.dto.sonar.IssueResult;
import com.eylmz.master.sonar.client.webclient.SonarFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SonarService implements ISonarService {

    private final SonarFeignClient sonarFeignClient;

    @Override
    public IssueResult getIssues(String id, String types) {
        return this.sonarFeignClient.getIssues(id, types);
    }

    @Override
    public ComponentResult getComponents(String component, String metricKeys) {
        return this.sonarFeignClient.getComponents(component, metricKeys);
    }

}
