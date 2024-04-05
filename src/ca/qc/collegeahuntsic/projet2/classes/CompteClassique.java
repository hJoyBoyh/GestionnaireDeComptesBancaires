/* Ficher CompteClassique.java
 * Auteur: Kenny Rigaud
 * Date de création : 25 oct. 2022
 */
package ca.qc.collegeahuntsic.projet2.classes;

import java.time.LocalDate;

/**
 * TODO Auteur : Kenny Jones, Rigaud
 */
public class CompteClassique extends CompteBancaire {
	private double limiteRetrait = 1000.00;
	private double decouvertMax = -500;
	private double decouvert = 0;

	/**
	 *
	 */
	public CompteClassique() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param solde
	 * @param limite
	 * @param dateOuverture
	 */
	public CompteClassique(double solde, LocalDate dateOuverture, int numSuccrusale) {
		super(solde, dateOuverture, numSuccrusale);
		decouverteMethode();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return super.toString() + " decouvert : " + getDecouvert();
	}

	private void decouverteMethode() {
		if (solde < 0) {
			decouvert = solde * -1;
		} else {
			decouvert = 0;
		}
	}

	@Override
	public void retrait(double montant, LocalDate date) throws Exception {

		if (montant <= limiteRetrait && (solde - montant >= decouvertMax)) {
			solde = solde - montant;
			listeOperation.add(new Operation(Type.RETRAIT, date, montant));
			decouverteMethode();

		}

		else if (solde - montant < decouvertMax)

		{
			decouverteMethode();
			throw new Exception("le retrait depasse le decouvert maximal qui est de : " + getDecouvertMax());
		}
	}

	@Override
	public void depot(double montant, LocalDate date) throws Exception {
		if (montant <= limiteDepot) {

			solde = solde + montant;
			listeOperation.add(new Operation(Type.DEPOT, date, montant));
			decouverteMethode();

		} else {
			throw new Exception("le depot depasse la limite de depot qui est de :" + getLimiteDepot());
		}

	}

	@Override
	public void virement(CompteBancaire compteBancaire, double montant, LocalDate date) throws Exception {

		if (this.equals(compteBancaire)) {
			throw new Exception("Vous ne pouvez pas effectuer un virement sur le meme compte");
		}
		if (montant < 0) {
			throw new Exception("Veuiller inserer un montant superieur a 0");
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

	/**
	 * @return the limiteRetrait
	 */
	@Override
	public double getLimiteRetrait() {
		return limiteRetrait;
	}

	/**
	 * @param limiteRetrait the limiteRetrait to set
	 */
	@Override
	public void setLimiteRetrait(double limiteRetrait) {
		this.limiteRetrait = limiteRetrait;
	}

	/**
	 * @return the decouvertMax
	 */
	public double getDecouvertMax() {
		return decouvertMax;
	}

	/**
	 * @return the decouvert
	 */
	public double getDecouvert() {
		return decouvert;
	}

	/**
	 * @param decouvert the decouvert to set
	 */
	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}

	/**
	 * @param decouvertMax the decouvertMax to set
	 */
	public void setDecouvertMax(double decouvertMax) {
		this.decouvertMax = decouvertMax;
	}

	@Override
	public int compareTo(CompteBancaire o) {
		int result = this.compareTo(o);
		return result;
	}
}
