package com.eylmz.master.sonar.client.repository;

import com.eylmz.master.sonar.client.dto.github.Contributor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IContributorRepository extends CrudRepository<Contributor, Integer> {

}
