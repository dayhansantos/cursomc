package br.com.dayhan.cursomc.domain;

import br.com.dayhan.cursomc.domain.enums.TipoCliente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"enderecos", "telefones", "pedidos"})
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    
    @Column(unique = true)
    private String email;
    private String cpfCnpj;
    private Integer tipo;

    @OneToMany(mappedBy = "cliente", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Endereco> enderecos = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "TELEFONE")
    private Set<String> telefones = new HashSet<>();

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST)
	@JsonIgnore
    private List<Pedido> pedidos = new ArrayList<>();

    public Cliente(final Integer id, final String nome, final String email, final String cpfCnpj, final TipoCliente tipo) {
    	this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpfCnpj = cpfCnpj;
        this.tipo = (tipo == null) ? null : tipo.getCod();
    }

    public TipoCliente getTipo() {
        return TipoCliente.toEnum(tipo);
    }

    public void setTipo(final TipoCliente tipo) {
        this.tipo = tipo.getCod();
    }

    public void addEndereco(final Endereco ...enderecos) {
    	for (Endereco endereco : enderecos) {
    		endereco.setCliente(this);
    		this.enderecos.add(endereco);
		}
    }
    
    public void addPedido(final Pedido ...pedidos) {
    	for (Pedido pedido : pedidos) {
    		pedido.setCliente(this);
    		this.pedidos.add(pedido);
		}
    }
    
    public void addTelefone(String ...telefones) {
        Collections.addAll(this.telefones, telefones);
    }
}
