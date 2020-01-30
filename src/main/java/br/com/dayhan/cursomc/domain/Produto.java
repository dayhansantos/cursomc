package br.com.dayhan.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Produto implements Serializable {

    private static final long serialVersionUID = 4446285086533085607L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Double preco;

    @ManyToMany
    @JoinTable(name = "PRODUTO_CATEGORIA", joinColumns = @JoinColumn(name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    // Informa que a referência já foi chamada na outra classe
	@JsonIgnore
    private List<Categoria> categorias = new ArrayList<>();

    @OneToMany(mappedBy = "id.produto", cascade = CascadeType.PERSIST)
	@JsonIgnore
    private Set<ItemPedido> itens = new HashSet<>();

    public Produto() {
    }

    public Produto(String nome, Double preco) {
        this.nome = nome;
        this.preco = preco;
    }

	@JsonIgnore
    public List<Pedido> getPedidos() {
        List<Pedido> lista = new ArrayList<>();
        for (ItemPedido o : this.itens) {
            lista.add(o.getPedido());
        }
        return lista;
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Set<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(Set<ItemPedido> itens) {
        this.itens = itens;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Produto.class.getSimpleName() + "[", "]").add("id=" + id)
                .add("nome='" + nome + "'").add("preco=" + preco).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    public void addItemPedido(final ItemPedido ...itemPedidos) {
    	for (ItemPedido itemPedido : itemPedidos) {
    		itemPedido.getId().setProduto(this);
    		this.itens.add(itemPedido);
		}
    }
}
