package com.eylmz.master.sonar.client.repository;

import com.eylmz.master.sonar.client.dto.Project;
import com.eylmz.master.sonar.client.dto.github.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT p FROM Project p WHERE p.scope = 'PRJ'")
    List<User> findUsersByUuid(String uuid);
}
