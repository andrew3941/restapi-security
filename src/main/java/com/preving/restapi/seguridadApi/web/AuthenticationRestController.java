package com.preving.restapi.seguridadApi.web;


import com.preving.restapi.seguridadApi.exceptions.CustomRestApiException;
import com.preving.restapi.seguridadApi.exceptions.NotValidRestApiException;
import com.preving.restapi.seguridadApi.exceptions.errors.RestApiErrorCode;
import com.preving.restapi.seguridadApi.exceptions.errors.RestApiErrorDetail;
import com.preving.restapi.seguridadApi.jwt.JwtAuthenticationRequest;
import com.preving.restapi.seguridadApi.jwt.JwtTokenUtil;
import com.preving.restapi.seguridadApi.jwt.JwtUser;
import com.preving.restapi.seguridadApi.service.ExtranetUserService;
import com.preving.restapi.seguridadApi.service.JwtAuthenticationResponse;
import com.preving.restapi.seguridadApi.service.JwtUserDetailsService;
import com.preving.restapi.seguridadApi.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationRestController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private ExtranetUserService extranetUserService;

    @Autowired
    private UsuariosService usuariosService;

    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> login(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {

        // Perform the security
        try {
            final Authentication authentication = this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Reload password post-security so we can generate token
            final UserDetails userDetails = this.userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

            final String token = this.jwtTokenUtil.generateToken(userDetails);

            // Return the token
            return ResponseEntity.ok(new JwtAuthenticationResponse(token));
        } catch (ProviderNotFoundException e) {
            e.printStackTrace();

            List<RestApiErrorDetail> errores = new ArrayList<>();
            errores.add(new RestApiErrorDetail("user.incorrect", RestApiErrorCode.USER_INCORRECT.getMessage()));
            throw new CustomRestApiException(HttpStatus.INTERNAL_SERVER_ERROR, RestApiErrorCode.USER_INCORRECT, errores);
        }
    }

    @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String authToken = this.jwtTokenUtil.getTokenFromHeader(request);
        String username = this.jwtTokenUtil.getUsernameFromToken(authToken);

        JwtUser user = (JwtUser) this.userDetailsService.loadUserByUsername(username);

        if (this.jwtTokenUtil.canTokenBeRefreshed(authToken, user.getLastPasswordResetDate())) {
            String refreshedToken = this.jwtTokenUtil.refreshToken(authToken);
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));

        } else {
            return ResponseEntity.badRequest().body(null);

        }
    }

    @RequestMapping(value = "/login/{usuarioId}/{hash}", method = RequestMethod.GET)
    public ResponseEntity<?> loginParams(
            @PathVariable("usuarioId") String usuarioId,
            @PathVariable("hash") String hash
    ) throws AuthenticationException {
        String token = null;
        String hashCalculado = this.usuariosService.getHashByUsuarioId(usuarioId);

        if (!hash.isEmpty() && hashCalculado.equals(hash)) {
            // Reload password post-security so we can generate token
            final UserDetails userDetails = this.userDetailsService.loadUserById(Long.parseLong(usuarioId));

            token = this.jwtTokenUtil.generateToken(userDetails);
        } else {
            sendAuthError();
        }
        // Return the token
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @RequestMapping(value = "/login-extranet/{id}/{hash}", method = RequestMethod.GET)
    public ResponseEntity<?> loginExtranetParams(
            @PathVariable("id") String usuarioId,
            @PathVariable("hash") String hash
    ) throws AuthenticationException {

        String hashCalculado = this.extranetUserService.getHashByUsuarioId(usuarioId);
        if (hash.isEmpty() || !hashCalculado.equals(hash))
            sendAuthError();
      if(!extranetUserService.hasActiveSession(usuarioId))
          sendAuthError();

        final UserDetails userDetails = this.extranetUserService.findUserById(usuarioId, hash);

        String token = this.jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    private void sendAuthError() throws NotValidRestApiException {
        List<RestApiErrorDetail> errores = new ArrayList<>();
        errores.add(new RestApiErrorDetail("user.incorrect", RestApiErrorCode.USER_INCORRECT.getMessage()));
        throw new NotValidRestApiException(RestApiErrorCode.USER_INCORRECT, errores);
    }

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public String getToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest
    ) throws AuthenticationException {

        final Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = this.userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        return this.jwtTokenUtil.generateToken(userDetails);
    }

    @RequestMapping(value = "/check-authentication", method = RequestMethod.POST)
    public Authentication authenticate(
           @RequestBody Authentication authentication
    ) throws AuthenticationException {

       return this.usuariosService.getAuthentication(authentication);
    }

    @RequestMapping(value = "/get-user-by-username", method = RequestMethod.POST)
    public Map filter(HttpServletRequest request, @RequestBody String username
    ) throws AuthenticationException {

        Map map = new HashMap();

        String authToken = this.jwtTokenUtil.getTokenFromHeader(request);
        JwtUser userDetails =  this.userDetailsService.loadJwtUserByUsername(username);

        if (userDetails != null &&
                this.jwtTokenUtil.validateToken(authToken, userDetails)) {
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null);

            map.put("principal", authentication.getPrincipal());

            map.put("authorities", userDetails.getAuthorities());

            return map;
        }

        return null;
    }
}
