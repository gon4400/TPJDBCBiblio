package biblio.ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FenetreAccueil {
	public static String creationFenetre(String pseudo)
	{
		Object[] possibilities = {"Emprunt du livre","retour emprunt","Liste d'emprunt"};
		return (String)JOptionPane.showInputDialog(new JFrame(),"Bienvenue   "+pseudo+"\nQuel est votre choix","Biblio",JOptionPane.PLAIN_MESSAGE, null,  possibilities,"emprunt");
		
	}
}
