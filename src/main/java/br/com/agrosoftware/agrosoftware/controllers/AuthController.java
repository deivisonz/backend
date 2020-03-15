package br.com.agrosoftware.agrosoftware.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agrosoftware.agrosoftware.security.JWTUtil;
import br.com.agrosoftware.agrosoftware.services.UsuarioService;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired UsuarioService usuarioService;
    @Autowired JWTUtil jwtUtil;

    @PostMapping("/refresh_token")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        var usuario = usuarioService.getUsuarioJWT();
        response.addHeader("Authorization", "Bearer " + jwtUtil.generateToken(usuario.getUsername()));
        return ResponseEntity.noContent().build();
    }

}
