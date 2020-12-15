package com.eylmz.master.sonar.client.repository;

import com.eylmz.master.sonar.client.dto.github.PullRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPullRequestRepository extends CrudRepository<PullRequest, Integer> {

}
