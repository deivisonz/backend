package br.com.agrosoftware.agrosoftware.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agrosoftware.agrosoftware.exceptions.ObjectNotFoundException;
import br.com.agrosoftware.agrosoftware.models.Propriedade;
import br.com.agrosoftware.agrosoftware.repositories.PropriedadeRepository;

@Service
public class PropriedadeService {
	@Autowired PropriedadeRepository propriedadeRepo;
	@Autowired UsuarioService usuarioService;
    
	public Propriedade find(int id) {
	    return propriedadeRepo.findById(id).orElseThrow(() -> new ObjectNotFoundException(Propriedade.class.getName() + " não encontrado(a)."));
	}
 
    public void update(Propriedade propriedade) {
    	propriedadeRepo.save(propriedade);
    }

}
