package com.springprojectdefence.controller;

import com.springprojectdefence.entities.BasicUser;
import com.springprojectdefence.interceptors.TitleInterceptor;
import com.springprojectdefence.models.bindingModels.RegistrationModel;
import com.springprojectdefence.service.BasicUserService;
import com.springprojectdefence.service.EditUserRoleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@ActiveProfiles("test")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Captor
    private ArgumentCaptor<HttpServletRequest> requestArgumentCaptor;

    @Captor
    private ArgumentCaptor<HttpServletResponse> responseArgumentCaptor;

    @Captor
    private ArgumentCaptor<Object> handler;

    @MockBean
    private TitleInterceptor titleInterceptor;

    @MockBean
    private BasicUserService basicUserService;

    @MockBean
    private EditUserRoleService roleService;

    @Before
    public void setUp() throws Exception {
        when(this.titleInterceptor.preHandle(requestArgumentCaptor.capture(), responseArgumentCaptor.capture(), handler.capture()))
                .thenReturn(true);

    }

    @Test
    public void registerUser() throws Exception {
        mockMvc.perform(post("/register")
        .param("username", "Stefan")
                .param("password", "TestPassword123")
                .param("confirmedPassword", "TestPassword123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"))
                .andExpect(redirectedUrl("/"))
                .andExpect(model().hasNoErrors());

        ArgumentCaptor<RegistrationModel> captor = ArgumentCaptor.forClass(RegistrationModel.class);
        Mockito.verify(basicUserService).register(captor.capture());
        RegistrationModel user = captor.getValue();
        assertEquals("Stefan", user.getUsername());
    }

    @Test
    public void registerGivenInvalidPassword_ShoulNotRegister() throws Exception{
        this.mockMvc.perform(post("/register")
                .param("username", "Stefan")
                .param("password", "TestPassword123")
                .param("confirmedPassword", "TestPassword"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));


    }

}