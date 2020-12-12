package com.eylmz.master.sonar.client.repository;

import com.eylmz.master.sonar.client.dto.github.UserDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<UserDTO, Integer> {

}
