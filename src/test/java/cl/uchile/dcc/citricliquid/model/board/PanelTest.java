package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.units.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater M.</a>.
 * @version 1.0.6-rc.1
 * @since 1.0
 */
class PanelTest {
  private final static String PLAYER_NAME = "Suguri";
  private final static int BASE_HP = 4;
  private final static int BASE_ATK = 1;
  private final static int BASE_DEF = -1;
  private final static int BASE_EVD = 2;
  private Panel testNeutralPanel;
  private Panel testPanel;
  protected HomePanel testHomePanel;
  protected BonusPanel testBonusPanel;
  protected DropPanel testDropPanel;
  protected EncounterPanel testEncounterPanel;
  protected BossPanel testBossPanel;
  protected Player suguri;
  protected long testSeed;

  @BeforeEach
  public void setUp() {
    suguri = new Player(PLAYER_NAME, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
    testBonusPanel = new BonusPanel();
    testBossPanel = new BossPanel();
    testDropPanel = new DropPanel();
    testEncounterPanel = new EncounterPanel();
    testHomePanel = new HomePanel(suguri);
    testNeutralPanel = new Panel();
    testPanel = new Panel();
    testSeed = new Random().nextLong();
  }

  @Test
  public void constructorTest() {
    assertEquals(PanelType.BONUS, testBonusPanel.getType());
    assertEquals(PanelType.BOSS, testBossPanel.getType());
    assertEquals(PanelType.DROP, testDropPanel.getType());
    assertEquals(PanelType.ENCOUNTER, testEncounterPanel.getType());
    assertEquals(PanelType.HOME, testHomePanel.getType());
    assertEquals(PanelType.NEUTRAL, testNeutralPanel.getType());
    assertEquals(PanelType.NEUTRAL, testPanel.getType());
  }

  @Test
  public void nextPanelTest() {
    assertTrue(testNeutralPanel.getNextPanels().isEmpty());
    final Ipanel expectedPanel1 = new Panel();
    final Ipanel expectedPanel2 = new BonusPanel();

    testNeutralPanel.addNextPanel(expectedPanel1);
    assertEquals(1, testNeutralPanel.getNextPanels().size());

    testNeutralPanel.addNextPanel(expectedPanel2);
    assertEquals(2, testNeutralPanel.getNextPanels().size());

    testNeutralPanel.addNextPanel(expectedPanel2);
    assertEquals(2, testNeutralPanel.getNextPanels().size());
    assertEquals(Set.of(expectedPanel1, expectedPanel2),
                 testNeutralPanel.getNextPanels());
  }

  @Test
  public void addRemovePlayersTest() {
    assertTrue(testNeutralPanel.getPlayersHere().isEmpty());
    final Player holmes = new Player("Sherlock", 5,5,5,5);

    testNeutralPanel.addPlayer(suguri);
    assertEquals(1, testNeutralPanel.getPlayersHere().size());

    testNeutralPanel.addPlayer(holmes);
    assertEquals(2, testNeutralPanel.getPlayersHere().size());
    assertEquals(Set.of(suguri, holmes), testNeutralPanel.getPlayersHere());

    testNeutralPanel.removePlayer(suguri);
    assertEquals(1, testNeutralPanel.getPlayersHere().size());
    assertEquals(Set.of(holmes), testNeutralPanel.getPlayersHere());
  }

  @Test
  public void neutralActivatedByTest() {
    Player copyOfSuguri = suguri.copy();
    testNeutralPanel.activatedBy(suguri);
    assertEquals(copyOfSuguri, suguri);
  }
}