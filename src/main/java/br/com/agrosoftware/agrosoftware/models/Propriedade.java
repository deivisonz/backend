package br.com.agrosoftware.agrosoftware.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import br.com.agrosoftware.agrosoftware.enums.Cultivo;
import br.com.agrosoftware.agrosoftware.enums.UF;

@Entity
public class Propriedade implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int proCdPropriedade;
	
	@NotNull(message = "Nome é obrigatório.")
	@Size(max = 60, message = "Nome: Máximo de 60 caracteres permitido.")
	private String proDsNome;
	
	@Size(max = 500, message = "Descrição: Máximo de 500 caracteres permitido.")
	private String proDsDescricao;
	
	@Positive(message = "Tamanho em Hectares não pode ser um valor negativo.")
	private double proVlTamanhoHectares;
	
	@Enumerated(EnumType.STRING)
	private UF proFlUf;
	
	@PositiveOrZero(message = "O número ideal de mm não pode ser negativo.")
	private int proMmIdeal;
	
	@OneToMany(mappedBy = "culCdPropriedade", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Cultura> culturas = new HashSet<>();
	
	private boolean proBlAtivo = true;
	
	public Propriedade() {
    }
	
    public Propriedade(String nome, String descricao, double tamanhoHectares, Cultivo cultivo, UF uf, int proMmIdeal) {
        super();
        this.proDsNome = nome;
        this.proDsDescricao = descricao;
        this.proVlTamanhoHectares = tamanhoHectares;
        this.proFlUf = uf;
        this.proMmIdeal = proMmIdeal;
    }

	public int getProCdPropriedade() {
		return proCdPropriedade;
	}

	public void setProCdPropriedade(int proCdPropriedade) {
		this.proCdPropriedade = proCdPropriedade;
	}

	public String getProDsNome() {
		return proDsNome;
	}

	public void setProDsNome(String proDsNome) {
		this.proDsNome = proDsNome;
	}

	public String getProDsDescricao() {
		return proDsDescricao;
	}

	public void setProDsDescricao(String proDsDescricao) {
		this.proDsDescricao = proDsDescricao;
	}

	public double getProVlTamanhoHectares() {
		return proVlTamanhoHectares;
	}

	public void setProVlTamanhoHectares(double proVlTamanhoHectares) {
		this.proVlTamanhoHectares = proVlTamanhoHectares;
	}

	public UF getProFlUf() {
		return proFlUf;
	}

	public void setProFlUf(UF proFlUf) {
		this.proFlUf = proFlUf;
	}

	public boolean isProBlAtivo() {
		return proBlAtivo;
	}

	public void setProBlAtivo(boolean proBlAtivo) {
		this.proBlAtivo = proBlAtivo;
	}

	public int getProMmIdeal() {
		return proMmIdeal;
	}

	public void setProMmIdeal(int proMmIdeal) {
		this.proMmIdeal = proMmIdeal;
	}

	public Set<Cultura> getCulturas() {
		return culturas;
	}

	public void setCulturas(Set<Cultura> culturas) {
		this.culturas = culturas;
	}
   	
	
}
