package biblio.test;

import biblio.dao.ExemplairesDao;
import biblio.dao.UtilisateursDao;
import biblio.domain.Employe;
import biblio.domain.EmpruntEnCours;
import biblio.domain.EnumCategorieEmploye;
import biblio.domain.EnumStatusExemplaire;
import biblio.domain.Exemplaire;

public class TestEmployerEnRetard {
	public static void main(String[] args) {
		//////////////////////////////////////////////////////////////////////////
		// ============ Creation d'un Data base des Exemplaires (DAO)
		ExemplairesDao DAO1 = new ExemplairesDao();

		// ============ Creation de la Dao exemplaire
		Exemplaire ex1 = new Exemplaire("25/05/2015", "ISBN001");
		System.out.println(ex1);
		// ajout de l'exemplaire
		DAO1.ajoutExemplaire(ex1);
		// Recherche de l'exemplaire
		System.out.println("Recherche dans la Dao de la key 1000 : " + DAO1.findByKey(1000));
		System.out.println();

		Exemplaire ex2 = new Exemplaire("18/03/2012", "ISBN002");
		System.out.println(ex2);
		DAO1.ajoutExemplaire(ex2);
		// Recherche de l'exemplaire
		System.out.println("Recherche dans la Dao de la key 1001 : " + DAO1.findByKey(1001));
		System.out.println();

		// =========== Cr�ation d'un Data Base des Utilisateurs
		UtilisateursDao UtilisateurDao1 = new UtilisateursDao();

		// =========== Creation d'un employe
		Employe em = new Employe("NomEmploye", "PrenomEmploye", "25/05/1980", "Femme", "pwd", "pseudo", "matr1",
				EnumCategorieEmploye.BIBLIOTHECAIRE);
		System.out.println(em.toString());
		UtilisateurDao1.ajoutUtilisateur(em);
		System.out.println("Recherche de l'adherent avec id 1000 : " + UtilisateurDao1.findByKey(1000));
		System.out.println();

		// ======== Creation d'un emprunt en cours pour un employe

		EmpruntEnCours emp1 = new EmpruntEnCours(ex1, em, "01/03/2000");
		System.out.println(emp1);

		em.addEmpruntEnCours(emp1);
		ex1.setEmpruntEnCours(emp1);
		ex1.setStatus(EnumStatusExemplaire.PRETE);

		// ======== Creation d'un emprunt en cours pour un employe
		EmpruntEnCours emp2 = new EmpruntEnCours(ex2, em);
		em.addEmpruntEnCours(emp2);
		ex2.setEmpruntEnCours(emp2);
		ex2.setStatus(EnumStatusExemplaire.PRETE);

		// ===========Affichage des emprunts de l'employé
		System.out.println("Nombre d'emprunt de l'employé : " + em.getNbEmpruntsEnCours());

	}
}
