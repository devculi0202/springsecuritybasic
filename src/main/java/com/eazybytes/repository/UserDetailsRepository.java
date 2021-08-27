package com.eazybytes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.eazybytes.entity.User;
import com.eazybytes.entity.UserDetails;

@Repository
public interface UserDetailsRepository extends CrudRepository<UserDetails, String> {
  UserDetails findByUser(User user);
}
