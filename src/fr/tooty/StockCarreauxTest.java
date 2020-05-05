package fr.tooty;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StockCarreauxTest {
	StockCarreaux sca = new StockCarreaux();
	@Test
	void testGetSizeStockCarreaux() {
		assertEquals(18, sca.getSizeStockCarreaux());
	}
	@Test
	void testGetCarreau() {
		assertEquals('a', sca.getCarreau(0).getlettre());
		assertEquals('A', sca.getCarreau(1).getlettre());
		assertEquals('b', sca.getCarreau(2).getlettre());
		assertEquals('c', sca.getCarreau(4).getlettre());
		assertEquals('I', sca.getCarreau(17).getlettre());
		assertEquals(1, sca.getCarreau(0).getTailleRow());
		assertEquals(1, sca.getCarreau(0).getTailleCol());
	}
	@Test
	void testDeleteCarreau() {
		sca.deleteCarreau('a'); //suppression de 'a'
		sca.deleteCarreau('A'); //suppression de 'A'
		System.out.println(sca.toString());
		assertEquals(16, sca.getSizeStockCarreaux());
		assertEquals('b', sca.getCarreau(0).getlettre());
	}
	@Test
	void testToString() {
		String s = 	"                    e E         gg GG         iii III \n" + 
								"    b B       dd DD e E         gg GG hhh HHH iii III \n" + 
								"a A b B cc CC dd DD e E fff FFF gg GG hhh HHH iii III \n";
		assertEquals(s, sca.toString());
		sca.deleteCarreau('a');
		sca.deleteCarreau('A');
		String s2 = "                e E         gg GG         iii III \n" + 
								"b B       dd DD e E         gg GG hhh HHH iii III \n" + 
								"b B cc CC dd DD e E fff FFF gg GG hhh HHH iii III \n";
		assertEquals(s2, sca.toString());
		for(char c: Carreau.getTabLettrePossible()) {
			sca.deleteCarreau(c);
			sca.deleteCarreau(Character.toUpperCase(c));
		}
		String s3=  "Aucun carreau correspondant à la demande et/ou disponible dans le stock !";
		assertEquals(s3, sca.toString());
	}
	@Test
	void testAffichageAvecCondition() {
		String blue = "          e     gg     iii \n" + 
					  			"  b    dd e     gg hhh iii \n" + 
					  			"a b cc dd e fff gg hhh iii \n";
		assertEquals(blue, sca.toString(Carte.BLEU));
		String red = 	"          E     GG     III \n" + 
									"  B    DD E     GG HHH III \n" + 
									"A B CC DD E FFF GG HHH III \n";
		assertEquals(red, sca.toString(Carte.ROUGE));
		String t1 = "              e E         \n" + 
								"    b B       e E         \n" + 
								"a A b B cc CC e E fff FFF \n";
		assertEquals(t1, sca.toString(Carte.TAILLE_1));
		String err = "Veuillez d'abord piocher une carte !!!\n";
		assertEquals(err, sca.toString(Carte.INCONNUE));
	}
	@Test
	void testStockRestreint() {
		assertTrue(sca.stockRestreintVide());
		sca.toString(Carte.BLEU);
		assertFalse(sca.stockRestreintVide());
		assertTrue(sca.carreauExistInRestreint('a'));
		assertFalse(sca.carreauExistInRestreint('B'));
	}
	@Test
	void testEstVide() {
		assertFalse(sca.estVide());
		for(char c: Carreau.getTabLettrePossible()) {
			sca.deleteCarreau(c);
			sca.deleteCarreau(Character.toUpperCase(c));
		}
		assertTrue(sca.estVide());
	}
}
