package cs3500.animator.model;


import java.util.List;

import cs3500.animator.Action;
import cs3500.animator.EasyShape;

/**
 * This is an interface for the EasyAnimator model.
 */
public interface EasyAnimatorOperations {

  /**
   * Updates the animation. This method does two main things to move the animation along: -
   * Increases the time value of the animation by 1 - Applies all current actions to their
   * respective shapes - "current" here meaning that the current time of the animation is after or
   * exactly equal to the startTime of the action and below the endTime of the action
   */
  public void updateAnimation(int time);

  /**
   * Determines whether this animation is complete or not.
   *
   * @return True if the animation is over, else False
   */
  public boolean animationOver();

  /**
   * Add a new action to the model. This is how additional actions are entered into the scene.
   *
   * @param a A new action to be included in the model
   */
  public void addAction(Action a);

  /**
   * Add a new shape to the model. This is how additional shapes are entered into the scene.
   *
   * @param s A new shape to be included in the model
   */
  public void addShape(EasyShape s);

  /**
   * Format and return a text representation of this model's shapes and actions. <p> An output
   * should resemble the following format (Without the horizontal lines):
   * ----------------------------------------------- Shapes: Name: R Type: rectangle Lower-left
   * corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0) Appears at t=1
   * Disappears at t=100
   *
   * Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50
   * ------------------------------------------------ </p>
   *
   * @return A string representation of the model's current fields
   */
  public String getTextDescription();

  /**
   * Gets a copy of shapes in the model.
   *
   * @return a copy of shapes in the model.
   */
  public List<EasyShape> getShapes();

  public List<EasyShape> getShapesCopy();

  /**
   * Return a copy of Actions in the model.
   *
   * @return a copy of Actions in the model.
   */
  public List<Action> getActions();

  public int getEndTime();


  void setShapes(List<EasyShape> shapes);
}
