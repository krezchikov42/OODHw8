package cs3500.animator;

/**
 * Represents an interface for objects that can be cloned.
 */
public interface CanBeCloned<T> {
  /**
   * Returns a clone of Object type t.
   *
   * @return a clone of t.
   */
  public T clone();
}
