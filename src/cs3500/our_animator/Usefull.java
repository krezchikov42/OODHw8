package cs3500.animator;

/**
 * Represents a class with usefull methods.
 */
public class Usefull {
  /**
   * Checks if objects are null and throws an exception otherwise.
   *
   * @param o       that will be checked if null.
   * @param message that will be in exception.
   */
  public static void throwkNull(Object o, String message) {
    if (o == null) {
      throw new IllegalArgumentException(message + " is null");
    }
  }

  /**
   * Checks if the int is non negative.
   *
   * @param a       is any number.
   * @param message the message that will be in the exception.
   */
  public static void throwNonNegative(int a, String message) {
    if (a < 0) {
      throw new IllegalArgumentException(message + " is negative");
    }
  }

  /**
   * Checks if 2 numbers are between inclusive.
   *
   * @param check the number supposed to be inbetween.
   * @param start the start of the range.
   * @param end   the end of the range.
   * @return if check is between start and end
   */

  public static boolean checkBetweenInc(double check, double start, double end) {
    return (check >= start || check <= end);
  }

}
