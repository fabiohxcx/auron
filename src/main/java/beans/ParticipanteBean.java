package beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Participante;
import dao.ParticipanteDao;

@Named
@RequestScoped
public class ParticipanteBean {
	private Participante participante = new Participante();

	@Inject
	private ParticipanteDao participanteDao;

	public void cadastrar() {
		this.participanteDao.inserir(this.participante);
		System.out.println(this.participante.getNome());
	}

	public Participante getParticipante() {
		return this.participante;
	}

	public List<Participante> getParticipantes() {
		return this.participanteDao.getParticipantes();
	}

}
