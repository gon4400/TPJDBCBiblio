package biblio.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class FenetreConnexion2 {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JPanel panel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetreConnexion2 window = new FenetreConnexion2();
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
	public FenetreConnexion2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setTitle("Connexion");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(225, 89, 64, 100);
		frame.getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));
		
		JLabel lblNewLabel = new JLabel("Login");
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		panel.add(lblNewLabel_1);
		
		panel_1 = new JPanel();
		FlowLayout fl_panel_1 = (FlowLayout) panel_1.getLayout();
		fl_panel_1.setVgap(20);
		panel_1.setBounds(324, 89, 120, 100);
		frame.getContentPane().add(panel_1);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setHgap(20);
		panel_2.setBounds(224, 200, 200, 50);
		frame.getContentPane().add(panel_2);
		
		JButton btnNewButton = new JButton("Valider");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				FenetreAccueil2 window = new FenetreAccueil2();
				window.frame.setVisible(true);
			}
		});
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Anuler");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
			}
		});
		panel_2.add(btnNewButton_1);
		BufferedImage myPicture = null;
		BufferedImage myPicture2=null;
		try {
			myPicture = ImageIO.read(new File("D:\\Tp_Java\\Workspace_Pierre\\TPJDBCBibilio\\src\\biblio\\logo-biblio.jpg"));
			myPicture2 = ImageIO.read(new File("D:\\Tp_Java\\Workspace_Pierre\\TPJDBCBibilio\\src\\biblio\\bienvenue.jpg"));
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 11, 190, 239);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(new BorderLayout(-20, -20));
		JLabel logo = new JLabel(new ImageIcon(myPicture));
		panel_3.add(logo, BorderLayout.CENTER);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(200, 0, 224, 78);
		frame.getContentPane().add(panel_4);
		panel_3.add(logo, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("Bienvenue à la biblio de Paris");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblNewLabel_2, BorderLayout.CENTER);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
