package br.com.dayhan.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@EqualsAndHashCode(exclude = "cidades")
public class Estado implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

	@JsonIgnore
    @OneToMany(mappedBy = "estado", cascade = CascadeType.PERSIST)
    private List<Cidade> cidades = new ArrayList<>();

	public Estado(String nome){
	    this.nome = nome;
    }
    public void addCidade(final Cidade ...cidades) {
    	for (Cidade cidade : cidades) {
    		cidade.setEstado(this);
    		this.cidades.add(cidade);
		}
    }
}
