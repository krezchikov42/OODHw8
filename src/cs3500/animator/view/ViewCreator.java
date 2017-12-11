package cs3500.animator.view;

import cs3500.animator.view.svg.SVGLoopbackView;
import cs3500.animator.view.svg.SVGView;
import cs3500.animator.view.text.VerboseView;
import cs3500.animator.view.visual.InteractiveSwingView;
import cs3500.animator.view.visual.SwingView;

/**
 * A factory for creating cs3500.animator.views. The possible cs3500.animator.views so far are <ul>
 * <li> visual - a visual display of the animation. </li> <li> text - a full description of the life
 * of the program. </li> <li> svg - an SVG formatted file that can be rendered in a browser. </li>
 * <li> interactive - An interactive visual view. </li> </ul>
 */
public class ViewCreator {

  /**
   * Creates the appropriate View based on a String specification by the user.
   *
   * @param viewType The type of view the user requests.
   * @return The appropriate view for the animator.
   */
  public static SimpleAnimationView create(String viewType) {
    switch (viewType) {
      case "visual":
        return new SwingView();
      case "text":
        return new VerboseView();
      case "svg":
        return new SVGView();
      case "svgloop":
        return new SVGLoopbackView();
      case "interactive":
        return new InteractiveSwingView();
      default:
        throw new IllegalArgumentException("We don't got those views.");
    }
  }
}
