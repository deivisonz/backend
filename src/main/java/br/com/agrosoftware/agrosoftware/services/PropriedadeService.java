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
    	var propriedade = proprietario.getUsuCdPropriedade();
    	propriedadeRepo.save(propriedade);
    	proprietario.setUsuCdPropriedade(propriedade);
    	
        proprietario.getUsuLsFuncoes().add(Funcao.ADMIN);
        proprietario.getUsuLsPermissoes().addAll(Set.of(Permissao.values()));
        usuarioService.insert(proprietario);
    }
    
    public void update(Propriedade propriedade) {
    	propriedadeRepo.save(propriedade);
    }

}
