package biblio.metier;

import java.util.Date;

public class Auteur extends Personne
{
	
	public Auteur(String nom, String prenom, Date dateNaissance, String sexe) {
		super(nom, prenom, dateNaissance, sexe);
	}

	@Override
	public boolean isConditionsPretAcceptees() {
		return true;
	}
}