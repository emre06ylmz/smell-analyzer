package com.eylmz.master.sonar.client.repository;

import com.eylmz.master.sonar.client.dto.github.PullRequest;
import com.eylmz.master.sonar.client.dto.github.RepositoryCommit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface IPullRequestRepository extends CrudRepository<PullRequest, Integer> {

    @Query("SELECT u FROM PullRequest u WHERE u.uuid = :uuid")
    List<PullRequest> findPullRequestByUuid(@Param("uuid") String uuid);

}
