package com.eylmz.master.sonar.client.service;

import com.eylmz.master.sonar.client.dto.github.RepositoryCommit;
import com.eylmz.master.sonar.client.repository.IRepositoryCommitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepositoryCommitService implements IRepositoryCommitService{

    @Autowired
    private IRepositoryCommitRepository repositoryCommitRepository;

    @Override
    public void addRepositoryCommit(RepositoryCommit repositoryCommit) {
        repositoryCommitRepository.save(repositoryCommit);
    }

    @Override
    public List<RepositoryCommit> listRepositoryCommits(String uuid) {
        return repositoryCommitRepository.findRepositoryCommitByUuid(uuid);
    }

}
