/* Ficher FenTransation.java
 * Auteur: Kenny Rigaud
 * Date de création : 31 oct. 2022
 */
package ca.qc.collegeahuntsic.projet2.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import ca.qc.collegeahuntsic.projet2.classes.CompteBancaire;
import ca.qc.collegeahuntsic.projet2.classes.CompteClassique;
import ca.qc.collegeahuntsic.projet2.classes.CompteEpargne;

/**
 * TODO Auteur : Kenny Jones, Rigaud
 */
public class FenTransation extends FenGestionClient {
	private CompteBancaire compteBancaire;
	private CompteClassique compteClassique;
	private CompteEpargne compteEpargne;

	private String texte;
	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JButton btnConfirmer;
	private JButton btnEtatDuCompte;
	private JButton btnRetour;
	private JLabel lblSolde;
	private JLabel lblMontant;
	private JTextField txtSolde;
	private JTextField txtMontant;
	private JRadioButton rdbtnDepot;
	private JRadioButton rdbtnRetrait;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JScrollPane scrollPane;
	private JTextArea textAreaAffichage2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					FenTransation frame = new FenTransation();
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
	public FenTransation() {
		initialize();
		mnFicher.setVisible(false);

	}

	private void initialize() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 546, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(4, 1, 0, 0));

		lblSolde = new JLabel("Solde");
		panel.add(lblSolde);

		txtSolde = new JTextField();
		txtSolde.setEditable(false);
		panel.add(txtSolde);
		txtSolde.setColumns(10);

		lblMontant = new JLabel("Montant");
		panel.add(lblMontant);

		txtMontant = new JTextField();
		panel.add(txtMontant);
		txtMontant.setColumns(10);

		rdbtnDepot = new JRadioButton("Depot");
		panel.add(rdbtnDepot);

		rdbtnRetrait = new JRadioButton("Retrait");
		panel.add(rdbtnRetrait);

		lblNewLabel_3 = new JLabel("");
		panel.add(lblNewLabel_3);

		lblNewLabel_2 = new JLabel("");
		panel.add(lblNewLabel_2);

		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new GridLayout(1, 0, 20, 0));

		btnConfirmer = new JButton("Confirmer");
		btnConfirmer.addActionListener(new BtnConfirmerActionListener());
		panel_2.add(btnConfirmer);

		btnEtatDuCompte = new JButton("Etat du compte");
		btnEtatDuCompte.addActionListener(new BtnEtatDuCompteActionListener());
		panel_2.add(btnEtatDuCompte);

		btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new BtnRetourActionListener());
		btnRetour.setPreferredSize(new Dimension(50, 50));
		panel_2.add(btnRetour);

		scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);

		textAreaAffichage2 = new JTextArea();
		scrollPane.setViewportView(textAreaAffichage2);
		TitledBorder title;
		title = BorderFactory.createTitledBorder("Affichage");
	}

	public void chargementCompteBancaire(CompteBancaire cB) {
		if (cB instanceof CompteClassique) {
			compteClassique = (CompteClassique) cB;
			setTitle("Compte Classique : " + compteClassique.getNumeroDeCompte());
			txtSolde.setText(String.valueOf(compteClassique.getSolde()));
		}
		if (cB instanceof CompteEpargne) {
			compteEpargne = (CompteEpargne) cB;
			setTitle("Compte Epargne : " + compteEpargne.getNumeroDeCompte());
			txtSolde.setText(String.valueOf(compteEpargne.getSolde()));
		}
	}

	private class BtnConfirmerActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (rdbtnDepot.isSelected() == true && rdbtnRetrait.isSelected() == true
					|| rdbtnDepot.isSelected() == false && rdbtnRetrait.isSelected() == false) {
				textAreaAffichage2.setText("Choisisser un seul type d operation svp!!!");
			}

			try {
				double montant = Double.parseDouble(txtMontant.getText());

// COMPTE CLASSIQUE
				if (compteClassique != null) {
					if (rdbtnDepot.isSelected() == true && rdbtnRetrait.isSelected() == false) {

						try {
							compteClassique.depot(montant, LocalDate.now());
							textAreaAffichage2.setText("Depot accepter :" + montant + "$");
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							textAreaAffichage2.setText(e1.getMessage());
						}

					}

					if (rdbtnDepot.isSelected() == false && rdbtnRetrait.isSelected() == true) {
						try {
							compteClassique.retrait(montant, LocalDate.now());
							textAreaAffichage2.setText("Retrait accepter : " + montant + "$");
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							textAreaAffichage2.setText(e1.getMessage());
						}

					}
				}
// COMPTE EPARGNE
				if (compteEpargne != null) {
					if (rdbtnDepot.isSelected() == true && rdbtnRetrait.isSelected() == false) {

						try {
							compteEpargne.depot(montant, LocalDate.now());
							textAreaAffichage2.setText("Depot accepter : " + montant + "$");

						} catch (Exception e1) {
							// TODO Auto-generated catch block
							textAreaAffichage2.setText(e1.getMessage());
						}

					}

					if (rdbtnDepot.isSelected() == false && rdbtnRetrait.isSelected() == true) {
						try {
							compteEpargne.retrait(montant, LocalDate.now());
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							textAreaAffichage2.setText(e1.getMessage());
						}

					}
				}
			} catch (NumberFormatException e1) {
				// TODO: handle exception
				textAreaAffichage2.setText("Inserer une montant valide!!!");
			} catch (Exception e1) {
				textAreaAffichage2.setText("Inserer une montant valide!!!");
			}

		}
	}

	private class BtnRetourActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
	}

	private class BtnEtatDuCompteActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (compteClassique != null) {
				textAreaAffichage2.setText(compteClassique.consultation());
			}
		}
	}
}
