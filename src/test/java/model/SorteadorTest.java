package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import model.Par;
import model.Participante;
import model.Sorteador;
import model.Sorteio;
import model.SorteioException;

import org.junit.Before;
import org.junit.Test;

public class SorteadorTest {
	private Participante p1;
	private Participante p2;
	private Participante p3;
	private List<Participante> participantes;
	private Sorteio sorteio;

	@Before
	public void setUp() throws Exception {
		this.p1 = new Participante();
		this.p1.setNome("Leonardo");

		this.p2 = new Participante();
		this.p2.setNome("Nico");

		this.p3 = new Participante();
		this.p3.setNome("Fábio");

		this.participantes = Arrays.asList(this.p1, this.p2, this.p3);
		this.sorteio = new Sorteio();
	}

	@Test
	public void aQuantidadedeParesEParticipantesDeveSerAMesma() throws SorteioException {
		int quantidadeDeParticipantes = this.participantes.size();

		Sorteador sorteador = new Sorteador(this.sorteio, this.participantes);
		sorteador.sortear();
		int quantidadeDePares = this.sorteio.getQuantidadeDePares();

		assertTrue(quantidadeDePares == quantidadeDeParticipantes);
	}

	@Test
	public void naoDeveRepetirUmAmigoOculto() throws SorteioException {
		Sorteador sorteador = new Sorteador(this.sorteio, this.participantes);
		sorteador.sortear();
		Set<Par> pares = this.sorteio.getPares();
		Iterator<Par> it = pares.iterator();
		Par par = it.next();
		Par par2 = it.next();
		Par par3 = it.next();
		Participante amigoOculto1 = par.getAmigoOculto();
		Participante amigoOculto2 = par2.getAmigoOculto();
		Participante amigoOculto3 = par3.getAmigoOculto();
		assertFalse(amigoOculto1.equals(amigoOculto2));
		assertFalse(amigoOculto2.equals(amigoOculto3));
		assertFalse(amigoOculto3.equals(amigoOculto1));
	}

	@Test
	public void aQuantidadeDeParesEParticipantesDeveSerAMesma() throws SorteioException {
		int quantidadeDeParticipantes = this.participantes.size();
		Sorteador sorteador = new Sorteador(this.sorteio, this.participantes);
		sorteador.sortear();
		int quantidadeDePares = this.sorteio.getQuantidadeDePares();
		assertEquals(quantidadeDePares, quantidadeDeParticipantes);
	}

	@Test(expected = SorteioException.class)
	public void naoDeveAceitarUmaListaComMenosDeDoisParticipantes() throws SorteioException {

		List<Participante> lista = new ArrayList<Participante>();
		lista.add(this.p1);
		Sorteador sorteador = new Sorteador(this.sorteio, lista);
		sorteador.sortear();
	}

	@Test(expected = SorteioException.class)
	public void naoDeveAceitarUmaListaDeParticipantesNula() throws SorteioException {
		@SuppressWarnings("unused")
		Sorteador sorteador = new Sorteador(this.sorteio, null);
	}

	@Test
	public void naoDeveRepetirUmAmigo() throws SorteioException {
		Sorteador sorteador = new Sorteador(this.sorteio, this.participantes);
		sorteador.sortear();
		Set<Par> pares = this.sorteio.getPares();
		Iterator<Par> it = pares.iterator();
		Par par = it.next();
		Par par2 = it.next();
		Par par3 = it.next();
		Participante amigo1 = par.getAmigo();
		Participante amigo2 = par2.getAmigo();
		Participante amigo3 = par3.getAmigo();
		assertFalse(amigo1.equals(amigo2));
		assertFalse(amigo2.equals(amigo3));
		assertFalse(amigo3.equals(amigo1));
	}

	@Test
	public void deveVerificarSeAmigoEhDiferenteDeAmigoOculto() throws SorteioException {
		Sorteador sorteador = new Sorteador(this.sorteio, this.participantes);
		sorteador.sortear();
		Set<Par> pares = this.sorteio.getPares();
		Iterator<Par> it = pares.iterator();
		Par par = it.next();
		Par par2 = it.next();
		Par par3 = it.next();
		Participante amigo1 = par.getAmigo();
		Participante amigoOculto1 = par.getAmigoOculto();
		Participante amigo2 = par2.getAmigo();
		Participante amigoOculto2 = par2.getAmigoOculto();
		Participante amigo3 = par3.getAmigo();
		Participante amigoOculto3 = par3.getAmigoOculto();

		assertFalse(amigo1.equals(amigoOculto1));
		assertFalse(amigo2.equals(amigoOculto2));
		assertFalse(amigo3.equals(amigoOculto3));
	}

	@Test
	public void deveVerificarSeOAmigoOcultoDoUltimoParEhOAmigoDoPrimeiroPar() throws SorteioException {
		Sorteador sorteador = new Sorteador(this.sorteio, this.participantes);
		sorteador.sortear();

		Set<Par> pares = this.sorteio.getPares();
		Iterator<Par> it = pares.iterator();
		Par par = it.next();
		it.next();
		Par par3 = it.next();

		Participante primeiro = par.getAmigo();
		Participante ultimo = par3.getAmigoOculto();

		assertTrue(primeiro.equals(ultimo));
	}

}
