package biblio.metier;

import java.util.Date;

public class Employe extends Utilisateur {

	private String codeMatricule;
	private EnumCategorieEmploye categorieEmploye;

	public Employe(String nom, String prenom, Date dateNaissance, String sexe, int idUtilisateur, String pwd,
			String pseudonyme, String codeMatricule, EnumCategorieEmploye categorieEmploye) {
		super(nom, prenom, dateNaissance, sexe, idUtilisateur, pwd, pseudonyme);
		this.codeMatricule = codeMatricule;
		this.categorieEmploye = categorieEmploye;

	}

	public String getCodeMatricule() {
		return codeMatricule;
	}

	public void setCodeMatricule(String codeMatricule) {
		this.codeMatricule = codeMatricule;
	}

	public EnumCategorieEmploye getCategorieEmploye() {
		return categorieEmploye;
	}

	public void setCategorieEmploye(EnumCategorieEmploye categorieEmploye) {
		this.categorieEmploye = categorieEmploye;
	}

	@Override
	public boolean isConditionsPretAcceptees() {
		return true;
	}

}
