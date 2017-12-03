package cs3500.animator.view;

import java.util.List;

import cs3500.animator.model.ShapeAttributes;

/**
 * This is an interface for an animation view that requires frame by frame updates.
 */
public interface MultiFrameView extends SimpleAnimationView {

  /**
   * Shows the current state of the animation based on the frame.
   *
   * @param shapes The list of shapes that are active.
   */
  void show(List<ShapeAttributes> shapes);

}
