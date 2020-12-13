package com.eylmz.master.sonar.client.service;

import com.eylmz.master.sonar.client.dto.ProjectDTO;
import com.eylmz.master.sonar.client.dto.github.ContributorDTO;
import com.eylmz.master.sonar.client.dto.github.UserDTO;
import com.eylmz.master.sonar.client.repository.IContributorRepository;
import com.eylmz.master.sonar.client.repository.IProjectRepository;
import com.eylmz.master.sonar.client.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GithubDTOService implements IGithubDTOService {

    @Autowired
    private IContributorRepository contributorRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IProjectRepository projectRepository;

    public List<ContributorDTO> getAllContributors()
    {
        List<ContributorDTO> contributorDTOS = new ArrayList<>();
        this.contributorRepository.findAll().forEach(contributorDTOS::add);
        return contributorDTOS;
    }
    public void addContributor(ContributorDTO contributorDTO)
    {
        this.contributorRepository.save(contributorDTO);
    }

    public void addUser(UserDTO userDTO)
    {
        this.userRepository.save(userDTO);
    }

    @Override
    public void addProject(ProjectDTO projectDTO) {
        this.projectRepository.save(projectDTO);
    }

}