package br.com.agrosoftware.agrosoftware.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
public class Cultura implements Serializable{
	private static final long serialVersionUID = 1L;
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int culCdCultivo;
	
	@NotNull(message = "Nome é obrigatório.")
	@Size(max = 60, message = "Nome: Máximo de 60 caracteres permitido.")
	private String culDsNome;

	@Positive(message = "O número ideal de mm não pode ser negativo.")
	private int culVlMmIdeal;
	
	@Positive(message = "Informe temperatura mínima média ideal para o cultivo")
	private Double culVlTempMinIdeal;
	
	@Positive(message = "Informe temperatura máxima média ideal para o cultivo")
	private Double culVlTempMaxIdeal;	
	
	@ManyToOne
    @JoinColumn(name = "cul_cd_propriedade")
    //@NotNull(message = "Propriedade é obrigatório.")
    private Propriedade culCdPropriedade;

	public Cultura() {
    }
	
    public Cultura(String culDsNome, int culVlMmIdeal, Double culVlTempMinIdeal, Double culVlTempMaxIdeal, Propriedade culCdPropriedade) {
        super();
        this.culDsNome = culDsNome;
        this.culVlMmIdeal = culVlMmIdeal;
        this.culVlTempMinIdeal = culVlTempMinIdeal;
        this.culVlTempMaxIdeal = culVlTempMaxIdeal;
        this.culCdPropriedade = culCdPropriedade;
    }
 
	public int getCulCdCultivo() {
		return culCdCultivo;
	}

	public void setCulCdCultivo(int culCdCultivo) {
		this.culCdCultivo = culCdCultivo;
	}

	public String getCulDsNome() {
		return culDsNome;
	}

	public void setCulDsNome(String culDsNome) {
		this.culDsNome = culDsNome;
	}

	public int getCulVlMmIdeal() {
		return culVlMmIdeal;
	}

	public void setCulVlMmIdeal(int culVlMmIdeal) {
		this.culVlMmIdeal = culVlMmIdeal;
	}

	public Double getCulVlTempMinIdeal() {
		return culVlTempMinIdeal;
	}

	public void setCulVlTempMinIdeal(Double culVlTempMinIdeal) {
		this.culVlTempMinIdeal = culVlTempMinIdeal;
	}

	public Double getCulVlTempMaxIdeal() {
		return culVlTempMaxIdeal;
	}

	public void setCulVlTempMaxIdeal(double culVlTempMaxIdeal) {
		this.culVlTempMaxIdeal = culVlTempMaxIdeal;
	}
	
	
}
