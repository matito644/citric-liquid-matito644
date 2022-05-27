package cl.uchile.dcc.citricliquid.model;

import cl.uchile.dcc.citricliquid.model.interfaces.PlayerCanAttackMe;
import cl.uchile.dcc.citricliquid.model.interfaces.RivalCanAttackMe;
import cl.uchile.dcc.citricliquid.model.interfaces.RivalCanAttackYou;
import org.jetbrains.annotations.NotNull;


public class WildUnit extends AbstractPlayer implements PlayerCanAttackMe, RivalCanAttackYou{

    /**
     * Creates a wild unit opponent.
     *
     * @param name the character's name.
     * @param hp   the initial (and max) hit points of the character.
     * @param atk  the base damage the character does.
     * @param def  the base defense of the character.
     * @param evd  the base evasion of the character.
     */
    public WildUnit(String name, int hp, int atk, int def, int evd) {
        super(name, hp, atk, def, evd);
    }

    @Override
    public void attack(@NotNull RivalCanAttackMe target) {
        target.receiveWildUnitAttack(this);
    }

    @Override
    public void receivePlayerAttack(@NotNull Player player) {
        this.receiveDamage(player.getAtk());
    }


    public WildUnit copy() {
        return new WildUnit(getName(), getMaxHp(), getAtk(), getDef(), getEvd());
    }
}
