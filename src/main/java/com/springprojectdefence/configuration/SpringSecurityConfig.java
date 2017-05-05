package com.springprojectdefence.configuration;

import com.springprojectdefence.service.BasicUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.social.config.annotation.EnableSocial;


@Configuration
@EnableWebSecurity
@EnableSocial
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BasicUserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userService).passwordEncoder(getbCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/register/**", "/twitter/**","/connect/**", "/signin/**", "/facebook", "/bootstrap/**", "/jquery/**", "/cookies/**")
                .permitAll()
                .antMatchers("/user/**").access("hasRole('ADMIN') OR hasRole('USER')")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .usernameParameter("username").passwordParameter("password")
                .and().rememberMe()
                .rememberMeCookieName("RememberMeIAmYourMaker!")
                .rememberMeParameter("rememberMe")
                .tokenValiditySeconds(10000)
                .key("Soft Uni defence project encryption key")
                .and().logout().logoutSuccessUrl("/login?logout").permitAll()
                .and().exceptionHandling().accessDeniedPage("/unauthorized")
                .and()
                .csrf().disable();
    }

    @Bean
    public BCryptPasswordEncoder getbCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    public CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
        httpSessionCsrfTokenRepository.setSessionAttributeName("_csrf");
        return httpSessionCsrfTokenRepository;
    }
}

