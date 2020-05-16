package br.com.agrosoftware.agrosoftware.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agrosoftware.agrosoftware.models.Propriedade;
import br.com.agrosoftware.agrosoftware.repositories.PropriedadeRepository;
import br.com.agrosoftware.agrosoftware.services.PropriedadeService;

@RestController
@RequestMapping("/propriedades")
public class PropriedadeController {
	@Autowired PropriedadeService propriedadeService;
	@Autowired PropriedadeRepository propriedadeRepository;
	
	@PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody Propriedade propriedade) {
	    var novaPropriedade = propriedadeService.find(id);
	    BeanUtils.copyProperties(propriedade, novaPropriedade, "id");
	    propriedadeService.update(novaPropriedade);
	    
        return ResponseEntity.noContent().build();
    }
	
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
    	propriedadeService.delete(id);
        return ResponseEntity.noContent().build();
    }
	
    @GetMapping("/allpropriedades")
    public ResponseEntity<List<Propriedade>> findAll() {
    	List<Propriedade> propriedadeList = propriedadeRepository.findAll();
        return new  ResponseEntity<List<Propriedade>>(propriedadeList,HttpStatus.OK);
    }
}
