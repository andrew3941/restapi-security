package com.preving.restapi.seguridadApi.service;

import com.preving.restapi.seguridadApi.jwt.JwtUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by rogeliogragera on 12/5/17.
 */
public interface JwtUserDetailsService extends UserDetailsService {

    UserDetails loadUserById(long id) throws UsernameNotFoundException;

    JwtUser loadJwtUserByUsername(String username) throws UsernameNotFoundException;
}
