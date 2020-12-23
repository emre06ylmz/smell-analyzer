package com.eylmz.master.sonar.client.repository;

import com.eylmz.master.sonar.client.dto.github.Issue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface IIssueRepository extends CrudRepository<Issue, Integer> {

    @Query("SELECT p FROM Issue p WHERE p.uuid = :uuid")
    List<Issue> findIssuesByUuid(@Param("uuid") String uuid);

}
