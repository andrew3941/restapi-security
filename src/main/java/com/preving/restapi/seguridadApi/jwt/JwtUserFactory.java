package com.preving.restapi.seguridadApi.jwt;

import com.preving.restapi.seguridadApi.domain.Authority;
import com.preving.restapi.seguridadApi.domain.ExtranetUser;
import com.preving.restapi.seguridadApi.domain.IntranetUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(IntranetUser user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getAuthorities()),
                user.getEnabled(),
                user.getLastPasswordResetDate()
        );
    }

    public static JwtUser create(ExtranetUser user, String hash) {
        return new JwtUser(
                user.getId(),
                user.getEmail(), //email
                user.getName(), //nombre
                user.getSurname(), //apellidos
                user.getEmail(),
                hash,
                new ArrayList<GrantedAuthority>(),
                user.getEnabled(),
                null
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
        return authorities.stream()
                .map(authority ->
                        new SimpleGrantedAuthority((authority.getAplicacion().getCodigoApp() != null ?
                                authority.getAplicacion().getCodigoApp() : authority.getAplicacion().getId()) +
                                "-" + authority.getId()))
                .collect(Collectors.toList());
    }
}
