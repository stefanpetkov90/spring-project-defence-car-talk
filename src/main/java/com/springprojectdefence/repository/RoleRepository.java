package com.springprojectdefence.repository;

import com.springprojectdefence.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findOneByAuthority(String authority);
}
