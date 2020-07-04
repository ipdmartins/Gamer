package gamer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Testes {
	
	private static Gamer gamer = new Gamer();

	@Test
	public void testaGanhadorA() {
		
		gamer.validade(1);
		
		assertEquals(1, gamer.getSetCounterA());
	}
	
	@Test
	public void testaGanhadorB() {
		
		gamer.validade(2);
		
		assertEquals(1, gamer.getSetCounterB());
	}
	
	@Test
	public void testaGanhadorIncorreto() {
		
		gamer.validade(2);
		
		assertNotEquals(2, gamer.getSetCounterA());
	}

}
