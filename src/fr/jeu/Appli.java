package fr.jeu;

import java.util.Scanner;

import fr.tooty.Tooty;

public class Appli {
	public static void main(String args[]) {
		System.out.println("Bonjour et bienvenue sur une partie de Tooty !!!\n");
		Tooty jeu = new Tooty();
		int tour = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Voici tous les carreaux disponibles :\n\n" + jeu.toStringStockCarreaux());
		
		do {
			System.out.println("---------- Tour numéro " + ++tour + " ----------");
			System.out.println("Le mur de carreaux :\n\n" + jeu.toStringMur());
			jeu.piocherCarte();
			System.out.println("Vous avez pioché une carte " + jeu.toStringCarte());
			System.out.println("Les carreaux correspondants à cette carte sont :\n\n" 
					+ jeu.toStringStockCarreauxAvecCondition());
			if(!jeu.stockRestreintVide()) {
				do {
					System.out.print("--> Entrer votre action : \"Next\" - \"Stop\" - "
							 + "\"<carreau> <Ligne> <Colonne>\"\n>>> ");
					String choix = new String(sc.next());
					
					if(choix.toLowerCase().equals("stop")) {
						jeu.cmdStop();
						System.out.println("___Vous avez choisi d'abandonner la partie !___\n");
						break;
					}
					else if(choix.toLowerCase().equals("next")) {
						jeu.jeterCarte();
						break;
					}
					else if(choix.length() == 1){
						if(jeu.carreauExistInSRestreint(choix.charAt(0))) {
							if(sc.hasNextInt()) {
								int numRow = sc.nextInt();
								if(sc.hasNextInt()) {
									int numCol = sc.nextInt();
									String etat = jeu.cmdPoserCarreau(choix.charAt(0), numRow - 1, numCol - 1);
									if(etat.equals("Valide")) {
										break;
									}
										System.out.println(etat);
								}
								else
									System.out.println("--> Veillez entrer le numero de colonne de l'emplacement du carreau.\n");
							}
							else
								System.out.println("--> Veillez entrer les numeros de ligne et colonne de l'emplacement du carreau.\n");
							}
						else
							System.out.println("--> Le nom du carreau que vous avez entré n'est pas present dans le stock.\n");
						}
					else
						System.out.println("--> Instruction inconnue !\n");
				}while(true);
			}
			else {
				jeu.jeterCarte();
				System.out.println("--> Passage au tour suivant.\n");
			}
		}while(!jeu.abandon() && !jeu.stockCarreauxVide() && !jeu.paquetCarteVide());
		sc.close();
		if(jeu.stockCarreauxVide())
			System.out.println("--> Le Stock de Carreaux est épuisé !");
		if(jeu.paquetCarteVide())
			System.out.println("--> Il n'y a plus de pioche !\n");
		System.out.println(jeu.toStringGameOver());
	}
}
