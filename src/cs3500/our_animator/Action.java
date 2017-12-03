package cs3500.animator;

/**
 * Represents an action to be applied on a shape over a specified time.
 */
public abstract class Action implements Textable, CanBeCloned<Action> {


  protected EasyShape shape;


  protected int startTime;
  protected int endTime;
  protected String textaction;

  /**
   * Creates a new Action object.
   *
   * @param shape     the shape the action will be applied to.
   * @param startTime the start time of the shape.
   * @param endTime   the end time of the shape.
   */
  protected Action(EasyShape shape, int startTime, int endTime) {
    this.shape = shape;
    this.startTime = startTime;
    this.endTime = endTime;
    shape.addAction(this);
  }

  public void setShape(EasyShape shape) {
    this.shape = shape;
  }

  /**
   * Return the start time of the Action.
   *
   * @return the start time of the Action.
   */
  public int getStartTime() {
    return startTime;
  }

  /**
   * Return the end time of the Action.
   *
   * @return the end time of the Action.
   */
  public int getEndTime() {
    return endTime;
  }

  /**
   * Gets copy of shape.
   *
   * @return copy of shape
   */
  public EasyShape getShape() {
    return shape.clone();
  }

  /**
   * Applies the action effects to the shape, to be used once per tick.
   */
  public abstract void applyToShape(int currentTime);

  /**
   * Converts this actions's values into a readable english string.
   *
   * @return A string, this action explained in an english sentence
   */
  public abstract String toString();

  /**
   * Determines whether the given time is within this action's time of activity.
   *
   * @param time The current time of the model this action is a part of
   * @return True if this action is current and should be applied to its shape
   */
  public boolean isCurrent(int time) {
    return (time >= startTime) && (time <= endTime);
  }

  @Override
  public String getText(float ticksOverSeconds) {
    return String.format("%s %s from t=%.2f to t=%.2f\n", shape.getName(),
            textaction, startTime / ticksOverSeconds, endTime / ticksOverSeconds);
  }

  public abstract String getSVG(float ticksOverSeconds, boolean shouldLoop);

  @Override
  public abstract Action clone();

}
