package cl.uchile.dcc.citricliquid.model;


import org.jetbrains.annotations.NotNull;

/**
 * This class represents a boss unit in the game.
 */
public class BossUnit extends AbstractPlayer implements Ibattle {

  /**
   * Creates a boss unit opponent.
   *
   * @param name the character's name.
   * @param hp   the initial (and max) hit points of the character.
   * @param atk  the base damage the character does.
   * @param def  the base defense of the character.
   * @param evd  the base evasion of the character.
   */
  public BossUnit(String name, int hp, int atk, int def, int evd) {
    super(name, hp, atk, def, evd);
  }

  @Override
  public void attack(@NotNull Ibattle target) {
    target.receiveBossUnitAttack(this);
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
   * Return a copy of the boss unit.
   */
  public BossUnit copy() {
    return new BossUnit(getName(), getMaxHp(), getAtk(), getDef(), getEvd());
  }

}
