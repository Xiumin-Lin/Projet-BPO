package fr.tooty;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Paquet s'occupe de la gestion d'un paquet de carte
 * @see Carte
 */
public class Paquet {
	private static final int NB_CARTE_COULEUR = 9;
	private static final int NB_CARTE_TAILLE = 5;
	private LinkedList<Carte> deck;
	/**
	 * Initialise dans une LinkdedList tous les cartes pour le jeu Tooty
	 */
	public Paquet() {
		this.deck = new LinkedList<>();
		int nbCarte = 0;
		for(Carte c: Carte.values()) {
				if(c.equals(Carte.BLEU) || c.equals(Carte.ROUGE))
					nbCarte = Paquet.NB_CARTE_COULEUR;
				else
					nbCarte = Paquet.NB_CARTE_TAILLE;
				if(!c.equals(Carte.INCONNUE))
					for(int i = 0; i < nbCarte; ++i) {
							deck.add(c);
					}
		}
		this.melanger(); //melange du paquet
	}
	/**
	 * @return true si le paquet est Vide
	 */
	public boolean estVide() {
		return this.deck.isEmpty();
	}
	/**
	 * Pioche la carte à l'indice 0 du paquet. Cette carte est ensuite supprimée.
	 * @return la carte à l'indice 0 de la list
	 */
	public Carte piocher() {
		assert !this.estVide();
		Carte a = this.deck.getFirst();
		this.deck.removeFirst();
		return a;
	}
	
	/**
	 * @return retourne une chaîne indiquant le nb de carte dans le paquet et leurs valeurs
	 */
	public String toString() {
		StringBuilder s = new StringBuilder("Il y a " + 
																				this.deck.size() + 
																				" carte(s) dans le deck.\n");
		for(Carte c : Carte.values()) {
			int i = 0;
			for(Carte d : this.deck) {
				if(d.equals(c))
					++i;
			}
			if(i != 0)
				s.append(i + " carte(s) \"" + c.toString() +"\" ; ");
		}
		return s.toString();
	}
	
	/**
	 * Melange le paquet de carte
	 */
	private void melanger() {
		Collections.shuffle(this.deck);
	}
	
}
