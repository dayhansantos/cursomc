package br.com.dayhan.cursomc.dto;

public class CredenciaisDTO {
    private String email;
    private String senha;

    public String getEmail() {
        return email;
    }

    public CredenciaisDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getSenha() {
        return senha;
    }

    public CredenciaisDTO setSenha(String senha) {
        this.senha = senha;
        return this;
    }
}
