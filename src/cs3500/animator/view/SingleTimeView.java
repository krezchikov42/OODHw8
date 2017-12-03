package cs3500.animator.view;

import java.util.List;

import cs3500.animator.model.animationobjects.AnimationObject;

/**
 * This interface represents a text based view that needs to output all at once.
 */
public interface SingleTimeView extends SimpleAnimationView {

  /**
   * Produces the string-based view.
   *
   * @param animations The List of shapes that need to be displayed overall.
   * @param tickRate The tickrate that determines the times shapes and
   *                 Commands will be active, in tick/second.
   * @param totalAnimationLength The total length of the animation, in ticks.
   * @return The String output of the view.
   */
  String produce(List<AnimationObject> animations, int tickRate, int totalAnimationLength);
}
