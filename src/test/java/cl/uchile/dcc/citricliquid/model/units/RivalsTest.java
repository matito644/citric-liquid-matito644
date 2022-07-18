package cl.uchile.dcc.citricliquid.model.units;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RivalsTest {
  private WildUnit robo;
  private WildUnit seagull;
  private BossUnit shifu;
  private BossUnit castle;

  @BeforeEach
  public void setUp() {
    robo = new WildUnit("Robo Ball", 3, -1, 1, -1);
    seagull = new WildUnit("Seagull", 3, 1, -1, -1);
    shifu = new BossUnit("Shifu Robot", 7, 2, 3, -2);
    castle = new BossUnit("Flying Castle", 10, 2, 1, -3);
  }

  @Test
  public void constructorTest() {
    WildUnit expectedSeagull = new WildUnit("Seagull", 3, 1, -1, -1);
    BossUnit expectedCastle = new BossUnit("Flying Castle", 10, 2, 1, -3);
    assertEquals(expectedSeagull, seagull);
    assertEquals(expectedCastle, castle);
  }

  @Test
  public void equalsTest() {
    WildUnit expectedRobo = robo.copy();
    assertEquals(expectedRobo, robo);
    BossUnit expectedShifu = shifu.copy();
    assertEquals(expectedShifu, shifu);
    assertNotEquals(robo, seagull);
    assertNotEquals(shifu, castle);
  }
}
