package com.springprojectdefence.serviceImpl;

import com.springprojectdefence.entities.Role;
import com.springprojectdefence.entities.User;
import com.springprojectdefence.models.bindingModels.user.EditUserRoleModel;
import com.springprojectdefence.repository.EditUserRoleRepository;
import com.springprojectdefence.repository.RoleRepository;
import com.springprojectdefence.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    public static final String DEFAULT_ROLE = "ROLE_USER";

    public static final String ADMIN_ROLE = "ROLE_ADMIN";

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EditUserRoleRepository editUserRoleRepository;


    @Override
    public Role getDefaultRole() {
        return this.roleRepository.findOneByAuthority(DEFAULT_ROLE);
    }

    @Override
    public Role getAdminRole(){
        return this.roleRepository.findOneByAuthority(ADMIN_ROLE);
    }

}

