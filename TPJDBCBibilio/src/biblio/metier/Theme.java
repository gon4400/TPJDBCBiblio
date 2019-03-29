package biblio.metier;

public class Theme {
	private String code;
	private String libelle;
	private Theme parent;
	
	
	public Theme(String code, String libelle, Theme parent) {
		this.code = code;
		this.libelle = libelle;
		this.parent = parent;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Theme getParent() {
		return parent;
	}
	public void setParent(Theme parent) {
		this.parent = parent;
	}
	
	

}
