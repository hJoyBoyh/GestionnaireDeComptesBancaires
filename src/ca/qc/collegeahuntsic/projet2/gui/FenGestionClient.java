/* Ficher FenGestionClient.java
 * Auteur: Kenny Rigaud
 * Date de création : 31 oct. 2022
 */
package ca.qc.collegeahuntsic.projet2.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ca.qc.collegeahuntsic.projet2.classes.Banque;
import ca.qc.collegeahuntsic.projet2.classes.Client;
import ca.qc.collegeahuntsic.projet2.classes.CompteBancaire;
import ca.qc.collegeahuntsic.projet2.classes.CompteClassique;
import ca.qc.collegeahuntsic.projet2.classes.CompteEpargne;

/**
 * TODO Auteur : Kenny Jones, Rigaud
 */
public class FenGestionClient extends JFrame {

	FenTransation transaction;
//Banque et etc variable

	private Banque banqueScotia = new Banque("Banque Scotia", 25);
	private Client client1 = new Client("Kenny", "Rigaud");
	private Client client2 = new Client("Jones", "Cormic");
	private Client client3 = new Client("Allen", "Joseph");

	private Client clientTemp;
	private int indexListeClient;
	private int indexListeCompte;
	private int indexComboTaux;
	private int indexCompteDestinataire;
	private double taux;

	private String texte;
	private double[] tauxTableau = { 0.5, 1.0, 1.5, 2, 2.5, 3.0 };
// jframe variables
	private JPanel contentPane;
	private JPanel panelClient;
	private JPanel panelInfo;
	private JPanel panelGestion;
	private JPanel panelnfoNomPrenom;
	private JButton btnNouveauClient;
	private JButton btnRelever;
	private JPanel panelListeClient;
	private JTextArea textAreaAffichage;
	private JPanel panelGestionClientAvecBouton;
	private JButton btnOuvrirCompte;
	private JButton btnTransaction;
	private JButton btnVirement;
	private JButton btnFermerCompte;
	private JComboBox comboBoxTauxRendement;
	private JRadioButton rdbtnEpargne;
	private JRadioButton rdbtnClassique;
	private JLabel lblTauxRendement;
	private JLabel lblMontant;
	private JLabel lblNewLabel_2;
	private JTextField txtPrenom;
	private JTextField txtNom;
	private JLabel lblNom;
	private JLabel lblPrenom;
	private JMenuBar menuBar;
	public JMenu mnFicher;
	private Button buttonSauvegarder;
	private Button buttonChargement;

	// VARIABLE COMBO ET LIST ET SCROLLPANE
	private DefaultComboBoxModel<Double> comboModeleTaux = new DefaultComboBoxModel<>();
	private DefaultListModel<String> modeleClient = new DefaultListModel<>();
	private DefaultListModel<CompteBancaire> modeleListeCompte = new DefaultListModel<>();
	private DefaultListModel<CompteBancaire> modeleCompteDestinataire = new DefaultListModel<>();
	private JScrollPane scrollPane;
	private JList listClient;
	private JScrollPane scrollPane_1;
	private JList listComptesClient;
	private JButton btnConfirmer;
	private JScrollPane scrollPane_2;
	private JList listcompteDestinataires;
	private JTextField txtMontant;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					FenGestionClient frame = new FenGestionClient();
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
	public FenGestionClient() {
		setTitle(banqueScotia.getNom());

		initialize();
		chargementCombo();

		chargementListeClient();

	}

	private void initialize() {
		try {
			client1.ajouterCompteBancaire(new CompteClassique(2000, LocalDate.now(), banqueScotia.getNumSuccursale()));
			if (client1.getListeCompteBancaire().get(0) instanceof CompteClassique) {
				CompteClassique cc = (CompteClassique) client1.getListeCompteBancaire().get(0);
				cc.depot(300, LocalDate.of(2003, 2, 2));
			}

			client2.ajouterCompteBancaire(new CompteClassique(3000, LocalDate.now(), banqueScotia.getNumSuccursale()));
			client2.ajouterCompteBancaire(new CompteEpargne(6000, LocalDate.now(), banqueScotia.getNumSuccursale()));
			client2.getListeCompteBancaire().get(0).retrait(300, LocalDate.now());
			client3.ajouterCompteBancaire(new CompteClassique(8000, LocalDate.now(), banqueScotia.getNumSuccursale()));

			banqueScotia.ajouterClient(client1);
			banqueScotia.ajouterClient(client2);
			banqueScotia.ajouterClient(client3);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 984, 648);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnFicher = new JMenu("Ficher");
		menuBar.add(mnFicher);

		buttonSauvegarder = new Button("Sauvegarder");
		buttonSauvegarder.addActionListener(new ButtonSauvegarderActionListener());
		mnFicher.add(buttonSauvegarder);

		buttonChargement = new Button("Chargement");
		buttonChargement.addActionListener(new ButtonChargementActionListener());
		mnFicher.add(buttonChargement);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		BoxLayout lay = new BoxLayout(panelClient, BoxLayout.X_AXIS);

		panelClient = new JPanel();
		contentPane.add(panelClient, BorderLayout.NORTH);
		panelClient.setLayout(new GridLayout(0, 2, 10, 10));

		panelInfo = new JPanel();
		panelClient.add(panelInfo);
		panelInfo.setLayout(new BorderLayout(0, 20));

		panelnfoNomPrenom = new JPanel();
		TitledBorder infoClientTitre = BorderFactory.createTitledBorder("Information  du client");
		panelnfoNomPrenom.setBorder(infoClientTitre);
		panelInfo.add(panelnfoNomPrenom, BorderLayout.CENTER);
		panelnfoNomPrenom.setLayout(new GridLayout(3, 2, 0, 0));

		lblNom = new JLabel("Nom");
		panelnfoNomPrenom.add(lblNom);

		txtNom = new JTextField();
		panelnfoNomPrenom.add(txtNom);
		txtNom.setColumns(10);

		lblPrenom = new JLabel("Prenom");
		panelnfoNomPrenom.add(lblPrenom);

		txtPrenom = new JTextField();
		panelnfoNomPrenom.add(txtPrenom);
		txtPrenom.setColumns(10);

		btnNouveauClient = new JButton("Nouveau Client");
		btnNouveauClient.addActionListener(new BtnNouveauClientActionListener());
		panelnfoNomPrenom.add(btnNouveauClient);

		btnRelever = new JButton("Releve Client");
		btnRelever.addActionListener(new BtnReleverActionListener());
		panelnfoNomPrenom.add(btnRelever);
		TitledBorder clientTitre;
		clientTitre = BorderFactory.createTitledBorder("Clients");

		scrollPane = new JScrollPane();
		panelInfo.add(scrollPane, BorderLayout.SOUTH);

		listClient = new JList(modeleClient);
		listClient.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listClient.addListSelectionListener(new ListClientListSelectionListener());
		TitledBorder clientTitre1;
		// listClient.setPreferredSize(new Dimension(0, 40));
		clientTitre1 = BorderFactory.createTitledBorder("Clients");
		scrollPane.setBorder(clientTitre1);
		scrollPane.setViewportView(listClient);
		scrollPane.setPreferredSize(new Dimension(0, 40));

		panelGestion = new JPanel();
		panelClient.add(panelGestion);
		panelGestion.setLayout(new BorderLayout(0, 0));

		panelGestionClientAvecBouton = new JPanel();
		TitledBorder GestionClientTitre = BorderFactory.createTitledBorder("Gestion Client");
		panelGestionClientAvecBouton.setBorder(GestionClientTitre);
		panelGestion.add(panelGestionClientAvecBouton, BorderLayout.CENTER);
		panelGestionClientAvecBouton.setLayout(new GridLayout(7, 2, 0, 0));

		btnOuvrirCompte = new JButton("Ouvrir Compte");
		btnOuvrirCompte.addActionListener(new BtnOuvrirCompteActionListener());
		panelGestionClientAvecBouton.add(btnOuvrirCompte);

		rdbtnClassique = new JRadioButton("Classique");
		panelGestionClientAvecBouton.add(rdbtnClassique);

		lblTauxRendement = new JLabel("Taux de rendement:");
		panelGestionClientAvecBouton.add(lblTauxRendement);

		rdbtnEpargne = new JRadioButton("Epargne");
		panelGestionClientAvecBouton.add(rdbtnEpargne);

		comboBoxTauxRendement = new JComboBox(comboModeleTaux);
		panelGestionClientAvecBouton.add(comboBoxTauxRendement);

		lblMontant = new JLabel("Montant");
		panelGestionClientAvecBouton.add(lblMontant);

		btnFermerCompte = new JButton("Fermer Compte");
		btnFermerCompte.addActionListener(new BtnFermerCompteActionListener());
		panelGestionClientAvecBouton.add(btnFermerCompte);

		txtMontant = new JTextField();
		panelGestionClientAvecBouton.add(txtMontant);
		txtMontant.setColumns(10);

		btnTransaction = new JButton("Transaction");
		btnTransaction.addActionListener(new BtnTransactionActionListener());
		panelGestionClientAvecBouton.add(btnTransaction);

		lblNewLabel_2 = new JLabel("");
		panelGestionClientAvecBouton.add(lblNewLabel_2);

		btnVirement = new JButton("Virement");
		btnVirement.addActionListener(new BtnVirementActionListener());
		panelGestionClientAvecBouton.add(btnVirement);

		btnConfirmer = new JButton("Confirmer");
		btnConfirmer.addActionListener(new BtnConfirmerActionListener());
		btnConfirmer.setEnabled(false);
		panelGestionClientAvecBouton.add(btnConfirmer);

		panelListeClient = new JPanel();
		contentPane.add(panelListeClient, BorderLayout.CENTER);
		panelListeClient.setLayout(new GridLayout(3, 0, 0, 10));
		TitledBorder ListeCompteTitre;
		ListeCompteTitre = BorderFactory.createTitledBorder("Liste des comptes du client");

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(ListeCompteTitre);
		panelListeClient.add(scrollPane_1);

		listComptesClient = new JList(modeleListeCompte);
		listComptesClient.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(listComptesClient);

		textAreaAffichage = new JTextArea();
		TitledBorder affichageTitre;
		affichageTitre = BorderFactory.createTitledBorder("Affichage");

		scrollPane_2 = new JScrollPane();
		panelListeClient.add(scrollPane_2);

		listcompteDestinataires = new JList(modeleCompteDestinataire);
		listcompteDestinataires.setEnabled(false);
		listcompteDestinataires.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listcompteDestinataires.setBackground(Color.LIGHT_GRAY);
		scrollPane_2.setViewportView(listcompteDestinataires);
		textAreaAffichage.setBorder(affichageTitre);
		panelListeClient.add(textAreaAffichage);
	}

// PERMET DE RÉINIALISER L'ÉTAT D'ORIGINE DES COMPTES DESTINARAIRE
	private void etatInitialeCompteDestinataire() {
		listcompteDestinataires.setBackground(Color.LIGHT_GRAY);
		listcompteDestinataires.setEnabled(false);
		modeleCompteDestinataire.removeAllElements();
	}

// permet de attribuer de savoir les index des jlist et des comboBox
	private void chargementIndex() {
		indexListeClient = listClient.getSelectedIndex();
		indexComboTaux = comboBoxTauxRendement.getSelectedIndex();
		indexListeCompte = listComptesClient.getSelectedIndex();

		if (listcompteDestinataires.isEnabled() == true) {
			indexCompteDestinataire = listcompteDestinataires.getSelectedIndex();
		}
	}

// permet de charger les taux dans la comboBox
	private void chargementCombo() {
		for (double taux : tauxTableau) {
			comboModeleTaux.addElement(taux);
		}
	}

// permet de charger la les clients de la banque dans un jlist
	public void chargementListeClient() {
		modeleClient.removeAllElements();

		for (Client c : banqueScotia.getListeClient()) {
			modeleClient.addElement(c.getNom() + " " + c.getPrenom());
		}

	}

// permet de charger les comptes bancaire du client sélectionner
	public void chargementCompte() {
		indexListeClient = listClient.getSelectedIndex();
		modeleListeCompte.removeAllElements();

		for (CompteBancaire c : banqueScotia.getListeClient().get(indexListeClient).getListeCompteBancaire()) {
			modeleListeCompte.addElement(c);
		}

	}

// permet de charger dans une jlist les compte destinataire pour les virements
	private void chargementCompteDestinataire() {
		indexListeClient = listClient.getSelectedIndex();
		modeleCompteDestinataire.removeAllElements();

		for (CompteBancaire c : banqueScotia.getListeClient().get(indexListeClient).getListeCompteBancaire()) {
			modeleCompteDestinataire.addElement(c);
		}

	}

// permet de placer du texte dans le textArea
	public void chargementTexte(String text) {
		texte = "";
		texte = text;
		textAreaAffichage.setText(texte);
	}

	private class BtnNouveauClientActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {

				String prenom = txtPrenom.getText();
				String nom = txtNom.getText();

// cas ou les txt ne sont pas comforme pour la création d'un nouveau client
				if (prenom.isEmpty() || nom.isEmpty()) {

					textAreaAffichage.setText(" Veuiller inserer un nom complet valide");
				}

// ajout d'une nouveau client
				if (!(prenom.isEmpty()) && !(nom.isEmpty())) {

					textAreaAffichage.setText("");
					banqueScotia.ajouterClient(new Client(nom, prenom));

					chargementListeClient();
					chargementTexte("Nouveau client");
				}

			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}

		}
	}

	private class ListClientListSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {

			chargementIndex();
			if (indexListeClient != -1) {
				chargementCompte();
			}
			chargementTexte("");

		}
	}

	private class BtnReleverActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			chargementIndex();
			if (indexListeClient != -1) {
// montrer le toString du client selectionner
				textAreaAffichage.setText("");
				chargementTexte(banqueScotia.getListeClient().get(indexListeClient).toString());
			}
			if (indexListeClient == -1) {
// cas ou aucun client n'a été sélectionner
				chargementTexte("Selectionner un client");
			}
		}
	}

	private class BtnOuvrirCompteActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			try {
				chargementIndex();

				taux = tauxTableau[indexComboTaux];
				clientTemp = banqueScotia.getListeClient().get(indexListeClient);
// cas ou aucun client est selectionner
				if (indexListeClient == -1) {
					chargementTexte("Selectionner un client");
				}
// cas ou le rdbtn Classique est selectionner cération d'un nouveau compte classique dans le client sélectionner
				if (rdbtnClassique.isSelected() == true && rdbtnEpargne.isSelected() == false) {

					banqueScotia.ouvrirCompteClassique(clientTemp, Double.parseDouble(txtMontant.getText()),
							LocalDate.now());

					chargementCompte();
					chargementTexte("Nouveau compte creer");
				}
// cas ou le rdbtn Epargne est selectionner cération d'un nouveau compte Epargne dans le client sélectionner
				if (rdbtnClassique.isSelected() == false && rdbtnEpargne.isSelected() == true) {

					banqueScotia.ouvrirCompteEpargne(clientTemp, Double.parseDouble(txtMontant.getText()),
							LocalDate.now(), taux);

					chargementCompte();
					chargementTexte("Nouveau compte creer");
				} else {
					chargementTexte("Selectionner un type de compte svp!!");
				}

			} catch (NumberFormatException e1) {
				chargementTexte("Inserer un montant pour le solde valide");
			} catch (IndexOutOfBoundsException e1) {
				chargementTexte("Selectionner un client");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

	private class BtnFermerCompteActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {

				chargementIndex();
// selection du client et instanciation de numero de compte du client selectionner
				clientTemp = banqueScotia.getListeClient().get(indexListeClient);
				String numCompte = clientTemp.getListeCompteBancaire().get(indexListeCompte).getNumeroDeCompte();

// fermeture du compte bancaire selectionner
				banqueScotia.fermerCompte(clientTemp, numCompte);
				chargementCompte();
				chargementTexte("Fermeture du compte reussi!!!");

			} catch (IndexOutOfBoundsException e1) {
				// TODO Auto-generated catch block
				chargementTexte("Selectionner un compte bancaire");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	private class BtnTransactionActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
// nouvelle instance de la Fenetre FenTransaction
				chargementIndex();
				chargementTexte("");

				CompteBancaire compteBancaire = banqueScotia.getListeClient().get(indexListeClient)
						.getListeCompteBancaire().get(indexListeCompte);

				transaction = new FenTransation();
				transaction.chargementCompteBancaire(compteBancaire);
				transaction.setVisible(true);

			} catch (IndexOutOfBoundsException e1) {
				chargementTexte("Selectionner un compte bancaire");
			}
		}
	}

	private class BtnVirementActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
// chargement du nouveau décor pour le jlist CompteDestinaire avec les info utile
				chargementIndex();
				chargementCompteDestinataire();
				chargementTexte("Selectionner deux comptes");

				listcompteDestinataires.setBackground(Color.WHITE);
				listcompteDestinataires.setEnabled(true);
				btnConfirmer.setEnabled(true);

				TitledBorder titreCompteDestinataire = BorderFactory.createTitledBorder("Comptes destinataires");
				scrollPane_2.setBorder(titreCompteDestinataire);

			} catch (IndexOutOfBoundsException e1) {
				chargementTexte("Selectionner un client");
			}
		}
	}

	private class BtnConfirmerActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {

				double montant = Double.parseDouble(txtMontant.getText());
// instanciation du compte qui envoye l'argent et celui qui le recoit
				chargementIndex();
				CompteBancaire compteEnvoyeur = banqueScotia.getListeClient().get(indexListeClient)
						.getListeCompteBancaire().get(indexListeCompte);

				CompteBancaire compteDestinataire = banqueScotia.getListeClient().get(indexListeClient)
						.getListeCompteBancaire().get(indexCompteDestinataire);
// virement d'argent dependant de quel type de compte bancaire que le compte envoyeur est
				if (compteEnvoyeur instanceof CompteClassique) {
					try {
						CompteClassique cc = (CompteClassique) compteEnvoyeur;

						cc.virement(compteDestinataire, montant, LocalDate.now());
						chargementListeClient();
						etatInitialeCompteDestinataire();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						chargementTexte(e1.getMessage());
					}

				}
				if (compteEnvoyeur instanceof CompteEpargne) {

					try {
						CompteEpargne ce = (CompteEpargne) compteEnvoyeur;

						ce.virement(compteDestinataire, montant, LocalDate.now());
						chargementListeClient();
						etatInitialeCompteDestinataire();

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						chargementTexte(e1.getMessage());
					}

				}

			} catch (NumberFormatException e1) {

				chargementTexte("Veuillez inserer un montant valide");
			} catch (IndexOutOfBoundsException e1) {

				chargementTexte("Veuillez selectionner deux compte");
			}
		}

	}

	private class ButtonSauvegarderActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
//			try {
//				Utilitaire.sauvegarder(banqueScotia, "./MesRessources/banque.xml");
//			} catch (FileNotFoundException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
		}
	}

	private class ButtonChargementActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
//			try {
//				banqueScotia = Utilitaire.chargement("./MesRessources/banque.xml");
//				chargementListeClient();
//				chargementCompte();
//			} catch (FileNotFoundException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}

		}
	}
}
