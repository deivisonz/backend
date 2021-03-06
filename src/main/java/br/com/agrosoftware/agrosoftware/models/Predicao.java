package br.com.agrosoftware.agrosoftware.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Predicao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int preCdPredicao;
	
	private String preDsMesAno;
	
	private double preVlTemperaturaMedia;
	
	private double preVlPrecipitacao;
	
	private String preTxObservacaoTempMedia;
	
	private String preTxObservacaoPrecipitacao;
	
	private int prevlCorCard;
	
	@ManyToOne
    @JoinColumn(name = "pre_cd_propriedade")
    private Propriedade preCdPropriedade;
	
	public Predicao(String mesAno) {
        super();
        this.preDsMesAno = mesAno;
    }
	
	public int getPreCdPredicao() {
		return preCdPredicao;
	}

	public void setPreCdPredicao(int preCdPredicao) {
		this.preCdPredicao = preCdPredicao;
	}

	public String getPreDsMesAno() {
		return preDsMesAno;
	}
	
	public void setPreDsMesAno(String preDsMesAno) {
		this.preDsMesAno = preDsMesAno;
	}
	
	public double getPreVlTemperaturaMedia() {
		return preVlTemperaturaMedia;
	}
	
	public void setPreVlTemperaturaMedia(double preVlTemperaturaMedia) {
		this.preVlTemperaturaMedia = preVlTemperaturaMedia;
	}
	
	public double getPreVlPrecipitacao() {
		return preVlPrecipitacao;
	}
	
	public void setPreVlPrecipitacao(double preVlPrecipitacao) {
		this.preVlPrecipitacao = preVlPrecipitacao;
	}

	public String getPreTxObservacaoTempMedia() {
		return preTxObservacaoTempMedia;
	}

	public void setPreTxObservacaoTempMedia(String preTxObservacao) {
		this.preTxObservacaoTempMedia = preTxObservacao;
	}
	

	public int getPrevlCorCard() {
		return prevlCorCard;
	}

	public void setPrevlCorCard(int prevlCorCard) {
		this.prevlCorCard = prevlCorCard;
	}

	public String getPreTxObservacaoPrecipitacao() {
		return preTxObservacaoPrecipitacao;
	}

	public void setPreTxObservacaoPrecipitacao(String preTxObservacaoPrecipitacao) {
		this.preTxObservacaoPrecipitacao = preTxObservacaoPrecipitacao;
	}

	@Override
	public String toString() {
		return "Predicao [preDsMesAno=" + preDsMesAno + ", preVlTemperaturaMedia=" + preVlTemperaturaMedia
				+ ", preVlPrecipitacao=" + preVlPrecipitacao + "]";
	}
		
	
}
