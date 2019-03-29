package biblio.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import biblio.metier.Adherent;
import biblio.metier.EmpruntEnCours;
import biblio.metier.Exemplaire;
import biblio.metier.Utilisateur;

public class EmpruntEnCoursDao {
	private Connection cnx;
	long millis = System.currentTimeMillis();

	public EmpruntEnCoursDao(Connection cnx) {
		super();
		this.cnx = cnx;
	}

	public boolean insertEmpruntEnCours(Utilisateur u, Exemplaire ex) throws SQLException {

		PreparedStatement pstmt = null;

		try {
			pstmt = cnx.prepareStatement("INSERT INTO empruntencours  VALUES (?,?,?,?)");
			pstmt.setInt(1, 1);
			pstmt.setInt(2, ex.getIdExemplaire());
			pstmt.setInt(3, u.getIdUtilisateur());
			pstmt.setDate(4, new Date(millis));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			pstmt.close();
			return false;
		}
		pstmt.close();
		return true;

	}

	public boolean insertEmpruntEncours(EmpruntEnCours emprunt) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = cnx.prepareStatement("INSERT INTO EMPRUNTENCOURS VALUES(?,?,?,?)");
			pstmt.setInt(1, emprunt.getIdEmpruntEnCours());
			pstmt.setInt(2, emprunt.getExemplaire().getIdExemplaire());
			pstmt.setInt(3, emprunt.getEmprunteur().getIdUtilisateur());
			pstmt.setDate(4, new Date(emprunt.getDateEmprunt().getTime()));
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			pstmt.close();
			return false;
		}
		pstmt.close();
		return true;
	}

	public EmpruntEnCours findByKey(int id) throws Exception {
		PreparedStatement pstmt = cnx.prepareStatement("SELECT * FROM EMPRUNTENCOURS WHERE IDEMPRUNTENCOURS=?");
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		EmpruntEnCours ec = createEmpruntEnCours(rs);
		rs.close();
		pstmt.close();
		return ec;
	}

	public ArrayList<EmpruntEnCoursDb> findbyUtilisateur(Utilisateur u) throws Exception {

		ArrayList<EmpruntEnCoursDb> listEmpruntEnCoursDb = new ArrayList<EmpruntEnCoursDb>();
		PreparedStatement pstmt = cnx.prepareStatement("select * FROM empruntencours where idutilisateur=?");
		pstmt.setInt(1, u.getIdUtilisateur());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {

			int idExemplaire = rs.getInt("idExemplaire");
			Date dateEmprunt = rs.getDate("dateEmprunt");
			int idEmpruntEncours = rs.getInt("idEmpruntEncours");
			EmpruntEnCoursDb epc = new EmpruntEnCoursDb(u.getIdUtilisateur(), idExemplaire, dateEmprunt, u, null,
					idEmpruntEncours);

			listEmpruntEnCoursDb.add(epc);
		}
		return listEmpruntEnCoursDb;
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

	public void remove(EmpruntEnCours empruntEncours) throws Exception {
		PreparedStatement pstmt = cnx.prepareStatement("delete empruntencours WHERE IDEMPRUNTENCOURS=?");
		pstmt.setInt(1, empruntEncours.getIdEmpruntEnCours());
		pstmt.executeUpdate();
	}

	private EmpruntEnCours createEmpruntEnCours(ResultSet rs) throws Exception {
		rs.next();
		EmpruntEnCours e = new EmpruntEnCours(new Date(rs.getDate("DATEEMPRUNT").getTime()),
				rs.getInt("IDEMPRUNTENCOURS"));
		e.setExemplaire(new ExemplaireDao(cnx).findByKey(rs.getInt("IDEXEMPLAIRE")));
		PreparedStatement pstmt = cnx.prepareStatement("SELECT * FROM UTILISATEUR WHERE IDUTILISATEUR=?");
		pstmt.setInt(1, rs.getInt("IDUTILISATEUR"));
		ResultSet rs2 = pstmt.executeQuery();
		rs2.next();
		pstmt.close();
		rs2.close();
		return e;
	}

}
