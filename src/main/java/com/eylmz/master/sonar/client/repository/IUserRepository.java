package com.eylmz.master.sonar.client.repository;

import com.eylmz.master.sonar.client.dto.github.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface IUserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT p FROM User p WHERE p.uuid = :login")
    List<User> findUsersByUuid(@Param("login") String login);

    @Query("SELECT u FROM User u WHERE u.login = :login")
    User findUserByLogin(@Param("login") String login);
}
