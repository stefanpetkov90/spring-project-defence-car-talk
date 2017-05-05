package com.springprojectdefence.repository;

import com.springprojectdefence.entities.BasicUser;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicUserRepository extends UserRepository<BasicUser> {
}
