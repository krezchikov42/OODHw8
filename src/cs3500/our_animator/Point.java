package cs3500.animator;

/**
 * Represents a point in a 2d space.
 */
public class Point implements CanBeCloned<Point> {
  private double x;
  private double y;

  /**
   * Creates a Point.
   *
   * @param x is the x position.
   * @param y is the y position.
   */
  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  private Point(Point p) {
    this(p.getX(), p.getY());
  }

  /**
   * Gets the x position of a point.
   *
   * @return the x position.
   */
  public double getX() {
    return x;
  }

  /**
   * Gets the y position of a point.
   *
   * @return the y position.
   */
  public double getY() {
    return y;
  }

  /**
   * Changes the point by an x and y.
   *
   * @param x is how much it changes in the x direction.
   * @param y is how much it changes in the y direction.
   */
  public Point add(double x, double y) {
    return new Point(this.x + x, this.y + y);
  }

  @Override
  public String toString() {
    return String.format("(%.2f,%.2f)", x, y);
  }

  @Override
  public Point clone() {
    return new Point(this);
  }
}
