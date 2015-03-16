package model;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Sorteio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty
	private String nome;
	@OneToMany(mappedBy = "sorteio", cascade = CascadeType.PERSIST)
	private Set<Par> pares = new LinkedHashSet<>();

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Par> getPares() {
		return Collections.unmodifiableSet(this.pares);
	}

	public void setPares(Set<Par> pares) {
		this.pares = pares;
	}

	public void adicionaPar(Par par) {
		this.pares.add(par);
	}

	public int getQuantidadeDePares() {
		return this.pares.size();
	}

	public Integer getId() {
		return this.id;
	}

}
