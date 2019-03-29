package biblio.metier;

import java.util.Date;

public class Adherent extends Utilisateur {

	private String telephone;
	private static int nbMaxPrets = 3;
	private static int dureeMaxPrets = 15;
	

	public Adherent(String nom, String prenom, Date dateNaissance, String sexe, int idUtilisateur, String pwd,
			String pseudonyme, String telephone,String cat) {
		super(nom, prenom, dateNaissance, sexe, idUtilisateur, pwd, pseudonyme,cat);
		this.telephone = telephone;

	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getNbRetards() {

		int retour = 0;
		Date datetoday = new Date();

		for (EmpruntEnCours e : getEmpruntEnCours()) {

			if (e.getDateEmprunt().getTime() + dureeMaxPrets * 86400000 < datetoday.getTime())
				retour++;

		}
		return retour;
	}
	
	@Override
	public boolean isConditionsPretAcceptees() {

		if (getNbRetards() > 0 || getNbEmpruntsEnCours() >= nbMaxPrets)
			return false;

		return true;

	}

	public static int getNbMaxPrets() {
		return nbMaxPrets;
	}

	public static void setNbMaxPrets(int nbMaxPrets) {
		Adherent.nbMaxPrets = nbMaxPrets;
	}

	public static int getDureeMaxPrets() {
		return dureeMaxPrets;
	}

	public static void setDureeMaxPrets(int dureeMaxPrets) {
		Adherent.dureeMaxPrets = dureeMaxPrets;
	}
	
	

}