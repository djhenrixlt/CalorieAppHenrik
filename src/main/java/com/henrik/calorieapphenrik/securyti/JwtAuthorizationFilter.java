package com.henrik.calorieapphenrik.securyti;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.henrik.calorieapphenrik.securyti.SecurityConstants.AUTHORIZATION_HEADER;
import static com.henrik.calorieapphenrik.securyti.SecurityConstants.AUTHORIZATION_HEADER_PREFIX;
import static org.apache.logging.log4j.util.Strings.isNotEmpty;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private JwtProvider jwtProvider;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
                                  JwtProvider jwtProvider) {
        super(authenticationManager);
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain)
            throws IOException, ServletException {

        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);

        if(isNotEmpty(authorizationHeader) && authorizationHeader
                .startsWith(AUTHORIZATION_HEADER_PREFIX)){
            String jwt = authorizationHeader.replace(AUTHORIZATION_HEADER_PREFIX, "");
            UsernamePasswordAuthenticationToken authentication = jwtProvider.getAuthentication(jwt);

            if(authentication == null){
                chain.doFilter(request, response);
                return;
            }
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }
}
