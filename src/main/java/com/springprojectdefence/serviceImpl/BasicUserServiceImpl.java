package com.springprojectdefence.serviceImpl;

import com.springprojectdefence.entities.BasicUser;
import com.springprojectdefence.entities.Role;
import com.springprojectdefence.entities.User;
import com.springprojectdefence.models.bindingModels.RegistrationModel;
import com.springprojectdefence.models.bindingModels.user.UserModel;
import com.springprojectdefence.repository.BasicUserRepository;
import com.springprojectdefence.repository.EditUserRoleRepository;
import com.springprojectdefence.service.BasicUserService;
import com.springprojectdefence.service.RoleService;
import com.springprojectdefence.validation.Errors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasicUserServiceImpl implements BasicUserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private BasicUserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EditUserRoleRepository editUserRoleRepository;

    @Transactional
    @Override
    public void register(RegistrationModel registrationModel){
        BasicUser user = this.modelMapper.map(registrationModel, BasicUser.class);
        String encryptedPassword = this.bCryptPasswordEncoder.encode(registrationModel.getPassword());
        user.setPassword(encryptedPassword);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setCredentialsNonExpired(true);
        this.userRepository.save(user);
        if (user.getId() == 1){
            user.addRole(this.roleService.getAdminRole());
        }else {
            user.addRole(this.roleService.getDefaultRole());
        }
    }

    @Transactional
    @Override
    public boolean usernameExists(String username) throws NullPointerException {
        User user = this.userRepository.findOneByUsername(username);
        if (user != null){
            return true;
        }
        return false;
    }

    @Override
    public List<UserModel> getAll() {
        ModelMapper modelMapper = new ModelMapper();
        List<User> users = this.editUserRoleRepository.findAll();
        List<UserModel> userModels = new ArrayList<>();
        for (User user : users) {
            UserModel userModel = modelMapper.map(user, UserModel.class);
            userModels.add(userModel);
        }

        return userModels;
    }

    @Override
    public void saveRegisteredUser(BasicUser user) {
        this.userRepository.save(user);
    }


    @Override
    public void delete() {

    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findOneByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException(Errors.INVALID_CREDENTIALS);
        }
        return user;
    }
}
