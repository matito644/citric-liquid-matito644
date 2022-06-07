package cl.uchile.dcc.citricliquid.model;

/**
 * This interface allows characters to fight with each other.
 */
public interface Ibattle {

  /**
   * Main method to attack the opponent.
   *
   * @param target represents the character that will receive the attack.
   */
  void attack(Ibattle target);

  /**
   * Receive the attack of a player.
   *
   * @param player will tell us the damage, according to its atk.
   */
  void receivePlayerAttack(Player player);

  /**
   * Receive the attack of a wild unit.
   *
   * @param wild will tell us the damage, according to its atk.
   */
  void receiveWildUnitAttack(WildUnit wild);

  /**
   * Receive the attack of a boss unit.
   *
   * @param boss will tell us the damage, according to its atk.
   */
  void receiveBossUnitAttack(BossUnit boss);

}
