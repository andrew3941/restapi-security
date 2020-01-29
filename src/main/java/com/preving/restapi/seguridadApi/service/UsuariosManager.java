package com.preving.restapi.seguridadApi.service;


import com.preving.restapi.seguridadApi.dao.SeguridadDao;
import com.preving.restapi.seguridadApi.domain.crypt.Encryptor;
import com.preving.restapi.seguridadApi.exceptions.CustomRestApiException;
import com.preving.restapi.seguridadApi.exceptions.errors.RestApiErrorCode;
import com.preving.restapi.seguridadApi.exceptions.errors.RestApiErrorDetail;
import com.preving.restapi.seguridadApi.jwt.JwtTokenUtil;
import org.bouncycastle.crypto.CryptoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by rogeliogragera on 15/3/17.
 */
@Service
public class UsuariosManager implements UsuariosService {

    @Value("${frase.autenticacion.intranet}")
    private String fraseAutenticacion;

    @Autowired
    private SeguridadDao seguridadDao;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    public String getHashByUsuarioId(String usuarioId){
        Encryptor e = new Encryptor(fraseAutenticacion);
        try {
            return e.encryptAndHashString(usuarioId);
        } catch (CryptoException e1) {
            e1.printStackTrace();

            List<RestApiErrorDetail> errores = new ArrayList<>();
            errores.add(new RestApiErrorDetail("error.codificacion.unicidad", RestApiErrorCode.ERROR_CODIFICACION_UNICIDAD.getMessage()));
            throw new CustomRestApiException(HttpStatus.INTERNAL_SERVER_ERROR, RestApiErrorCode.ERROR_CODIFICACION_UNICIDAD, errores);
        }
    }

    public Authentication getAuthentication(Authentication authentication) {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        // use the credentials
        // and authenticate against the third-party system
        boolean authenticated = this.seguridadDao.authenticateFromIntranet(username, password);

        if (authenticated) {
            return new UsernamePasswordAuthenticationToken(username, password);

        } else {
            return null;
        }
    }

    public void securityFilter(HttpServletRequest request) {
        String authToken = this.jwtTokenUtil.getTokenFromHeader(request);
        String username = this.jwtTokenUtil.getUsernameFromToken(authToken);

        // Modificamos para que esta funcionalidad tan solo salga en modo debug.
        // No obstante se comenta, hasta probar esto correctamente
        // logger.debug("Verificando autenticación para el usuario: " + username);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // It is not compelling necessary to load the use details from the database. You could also store the information
            // in the token and read it from it. It's up to you ;)
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            // For simple validation it is completely sufficient to just check the token integrity. You don't have to call
            // the database compellingly. Again it's up to you ;)
            if (userDetails != null &&
                    this.jwtTokenUtil.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Modificamos para que esta funcionalidad tan solo salga en modo debug.
                // No obstante se comenta, hasta probar esto correctamente
                // logger.debug("Usuario autenticado " + username + ", configurando contexto de seguridad");

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
    }



}
