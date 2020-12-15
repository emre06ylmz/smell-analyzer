package com.eylmz.master.sonar.client.repository;

import com.eylmz.master.sonar.client.dto.github.Issue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IIssueRepository extends CrudRepository<Issue, Integer> {

}
