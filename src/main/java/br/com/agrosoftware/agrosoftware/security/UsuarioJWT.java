package br.com.agrosoftware.agrosoftware.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.agrosoftware.agrosoftware.enums.Funcao;

public class UsuarioJWT implements UserDetails {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String email;
    private String senha;
    private Collection<? extends GrantedAuthority> authorities;
    
    public UsuarioJWT() {
    }
    
    public UsuarioJWT(int id, String email, String senha, Set<Funcao> funcoes) {
        super();
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.authorities = funcoes.stream().map(x -> new SimpleGrantedAuthority(x.getRole())).collect(Collectors.toList());
    }

    public int getId() {
        return id;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
    public boolean hasRole(Funcao funcao) {
        return getAuthorities().contains(new SimpleGrantedAuthority(funcao.getRole()));
    }
    
}
