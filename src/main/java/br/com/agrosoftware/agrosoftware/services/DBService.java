package br.com.agrosoftware.agrosoftware.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.agrosoftware.agrosoftware.enums.Cultivo;
import br.com.agrosoftware.agrosoftware.enums.Funcao;
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
        var usuarioVinicius = new Usuario("Vinicius", "vinicius@hotmil.com", pe.encode("123456")); 
        usuarioDeivison.getUsuLsFuncoes().add(Funcao.ADMIN);
        usuarioVinicius.getUsuLsFuncoes().add(Funcao.USUARIO);
        
        //Propriedades
        var propriedade1 = new Propriedade("Sítio Monte Belo", "Um sitio de merda", 1.5, Cultivo.MILHO, UF.ES); 
        var propriedade2 = new Propriedade("Sítio Boa Fé", "Um sitio de pouca merda", 3.2, Cultivo.CAFE, UF.ES);
        var propriedade3 = new Propriedade("Sítio Boa Viagem", "Um sitio de nenhuma merda", 5.0, Cultivo.CANA, UF.MG);
        propriedadeRepository.saveAll(List.of(propriedade1, propriedade2, propriedade3));
        
        //usuarioDeivison.setUsuCdPropriedade(propriedade1);
        //usuarioVinicius.setUsuCdPropriedade(propriedade2);
        usuarioRepository.saveAll(List.of(usuarioDeivison, usuarioVinicius));      
        
    }
    
}
