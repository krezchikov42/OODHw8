package cs3500.animator.shapes;

import cs3500.animator.model.Posn;
import cs3500.animator.model.ShapeAttributes;
import cs3500.animator.view.ShapeAttributesVisitor;

public interface TriangleAttributes extends ShapeAttributes {

  /**
   * Returns a copy of the point1.
   *
   * @return A copy of point1.
   */
  Posn getPoint1();

  /**
   * Returns a copy of the point2.
   *
   * @return A copy of point2.
   */
  Posn getPoint2();

  /**
   * Returns a copy of the point3.
   *
   * @return A copy of point3.
   */
  Posn getPoint3();

  @Override
  void drawMe(ShapeAttributesVisitor view);
}
