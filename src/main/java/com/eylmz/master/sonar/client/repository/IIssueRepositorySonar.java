package com.eylmz.master.sonar.client.repository;

import com.eylmz.master.sonar.client.dto.sonar.IssueSonar;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface IIssueRepositorySonar extends CrudRepository<IssueSonar, Integer> {

    @Query("SELECT p FROM com.eylmz.master.sonar.client.dto.sonar.IssueSonar p WHERE p.project_uuid = :project_uuid")
    List<IssueSonar> findIssuesByProject_uuid(@Param("project_uuid") String project_uuid);

}