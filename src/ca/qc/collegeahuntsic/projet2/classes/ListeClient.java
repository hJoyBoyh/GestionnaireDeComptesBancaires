package ca.qc.collegeahuntsic.projet2.classes;

import java.util.ArrayList;

public class ListeClient extends ArrayList<Client> {
	public ListeClient() {

	}

	@Override

	public boolean add(Client autre) {
		if (!this.contains(autre)) {
			return super.add(autre);
		}
		throw new IllegalArgumentException("Le client existe deja");

	}

}
