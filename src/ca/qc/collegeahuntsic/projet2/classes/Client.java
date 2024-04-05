/* Ficher Client.java
 * Auteur: Kenny Rigaud
 * Date de création : 25 oct. 2022
 */
package ca.qc.collegeahuntsic.projet2.classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

/**
 * TODO Auteur : Kenny Jones, Rigaud
 */
public class Client implements Comparable<Client> {
	private String nom;
	private String prenom;
	private ArrayList<CompteBancaire> listeCompteBancaire = new ArrayList<>();

	/**
	 *
	 */
	public Client() {
		// TODO Auto-generated constructor stub
	}

	public Client(String nom, String prenom) {
		setNom(nom);
		setPrenom(prenom);
	}

	/**
	 * Méthode permettant de d'ajouter un compte bancaire dans la liste des comptes
	 * bancaire du client
	 *
	 * @param compte
	 */
	public void ajouterCompteBancaire(CompteBancaire compte) {
		if (listeCompteBancaire.size() == 10) {
			throw new IndexOutOfBoundsException("Impossible de rajouter un autre compte bancaire ,car" + this.toString()
					+ "Possede deja 10 compte bancaire");
		}
		listeCompteBancaire.add(compte);
	}

	/**
	 * Méthode permettant de retirer un compte bancaire dans la liste des comptes
	 * bancaire du client
	 *
	 * @param numeroDeCompte String
	 */
	public void fermerCompteBancaire(String numeroDeCompte) {
		int index = -1;

		// ne marche pas
		for (CompteBancaire cb : listeCompteBancaire) {
			if (cb.getNumeroDeCompte() == (numeroDeCompte)) {

				index = listeCompteBancaire.indexOf(cb);

			}

		}
		if (index == -1) {
			throw new IllegalArgumentException("Fermeture du compte impossible ,car elle n existe pas");
		}
		listeCompteBancaire.remove(index);

	}

	/**
	 * Méthode permettant d'effectuer un dépot dans le compte bancaire du client si
	 * nous connaisson son numéro de compte
	 *
	 * @param numeroDeCompte Srtring
	 * @param montant        Double
	 * @param date           LocalDate
	 */
	public void deposer(String numeroDeCompte, double montant, LocalDate date) {
		for (CompteBancaire cb : listeCompteBancaire) {
			if (cb.getNumeroDeCompte() == numeroDeCompte) {
				if (cb instanceof CompteClassique) {

					try {
						CompteClassique cc = (CompteClassique) cb;
						cc.depot(montant, date);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (cb instanceof CompteEpargne) {
					try {

						CompteEpargne cc = (CompteEpargne) cb;

						cc.depot(montant, date);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

	}

	/**
	 * Méthode permettant d'effectuer un retrait dans le compte bancaire du client
	 * si nous connaisson son numéro de compte
	 *
	 * @param numeroDeCompte String
	 * @param montant        Double
	 * @param date           LocalDate
	 */
	public void retirer(String numeroDeCompte, double montant, LocalDate date) {
		for (CompteBancaire cb : listeCompteBancaire) {
			if (cb.getNumeroDeCompte() == numeroDeCompte) {
				if (cb instanceof CompteClassique) {

					try {
						CompteClassique cc = (CompteClassique) cb;
						cc.retrait(montant, date);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (cb instanceof CompteEpargne) {

					try {
						CompteEpargne cc = (CompteEpargne) cb;
						cc.retrait(montant, date);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

	}

	/**
	 * Méthode permettant de consulter tous les compte bancaire du client
	 *
	 * @return les comptes bancaire de la listeCompteBancaire en String
	 */

	public String consultationComptes() {
		StringBuffer msg = new StringBuffer("Etat des comptes :" + "\n");

		for (CompteBancaire cb : listeCompteBancaire) {
			msg.append("\n" + cb.consultation());
		}

		return msg.toString();
	}

	/**
	 * Méthode permettant de consulter tout les compte épargne du client
	 *
	 * @return les comptes épargne de la listeCompteBancaire en String
	 */
	public String consultationComptesEpargne() {
		StringBuffer msg = new StringBuffer("Etat des comptes :" + "\n");

		CompteEpargne ce = null;
		for (CompteBancaire cb : listeCompteBancaire) {
			if (cb instanceof CompteEpargne)
				// ce = (CompteEpargne) cb;
				msg.append("\n" + cb.consultation());
		}

		return msg.toString();

	}

	/**
	 * Méthode permettant d'obtenir le solde total de tous les comptes du client
	 *
	 * @return le solde total de tous les comptes du client en String
	 */
	public String soldeDeToutLesComptes() {
		double total = 0.0;
		StringBuffer msg = new StringBuffer("Solde de tous les comptes : ");

		for (CompteBancaire cb : listeCompteBancaire) {
			total += cb.getSolde();
		}
		msg.append(total);
		return msg.toString();
	}

	/**
	 * Méthode permettant d'obtenir le nombre de comptes bancaire du client
	 *
	 * @return le nombre de comptes bancaire du client en int
	 */
	public int nombreComptes() {
		return listeCompteBancaire.size();
	}

	@Override
	public String toString() {
		StringBuffer msg = new StringBuffer("Liste compte" + "\n");
		for (CompteBancaire cb : listeCompteBancaire) {
			msg.append(cb.toString() + "\n");
		}
		return getClass().getSimpleName() + " " + nom + ", " + prenom + "\n" + msg.toString();
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
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the listeCompteBancaire
	 */
	public ArrayList<CompteBancaire> getListeCompteBancaire() {
		return listeCompteBancaire;
	}

	/**
	 * @param listeCompteBancaire the listeCompteBancaire to set
	 */
	public void setListeCompteBancaire(ArrayList<CompteBancaire> listeCompteBancaire) {
		this.listeCompteBancaire = listeCompteBancaire;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nom, prenom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(nom, other.nom) && Objects.equals(prenom, other.prenom);
	}

	@Override
	public int compareTo(Client autre) {
		// TODO Auto-generated method stub

		return this.compareTo(autre);
	}

}
