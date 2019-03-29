package biblio.metier;

import java.util.ArrayList;

public class Livre {
	private ArrayList<Auteur> auteur = new ArrayList<>();
	private Editeur editeur;
	private String isbn;
	private String titre;
	private TypeDonneesAnnee anneeParution;
	private Theme theme;
	private int nbPages;

	public Livre(ArrayList<Auteur> auteur, Editeur editeur, String isbn, String titre, TypeDonneesAnnee anneeParution,
			Theme theme, int nbPages) {
		setAuteur(auteur);
		this.editeur = editeur;
		this.isbn = isbn;
		this.titre = titre;
		this.anneeParution = anneeParution;
		this.theme = theme;
		this.nbPages = nbPages;
	}

	public Livre(Editeur editeur, String isbn, String titre, TypeDonneesAnnee anneeParution, Theme theme, int nbPages) {
		this(null, editeur, isbn, titre, anneeParution, theme, nbPages);
	}

	public ArrayList<Auteur> getAuteur() {
		return auteur;
	}

	public void setAuteur(ArrayList<Auteur> auteur) {
		if (auteur == null)
			return;
		this.auteur = auteur;
	}

	public Editeur getEditeur() {
		return editeur;
	}

	public void setEditeur(Editeur editeur) {
		this.editeur = editeur;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public TypeDonneesAnnee getAnneeParution() {
		return anneeParution;
	}

	public void setAnneeParution(TypeDonneesAnnee anneeParution) {
		this.anneeParution = anneeParution;
	}

	public int getNbPages() {
		return nbPages;
	}

	public void setNbPages(int nbPages) {
		this.nbPages = nbPages;
	}

	public void addAuteur(Auteur a) {
		auteur.add(a);
	}

}