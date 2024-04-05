/* Ficher Operation.java
 * Auteur: Kenny Rigaud
 * Date de création : 25 oct. 2022
 */
package ca.qc.collegeahuntsic.projet2.classes;

import java.time.LocalDate;

/**
 * TODO Auteur : Kenny Jones, Rigaud
 */
public class Operation {

	private Type typeOpe;
	private LocalDate dateOpe;
	private double montant;

	/**
	 *
	 */
	public Operation() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param typeOpe
	 * @param dateOpe
	 * @param montant
	 */
	public Operation(Type typeOpe, LocalDate dateOpe, double montant) {
		super();
		setTypeOpe(typeOpe);
		setDateOpe(dateOpe);
		setMontant(montant);
	}

	@Override
	public String toString() {
		return "Type operation : " + typeOpe + ", Date operatoire : " + dateOpe + ", Montant : " + montant;
	}

	/**
	 * @return the typeOpe
	 */
	public Type getTypeOpe() {
		return typeOpe;
	}

	/**
	 * @param typeOpe the typeOpe to set
	 */
	public void setTypeOpe(Type typeOpe) {
		this.typeOpe = typeOpe;
	}

	/**
	 * @return the dateOpe
	 */
	public LocalDate getDateOpe() {
		return dateOpe;
	}

	/**
	 * @param dateOpe the dateOpe to set
	 */
	public void setDateOpe(LocalDate dateOpe) {
		this.dateOpe = dateOpe;
	}

	/**
	 * @return the montant
	 */
	public double getMontant() {
		return montant;
	}

	/**
	 * @param montant the montant to set
	 */
	public void setMontant(double montant) {
		this.montant = montant;
	}

}
