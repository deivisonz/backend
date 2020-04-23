package br.com.agrosoftware.agrosoftware.controllers;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agrosoftware.agrosoftware.enums.Funcao;
import br.com.agrosoftware.agrosoftware.models.Usuario;
import br.com.agrosoftware.agrosoftware.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired UsuarioService usuarioService;
    
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> find(@PathVariable int id) {
        return ResponseEntity.ok().body(usuarioService.find(id));
    }
    
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody List<Usuario> usuarios) {
        usuarios.forEach(usuarioService::insert);
        return ResponseEntity.created(null).build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody Usuario usuario) {
        var newUsuario = usuarioService.find(id);
        BeanUtils.copyProperties(usuario, newUsuario, "id", "senha");
        usuarioService.update(newUsuario);
        
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/authenticated")
    public ResponseEntity<Usuario> getAuthenticated() {
        var usuario = usuarioService.getAuthenticated();
        usuario.setUsuLsFuncoes(usuario.getUsuLsFuncoes().stream().sorted(Comparator.comparingInt(Funcao::ordinal).reversed()).collect(Collectors.toSet()));
        return ResponseEntity.ok().body(usuario);
    }
}