package biblio.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import biblio.controller.Emprunterctl2;
import biblio.metier.Exemplaire;

public class FenetreRetour2 {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetreRetour2 window = new FenetreRetour2(null);
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
	public FenetreRetour2(ArrayList<Exemplaire> listeExem) {
		initialize(listeExem);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(ArrayList<Exemplaire> listeExem) {
		frame = new JFrame();
		frame.setTitle("Retour");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		List list = new List();
		for(Exemplaire e : listeExem)
		{
			list.add(Integer.toString(e.getIdExemplaire()));
		}
		list.setBounds(54, 32, 76, 182);
		panel.add(list);
		
		JButton btnNewButton = new JButton("Rendre");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Emprunterctl2.effectuerRetour(list.getSelectedItem());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(255, 76, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Retour");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Emprunterctl2.choix();
			}
		});
		btnNewButton_1.setBounds(255, 152, 89, 23);
		panel.add(btnNewButton_1);
	}

}
