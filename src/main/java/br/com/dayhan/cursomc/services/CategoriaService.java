package br.com.dayhan.cursomc.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    public Categoria insert(Categoria categoria) {
        categoria.setId(null);
        return categoriaRepository.save(categoria);
    }

    public Categoria update(Categoria obj) {
        this.find(obj.getId());
        return categoriaRepository.save(obj);
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
}
