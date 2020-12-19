package com.eylmz.master.sonar.client.service;

import com.eylmz.master.sonar.client.dto.github.RepositoryCommit;

public interface IRepositoryCommitService{

    void addRepositoryCommit(RepositoryCommit repositoryCommit);
    Iterable<RepositoryCommit> listUsers(RepositoryCommit repositoryCommit);
}
