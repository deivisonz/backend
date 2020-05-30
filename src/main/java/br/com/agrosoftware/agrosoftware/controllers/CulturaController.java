package br.com.agrosoftware.agrosoftware.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agrosoftware.agrosoftware.models.Cultura;
import br.com.agrosoftware.agrosoftware.repositories.CulturaRepository;
import br.com.agrosoftware.agrosoftware.services.CulturaService;

@RestController
@RequestMapping("/cultura")
public class CulturaController {
	@Autowired CulturaService culturaService;
	@Autowired CulturaRepository culturaRepository;
	
	
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Cultura propriedade) {
    	culturaService.insert(propriedade);
        return ResponseEntity.created(null).build();
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody Cultura cultura) {
	    var novaCultura = culturaService.find(id);
	    BeanUtils.copyProperties(cultura, novaCultura, "id");
	    culturaService.update(novaCultura);
	    
        return ResponseEntity.noContent().build();
    }
	
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
    	culturaService.delete(id);
        return ResponseEntity.noContent().build();
    }
	
    @GetMapping("/all")
    public ResponseEntity<List<Cultura>> findAll() {
    	List<Cultura> culturaList = culturaRepository.findAll();
        return new  ResponseEntity<List<Cultura>>(culturaList,HttpStatus.OK);
    }
}
