package biblio.ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import biblio.metier.EmpruntEnCours;
import biblio.metier.Exemplaire;
import biblio.metier.Utilisateur;

public class FenetreListeEmprunt {
	public static void creationFenetre(Utilisateur u)
	{
		String emprunt="Vos Emprunts :\n";
		for(EmpruntEnCours eec : u.getEmpruntEnCours())
		{
			Exemplaire e = eec.getExemplaire();
			emprunt+="IdExemplaire : "+ e.getIdExemplaire()+" / Date de l'emprunt : "
			+(eec.getDateEmprunt())+"\n";
		}
		JOptionPane.showMessageDialog(new JFrame(), emprunt, "ListeEmprunt", JOptionPane.INFORMATION_MESSAGE);
	}
}