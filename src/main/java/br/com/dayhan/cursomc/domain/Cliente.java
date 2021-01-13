package br.com.dayhan.cursomc.domain;

import br.com.dayhan.cursomc.domain.enums.Perfil;
import br.com.dayhan.cursomc.domain.enums.TipoCliente;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity
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
    
    private @JsonIgnore String senha;

    @OneToMany(mappedBy = "cliente", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Endereco> enderecos = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "TELEFONE")
    private Set<String> telefones = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PERFIS")
    private final Set<Integer> perfis = new HashSet<>();

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST)
	@JsonIgnore
    private List<Pedido> pedidos = new ArrayList<>();

    public Cliente() {
        addPerfil(Perfil.CLIENTE);
    }

    public Cliente(final Integer id, final String nome, final String email, final String cpfCnpj, final TipoCliente tipo, String senha) {
        this();
    	this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpfCnpj = cpfCnpj;
        this.tipo = (tipo == null) ? null : tipo.getCod();
        this.senha = senha;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getSenha() {
        return senha;
    }

    public Cliente setSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Set<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<String> telefones) {
        this.telefones = telefones;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id) && Objects.equals(nome, cliente.nome) && Objects.equals(email, cliente.email) && Objects.equals(cpfCnpj, cliente.cpfCnpj) && Objects.equals(tipo, cliente.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, cpfCnpj, tipo);
    }

    public Set<Perfil> getPerfis() {
        return this.perfis.stream().map(Perfil::toEnum)
                .collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil.getCod());
    }
}
