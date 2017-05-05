package com.springprojectdefence.repository;

import com.springprojectdefence.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface UserRepository<T extends User> extends CrudRepository<T, Long>{

    T findOneByUsername(String username);
}
