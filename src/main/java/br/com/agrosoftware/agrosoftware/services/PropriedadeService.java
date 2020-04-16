package br.com.agrosoftware.agrosoftware.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agrosoftware.agrosoftware.enums.Funcao;
import br.com.agrosoftware.agrosoftware.enums.Permissao;
import br.com.agrosoftware.agrosoftware.exceptions.ObjectNotFoundException;
import br.com.agrosoftware.agrosoftware.models.Propriedade;
import br.com.agrosoftware.agrosoftware.models.Usuario;
import br.com.agrosoftware.agrosoftware.repositories.PropriedadeRepository;

@Service
public class PropriedadeService {
	@Autowired PropriedadeRepository propriedadeRepo;
	@Autowired UsuarioService usuarioService;
    
	public Propriedade find(int id) {
	    return propriedadeRepo.findById(id).orElseThrow(() -> new ObjectNotFoundException(Propriedade.class.getName() + " não encontrado(a)."));
	}

    public void insert(Usuario proprietario) {
    	var propriedades = proprietario.getPropriedades();
    	propriedadeRepo.saveAll(propriedades);
     
    	if (propriedades.size() > 0) {
    		proprietario.setPropriedades(propriedades);
    	}
        proprietario.getFuncoes().add(Funcao.ADMIN);
        proprietario.getPermissoes().addAll(Set.of(Permissao.values()));
        usuarioService.insert(proprietario);
    }
    
    public void update(Propriedade propriedade) {
    	propriedadeRepo.save(propriedade);
    }

}
