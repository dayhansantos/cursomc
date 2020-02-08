package br.com.dayhan.cursomc.services;

import br.com.dayhan.cursomc.domain.Endereco;
import br.com.dayhan.cursomc.exception.NotFoundException;
import br.com.dayhan.cursomc.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco find(Integer id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        "Objeto não encontrado: " + id + ", Tipo: " + Endereco.class.getSimpleName()));
    }
}
