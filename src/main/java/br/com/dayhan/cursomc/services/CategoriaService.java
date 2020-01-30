package br.com.dayhan.cursomc.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.dayhan.cursomc.domain.Categoria;
import br.com.dayhan.cursomc.dto.CategoriaDTO;
import br.com.dayhan.cursomc.exception.DataIntegrityException;
import br.com.dayhan.cursomc.exception.NotFoundException;
import br.com.dayhan.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria find(Integer id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new NotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getSimpleName()));
    }

    public Categoria insert(CategoriaDTO categoriaDTO) {
    	Categoria categoria = this.getFromDTO(categoriaDTO);
    	categoria.setId(null);
        return categoriaRepository.save(categoria);
    }

    public Categoria update(CategoriaDTO obj) {
    	Categoria categoria = this.getFromDTO(obj);
        this.find(categoria.getId());
        return categoriaRepository.save(categoria);
    }

    public void delete(Integer id) {
        this.find(id);
        try {
            this.categoriaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
        }
    }

	public List<CategoriaDTO> findAll() {
		List<Categoria> categorias = this.categoriaRepository.findAll();
		return categorias.stream()
				.map(c -> new CategoriaDTO(c))
				.collect(Collectors.toList());
	}
	
	/**
	 * Retorna uma lista paginada
	 * @param page
	 * @param linesPerPage
	 * @param orderBy
	 * @param direction
	 * @return
	 */
	public Page<CategoriaDTO> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return categoriaRepository.findAll(pageRequest).map(c -> new CategoriaDTO(c));
	}
	
	public Categoria getFromDTO(CategoriaDTO categoriaDTO) {
		return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
	}
}
