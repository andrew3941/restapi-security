package com.preving.restapi.seguridadApi.jwt;

import com.preving.restapi.seguridadApi.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * Created by javier-montesinos on 10/05/17.
 */
@Component
public class PrevingSSOAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UsuariosService usuariosService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return usuariosService.getAuthentication(authentication);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}


