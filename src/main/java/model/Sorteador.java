package model;

import java.util.Collections;
import java.util.List;

public class Sorteador {
	private Sorteio sorteio;
	private List<Participante> participantes;
	private int totalDeParticipantes;

	public Sorteador(Sorteio sorteio, List<Participante> participantes) throws SorteioException {
		if (participantes == null) {
			throw new SorteioException("Por favor, insira uma lista de participantes");
		}

		this.sorteio = sorteio;
		this.participantes = participantes;
		this.totalDeParticipantes = this.participantes.size();
	}

	public void sortear() throws SorteioException {
		int indiceAtual = 0;

		verificaTamanhoDaListaParticipantes();
		embaralhaParticipantes();

		while (indiceAtual < this.totalDeParticipantes) {
			if (participanteAtualEhUltimo(indiceAtual)) {
				criaEAdicionaOParNoSorteio(this.sorteio, indiceAtual, 0);
				break;
			}

			criaEAdicionaOParNoSorteio(this.sorteio, indiceAtual, indiceAtual + 1);

			indiceAtual++;
		}
	}

	private void embaralhaParticipantes() {
		Collections.shuffle(this.participantes);
	}

	private void verificaTamanhoDaListaParticipantes() throws SorteioException {
		if (this.totalDeParticipantes < 2) {
			throw new SorteioException();
		}
	}

	private boolean participanteAtualEhUltimo(int indiceAtual) {
		return indiceAtual == this.totalDeParticipantes - 1;
	}

	private void criaEAdicionaOParNoSorteio(Sorteio sorteio, int indiceAtual, int indiceFinal) {
		Par par = new Par(this.participantes.get(indiceAtual), this.participantes.get(indiceFinal), this.sorteio);
		this.sorteio.adicionaPar(par);
	}

}
