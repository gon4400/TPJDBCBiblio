package biblio.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.List;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import biblio.controller.Emprunterctl2;
import biblio.metier.EmpruntEnCours;

public class FenetreListeEmprunt2 {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetreListeEmprunt2 window = new FenetreListeEmprunt2(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FenetreListeEmprunt2(ArrayList<EmpruntEnCours> listeEmprunt) {
		initialize(listeEmprunt);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(ArrayList<EmpruntEnCours> listeEmprunt) {
		frame = new JFrame();
		frame.setTitle("liste d'emprunt");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		List list = new List();
		list.setBounds(38, 29, 209, 202);
		for(EmpruntEnCours eec:listeEmprunt)
		{
			list.add(eec.getExemplaire().getIdExemplaire()+" "+eec.getDateEmprunt());
		}
		panel.add(list);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Emprunterctl2.choix();
			}
		});
		btnRetour.setBounds(304, 128, 89, 23);
		panel.add(btnRetour);
		
		
	}

}
