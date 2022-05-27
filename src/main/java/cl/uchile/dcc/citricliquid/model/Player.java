package cl.uchile.dcc.citricliquid.model;

import cl.uchile.dcc.citricliquid.model.interfaces.PlayerCanAttackMe;
import cl.uchile.dcc.citricliquid.model.interfaces.PlayerCanAttackYou;
import cl.uchile.dcc.citricliquid.model.interfaces.RivalCanAttackMe;
import org.jetbrains.annotations.NotNull;

/**
 * This class represents a player in the game 99.7% Citric Liquid.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater
 *     Muñoz</a>.
 * @version 1.1.222804
 * @since 1.0
 */
public class Player extends AbstractPlayer implements RivalCanAttackMe, PlayerCanAttackMe, PlayerCanAttackYou {
  private int normaLevel;

  /**
   * Creates a new character.
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
    if (normaLevel <= 5) {normaLevel++;}
  }


  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof final Player player)) {
      return false;
    }
    return getMaxHp() == player.getMaxHp()
           && getAtk() == player.getAtk()
           && getDef() == player.getDef()
           && getEvd() == player.getEvd()
           && getNormaLevel() == player.getNormaLevel()
           && getStars() == player.getStars()
           && getCurrentHp() == player.getCurrentHp()
           && getName().equals(player.getName());
  }
  /**
   * Returns a copy of this character.
   */
  public Player copy() {
    return new Player(getName(), getMaxHp(), getAtk(), getDef(), getEvd());
  }

  @Override
  public void attack(@NotNull PlayerCanAttackMe target) {
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
