/* Ficher Authentification.java
* Auteur: Kenny Rigaud
* Date de création : 30 oct. 2022
*/
package ca.qc.collegeahuntsic.projet2.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * TODO Auteur : Kenny Jones, Rigaud
 */
public class FenAuthentification extends JFrame {
// Variable
	String identifiant = "Kenny";
	String MDP = "12345";
// Varible de la fênètre;
	private JPanel contentPane;
	private JTextField txtIdentifiant;
	private JLabel lblMDP;
	private JLabel lblIdentifiant;
	private JButton btnOK;
	private JButton btnCancel;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					FenAuthentification frame = new FenAuthentification();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FenAuthentification() {
		initialize();
	}

	private void initialize() {
		setTitle("Authentification");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 679, 311);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 3, 0, 0));

		lblIdentifiant = new JLabel("Identifiant");
		lblIdentifiant.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblIdentifiant);

		txtIdentifiant = new JTextField();
		contentPane.add(txtIdentifiant);
		txtIdentifiant.setColumns(10);

		lblMDP = new JLabel("Mot de passe");
		lblMDP.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblMDP);

		btnOK = new JButton("OK");
		btnOK.addActionListener(new BtnOKActionListener());

		passwordField = new JPasswordField();
		contentPane.add(passwordField);
		btnOK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnOK);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new BtnCancelActionListener());
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnCancel);

		//

	}

	private class BtnOKActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			FenGestionClient gestionClient = null;

			if (!(txtIdentifiant.getText().equals(identifiant))
					&& !(String.valueOf(passwordField.getPassword()).equals(MDP))) {
				JOptionPane.showMessageDialog(null,
						"L identifiant ou le mot de passe est incorrect" + String.valueOf(passwordField.getPassword()));
			} else {

				gestionClient = new FenGestionClient();

				gestionClient.setVisible(true);
				dispose();

			}
		}
	}

	private class BtnCancelActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			txtIdentifiant.setText("");
			passwordField.setText("");
		}

	}
}
