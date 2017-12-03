package cs3500.animator.view;

import cs3500.animator.model.animationobjects.Ellipse;
import cs3500.animator.model.animationobjects.Rectangle;

/**
 * This interface represents a visitor that works with AnimationObjects for String-based views.
 */
public interface AnimationObjectVisitor {

  /**
   * Apply this visitor to the given Rectangle.
   *
   * @param rectangle The AnimationObjecct to be applied to.
   * @return A String created by this visitor for the view.
   */
  String visitRectangle(Rectangle rectangle);

  /**
   * Apply this visitor to the given Ellipse.
   *
   * @param ellipse The AnimationObjecct to be applied to.
   * @return A String created by this visitor for the view.
   */
  String visitEllipse(Ellipse ellipse);

}
