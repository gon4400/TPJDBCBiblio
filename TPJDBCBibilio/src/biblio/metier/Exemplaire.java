package biblio.metier;

import java.util.Date;

public class Exemplaire {
	private int idExemplaire;
	private Date dateAchat;
	private EnumStatusExemplaire status;
	private Livre livre;
	
	public Exemplaire(int idExemplaire, Date dateAchat, EnumStatusExemplaire status, Livre livre) {
		super();
		this.idExemplaire = idExemplaire;
		this.dateAchat = dateAchat;
		this.status = status;
		this.livre = livre;
	}

	public int getIdExemplaire() {
		return idExemplaire;
	}

	public void setIdExemplaire(int idExemplaire) {
		this.idExemplaire = idExemplaire;
	}

	

	public Date getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(Date dateAchat) {
		this.dateAchat = dateAchat;
	}

	public EnumStatusExemplaire getStatus() {
		return status;
	}

	public void setStatus(EnumStatusExemplaire status) {
		this.status = status;
	}

	public Livre getLivre() {
		return livre;
	}

	public void setLivre(Livre livre) {
		this.livre = livre;
	}

	@Override
	public String toString() {
		return "Exemplaire [idExemplaire=" + idExemplaire + ", dateAchat=" + dateAchat
				+ ", status=" + status + ", livre=" + livre + "]";
	}
	
	
	
	
	
	
}
