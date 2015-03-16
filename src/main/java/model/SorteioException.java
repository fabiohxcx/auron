package model;

public class SorteioException extends Exception {

	private static final long serialVersionUID = 1L;

	public SorteioException(String msg) {
		super(msg);
	}

	public SorteioException() {
		super("Por favor, insira mais de dois participantes na lista");
	}

}
