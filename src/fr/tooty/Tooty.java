package fr.tooty;

/**
 * Tooty gère toutes les actions possibles dans le jeu.
 * C'est la seule Class qui echange avec le main.
 */
public class Tooty {
	private static final int PTS_NIV_COMPLET = 5;
	private static final int MALUS = - 1;
	private int nbCartesEcartees;
	private boolean giveUp;
	private MurCarreaux mur;
	private StockCarreaux stock;
	private Paquet paquet;
	private Carte cPiochee;
	
	/**
	 * Initialise le mur, le stock de carreau, le paquet de carte et les 
	 * informations sur la partie (Score, nb de carte jetée, la carte pioché)
	 */
	public Tooty() {
		this.nbCartesEcartees = 0;
		this.giveUp = false;
		this.mur = new MurCarreaux();
		this.stock = new StockCarreaux();
		this.paquet = new Paquet();
		this.mur.poserCarreauNeutre();
		this.cPiochee = Carte.INCONNUE;
	}
	/**
	 * @return le Score de la partie de Tooty
	 */
	public int getScore() {
		int score = PTS_NIV_COMPLET*this.mur.getNbNiveauComplet()
			+ MALUS*(this.stock.getSizeStockCarreaux() + this.nbCartesEcartees);
		return score;
	}
	/**
	 * Jete la carte piochée
	 */
	public void jeterCarte() {
		this.nbCartesEcartees++;
	}
	/**
	 * Pioche une carte du paquet
	 */
	public void piocherCarte() {
		this.cPiochee = this.paquet.piocher();
	}
	/**
	 * @return true si le joueur a abandonné la partie
	 */
	public boolean abandon() {
		return this.giveUp;
	}
	/**
	 * @return true si le paquet de carte est vide
	 */
	public boolean paquetCarteVide() {
		return this.paquet.estVide();
	}
	/**
	 * @return true si le stock de carreau est vide
	 */
	public boolean stockCarreauxVide() {
		return this.stock.estVide();
	}
	/**
	 * @return true si le stock de carreau restreint par une carte est vide
	 */
	public boolean stockRestreintVide() {
		return this.stock.stockRestreintVide();
	}
	/**
	 * Indique si un carreau est present dans le stock de carreau restreint
	 * @param c : la lettre du carreau
	 * @return true si le carreau est bien present dans le stock de carreau
	 */
	public boolean carreauExistInSRestreint(char c) {
		return this.stock.carreauExistInRestreint(c);
	}
	/**
	 * @return
	 */
	public String toStringMur() {
		return this.mur.toString();
	}
	/**
	 * @return en String la représentation du mur
	 */
	public String toStringStockCarreaux() {
		return this.stock.toString();
	}
	/**
	 * @return en String le nom de la carte
	 */
	public String toStringCarte() {
		return this.cPiochee.toString();
	}
	/**
	 * @return en String tous les carreaux du stock restreint
	 */
	public String toStringStockCarreauxAvecCondition() {
		return this.stock.toString(this.cPiochee);
	}
	/**
	 * @return en String le score et les details de la partie
	 */
	public String toStringGameOver() {
		return "--> GAME OVER !!!\nVotre score est de :\n"
				+ this.getScore() +" point(s) (" 
				+ this.mur.getNbNiveauComplet() +" niveaux complets, "
				+ this.stock.getSizeStockCarreaux() + " carreau(x) non posé(s), "
				+ this.nbCartesEcartees +" carte(s) écartée(s)).";
	}
	/**
	 * Commande Stop : permet d'abandonner la partie de Tooty
	 */
	public void cmdStop() {
		this.giveUp = true;
	}
	/**
	 * Commande Poser Carreau : permet de poser un carreau à 
	 * l'emplacement choisi si la position est valide. 
	 * @param c : le carreau à poser
	 * @param idxR : indice ligne de la case du mur où le coin en bas à gauche du
	 * 				 carreau doit être posé.
	 * @param idxC : indice colonne de la case du mur où le coin en bas à gauche du
	 * 				 carreau doit être posé.
	 * @return "Valide" si le carreau à bien été posé, sinon renvoie un message d'erreur
	 * indiquant la raison de l'echec.
	 */
	public String cmdPoserCarreau(char c, int idxR, int idxC) {
		Carreau carreauChoisi = new Carreau(c);
		if(this.mur.poserCarreauValide(carreauChoisi, idxR, idxC)) {
			this.mur.poserCarreau(carreauChoisi, idxR, idxC);
			this.stock.deleteCarreau(carreauChoisi.getlettre());
			return "Valide";
		}
		else 
			return "--> Position incorrecte ! : " + this.mur.getMessageErreur();
	}
}
