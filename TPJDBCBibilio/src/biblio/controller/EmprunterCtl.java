package biblio.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import biblio.dao.ConnectionFactory;
import biblio.dao.EmpruntArchiveDao;
import biblio.dao.EmpruntEnCoursDao;
import biblio.dao.EmpruntEnCoursDb;
import biblio.dao.ExemplaireDao;
import biblio.dao.UtilisateurDao;
import biblio.metier.BiblioException;
import biblio.metier.EmpruntArchive;
import biblio.metier.EmpruntEnCours;
import biblio.metier.EnumStatusExemplaire;
import biblio.metier.Exemplaire;
import biblio.metier.Utilisateur;
import biblio.ui.FenetreAccueil;
import biblio.ui.FenetreChoixExemplaire;
import biblio.ui.FenetreConnexion;
import biblio.ui.FenetreEmprunt;
import biblio.ui.FenetreListeEmprunt;
import biblio.ui.FenetreRetour;

public class EmprunterCtl {

	private static ExemplaireDao DBEx;
	private static EmpruntArchiveDao DBEA;
	private static EmpruntEnCoursDao DBEC;
	private static UtilisateurDao DBU;
	public static SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

	private static void recoverListEmprunt(Utilisateur u1) throws Exception {
		ArrayList<EmpruntEnCoursDb> listEmpruntEnCoursDb = new ArrayList<EmpruntEnCoursDb>();
		listEmpruntEnCoursDb = DBEC.findbyUtilisateur(u1);
		for (EmpruntEnCoursDb empruntEnCoursDB : listEmpruntEnCoursDb) {
			u1.addEmpruntEnCours(new EmpruntEnCours(empruntEnCoursDB.getDateEmprunt(),u1,DBEx.findByKey(empruntEnCoursDB.getIdExemplaire()),empruntEnCoursDB.getIdEmpruntEnCours()));	}

	}
	public static void main(String[] args) throws Exception {
		Connection cnx = ConnectionFactory.getConnection("oracle.jdbc.driver.OracleDriver",
				"jdbc:oracle:thin:@localhost:1521:xe", "biblio", "biblio");
		Utilisateur u1 = null;
		Exemplaire ex1 = null;
		int retour=0;
		int idexem = 0;
		String isbn;
		ArrayList<Exemplaire> listeExemplaire = new ArrayList<>();
		EmpruntEnCours eecRetour = null;
		DBEx = new ExemplaireDao(cnx);
		DBEA = new EmpruntArchiveDao(cnx);
		DBEC = new EmpruntEnCoursDao(cnx);
		DBU = new UtilisateurDao(cnx);
		while (true) {
			String[] id = FenetreConnexion.creationFenetre();
			try {
				u1 = DBU.findByKey(Integer.parseInt((id[0])));
			} catch (SQLException e) {
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "fin");
				break;
			}
			if (!u1.getPwd().equals(id[1])) {
				JOptionPane.showMessageDialog(null, "login ou mot de passe incorrect");
				continue;
			}
			
		  recoverListEmprunt(u1);
			String accueil = FenetreAccueil.creationFenetre(u1.getPseudonyme());

			if (accueil == null) {
				continue;
			}
			if (accueil.toLowerCase().equals("Emprunt du livre".toLowerCase())) {
				try {
					isbn = FenetreEmprunt.creationFenetreEmprunt(u1.getPseudonyme());
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					continue;
				}
				try {
					listeExemplaire = DBEx.findByIsbn(isbn);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					continue;
				}

				if (listeExemplaire.size() == 0) {
					JOptionPane.showMessageDialog(null, "Pas d'exemplaire disponible correspondant à ce livre");
					continue;
				}
				idexem = FenetreChoixExemplaire.creationFenetreChoixExemplaire(listeExemplaire);
				try {
					ex1 = DBEx.findByKey(idexem);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					continue;
				}

				if (!u1.isConditionsPretAcceptees()) {
					JOptionPane.showMessageDialog(null, "Conditions non respectées");
					continue;
				}
				if (ex1.getStatus() != EnumStatusExemplaire.DISPONIBLE) {

					JOptionPane.showMessageDialog(null, "Livre pas disponible");
					continue;
				}
				EmpruntEnCours eec = new EmpruntEnCours(new Date(), 2);
				eec.setExemplaire(ex1);
				u1.addEmpruntEnCours(eec);
				DBEC.insertEmpruntEncours(eec);
				DBEx.updateStatus(ex1);
				JOptionPane.showMessageDialog(null, "Emprunt effectué");

			} else if (accueil.toLowerCase().equals("retour emprunt")) {
				try{retour = FenetreRetour.creationFenetreRetour(u1.getPseudonyme(), u1);}
				catch(NullPointerException e) {continue;}

				

				if (listeExemplaire.size() == 0) {
					JOptionPane.showMessageDialog(null, "Pas d'exemplaire disponible correspondant à ce livre");
					continue;
				}

				if (retour == -1) {
					continue;
				} else {
					ex1 = DBEx.findByKey(retour);
					try {
						eecRetour = u1.retourEmprunt(ex1);
					} catch (BiblioException e) {
						System.err.println("erreur improbable");
						return;
					}
					EmpruntArchive ea = new EmpruntArchive(new Date(), eecRetour.getDateEmprunt(),
							eecRetour.getEmprunteur(), 1, ex1);
					DBEA.insertEmpruntArchive(ea);
					DBEx.updateStatus(ex1);
					DBEC.remove(eecRetour);
					eecRetour = null;
					JOptionPane.showMessageDialog(null, "Livre rendu");
				}
			} else if (accueil.toLowerCase().equals("Liste d'emprunt".toLowerCase())) {
				FenetreListeEmprunt.creationFenetre(u1);
			}
		}
	}
}