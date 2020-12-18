package com.eylmz.master.sonar.client.repository;

import com.eylmz.master.sonar.client.dto.github.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEventRepository extends CrudRepository<Event, Integer> {

}
