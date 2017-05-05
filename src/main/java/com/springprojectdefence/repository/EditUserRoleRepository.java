package com.springprojectdefence.repository;

import com.springprojectdefence.entities.BasicUser;
import com.springprojectdefence.entities.Role;
import com.springprojectdefence.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface EditUserRoleRepository extends CrudRepository<User, Long> {
    @Query(value = "SELECT u FROM User AS u")

    List<User> findAll();
}
