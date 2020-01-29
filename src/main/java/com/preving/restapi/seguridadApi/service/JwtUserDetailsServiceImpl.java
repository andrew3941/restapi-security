package com.preving.restapi.seguridadApi.service;

import com.preving.restapi.seguridadApi.dao.IntranetUserRepository;
import com.preving.restapi.seguridadApi.domain.IntranetUser;
import com.preving.restapi.seguridadApi.exceptions.CustomRestApiException;
import com.preving.restapi.seguridadApi.exceptions.errors.RestApiErrorCode;
import com.preving.restapi.seguridadApi.exceptions.errors.RestApiErrorDetail;
import com.preving.restapi.seguridadApi.jwt.JwtUser;
import com.preving.restapi.seguridadApi.jwt.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by stephan on 20.03.16.
 */
@Service
public class JwtUserDetailsServiceImpl implements JwtUserDetailsService {

    @Autowired
    private IntranetUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        IntranetUser user = userRepository.findUserByUsername(username);
        if (user == null) {
            //List<RestApiErrorDetail> errores = new ArrayList<>();
            //errores.add(new RestApiErrorDetail("user.not.found", RestApiErrorCode.USER_NOT_FOUND.getMessage()));
            //throw new CustomRestApiException(HttpStatus.BAD_REQUEST, RestApiErrorCode.USER_NOT_FOUND, errores);

            // Devolvemos null para indicar al metodo que lo llama que el usuario no se ha encontrado, y que la validacion no va a ser correcta
            return null;
        } else {
            return JwtUserFactory.create(user);
        }
    }

    public JwtUser loadJwtUserByUsername(String username) throws UsernameNotFoundException {
        IntranetUser user = userRepository.findUserByUsername(username);
        if (user == null) {
            //List<RestApiErrorDetail> errores = new ArrayList<>();
            //errores.add(new RestApiErrorDetail("user.not.found", RestApiErrorCode.USER_NOT_FOUND.getMessage()));
            //throw new CustomRestApiException(HttpStatus.BAD_REQUEST, RestApiErrorCode.USER_NOT_FOUND, errores);

            // Devolvemos null para indicar al metodo que lo llama que el usuario no se ha encontrado, y que la validacion no va a ser correcta
            return null;
        } else {
            return JwtUserFactory.create(user);
        }
    }

    @Override
    public UserDetails loadUserById(long id) throws UsernameNotFoundException {
        IntranetUser user = userRepository.findUserById(id);
        if (user == null) {
            List<RestApiErrorDetail> errores = new ArrayList<>();
            errores.add(new RestApiErrorDetail("user.not.found", RestApiErrorCode.USER_NOT_FOUND.getMessage()));
            throw new CustomRestApiException(HttpStatus.BAD_REQUEST, RestApiErrorCode.USER_NOT_FOUND, errores);
        } else {
            return JwtUserFactory.create(user);
        }
    }
}
