package br.com.dayhan.cursomc.services;

import br.com.dayhan.cursomc.domain.Cidade;
import br.com.dayhan.cursomc.domain.Cliente;
import br.com.dayhan.cursomc.domain.Endereco;
import br.com.dayhan.cursomc.domain.enums.TipoCliente;
import br.com.dayhan.cursomc.dto.ClienteDTO;
import br.com.dayhan.cursomc.dto.ClienteNewDTO;
import br.com.dayhan.cursomc.exception.DataIntegrityException;
import br.com.dayhan.cursomc.exception.NotFoundException;
import br.com.dayhan.cursomc.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public Cliente find(Integer id) {
		return clienteRepository.findById(id).orElseThrow(
				() -> new NotFoundException(
						"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getSimpleName()));
	}

	public void update(ClienteDTO obj) {
    	var newObj = this.find(obj.getId());
    	updateData(newObj, obj);
		clienteRepository.save(newObj);
	}

    private void updateData(Cliente cliente, ClienteDTO obj) {
    	cliente.setNome(obj.getNome());
    	cliente.setEmail(obj.getEmail());
	}

	public void delete(Integer id) {
        this.find(id);
        try {
            this.clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir pois há pedidos relacionados");
        }
    }

	public List<ClienteDTO> findAll() {
		var clientes = this.clienteRepository.findAll();
		return clientes.stream()
				.map(ClienteDTO::new)
				.collect(Collectors.toList());
	}
	
	/**
	 * Retorna uma lista paginada
	 * @param page Numero da pagina que será consultada
	 * @param linesPerPage Quantidade de registros por página
	 * @param orderBy Nome do campo que será usado para ordenação
	 * @param direction Direção da ordenação, podendo ser ASC ou DESC
	 * @return uma lista de clientes paginada
	 */
	public Page<ClienteDTO> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest).map(ClienteDTO::new);
	}
	
	public Cliente getFromDTO(ClienteNewDTO objDTO) {
		var cliente = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfCnpj(), TipoCliente.toEnum(objDTO.getTipo()), bCryptPasswordEncoder.encode(objDTO.getSenha()));
		var cidade = new Cidade(objDTO.getCidadeId(), null, null);
		var endereco = new Endereco(objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cidade);
		cliente.addEndereco(endereco);
		cliente.setTelefones(objDTO.getTelefones());
		return cliente;
	}

	@Transactional
	public Cliente insert(ClienteNewDTO obj) {
    	var cliente = this.getFromDTO(obj);
        return clienteRepository.save(cliente);
    }
}