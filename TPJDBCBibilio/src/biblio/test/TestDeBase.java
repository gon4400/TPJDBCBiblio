package biblio.test;

import biblio.dao.ExemplaireDao;

public class TestDeBase {

	public static void main(String[] args) {

		//////////////////////////////////////////////////////////////////////////
		// ============ Creation d'un Data base des Exemplaires (DAO)
		ExemplaireDao DAO1 = new ExemplaireDao();
		// ============ Creation de la Dao exemplaire
		Exemplaire ex1 = new Exemplaire("25/05/2018", "ISBN001");
		System.out.println(ex1);
		// ajout de l'exemplaire
		DAO1.ajoutExemplaire(ex1);
		// Recherche de l'exemplaire
		System.out.println("Recherche dans la Dao de la key 1000 : " + DAO1.findByKey(1000));
		System.out.println();

		Exemplaire ex2 = new Exemplaire("18/03/2017", "ISBN002");
		System.out.println(ex2);
		DAO1.ajoutExemplaire(ex2);
		// Recherche de l'exemplaire
		System.out.println("Recherche dans la Dao de la key 1001 : " + DAO1.findByKey(1001));

		System.out.println();

		// =========== Cr�ation d'un Data Base des Utilisateurs
		UtilisateursDao UtilisateurDao1 = new UtilisateursDao();

		// ============ Creation d'un Adherent
		Adherent ad = new Adherent("m", "pierre", "05/02/1994", "homme", "pierre", "pierre77", "058956585");
		System.out.println(ad.toString());
		UtilisateurDao1.ajoutUtilisateur(ad);
		System.out.println("Recherche de l'adherent avec id 1000 : " + UtilisateurDao1.findByKey(1000));
		System.out.println();

		// =========== Creation d'un employe
		Employe em = new Employe("k", "lea", "25/05/1998", "Femme", "lea", "lea1855", "matr1",
				EnumCategorieEmploye.BIBLIOTHECAIRE);
		System.out.println(em);

		UtilisateurDao1.ajoutUtilisateur(em);
		System.out.println("Recherche de l'employé avec id 1001 : " + UtilisateurDao1.findByKey(1001));
		System.out.println();

		// ======== Creation d'un emprunt en cours pour un adherent

		EmpruntEnCours emp1 = new EmpruntEnCours(ex1, ad);
		ad.addEmpruntEnCours(emp1);
		ex1.setEmpruntEnCours(emp1);
		ex1.setStatus(EnumStatusExemplaire.PRETE);

		System.out.println("===========================");

		// ======= Création d'un emprunt en cours pour un employé
		EmpruntEnCours emp2 = new EmpruntEnCours(ex2, em);
		em.addEmpruntEnCours(emp2);
		ex2.setEmpruntEnCours(emp2);
		ex2.setStatus(EnumStatusExemplaire.PRETE);

		// ======= Affichage des emprunt en cours
		System.out.println(emp1);
		System.out.println(emp2);

		// ===========Affichage emprunt par personne
		System.out.println("Nombre d'emprunt pour l'adherent 1000 : " + ad.getNbEmpruntsEnCours());
		System.out.println("Nombre de'emprunt pour l'employe 1001 : " + em.getNbEmpruntsEnCours());

	}
}
