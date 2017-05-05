package com.springprojectdefence.service;

import com.springprojectdefence.models.bindingModels.user.EditUserRoleModel;


public interface EditUserRoleService {
    EditUserRoleModel getById(Long id);
    void save(EditUserRoleModel editUserRoleModel);
}
