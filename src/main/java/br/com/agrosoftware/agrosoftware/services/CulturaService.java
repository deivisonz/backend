package br.com.agrosoftware.agrosoftware.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.agrosoftware.agrosoftware.exceptions.DataIntegrityException;
import br.com.agrosoftware.agrosoftware.exceptions.ObjectNotFoundException;
import br.com.agrosoftware.agrosoftware.models.Cultura;
import br.com.agrosoftware.agrosoftware.models.Usuario;
import br.com.agrosoftware.agrosoftware.repositories.CulturaRepository;

@Service
public class CulturaService {

	@Autowired CulturaRepository culturaRepository;
	
	public Cultura find(int id) {
	    return culturaRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(Cultura.class.getName() + " não encontrado(a)."));
	}
	
    public void insert(Cultura cultura) {       
    	culturaRepository.save(cultura);
    }
 
    public void update(Cultura cultura) {
    	culturaRepository.save(cultura);
    }
    
    public void delete(int id) {
        try {
        	culturaRepository.delete(find(id));
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException(Usuario.class.getName() + " possui vínculos com outros cadastros. Não é possível excluir.");
        }
    }
	
}
