package biblio.test;

import biblio.dao.ExemplairesDao;
import biblio.dao.UtilisateursDao;
import biblio.domain.Employe;
import biblio.domain.EmpruntEnCours;
import biblio.domain.EnumCategorieEmploye;
import biblio.domain.EnumStatusExemplaire;
import biblio.domain.Exemplaire;

public class TestEmployeTroisEmprunts {

	public static void main(String[] args) {
		//////////////////////////////////////////////
		////////////// creation exemplaire dao//////////////
		ExemplairesDao dao1 = new ExemplairesDao();
		////////////// creation 1 er exemplaire //////////////
		Exemplaire ex1 = new Exemplaire("25/05/2015", "ISBN001");

		System.out.println(ex1.toString());
		////////////// creation 2 er exemplaire //////////////
		Exemplaire ex2 = new Exemplaire("18/03/2012", "ISBN002");

		System.out.println(ex2.toString());
		////////////// creation 3 er exemplaire //////////////
		Exemplaire ex3 = new Exemplaire("18/09/2014", "ISBN003");

		System.out.println(ex3.toString());
		////////////// creation 4 er exemplaire //////////////
		Exemplaire ex4 = new Exemplaire("20/03/2013", "ISBN004");

		System.out.println(ex4.toString());
		////////////// ajout des exemplaires //////////////
		dao1.ajoutExemplaire(ex1);
		dao1.ajoutExemplaire(ex2);
		dao1.ajoutExemplaire(ex3);
		dao1.ajoutExemplaire(ex4);
		////////////// creation utilisateur dao //////////////
		UtilisateursDao UtilisateurDao1 = new UtilisateursDao();
		////////////// creation de l'employé //////////////
		Employe em1 = new Employe("boulet", "Jean-arthur", "22/07/1995", "homme", "arthur14", "luffy", "arthur55",
				EnumCategorieEmploye.BIBLIOTHECAIRE);
		System.out.println(em1);
		System.out.println();
		////////////// ajout de l'employé //////////////
		UtilisateurDao1.ajoutUtilisateur(em1);
		////////////// 1er emprunt en cours //////////////
		EmpruntEnCours ep1 = new EmpruntEnCours(ex1, em1);
		em1.addEmpruntEnCours(ep1);
		ex1.setEmpruntEnCours(ep1);
		ex1.setStatus(EnumStatusExemplaire.PRETE);
		System.out.println(ep1);
		////////////// 2er emprunt en cours //////////////
		EmpruntEnCours ep2 = new EmpruntEnCours(ex2, em1);
		em1.addEmpruntEnCours(ep2);
		ex2.setEmpruntEnCours(ep2);
		ex2.setStatus(EnumStatusExemplaire.PRETE);
		System.out.println(ep2);
		////////////// 3er emprunt en cours //////////////
		EmpruntEnCours ep3 = new EmpruntEnCours(ex3, em1);
		em1.addEmpruntEnCours(ep3);
		ex3.setEmpruntEnCours(ep3);
		ex3.setStatus(EnumStatusExemplaire.PRETE);
		System.out.println(ep3);
		////////////// 4er emprunt en cours //////////////
		EmpruntEnCours ep4 = new EmpruntEnCours(ex4, em1);
		em1.addEmpruntEnCours(ep4);
		ex4.setEmpruntEnCours(ep4);
		ex4.setStatus(EnumStatusExemplaire.PRETE);
		System.out.println(ep4);
		System.out.println(em1.getNbEmpruntsEnCours());

	}

}
