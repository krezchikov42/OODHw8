package cs3500.animator.shapes;

import java.awt.Color;

import cs3500.animator.model.Posn;
import cs3500.animator.model.Tuple;
import cs3500.animator.view.ShapeAttributesVisitor;

/**
 * Represents all features of a shape at a specific point in time.
 */
public interface ShapeAttributes {

  /**
   * Get the {@link cs3500.animator.model.Posn} of an item to be rendered.
   * @return  The position of the item.
   */
  Posn getPosition();

  /**
   * Get the width of the item to be rendered.
   * @return  The width of the item.
   */
  double getWidth();

  /**
   * Get the height of the item to be rendered.
   * @return  The height of the item.
   */
  double getHeight();

  /**
   * Get a tuple of the width and the height.
   * @return  A tuple describing the scale of the object.
   */
  Tuple<Double, Double> getScale();

  /**
   * Get the {@link Color} of an item to be rendered.
   * @return  The color of the item.
   */
  Color getColor();

  /**
   * Facilitates the drawing of this object in a graphics based view.
   *
   * @param view The a visitor to meant to render this rectangle.
   */
  void drawMe(ShapeAttributesVisitor view);

  /**
   * Returns a name, because ShapeAtrributes have those.
   *
   * @return THE NAME.
   */
  String getName();

}
