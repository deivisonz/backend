package br.com.agrosoftware.agrosoftware.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


@Entity
public class Cultura implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message = "Nome é obrigatório.")
	@Size(max = 60, message = "Nome: Máximo de 60 caracteres permitido.")
	private String culDsNome;

	@Positive(message = "O número ideal de mm não pode ser negativo.")
	private int CulMmIdeal;
	

	public Cultura() {
    }
	
    public Cultura(String culDsNome, int CulMmIdeal) {
        super();
        this.culDsNome = culDsNome;
        this.CulMmIdeal = CulMmIdeal;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCulDsNome() {
		return culDsNome;
	}

	public void setCulDsNome(String culDsNome) {
		this.culDsNome = culDsNome;
	}

	public int getCulMmIdeal() {
		return CulMmIdeal;
	}

	public void setCulMmIdeal(int culMmIdeal) {
		CulMmIdeal = culMmIdeal;
	}
	
	
}
