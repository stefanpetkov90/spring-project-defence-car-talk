package com.springprojectdefence.service;

import com.springprojectdefence.entities.Role;
import com.springprojectdefence.entities.User;
import com.springprojectdefence.models.bindingModels.user.EditUserRoleModel;

public interface RoleService {

    Role getDefaultRole();

    Role getAdminRole();
}
