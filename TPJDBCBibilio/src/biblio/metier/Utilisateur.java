package biblio.metier;

import java.util.ArrayList;
import java.util.Date;

public class Utilisateur extends Personne {

	private int idUtilisateur;
	private String pwd;
	private String pseudonyme;
	private ArrayList<EmpruntEnCours> empruntEnCours = new ArrayList<>();

	public Utilisateur(String nom, String prenom, Date dateNaissance, String sexe, int idUtilisateur, String pwd,
			String pseudonyme) {
		super(nom, prenom, dateNaissance, sexe);
		this.idUtilisateur = idUtilisateur;
		this.pwd = pwd;
		this.pseudonyme = pseudonyme;
	

	}

	public ArrayList<EmpruntEnCours> getEmpruntEnCours() {
		return empruntEnCours;
	}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPseudonyme() {
		return pseudonyme;
	}

	public void setPseudonyme(String pseudonyme) {
		this.pseudonyme = pseudonyme;
	}

	public void addEmpruntEnCours(EmpruntEnCours ep) {
		empruntEnCours.add(ep);
		ep.getExemplaire().setStatus(EnumStatusExemplaire.PRETE);
		ep.setEmprunteur(this);
	}

	public EmpruntEnCours retourEmprunt(Exemplaire retour) throws BiblioException {

		for (EmpruntEnCours eec : empruntEnCours) {
			if (eec.getExemplaire().getIdExemplaire() == retour.getIdExemplaire()) {
				empruntEnCours.remove(eec);
				retour.setStatus(EnumStatusExemplaire.DISPONIBLE);
				return eec;
			}
		}
		throw new BiblioException("Vous n'avez pas emprunté ce livre");

	}

	public int getNbEmpruntsEnCours() {

		return empruntEnCours.size();
	}

	@Override
	public boolean isConditionsPretAcceptees() {
		return true;
	}


}
