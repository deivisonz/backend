package br.com.agrosoftware.agrosoftware.enums;

public enum Cultivo {
	CAFE("Café"),
	SOJA("Sojá"),
	CANA("Cana-de-açúcar"),
	MILHO("Milho");
	
	private String nome;
	
	private Cultivo(String nome) {
        this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	
}
