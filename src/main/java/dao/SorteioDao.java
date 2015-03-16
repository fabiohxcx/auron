package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Par;
import model.Sorteio;

@Stateless
public class SorteioDao {

	@PersistenceContext
	private EntityManager em;

	public void inserir(Sorteio sorteio) {
		this.em.persist(sorteio);
	}

	public List<Par> getPares(Sorteio sorteio) {
		return this.em.createQuery("from Par where sorteio_id =:sorteio ", Par.class)
				.setParameter("sorteio", sorteio.getId()).getResultList();
	}

}