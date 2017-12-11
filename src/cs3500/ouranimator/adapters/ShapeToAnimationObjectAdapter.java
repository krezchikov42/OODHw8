package cs3500.ouranimator.adapters;

import cs3500.animator.model.Posn;
import cs3500.animator.model.ShapeAttributes;
import cs3500.animator.model.Tuple;
import cs3500.animator.model.animationobjects.AnimationObject;
import cs3500.animator.model.command.Command;
import cs3500.animator.shapes.Ellipse;
import cs3500.animator.shapes.Rectangle;
import cs3500.animator.view.AnimationObjectVisitor;
import cs3500.ouranimator.Action;
import cs3500.ouranimator.ColorAction;
import cs3500.ouranimator.EasyShape;
import cs3500.ouranimator.MoveAction;
import cs3500.ouranimator.Point;
import cs3500.ouranimator.ScaleAction;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Addapter from Shape to AnimationObject.
 */

public class ShapeToAnimationObjectAdapter implements Rectangle, Ellipse {

  private EasyShape shape;

  /**
   * Creates a new ShapetoAnimationObject.
   *
   * @param s represents the shape to be adapted.
   */
  public ShapeToAnimationObjectAdapter(EasyShape s) {
    this.shape = s;
  }

  @Override
  public void addCommand(Command command) {
    throw new IllegalArgumentException("Shouldn't need to add actions");
  }

  @Override
  public void applyCommands(int tick) {

    for (Action a : this.shape.getActions()) {
      if (a.isCurrent(tick)) {
        a.applyToShape(tick);
      }
    }
  }

  @Override
  public String getName() {
    return this.shape.getName();
  }

  @Override
  public int getAppearanceTime() {
    return this.shape.getAppearTime();
  }

  @Override
  public int getDisappearanceTime() {
    return this.shape.getHideTime();
  }

  @Override
  public ShapeAttributes asRenderItem(int tick) {
    return new ShapeToAttributesAdapter(this.shape);
  }

  @Override
  public AnimationObject copyMe() {
    return new ShapeToAnimationObjectAdapter(this.shape.clone());
  }

  @Override
  public String acceptVisitor(AnimationObjectVisitor visitor) {
    String ret = null;
    switch (this.shape.getShapeType()) {
      case "rectangle":
        ret = visitor.visitRectangle((Rectangle) this);
        break;
      case "oval":
        ret = visitor.visitEllipse((Ellipse) this);
        break;
      default:
        throw new IllegalArgumentException("Only rectangles and ovals allowed");
    }
    return ret;
  }

  @Override
  public List<Command> getCommandCopies() {
    // make into clones, then make into CommandAdapter
    List<Action> actualActions = this.shape.getActions();
    List<Command> commandCopies = new ArrayList<Command>();

    for (Action a : actualActions) {
      // while (iter.hasNext()) {
      Command c = null;
      if (a instanceof ColorAction) {
        c = new ColorActiontoCommand((ColorAction) a.clone());
      } else if (a instanceof MoveAction) {
        c = new MoveActiontoCommand((MoveAction) a.clone());
      } else {
        c = new ScaleActiontoCommand((ScaleAction) a.clone());
      }
      commandCopies.add(c);
    }

    return commandCopies;
  }

  @Override
  public void applyMove(Posn p) {
    this.shape.setPosition(new Point(p.getX(), p.getY()));
  }

  @Override
  public void applyColor(Color c) {
    this.shape.setColor(new cs3500.ouranimator.Color(c.getRed(), c.getGreen(), c.getBlue()));
  }

  @Override
  public void applyScale(Tuple<Double, Double> scale) {
    this.shape.setWidth(scale.first());
    this.shape.setHeight(scale.second());
  }
}
