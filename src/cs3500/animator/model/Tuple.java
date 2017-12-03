package cs3500.animator.model;

/**
 * A class to represent a tuple of any two values.
 *
 * @param <X> The type of the first value in the tuple.
 * @param <Y> The type of the second value in the tuple.
 */
public interface Tuple<X, Y> {


  /**
   * Retrieves the first element of this Tuple.
   *
   * @return This tuple's first element.
   */
  X first();

  /**
   * Retrieves the second element of this Tuple.
   *
   * @return This tuple's second element.
   */
  Y second();


}
