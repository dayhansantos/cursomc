package br.com.dayhan.cursomc.resources.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class FieldMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String fieldName;
	private String message;
	
	public FieldMessage(String fieldName, String message) {
		this.fieldName = fieldName;
		this.message = message;
	}
}
