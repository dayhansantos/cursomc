package br.com.dayhan.cursomc.services;

import br.com.dayhan.cursomc.domain.Categoria;
import br.com.dayhan.cursomc.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Optional<Categoria> buscar(Integer id){
        return categoriaRepository.findById(id);
    }
}
