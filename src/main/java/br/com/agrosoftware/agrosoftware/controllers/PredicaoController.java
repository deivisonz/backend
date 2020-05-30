package br.com.agrosoftware.agrosoftware.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/predicao")
public class PredicaoController {

	@GetMapping("/all")
    public ResponseEntity<Integer> findAll() {
        return new ResponseEntity<Integer>(0 ,HttpStatus.OK);
    }
}
