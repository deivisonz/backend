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
	
    public Propriedade(String nome, double tamanhoHectares, Cultivo cultivo, UF uf) {
        super();
        this.nome = nome;
        this.tamanhoHectares = tamanhoHectares;
        this.cultivo = cultivo;
        this.uf = uf;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getTamanhoHectares() {
		return tamanhoHectares;
	}

	public void setTamanhoHectares(double tamanhoHectares) {
		this.tamanhoHectares = tamanhoHectares;
	}

	public Cultivo getCultivo() {
		return cultivo;
	}

	public void setCultivo(Cultivo cultivo) {
		this.cultivo = cultivo;
	}

	public UF getUf() {
		return uf;
	}

	public void setUf(UF uf) {
		this.uf = uf;
	}
	
	
	
}
