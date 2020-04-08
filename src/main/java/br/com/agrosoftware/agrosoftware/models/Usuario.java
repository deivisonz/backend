package br.com.agrosoftware.agrosoftware.models;

import java.io.Serializable;
import java.util.HashSet;
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
import br.com.agrosoftware.agrosoftware.enums.Permissao;

@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message = "Nome é obrigatório.")
	@Size(max = 60, message = "Nome: Máximo de 60 caracteres permitido.")
	private String nome;
	
	@Column(unique = true)
	@Email(message = "E-mail inválido.")
	@NotNull(message = "E-mail é obrigatório.")
	@Size(max = 60, message = "E-mail: Máximo de 60 caracteres permitido.")
	private String email;
	
	@JsonIgnore
	@NotNull(message = "Senha é obrigatório.")
	@Size(min = 6, message = "A senha deve conter no mínimo 6 caracteres.")
	private String senha;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "usuario_funcao", joinColumns = @JoinColumn(name = "usuario_id"))
	@Column(name = "funcao_id")
	@NotEmpty(message = "É obrigatório no mínimo uma função.")
	private Set<Funcao> funcoes = Sets.newHashSet(Funcao.USUARIO);
	
	@ElementCollection
    @CollectionTable(name = "usuario_permissao", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "permissao_id")
    private Set<Permissao> permissoes = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name = "propriedade_id")
	@NotNull(message = "Propriedade é obrigatório.")
	private Propriedade propriedade;

	private boolean ativo = true;

	public Usuario() {
	}
	
    public Usuario(String nome, String email, String senha) {
        super();
        this.nome = nome;
        this.email = email;
        this.senha = senha;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    public String getSenha() {
        return senha;
    }

    @JsonProperty
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Funcao> getFuncoes() {
        return funcoes;
    }

    public void setFuncoes(Set<Funcao> funcoes) {
        this.funcoes = funcoes;
    }

    public Set<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(Set<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    @JsonIgnore
    public Propriedade getPropriedade() {
        return propriedade;
    }

    @JsonProperty
    public void setPropriedade(Propriedade propriedade) {
        this.propriedade = propriedade;
    }
    
    
    
}
