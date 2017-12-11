package cs3500.animator.view.visual;

import cs3500.animator.shapes.EllipseAttributes;
import cs3500.animator.shapes.RectangleAttributes;
import cs3500.animator.shapes.TextAttributes;
import cs3500.animator.shapes.TriangleAttributes;
import cs3500.animator.view.ShapeAttributesVisitor;
import java.awt.Font;
import java.awt.Graphics;

public class SwingRenderVisitor implements ShapeAttributesVisitor {

  private final Graphics g;

  /**
   * Constructs a {@code SwingRenderVisitor} object.
   *
   * @param g The graphics object the visitor will add to over its lifetime.
   */
  SwingRenderVisitor(Graphics g) {
    this.g = g;
  }

  @Override
  public void drawRectangle(RectangleAttributes rect) {
    g.setColor(rect.getColor());

    g.fillRect((int) rect.getPosition().getX(), (int) rect.getPosition().getY(),
        (int) rect.getWidth(), (int) rect.getHeight());
  }

  @Override
  public void drawText(TextAttributes text) {
    g.setFont(new Font("TimesRoman", Font.PLAIN, text.getFontSize()));
    g.setColor(text.getColor());

    g.drawString(text.getContents(), (int) text.getPosition().getX(),
        (int) text.getPosition().getY());
  }

  @Override
  public void drawTriangle(TriangleAttributes triangle) {

    g.setColor(triangle.getColor());

    int[] xPoints = {(int) (triangle.getPoint1().getX() + triangle.getPosition().getX()),
        (int) (triangle.getPoint2().getX() + triangle.getPosition().getX()),
        (int) (triangle.getPoint3().getX() + triangle.getPosition().getX())};

    int[] yPoints = {(int) (triangle.getPoint1().getY() + triangle.getPosition().getY()),
        (int) (triangle.getPoint2().getY() + triangle.getPosition().getY()),
        (int) (triangle.getPoint3().getY() + triangle.getPosition().getY())};

    g.fillPolygon(xPoints, yPoints, 3);

  }

  @Override
  public void drawEllipse(EllipseAttributes ellipse) {

    g.setColor(ellipse.getColor());

    g.fillOval((int) (ellipse.getPosition().getX() - ellipse.getWidth()),
        (int) (ellipse.getPosition().getY() - ellipse.getHeight()),
        (int) (2 * ellipse.getWidth()), (int) (2 * ellipse.getHeight()));

  }


}
