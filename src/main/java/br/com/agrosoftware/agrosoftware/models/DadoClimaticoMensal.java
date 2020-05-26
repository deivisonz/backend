package br.com.agrosoftware.agrosoftware.models;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import br.com.agrosoftware.agrosoftware.enums.Estacao;

@Entity
public class DadoClimaticoMensal implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message = "Estação deve ser informada.")
	private Estacao estacao;
	
	@NotNull(message = "Data deve ser informada.")
	private LocalDate data;
	
	@NotNull(message = "Hora(UTC) deve ser informada.")
	private int hora;
	
	private double direcaoVento;
	
	private double velocidadeVento;
	
	private double velocidadeVentoMaximaMedia;
	
	private double evaporacaoPiche;
	
	private double evapoBHPotencial;
	
	private double evapoBHReal;
	
	private double insolacaoTotal;
	
	private double NebulosidadeMedia;
	
	private int numDiasPrecipitacao;
	
	private double precipitacaoTotal;
	
	private double pressaoNivelMarMedia;
	
	private double pressaoMedia;
	
	private double tempMaximaMedia;
	
	private double tempCompensadaMedia;
	
	private double tempMinimaMedia;
	
	private double umidadeRelativaMedia;
	
	private double visibilidadeMedia;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Estacao getEstacao() {
		return estacao;
	}

	public void setEstacao(Estacao estacao) {
		this.estacao = estacao;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public double getDirecaoVento() {
		return direcaoVento;
	}

	public void setDirecaoVento(double direcaoVento) {
		this.direcaoVento = direcaoVento;
	}

	public double getVelocidadeVento() {
		return velocidadeVento;
	}

	public void setVelocidadeVento(double velocidadeVento) {
		this.velocidadeVento = velocidadeVento;
	}

	public double getVelocidadeVentoMaximaMedia() {
		return velocidadeVentoMaximaMedia;
	}

	public void setVelocidadeVentoMaximaMedia(double velocidadeVentoMaximaMedia) {
		this.velocidadeVentoMaximaMedia = velocidadeVentoMaximaMedia;
	}

	public double getEvaporacaoPiche() {
		return evaporacaoPiche;
	}

	public void setEvaporacaoPiche(double evaporacaoPiche) {
		this.evaporacaoPiche = evaporacaoPiche;
	}

	public double getEvapoBHPotencial() {
		return evapoBHPotencial;
	}

	public void setEvapoBHPotencial(double evapoBHPotencial) {
		this.evapoBHPotencial = evapoBHPotencial;
	}

	public double getEvapoBHReal() {
		return evapoBHReal;
	}

	public void setEvapoBHReal(double evapoBHReal) {
		this.evapoBHReal = evapoBHReal;
	}

	public double getInsolacaoTotal() {
		return insolacaoTotal;
	}

	public void setInsolacaoTotal(double insolacaoTotal) {
		this.insolacaoTotal = insolacaoTotal;
	}

	public double getNebulosidadeMedia() {
		return NebulosidadeMedia;
	}

	public void setNebulosidadeMedia(double nebulosidadeMedia) {
		NebulosidadeMedia = nebulosidadeMedia;
	}

	public int getNumDiasPrecipitacao() {
		return numDiasPrecipitacao;
	}

	public void setNumDiasPrecipitacao(int numDiasPrecipitacao) {
		this.numDiasPrecipitacao = numDiasPrecipitacao;
	}

	public double getPrecipitacaoTotal() {
		return precipitacaoTotal;
	}

	public void setPrecipitacaoTotal(double precipitacaoTotal) {
		this.precipitacaoTotal = precipitacaoTotal;
	}

	public double getPressaoNivelMarMedia() {
		return pressaoNivelMarMedia;
	}

	public void setPressaoNivelMarMedia(double pressaoNivelMarMedia) {
		this.pressaoNivelMarMedia = pressaoNivelMarMedia;
	}

	public double getPressaoMedia() {
		return pressaoMedia;
	}

	public void setPressaoMedia(double pressaoMedia) {
		this.pressaoMedia = pressaoMedia;
	}

	public double getTempMaximaMedia() {
		return tempMaximaMedia;
	}

	public void setTempMaximaMedia(double tempMaximaMedia) {
		this.tempMaximaMedia = tempMaximaMedia;
	}

	public double getTempCompensadaMedia() {
		return tempCompensadaMedia;
	}

	public void setTempCompensadaMedia(double tempCompensadaMedia) {
		this.tempCompensadaMedia = tempCompensadaMedia;
	}

	public double getTempMinimaMedia() {
		return tempMinimaMedia;
	}

	public void setTempMinimaMedia(double tempMinimaMedia) {
		this.tempMinimaMedia = tempMinimaMedia;
	}

	public double getUmidadeRelativaMedia() {
		return umidadeRelativaMedia;
	}

	public void setUmidadeRelativaMedia(double umidadeRelativaMedia) {
		this.umidadeRelativaMedia = umidadeRelativaMedia;
	}

	public double getVisibilidadeMedia() {
		return visibilidadeMedia;
	}

	public void setVisibilidadeMedia(double visibilidadeMedia) {
		this.visibilidadeMedia = visibilidadeMedia;
	}

	
	
}