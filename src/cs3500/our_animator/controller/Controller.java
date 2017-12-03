package cs3500.animator.controller;

/**
 * Represents a controller for an animation.
 */
public interface Controller {

  /**
   * Runs a visual view with a model.
   */
  void runViewWithVisualComponent();

  /**
   * Gets the text from the view.
   * @return text representation of the animation.
   */
  String getTextFromTextualView();

  /**
   * Checks if the Controller is looping.
   * @return if the Controller is looping.
   */
  public boolean isLoop();

  /**
   * Gets the rate.
   * @return the rate.
   */
  public float getRate();

  /**
   * Checks if the animation is running.
   * @return if the animation is running.
   */
  public boolean isRunning();
}


