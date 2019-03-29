package biblio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

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
		PreparedStatement pstmt = cnx.prepareStatement("SELECT * FROM Utilisateur WHERE idUtilisateur=?");
		pstmt.setInt(1, idUtilisateur);
		ResultSet rs = pstmt.executeQuery();
		Utilisateur u = null;

		boolean next = rs.next();

		if (next) {
			int idutilisateur = rs.getInt("idUtilisateur");
			String nom = rs.getString("nom");
			String prenom = rs.getString("prenom");
			String pseudonyme = rs.getString("pseudonyme");
			String pwd = rs.getString("pwd");
			Date dateNaissance = rs.getDate("dateNaissance");
			String sexe = rs.getString("sexe");

			u = new Utilisateur(nom, prenom, dateNaissance, sexe, idutilisateur, pwd, pseudonyme); // ici, mapping Objet
																									// Relationel
		} else {
			u = null;
		}

		return u;

	}

	public ArrayList<Utilisateur> findall() throws SQLException {
		Statement stmt = cnx.createStatement();
		ArrayList<Utilisateur> listeUtilisateur = new ArrayList<Utilisateur>();
		ResultSet rs = stmt.executeQuery("select * FROM Utilisateur");
		while (rs.next()) {
			int idutilisateur = rs.getInt("idUtilisateur");
			String nom = rs.getString("nom");
			String prenom = rs.getString("prenom");
			String pseudonyme = rs.getString("pseudonyme");
			String pwd = rs.getString("pwd");
			Date dateNaissance = rs.getDate("dateNaissance");
			String sexe = rs.getString("sexe");
			Utilisateur u = new Utilisateur(nom, prenom, dateNaissance, sexe, idutilisateur, pwd, pseudonyme);// mapping
																												// Objet
																												// Relationel
			listeUtilisateur.add(u);

		}

		return listeUtilisateur;
	}
}
