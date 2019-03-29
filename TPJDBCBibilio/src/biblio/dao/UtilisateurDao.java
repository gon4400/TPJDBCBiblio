package biblio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import biblio.metier.Adherent;
import biblio.metier.Employe;
import biblio.metier.EnumCategorieEmploye;
import biblio.metier.EnumStatusExemplaire;
import biblio.metier.Exemplaire;
import biblio.metier.Livre;
import biblio.metier.Utilisateur;

public class UtilisateurDao {

	Connection cnx = null;

	public UtilisateurDao(Connection cnx) {
		this.cnx = cnx;
	}

	public Utilisateur findByKey(int idUtilisateur) throws SQLException {
		PreparedStatement pstmt = cnx
				.prepareStatement("select * from utilisateur,adherent,employe  where utilisateur.idutilisateur=?");
		pstmt.setInt(1, idUtilisateur);
		ResultSet rs = pstmt.executeQuery();
		Utilisateur u = null;

		boolean next = rs.next();

		if (next) {
			int id = rs.getInt(1);
			String nom = rs.getString("nom");
			String prenom = rs.getString("prenom");
			String pseudonyme = rs.getString("pseudonyme");
			String pwd = rs.getString("pwd");
			Date dateNaissance = rs.getDate("dateNaissance");
			String sexe = rs.getString("sexe");
			String cat = rs.getString("categorieutilisateur");
			String telephone = "telephone";

			u = new Utilisateur(nom, prenom, dateNaissance, sexe, id, pwd, pseudonyme, cat); // ici, mapping
																								// Objet
			if (cat.equals("ADHERENT")) {

				u = new Adherent(nom, prenom, dateNaissance, sexe, id, pwd, pseudonyme, telephone, cat);

			}
			if (cat.equals("EMPLOYE")) {
				String code = rs.getString("codematricule");
				EnumCategorieEmploye status = null;
				if (rs.getString("categorieemploye").toLowerCase().equals("BIBLIOTHECAIRE".toLowerCase()))
					status = EnumCategorieEmploye.BIBLIOTHECAIRE;
				else if (rs.getString("categorieemploye".toLowerCase()).equals("GESTIONNAIRE".toLowerCase()))
					status = EnumCategorieEmploye.GESTIONNAIRE;
				else
					status = EnumCategorieEmploye.RESPONSABLE;
				u = new Employe(nom, prenom, dateNaissance, sexe, id, pwd, pseudonyme, code, status, cat);
			}
			// Relationel
		} else {
			u = null;
		}

		return u;

	}

	public ArrayList<Utilisateur> findall() throws SQLException {
		Statement stmt = cnx.createStatement();
		ArrayList<Utilisateur> listeUtilisateur = new ArrayList<Utilisateur>();
		Utilisateur user = null;
		ResultSet rs = stmt.executeQuery(
				"select utilisateur.idutilisateur, utilisateur.pwd, utilisateur.nom, utilisateur.prenom, categorieutilisateur, telephone, codematricule, categorieemploye "
						+ "from utilisateur, adherent, employe "
						+ "where utilisateur.idutilisateur=adherent.idutilisateur (+) "
						+ "and utilisateur.idutilisateur=employe.idutilisateur (+)");
		while (rs.next()) {
			int idutilisateur = rs.getInt("idUtilisateur");
			String nom = rs.getString("nom");
			String prenom = rs.getString("prenom");
			String pseudonyme = rs.getString("pseudonyme");
			String pwd = rs.getString("pwd");
			Date dateNaissance = rs.getDate("dateNaissance");
			String sexe = rs.getString("sexe");
			String cat = rs.getString("categorieutilisateur");
			String id = "";
			String tel = "";
			String code = "";
			String cat_employe = "";
			if (cat.equals("ADHERENT")) {
				tel = rs.getString(6);
				user = new Adherent(nom, prenom, dateNaissance, sexe, idutilisateur, pwd, pseudonyme, tel, cat);
			}
			if (cat.equals("EMPLOYE")) {
				code = rs.getString(7);
				cat_employe = rs.getString(8);
				EnumCategorieEmploye cat2 = EnumCategorieEmploye.valueOf(cat_employe.toLowerCase());
				user = new Employe(nom, prenom, dateNaissance, sexe, idutilisateur, pwd, pseudonyme, code, cat2, cat);
			}

		}

		return listeUtilisateur;
	}
}
