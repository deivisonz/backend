package br.com.agrosoftware.agrosoftware.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.agrosoftware.agrosoftware.enums.Cultivo;
import br.com.agrosoftware.agrosoftware.enums.Funcao;
import br.com.agrosoftware.agrosoftware.enums.Permissao;
import br.com.agrosoftware.agrosoftware.enums.UF;
import br.com.agrosoftware.agrosoftware.models.Propriedade;
import br.com.agrosoftware.agrosoftware.models.Usuario;
import br.com.agrosoftware.agrosoftware.repositories.PropriedadeRepository;
import br.com.agrosoftware.agrosoftware.repositories.UsuarioRepository;

@Service
public class DBService {
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private PropriedadeRepository propriedadeRepository;
    @Autowired BCryptPasswordEncoder pe;
    
    public void instantiateDatabase() {   
        // Usuários
        var usuarioDeivison = new Usuario("Deivison", "deivison@erlacher.com", pe.encode("112233")); 
        var usuarioVinicius = new Usuario("Vinicius", "vinicius@barth.com", pe.encode("123456")); 
        usuarioDeivison.getFuncoes().add(Funcao.ADMIN);
        usuarioVinicius.getPermissoes().addAll(Set.of(Permissao.values())); 
        
        //Propriedades
        var propriedade1 = new Propriedade("Sítio Monte Belo", 1.5, Cultivo.MILHO, UF.ES); 
        var propriedade2 = new Propriedade("Sítio Boa Fé", 3.2, Cultivo.CAFE, UF.ES);
        propriedadeRepository.saveAll(List.of(propriedade1, propriedade2));
        
        usuarioDeivison.setPropriedade(propriedade1);
        usuarioVinicius.setPropriedade(propriedade2);
        usuarioRepository.saveAll(List.of(usuarioDeivison, usuarioVinicius));      
        
    }
    
}
