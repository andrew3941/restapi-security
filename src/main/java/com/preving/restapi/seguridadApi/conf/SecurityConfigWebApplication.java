package com.preving.restapi.seguridadApi.conf;

import com.preving.restapi.seguridadApi.jwt.CustomAccessDeniedHandler;
import com.preving.restapi.seguridadApi.jwt.JwtAuthenticationEntryPoint;
import com.preving.restapi.seguridadApi.jwt.JwtAuthenticationTokenFilter;
import com.preving.restapi.seguridadApi.jwt.PrevingSSOAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan
public class SecurityConfigWebApplication extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    /*

        No necesario en Spring 5

    @Autowired
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.authenticationProvider(this.previngSSOAuthenticationProvider);
    }*/

    /* Definiciones de beans para JWT */

        @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        /*@Bean(name = BeanIds.USER_DETAILS_SERVICE)
        @Override
        public UserDetailsService userDetailsService() {
            return super.userDetailsService();
        }*/

    /* Fin definiciones de beans */

    @Autowired
    private PrevingSSOAuthenticationProvider previngSSOAuthenticationProvider;


    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // we don't need CSRF because our token is invulnerable
        httpSecurity.csrf().disable()

            .exceptionHandling().authenticationEntryPoint(this.unauthorizedHandler).and()
            .exceptionHandling().accessDeniedHandler(this.customAccessDeniedHandler).and()

                // don't create session
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

            .authorizeRequests()
                // necesario para aceptar CORS OPTIONS
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

            .antMatchers("/auth/**/*")
                .permitAll();


            // Ejemplo de seguridad particular asociada a un metodo HTTP y un mapeo concreto en base a unos roles indicados en el fichero yml
            /*if(perfilesGet != null && !perfilesGet.equals("")){
                httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET, "/perfiles/**").access("hasAnyAuthority('" + perfilesGet + "')");
            }*/


            // Cerramos el acceso por defecto a todos los que no se indiquen explicitamente
            httpSecurity.authorizeRequests().antMatchers("/**").denyAll()

            .anyRequest()
                .authenticated();

        // Custom JWT based security filter
        httpSecurity
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        // disable page caching
        httpSecurity.headers().cacheControl();
    }
}
