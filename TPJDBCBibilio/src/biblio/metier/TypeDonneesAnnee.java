package biblio.metier;

public class TypeDonneesAnnee {
	private int annee;
	
	public TypeDonneesAnnee(int annee) throws BiblioException
	{
		if (annee<0) throw new BiblioException("L'annee de partution ne peut pas être negatif");
		this.annee=annee;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}
}
