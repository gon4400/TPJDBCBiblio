package biblio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import biblio.metier.EmpruntArchive;

public class EmpruntArchiveDao {

	private Connection cnx;

	public EmpruntArchiveDao(Connection cnx) {
		this.cnx = cnx;
	}

	public EmpruntArchive findByKey(int id) throws Exception {
		PreparedStatement pstmt = cnx.prepareStatement("SELECT * FROM EMPRUNTENCOURS WHERE IDEMPRUNTENCOURS=?");
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		EmpruntArchive ea = createEmpruntArchive(rs);
		pstmt.close();
		rs.close();
		return ea;
	}

	public void insertEmpruntArchive(EmpruntArchive ea) throws Exception {
		PreparedStatement pstmt = cnx.prepareStatement("INSERT INTO EMPRUNTARCHIVE VALUES(?,?,?,?,?)");
		pstmt.setInt(1, ea.getIdempruntArchive());
		pstmt.setInt(2, ea.getEmprunteur().getIdUtilisateur());
		pstmt.setDate(3, new Date(ea.getDateRestitutionEff().getTime()));
		pstmt.setInt(4, ea.getExemplaire().getIdExemplaire());
		pstmt.setDate(5, new Date(ea.getDateEmprunt().getTime()));

		pstmt.executeUpdate();
		return;
	}

	private EmpruntArchive createEmpruntArchive(ResultSet rs) throws Exception {
		rs.next();
		EmpruntArchive e = new EmpruntArchive(new Date(rs.getDate("DATEEMPRUNT").getTime()),
				new Date(rs.getDate("DATERESITUTIONEFF").getTime()),
				new UtilisateurDao(cnx).findByKey(rs.getInt("IDUtilisateur")), rs.getInt("IDEMPRUNTENCOURS"),
				new ExemplaireDao(cnx).findByKey(rs.getInt("IDEXEMPLAIRE")));
		PreparedStatement pstmt = cnx.prepareStatement("SELECT * FROM UTILISATEUR WHERE IDUTILISATEUR=?");
		pstmt.setInt(1, rs.getInt("IDUTILISATEUR"));
		ResultSet rs2 = pstmt.executeQuery();
		rs2.next();
		pstmt.close();
		rs2.close();
		return e;
	}
}