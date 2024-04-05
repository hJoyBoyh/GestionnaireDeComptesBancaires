/* Ficher Test.java
 * Auteur: Kenny Rigaud
 * Date de création : 26 oct. 2022
 */
package ca.qc.collegeahuntsic.projet2.classes;

import java.time.LocalDate;

/**
 * TODO Auteur : Kenny Jones, Rigaud
 */
public class Test {

	/**
	 *
	 */
	public Test() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
//TEST BANQUE

		Banque bk = new Banque("Scoatia", 12);
		Client client1 = new Client("Kenn", "Kenn");
		Client clientA = new Client("Kenn", "Kenn");
		Client client2 = new Client("jo", "jo");
		LocalDate date = LocalDate.now();
		CompteBancaire compteClassique1 = new CompteClassique(2000, date, bk.getNumSuccursale());
		CompteBancaire compteClassique2 = new CompteClassique(5000, date, bk.getNumSuccursale());
		CompteBancaire compteBancaire1 = new CompteEpargne(1000, date, bk.getNumSuccursale());
		// ajout client
		bk.ajouterClient(client1);
		bk.ajouterClient(client2);
		// bk.ajouterClient(clientA);
		// ajout comptes
		bk.ouvrirCompteClassique(client1, 2000, date);
		bk.ouvrirCompteClassique(client2, 5000, date);
		bk.ouvrirCompteEpargne(client2, 1000, date);

		System.out.println(bk.toString());
		// nombre client
		System.out.println(bk.nombreClient());
		System.out.println(client2.toString());

		System.out.println(client2.getListeCompteBancaire().get(0).getNumeroDeCompte());
// fermer compte
		bk.fermerCompte(client2, client2.getListeCompteBancaire().get(0).getNumeroDeCompte());
		System.out.println(client2.toString());
// test depot
		if (client2.getListeCompteBancaire().get(0) instanceof CompteEpargne) {
			CompteEpargne ce = (CompteEpargne) client2.getListeCompteBancaire().get(0);
			ce.depot(100, LocalDate.of(2000, 1, 13));
			System.out.println(client2.toString());

			client2.getListeCompteBancaire().get(0);

		}
		System.out.println(client2.toString());
// TEST CLIENT
		System.out.println("Client-------------------------------------------------");

		// test client
		Client client3 = new Client("Kenny", "Jones");
		CompteBancaire compteClassique3 = new CompteClassique(2000, date, bk.getNumSuccursale());
		CompteBancaire compteEpargne2 = new CompteEpargne(3000, date, bk.getNumSuccursale());
// ajout comptes
		client3.ajouterCompteBancaire(compteClassique3);
		client3.ajouterCompteBancaire(compteEpargne2);
		System.out.println(client3.toString());
// depot
		System.out.println("depot" + "\n");
		client3.deposer(compteClassique3.getNumeroDeCompte(), 500, date);
		client3.deposer(compteEpargne2.getNumeroDeCompte(), 10000, LocalDate.of(2003, 2, 2));
		System.out.println(client3.toString());
// retrait
		System.out.println("retrait " + "\n");
		// client3.retirer(compteEpargne2.getNumeroDeCompte(), 500, LocalDate.now());
		client3.retirer(compteClassique3.getNumeroDeCompte(), 500, date);
		System.out.println(client3.toString());
// consulation
		System.out.println("consulatation" + "\n");
		System.out.println(client3.consultationComptes());
		System.out.println("consulation compte epargne" + "\n");
		System.out.println(client3.consultationComptesEpargne());
// obtenir nombre comptes et solde total
		System.out.println("obtenir");
		System.out.println(client3.soldeDeToutLesComptes());
		System.out.println(client3.nombreComptes());
// TEST COMPTE BANCAIRE CLASSIQUE
		System.out.println("Compte classique -----------------------------------------------");
		CompteBancaire Cbc = new CompteClassique(1000, date, 25);
		System.out.println(Cbc.toString());
		if (Cbc instanceof CompteClassique) {
			CompteClassique Cbc2 = (CompteClassique) Cbc;
			// Cbc2.depot(10001, LocalDate.now());
			// Cbc2.depot(10000, LocalDate.now());
			Cbc2.retrait(1000, date);

			System.out.println(Cbc2.getSolde());
			// Cbc2.retrait(500, LocalDate.now());
			Cbc2.depot(20, LocalDate.now());
			// Cbc2.retrait(500, LocalDate.now());

			System.out.println(Cbc2.getSolde());
			Cbc2.virement(compteBancaire1, 300, date);

		}

		System.out.println(compteBancaire1.toString());
		System.out.println(Cbc.toString());
		System.out.println(Cbc.getSolde());
// TEST COMPTE BANCAIRE EPARGNE
		System.out.println("Compte epargne --------------------------------------------");

		// je suis capable d extraire les donnee en xml tous sauf pour les donner en
		// LocalDate
		CompteBancaire Cbe = new CompteEpargne(1000, LocalDate.of(2003, 1, 12), 25);

		CompteClassique ne = new CompteClassique();

		// Utilitaire.sauvegarder(bk, "./MesRessources/banque.xml");

		// Banque laban = null;

		// laban = Utilitaire.chargement("./MesRessources/banque.xml");
		// System.out.println(laban.toString());
		// System.out.println(laban.getListeClient().get(0).toString());

	}

}
