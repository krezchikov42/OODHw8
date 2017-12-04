package cs3500.animator.view.svg;

import java.awt.*;

import cs3500.animator.model.command.Command;
import cs3500.animator.model.Posn;
import cs3500.animator.model.Tuple;
import cs3500.animator.view.CommandVisitor;

/**
 * A visitor that produces SVG tags for {@link Command}s with loopback.
 */
public class SVGLoopbackCommandVisitor implements CommandVisitor {

  private final int tickRate;
  private final String xVal;
  private final String yVal;
  private final String widthVal;
  private final String heightVal;

  /**
   * Constructs a new SVGCommandVisitor to help construct SVGView.
   *
   * @param tickRate  The tickRate of the SVGAnimation.
   * @param xVal      A String name for the x-axis location.
   * @param yVal      A String name for the y-axis location.
   * @param widthVal  A String name for the width attribute.
   * @param heightVal A String name for the height attribute.
   */
  SVGLoopbackCommandVisitor(int tickRate, String xVal, String yVal,
                            String widthVal, String heightVal) {
    this.tickRate = tickRate;
    this.xVal = xVal;
    this.yVal = yVal;
    this.widthVal = widthVal;
    this.heightVal = heightVal;
  }

  @Override
  public String visitMove(Command<Posn> c) {
    Posn start = c.getState(c.getStartTime());
    Posn end = c.getState(c.getEndTime());

    StringBuilder moveTags = new StringBuilder();

    // To do the x movement
    moveTags.append(beginningNonsense(c, this.xVal, "" + start.getX(),
            "" + end.getX(), "xml"));
    moveTags.append("\n\t");
    moveTags.append(beginningNonsense(c, this.yVal, "" + start.getY(),
            "" + end.getY(), "xml"));

    return moveTags.toString();
  }

  @Override
  public String visitChangeColor(Command<Color> c) {
    Color start = c.getState(c.getStartTime());
    Color end = c.getState(c.getEndTime());

    StringBuilder moveTags = new StringBuilder();

    String fromRGB = "rgb(" + start.getRed() + "," + start.getGreen() + "," + start.getBlue() + ")";
    String toRGB = "rgb(" + end.getRed() + "," + end.getGreen() + "," + end.getBlue() + ")";


    // To do the x movement
    moveTags.append(beginningNonsense(c, "fill", fromRGB, toRGB, "xml"));
    return moveTags.toString();

  }

  @Override
  public String visitScale(Command<Tuple<Double, Double>> c) {

    Tuple<Double, Double> start = c.getState(c.getStartTime());
    Tuple<Double, Double> end = c.getState(c.getEndTime());

    StringBuilder moveTags = new StringBuilder();

    // To do the x movement
    moveTags.append(beginningNonsense(c, this.widthVal, "" + start.first(),
            "" + end.first(), "xml"));
    moveTags.append("\n\t");
    moveTags.append(beginningNonsense(c, this.heightVal, "" + start.second(),
            "" + end.second(), "xml"));

    return moveTags.toString();

  }

  /**
   * Creates an SVGTag for the passed in command.
   *
   * @param c             The command.
   * @param attributeName The attribute to be changed.
   * @param from          The start state.
   * @param to            The end state.
   * @param attributeType The attributeType SVG tag (ie. "xml").
   * @return A properly formatted SVGTag given the parameters.
   */
  private String beginningNonsense(Command c, String attributeName, String from, String to,
                                   String attributeType) {
    int beginMillis = 1000 * c.getStartTime() / tickRate;
    int endMillis = 1000 * c.getEndTime() / tickRate;
    StringBuilder tags = new StringBuilder();


    tags.append("<animate attributeType=\"" + attributeType + "\" begin=\"base.begin+")
            .append(beginMillis).append("ms\" ");
    tags.append("dur=\"").append(endMillis - beginMillis).append("ms\" ");
    tags.append("attributeName=\"").append(attributeName).append("\" ");
    tags.append("from=\"").append(from).append("\" ");
    tags.append("to=\"").append(to).append("\" ");
    tags.append("fill=\"").append("freeze").append("\"");
    tags.append("/>\n");

    return tags.toString();

  }


}
