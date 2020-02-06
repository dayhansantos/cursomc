package br.com.dayhan.cursomc.dto;

import br.com.dayhan.cursomc.domain.Cliente;
import br.com.dayhan.cursomc.services.validation.ClienteUpdate;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ClienteUpdate
@Data
@NoArgsConstructor
public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = -8022372990873047239L;

	private Integer id;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre {min} e {max} caracteres")
	private String nome;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "E-mail inválido")
	private String email;
	
	public ClienteDTO(Cliente cliente) {
		id = cliente.getId();
		nome = cliente.getNome();
		email = cliente.getEmail();
	}
}
