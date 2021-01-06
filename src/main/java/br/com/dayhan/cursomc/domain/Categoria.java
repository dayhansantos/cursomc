package br.com.dayhan.cursomc.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Categoria implements Serializable {

    private static final long serialVersionUID = -590464393390842220L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    @ManyToMany(mappedBy = "categorias", cascade = CascadeType.PERSIST)
    private List<Produto> produtos = new ArrayList<>();

    public Categoria() {}

    public Categoria(String nome) {
        this.nome = nome;
    }
    
    public Categoria(Integer id, String nome) {
    	this(nome);
    	this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void addProduto(final Produto ...produtos) {
    	for (Produto produto : produtos) {
    		produto.getCategorias().add(this);
    		this.produtos.add(produto);
		}
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(id, categoria.id) && Objects.equals(nome, categoria.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }
}
