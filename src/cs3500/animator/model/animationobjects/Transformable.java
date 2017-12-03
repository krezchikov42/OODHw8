package cs3500.animator.model.animationobjects;

import java.awt.*;

import cs3500.animator.model.Posn;
import cs3500.animator.model.Tuple;

public interface Transformable {

  /**
   * Applies the given MoveCommand to this AnimationObject.
   *
   * @param p The Position to be set to.
   */
  public void applyMove(Posn p);

  /**
   * Applies the given ColorCommand to this AnimationObject.
   *
   * @param c The color that this should be set to.
   */
  public void applyColor(Color c);

  /**
   * Applies the ScaleCommand and scaled this AnimationObject.
   *
   * @param scale The height with for this object to have set to.
   */
  public void applyScale(Tuple<Double, Double> scale);

}
