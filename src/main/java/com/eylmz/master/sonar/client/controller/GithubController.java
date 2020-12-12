package com.eylmz.master.sonar.client.controller;

import com.eylmz.master.sonar.client.dto.github.ContributorDTO;
import com.eylmz.master.sonar.client.dto.github.UserDTO;
import com.eylmz.master.sonar.client.exception.GithubException;
import com.eylmz.master.sonar.client.service.IGithubDTOService;
import com.eylmz.master.sonar.client.service.IGithubApiService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.eclipse.egit.github.core.Contributor;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/github")
@Api(value = "Github Api documentation")
public class GithubController {

    @Autowired
    private final IGithubApiService githubService;

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private final IGithubDTOService githubDTOService;

    @GetMapping("/getCommits")
    public Collection<RepositoryCommit> getCommits(@RequestParam(name = "sha", required = false) String sha, @RequestParam(name = "path", required = false) String path) {
        try {
            return githubService.getCommits(sha, path);
        } catch (GithubException e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "commits not found"
            );
        }
    }

    @GetMapping("/getContributors")
    public List<ContributorDTO> getContributors() {
        try {
            List<ContributorDTO> contributors =  this.githubService.getContributors().stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());

            for (ContributorDTO contributor : contributors) {
                githubDTOService.addContributor(contributor);
            }

            return contributors;
        } catch (GithubException e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "contributors not found"
            );
        }
    }

    @GetMapping("/getUsers")
    public List<UserDTO> getUsers() {
        try {
            List<UserDTO> userDTOList = new ArrayList<>();
            List<Contributor> contributors = this.githubService.getContributors();
            for (Contributor contributor : contributors) {
                UserDTO userDTO = this.convertToDto(githubService.getUser(contributor.getLogin()));
                githubDTOService.addUser(userDTO);
                userDTOList.add(userDTO);
            }
            return userDTOList;
        } catch (GithubException e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "users not found"
            );
        }
    }

    @GetMapping("/getUser")
    public UserDTO getUser(@RequestParam(name = "login", required = false) String login) {
        try {
            return this.convertToDto(githubService.getUser(login));
        } catch (GithubException e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "user not found"
            );
        }
    }

    private ContributorDTO convertToDto(Contributor contributor) {
        return this.modelMapper.map(contributor, ContributorDTO.class);
    }

    private UserDTO convertToDto(User user) {
        return this.modelMapper.map(user, UserDTO.class);
    }


}
