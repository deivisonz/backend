package br.com.agrosoftware.agrosoftware.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.agrosoftware.agrosoftware.models.Predicao;
import br.com.agrosoftware.agrosoftware.services.DBService;

@RestController
@RequestMapping("/predicao")
public class PredicaoController {
	
	@Autowired private DBService dBService;

	@GetMapping("/anual")
    public ResponseEntity<List<Predicao>> findAll(@RequestParam int idCultivo) throws Exception {
		List<Predicao> predicao = dBService.predicaoClima(idCultivo);
		
        return new ResponseEntity<List<Predicao>>(predicao ,HttpStatus.OK);
    }

}
