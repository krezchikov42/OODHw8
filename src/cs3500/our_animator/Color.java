package cs3500.animator;

/**
 * Represents a color.
 */
public class Color implements CanBeCloned<Color> {
  private double red;
  private double green;
  private double blue;

  /**
   * Creates a color with rgb values between 0 and 1.
   *
   * @param r the red value.
   * @param g the green value.
   * @param b the blue color.
   */
  public Color(double r, double g, double b) {
    Usefull.checkBetweenInc(r, 0, 1.0);
    Usefull.checkBetweenInc(g, 0, 1.0);
    Usefull.checkBetweenInc(b, 0, 1.0);

    this.red = r;
    this.green = g;
    this.blue = b;
  }

  /**
   * Creates a color with rgb values between 0 and 255.
   *
   * @param r the red value.
   * @param g the green value;
   * @param b the blue value.
   */
  public Color(int r, int g, int b) {
    this(r / 255f, g / 255f, b / 255f);
  }

  //used to clone the Color
  private Color(Color c) {
    this(c.getRed(), c.getGreen(), c.getBlue());
  }

  /**
   * Get red value between 0 and 1.
   *
   * @return the red value.
   */
  public double getRed() {
    return red;
  }

  /**
   * Get green value between 0 and 1.
   *
   * @return the green value.
   */
  public double getGreen() {
    return green;
  }

  /**
   * Get blue value between 0 and 1.
   *
   * @return the blue value.
   */
  public double getBlue() {
    return blue;
  }

  /**
   * Gets the red value between 0 and 255.
   *
   * @return red value.
   */
  public int getRed255() {
    return (int) (red * 255);
  }

  /**
   * Gets the green value between 0 and 255.
   *
   * @return green value.
   */
  public int getGreen255() {
    return (int) (green * 255);
  }

  /**
   * Gets the blue value between 0 and 255.
   *
   * @return blue value.
   */
  public int getBlue255() {
    return (int) (blue * 255);
  }

  @Override
  public String toString() {
    return String.format("%.2f,%.2f,%.2f", red, green, blue);
  }


  @Override
  public Color clone() {
    return new Color(this);
  }
}
