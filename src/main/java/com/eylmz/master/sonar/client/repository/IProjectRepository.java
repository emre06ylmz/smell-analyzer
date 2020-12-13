package com.eylmz.master.sonar.client.repository;

import com.eylmz.master.sonar.client.dto.ProjectDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProjectRepository extends CrudRepository<ProjectDTO, Integer> {

}
