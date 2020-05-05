package fr.tooty;

import java.util.Random;

/**
 * MurCarreaux est une classe qui s'occupe de la gestion du mur de carreaux
 * @see Carreau
 */
public class MurCarreaux {
	private static final int NB_COL = 5;
	private static final char CASE_VIDE = ' ';
	//Double le nb de ligne du mur s'il n'est pas assez grand
	private static final int PAS_EXTENSION = 2;
	
	// Nb de ligne du mur de carreaux, il peut augmenter au cours du jeu
	private int nbRow = 10;
	// indice de la 1ère ligne vide de tout carreau du mur
	private int idxLigneVide;
	// nb de ligne remplie de carreau sur le mur
	private int nbNiveauComplet;
	// un tableau de tableau qui représente le mur de carreaux
	private char[][] mur;
	
	private String messageErreur;
	private Carreau neutre;
	
	/**
	 * Initialise un mur avec que des 'espace'. <br/>
	 * Le nb de ligne et de colonne du mur sont définis par les variables de 
	 * class nbRow et NB_COL
	 */
	public MurCarreaux() {
		this.mur = new char[this.nbRow][NB_COL];
		for(int i = 0; i < this.mur.length; ++i) {
			for(int j = 0; j < this.mur[i].length; ++j) {
				this.mur[i][j] = CASE_VIDE;
			}
		}
		this.idxLigneVide = 0;
		this.nbNiveauComplet = 0;
		this.messageErreur = new String();
		this.neutre = new Carreau('?', 0, 0);
	}
	/**
	 * Initialise un mur avec que des caractères 'espace'. <br/>
	 * Le joueur doit choisir le nb de ligne du mur
	 * @param tailleLigne : nombre de ligne du mur
	 * @see MurCarreaux#NB_COL
	 */
	public MurCarreaux(int tailleLigne) {
		assert tailleLigne >= 0;
		this.mur = new char[tailleLigne][NB_COL];
		for(int i = 0; i < this.mur.length; ++i) {
			for(int j = 0; j < this.mur[i].length; ++j) {
				this.mur[i][j] = CASE_VIDE;
			}
		}
		this.idxLigneVide = 0;
		this.nbNiveauComplet = 0;
		this.messageErreur = new String();
		this.neutre = new Carreau('?', 0, 0);
	}
	/**
	 * Initialise un carreau neutre aléatoire et le pose sur le mur
	 */
	public void poserCarreauNeutre() {
		Random r = new Random();
		switch(1 + r.nextInt(5-1)) {
		case 1:
			this.neutre = new Carreau(Carreau.LETTRE_NEUTRE, Carreau.TAILLE_MIN, Carreau.TAILLE_MAX);
			this.poserCarreau(neutre, 0, 0);
			break;
		case 2:
			this.neutre = new Carreau(Carreau.LETTRE_NEUTRE, Carreau.TAILLE_MIN, Carreau.TAILLE_MAX);
			this.poserCarreau(neutre, 0, 2);
			break;
		case 3:
			this.neutre = new Carreau(Carreau.LETTRE_NEUTRE, Carreau.TAILLE_MAX, Carreau.TAILLE_MIN);
			this.poserCarreau(neutre, 0, 0);
			break;
		default:
			this.neutre = new Carreau(Carreau.LETTRE_NEUTRE, Carreau.TAILLE_MAX, Carreau.TAILLE_MIN);
			this.poserCarreau(neutre, 0, 4);
		}
	}
	/**
	 * @return le nb de ligne du mur
	 */
	public int getTailleRow() {
		return this.mur.length;
	}
	/**
	 * @return  le nb de colonne du mur
	 */
	public int getTailleCol() {
		return this.mur[0].length;
	}
	/**
	 * Indique si les indices correspondent à une case du mur
	 * @param idxR : indice ligne de la case
	 * @param idxC : indice colonne de la case
	 * @return true si les indices d'une case sont valides
	 */
	public boolean caseValide(int idxR, int idxC) {
		return idxR >= 0 && idxR < this.getTailleRow() && 
				idxC >= 0 && idxC < this.getTailleCol();
	}
	/**
	 * Retourne la valeur contenue dans la case du mur qui est demandée
	 * @param idxR : indice ligne de la case demandée
	 * @param idxC : indice colonne de la case demandée
	 * @return la valeur contenue dans la case demandée
	 */
	public char getCaseMur(int idxR, int idxC) {
		assert this.caseValide(idxR, idxC);
		return this.mur[idxR][idxC];
	}
	/**
	 * Met à jour la valeur d'une case du mur
	 * @param v : la nouvelle valeur 
	 * @param idxR : indice ligne de la case
	 * @param idxC : indice colonne de la case
	 */
	public void setCaseMur(char v, int idxR, int idxC) {
		assert idxC < this.getTailleCol() && idxC >= 0 && idxR >= 0;
		//On agrandi la hauteur du mur tant que sa taille est < à idxR 
		while(idxR >= this.getTailleRow() - Carreau.TAILLE_MAX - 1){
			MurCarreaux newMur = new MurCarreaux(this.nbRow * PAS_EXTENSION);
			for(int i = 0; i < this.mur.length; ++i) {
				for(int j = 0; j < this.mur[i].length; ++j) {
					newMur.mur[i][j] = this.mur[i][j];
				}
			}
			this.mur = newMur.mur;
			this.nbRow = this.getTailleRow();
		}
		this.mur[idxR][idxC] = v;
		//On met à jour l'indice de la 1ere ligne Vide
		if(this.idxLigneVide <= idxR)
			this.idxLigneVide = idxR + 1;
	}
	/**
	 * @return en String, la representation du mur
	 */
	public String toString() {
		StringBuilder s = new StringBuilder("");
		for(int i = this.idxLigneVide; i >= 0; --i) {
			char[] c = this.mur[i];
			if( i + 1 < 10)
				s.append(" ");
			s.append(i + 1 + " "); //ajout du int i dans le StringBuilder
			for(char j: c) {
				s.append(j); //ajout du char j dans le StringBuilder
			}
			s.append("\n");
		}
		s.append("   ");
		for(int k = 1; k <= this.getTailleCol(); ++k) {
			s.append(k); //ajout du int k dans le StringBuilder
		}
		s.append("\n");
		return s.toString();
	}
	/**
	 * Pose un carreau sur le mur 
	 * @param ca : le carreau à poser
	 * @param idxR : indice ligne de la case du mur où le coin en bas à gauche du
	 * 				 carreau doit être posé.
	 * @param idxC : indice colonne de la case du mur où le coin en bas à gauche du
	 * 				 carreau doit être posé.
	 */
	public void poserCarreau(Carreau ca, int idxR, int idxC) {
		for(int i = 0; i < ca.getTailleRow(); ++i) {
			for(int j = 0; j < ca.getTailleCol(); ++j) {
				this.setCaseMur(ca.getlettre(), idxR + i, idxC + j);
			}
		}
		//On met à jour le nb de Niveau completé après que le posage d'un carreau
		this.updateNbNiveauComplet();
	}
	/**
	 * Mise à jour du nb de niveau remplie de carreau sur le mur
	 */
	private void updateNbNiveauComplet() {
		for(int i = 0; i < this.getTailleRow(); ++i) {
			for(int j = 0; j < this.getTailleCol(); ++j) {
				if(this.getCaseMur(i, j) == CASE_VIDE) {
					this.nbNiveauComplet = i;
					return;
				}
			}
		}
	}
	/**
	 * @return le nb de niveau remplie de carreau sur le mur
	 */
	public int getNbNiveauComplet() {
		return this.nbNiveauComplet;
	}

	/**
	 * @return un message d'erreur indiquant pourquoi un carreau n'a pas pu être posé
	 */
	public String getMessageErreur() {
		return this.messageErreur;
	}
	/**
	 * Indique si un carreau peut être poser ou pas. S'il ne peut pas l'être,
	 * un message indiquant la raison est enregistré dans messageErreur 
	 * accessible avec la methode getMessageErreur.
	 * @param ca : le carreau à poser
	 * @param idxR : indice ligne de la case du mur où le coin en bas à gauche du
	 * 				 carreau doit être posé.
	 * @param idxC : indice colonne de la case du mur où le coin en bas à gauche du
	 * 				 carreau doit être posé.
	 * @return true si le carreau à bien été posé sinon false
	 */
	public boolean poserCarreauValide(Carreau ca, int idxR, int idxC) {
		if(this.carreauDepasseMur(ca, idxR, idxC)) {
			this.messageErreur = "Le carreau dépasse du mur !\n";
			return false;
		}
		else {
			if(this.carreauNonStable(ca, idxR, idxC)) {
				this.messageErreur = "Le carreau n'est pas stable ! (Il se repose sur une case vide)\n";
				return false;
			}
			if(this.carreauIsoler(ca, idxR, idxC)) {
				this.messageErreur = "Le carreau est isoler !\n";
				return false;
			}
			if(this.carreauSuperposition(ca, idxR, idxC)) {
				this.messageErreur = "Le carreau superpose un autre carreau !\n";
				return false;
			}
			if(this.carreauHasClone(ca, idxR, idxC)) {
				this.messageErreur = "Le carreau clone un autre carreau !\n";
				return false;
			}
		}
		return true;
	} 
	/**
	 * Indique si le carreau dépasse du mur ou pas
	 * @param ca : le carreau à poser
	 * @param idxR : indice ligne de la case du mur où le coin en bas à gauche du
	 * 				 carreau doit être posé.
	 * @param idxC : indice colonne de la case du mur où le coin en bas à gauche du
	 * 				 carreau doit être posé.
	 * @return true si un carreau dépasse du mur sinon false
	 */
	private boolean carreauDepasseMur(Carreau ca, int idxR, int idxC) {
		return (idxC + ca.getTailleCol()) > NB_COL || idxC < 0 || idxR < 0 || idxR > this.idxLigneVide + 1 ;
	}
	/**
	 * Indique si le carreau est stable ou pas, c'est-à-dire si il y a du vide sous le carreau
	 * @param ca : le carreau à poser
	 * @param idxR : indice ligne de la case du mur où le coin en bas à gauche du
	 * 				 carreau doit être posé.
	 * @param idxC : indice colonne de la case du mur où le coin en bas à gauche du
	 * 				 carreau doit être posé.
	 * @return true si le carreau n'est pas Stable sinon false
	 */
	private boolean carreauNonStable(Carreau ca, int idxR, int idxC) {
		if(idxR > 0) {
			for(int i = 0; i < ca.getTailleCol(); i++) {
				if(this.getCaseMur(idxR - 1, idxC + i) == CASE_VIDE) {
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * Indique si le carreau superpose un autre carreau
	 * @param ca : le carreau à poser
	 * @param idxR : indice ligne de la case du mur où le coin en bas à gauche du
	 * 				 carreau doit être posé.
	 * @param idxC : indice colonne de la case du mur où le coin en bas à gauche du
	 * 				 carreau doit être posé.
	 * @return true si le carreau superpose un autre carreau
	 */
	private boolean carreauSuperposition(Carreau ca, int idxR, int idxC) {
		for(int j = 0; j < ca.getTailleCol(); ++j) {
			if(this.getCaseMur(idxR, idxC+j) != CASE_VIDE) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Indique si le carreau est isolé, c'est-à-dire s'il touche ou pas un autre carreau
	 * @param ca : le carreau à poser
	 * @param idxR : indice ligne de la case du mur où le coin en bas à gauche du
	 * 				 carreau doit être posé.
	 * @param idxC : indice colonne de la case du mur où le coin en bas à gauche du
	 * 				 carreau doit être posé.
	 * @return true si le carreau est isolé
	 */
	private boolean carreauIsoler(Carreau ca, int idxR, int idxC) {
		if(idxR > 0)
			return false;
		if(idxC + ca.getTailleCol() == NB_COL && idxC > 0) { //CARREAU TOUT A DROITE
			for(int i = 0; i < ca.getTailleRow(); i++) {
				if(this.getCaseMur(idxR+i, idxC-1) != CASE_VIDE) {
					return false;
				}
			}
		}
		else if(idxC == 0 && (idxC + ca.getTailleCol()) < NB_COL) { //CARREAU TOUT A GAUCHE
			for(int i = 0; i < ca.getTailleRow(); i++) {
				if(this.getCaseMur(idxR+i, idxC + ca.getTailleCol()) != CASE_VIDE) {
					return false;
				}
			}
		}
		else {
			for(int i = 0; i < ca.getTailleRow(); i++) {
				if(this.getCaseMur(idxR+i, idxC-1) != CASE_VIDE 
						|| this.getCaseMur(idxR+i, idxC+ca.getTailleCol()) != CASE_VIDE) {
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * Indique si le carreau clone un autre carreau
	 * @param ca : le carreau à poser
	 * @param idxR : indice ligne de la case du mur où le coin en bas à gauche du
	 * 				 carreau doit être posé.
	 * @param idxC : indice colonne de la case du mur où le coin en bas à gauche du
	 * 				 carreau doit être posé.
	 * @return true si le carreau clone un autre carreau
	 */
	private boolean carreauHasClone(Carreau ca, int idxR, int idxC) {
		int i = 0;
		if(idxR != 0) { //CARREAU SUR LA 1ere LIGNE
			for(i = ca.getTailleCol() - 1; i > 0 ; --i) {
				if(this.getCaseMur(idxR - 1, idxC+i) != this.getCaseMur(idxR - 1, idxC+i - 1)) {
					break;
				}
			}
			if(i == 0) {
				if(this.getCaseMur(idxR - 1, idxC) == Carreau.LETTRE_NEUTRE) {
					if(this.neutre.getTailleCol() == ca.getTailleCol())
						return true;
				}
				else {
					Carreau voisin = new Carreau(this.getCaseMur(idxR - 1, idxC));
					if(voisin.getTailleCol() == ca.getTailleCol())
						return true;
				}
			}
		}
		
		if(idxC == 0) { //CARREAU TOUT A GAUCHE
			for(i = ca.getTailleRow() - 1; i > 0; --i) {
				if(this.getCaseMur(idxR + i, idxC + ca.getTailleCol()) != this.getCaseMur(idxR + i - 1, idxC + ca.getTailleCol())) {
					break;
				}
			}
			if(i == 0) {
				if(this.getCaseMur(idxR, idxC + ca.getTailleCol()) == Carreau.LETTRE_NEUTRE) {
					if(this.neutre.getTailleRow() == ca.getTailleRow())
						return true;
				}
				else {
					Carreau voisin = new Carreau(this.getCaseMur(idxR, idxC + ca.getTailleCol()));
					if(voisin.getTailleRow() == ca.getTailleRow())
						return true;
				}
			}
		}
		else if(idxC + ca.getTailleCol()  == NB_COL) { //CARREAU TOUT A DROITE
			for(i = ca.getTailleRow() - 1; i > 0; --i) {
				if(this.getCaseMur(idxR + i, idxC - 1) != this.getCaseMur(idxR + i - 1, idxC - 1)) {
					break;
				}
			}
			if(i == 0) {
				if(this.getCaseMur(idxR, idxC - 1) == Carreau.LETTRE_NEUTRE) {
					if(this.neutre.getTailleRow() == ca.getTailleRow())
						return true;
				}
				else {
					Carreau voisin = new Carreau(this.getCaseMur(idxR, idxC - 1));
					if(voisin.getTailleRow() == ca.getTailleRow())
						return true;
				}
			}
		}
		else {
			//Verification s'il y a un carreau adjacent à droite
			for(i = ca.getTailleRow() - 1; i > 0; --i) { 
				if(this.getCaseMur(idxR + i, idxC + ca.getTailleCol()) != this.getCaseMur(idxR + i - 1, idxC + ca.getTailleCol())) {
					break;
				}
			}
			if(i == 0) {
				if(this.getCaseMur(idxR, idxC + ca.getTailleCol()) == Carreau.LETTRE_NEUTRE) {
					if(this.neutre.getTailleRow() == ca.getTailleRow())
						return true;
				}
				else {
					Carreau voisin = new Carreau(this.getCaseMur(idxR, idxC + ca.getTailleCol()));
					if(voisin.getTailleRow() == ca.getTailleRow())
						return true;
				}
			}
			//Verification s'il y a un carreau adjacent à gauche
			for(i = ca.getTailleRow() - 1; i > 0; --i) { 
				if(this.getCaseMur(idxR + i, idxC -1) != this.getCaseMur(idxR + i - 1, idxC - 1)) {
					break;
				}
			}
			if(i == 0) {
				if(this.getCaseMur(idxR, idxC - 1) == Carreau.LETTRE_NEUTRE) {
					if(this.neutre.getTailleRow() == ca.getTailleRow())
						return true;
				}
				else {
					Carreau voisin = new Carreau(this.getCaseMur(idxR, idxC - 1));
					if(voisin.getTailleRow() == ca.getTailleRow())
						return true;
				}
			}
		}
		return false;
	}
}