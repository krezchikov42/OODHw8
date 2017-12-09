package cs3500.our_animator.adapters;

import cs3500.animator.model.ShapeAttributes;
import cs3500.animator.shapes.Ellipse;
import cs3500.animator.shapes.EllipseAttributes;
import cs3500.animator.shapes.Rectangle;
import cs3500.animator.shapes.RectangleAttributes;
import cs3500.our_animator.Action;
import cs3500.our_animator.ColorAction;
import cs3500.our_animator.MoveAction;
import cs3500.our_animator.ScaleAction;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cs3500.animator.model.Posn;
import cs3500.animator.model.Tuple;
import cs3500.animator.model.animationobjects.AnimationObject;
import cs3500.animator.model.command.Command;
import cs3500.animator.view.AnimationObjectVisitor;
import cs3500.our_animator.EasyShape;
import cs3500.our_animator.Point;

//import cs3500.our_animator.Color;

/**
 * Addapter from Shape to AnimationObject.
 */

public class ShapeToAnimationObjectAdapter implements Rectangle,Ellipse {

  private EasyShape shape;

  /**
   * Creates a new ShapetoAnimationObject.
   * @param s represents the shape to be adapted.
   */
  public ShapeToAnimationObjectAdapter(EasyShape s) {
    this.shape = s;
  }

  @Override
  public void addCommand(Command command) {
    // not sure if this is right
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
      case "rectangle": ret = visitor.visitRectangle( (Rectangle) this); break;
      case "oval": ret = visitor.visitEllipse((Ellipse) this); break;
      default: throw new IllegalArgumentException("Only rectangles and ovals allowed");
    }
    return ret;
  }

  @Override
  public List<Command> getCommandCopies() {
    // make into clones, then make into CommandAdapter
    Iterator<Action> iter = this.shape.getActions().iterator();
    List<Action> actualActions = this.shape.getActions();
    List<Command> commandCopies = new ArrayList<Command>();

    for (Action a: actualActions) {
//    while (iter.hasNext()) {
   //   Action a = iter.next();
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
    this.shape.setColor(new cs3500.our_animator.Color(c.getRed(), c.getGreen(), c.getBlue()));
  }

  @Override
  public void applyScale(Tuple<Double, Double> scale) {
    this.shape.setWidth(scale.first());
    this.shape.setHeight(scale.second());
  }
}
