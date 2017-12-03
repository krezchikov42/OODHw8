package cs3500.animator;

/**
 * Represents an action to scale a shape to a different size over time.
 */
public class ScaleAction extends Action {

  private double origX;
  private double origY;
  private double targetX;
  private double targetY;
  private double xincr;
  private double yincr;

  /**
   * Constructs an action to scale a shape over time.
   *
   * @param shape     The one and only shape that this action will be applied to
   * @param origX     The x dimension of shape at the start of the action
   * @param origY     The y dimension of shape at the start of the action
   * @param targetX   The x dimension of shape at the end of the action
   * @param targetY   The y dimension of shape at the end of the action
   * @param startTime The time at which this action begins being applied
   * @param endTime   The time at which this action is no longer to be applied
   */
  public ScaleAction(EasyShape shape, double origX, double origY, double targetX,
                     double targetY, int startTime, int endTime) {
    super(shape, startTime, endTime);
    this.origX = origX;
    this.origY = origY;
    this.targetX = targetX;
    this.targetY = targetY;
    int duration = this.endTime - this.startTime;
    xincr = (targetX - origX) / duration;
    yincr = (targetY - origY) / duration;
    textaction =
            String.format("scales from Width %.0f, Height %.0f to Width %.0f, Height %.0f",
                    shape.getWidth(), shape.getHeight(), targetX, targetY);
  }

  private ScaleAction(ScaleAction action) {
    this(action.getShape(), action.getOrigX(), action.getOrigY(), action.getTargetX(),
            action.getTargetY(), action.getStartTime()
            , action.getEndTime());
  }


  public double getTargetX() {
    return targetX;
  }

  public double getTargetY() {
    return targetY;
  }

  public double getOrigX() {
    return origX;
  }

  public double getOrigY() {
    return origY;
  }

  @Override
  public void applyToShape(int currentTime) {
    EasyShape clone = shape.clone();
    clone.setHeight(shape.getHeight() + yincr * currentTime);
    clone.setWidth(shape.getWidth() + xincr * currentTime);
    //return clone;
  }

  @Override
  public String toString() {
    return String.format("Shape %s scales from %s to (%f, %f) from t=%d to t=%d",
            this.shape.getName(), origX, origY, targetX, targetY,
            this.startTime, this.endTime);
  }

  @Override
  public String getSVG(float ticksOverSeconds, boolean shouldLoop) {
    String looper = (shouldLoop) ? "base.begin+" : "";
    return String.format("<animateTransform attributeName=\"transform\" attributeType=\"XML\"\n"
                    + "           type=\"scale\" from=\"%.2f %.2f\" " +
                    "to=\"%.2f %.2f\" additive=\"sum\"\n"
                    + "           begin=\"%s%.2fs\" dur=\"%.2fs\" fill=\"freeze\" />",
            this.origX, this.origY, this.targetX, this.targetY, looper,
    this.startTime / ticksOverSeconds, this.endTime / ticksOverSeconds);
  }

  @Override
  public Action clone() {
    return new ScaleAction(this);
  }
}