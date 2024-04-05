/* Ficher CompteBancaire.java
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
public abstract class CompteBancaire implements Transaction, Comparable<CompteBancaire> {
	protected String numeroDeCompte;
	protected double solde;
	protected double limiteDepot = 10000.00;
	protected LocalDate dateOuverture;
	protected ArrayList<Operation> listeOperation = new ArrayList<>();

	/**
	 *
	 */
	public CompteBancaire() {
		// TODO Auto-generated constructor stub

	}

	/**
	 * @param solde
	 * @param limite
	 * @param dateOuverture
	 */
	public CompteBancaire(double solde, LocalDate dateOuverture, int numSuccrusale) {

		numeroDeCompte = Generateur.genererCode(3, numSuccrusale, numSuccrusale);
		setSolde(solde);

		setDateOuverture(dateOuverture);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + ": Numero de Compte : " + numeroDeCompte + " Solde : "
				+ String.format("%.2f$", solde) + "  Limite : " + String.format("%.2f$", limiteDepot)
				+ "  Date Ouverture : " + dateOuverture;
	}

	/**
	 * Méthode qui permet de consulter tout les opérations effectuer dans le comptes
	 *
	 * @return tout les opérations effectuer dans le comptes en String
	 */
	public String consultation() {
		StringBuffer msg = new StringBuffer("Etat de compte :" + toString() + "\n");

		for (Operation op : listeOperation) {
			msg = msg.append(op.toString() + "\n");
		}

		return msg.toString();

	}

	/**
	 * @return the numeroDeCompte
	 */
	public String getNumeroDeCompte() {
		return numeroDeCompte;
	}

	/**
	 * @param numeroDeCompte the numeroDeCompte to set
	 */
	public void setNumeroDeCompte(String numeroDeCompte) {
		this.numeroDeCompte = numeroDeCompte;
	}

	/**
	 * @return the limiteRetrait
	 */
	public double getLimiteRetrait() {
		return limiteDepot;
	}

	/**
	 * @param limiteRetrait the limiteRetrait to set
	 */
	public void setLimiteRetrait(double limiteRetrait) {
		this.limiteDepot = limiteRetrait;
	}

	/**
	 * @return the solde
	 */
	public double getSolde() {
		return solde;
	}

	/**
	 * @param solde the solde to set
	 */
	public void setSolde(double solde) {
		this.solde = solde;
	}

	/**
	 * @return the limite
	 */
	public double getLimiteDepot() {
		return limiteDepot;
	}

	/**
	 * @param limite the limite to set
	 */
	public void setLimiteDepot(double limite) {
		this.limiteDepot = limite;
	}

	/**
	 * @return the dateOuverture
	 */
	public LocalDate getDateOuverture() {
		return dateOuverture;
	}

	/**
	 * @param dateOuverture the dateOuverture to set
	 */
	public void setDateOuverture(LocalDate dateOuverture) {
		this.dateOuverture = dateOuverture;
	}

	/**
	 * @return the listeOperation
	 */
	public ArrayList<Operation> getListeOperation() {
		return listeOperation;
	}

	/**
	 * @param listeOperation the listeOperation to set
	 */
	public void setListeOperation(ArrayList<Operation> listeOperation) {
		this.listeOperation = listeOperation;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numeroDeCompte);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompteBancaire other = (CompteBancaire) obj;
		return Objects.equals(numeroDeCompte, other.numeroDeCompte);
	}

}
