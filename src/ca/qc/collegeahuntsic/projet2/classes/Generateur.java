/* Ficher Generateur.java
 * Auteur: Kenny Rigaud
 * Date de création : 25 oct. 2022
 */
package ca.qc.collegeahuntsic.projet2.classes;

import java.util.Random;

/**
 * TODO Auteur : Kenny Jones, Rigaud
 */
public class Generateur {

	public static String genererCode(int longueur, int numSeq, int succursale) {
		if (longueur <= 0) {
			longueur = 1;
		}

		String returnCode = ""; // String qui contiendra le code final
		returnCode += genererPrefixe(longueur);
		returnCode += String.format("%04d-", numSeq);
		returnCode += String.format("%02d", succursale);

		return returnCode;
	}

	static String genererPrefixe(int longueur) {
		Random rand = new Random();
		switch (longueur) {
		case 2:
			return String.format("%02d-", rand.nextInt(100 - 1) + 1);
		case 3:
			return String.format("%03d-", rand.nextInt(1000 - 1) + 1);
		case 4:
			return String.format("%04d-", rand.nextInt(10000 - 1) + 1);
		case 5:
			return String.format("%05d-", rand.nextInt(10000 - 1) + 1);
		default:
			throw new IllegalArgumentException("longueur trop grande");
		}
	}

	public static void main(String[] args) {
		String numero = Generateur.genererCode(3, 15, 25);
		System.out.println(numero);
	}
}
