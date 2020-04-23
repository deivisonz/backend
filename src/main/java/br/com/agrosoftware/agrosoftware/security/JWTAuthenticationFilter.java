package br.com.agrosoftware.agrosoftware.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.agrosoftware.agrosoftware.exceptions.Error;
import br.com.agrosoftware.agrosoftware.models.Usuario;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private JWTUtil jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {
            var creds = new ObjectMapper().readValue(req.getInputStream(), Usuario.class);
            var authToken = new UsernamePasswordAuthenticationToken(creds.getUsuDsEmail(), creds.getUsuDsSenha(), new ArrayList<>());
            var auth = authenticationManager.authenticate(authToken);
            
            return auth;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {
        var username = ((UsuarioJWT) auth.getPrincipal()).getUsername();
        var token = jwtUtil.generateToken(username);
        res.addHeader("Authorization", "Bearer " + token);
    }
    
    private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {
        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE); 
            
            final String msg;
            if (exception instanceof BadCredentialsException) {
                msg = "E-mail ou senha inválidos.";
            } else {
                msg = "Falha na autenticação.";
            }
            
            response.getWriter().append(new ObjectMapper().writeValueAsString(new Error(HttpStatus.UNAUTHORIZED, msg)));
        }
        
    }
}
