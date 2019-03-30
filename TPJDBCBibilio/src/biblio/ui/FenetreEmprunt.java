package biblio.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import biblio.controller.Emprunterctl;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

public class FenetreEmprunt {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetreEmprunt window = new FenetreEmprunt();
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
	public FenetreEmprunt() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("emprunt d'un livre");
		frame.setBounds(100, 100, 450, 300);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2, BorderLayout.NORTH);
		BufferedImage myPicture;
		try {
			myPicture = ImageIO.read(new File("./src/emprunter.png"));
		
			
		JLabel lblNewLabel_1 = new JLabel(new ImageIcon(myPicture));
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		panel_2.add(lblNewLabel_1);
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("Entrez l'id de l'exemplaire que vous souhaitez emprunter");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 10));
		lblNewLabel.setBackground(new Color(210, 180, 140));
		panel_1.add(lblNewLabel);
		
		JTextField  textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setHgap(20);
		frame.getContentPane().add(panel_3, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("OK");
		panel_3.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Emprunterctl.effectuerEmprunt(textField.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnNewButton_1 = new JButton("Retour");
		panel_3.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Emprunterctl.choix();
			}
		});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
