package com.springprojectdefence.serviceImpl;

import com.springprojectdefence.entities.BasicUser;
import com.springprojectdefence.entities.Role;
import com.springprojectdefence.entities.SocialUser;
import com.springprojectdefence.entities.User;
import com.springprojectdefence.models.bindingModels.user.EditUserRoleModel;
import com.springprojectdefence.repository.EditUserRoleRepository;
import com.springprojectdefence.repository.RoleRepository;
import com.springprojectdefence.service.EditUserRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class EditUserRoleRepositoryImpl implements EditUserRoleService {

    @Autowired
    private EditUserRoleRepository editUserRoleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public EditUserRoleModel getById(Long id) {
        User user = this.editUserRoleRepository.findOne(id);
        ModelMapper modelMapper = new ModelMapper();
        EditUserRoleModel editUserRoleModel = null;
        if (user != null) {
            editUserRoleModel = modelMapper.map(user, EditUserRoleModel.class);
        }
        return editUserRoleModel;
    }

    @Override
    public void save(EditUserRoleModel editUserRoleModel) {

        if (editUserRoleModel.getPassword().equals("")){
            User user = this.modelMapper.map(editUserRoleModel, SocialUser.class);
            this.editUserRoleRepository.save(user);
        }else {
            User user = this.modelMapper.map(editUserRoleModel, BasicUser.class);
            this.editUserRoleRepository.save(user);
        }
    }
}
