package cs3500.animator.view;

import cs3500.animator.controller.AnimationController;

/**
 * This interface represents a view through which we can observe our SimpleAnimator.
 */
public interface SimpleAnimationView {

  /**
   * This takes a controller and determines what kind of view is required based on the input.
   *
   * @param controller The given controller.
   */
  void howShouldIBeShown(AnimationController controller);

}
