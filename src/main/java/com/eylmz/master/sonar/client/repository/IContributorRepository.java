package com.eylmz.master.sonar.client.repository;

import com.eylmz.master.sonar.client.dto.github.ContributorDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IContributorRepository extends CrudRepository<ContributorDTO, Integer> {

}
