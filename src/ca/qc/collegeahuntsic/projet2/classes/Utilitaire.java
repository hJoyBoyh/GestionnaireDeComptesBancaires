/* Ficher Utilitaire.java
 * Auteur: Kenny Rigaud
 * Date de création : 31 oct. 2022
 */
package ca.qc.collegeahuntsic.projet2.classes;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * TODO Auteur : Kenny Jones, Rigaud
 */
public class Utilitaire {

	/**
	 * Méthode permettant de sauvegarder les données d'une classe Banque vers un
	 * fichier xml
	 *
	 * @param b
	 * @param path
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void sauvegarder(Banque b, String path) throws FileNotFoundException, IOException {
		try (
				// Ouvre le fichier en ecriture
				FileOutputStream fos = new FileOutputStream(path);
				XMLEncoder encodeur = new XMLEncoder(fos);) {
			// serialiser l'objet(s)
			encodeur.writeObject(b);

		}
	}

	/**
	 * Méthode permettant de charger les données d'une classe Banque à partir d'un
	 * fichier xml
	 *
	 * @param path
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static Banque chargement(String path) throws FileNotFoundException, IOException {

		try (XMLDecoder decodeur = new XMLDecoder(new FileInputStream(path)))

		{
			// deserialiser tab
			Banque temp = (Banque) decodeur.readObject();
			return temp;
		}

	}

}
