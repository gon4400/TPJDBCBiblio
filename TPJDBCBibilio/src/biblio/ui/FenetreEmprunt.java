package biblio.ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FenetreEmprunt {
	public static String creationFenetreEmprunt(String pseudo)
	{
		return JOptionPane.showInputDialog(new JFrame(), "Bienvenue  "+pseudo+"\nEntrez le isbn du livre que vous souhaitez emprunter : ", "BiblioEmprunt", JOptionPane.PLAIN_MESSAGE);
	}
	
	public static void main(String[] args) {
		System.out.println(creationFenetreEmprunt("pierre"));
	}
}