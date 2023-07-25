package com.umerscode.employeemanager.Jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JwtUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
               HttpServletResponse response) throws AuthenticationException {

        try {
            AuthenticationRequest   authenticationRequest = new ObjectMapper()
                    .readValue(request.getInputStream(), AuthenticationRequest.class);
            System.out.println(authenticationRequest);


        Authentication authentication = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(),authenticationRequest.getPassword());

        Authentication authenticate = authenticationManager.authenticate(authentication);
        return authenticate;
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
