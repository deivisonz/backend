package br.com.agrosoftware.agrosoftware.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.agrosoftware.agrosoftware.enums.Funcao;
import br.com.agrosoftware.agrosoftware.exceptions.AuthorizationException;
import br.com.agrosoftware.agrosoftware.exceptions.DataIntegrityException;
import br.com.agrosoftware.agrosoftware.exceptions.ObjectNotFoundException;
import br.com.agrosoftware.agrosoftware.models.Usuario;
import br.com.agrosoftware.agrosoftware.repositories.UsuarioRepository;
import br.com.agrosoftware.agrosoftware.security.UsuarioJWT;

@Service
public class UsuarioService {
    
    @Autowired UsuarioRepository usuarioRepo;
    @Autowired BCryptPasswordEncoder pe;
    
    public Usuario find(int id) {
        var usuarioJwt = getUsuarioJWT();
        if (usuarioJwt == null || (!usuarioJwt.hasRole(Funcao.ADMIN) && id != usuarioJwt.getId()))
            throw new AuthorizationException("Acesso negado.");
        
        return usuarioRepo.findById(id).orElseThrow(() -> new ObjectNotFoundException(Usuario.class.getName() + " não encontrado(a)."));
    }
    
    public void insert(Usuario usuario) {
        if (usuarioRepo.findByUsuDsEmail(usuario.getUsuDsEmail()) != null)
            throw new DataIntegrityException("E-mail já existente.");
        
        usuario.setUsuDsSenha(pe.encode(usuario.getUsuDsSenha()));        
        usuarioRepo.save(usuario);
    }
    
    public void update(Usuario usuario) {
        if (usuarioRepo.findByUsuDsEmailAndUsuCdUsuarioNotIn(usuario.getUsuDsEmail(), usuario.getUsuCdUsuario()) != null)
            throw new DataIntegrityException("E-mail já existente.");
        
        usuarioRepo.save(usuario);
    }
    
    public void delete(int id) {
        try {
            usuarioRepo.delete(find(id));
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException(Usuario.class.getName() + " possui vínculos com outros cadastros. Não é possível excluir.");
        }
    }
    
    public UsuarioJWT getUsuarioJWT() {
        try {
            return (UsuarioJWT) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }
    
    public Usuario getAuthenticated() {
        try {
            return find(getUsuarioJWT().getId());
        } catch (Exception e) {
            return null;
        }
    }
}
