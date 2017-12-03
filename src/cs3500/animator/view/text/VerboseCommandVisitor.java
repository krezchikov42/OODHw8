package cs3500.animator.view.text;

import java.awt.*;

import cs3500.animator.command.Command;
import cs3500.animator.model.Posn;
import cs3500.animator.model.Tuple;
import cs3500.animator.model.animationobjects.AnimationObject;
import cs3500.animator.view.CommandVisitor;

/**
 * A visitor that collects verbose text output for commands.
 */
class VerboseCommandVisitor implements CommandVisitor {
  private final AnimationObject ao;
  private final int tickRate;

  /**
   * Constructs a new VerboseCommandVisitor.
   *
   * @param ao The animationObject that the commands being visited exist for.
   * @param tickRate The tick rate of the overall animation.
   */
  VerboseCommandVisitor(AnimationObject ao, int tickRate) {
    this.ao = ao;
    this.tickRate = tickRate;
  }

  @Override
  public String visitMove(Command<Posn> c) {
    Posn startP = c.getState(c.getStartTime());
    Posn endP = c.getState(c.getEndTime());

    return "Shape " + ao.getName()
            + " moves from " + this.formatPosition(startP)
            + " to " + this.formatPosition(endP)
            + this.formatTimeRange(c);
  }

  @Override
  public String visitChangeColor(Command<Color> c) {
    Color startColor = c.getState(c.getStartTime());
    Color endColor = c.getState(c.getEndTime());

    return "Shape " + ao.getName()
            + " changes color from " + this.formatColor(startColor)
            + " to " + this.formatColor(endColor)
            + this.formatTimeRange(c);
  }

  @Override
  public String visitScale(Command<Tuple<Double, Double>> c) {
    Tuple<Double, Double> startScale = c.getState(c.getStartTime());
    Tuple<Double, Double> endScale = c.getState(c.getEndTime());

    return "Shape " + ao.getName()
            + " scales from"
            + " Width: " + startScale.first() + ","
            + " Height: " + startScale.second() + " to"
            + " Width: " + endScale.first() + ","
            + " Height: " + endScale.second()
            + this.formatTimeRange(c);

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
   * Formats a command's time range to a more readable format.
   *
   * @param c The Command who's time range is to be formatted.
   * @return The given Command's time range in a more readable format.
   */
  private String formatTimeRange(Command c) {
    return " from t=" + ((double) c.getStartTime()) / ((double) tickRate) + "s" +
            " to t=" + ((double) c.getEndTime()) / ((double) tickRate) + "s";
  }
}
