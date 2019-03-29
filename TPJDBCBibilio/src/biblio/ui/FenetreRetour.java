package biblio.ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import biblio.metier.EmpruntEnCours;
import biblio.metier.Utilisateur;

public class FenetreRetour {
	public static int creationFenetreRetour(String pseudo,Utilisateur u)
	{
		if(u.getNbEmpruntsEnCours()==0)
		{
			JOptionPane.showMessageDialog(null, "Vous n'avez pas d'emprunt en cours");
			return -1;
		}
		Object[] possibilities = new Object[u.getNbEmpruntsEnCours()];
		int i = 0;
		for(EmpruntEnCours eec : u.getEmpruntEnCours())
		{
			possibilities[i]=eec.getExemplaire().getIdExemplaire();
			i++;
		}
		//Object[] possibilities = {"emprunt", "retour"};
		return (int)JOptionPane.showInputDialog(new JFrame(), "Bienvenue "+pseudo+"\nEntrez l'id de l'exemplaire que vous voulez rendre : ", "BiblioRetour", JOptionPane.PLAIN_MESSAGE,null,possibilities,null);
	}
}