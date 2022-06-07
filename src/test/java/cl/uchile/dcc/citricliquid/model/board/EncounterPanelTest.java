package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.Player;
import cl.uchile.dcc.citricliquid.model.WildUnit;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EncounterPanelTest extends PanelTest {

  @RepeatedTest(100)
  public void encounterPanelTest() {
    WildUnit wild = testEncounterPanel.getWildUniInThisPanel();
    WildUnit expectedWild = testEncounterPanel.getWildUniInThisPanel().copy();
    Player expectedSuguri = suguri.copy();
    int expectedHpWild = wild.getCurrentHp()-suguri.getAtk();
    int expectedHpSuguri = suguri.getCurrentHp()- wild.getAtk();
    expectedWild.setCurrentHp(expectedHpWild);
    expectedSuguri.setCurrentHp(expectedHpSuguri);
    testEncounterPanel.shortBattleVsWild(suguri);
    assertEquals(expectedSuguri, suguri);
    assertEquals(expectedWild, wild);
  }
}
