package br.com.dayhan.cursomc.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "produtos")
public class Categoria implements Serializable {

    private static final long serialVersionUID = -590464393390842220L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    @ManyToMany(mappedBy = "categorias", cascade = CascadeType.PERSIST)
    private List<Produto> produtos = new ArrayList<>();

    public Categoria(String nome) {
        this.nome = nome;
    }
    
    public Categoria(Integer id, String nome) {
    	this(nome);
    	this.id = id;
    }

    public void addProduto(final Produto ...produtos) {
    	for (Produto produto : produtos) {
    		produto.getCategorias().add(this);
    		this.produtos.add(produto);
		}
    }
}
