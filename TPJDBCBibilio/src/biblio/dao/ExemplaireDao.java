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

public class ExemplaireDao {

	private Connection cnx;
	public ExemplaireDao(Connection cnx) {
		this.cnx = cnx;
	}

	public Exemplaire findByKey(int idExemplaire) throws Exception {
		PreparedStatement pstmt = cnx.prepareStatement("SELECT * FROM EXEMPLAIRE WHERE IDEXEMPLAIRE=?");
		pstmt.setInt(1, idExemplaire);
		ResultSet rs = pstmt.executeQuery();
		Exemplaire e  = null;
		if (rs.next()) {
			e = createExemplaire(rs);
		}
		rs.close();
		pstmt.close();
		return e;
	}

	private Exemplaire createExemplaire(ResultSet rs) throws Exception {
		EnumStatusExemplaire status = null;
		if (rs.getString("STATUS").toLowerCase().equals("DISPONIBLE".toLowerCase()))
			status = EnumStatusExemplaire.DISPONIBLE;
		else if (rs.getString("STATUS".toLowerCase()).equals("PRETE".toLowerCase()))
			status = EnumStatusExemplaire.PRETE;
		else
			status = EnumStatusExemplaire.SUPPRIME;
		return new Exemplaire(rs.getInt("IDEXEMPLAIRE"), new Date(rs.getDate("DATEACHAT").getTime()), status,
				null);
	}

	public ArrayList<Exemplaire> findByIsbn(String isbn) throws Exception
	{
		PreparedStatement pstmt = cnx.prepareStatement("SELECT STATUS,IDEXEMPLAIRE,DATEACHAT,EXEMPLAIRE.ISBN FROM EXEMPLAIRE INNER JOIN LIVRE ON EXEMPLAIRE.ISBN=LIVRE.ISBN WHERE LIVRE.ISBN=?");
		pstmt.setString(1, isbn);
		ResultSet rs = pstmt.executeQuery();
		ArrayList<Exemplaire> listeExemplaire = new ArrayList<>();
		while(rs.next())
		{
			listeExemplaire.add(createExemplaire(rs));
		}
		return listeExemplaire;
		
	}
	
	
	public void updateStatus(Exemplaire exemplaire) throws Exception {
		PreparedStatement pstmt = cnx.prepareStatement("update EXEMPLAIRE set  status=? where idexemplaire=? ");
		pstmt.setString(1, String.valueOf(exemplaire.getStatus()));
		pstmt.setInt(2, exemplaire.getIdExemplaire());
		pstmt.executeUpdate();
	}
	

	public ArrayList<Exemplaire> findall() throws SQLException {
		Statement stmt = cnx.createStatement();
		ArrayList<Exemplaire> listeExemplaire = new ArrayList<Exemplaire>();
		ResultSet rs = stmt.executeQuery("select * FROM exemplaire");
		while (rs.next()) {
			Livre livre = null;
			int idexemplaire = rs.getInt(1);
			Date dateachat = rs.getDate(2);
			String status = rs.getString(3);
			String ISBN = rs.getString(4);
			EnumStatusExemplaire statut = EnumStatusExemplaire.valueOf(status);
			Exemplaire ex = new Exemplaire(idexemplaire, dateachat, statut, livre);// mapping Objet Relationel
			listeExemplaire.add(ex);

		}

		return listeExemplaire;
	}

}

