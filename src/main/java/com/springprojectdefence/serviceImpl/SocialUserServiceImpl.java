package com.springprojectdefence.serviceImpl;

import com.springprojectdefence.entities.Provider;
import com.springprojectdefence.entities.SocialUser;
import com.springprojectdefence.repository.SocialUserRepository;
import com.springprojectdefence.service.RoleService;
import com.springprojectdefence.service.SocialUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.facebook.api.FacebookObject;
import org.springframework.social.facebook.api.User;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Service;

@Service
public class SocialUserServiceImpl implements SocialUserService {

    @Autowired
    private SocialUserRepository socialUserRepository;

    @Autowired
    private RoleService roleService;

    @Override
    public void registerOrLogin(User facebookUser) {
        String email = facebookUser.getEmail();
        SocialUser socialUser = this.socialUserRepository.findOneByUsername(email);
        if (socialUser == null){
            socialUser = registerUser(email);
        }
        loginUser(socialUser);
    }

    @Override
    public void registerOrLogin(TwitterProfile twitter) {
        String name = twitter.getName();
        SocialUser socialUser = this.socialUserRepository.findOneByUsername(name);
        if (socialUser == null){
            socialUser = registerUser(name);
        }
        loginUser(socialUser);
    }

    private SocialUser registerUser(String email){
        SocialUser socialUser = new SocialUser();
        socialUser.setUsername(email);
        if (email.contains("@")){
            socialUser.setProvider(Provider.FACEBOOK.name());
        }else {
            socialUser.setProvider(Provider.TWITTER.name());
        }
        socialUser.setAccountNonExpired(true);
        socialUser.setAccountNonLocked(true);
        socialUser.setCredentialsNonExpired(true);
        socialUser.setEnabled(true);
        socialUser.setCredentialsNonExpired(true);
        socialUser.addRole(this.roleService.getDefaultRole());
        this.socialUserRepository.save(socialUser);
        return socialUser;
    }

    private void loginUser(SocialUser socialUser){
        Authentication authentication = new UsernamePasswordAuthenticationToken(socialUser.getUsername(), null, socialUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
