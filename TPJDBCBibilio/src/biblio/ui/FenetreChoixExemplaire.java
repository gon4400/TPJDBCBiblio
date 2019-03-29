package biblio.ui;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import biblio.metier.EnumStatusExemplaire;
import biblio.metier.Exemplaire;

public class FenetreChoixExemplaire {
	
	public static int creationFenetreChoixExemplaire(ArrayList<Exemplaire> listeExemplaire)
	{
		Object[] possibilities = new Object[listeExemplaire.size()];
		for(int i=0;i<listeExemplaire.size();i++)
		{
			if(listeExemplaire.get(i).getStatus()==EnumStatusExemplaire.DISPONIBLE)
				possibilities[i]=listeExemplaire.get(i).getIdExemplaire();
			i++;
		}
		return (int)JOptionPane.showInputDialog(new JFrame(), "Choississez l'exemplaire que vous voulez emprunter : ", "BiblioEmprunt", JOptionPane.PLAIN_MESSAGE,null,possibilities,null);
	}
}
