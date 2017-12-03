package cs3500.animator.view.text;

import java.awt.Color;

import cs3500.animator.model.Posn;
import cs3500.animator.model.animationobjects.Ellipse;
import cs3500.animator.model.animationobjects.Rectangle;
import cs3500.animator.shapes.ShapeAttributes;
import cs3500.animator.view.AnimationObjectVisitor;

/**
 * This class allows the animator to construct a verbose output by visiting each shape in the
 * animator.  
 */
class VerboseObjectVisitor implements AnimationObjectVisitor {
  private final int tickRate;

  VerboseObjectVisitor(int tickRate) {
    this.tickRate = tickRate;
  }

  @Override
  public String visitRectangle(Rectangle rectangle) {
    StringBuilder result = new StringBuilder();
    ShapeAttributes initialR = rectangle.asRenderItem(rectangle.getAppearanceTime());

    result.append("Name: ").append(rectangle.getName()).append("\n");
    result.append("Type: ").append("rectangle").append("\n");
    result.append("Lower-left corner: ").append(this.formatPosition(initialR.getPosition()))
            .append(", Width: ").append(initialR.getWidth())
            .append(", Height: ").append(initialR.getHeight())
            .append(", Color: ").append(this.formatColor(initialR.getColor())).append("\n");
    result.append(formatAppearDisappear(rectangle.getAppearanceTime(),
            rectangle.getDisappearanceTime()));

    return result.toString();
  }

  @Override
  public String visitEllipse(Ellipse ellipse) {
    StringBuilder result = new StringBuilder();
    ShapeAttributes initialE = ellipse.asRenderItem(ellipse.getAppearanceTime());

    result.append("Name: ").append(ellipse.getName()).append("\n");
    result.append("Type: ").append("oval").append("\n");
    result.append("Center: ").append(this.formatPosition(initialE.getPosition()))
            .append(", X radius: ").append(initialE.getWidth())
            .append(", Y radius: ").append(initialE.getHeight())
            .append(", Color: ").append(this.formatColor(initialE.getColor())).append("\n");
    result.append(formatAppearDisappear(ellipse.getAppearanceTime(),
            ellipse.getDisappearanceTime()));

    return result.toString();
  }

  /**
   * Formats a color to a more readable format.
   *
   * @param c The color to be formatted.
   * @return The given Color in a more readable format.
   */
  private String formatColor(Color c) {
    return "(" + twoDecimals(((double) c.getRed()) / 255.0) + ","
            + twoDecimals(((double) c.getGreen()) / 255.0) + ","
            + twoDecimals(((double) c.getBlue()) / 255.0) + ")";
  }

  /**
   * Cuts the double String to two decimal places.
   *
   * @param d The double to be truncated.
   * @return A double as a String with only two decimal places.
   */
  private String twoDecimals(double d) {
    return String.format("%.1f", d);
  }

  /**
   * Formats a Posn to a more readable format.
   *
   * @param p The Posn to be formatted.
   * @return The given Posn in a more readable format.
   */
  private String formatPosition(Posn p) {
    return "(" + p.getX() + "," + p.getY() + ")";
  }

  /**
   * Formats the appearance and disappearance time of the AnimationObject.
   *
   * @param appear The appearance time of the AnimationObject.
   * @param disappear THe disappearance time of the AnimationObject.
   * @return The well formatted appear-disappear.
   */
  private String formatAppearDisappear(int appear, int disappear) {
    return "Appears at t=" + ((double) appear) / ((double) tickRate) + "s\n" +
            "Disappears at t=" + ((double) disappear) / ((double) tickRate) + "s\n";
  }
}
