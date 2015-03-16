package beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import model.Par;
import model.Participante;
import model.Sorteador;
import model.Sorteio;
import model.SorteioException;
import dao.ParticipanteDao;
import dao.SorteioDao;

@Named
@RequestScoped
public class SorteioBean {

	private Sorteio sorteio = new Sorteio();
	private ParticipanteDao participanteDao = new ParticipanteDao();
	private SorteioDao sorteioDao;

	public Sorteio getSorteio() {
		return this.sorteio;
	}

	public void sortear() {
		List<Participante> participantes = this.participanteDao.getParticipantes();
		Sorteador sorteador;
		this.sorteioDao = new SorteioDao();
		try {
			sorteador = new Sorteador(this.sorteio, participantes);
			sorteador.sortear();

			this.sorteioDao.inserir(this.sorteio);

		} catch (SorteioException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		}

		System.out.println("Sorteio: " + this.sorteio.getNome());
	}

	public List<Par> getPares() {
		return this.sorteioDao.getPares(this.sorteio);
	}
}
