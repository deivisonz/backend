package br.com.agrosoftware.agrosoftware.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.agrosoftware.agrosoftware.repositories.UsuarioRepository;
import br.com.agrosoftware.agrosoftware.security.UsuarioJWT;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired private UsuarioRepository usuarioRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var usuario = usuarioRepo.findByUsuDsEmail(email);
        if (usuario == null)
            throw new UsernameNotFoundException(email);
            
        return new UsuarioJWT(usuario.getUsuCdUsuario(), usuario.getUsuDsEmail(), usuario.getUsuDsSenha(), usuario.getUsuLsFuncoes());
    }

}
