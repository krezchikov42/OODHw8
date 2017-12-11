package cs3500.animator.controller;

import cs3500.animator.view.InteractiveView;
import cs3500.animator.view.MultiFrameView;
import cs3500.animator.view.SingleTimeView;

/**
 * Represents a Controller for an animation program.
 */
public interface AnimationController {

  /**
   * Given a multi-frame view, displays the animation in a continuous timeline.
   *
   * @param view The view that produces the GUI
   */
  void showMultiFrame(MultiFrameView view);

  /**
   * Given a single-time view like a textual output or an svg, outputs the product of the view in a
   * single go.
   *
   * @param view The single-time view that is used to produce the output.
   */
  void produceSingleTime(SingleTimeView view);

  /**
   * Given an interactive view, display it and mediate user input affecting the model.
   *
   * @param view The interactive view to display.
   */
  void showInteractiveView(InteractiveView view);
}
