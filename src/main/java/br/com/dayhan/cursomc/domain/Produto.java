package br.com.dayhan.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto implements Serializable {

    private static final long serialVersionUID = 4446285086533085607L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Double preco;

    @ManyToMany
    @JoinTable(name = "PRODUTO_CATEGORIA", joinColumns = @JoinColumn(name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
	@JsonIgnore
    private List<Categoria> categorias = new ArrayList<>();

    @OneToMany(mappedBy = "id.produto", cascade = CascadeType.PERSIST)
	@JsonIgnore
    private Set<ItemPedido> itens = new HashSet<>();

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

    public void addItemPedido(final ItemPedido ...itemPedidos) {
    	for (ItemPedido itemPedido : itemPedidos) {
    		itemPedido.getId().setProduto(this);
    		this.itens.add(itemPedido);
		}
    }
}
