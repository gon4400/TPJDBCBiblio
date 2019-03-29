package biblio.test;

import biblio.dao.ExemplairesDao;
import biblio.dao.UtilisateursDao;
import biblio.domain.Adherent;
import biblio.domain.EmpruntEnCours;
import biblio.domain.EnumStatusExemplaire;
import biblio.domain.Exemplaire;

public class TestAdherentTroisEmprunts {

	public static void main(String[] args) {

		//////////////////////////////////////////////////////////////////////////
		// ============ Creation d'un Data base des Exemplaires (DAO)
		ExemplairesDao DAO1 = new ExemplairesDao();

		// Creation des exemplaires
		Exemplaire ex1 = new Exemplaire("25/05/2015", "ISBN001");
		DAO1.ajoutExemplaire(ex1);
		System.out.println(ex1.toString());

		Exemplaire ex2 = new Exemplaire("18/03/2012", "ISBN002");
		DAO1.ajoutExemplaire(ex2);
		System.out.println(ex2.toString());

		Exemplaire ex3 = new Exemplaire("18/09/2014", "ISBN003");
		DAO1.ajoutExemplaire(ex3);
		System.out.println(ex3.toString());

		Exemplaire ex4 = new Exemplaire("20/03/2013", "ISBN004");
		DAO1.ajoutExemplaire(ex4);
		System.out.println(ex4.toString());

		// =========== Creation d'un Data Base des Utilisateurs
		UtilisateursDao UtilisateurDao1 = new UtilisateursDao();

		// ============ Creation d'un Adherent

		Adherent ad = new Adherent("NomAdherent", "PrenomAdherent", "05/02/1985", "Femme", "pwd", "psd", "058956585");
		System.out.println(ad.toString());
		System.out.println();

		// =========== Ajouter dans la Data base des Utilisateurs : adherent et employé
		UtilisateurDao1.ajoutUtilisateur(ad);

		// ======== Creation d'un emprunt en cours pour l'adh�rent
		EmpruntEnCours emp1 = new EmpruntEnCours(ex1, ad);
		ad.addEmpruntEnCours(emp1);
		ex1.setEmpruntEnCours(emp1);
		ex1.setStatus(EnumStatusExemplaire.PRETE);
		System.out.println(emp1);

		// ======== Creation 2  emprunt en cours pour l'adhérent
		EmpruntEnCours emp2 = new EmpruntEnCours(ex2, ad);
		ad.addEmpruntEnCours(emp2);
		ex2.setEmpruntEnCours(emp2);
		ex2.setStatus(EnumStatusExemplaire.PRETE);
		System.out.println(emp2);

		// ======== Creation 3  emprunt en cours pour l'adhérent
		EmpruntEnCours emp3 = new EmpruntEnCours(ex3, ad);
		ad.addEmpruntEnCours(emp3);
		ex3.setEmpruntEnCours(emp3);
		ex3.setStatus(EnumStatusExemplaire.PRETE);
		System.out.println(emp3);

		// ======== Creation 4  emprunt en cours pour l'adhérent
		EmpruntEnCours emp4 = new EmpruntEnCours(ex4, ad);
		ad.addEmpruntEnCours(emp4);
		ex4.setEmpruntEnCours(emp4);
		ex4.setStatus(EnumStatusExemplaire.PRETE);
		System.out.println(emp4);
		System.out.println(ad.getNbEmpruntsEnCours());
		
	}
}
