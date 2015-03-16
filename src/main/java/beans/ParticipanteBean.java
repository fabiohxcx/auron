package beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import model.Participante;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import dao.ParticipanteDao;

@Named
@RequestScoped
public class ParticipanteBean {
	private Participante participante = new Participante();
	private List<Participante> participantes;

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
		if (this.participantes == null)
			this.participantes = this.participanteDao.getParticipantes();
		return this.participantes;
	}

	public String login() {
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(this.participante.getEmail(),
					this.participante.getSenha());
			Subject user = SecurityUtils.getSubject();
			user.login(token);

			return "sorteio?faces-redirect=true";
		} catch (AuthenticationException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		}
		return null;
	}

}
