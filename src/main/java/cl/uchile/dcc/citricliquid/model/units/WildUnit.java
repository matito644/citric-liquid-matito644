package cl.uchile.dcc.citricliquid.model.units;

import org.jetbrains.annotations.NotNull;

/**
 * This class represents a wild unit in the game.
 */
public class WildUnit extends AbstractPlayer implements Ibattle {

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
  public void attack(@NotNull Ibattle target) {
    target.receiveWildUnitAttack(this);
  }

  @Override
  public void receivePlayerAttack(@NotNull Player player) {
    this.receiveDamage(player.getAtk());
  }

  @Override
  public void receiveWildUnitAttack(WildUnit wild) { }

  @Override
  public void receiveBossUnitAttack(BossUnit boss) { }

  /**
   * Return a copy of the wild unit.
   */
  public WildUnit copy() {
    return new WildUnit(getName(), getMaxHp(), getAtk(), getDef(), getEvd());
  }
}
