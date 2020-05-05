package fr.tooty;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MurCarreauxTest {
	MurCarreaux t = new MurCarreaux();
	@Test
	void testCaseValide() {
		assertTrue(t.caseValide(0, 0));
		assertFalse(t.caseValide(10, 10));
	}
	@Test
	void testGetTailleRow() {
		assertEquals(10, t.getTailleRow());
	}
	@Test
	void testGetTailleCol() {
		assertEquals(5, t.getTailleCol());
	}
	@Test
	void testGetCaseMur() {
		assertEquals(' ', t.getCaseMur(0, 0));
	}
	@Test
	void testSetCaseMur() {
		t.setCaseMur('x', 0, 0);
		t.setCaseMur('x', 1, 0);
		assertEquals('x', t.getCaseMur(0, 0));
		assertEquals('x', t.getCaseMur(1, 0));
		assertEquals(10, t.getTailleRow());
		t.setCaseMur('x', 8, 0);
		assertEquals(20, t.getTailleRow());
	}
	@Test
	void testToString() {
		String s = " 1      \n"
						 + "   12345\n";
		assertEquals(s, t.toString());
	}
	@Test 
	void testPoserCarreau(){
		Carreau d = new Carreau('d');
		t.poserCarreau(d, 0, 0);
		String s = " 3      \n"
				 + " 2 dd   \n"
				 + " 1 dd   \n"
				 + "   12345\n";
		assertEquals(s, t.toString());
	} 
	@Test 
	void testGetNbNiveauComplet() {
		assertEquals(0, t.getNbNiveauComplet());
	}
}
