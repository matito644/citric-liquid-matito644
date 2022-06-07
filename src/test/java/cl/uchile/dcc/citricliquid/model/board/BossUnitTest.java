package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.BossUnit;
import cl.uchile.dcc.citricliquid.model.Player;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BossUnitTest extends PanelTest {

  @RepeatedTest(100)
  public void bossPanelTest() {
    BossUnit boss = testBossPanel.getBossUnitInThisPanel();
    BossUnit expectedBoss = testBossPanel.getBossUnitInThisPanel().copy();
    Player expectedSuguri = suguri.copy();
    int expectedHpBoss = boss.getCurrentHp()-suguri.getAtk();
    int expectedHpSuguri = suguri.getCurrentHp()- boss.getAtk();
    expectedBoss.setCurrentHp(expectedHpBoss);
    expectedSuguri.setCurrentHp(expectedHpSuguri);
    testBossPanel.shortBattleVsBoss(suguri);
    assertEquals(expectedSuguri, suguri);
    assertEquals(expectedBoss, boss);
  }
}
