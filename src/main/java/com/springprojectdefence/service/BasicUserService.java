package com.springprojectdefence.service;


import com.springprojectdefence.entities.BasicUser;
import com.springprojectdefence.entities.User;
import com.springprojectdefence.models.bindingModels.RegistrationModel;
import com.springprojectdefence.models.bindingModels.user.UserModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface BasicUserService extends UserDetailsService {

    void register(RegistrationModel registrationModel);

    boolean usernameExists(String username);

    List<UserModel> getAll();

    void saveRegisteredUser(BasicUser user);

    @PreAuthorize("hasRole('ADMIN')")
    void delete();
}
