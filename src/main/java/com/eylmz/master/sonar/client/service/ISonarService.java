package com.eylmz.master.sonar.client.service;

import com.eylmz.master.sonar.client.dto.Project;
import com.eylmz.master.sonar.client.dto.sonar.IssueSonar;
import com.eylmz.master.sonar.client.repository.IIssueRepositorySonar;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ISonarService {

    void addIssue(IssueSonar issue) ;

    List<IssueSonar> listIssues(String projectUuid);

    List<Project> listProject();
}
