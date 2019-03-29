package biblio.controller;

import java.awt.EventQueue;
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
import biblio.metier.Adherent;
import biblio.metier.BiblioException;
import biblio.metier.EmpruntArchive;
import biblio.metier.EmpruntEnCours;
import biblio.metier.EnumStatusExemplaire;
import biblio.metier.Exemplaire;
import biblio.metier.Utilisateur;
import biblio.ui.FenetreAccueil;
import biblio.ui.FenetreConnexion;
import biblio.ui.FenetreEmprunt;
import biblio.ui.FenetreListeEmprunt;
import biblio.ui.FenetreRetour;

public class Emprunterctl {

	private static FenetreConnexion fenetreconnexion;
	private static FenetreAccueil fenetreaccueil;
	private static FenetreListeEmprunt fenetrelisteemprunt;
	public static SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private static ExemplaireDao DBExemplaire;
	private static EmpruntEnCoursDao DBEEC;
	private static EmpruntArchiveDao DBEA;
	private static UtilisateurDao DBU;
	private static Utilisateur u1;
	private static FenetreEmprunt fenetreemprunt;
	private static FenetreRetour fenetreretour;
	static Adherent unAdherent = null;
	public static void main(String[] args) {

		if (fenetreaccueil != null) {
			fenetreaccueil.frame.dispose();
		}
		Connection cnx = null;
		
		try {
			cnx = ConnectionFactory.getConnection("oracle.jdbc.driver.OracleDriver",
					"jdbc:oracle:thin:@localhost:1521:xe", "biblio", "biblio");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erreur improbable\n" + e.getMessage());
			return;
		}
		DBExemplaire = new ExemplaireDao(cnx);
		DBEEC = new EmpruntEnCoursDao(cnx);
		DBEA = new EmpruntArchiveDao(cnx);
		DBU = new UtilisateurDao(cnx);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fenetreconnexion = new FenetreConnexion();
					fenetreconnexion.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void connection(String id, String mdp) throws Exception {
		fenetreconnexion.frame.dispose();

		try {
			u1 = DBU.findByKey(Integer.parseInt((id)));
		} catch(NumberFormatException e)
		{
			JOptionPane.showMessageDialog(null,"Identifiant ou mdp incorrect");		
			main(null);
			return;
		}
		catch(SQLException e)
		{
			try 
			{
				unAdherent = (Adherent) u1;
				u1 = DBU.findByKey(Integer.parseInt((id)));
			}
			catch(SQLException e2)
			{
				JOptionPane.showMessageDialog(null,"Identifiant ou mdp incorrect");		
				main(null);
				return;
			}
		}
		if(!u1.getPwd().equals(mdp))
		{
			
			JOptionPane.showMessageDialog(null,"Identifiant ou mdp incorrect");
			main(null);
			return;
		}
		try 
		{
			setEmpruntToEmprunteur(u1);
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null,e.getMessage());		
			main(null);
			return;
		}

		choix();

	}

	private static void setEmpruntToEmprunteur(Utilisateur u) throws Exception {
		ArrayList<EmpruntEnCoursDb> listEmpruntEnCoursDb = new ArrayList<EmpruntEnCoursDb>();
		listEmpruntEnCoursDb = DBEEC.findbyUtilisateur(u1);
		for (EmpruntEnCoursDb empruntEnCoursDB : listEmpruntEnCoursDb) {
			u1.addEmpruntEnCours(new EmpruntEnCours(empruntEnCoursDB.getDateEmprunt(), u1,
					DBExemplaire.findByKey(empruntEnCoursDB.getIdExemplaire()),
					empruntEnCoursDB.getIdEmpruntEnCours()));
		}

	}

	public static void emprunter() {
		if (fenetreemprunt != null) {
			fenetreemprunt.frame.dispose();
		}
		//fenetrechoix.frame.dispose();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fenetreemprunt = new FenetreEmprunt();
					fenetreemprunt.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public static void retour() {
		//fenetrechoix.frame.dispose();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArrayList<Exemplaire> listeExem = new ArrayList<>();
					for (EmpruntEnCours eec : u1.getEmpruntEnCours()) {
						listeExem.add(eec.getExemplaire());
					}
					fenetreretour = new FenetreRetour(listeExem);
					fenetreretour.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void listeEmprunt() {
		//fenetrechoix.frame.dispose();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fenetrelisteemprunt = new FenetreListeEmprunt(u1.getEmpruntEnCours());
					fenetrelisteemprunt.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public static void choix() {
		if (fenetreemprunt != null) {
			fenetreemprunt.frame.dispose();
		}
		if (fenetreretour != null) {
			fenetreretour.frame.dispose();
		}
		if (fenetrelisteemprunt != null) {
			fenetrelisteemprunt.frame.dispose();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fenetreaccueil = new FenetreAccueil();
					fenetreaccueil.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void effectuerEmprunt(String idExemplaire) throws Exception {
		Exemplaire e1 = null;
		int id = 0;
		try {
			id = Integer.parseInt(idExemplaire);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			emprunter();
			return;
		}
		try {
			e1 = DBExemplaire.findByKey(id);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			emprunter();
			return;
		}
	
		boolean conditionsAcceptees = false;
		if (u1 instanceof Adherent) {
		
			unAdherent = (Adherent) u1;
			if (!unAdherent.isConditionsPretAcceptees()) {
				JOptionPane.showMessageDialog(null,
						"Pret refuse\nNb de prets :"
								+ unAdherent.getNbEmpruntsEnCours()
								+ ". Maximum :" + Adherent.getNbMaxPrets()
								+ "\nNb de retards :"
								+ unAdherent.getNbRetards());
			} else {
				conditionsAcceptees = true;
				emprunter();
			}
		
		/*if (unAdherent.isConditionsPretAcceptees()) { 
			JOptionPane.showMessageDialog(null, "Conditions non respectées");
			emprunter();
			return;
		}*/
		
		
		
		if (e1.getStatus() != EnumStatusExemplaire.DISPONIBLE) {
	
			JOptionPane.showMessageDialog(null, "Livre non disponible");
			emprunter();
			return;
		}
		


		try {
			EmpruntEnCours eec = new EmpruntEnCours(new Date(), 2);
			eec.setExemplaire(e1);
			u1.addEmpruntEnCours(eec);
			DBEEC.insertEmpruntEncours(eec);
			DBExemplaire.updateStatus(e1);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			emprunter();
			return;
		}
		JOptionPane.showMessageDialog(null, "Emprunt effectué");
		choix();
		}
	}

	public static void effectuerRetour(String idExemplaire) throws Exception {
		Exemplaire e1 = null;
		EmpruntEnCours eecRetour = null;
		int id = 0;
		try {
			id = Integer.parseInt(idExemplaire);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			retour();
			return;
		}

		try {
			e1 = DBExemplaire.findByKey(id);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			retour();
			return;
		}
		try {
			eecRetour = u1.retourEmprunt(e1);
		} catch (BiblioException e) {
			System.err.println("erreur improbable");
			System.exit(1);
		}
		EmpruntArchive ea = new EmpruntArchive(new Date(), eecRetour.getDateEmprunt(),
				eecRetour.getEmprunteur(), 1, e1);
		DBEA.insertEmpruntArchive(ea);
		DBExemplaire.updateStatus(e1);
		DBEEC.remove(eecRetour);
		eecRetour = null;
		JOptionPane.showMessageDialog(null, "Livre rendu");
		choix();

	}

}