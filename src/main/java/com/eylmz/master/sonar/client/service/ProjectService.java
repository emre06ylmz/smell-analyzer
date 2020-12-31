package com.eylmz.master.sonar.client.service;

import com.eylmz.master.sonar.client.dto.Project;
import com.eylmz.master.sonar.client.repository.IProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService implements IProjectService {

    @Autowired
    private IProjectRepository projectRepository;

    @Override
    public List<Project> listProject() {
        return new ArrayList<>(this.projectRepository.listProjects());
    }

    @Override
    public List<Project> listFiles(String uuid) {
        return new ArrayList<>(this.projectRepository.listFiles(uuid));
    }

    @Override
    public void addProject(Project projectDTO) {
        this.projectRepository.save(projectDTO);
    }

}
