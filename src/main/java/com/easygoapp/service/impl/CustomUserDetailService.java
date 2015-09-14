package com.easygoapp.service.impl;

import com.easygoapp.domain.User;
import com.easygoapp.domain.UserRole;
import com.easygoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stanislav Markov mailto: stasmarkov88@gmail.com
 */

@Component("customUserDetailsService")
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        //our domain entity User
        User userToAuthorise = userService.getByLogin(login);
        List<GrantedAuthority> authorities = buildUserAuthority(userToAuthorise.getUserRoles());

        return new org.springframework.security.core.userdetails.User(userToAuthorise.getLogin(),
                userToAuthorise.getPassword(), authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(List<UserRole> userRoles) {

        List<SimpleGrantedAuthority> listAuth = new ArrayList<>();
        // Build user's authorities
        for (UserRole userRole : userRoles) {
            listAuth.add(new SimpleGrantedAuthority(userRole.getRole()));
        }
        return new ArrayList<GrantedAuthority>(listAuth);
    }
}
