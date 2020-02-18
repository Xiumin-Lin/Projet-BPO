package fr.tooty;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MurTest {

	@Test
	void test() {
		System.out.println("Test Unitaire du Tableau");
		int nbLigne = 4;
		Mur t = new Mur(nbLigne);
		assertEquals(5, t.getTailleCol());
		assertEquals(4, t.getTailleRow());
		assertEquals('*', t.getValeur(0, 0));
		
		t.setValeur(0, 0, 'x');
		t.setValeur(1, 0, 'x');
		t.setValeur(2, 0, 'x');
		assertEquals('x', t.getValeur(0, 0));
		
		String s = "4*****\n3x****\n2x****\n1x****\n 12345";
		assertEquals(s, t.toString());
		
		System.out.println("Affichage Tableau :\n" + t.toString());
	}

}
