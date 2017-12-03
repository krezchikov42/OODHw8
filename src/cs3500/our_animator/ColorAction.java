package cs3500.animator;


/**
 * Represents an action to change the color of a shape over time.
 */
public class ColorAction extends Action {

  private Color startColor;
  private Color endColor;
  private double redIncrement;
  private double blueIncrement;
  private double greenIncrement;

  /**
   * Constructs a new cs3500.animator.ColorAction with a given shape, color to change to, and the
   * time markers.
   *
   * @param shape      The one and only shape that this specific action will apply itself to
   * @param startColor The color that this action's shape begins at
   * @param endColor   The color that this action's shape will be at the end of the action.
   * @param startTime  The time at which this action begins to be applied to its shape
   * @param endTime    The time 1 after when this action will stop being applied
   */
  public ColorAction(EasyShape shape, Color startColor, Color endColor, int startTime,
                     int endTime) {
    super(shape, startTime, endTime);
    this.startColor = startColor;
    this.endColor = endColor;
    textaction = String.format("changes color from %s to %s", startColor, endColor);

    this.setIncrements();
  }

  private ColorAction(ColorAction action) {
    this(action.getShape(), action.getStartColor(), action.getEndColor(), action.getStartTime()
            , action.getEndTime());
  }

  /**
   * Initializes the list of color increments to be added each tick to the shape's color values.
   * These increment values are used in applyToShape.
   */
  private void setIncrements() {
    int duration = this.endTime - this.startTime;
    this.redIncrement = Math.min(1, (this.endColor.getRed() - this.startColor.getRed()) / duration);
    this.blueIncrement = Math.min(1, (this.endColor.getBlue() - this.startColor.getBlue())
            / duration);
    this.greenIncrement =
            Math.min(1, (this.endColor.getGreen() - this.startColor.getGreen()) / duration);
  }

  /**
   * Increments each value (red, green, blue) of the shape's color using calculated values that will
   * ensure the shape's color is equal to this.endColor once the action ends.
   *
   * @param currentTime The current time in the animation model using this action
   */
  @Override
  public void applyToShape(int currentTime) {

    int duration = this.endTime - this.startTime;
    double newRed = Math.min(1, (this.shape.getColor().getRed()) + this.redIncrement * currentTime);
    double newBlue = Math.min(1, (this.shape.getColor().getBlue()) +
        this.blueIncrement * currentTime);
    double newGreen = Math.min(1,
            (this.shape.getColor().getGreen()) + this.greenIncrement * currentTime);
    newRed = Math.max(0, newRed);
    newGreen = Math.max(0, newGreen);
    newBlue = Math.max(0, newBlue);
    Color newColor = new Color(newRed, newGreen, newBlue);

    shape.setColor(newColor);
  }

  @Override
  public String toString() {
    return String.format("Shape %s changes color from %s to %s from t=%d to t=%d",
            this.shape.getName(), this.startColor.toString(), this.endColor.toString(),
            this.startTime, this.endTime);
  }

  @Override
  public String getSVG(float ticksOverSeconds, boolean shouldLoop) {
    String looper = (shouldLoop) ? "base.begin+" : "";
    return String.format("<animate attributeName=\"fill\" attributeType=\"CSS\"\n"
                    + "           from=\"rgb(%d,%d,%d)\" to=\"rgb(%d,%d,%d)\"\n"
                    + "           begin=\"%s%.2fs\" dur=\"%.2fs\" fill=\"freeze\" />",
            startColor.getRed255(), startColor.getGreen255(), startColor.getBlue255(),
            endColor.getRed255(), endColor.getGreen255(), endColor.getBlue255(), looper,
            startTime / ticksOverSeconds, (endTime - startTime) / ticksOverSeconds);
  }

  /**
   * Gets a copy of end Color.
   *
   * @return a copy of the end color.
   */
  public Color getEndColor() {
    return endColor.clone();
  }

  public Color getStartColor() {
    return startColor.clone();
  }

  @Override
  public Action clone() {
    return new ColorAction(this);
  }
}
