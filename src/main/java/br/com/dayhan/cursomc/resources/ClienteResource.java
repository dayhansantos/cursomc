package br.com.dayhan.cursomc.resources;

import br.com.dayhan.cursomc.domain.Cliente;
import br.com.dayhan.cursomc.dto.ClienteDTO;
import br.com.dayhan.cursomc.dto.ClienteNewDTO;
import br.com.dayhan.cursomc.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    private ClienteService service;

    @Autowired
    public ClienteResource(ClienteService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Integer id) {
        final Cliente cliente = service.find(id);
        return ResponseEntity.ok(cliente);
    }
    
    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO obj) {
        Cliente cliente = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO obj, @PathVariable Integer id) {
        obj.setId(id);
        service.update(obj);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {
		final List<ClienteDTO> clientes = service.findAll();
		return ResponseEntity.ok(clientes);
	}
	
	@GetMapping(value = "/page")
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		
		final Page<ClienteDTO> clientes = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok(clientes);
	}
}
