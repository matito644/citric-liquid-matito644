package cl.uchile.dcc.citricliquid.model;

import org.jetbrains.annotations.NotNull;

/**
 * This class represents a player in the game 99.7% Citric Liquid.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater
 *     Muñoz</a>.
 * @version 1.1.222804
 * @since 1.0
 */
public class Player extends AbstractPlayer implements Ibattle {
  private int normaLevel;
  private int wins;

  /**
   * Creates a new player.
   *
   * @param name
   *     the character's name.
   * @param hp
   *     the initial (and max) hit points of the character.
   * @param atk
   *     the base damage the character does.
   * @param def
   *     the base defense of the character.
   * @param evd
   *     the base evasion of the character.
   */
  public Player(final String name, final int hp, final int atk, final int def,
                final int evd) {
    super(name, hp, atk, def, evd);
    normaLevel = 1;
    wins = 0;
  }

  /**
   * Returns the current norma level.
   */
  public int getNormaLevel() {
    return normaLevel;
  }

  /**
   * Performs a norma clear action; the {@code norma} counter increases in 1.
   */
  public void normaClear() {
    if (normaLevel <= 5) {
      normaLevel++;
    }
  }

  /**
   * Returns the number of wins.
   */
  public int getWins() {
    return wins;
  }

  /**
   * Increases this player's wins count by an amount.
   */
  public void increaseWins(int amount) {
    if (amount >= 0) {
      wins += amount;
    }
  }

  /**
   * Returns a copy of this character.
   */
  public Player copy() {
    return new Player(getName(), getMaxHp(), getAtk(), getDef(), getEvd());
  }

  @Override
  public void attack(@NotNull Ibattle target) {
    target.receivePlayerAttack(this);
  }

  @Override
  public void receiveWildUnitAttack(@NotNull WildUnit wild) {
    this.receiveDamage(wild.getAtk());
  }

  @Override
  public void receiveBossUnitAttack(@NotNull BossUnit boss) {
    this.receiveDamage(boss.getAtk());
  }

  @Override
  public void receivePlayerAttack(@NotNull Player player) {
    this.receiveDamage(player.getAtk());
  }
}
