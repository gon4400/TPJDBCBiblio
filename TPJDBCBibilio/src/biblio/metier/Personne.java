package biblio.metier;

import java.util.Date;

public abstract class Personne {
	
	private String nom;
	private String prenom;
	private Date dateNaissance;
	private String sexe;
	public Personne(String nom, String prenom, Date dateNaissance, String sexe) {
		
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.sexe = sexe;
	}
	@Override
	public String toString() {
		return "Personne [nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance + ", sexe=" + sexe
				+ "]";
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	
	
	
	public abstract boolean isConditionsPretAcceptees();
	

}
