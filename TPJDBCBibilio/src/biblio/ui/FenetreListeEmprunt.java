package biblio.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import biblio.controller.Emprunterctl;
import biblio.metier.EmpruntEnCours;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.FlowLayout;

public class FenetreListeEmprunt {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetreListeEmprunt window = new FenetreListeEmprunt(null);
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
	public FenetreListeEmprunt(ArrayList<EmpruntEnCours> listeEmprunt) {
		initialize(listeEmprunt);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(ArrayList<EmpruntEnCours> listeEmprunt) {
		frame = new JFrame();
		frame.setTitle("liste d'emprunt des livres");
		frame.setBounds(100, 100, 450, 300);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BufferedImage myPicture;
		try {
			myPicture = ImageIO.read(new File("./src/liste.png"));

			JPanel panel_1 = new JPanel();
			frame.getContentPane().add(panel_1, BorderLayout.SOUTH);

			JButton btnNewButton = new JButton("Retour");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Emprunterctl.choix();
				}
			});
			panel_1.add(btnNewButton);

			JPanel panel_2 = new JPanel();
			frame.getContentPane().add(panel_2, BorderLayout.CENTER);

			List list = new List();
			for (EmpruntEnCours eec : listeEmprunt) {
				list.add(eec.getExemplaire().getIdExemplaire() + " " + eec.getDateEmprunt());
			}
			panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 50));
			panel_2.add(list);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
