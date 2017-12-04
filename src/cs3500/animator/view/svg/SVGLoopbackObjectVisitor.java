package cs3500.animator.view.svg;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import cs3500.animator.model.command.Command;
import cs3500.animator.model.command.CommandType;
import cs3500.animator.model.animationobjects.AnimationObject;
import cs3500.animator.shapes.Ellipse;
import cs3500.animator.shapes.Rectangle;
import cs3500.animator.model.ShapeAttributes;
import cs3500.animator.view.AnimationObjectVisitor;

/**
 * A visitor for creating SVG tags from {@link AnimationObject}s with loopback.
 */
public class SVGLoopbackObjectVisitor implements AnimationObjectVisitor {

  private final int tickRate;

  /**
   * Constructs a new SVGLoopbackObjectVisitor.
   *
   * @param tickRate The tickrate the animation is running at.
   */
  SVGLoopbackObjectVisitor(int tickRate) {
    this.tickRate = tickRate;
  }

  @Override
  public String visitRectangle(Rectangle rectangle) {

    StringBuilder shapeTag = new StringBuilder();
    ShapeAttributes currRect = rectangle.asRenderItem(rectangle.getAppearanceTime());

    SVGLoopbackCommandVisitor commandVisitor = new SVGLoopbackCommandVisitor(tickRate, "x", "y",
            "width", "height");

    String colorTag = "rgb(" + currRect.getColor().getRed() + "," + currRect.getColor().getGreen()
            + "," + currRect.getColor().getBlue() + ")";

    shapeTag.append("<rect id=\"" + rectangle.getName() + "\" x=\"" + currRect.getPosition().getX()
            + "\" y=\"" + currRect.getPosition().getY() + "\" width=\"" + currRect.getWidth()
            + "\" height=\"" + currRect.getHeight() + "\" fill=\"" + colorTag
            + "\" visibility=\"visible\" >\n");

    Map<CommandType, String> possibleTags = new HashMap<>();

    possibleTags.put(CommandType.MOVE,
            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" " +
                    "attributeName=\"x\"" +
                    " to=\"" + currRect.getPosition().getX() + "\" fill=\"freeze\" />\n\t" +
                    "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" " +
                    "attributeName=\"y\"" +
                    " to=\"" + currRect.getPosition().getY() + "\" fill=\"freeze\" />\n");
    possibleTags.put(CommandType.CHANGECOLOR,
            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" " +
                    "attributeName=\"fill\" to=\"rgb(" + currRect.getColor().getRed() + "," +
                    currRect.getColor().getGreen() + "," + currRect.getColor().getBlue() +
                    ")\" fill=\"freeze\" />\n") ;
    possibleTags.put(CommandType.SCALE,
            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" " +
                    "attributeName=\"width\"" +
                    " to=\"" + currRect.getWidth() + "\" fill=\"freeze\" />\n\t" +
                    "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" " +
                    "attributeName=\"height\"" +
                    " to=\"" + currRect.getHeight() + "\" fill=\"freeze\" />\n");


    Set<CommandType> seenCommands = new HashSet<CommandType>();



    for (Command c : rectangle.getCommandCopies()) {

      shapeTag.append("\t");
      shapeTag.append(c.showVisitor(commandVisitor));
      shapeTag.append("\n");

      seenCommands.add(c.getCommandType());

    }

    for (CommandType cT : seenCommands) {

      shapeTag.append("\t");
      shapeTag.append(possibleTags.get(cT));
    }


    shapeTag.append("\n</rect>");

    return shapeTag.toString();

  }

  @Override
  public String visitEllipse(Ellipse ellipse) {

    StringBuilder shapeTag = new StringBuilder();
    ShapeAttributes currEllipse = ellipse.asRenderItem(ellipse.getAppearanceTime());

    SVGLoopbackCommandVisitor commandVisitor = new SVGLoopbackCommandVisitor(tickRate, "cx", "cy",
            "rx", "ry");

    String colorTag = "rgb(" + currEllipse.getColor().getRed() + "," +
            currEllipse.getColor().getGreen() + "," + currEllipse.getColor().getBlue() + ")";

    shapeTag.append("<ellipse id=\"" + ellipse.getName() + "\" cx=\"" +
            currEllipse.getPosition().getX()
            + "\" cy=\"" + currEllipse.getPosition().getY() + "\" rx=\"" + currEllipse.getWidth()
            + "\" ry=\"" + currEllipse.getHeight() + "\" fill=\"" + colorTag
            + "\" visibility=\"visible\">\n");

    Map<CommandType, String> possibleTags = new HashMap<CommandType, String>();

    possibleTags.put(CommandType.MOVE,
            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" " +
                    "attributeName=\"cx\"" +
                    " to=\"" + currEllipse.getPosition().getX() + "\" fill=\"freeze\" />\n\t" +
                    "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" " +
                    "attributeName=\"cy\"" +
                    " to=\"" + currEllipse.getPosition().getY() + "\" fill=\"freeze\" />\n");
    possibleTags.put(CommandType.CHANGECOLOR,
            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" " +
                    "attributeName=\"fill\" to=\"rgb(" + currEllipse.getColor().getRed() + "," +
                    currEllipse.getColor().getGreen() + "," + currEllipse.getColor().getBlue() +
                    ")\" fill=\"freeze\" />\n") ;
    possibleTags.put(CommandType.SCALE,
            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" " +
                    "attributeName=\"rx\"" +
                    " to=\"" + currEllipse.getWidth() + "\" fill=\"freeze\" />\n\t" +
                    "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" " +
                    "attributeName=\"ry\"" +
                    " to=\"" + currEllipse.getHeight() + "\" fill=\"freeze\" />\n");


    Set<CommandType> seenCommands = new HashSet<CommandType>();


    for (Command c : ellipse.getCommandCopies()) {

      shapeTag.append("\t");
      shapeTag.append(c.showVisitor(commandVisitor));
      shapeTag.append("\n");

      seenCommands.add(c.getCommandType());

    }

    for (CommandType cT : seenCommands) {

      shapeTag.append("\t");
      shapeTag.append(possibleTags.get(cT));
    }

    shapeTag.append("\n</ellipse>");

    return shapeTag.toString();
  }

}
