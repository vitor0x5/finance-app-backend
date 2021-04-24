package io.github.vitor0x5.domains.user.services.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class AuthenticationFilterService extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserAuthenticateService userAuthenticateService;

    public AuthenticationFilterService(JwtService jwtService, UserAuthenticateService userAuthenticateService) {
        this.jwtService = jwtService;
        this.userAuthenticateService = userAuthenticateService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorization = httpServletRequest.getHeader("Authorization");

        if(authorization != null && authorization.startsWith("Bearer")) {
            String token = authorization.split(" ")[1];
            if(jwtService.tokenIsValid(token)){
                String userEmail = jwtService.getLoggedUserEmail(token);
                UserDetails user = userAuthenticateService.loadUserByUsername(userEmail);

                UsernamePasswordAuthenticationToken userAuthentication = new
                        UsernamePasswordAuthenticationToken(
                                user,
                        null,
                                user.getAuthorities());

                userAuthentication.setDetails(new
                        WebAuthenticationDetailsSource()
                        .buildDetails(httpServletRequest));

                SecurityContextHolder.getContext().setAuthentication(userAuthentication);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
