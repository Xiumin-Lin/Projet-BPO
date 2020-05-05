package fr.tooty;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PaquetTest {
	Paquet p = new Paquet();
	@Test
	void testPaquet() {
		assertTrue(!p.estVide());
		System.out.println("TEST Paquet :\n" + p);
	}
	@Test
	void testPiocher() {
		System.out.println("TEST Pioche :\n" +p.toString());
		Carte c = p.piocher();
		System.out.println(c);
		System.out.println("TEST Après pioche:\n" +p.toString());
	}
	@Test
	void testVide() {
		assertTrue(!p.estVide());
		for(int i = 0; i < 33; ++i) {
			p.piocher();
		}
		assertTrue(p.estVide());
	}
}
