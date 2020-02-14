package br.com.dayhan.cursomc.resources;

import br.com.dayhan.cursomc.domain.Produto;
import br.com.dayhan.cursomc.dto.ProdutoDTO;
import br.com.dayhan.cursomc.resources.utils.URL;
import br.com.dayhan.cursomc.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> find(@PathVariable Integer id) {
		Produto produto = produtoService.find(id);
		return ResponseEntity.ok(produto);
	}
	
	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value = "nome", defaultValue = "") String nome, 
			@RequestParam(value = "categorias", defaultValue = "") String categorias, 
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids;
		try {
			ids = URL.decodeIntList(categorias);
		} catch (Exception e) {
			ids = new ArrayList<>();
		}
		final Page<Produto> list = produtoService.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDTO = list.map(ProdutoDTO::new);
		return ResponseEntity.ok(listDTO);
	}
}
