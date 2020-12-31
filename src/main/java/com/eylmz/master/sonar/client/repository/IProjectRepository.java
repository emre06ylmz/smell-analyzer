package com.eylmz.master.sonar.client.repository;

import com.eylmz.master.sonar.client.dto.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface IProjectRepository extends CrudRepository<Project, Integer> {
    @Query("SELECT p FROM Project p WHERE p.scope = 'PRJ'")
    List<Project> listProjects();

    @Query("SELECT p FROM Project p WHERE p.scope = 'FIL' and p.language = 'java' and p.project_uuid = :uuid")
    List<Project> listFiles(@Param("uuid") String uuid);
}
