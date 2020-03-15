package br.com.agrosoftware.agrosoftware.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.agrosoftware.agrosoftware.enums.Funcao;
import br.com.agrosoftware.agrosoftware.enums.Permissao;
import br.com.agrosoftware.agrosoftware.models.Usuario;
import br.com.agrosoftware.agrosoftware.repositories.UsuarioRepository;

@Service
public class DBService {
    @Autowired private UsuarioRepository usuarioRepo;
    @Autowired BCryptPasswordEncoder pe;
    
    public void instantiateDatabase() {   
        // Usuários
        var usuarioDeivison = new Usuario("Deivison", "deivison@erlacher.com", pe.encode("112233"), "1234-5678"); 
        var usuarioVinicius = new Usuario("Vinicius", "vinicius@barth.com", pe.encode("123456"), "99999-8888"); 
        usuarioDeivison.getFuncoes().add(Funcao.ADMIN);
        usuarioVinicius.getPermissoes().addAll(Set.of(Permissao.values()));
        usuarioRepo.saveAll(List.of(usuarioDeivison, usuarioVinicius));       
    }
    
}
