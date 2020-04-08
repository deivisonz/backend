package br.com.agrosoftware.agrosoftware.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import br.com.agrosoftware.agrosoftware.enums.Cultivo;
import br.com.agrosoftware.agrosoftware.enums.UF;

@Entity
public class Propriedade implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message = "Nome é obrigatório.")
	@Size(max = 60, message = "Nome: Máximo de 60 caracteres permitido.")
	private String nome;
	
	@Size(max = 500, message = "Descrição: Máximo de 500 caracteres permitido.")
	private String descricao;
	
	@Positive(message = "Tamanho em Hectares não pode ser um valor negativo.")
	private double tamanhoHectares;
	
	@NotNull(message = "Cultivo Principal deve ser informado.")
	private Cultivo cultivo;
	
	@NotNull(message = "Unidade Federativa é obrigatório.")
	private UF uf;
	
	private boolean ativo = true;
	
	public Propriedade() {
    }
	
    public Propriedade(String nome) {
        super();
        this.nome = nome;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
}
