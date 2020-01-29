package com.preving.restapi.seguridadApi.conf;

import com.preving.restapi.seguridadApi.dao.IntranetUserRepository;
import com.preving.restapi.seguridadApi.domain.IntranetUser;
import com.preving.restapi.seguridadApi.jwt.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.Optional;

/**
 * Creado por David el dia 16/04/2018.
 */
@EnableJpaAuditing
@Configuration
public class AuditingConfig {

    @Bean
    public AuditorAware<IntranetUser> createAuditorProvider() {
        return new SecurityAuditor();
    }

    public static class SecurityAuditor implements AuditorAware<IntranetUser> {
        @Autowired
        IntranetUserRepository usuariosRepository;

        @Override
        public Optional<IntranetUser> getCurrentAuditor() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            JwtUser user = authentication == null || !authentication.isAuthenticated() ?
                    null :
                    (JwtUser) authentication.getPrincipal();

            Long usuarioId = Objects.requireNonNull(user).getId();
            return this.usuariosRepository.findById(usuarioId);
        }
    }
}