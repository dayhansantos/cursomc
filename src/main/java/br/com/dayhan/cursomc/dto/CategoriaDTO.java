package br.com.dayhan.cursomc.dto;

import br.com.dayhan.cursomc.domain.Categoria;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class CategoriaDTO {
	private Integer id;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 80, message = "O tamanho deve ser entre {min} e {max} caracteres")
	private String nome;

	public CategoriaDTO(Categoria categoria) {
		this.id = categoria.getId();
		this.nome = categoria.getNome();
	}
}
