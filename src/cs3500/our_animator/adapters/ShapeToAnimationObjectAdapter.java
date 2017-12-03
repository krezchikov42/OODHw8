package cs3500.our_animator.adapters;

import cs3500.animator.model.ShapeAttributes;
import cs3500.our_animator.Action;
import java.awt.*;
import java.util.ArrayList;
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

public class ShapeToAnimationObjectAdapter implements AnimationObject {

  private EasyShape shape;

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
    return null;
  }

  @Override
  public List<Command> getCommandCopies() {
    // make into clones, then make into CommandAdapter
    List<Action> actualActions = this.shape.getActions();
    List<Command> commandCopies = new ArrayList<Command>();

    for (Action a: actualActions) {
      commandCopies.add(new ActionToCommandAdapter(a.clone()));
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
