package biblio.metier;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Emprunt 
{
	private Date dateEmprunt;
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	public Emprunt(Date dateEmprunt) {
		super();
		setDateEmprunt(dateEmprunt);
	}

	public Date getDateEmprunt() {
		return dateEmprunt;
	}

	public void setDateEmprunt(Date dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
		
	}
	
	

}