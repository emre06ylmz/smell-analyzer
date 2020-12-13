package com.eylmz.master.sonar.client.service;

import com.eylmz.master.sonar.client.dto.ProjectDTO;
import com.eylmz.master.sonar.client.dto.github.ContributorDTO;
import com.eylmz.master.sonar.client.dto.github.UserDTO;

import java.util.List;


public interface IGithubDTOService {
    List<ContributorDTO> getAllContributors();
    void addContributor(ContributorDTO contributorDTO);
    void addUser(UserDTO userDTO);
    void addProject(ProjectDTO projectDTO);
}
