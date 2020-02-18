package fr.tooty;

/**
 * Mur est une class qui s'occupe de la gestion du mur de carreaux
 * @author Lin
 * @version 1.0
 * @see Carreaux
 */
public class Mur {
	
	/**
	 * Nb de colonne du mur de carreaux
	 */
	private static final int NB_COL = 5;
	/**
	 * un tableau de tableau qui représente le mur de carreaux
	 */
	private char[][] mur;
	
	/**
	 * Constructeur Mur : initialise un mur avec que des caractères 
	 * 'espace'. <br/>
	 * Seul le nb de ligne du mur peut être chosi, le nb
	 * de colonne est NB_COL
	 * 
	 * @param nbRow
	 * 			nombre de ligne du mur
	 * @see Mur#NB_COL
	 */
	public Mur(int nbRow) {
		assert nbRow > 0;
		this.mur = new char[nbRow][NB_COL];
		for(int i = 0; i < this.mur.length; ++i) {
			for(int j = 0; j < this.mur[i].length; ++j) {
				this.mur[i][j] = '*';
			}
		}
	}
	
	/**
	 * Indique si les indices correspondent à une case du mur ou pas
	 * @param idxR
	 * 			indice ligne de la case
	 * @param idxC
	 * 			indice colonne de la case
	 * @return un booleén qui indique si les indices d'une case sont valides
	 */
	public boolean caseValide(int idxR, int idxC) {
		return idxR >= 0 && idxR < this.getTailleRow() && 
				idxC >= 0 && idxC < this.getTailleCol();
	}
	
	/**
	 * Retourne le nb de ligne du mur de carreaux
	 * @return le nb de ligne du mur
	 */
	public int getTailleRow() {
		return this.mur.length;
	}
	
	/**
	 * Retourne le nb de colonne du mur de carreaux
	 * @return  le nb de colonne du mur
	 */
	public int getTailleCol() {
		return this.mur[0].length;
	}
	
	/**
	 * Retourne la valeur contenue dans la case du mur qui est demandée
	 * @param idxR
	 * 			indice ligne de la case demandée
	 * @param idxC
	 * 			indice colonne de la case demandée
	 * @return la valeur contenue dans la case demandée
	 */
	public char getValeur(int idxR, int idxC) {
		assert this.caseValide(idxR, idxC);
		return this.mur[idxR][idxC];
	}
	
	/**
	 * Met à jour la valeur d'une case du mur
	 * @param idxR
	 * 			indice ligne de la case
	 * @param idxC
	 * 			indice colonne de la case
	 * @param v : la nouvelle valeur 
	 */
	public void setValeur(int idxR, int idxC, char v) {
		assert this.caseValide(idxR, idxC);
		this.mur[idxR][idxC] = v;
	}
	
	/**
	 * Retourne le mur dans une String
	 * A l'affichage :	?????
	 * 					x????
	 * 					x????
	 * 					x????
	 * 
	 * @return le mur dans une String
	 */
	public String toString() {
		String s ="";
		for(int i = this.getTailleRow() - 1; i >= 0; --i) {
			char[] c = this.mur[i];
			s += String.valueOf(i + 1);
			for(char j: c) {
				s += String.valueOf(j);
			}
			s += "\n";
		}
		s += " ";
		for(int k = 1; k <= this.getTailleCol(); ++k) {
			s += String.valueOf(k);
		}
		return s;
	}

}
