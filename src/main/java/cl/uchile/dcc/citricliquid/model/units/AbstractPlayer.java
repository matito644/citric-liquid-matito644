package cl.uchile.dcc.citricliquid.model.units;


import java.util.Objects;
import java.util.Random;

/**
 * This abstract class represents all the common features of a player.
 */
public abstract class AbstractPlayer {
  private final String name;
  private final int maxHp;
  private final int atk;
  private final int def;
  private final int evd;
  private int currentHp;
  private int stars;
  private final Random random;

  /**
   * Creates an abstract character.
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
  public AbstractPlayer(final String name, final int hp, final int atk, final int def,
                        final int evd) {
    this.name = name;
    this.maxHp = currentHp = hp;
    this.atk = atk;
    this.def = def;
    this.evd = evd;
    random = new Random();
  }

  /**
   * Returns the character's name.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the character's max hit points.
   */
  public int getMaxHp() {
    return maxHp;
  }

  /**
   * Returns the current character's attack points.
   */
  public int getAtk() {
    return atk;
  }

  /**
   * Returns the current character's defense points.
   */
  public int getDef() {
    return def;
  }

  /**
   * Returns the current character's evasion points.
   */
  public int getEvd() {
    return evd;
  }

  /**
   * Returns the current hit points of the character.
   */
  public int getCurrentHp() {
    return currentHp;
  }

  /**
   * Sets the current character's hit points.
   *
   * <p>The character's hit points have a constraint to always be between 0 and maxHP, both
   * inclusive.
   */
  public void setCurrentHp(final int newHp) {
    this.currentHp = Math.max(Math.min(newHp, maxHp), 0);
  }

  /**
   * Set's the seed for this player's random number generator.
   *
   * <p>The random number generator is used for taking non-deterministic decisions, this method is
   * declared to avoid non-deterministic behaviour while testing the code.
   */
  public void setSeed(final long seed) {
    random.setSeed(seed);
  }

  /**
   * Returns a uniformly distributed random value in [1, 6].
   */
  public int roll() {
    return random.nextInt(6) + 1;
  }

  /**
   * Returns this player's star count.
   */
  public int getStars() {
    return stars;
  }

  /**
   * Increases this player's star count by an amount.
   */
  public void increaseStarsBy(final int amount) {
    if (amount >= 0) {
      stars += amount;
    }
  }

  /**
   * Reduces this player's star count by a given amount.
   *
   * <p>The star count will must always be greater or equal to 0
   */
  public void reduceStarsBy(final int amount) {
    stars = Math.max(0, stars - amount);
  }

  /**
   * Decrease the hp of the character.
   *
   * @param damage is the amount of damage that the character will receive.
   */
  public void receiveDamage(int damage) {
    setCurrentHp(getCurrentHp() - damage);
  }

  /**
   * Return True is the character have any hp.
   */
  public boolean isAlive() {
    return this.currentHp != 0;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AbstractPlayer that)) {
      return false;
    }
    return maxHp == that.maxHp && atk == that.atk && def == that.def
            && evd == that.evd && currentHp == that.currentHp && Objects.equals(name, that.name);
  }
}