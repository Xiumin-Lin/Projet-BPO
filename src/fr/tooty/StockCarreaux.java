package fr.tooty;

import java.util.ArrayList;

/**
 * StockCarreaux gère une ArrayList de carreaux
 * @see Carreau
 */
public class StockCarreaux {
	private ArrayList<Carreau> sCarreaux;
	//Stock des carreaux restreint par une carte
	private ArrayList<Carreau> sRestreint;
	
	/**
	 * Initialise et stock dans une ArrayList tous les carreaux possibles du jeu Tooty
	 */
	public StockCarreaux() {
		this.sCarreaux = new ArrayList<>();
		for(char l : Carreau.getTabLettrePossible()) {
			this.sCarreaux.add(new Carreau(l)); //lettre bleu
			this.sCarreaux.add(new Carreau(Character.toUpperCase(l))); //lettre rouge
		}
		this.sRestreint = new ArrayList<>();
	}
	/**
	 * @return le nb de carreau contenue dans la liste de carreaux
	 */
	public int getSizeStockCarreaux() {
		return this.sCarreaux.size();
	}
	/**
	 * Indique si un indice est compris entre 0 et la taille du stock de carreau
	 * @param idx : 
	 * @return true si l'indice est valide et 
	 * 		   false si idx indique un indice en dehors du list
	 */
	private boolean indiceValide(int idx) {
		return idx >= 0 && idx < this.getSizeStockCarreaux();
	}
	/**
	 * Retourne le carreau stocké à un indice de la liste.
	 * @param idx : l'indice du carreau à retourner
	 * @return le carreau à l'indice idx
	 */
	public Carreau getCarreau(int idx) {
		assert this.indiceValide(idx);
		return this.sCarreaux.get(idx);
	}
	/**
	 * Supprime un carreau coorespondant à la lettre choisie dans le stock. 
	 * Si la lettre choisie ne se trouve pas dans le stock, alors rien ne change pas.
	 * @param c : la lettre du carreau à supprimer
	 */
	public void deleteCarreau(char c) {
		if(!this.estVide()) {
			for(int i = 0; i < this.getSizeStockCarreaux(); ++i) {
				if(this.getCarreau(i).getlettre() == c)
					this.sCarreaux.remove(i);
			}
		}
	}
	/**
	 * Indique si le stock de carreaux est vide ou pas
	 * @return true si le stock de carreaux est vide
	 */
	public boolean estVide() {
		return this.sCarreaux.isEmpty();
	}
	/**
	 * Renvoie en String la representation de tous les carreaux 
	 * séparé par un espace et qui sont encore présents dans le stock
	 * @return tous les carreaux encore présent dans la list
	 */
	public String toString() {
		StringBuilder s = new StringBuilder("");
		if(this.getSizeStockCarreaux() > 0) {
			for(int i = Carreau.TAILLE_MAX; i > 0 ; --i) {
				for(Carreau c : this.sCarreaux) {
					if(c.getTailleRow() >= i) {
						for(int j = 0; j < c.getTailleCol(); ++j) {
							s.append(c.getlettre());
						}
					}
					else {
						for(int k = 0; k < c.getTailleCol(); ++k) {
							s.append(" ");
						}
					}
					s.append(" ");
				}
				s.append("\n");
			}
		}
		else
			s.append("Aucun carreau correspondant à la demande et/ou disponible dans le stock !");
		return s.toString();
	}
	/**
	 * Renvoie en String la représentation de tous les carreaux correspondants
	 * à la carte et qui sont encore présent dans le stock.
	 * S'il n'y a pas carreau qui correspond à la condition, 
	 * la methode renvoi à la place un message avertissant l'absence de carreau.
	 * @return tous les carreaux correspondant à la condition de la carte 
	 * si au moins un carreau est dans la list, sinon retourne un message d'avertissement.
	 */
	public String toString(Carte condition) {
		if(condition.equals(Carte.INCONNUE))
			return "Veuillez d'abord piocher une carte !!!\n";
		StockCarreaux restriction = new StockCarreaux(); 
		restriction.sCarreaux = this.restriction(condition);
		return restriction.toString();
	}
	/**
	 * Selectionne et ajoute dans sRestreint tous les carreaux restreints 
	 * par une carte et qui sont encore disponible dans le Stock
	 * @param condition : la condition de la carte
	 * @return une ArrayList de carreaux encore dispo et restreint par la carte
	 */
	private ArrayList<Carreau> restriction(Carte condition){
		this.sRestreint.clear();
		for(Carreau c : this.sCarreaux) {
			if(c.couleur() == condition || c.getTailleRow() == condition.getValeur() 
					|| c.getTailleCol() == condition.getValeur()) {
				this.sRestreint.add(c);
			}
		}
		return this.sRestreint;
	}
	/**
	 * Indique s'il y a des carreaux dans la liste retreint
	 * @return true si la liste restreint n'est pas vide
	 */
	public boolean stockRestreintVide() {
		return this.sRestreint.isEmpty();
	}
	/**
	 * Verifie si un carreau avec la lettre choisi est present dans le stock restreint
	 * @param c : la lettre du carreau à verifier
	 * @return true si le carreau choisi est present dans la restreint
	 */
	public boolean carreauExistInRestreint(char c) {
		for(Carreau cRestreint: this.sRestreint) {
			if( c == cRestreint.getlettre())
				return true;
		}
		return false;
	}
	
}
