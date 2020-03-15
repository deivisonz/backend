package br.com.agrosoftware.agrosoftware.enums;

public enum Funcao {
	USUARIO("ROLE_USUARIO"),
	OPERADOR("ROLE_OPERADOR"),
	ADMIN("ROLE_ADMIN");
    
    private String role;
    
    private Funcao(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
    
    public static Funcao toEnum(String role) {
        if (role == null || role.isBlank())
            return null;
        
        for (Funcao x : Funcao.values())
            if (x.getRole().equals(role))
                return x;
        
        throw new IllegalArgumentException("Role inválida: " + role);
    }
}
