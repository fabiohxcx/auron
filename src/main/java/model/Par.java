package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Par {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	private Participante amigo;
	@ManyToOne
	private Participante amigoOculto;
	@ManyToOne
	private Sorteio sorteio;

	public Participante getAmigo() {
		return this.amigo;
	}

	public Participante getAmigoOculto() {
		return this.amigoOculto;
	}

	public Sorteio getSorteio() {
		return this.sorteio;
	}

	public Par(Participante amigo, Participante amigoOculto, Sorteio sorteio) {
		this.sorteio = sorteio;
		this.amigo = amigo;
		this.amigoOculto = amigoOculto;
	}

}
