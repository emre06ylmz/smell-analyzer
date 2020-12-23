package com.eylmz.master.sonar.client.repository;

import com.eylmz.master.sonar.client.dto.github.RepositoryCommit;
import com.eylmz.master.sonar.client.dto.github.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface IRepositoryCommitRepository extends CrudRepository<RepositoryCommit, Integer> {

    @Query("SELECT u FROM RepositoryCommit u WHERE u.uuid = :uuid")
    List<RepositoryCommit> findRepositoryCommitByUuid(@Param("uuid") String uuid);

}
