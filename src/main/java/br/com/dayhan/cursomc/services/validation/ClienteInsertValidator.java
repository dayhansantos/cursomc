package br.com.dayhan.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.dayhan.cursomc.domain.Cliente;
import br.com.dayhan.cursomc.domain.enums.TipoCliente;
import br.com.dayhan.cursomc.dto.ClienteNewDTO;
import br.com.dayhan.cursomc.repositories.ClienteRepository;
import br.com.dayhan.cursomc.resources.exception.FieldMessage;
import br.com.dayhan.cursomc.services.validation.utils.CNP;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public boolean isValid(ClienteNewDTO value, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (value.getTipo().equals(TipoCliente.PF.getCod()) && !CNP.isValidCPF(value.getCpfCnpj())) {
			list.add(new FieldMessage("cpfCnpj", "CPF inválido"));
		} 
		if (value.getTipo().equals(TipoCliente.PJ.getCod()) && !CNP.isValidCNPJ(value.getCpfCnpj())) {
			list.add(new FieldMessage("cpfCnpj", "CNPJ inválido"));
		}
		
		Cliente cliente = clienteRepository.findByEmail(value.getEmail());
		if(cliente != null) {
			list.add(new FieldMessage("email", "Email já existente"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}
