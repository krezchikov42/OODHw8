package cs3500.ouranimator.adapters;

import cs3500.animator.model.Posn;
import cs3500.animator.model.Tuple;
import cs3500.animator.shapes.EllipseAttributes;
import cs3500.animator.shapes.RectangleAttributes;
import cs3500.animator.view.ShapeAttributesVisitor;
import cs3500.ouranimator.EasyShape;
import cs3500.ouranimator.ProviderPosn;
import cs3500.ouranimator.ProviderTuple;
import java.awt.Color;

/**
 * Represents an EasyShape to ShapeAttributes adapter.
 */
public class ShapeToAttributesAdapter implements RectangleAttributes, EllipseAttributes {

  private EasyShape shape;

  /**
   * Creates a new ShapetoAttributesAdapter.
   *
   * @param s the Easyshape to be addapted.
   */
  public ShapeToAttributesAdapter(EasyShape s) {
    this.shape = s;
  }

  @Override
  public Posn getPosition() {
    return new ProviderPosn(this.shape.getPostition().getX(), this.shape.getPostition().getY());
  }

  @Override
  public double getWidth() {
    return this.shape.getWidth();
  }

  @Override
  public double getHeight() {
    return this.shape.getHeight();
  }

  @Override
  public Tuple<Double, Double> getScale() {
    return new ProviderTuple<Double, Double>(this.getHeight(), this.getWidth());
  }

  @Override
  public Color getColor() {
    return new java.awt.Color((float) this.shape.getColor().getRed(),
        (float) this.shape.getColor().getGreen(),
        (float) this.shape.getColor().getBlue());
  }

  @Override
  public void drawMe(ShapeAttributesVisitor view) {
    switch (this.shape.getShapeType()) {
      case "rectangle":
        view.drawRectangle((RectangleAttributes) this);
        break;
      case "oval":
        view.drawEllipse((EllipseAttributes) this);
        break;
      default:
        throw new IllegalArgumentException("Only rectangles and ovals allowed");
    }
  }

  @Override
  public String getName() {
    return this.shape.getName();
  }
}