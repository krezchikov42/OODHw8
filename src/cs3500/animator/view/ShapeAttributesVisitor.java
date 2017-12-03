package cs3500.animator.view;

import cs3500.animator.shapes.EllipseAttributes;
import cs3500.animator.shapes.RectangleAttributes;
import cs3500.animator.shapes.TextAttributes;
import cs3500.animator.shapes.TriangleAttributes;

/**
 * This visitors visits the various ShapeAttributes in order to draw them in a Graphics based view.
 */
public interface ShapeAttributesVisitor {

  /**
   * Given a rectangle this makes it appear in a Graphics based view.
   *
   * @param rect The rectangle to be drawn.
   */
  void drawRectangle(RectangleAttributes rect);

  /**
   * Given a Text this makes it appear in a Graphics based view.
   *
   * @param text The text to be drawn.
   */
  void drawText(TextAttributes text);

  /**
   * Given a Triangle this makes it appear in a Graphics based view.
   *
   * @param triangle The triangle to be drawn.
   */
  void drawTriangle(TriangleAttributes triangle);

  /**
   * Given a Ellipse this makes it appear in a Graphics based view.
   *
   * @param ellipse The Ellipse to be drawn.
   */
  void drawEllipse(EllipseAttributes ellipse);

}
