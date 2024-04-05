/* Ficher CompteEpargne.java
 * Auteur: Kenny Rigaud
 * Date de création : 25 oct. 2022
 */
package ca.qc.collegeahuntsic.projet2.classes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * TODO Auteur : Kenny Jones, Rigaud
 */
public class CompteEpargne extends CompteBancaire {
	private double tauxInteret = 0.5;
	private double montantInteret = 0;

	/**
	 *
	 */
	public CompteEpargne() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param solde
	 * @param limite
	 * @param dateOuverture
	 */
	public CompteEpargne(double solde, LocalDate dateOuverture, int numSuccrusale) {
		super(solde, dateOuverture, numSuccrusale);

	}

	public CompteEpargne(double solde, LocalDate dateOuverture, int numSuccrusale, double taux) {
		super(solde, dateOuverture, numSuccrusale);
		setTauxInteret(taux);
	}

	@Override
	public String toString() {
		return super.toString() + ", Taux : " + tauxInteret + "%, Interets : " + String.format("%.2f$", montantInteret);
	}

	@Override
	public void retrait(double montant, LocalDate date) throws Exception {
		throw new Exception("Retrait impossible ,car c est un compte epargne");

	}

	// a finir mettre le taux
	@Override
	public void depot(double montant, LocalDate date) throws Exception {
		if (montant > limiteDepot) {
			throw new Exception("Limite de depot depasser");
		} else {

			int nbrJours = (int) ChronoUnit.DAYS.between(date, LocalDate.now());

			listeOperation.add(new Operation(Type.DEPOT, date, montant));
			montantInteret = 0.0;
			for (Operation op : listeOperation) {
				montantInteret += (op.getMontant() * nbrJours * tauxInteret) / 100 / 365;
			}

			solde = solde + montant;

		}
	}

	@Override
	public void virement(CompteBancaire compteBancaire, double montant, LocalDate date) throws Exception {
		if (this.equals(compteBancaire)) {
			throw new Exception("Vous ne pouvez pas effectuer un virement sur le meme compte");
		}

		if (compteBancaire instanceof CompteClassique) {
			try {
				this.retrait(montant, date);
				CompteClassique Cc = (CompteClassique) compteBancaire;

				Cc.depot(montant, date);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new Exception(e.getMessage());
			}
		} else if (compteBancaire instanceof CompteEpargne) {
			try {
				this.retrait(montant, date);
				CompteEpargne Ce = (CompteEpargne) compteBancaire;
				Ce.depot(montant, date);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new Exception(e.getMessage());
			}

		}

	}

	public void setTauxInteret(double tauxInteret) {
		this.tauxInteret = tauxInteret;
	}

	/**
	 * @return the tauxInteret
	 */
	public double getTauxInteret() {
		return tauxInteret;
	}

	/**
	 * @return the montantInteret
	 */
	public double getMontantInteret() {
		return montantInteret;
	}

	@Override
	public int compareTo(CompteBancaire o) {
		int result = this.compareTo(o);
		return result;
	}

}
