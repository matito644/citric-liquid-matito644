package cl.uchile.dcc.citricliquid.model.board;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePanelTest extends PanelTest {

  @Test
  public void applyHealTest() {
    assertEquals(suguri.getMaxHp(), suguri.getCurrentHp());
    HomePanel.applyHealTo(suguri);
    assertEquals(suguri.getMaxHp(), suguri.getCurrentHp());

    suguri.setCurrentHp(1);
    HomePanel.applyHealTo(suguri);
    assertEquals(2, suguri.getCurrentHp());
  }

  @Test
  public void normaStarTest() {
    suguri.increaseStarsBy(9);
    testHomePanel.normaCheckStars(suguri);
    assertEquals(1, suguri.getNormaLevel());
    suguri.increaseStarsBy(15);
    testHomePanel.normaCheckStars(suguri);
    assertEquals(2, suguri.getNormaLevel());
  }

  @Test
  public void normaWinTest() {
    suguri.normaClear();
    suguri.increaseWins(1);
    testHomePanel.normaCheckWins(suguri);
    assertEquals(2, suguri.getNormaLevel());
    suguri.increaseWins(1);
    testHomePanel.normaCheckWins(suguri);
    assertEquals(3, suguri.getNormaLevel());
  }

  @Test
  public void normaCheck() {
    assertEquals(1, suguri.getNormaLevel());
    suguri.increaseStarsBy(10);
    testHomePanel.normaCheck(suguri);
    assertEquals(2, suguri.getNormaLevel());
    suguri.increaseWins(2);
    testHomePanel.normaCheck(suguri);
    assertEquals(3, suguri.getNormaLevel());
  }

  @Test
  public void ownerTest() {
    assertEquals(suguri, testHomePanel.getOwner());
  }

  @Test
  public void homeActivatedByTest() {
    assertEquals(suguri.getMaxHp(), suguri.getCurrentHp());
    assertEquals(1, suguri.getNormaLevel());

    suguri.setCurrentHp(1);
    suguri.increaseStarsBy(10);
    testHomePanel.activatedBy(suguri);
    assertEquals(2, suguri.getCurrentHp());
    assertEquals(2, suguri.getNormaLevel());

    suguri.increaseWins(2);
    testHomePanel.activatedBy(suguri);
    assertEquals(3, suguri.getCurrentHp());
    assertEquals(3, suguri.getNormaLevel());
  }

}
