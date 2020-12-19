package com.eylmz.master.sonar.client.repository;

import com.eylmz.master.sonar.client.dto.github.RepositoryCommit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryCommitRepository extends CrudRepository<RepositoryCommit, Integer> {

}
