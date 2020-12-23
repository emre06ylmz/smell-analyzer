package com.eylmz.master.sonar.client.service;

import com.eylmz.master.sonar.client.dto.sonar.IssueSonar;
import java.util.List;

public interface ISonarService {

    void addIssue(IssueSonar issue) ;

    List<IssueSonar> listIssues(String projectUuid);
}
