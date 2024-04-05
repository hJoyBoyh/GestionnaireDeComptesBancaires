/* Ficher Transaction.java
 * Auteur: Kenny Rigaud
 * Date de création : 25 oct. 2022
 */
package ca.qc.collegeahuntsic.projet2.classes;

import java.time.LocalDate;

/**
 * TODO Auteur : Kenny Jones, Rigaud
 */
public interface Transaction {

	/**
	 * Méthode permettant d effectuer un retrait
	 *
	 * @param montant Double
	 * @param date    LocalDate
	 * @throws Exception
	 */
	void retrait(double montant, LocalDate date) throws Exception;

	/**
	 * Méthode permettant d'effectuer un dépot
	 *
	 * @param montant Double
	 * @param date    Local Date
	 * @throws Exception
	 */
	void depot(double montant, LocalDate date) throws Exception;

	/**
	 * Méthode permettant d'effectuer un virement vers une autre compte bancaire
	 *
	 * @param compteBancaire CompteBancaire
	 * @param montant        Double
	 * @param date           LocalDate
	 * @throws Exception
	 */
	void virement(CompteBancaire compteBancaire, double montant, LocalDate date) throws Exception;

}
