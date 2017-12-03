package cs3500.animator.model;

/**
 * This interface represents a position in R2. (x, y).
 *<p>
 * The y value of this posn is interpreted as growing positive below the x axis
 * and positive above the x axis.
 *</p>
 *<p>
 *             |
 *    X = -    |  X = +
 *    Y = -    |  Y = -
 *             |
 *             |
 *----------------------------
 *             |
 *   X = -     |  X = +
 *   Y = +     |  Y = +
 *             |
 *             |
 *</p>
 *
 *<p>
 * I have made the x and y values doubles in order that partially completed animations can be as
 * smooth as possible. Eventually they will have to become Ints when they are turned into pixel
 * values but I want the underlying math for partial transitions to allow for decimal values in
 * order to remain smoother.
 *</p>
 */
public interface Posn {

  /**
   * Returns the x value of the current Posn.
   *
   * @return The x value of the current Posn.
   */
  double getX();

  /**
   * Returns the y value of the current Posn.
   *
   * @return The y value of the current Posn.
   */
  double getY();

  /**
   * Returns this Posn as a readable XY coordinate.
   *
   * @return String in the format (X,Y).
   */
  String toString();

}
