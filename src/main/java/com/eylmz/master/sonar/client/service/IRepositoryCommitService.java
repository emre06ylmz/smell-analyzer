package com.eylmz.master.sonar.client.service;

import com.eylmz.master.sonar.client.dto.github.RepositoryCommit;

import java.util.List;

public interface IRepositoryCommitService{

    void addRepositoryCommit(RepositoryCommit repositoryCommit);
    List<RepositoryCommit> listRepositoryCommits(String uuid);

}
