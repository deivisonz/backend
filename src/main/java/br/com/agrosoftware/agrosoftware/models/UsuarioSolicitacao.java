package br.com.agrosoftware.agrosoftware.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import br.com.agrosoftware.agrosoftware.enums.TipoSolicitacao;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "tipo_solicitacao"}))
public class UsuarioSolicitacao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
    @ManyToOne
    @JoinColumn(name = "usuario_id")
	@NotNull(message = "Usuário é obrigatório.")
	private Usuario usuario;
	
	@Column(unique = true)
	@NotNull(message = "Solicitação é obrigatório.")
	private String solicitacao;
	
	@Enumerated
    @NotNull(message = "Tipo de Solicitação é obrigatório.")
	@Column(name = "tipo_solicitacao")
    private TipoSolicitacao tipoSolicitacao;
	
	@NotNull(message = "Data e Hora de envio são obrigatórios.")
	private LocalDateTime envio;
	
	@NotNull(message = "Data e Hora de expiração são obrigatórios.")
	private LocalDateTime expiracao;
	
	public UsuarioSolicitacao() {
    }
	
    public UsuarioSolicitacao(Usuario usuario, String solicitacao, TipoSolicitacao tipoSolicitacao, LocalDateTime envio, LocalDateTime expiracao) {
        super();
        this.usuario = usuario;
        this.solicitacao = solicitacao;
        this.tipoSolicitacao = tipoSolicitacao;
        this.envio = envio;
        this.expiracao = expiracao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(String solicitacao) {
        this.solicitacao = solicitacao;
    }

    public TipoSolicitacao getTipoSolicitacao() {
        return tipoSolicitacao;
    }

    public void setTipoSolicitacao(TipoSolicitacao tipoSolicitacao) {
        this.tipoSolicitacao = tipoSolicitacao;
    }

    public LocalDateTime getEnvio() {
        return envio;
    }

    public void setEnvio(LocalDateTime envio) {
        this.envio = envio;
    }

    public LocalDateTime getExpiracao() {
        return expiracao;
    }

    public void setExpiracao(LocalDateTime expiracao) {
        this.expiracao = expiracao;
    }

}
