package br.com.dayhan.cursomc.services;

import br.com.dayhan.cursomc.domain.Categoria;
import br.com.dayhan.cursomc.domain.Produto;
import br.com.dayhan.cursomc.exception.NotFoundException;
import br.com.dayhan.cursomc.repositories.CategoriaRepository;
import br.com.dayhan.cursomc.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
	
	private ProdutoRepository produtoRepository;
	private CategoriaRepository categoriaRepository;

	@Autowired
	public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
		this.produtoRepository = produtoRepository;
		this.categoriaRepository = categoriaRepository;
	}

	public Produto find(Integer id) {
		return this.produtoRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(
						"Objeto não encontrado: " + id + ", Tipo: " + Produto.class.getSimpleName()));
	}

	/**
	 * Busca produtos pelo nome
	 * @param nome Nome do produto pesquisado
	 * @param ids Lista de IDs que será pesquisado
	 * @param page Numero da pagina que será consultada
	 * @param linesPerPage Quantidade de registros por página
	 * @param orderBy Nome do campo que será usado para ordenação
	 * @param direction Direção da ordenação, podendo ser ASC ou DESC
	 * @return Lista de produtos paginada
	 */
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return produtoRepository.search(nome, categorias, pageRequest);
	}
}
