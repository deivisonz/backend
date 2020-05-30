package br.com.agrosoftware.agrosoftware.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.agrosoftware.agrosoftware.models.Predicao;

@Repository
public interface PredicaoRepository  extends JpaRepository<Predicao, Integer> {

}
