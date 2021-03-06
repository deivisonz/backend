package br.com.agrosoftware.agrosoftware.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Sets;

import br.com.agrosoftware.agrosoftware.enums.Funcao;

@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int usuCdUsuario;
	
	@NotNull(message = "Nome é obrigatório.")
	@Size(max = 60, message = "Nome: Máximo de 60 caracteres permitido.")
	private String usuDsNome;
	
	@Column(unique = true)
	@Email(message = "E-mail inválido.")
	@NotNull(message = "E-mail é obrigatório.")
	@Size(max = 60, message = "E-mail: Máximo de 60 caracteres permitido.")
	private String usuDsEmail;
	
	@JsonIgnore
	@NotNull(message = "Senha é obrigatório.")
	@Size(min = 6, message = "A senha deve conter no mínimo 6 caracteres.")
	private String usuDsSenha;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "usuario_funcao", joinColumns = @JoinColumn(name = "usu_cd_usuario"))
	@NotEmpty(message = "É obrigatório no mínimo uma função.")
	private Set<Funcao> usuLsFuncoes = Sets.newHashSet(Funcao.USUARIO);
	
	@ManyToOne
    @JoinColumn(name = "usu_cd_propriedade")
    private Propriedade usuCdPropriedade;

	private boolean usuBlAtivo = true;

	public Usuario() {
	}
	
    public Usuario(String nome, String email, String senha) {
        super();
        this.usuDsNome = nome;
        this.usuDsEmail = email;
        this.usuDsSenha = senha;
    }  
   
    public int getUsuCdUsuario() {
		return usuCdUsuario;
	}

	public void setUsuCdUsuario(int usuCdUsuario) {
		this.usuCdUsuario = usuCdUsuario;
	}

	public String getUsuDsNome() {
		return usuDsNome;
	}

	public void setUsuDsNome(String usuDsNome) {
		this.usuDsNome = usuDsNome;
	}

	public String getUsuDsEmail() {
		return usuDsEmail;
	}

	public void setUsuDsEmail(String usuDsEmail) {
		this.usuDsEmail = usuDsEmail;
	}

	public Set<Funcao> getUsuLsFuncoes() {
		return usuLsFuncoes;
	}

	public void setUsuLsFuncoes(Set<Funcao> usuLsFuncoes) {
		this.usuLsFuncoes = usuLsFuncoes;
	}

	public boolean isUsuBlAtivo() {
		return usuBlAtivo;
	}

	public void setUsuBlAtivo(boolean usuBlAtivo) {
		this.usuBlAtivo = usuBlAtivo;
	}

	@JsonIgnore
	public String getUsuDsSenha() {
		return usuDsSenha;
	}

    @JsonProperty
    public void setUsuDsSenha(String senha) {
        this.usuDsSenha = senha;
    }

	public Propriedade getUsuCdPropriedade() {
		return usuCdPropriedade;
	}

	public void setUsuCdPropriedade(Propriedade usuCdPropriedade) {
		this.usuCdPropriedade = usuCdPropriedade;
	}
   
    
}
