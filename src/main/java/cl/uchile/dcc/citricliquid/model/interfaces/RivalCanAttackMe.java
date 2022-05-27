package cl.uchile.dcc.citricliquid.model.interfaces;

import cl.uchile.dcc.citricliquid.model.BossUnit;
import cl.uchile.dcc.citricliquid.model.WildUnit;

public interface RivalCanAttackMe {
    void receiveWildUnitAttack(WildUnit wild);
    void receiveBossUnitAttack(BossUnit boss);
}
