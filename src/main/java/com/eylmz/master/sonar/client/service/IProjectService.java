package com.eylmz.master.sonar.client.service;

import com.eylmz.master.sonar.client.dto.Project;

import java.util.List;

public interface IProjectService {
    List<Project> listProject();

    List<Project> listFiles(String uuid);

    void addProject(Project project);
}
