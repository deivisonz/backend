package br.com.agrosoftware.agrosoftware.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.agrosoftware.agrosoftware.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Transactional(readOnly = true)
    Usuario findByUsuDsEmail(String email);
    
    @Transactional(readOnly = true)
    Usuario findByUsuDsEmailAndUsuCdUsuarioNotIn(String email, int id);
       
}
