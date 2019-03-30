package biblio.metier;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EmpruntEnCours extends Emprunt {

	private Utilisateur emprunteur;
	private Exemplaire exemplaire;
	private int idEmpruntEnCours;
	

	
	
	public EmpruntEnCours(Date dateEmprunt, Utilisateur emprunteur, Exemplaire exemplaire, int idEmpruntEnCours) {
		super(dateEmprunt);
		this.emprunteur = emprunteur;
		this.exemplaire = exemplaire;
		this.idEmpruntEnCours = idEmpruntEnCours;
	}
	
	public EmpruntEnCours(Date dateEmprunt,int idEmpruntEnCours)
	{
		super(dateEmprunt);
		this.idEmpruntEnCours = idEmpruntEnCours;
	}

	public Utilisateur getEmprunteur() {
		return emprunteur;
	}

	public void setEmprunteur(Utilisateur emprunteur) {
		this.emprunteur = emprunteur;
	}

	public Exemplaire getExemplaire() {
		return exemplaire;
	}

	public void setExemplaire(Exemplaire exemplaire) {
		this.exemplaire = exemplaire;
	}

	public int getIdEmpruntEnCours() {
		return idEmpruntEnCours;
	}

	public void setIdEmpruntEnCours(int idEmpruntEnCours) {
		this.idEmpruntEnCours = idEmpruntEnCours;
	}

}
