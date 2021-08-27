package com.eazybytes.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.eazybytes.entity.Roles;

@Repository
public interface RoleRepository extends CrudRepository<Roles, String> {
  Optional<Roles> findByRoleName(String roleName);
}
