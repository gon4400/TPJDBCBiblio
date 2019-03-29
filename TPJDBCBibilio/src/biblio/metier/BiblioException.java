package biblio.metier;

public class BiblioException extends Exception {

	private static final long serialVersionUID = 1L;

	public BiblioException(String message) {
		super(message);
	}

	public BiblioException() {

		this("Erreur generique biblio");
	}

}