package br.com.dayhan.cursomc.services;

import br.com.dayhan.cursomc.domain.Categoria;
import br.com.dayhan.cursomc.dto.CategoriaDTO;
import br.com.dayhan.cursomc.exception.DataIntegrityException;
import br.com.dayhan.cursomc.exception.NotFoundException;
import br.com.dayhan.cursomc.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

	private CategoriaRepository categoriaRepository;

	@Autowired
	public CategoriaService(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	public Categoria find(Integer id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new NotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getSimpleName()));
    }

    public Categoria insert(CategoriaDTO categoriaDTO) {
    	var categoria = this.getFromDTO(categoriaDTO);
    	categoria.setId(null);
        return categoriaRepository.save(categoria);
    }

    public void update(CategoriaDTO obj) {
    	var newObj = this.find(obj.getId());
    	updateData(newObj, obj);
		categoriaRepository.save(newObj);
	}

    private void updateData(Categoria newObj, CategoriaDTO obj) {
		newObj.setNome(obj.getNome());
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
		var categorias = this.categoriaRepository.findAll();
		return categorias.stream()
				.map(CategoriaDTO::new)
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
	public Page<CategoriaDTO> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		var pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return categoriaRepository.findAll(pageRequest).map(CategoriaDTO::new);
	}
	
	public Categoria getFromDTO(CategoriaDTO categoriaDTO) {
		return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
	}
}
