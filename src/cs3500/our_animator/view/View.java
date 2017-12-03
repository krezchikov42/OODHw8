package cs3500.animator.view;

import cs3500.animator.Action;
import cs3500.animator.EasyShape;
import java.util.List;

/**
 * Representation of a View interface.
 */
public interface View {

  /**
   * Runs a visual representation.
   * @param shapes the shapes that will be drawn.
   */
  public void run(List<EasyShape> shapes);

  /**
   * Represents the a textual representation.
   *
   * @param shapes the shapes to be described.
   * @param actions the actions to be described.
   * @param rate the rate of the animation.
   * @param endTime the end time of the model
   * @return text
   */
  public String getText(List<EasyShape> shapes, List<Action> actions, float rate, int endTime);
}
