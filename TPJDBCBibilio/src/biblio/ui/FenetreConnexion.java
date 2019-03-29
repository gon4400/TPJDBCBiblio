package biblio.ui;

import javax.swing.JFrame;

import javax.swing.JOptionPane;

import javax.swing.JTextField;

public class FenetreConnexion extends JFrame {

	private static final long serialVersionUID = 8067088524171262602L;

	public static String[] creationFenetre() {

		JTextField field1 = new JTextField();
		JTextField field2 = new JTextField();
		field1.setSize(300, 100);
		field2.setSize(300, 100);
		Object[] f = { "login", field1, "Mot de passe", field2 };

		JOptionPane.showConfirmDialog(null, f, "Entrez login et mot de passe", JOptionPane.OK_CANCEL_OPTION);
		String[] res = { field1.getText(), field2.getText() };
		return res;
	}

}
