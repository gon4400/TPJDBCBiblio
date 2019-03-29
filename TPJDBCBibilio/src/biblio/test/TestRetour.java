package biblio.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import biblio.dao.EmpruntArchiveDao;
import biblio.dao.UtilisateursDao;
import biblio.domain.Adherent;
import biblio.domain.EmpruntArchive;
import biblio.domain.EmpruntEnCours;
import biblio.domain.EnumStatusExemplaire;
import biblio.domain.Exemplaire;

public class TestRetour {

	public static void main(String[] args) throws ParseException {

		//////////////////////////////////////////////////////////////////////////
		// Creation des exemplaires

		Exemplaire ex1 = new Exemplaire("25/05/2015", "ISBN001");
		System.out.println(ex1);
		Exemplaire ex2 = new Exemplaire("18/03/2012", "ISBN002");
		System.out.println(ex2);
		System.out.println();

		// =========== Cr�ation d'un Data Base des Utilisateurs
		UtilisateursDao UtilisateurDao1 = new UtilisateursDao();

		// ============ Creation d'un Adherent
		Adherent ad = new Adherent("meignan", "pierre", "07/29/1994", "homme", "pierre", "pierre77", "0122548899");
		System.out.println(ad);
		System.out.println();

		// =========== Ajouter dans la Data base des Utilisateurs : adherent et employ�
		UtilisateurDao1.ajoutUtilisateur(ad);

		// ======== Creation d'un emprunt en cours pour l'adh�rent
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dtToday = new Date();

		EmpruntEnCours emp1 = new EmpruntEnCours(ex1, ad, "02/04/2016");
		ad.addEmpruntEnCours(emp1);
		ex1.setEmpruntEnCours(emp1);
		ex1.setStatus(EnumStatusExemplaire.PRETE);

		// ======== Creation 2 ieme emprunt en cours pour l'adh�rent
		EmpruntEnCours emp2 = new EmpruntEnCours(ex2, ad, "02/04/2016");
		ad.addEmpruntEnCours(emp2);
		ex2.setEmpruntEnCours(emp2);
		ex2.setStatus(EnumStatusExemplaire.PRETE);

		System.out.println(emp1);
		System.out.println(emp2);

		// ====== Nombre d'emprunt avant le retour
		System.out.println("Nombre d'emprunt en cours : " + ad.getNbEmpruntsEnCours());
		System.out.println();

		////////////////////////////////////////////////////////////////////////////////////////
		// Retour de l'exemplaire

		// ======= Ajout a la base DAO
		EmpruntArchiveDao eaDao = new EmpruntArchiveDao();

		// Enregistrement du retour à la date du jour
		// ========= Changement du status de l'exemplaire

		ex1.setStatus(EnumStatusExemplaire.DISPONIBLE);
		System.out.println(ex1);
		System.out.println();

		// ====== Enregistrement dans l'archive
		EmpruntArchive emA = new EmpruntArchive(emp1);
		System.out.println("Creation d'un emprunt à archiver : " + emA);

		// ====== Ajout de l'emprunt archivé dans la DAO
		System.out.println("Nombre d'emprunt en cours d'archivage: " + eaDao.getEmpruntArchiveDataBase().size());
		eaDao.ajoutEmpruntArchive(emA);
		System.out.println("Nombre d'emprunt en cours d'archivage: " + eaDao.getEmpruntArchiveDataBase().size());
		System.out.println();

		// ======= Destruction de l'objet emprunt en cours
		// ======= affichage de l'adherent et de l'exemplaire
		System.out.println("Collection d'empunt en cours avant :" + ad.getEmpruntEnCours());
		System.out.println(ex1.getTheEmpruntEnCours());
		System.out.println(ad.getNbEmpruntsEnCours());
		System.out.println();

		// utilisateur perd la reference de l'objet emprunt en cours
		// ======== Suppresion de l'emprunt en cours
		ad.removeEmpruntEnCours(emp1);
		System.out.println("Collection d'empunt en cours après remove : " + ad.getEmpruntEnCours());
		System.out.println(ad.getNbEmpruntsEnCours());
		System.out.println();

		// ======= Declenchement du garbage collector
		ArrayList<EmpruntEnCours> gC;
		for (int i = 0; i < 10000; i++) {
			gC = new ArrayList<EmpruntEnCours>();
		}

	}

}
