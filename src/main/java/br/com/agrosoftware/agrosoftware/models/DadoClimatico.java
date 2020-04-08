package br.com.agrosoftware.agrosoftware.models;

import java.io.Serializable;
import java.security.Timestamp;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import br.com.agrosoftware.agrosoftware.enums.Estacao;

@Entity
public class DadoClimatico implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message = "Estação deve ser informada.")
	private Estacao estacao;
	
	@NotNull(message = "Data deve ser informada.")
	private LocalDate data;
	
	@NotNull(message = "Hora(UTC) deve ser informada.")
	private Timestamp hora;
	
	@NotNull(message = "Temperatura máxima(C°) deve ser informada.")
	private double temperaturaMaxima;
	
	@NotNull(message = "Temperatura mínima(C°) deve ser informada.")
	private double temperaturaMinima;
	
	@NotNull(message = "Temperatura média(C°) deve ser informada.")
	private double temperaturaMedia;
	
	@NotNull(message = "Umidade máxima(%) deve ser informada.")
	private double umidadeMaxima;
	
	@NotNull(message = "Umidade mínima(%) deve ser informada.")
	private double umidadeMinima;
	
	@NotNull(message = "Umidade média(%) deve ser informada.")
	private double umidadeMedia;
	
	@NotNull(message = "Ponto de Orvalho(C°) máximo deve ser informado.")
	private double ptoOrvalhoMaximo;
	
	@NotNull(message = "Ponto de Orvalho(C°) mínimo deve ser informadao.")
	private double ptoOrvalhoMinimo;
	
	@NotNull(message = "Ponto de Orvalho(C°) médio deve ser informado.")
	private double ptoOrvalhoMedio;
	
	@NotNull(message = "Pressão(hpa) máxima deve ser informada.")
	private double pressaoMaxima;
	
	@NotNull(message = "Pressão(hpa) mínima deve ser informada.")
	private double pressaoMinima;
	
	@NotNull(message = "Pressão(hpa) média deve ser informada.")
	private double pressaoMedia;
	
	@NotNull(message = "Velocidade(m/s) do vento deve ser informado.")
	private int velocidadeVento;
	
	@NotNull(message = "Direção(°) do vento deve ser informado.")
	private int direcaoVento;
	
	@NotNull(message = "Velocidade de Rajada(m/s) do vento deve ser informado.")
	private int velocidadeRajadaVento;
	
	@PositiveOrZero(message = "Precipitação(mm) não pode ser um valor negativo")
	private double precipitacao;

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

	public Timestamp getHora() {
		return hora;
	}

	public void setHora(Timestamp hora) {
		this.hora = hora;
	}

	public double getTemperaturaMaxima() {
		return temperaturaMaxima;
	}

	public void setTemperaturaMaxima(double temperaturaMaxima) {
		this.temperaturaMaxima = temperaturaMaxima;
	}

	public double getTemperaturaMinima() {
		return temperaturaMinima;
	}

	public void setTemperaturaMinima(double temperaturaMinima) {
		this.temperaturaMinima = temperaturaMinima;
	}

	public double getTemperaturaMedia() {
		return temperaturaMedia;
	}

	public void setTemperaturaMedia(double temperaturaMedia) {
		this.temperaturaMedia = temperaturaMedia;
	}

	public double getUmidadeMaxima() {
		return umidadeMaxima;
	}

	public void setUmidadeMaxima(double umidadeMaxima) {
		this.umidadeMaxima = umidadeMaxima;
	}

	public double getUmidadeMinima() {
		return umidadeMinima;
	}

	public void setUmidadeMinima(double umidadeMinima) {
		this.umidadeMinima = umidadeMinima;
	}

	public double getUmidadeMedia() {
		return umidadeMedia;
	}

	public void setUmidadeMedia(double umidadeMedia) {
		this.umidadeMedia = umidadeMedia;
	}

	public double getPtoOrvalhoMaximo() {
		return ptoOrvalhoMaximo;
	}

	public void setPtoOrvalhoMaximo(double ptoOrvalhoMaximo) {
		this.ptoOrvalhoMaximo = ptoOrvalhoMaximo;
	}

	public double getPtoOrvalhoMinimo() {
		return ptoOrvalhoMinimo;
	}

	public void setPtoOrvalhoMinimo(double ptoOrvalhoMinimo) {
		this.ptoOrvalhoMinimo = ptoOrvalhoMinimo;
	}

	public double getPtoOrvalhoMedio() {
		return ptoOrvalhoMedio;
	}

	public void setPtoOrvalhoMedio(double ptoOrvalhoMedio) {
		this.ptoOrvalhoMedio = ptoOrvalhoMedio;
	}

	public double getPressaoMaxima() {
		return pressaoMaxima;
	}

	public void setPressaoMaxima(double pressaoMaxima) {
		this.pressaoMaxima = pressaoMaxima;
	}

	public double getPressaoMinima() {
		return pressaoMinima;
	}

	public void setPressaoMinima(double pressaoMinima) {
		this.pressaoMinima = pressaoMinima;
	}

	public double getPressaoMedia() {
		return pressaoMedia;
	}

	public void setPressaoMedia(double pressaoMedia) {
		this.pressaoMedia = pressaoMedia;
	}

	public int getVelocidadeVento() {
		return velocidadeVento;
	}

	public void setVelocidadeVento(int velocidadeVento) {
		this.velocidadeVento = velocidadeVento;
	}

	public int getDirecaoVento() {
		return direcaoVento;
	}

	public void setDirecaoVento(int direcaoVento) {
		this.direcaoVento = direcaoVento;
	}

	public int getVelocidadeRajadaVento() {
		return velocidadeRajadaVento;
	}

	public void setVelocidadeRajadaVento(int velocidadeRajadaVento) {
		this.velocidadeRajadaVento = velocidadeRajadaVento;
	}

	public double getPrecipitacao() {
		return precipitacao;
	}

	public void setPrecipitacao(double precipitacao) {
		this.precipitacao = precipitacao;
	}

}