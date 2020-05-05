package fr.tooty;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CarreauTest {
	Carreau a = new Carreau('a');
	Carreau B = new Carreau('B');
	Carreau c = new Carreau('c');
	@Test
	void testGetLettre() {
		assertEquals('c', c.getlettre());
		assertEquals('a', a.getlettre());
		assertEquals('B', B.getlettre());
	}
	@Test
	void testGetTailleRow() {
		assertEquals(1, c.getTailleRow());
	}
	@Test
	void testGetTailleCol() {
		assertEquals(2, c.getTailleCol());
	} 
	@Test
	void testTailleValide() {
		assertTrue(Carreau.tailleValide(1));
		assertTrue(Carreau.tailleValide(2));
		assertFalse(Carreau.tailleValide(-1));
		assertFalse(Carreau.tailleValide(10));
	}
	@Test
	void testlettreValide() {
		assertTrue(Carreau.lettreValide('a'));
		assertTrue(Carreau.lettreValide('A'));
		assertFalse(Carreau.lettreValide('z'));
		assertFalse(Carreau.lettreValide('?'));
	}
	@Test
	void testCouleur() {
		assertEquals(Carte.BLEU, c.couleur());
		Carreau ca2 = new Carreau('H');
		assertEquals(Carte.ROUGE, ca2.couleur());
	}
	@Test
	void testGetTabLettrePossible() {
		char[] copy = Carreau.getTabLettrePossible();
		assertEquals('a', copy[0]);
		assertEquals('b', copy[1]);
		assertEquals('i', copy[8]);
	}
}
