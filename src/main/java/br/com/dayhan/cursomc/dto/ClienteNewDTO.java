package br.com.dayhan.cursomc.dto;

import br.com.dayhan.cursomc.services.validation.ClienteInsert;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@ClienteInsert
public class ClienteNewDTO implements Serializable {
	private static final long serialVersionUID = -4725389914756191168L;

	//Parâmetros de Cliente
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre {min} e {max} caracteres")
	private String nome;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "E-mail inválido")
    private String email;
	
	@NotEmpty(message = "Preenchimento obrigatório")
    private String cpfCnpj;
	
    private Integer tipo;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String senha;
    
    // Parâmetros de Endereço
    @NotEmpty(message = "Preenchimento obrigatório")
    private String logradouro;
    
    @NotEmpty(message = "Preenchimento obrigatório")
    private String numero;
    
    private String complemento;
    private String bairro;
    
    @NotEmpty(message = "Preenchimento obrigatório")
    private String cep;
    
    @NotEmpty(message = "Preenchimento obrigatório")
    private Set<String> telefones = new HashSet<>();
    
    private Integer cidadeId;

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

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }


    public String getSenha() {
        return senha;
    }

    public ClienteNewDTO setSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Set<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<String> telefones) {
        this.telefones = telefones;
    }

    public Integer getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Integer cidadeId) {
        this.cidadeId = cidadeId;
    }
}
