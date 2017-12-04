package cs3500.animator.view.svg;

import cs3500.animator.model.command.Command;
import cs3500.animator.shapes.Ellipse;
import cs3500.animator.shapes.Rectangle;
import cs3500.animator.model.ShapeAttributes;
import cs3500.animator.view.AnimationObjectVisitor;

/**
 * This class visits AnimationObjects copied from the model in order to construct SVG tags for
 * them.
 */
public class SVGObjectVisitor implements AnimationObjectVisitor {

  private final int tickRate;

  /**
   * Constructs a new SVGObjectVisitor.
   *
   * @param tickRate The tickrate the animation is running at.
   */
  SVGObjectVisitor(int tickRate) {
    this.tickRate = tickRate;
  }

  @Override
  public String visitRectangle(Rectangle rectangle) {

    StringBuilder shapeTag = new StringBuilder();
    ShapeAttributes currRect = rectangle.asRenderItem(rectangle.getAppearanceTime());

    SVGCommandVisitor commandVisitor = new SVGCommandVisitor(tickRate, "x", "y",
            "width", "height");

    String colorTag = "rgb(" + currRect.getColor().getRed() + "," + currRect.getColor().getGreen()
            + "," + currRect.getColor().getBlue() + ")";

    shapeTag.append("<rect id=\"" + rectangle.getName() + "\" x=\"" + currRect.getPosition().getX()
            + "\" y=\"" + currRect.getPosition().getY() + "\" width=\"" + currRect.getWidth()
            + "\" height=\"" + currRect.getHeight() + "\" fill=\"" + colorTag
            + "\" visibility=\"visible\" >\n");

    for (Command c : rectangle.getCommandCopies()) {

      shapeTag.append("\t");
      shapeTag.append(c.showVisitor(commandVisitor));
      shapeTag.append("\n");

    }

    shapeTag.append("</rect>");

    return shapeTag.toString();

  }

  @Override
  public String visitEllipse(Ellipse ellipse) {

    StringBuilder shapeTag = new StringBuilder();
    ShapeAttributes currEllipse = ellipse.asRenderItem(ellipse.getAppearanceTime());

    SVGCommandVisitor commandVisitor = new SVGCommandVisitor(tickRate, "cx", "cy",
            "rx", "ry");

    String colorTag = "rgb(" + currEllipse.getColor().getRed() + "," +
            currEllipse.getColor().getGreen() + "," + currEllipse.getColor().getBlue() + ")";

    shapeTag.append("<ellipse id=\"" + ellipse.getName() + "\" cx=\"" +
            currEllipse.getPosition().getX()
            + "\" cy=\"" + currEllipse.getPosition().getY() + "\" rx=\"" + currEllipse.getWidth()
            + "\" ry=\"" + currEllipse.getHeight() + "\" fill=\"" + colorTag
            + "\" visibility=\"visible\">\n");

    for (Command c : ellipse.getCommandCopies()) {

      shapeTag.append("\t");
      shapeTag.append(c.showVisitor(commandVisitor));
      shapeTag.append("\n");

    }

    shapeTag.append("</ellipse>");

    return shapeTag.toString();
  }
}
