package fr.tooty;

public enum Carte {
	BLEU('b'),
	ROUGE('r'),
	T1('1'),
	T2('2'),
	T3('3');
	
	private char valeur;
	
	//constructeur
	Carte(char c){
		this.valeur = c;
	}
	//Methode
	public char getCarte() {
		return this.valeur;
	}
}
