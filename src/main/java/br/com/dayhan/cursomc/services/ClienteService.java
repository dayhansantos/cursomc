package br.com.dayhan.cursomc.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.dayhan.cursomc.domain.Cidade;
import br.com.dayhan.cursomc.domain.Cliente;
import br.com.dayhan.cursomc.domain.Endereco;
import br.com.dayhan.cursomc.domain.enums.TipoCliente;
import br.com.dayhan.cursomc.dto.ClienteDTO;
import br.com.dayhan.cursomc.dto.ClienteNewDTO;
import br.com.dayhan.cursomc.exception.DataIntegrityException;
import br.com.dayhan.cursomc.exception.NotFoundException;
import br.com.dayhan.cursomc.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente find(Integer id) {
		return clienteRepository.findById(id).orElseThrow(
				() -> new NotFoundException(
						"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getSimpleName()));
	}

	public Cliente update(ClienteDTO obj) {
    	Cliente newObj = this.find(obj.getId());
    	updateData(newObj, obj);
        return clienteRepository.save(newObj);
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
            throw new DataIntegrityException("Não é possível excluir pois há entidades relacionadas");
        }
    }

	public List<ClienteDTO> findAll() {
		List<Cliente> clientes = this.clienteRepository.findAll();
		return clientes.stream()
				.map(c -> new ClienteDTO(c))
				.collect(Collectors.toList());
	}
	
	/**
	 * Retorna uma lista paginada
	 * @param page
	 * @param linesPerPage
	 * @param orderBy
	 * @param direction
	 * @return uma lista de clientes paginada
	 */
	public Page<ClienteDTO> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest).map(c -> new ClienteDTO(c));
	}
	
	public Cliente getFromDTO(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
	}
	
	public Cliente getFromDTO(ClienteNewDTO objDTO) {
		Cliente cliente = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfCnpj(), TipoCliente.toEnum(objDTO.getTipo()));
		Cidade cidade = new Cidade(objDTO.getCidadeId(), null, null);
		Endereco endereco = new Endereco(objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cidade);
		cliente.addEndereco(endereco);
		cliente.setTelefones(objDTO.getTelefones());
		return cliente;
	}

	@Transactional
	public Cliente insert(ClienteNewDTO obj) {
    	Cliente cliente = this.getFromDTO(obj);
        return clienteRepository.save(cliente);
    }
}