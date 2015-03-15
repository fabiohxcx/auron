package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Participante;

@Stateless
public class ParticipanteDao {

	@PersistenceContext
	private EntityManager em;

	public void inserir(Participante participante) {
		this.em.persist(participante);
	}

	public List<Participante> getParticipantes() {
		return this.em.createQuery("from Participante", Participante.class).getResultList();
	}

}
