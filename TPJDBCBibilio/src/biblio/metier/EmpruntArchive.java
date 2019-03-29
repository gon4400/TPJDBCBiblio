package biblio.metier;

import java.util.Date;

public class EmpruntArchive extends Emprunt {

	private Date dateRestitutionEff;
	private Utilisateur emprunteur;
	private Exemplaire exemplaire;
    private int idempruntArchive;

	public EmpruntArchive(Date dateEmprunt, Date dateRestitutionEff, Utilisateur emprunteur,int idempruntArchive,Exemplaire exemplaire) 
	{
		super(dateEmprunt);
		this.dateRestitutionEff = dateRestitutionEff;
		this.emprunteur = emprunteur;
		this.idempruntArchive=idempruntArchive;
		this.exemplaire=exemplaire;
	}

	
	public Exemplaire getExemplaire() {
		return exemplaire;
	}

	public void setExemplaire(Exemplaire exemplaire) {
		this.exemplaire = exemplaire;
	}
	
	public Date getDateRestitutionEff() {
		return dateRestitutionEff;
	}

	public void setDateRestitutionEff(Date dateRestitutionEff) {
		this.dateRestitutionEff = dateRestitutionEff;
	}

	public Utilisateur getEmprunteur() {
		return emprunteur;
	}

	public void setEmprunteur(Utilisateur emprunteur) {
		this.emprunteur = emprunteur;
	}

	public int getIdempruntArchive() {
		return idempruntArchive;
	}

	public void setIdempruntArchive(int idempruntArchive) {
		this.idempruntArchive = idempruntArchive;
	}

	
	
}