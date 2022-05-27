package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.BossUnit;
import cl.uchile.dcc.citricliquid.model.BossUnitType;
import cl.uchile.dcc.citricliquid.model.WildUnit;
import cl.uchile.dcc.citricliquid.model.WildUnitType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public abstract class AbstractTest {
    private final ArrayList<WildUnitType> rivalsWild = new ArrayList<>();
    private final ArrayList<BossUnitType> rivalsBoss = new ArrayList<>();

    @Test
    public abstract void constructorTest();

    @Test
    public abstract void nextPanelTest();

    @Test
    public abstract void homePanelTest();

    @Test
    public abstract void neutralPanelTest();

    @Test
    public abstract void bonusPanelConsistencyTest();

    @Test
    public abstract void dropPanelConsistencyTest();


    public WildUnit getWildUnit() {
        Collections.addAll(rivalsWild, WildUnitType.values());
        int numberOfUnits = rivalsWild.size();
        int randomPosition = new Random().nextInt(numberOfUnits);
        return createWild(rivalsWild.get(randomPosition));
    }

    public WildUnit createWild(WildUnitType unit) {
        return switch (unit) {
            case CHICKEN -> new WildUnit("Chicken", 3, -1, -1, 1);
            case ROBO -> new WildUnit("Robo Ball", 3, -1, 1, -1);
            case SEAGULL -> new WildUnit("Seagull", 3, 1, -1, -1);
        };
    }

    public BossUnit getBossUnit() {
        Collections.addAll(rivalsBoss, BossUnitType.values());
        int numberOfUnits = rivalsBoss.size();
        int randomPosition = new Random().nextInt(numberOfUnits);
        return createBoss(rivalsBoss.get(randomPosition));
    }

    public BossUnit createBoss(BossUnitType unit) {
        return switch (unit) {
            case STORE -> new BossUnit("Store Manager", 8,3,2,-1);
            case SHIFU -> new BossUnit("Shifu Robot", 7,2,3,-2);
            case CASTLE -> new BossUnit("Flying Castle", 10,2,1,-3);
        };
    }



}
