package fr.tooty;

/**
 * Carreau est une classe qui s'occupe de la gestion des carreaux
 */
public class Carreau {
	public static final char LETTRE_NEUTRE ='x';
	public static final int TAILLE_MIN = 1;
	public static final int TAILLE_MAX = 3;
	
	private static final char[] LETTRE_VALIDE = {'a','b','c','d','e','f','g','h','i'};
	
	private char lettre;
	private int tailleRow; // nb ligne du carreau
	private int tailleCol; // nb colonne du carreau
	
	/**
	 * Initialise un carreau dont la lettre et les dimensions sont décidés par le joueur.
	 * @param l : la lettre du nouveau carreau
	 * @param r : le nb de ligne du nouveau carreau
	 * @param c : le nb de colonne du nouveau carreau
	 */
	public Carreau(char l, int r, int c) {
		this.lettre = l;
		this.tailleRow = r;
		this.tailleCol = c;
	}
	/**
	 * Initialise un carreau et attribue selon la lettre donnée
	 * sa taille spécifique si cette lettre est valide.
	 * @param l : la lettre du nouveau carreau
	 */
	public Carreau(char l) {
		assert Carreau.lettreValide(l);
		this.lettre = l;
		switch(l) {
			case 'a': case 'A':
				this.tailleRow = TAILLE_MIN;
				this.tailleCol = TAILLE_MIN;
				break;
			case 'b': case 'B':
				this.tailleRow = TAILLE_MIN + 1;
				this.tailleCol = TAILLE_MIN;
				break;
			case 'c': case 'C':
				this.tailleRow = TAILLE_MIN;
				this.tailleCol = TAILLE_MIN + 1;
				break;
			case 'd': case 'D':
				this.tailleRow = TAILLE_MIN + 1;
				this.tailleCol = TAILLE_MIN + 1;
				break;
			case 'e': case 'E':
				this.tailleRow = TAILLE_MIN + 2;
				this.tailleCol = TAILLE_MIN;
				break;
			case 'f': case 'F':
				this.tailleRow = TAILLE_MIN;
				this.tailleCol = TAILLE_MIN + 2;
				break;
			case 'g': case 'G':
				this.tailleRow = TAILLE_MIN + 2;
				this.tailleCol = TAILLE_MIN + 1;
				break;
			case 'h': case 'H':
				this.tailleRow = TAILLE_MIN + 1;
				this.tailleCol = TAILLE_MIN + 2;
				break;
			case 'i': case 'I':
				this.tailleRow = TAILLE_MIN + 2;
				this.tailleCol = TAILLE_MIN + 2;
				break;
		}
	}
	/**
	 * @return la lettre du carreau
	 */
	public char getlettre() {
		return this.lettre;
	}
	/**
	 * @return le nb de ligne du carreau
	 */
	public int getTailleRow() {
		return this.tailleRow;
	}
	/**
	 * @return le nb de colonne du carreau
	 */
	public int getTailleCol() {
		return this.tailleCol;
	}
	/**
	 * Renvoie une copie du tableau de lettre possible pour un carreau
	 * @return le tableau de lettre possible pour un carreau
	 */
	public static char[] getTabLettrePossible() {
		char[] copyTab = new char[Carreau.LETTRE_VALIDE.length];
		int i = 0;
		for(char c:Carreau.LETTRE_VALIDE) {
			copyTab[i] = c;
			++i;
		}
		return copyTab; 
	}
	/**
	 * Renvoie la couleur du carreau
	 * @return la Carte couleur qui correspond au carreau
	 */
	public Carte couleur() {
		if(this.lettre != Character.toUpperCase(this.lettre)) {
			return Carte.BLEU;
		}
		else
			return Carte.ROUGE;
	}
	
	//------------------------ Methode Static ------------------------
	/**
	 * Indique si une taille est valide pour un carreau
	 * @param t : la taille à vérifier
	 * @return true si la taille est valide
	 */
	public static boolean tailleValide(int t) {
		return t >= Carreau.TAILLE_MIN && t <= Carreau.TAILLE_MAX;
	}
	/**
	 * Indique si une lettre est autorisée pour un carreau
	 * @param l : la lettre pour le carreau
	 * @return true si la lettre se trouve dans le tableau LETTRE_POSSIBLE
	 */
	public static boolean lettreValide(char l) {
		boolean b = false;
		for(char exist: Carreau.LETTRE_VALIDE){
			if(l == exist || l == Character.toUpperCase(exist)) {
				return b = true;
			}
		}
		return b;
	}
}
