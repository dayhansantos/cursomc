package br.com.dayhan.cursomc.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.dayhan.cursomc.domain.Cliente;
import br.com.dayhan.cursomc.dto.ClienteDTO;
import br.com.dayhan.cursomc.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Integer id) {
        final Cliente cliente = clienteService.find(id);
        return ResponseEntity.ok(cliente);
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO obj, @PathVariable Integer id) {
        obj.setId(id);
        clienteService.update(obj);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {
		final List<ClienteDTO> clientes = clienteService.findAll();
		return ResponseEntity.ok(clientes);
	}
	
	@GetMapping(value = "/page")
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		
		final Page<ClienteDTO> clientes = clienteService.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok(clientes);
	}
}
