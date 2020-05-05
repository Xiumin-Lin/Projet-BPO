package fr.tooty;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TootyTest {
	Tooty game = new Tooty();
	@Test
	void testGetScore() {
		assertEquals(-18, game.getScore());
	}
	@Test
	void testToStringGameOver() {
		String s ="--> GAME OVER !!!\nVotre score est de :\n" 
						+ "-18 point(s) (0 niveaux complets, 18 carreau(x) non posé(s),"
						+ " 0 carte(s) écartée(s)).";
		assertEquals(s, game.toStringGameOver());
	}
	@Test
	void testAbandon() {
		assertFalse(game.abandon());
		game.cmdStop();
		assertTrue(game.abandon());
	}
}
