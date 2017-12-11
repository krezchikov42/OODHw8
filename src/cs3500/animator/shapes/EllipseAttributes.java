package cs3500.animator.shapes;

import cs3500.animator.model.ShapeAttributes;
import cs3500.animator.view.ShapeAttributesVisitor;

/**
 * This class represents the characteristics of an Ellipse to be displayed.
 */
public interface EllipseAttributes extends ShapeAttributes {

  @Override
  void drawMe(ShapeAttributesVisitor view);
}
