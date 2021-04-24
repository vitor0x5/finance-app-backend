package io.github.vitor0x5.config;

import io.github.vitor0x5.domains.user.services.authentication.AuthenticationFilterService;
import io.github.vitor0x5.domains.user.services.authentication.JwtService;
import io.github.vitor0x5.domains.user.services.authentication.UserAuthenticateService;
import io.github.vitor0x5.shared.encoder.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserAuthenticateService userAuthenticateService;

    private final AuthenticationFilterService authenticationFilterService;

    public SecurityConfiguration(UserAuthenticateService userAuthenticateService, AuthenticationFilterService authenticationFilterService) {
        this.userAuthenticateService = userAuthenticateService;
        this.authenticationFilterService = authenticationFilterService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Encoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userAuthenticateService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/sign/**")
                    .permitAll()
                .antMatchers("/user/**")
                    .hasRole("ADMIN")
                .anyRequest().authenticated()
            .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .addFilterBefore( authenticationFilterService, UsernamePasswordAuthenticationFilter.class);
        ;
    }
}
