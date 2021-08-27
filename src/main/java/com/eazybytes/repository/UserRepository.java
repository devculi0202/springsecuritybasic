package com.eazybytes.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.eazybytes.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
  Optional<User> findByEmail(String email);
}
