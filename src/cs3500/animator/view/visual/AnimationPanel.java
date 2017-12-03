package cs3500.animator.view.visual;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import cs3500.animator.shapes.ShapeAttributes;
import cs3500.animator.view.ShapeAttributesVisitor;

/**
 * A Swing panel for rendering the shapes of the animation onto.
 */
public class AnimationPanel extends JPanel {
  private List<ShapeAttributes> shapes;

  AnimationPanel() {
    shapes = new ArrayList<>();
  }

  /**
   * Set the shapes in this panel that should be rendered.
   * @param shapes  The list of shapes to be rendered.
   */
  public void setShapes(List<ShapeAttributes> shapes) {
    this.shapes = shapes;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    ShapeAttributesVisitor v = new SwingRenderVisitor(g);

    for (ShapeAttributes shape : shapes) {
      shape.drawMe(v);
    }
  }
}
