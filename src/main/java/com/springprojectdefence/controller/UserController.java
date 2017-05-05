package com.springprojectdefence.controller;

import com.springprojectdefence.models.bindingModels.RegistrationModel;
import com.springprojectdefence.models.bindingModels.user.EditUserRoleModel;
import com.springprojectdefence.models.bindingModels.user.UserModel;
import com.springprojectdefence.service.BasicUserService;
import com.springprojectdefence.service.EditUserRoleService;
import com.springprojectdefence.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import com.springprojectdefence.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private BasicUserService userService;

    @Autowired
    private MessageSource messages;

    @Autowired
    private EditUserRoleService roleService;

    @GetMapping("/register")
    public String getRegisterPage(@ModelAttribute RegistrationModel registrationModel, Model model) {
        model.addAttribute("title", "Register");
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute RegistrationModel registrationModel, BindingResult bindingResult, @RequestParam(required = false) String error, Model model) {
        boolean registered = this.userService.usernameExists(registrationModel.getUsername());
        if (bindingResult.hasErrors()) {
            return "register";
        }
        if (registered) {
            model.addAttribute("error", Errors.USERNAME_EXISTS);
            return "register";
        }
        this.userService.register(registrationModel);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("title", "Log In");
        if (error != null) {
            model.addAttribute("error", Errors.INVALID_CREDENTIALS);
        }
        return "login";
    }

    @GetMapping("/user")
    public String getUserPage(Principal principal) {
        return "userHome";
    }

    @GetMapping("/admin")
    public String getAdminPage() {
        return "adminHome";
    }

    @GetMapping("/logout")
    public String getLogOutPage() {
        return "redirect:/login?logout";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model){
        List<UserModel> users = this.userService.getAll();
        model.addAttribute("title","Users");
        model.addAttribute("users", users);
        model.addAttribute("view", "/users/users-all");
        return "base-layout";
    }

    @GetMapping("edit/user/{id}")
    public String getEditPage(Model model, @PathVariable Long id) {
        EditUserRoleModel editUserRoleModel = this.roleService.getById(id);
        model.addAttribute("editUserRoleModel", editUserRoleModel);
        return "users/users-edit";
    }

    @PostMapping("edit/user/{id}")
    public String editUser(/*@RequestParam(required = false) String error,*/ @Valid @ModelAttribute EditUserRoleModel editUserRoleModel, @PathVariable Long id, BindingResult bindingResult, Model model){
    /*    if (error != null) {
            model.addAttribute("error", Errors.INVALID_CREDENTIALS);
        }*/
        if(bindingResult.hasErrors()){
            return "user/users-edit";
        }
        editUserRoleModel.setId(id);
        this.roleService.save(editUserRoleModel);
        return "redirect:/users";
    }
}
