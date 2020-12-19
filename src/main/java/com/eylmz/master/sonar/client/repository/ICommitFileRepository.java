package com.eylmz.master.sonar.client.repository;

import com.eylmz.master.sonar.client.dto.github.CommitFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommitFileRepository extends CrudRepository<CommitFile, Integer> {

}
