package biblio.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import biblio.controller.Emprunterctl;
import biblio.metier.Exemplaire;

import java.awt.BorderLayout;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.FlowLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FenetreRetour {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetreRetour window = new FenetreRetour(null);
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
	public FenetreRetour(ArrayList<Exemplaire> listeExem) {
		initialize(listeExem);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(ArrayList<Exemplaire> listeExem) {
		frame = new JFrame();
		frame.setTitle("Retour du livre");
		frame.setBounds(100, 100, 450, 300);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BufferedImage myPicture;
		try {
			myPicture = ImageIO.read(new File("./src/retour.png"));
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setVgap(80);
		frame.getContentPane().add(panel_2, BorderLayout.CENTER);
		
		List list_1 = new List();
		
		for(Exemplaire e : listeExem)
		{
			list_1.add(Integer.toString(e.getIdExemplaire()));
		}
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setHgap(20);
		flowLayout_1.setVgap(10);
		frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton_1  = new JButton("Retour");
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Rendre");
		panel_1.add(btnNewButton_1);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Emprunterctl.effectuerRetour(list_1.getSelectedItem());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Emprunterctl.choix();
			}
		});
		
		panel_1.add(btnNewButton);
		
		panel_2.add(list_1);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
