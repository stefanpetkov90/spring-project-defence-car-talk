package com.springprojectdefence.service;


import org.springframework.social.facebook.api.User;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;

public interface SocialUserService {
    void registerOrLogin(User facebookUser);
    void registerOrLogin(TwitterProfile twitterProfile);
}
