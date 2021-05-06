package io.github.vitor0x5.config;

import io.github.vitor0x5.domains.user.services.authentication.AuthenticationFilterService;
import io.github.vitor0x5.domains.user.services.authentication.UserAuthenticateService;
import io.github.vitor0x5.shared.encoder.Encoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserAuthenticateService userAuthenticateService;

    private final AuthenticationFilterService authenticationFilterService;

    private final Encoder encoder;

    public SecurityConfiguration(UserAuthenticateService userAuthenticateService, AuthenticationFilterService authenticationFilterService, Encoder encoder) {
        this.userAuthenticateService = userAuthenticateService;
        this.authenticationFilterService = authenticationFilterService;
        this.encoder = encoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userAuthenticateService)
                .passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/sign/**")
                    .permitAll()
                .anyRequest().authenticated()
            .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .addFilterBefore( authenticationFilterService, UsernamePasswordAuthenticationFilter.class);
        ;
    }
}
