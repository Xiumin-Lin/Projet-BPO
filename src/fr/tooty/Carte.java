package fr.tooty;

/**
 * Carte énumère les différentes valeurs possible pour une carte
 */
public enum Carte {
	TAILLE_1(1),
	TAILLE_2(2),
	TAILLE_3(3),
	BLEU(-1),
	ROUGE(-2),
	INCONNUE(-10);
	
	private int valeur;
	
	Carte(int c){
		this.valeur = c;
	}
	public int getValeur() {
		return this.valeur;
	}
}
