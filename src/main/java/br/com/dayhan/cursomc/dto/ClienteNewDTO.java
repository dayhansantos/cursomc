package br.com.dayhan.cursomc.dto;

import br.com.dayhan.cursomc.services.validation.ClienteInsert;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@ClienteInsert
@Data
@NoArgsConstructor
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
}
