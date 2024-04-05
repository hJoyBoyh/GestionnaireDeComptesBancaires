/* Ficher Banque.java
 * Auteur: Kenny Rigaud
 * Date de création : 25 oct. 2022
 */
package ca.qc.collegeahuntsic.projet2.classes;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * TODO Auteur : Kenny Jones, Rigaud
 */
public class Banque {
	private String nom;
	private int numSuccursale;
	private ListeClient listeClient;

	/**
	 *
	 */
	public Banque() {
		// TODO Auto-generated constructor stub
	}

	public Banque(String nom, int numSuccursale) {
		setNom(nom);
		setNumSuccursale(numSuccursale);
		listeClient = new ListeClient();
	}

	@Override
	public String toString() {
		StringBuffer msg = new StringBuffer("\n" + "Liste de clients de la banque: " + getNom() + ": ");

		for (Client client : listeClient) {
			msg.append("\n" + "Client: " + client.getNom() + " " + client.getPrenom());
		}
		return getClass().getSimpleName() + ": " + nom + ", NumeroSuccursale : " + numSuccursale + msg.toString();
	}

	/**
	 * Permet d'ajouter un client à la Banque
	 *
	 * @param client Client
	 * @throws Exception
	 */
	public void ajouterClient(Client client) throws Exception {

		listeClient.add(client);

	}

	/**
	 * Methode permettant d'obtenir le nombre de client inscrit a la banque
	 *
	 * @return le nombres de Client de la liste Client en int
	 */
	public int nombreClient() {
		return listeClient.size();
	}

	/**
	 * Methode permettant d'ouvrir un compte bancaire classique d'un client de la
	 * banque étant donné en paramètre
	 *
	 * @param client Client
	 * @param solde  Double
	 * @param date   LocalDate
	 * @throws Exception
	 */
	public void ouvrirCompteClassique(Client client, double solde, LocalDate date) throws Exception {
		if (listeClient.contains(client)) {
			client.ajouterCompteBancaire(new CompteClassique(solde, date, getNumSuccursale()));
		} else {

			throw new Exception("Ce client n est pas une client de la banque : " + getNom());
		}

	}

	/**
	 * Methode permettant d'ouvrir un compte bancaire épargne d'un client de la
	 * banque étant donné en parametre
	 *
	 * @param client Client
	 * @param solde  Double
	 * @param date   LocalDate
	 */
	public void ouvrirCompteEpargne(Client client, double solde, LocalDate date) {
		client.ajouterCompteBancaire(new CompteEpargne(solde, date, getNumSuccursale()));

	}

	/**
	 *
	 * Methode permettant d'ouvrir un compte bancaire épargne d'un client de la
	 * banque étant donné en parametre en plus d'un taux d'intéret
	 *
	 * @param client Client
	 * @param solde  Double
	 * @param date   LocalDate
	 * @param taux   Double
	 */
	public void ouvrirCompteEpargne(Client client, double solde, LocalDate date, double taux) {
		client.ajouterCompteBancaire(new CompteEpargne(solde, date, getNumSuccursale(), taux));

	}

	/**
	 * Methode permettant de fermer un compte d'un client de la banque si l'on
	 * connait son numéro de compte
	 *
	 * @param client         Client
	 * @param numeroDeCompte String
	 * @throws Exception
	 */
	public void fermerCompte(Client client, String numeroDeCompte) throws Exception {

		if (listeClient.contains(client)) {

			client.fermerCompteBancaire(numeroDeCompte);

		} else {
			throw new Exception("CLIENT EXISTE PAS");
		}
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the numSuccursale
	 */
	public int getNumSuccursale() {
		return numSuccursale;
	}

	/**
	 * @param numSuccursale the numSuccursale to set
	 */
	public void setNumSuccursale(int numSuccursale) {
		this.numSuccursale = numSuccursale;
	}

	/**
	 * @return the listeClient
	 */
	public ArrayList<Client> getListeClient() {
		return listeClient;
	}

	public void setListeClient(ListeClient listeClient) {
		this.listeClient = listeClient;
	}

}
