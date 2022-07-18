package cl.uchile.dcc.citricliquid.model.units;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerVsPlayerTest {
  private Player holmes;
  private Player watson;

  @BeforeEach
  public void setUp() {
    holmes = new Player("Holmes", 5, 2, 1, 1);
    watson = new Player("Watson", 4, 2, 2, 1);
  }

  @Test
  public void constructorTest() {
    assertNotEquals(holmes, watson);
    Player sherlock = new Player("Holmes", 5, 2, 1, 1);
    assertEquals(sherlock, holmes);
  }

  @Test
  public void fightTest() {
    assertEquals(5, holmes.getCurrentHp());
    assertEquals(4, watson.getCurrentHp());
    holmes.attack(watson);
    watson.attack(holmes);
    assertEquals(2, watson.getCurrentHp());
    assertEquals(3, holmes.getCurrentHp());
    holmes.attack(watson);
    assertEquals(0, watson.getCurrentHp());
    assertFalse(watson.isAlive());
    // holmes gana 2 victorias
    holmes.increaseWins(2);
    assertEquals(2, holmes.getWins());
  }
}
