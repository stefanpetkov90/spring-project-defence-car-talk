package com.springprojectdefence.controller;

import com.springprojectdefence.service.SocialUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterObject;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

@Controller
public class TwitterLoginController {

    @Autowired
    private SocialUserService socialUserService;

    private Twitter twitter;

    private ConnectionRepository connectionRepository;

    public TwitterLoginController(Twitter twitter, ConnectionRepository connectionRepository) {
        this.twitter = twitter;
        this.connectionRepository = connectionRepository;
    }

    @GetMapping("/twitter")
    public String helloTwitter(Model model) {
        model.addAttribute("title", "Twitter Log In");
        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            return "redirect:/connect/twitter";
        }
        TwitterProfile twitterUser = this.twitter.userOperations().getUserProfile();
        this.socialUserService.registerOrLogin(twitterUser);
        return "redirect:/";
    }

}
