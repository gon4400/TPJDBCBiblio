package biblio.dao;

import java.util.Date;

import biblio.metier.EmpruntEnCours;
import biblio.metier.Exemplaire;
import biblio.metier.Utilisateur;

public class EmpruntEnCoursDb extends EmpruntEnCours {

	private int idUtilisateur;
	private int idExemplaire;

	public EmpruntEnCoursDb(int idUtilisateur, int idExemplaire, Date dateEmprunt, Utilisateur emprunteur,
			Exemplaire exemplaire, int idEmpruntEnCours) {
		super(dateEmprunt, emprunteur, exemplaire, idEmpruntEnCours);
		this.idUtilisateur = idUtilisateur;
		this.idExemplaire = idExemplaire;
	}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public int getIdExemplaire() {
		return idExemplaire;
	}

	public void setIdExemplaire(int idExemplaire) {
		this.idExemplaire = idExemplaire;
	}

	@Override
	public String toString() {
		return super.toString() + "EmpruntEnCoursDb [idUtilisateur=" + idUtilisateur + ", idExemplaire=" + idExemplaire
				+ "]";
	}

}
