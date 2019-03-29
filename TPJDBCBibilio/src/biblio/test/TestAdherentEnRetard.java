package biblio.test;

import biblio.dao.ExemplaireDao;
import biblio.metier.Exemplaire;

public class TestAdherentEnRetard {
	public static void main(String[] args) {
		//////////////////////////
		///////////////// Creation  exemplaires Dao//////////////////////////////

		ExemplaireDao ed1 = new ExemplaireDao();
		System.out.println("Recherche dans la Dao de la key 1000 : " + ed1.findByKey(1000));
		System.out.println();
		// creation exemplaires 1
		Exemplaire ex1 = new Exemplaire("25/05/2015", "ISBN001");
		System.out.println(ex1);
		System.out.println("Recherche dans la Dao de la key 1001 : " + ed1.findByKey(1001));
		System.out.println();
		/// creation exemplaires 2
		Exemplaire ex2 = new Exemplaire("18/03/2012", "ISBN002");
		System.out.println(ex2);
		System.out.println();
		// ajout exemplaire 1 et 2
		ed1.ajoutExemplaire(ex1);
		ed1.ajoutExemplaire(ex2);
		/// creation utilisateur dao
		UtilisateursDao UtilisateurDao1 = new UtilisateursDao();

		System.out.println("Recherche de l'adherent avec id 1000 : " + UtilisateurDao1.findByKey(1000));
		System.out.println();
		/// creation adherent
		Adherent ad1 = new Adherent("b", "kilian", "05/02/1988", "homme", "kilian14", "psd145", "0675481233");
		System.out.println(ad1);
		System.out.println();
		// ajout de l'adherant
		UtilisateurDao1.ajoutUtilisateur(ad1);
		// 1er emprunt en cours
		EmpruntEnCours emp1 = new EmpruntEnCours(ex1, ad1, "22/02/2019");
		ad1.addEmpruntEnCours(emp1);

		ex1.setEmpruntEnCours(emp1);
		ex1.setStatus(EnumStatusExemplaire.PRETE);
		System.out.println(emp1);
		System.out.println(ad1.isConditionsPretAcceptees());
		System.out.println(ad1.getNbRetards() + " jours");
		// 2er emprunt en cours
		EmpruntEnCours emp2 = new EmpruntEnCours(ex1, ad1, "22/02/2017");
		ad1.addEmpruntEnCours(emp2);
		ex1.setEmpruntEnCours(emp2);

		ex1.setStatus(EnumStatusExemplaire.PRETE);
		System.out.println(emp2);
		System.out.println(ad1.isConditionsPretAcceptees());
		System.out.println(ad1.getNbRetards() + " jours");

	}

}
