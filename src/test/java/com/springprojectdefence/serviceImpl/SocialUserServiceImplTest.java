package com.springprojectdefence.serviceImpl;

import com.springprojectdefence.entities.Role;
import com.springprojectdefence.entities.SocialUser;
import com.springprojectdefence.repository.SocialUserRepository;
import com.springprojectdefence.service.RoleService;
import com.springprojectdefence.service.SocialUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.facebook.api.User;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class SocialUserServiceImplTest {

    public static final String EXPECTED_EMAIL = "stefan@gmail.com";

    @Mock
    private User facebookUser;

    @MockBean
    private SocialUserRepository socialUserRepository;

    @Captor
    private ArgumentCaptor<SocialUser> captor;

    @MockBean
    private RoleService roleService;

    @Autowired
    private SocialUserService socialUserService;

    @Before
    public void setUp() throws Exception {
        //Arrange
        Role role = new Role();
        role.setAuthority("ROLE_USER");
        when(this.roleService.getDefaultRole()).thenReturn(role);
        when(this.facebookUser.getEmail()).thenReturn(EXPECTED_EMAIL);
        when(this.socialUserRepository.findOneByUsername(EXPECTED_EMAIL)).thenReturn(null);
    }

    @Test
    public void registerOrLogin() throws Exception {
        //Act
        this.socialUserService.registerOrLogin(this.facebookUser);

        //Assert Database Call
        verify(this.socialUserRepository, times(1)).save(captor.capture());

        //Assert Authentication
        SocialUser loggedInUser = (SocialUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //Assert
        assertNotNull(loggedInUser);
        assertEquals(EXPECTED_EMAIL, loggedInUser.getUsername());
    }
}