package br.com.agrosoftware.agrosoftware.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agrosoftware.agrosoftware.models.Propriedade;
import br.com.agrosoftware.agrosoftware.services.PropriedadeService;

@RestController
@RequestMapping("/propriedades")
public class PropriedadeController {
	@Autowired PropriedadeService propriedadeService;

	@PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody Propriedade propriedade) {
	    var novaPropriedade = propriedadeService.find(id);
	    BeanUtils.copyProperties(propriedade, novaPropriedade, "id");
	    propriedadeService.update(novaPropriedade);
	    
        return ResponseEntity.noContent().build();
    }
}
