package biblio.metier;

public class Editeur {
	private String nomEditeur;
	private String ville;
	public Editeur(String nomEditeur, String ville) {
		super();
		this.nomEditeur = nomEditeur;
		this.ville = ville;
	}
	public String getNomEditeur() {
		return nomEditeur;
	}
	public void setNomEditeur(String nomEditeur) {
		this.nomEditeur = nomEditeur;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	
}