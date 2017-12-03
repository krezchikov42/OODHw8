package cs3500.our_animator.adapters;

import cs3500.our_animator.EasyShape;
import cs3500.our_animator.Point;
import cs3500.animator.model.Posn;
import cs3500.animator.model.Tuple;
import cs3500.animator.model.animationobjects.AnimationObject;
import cs3500.animator.model.command.Command;
import cs3500.animator.view.AnimationObjectVisitor;
import java.awt.Color;
import java.util.List;

//import cs3500.our_animator.Color;

public class ShapeAdapter implements AnimationObject {

  private EasyShape shape;

  public ShapeAdapter(EasyShape s) {
    this.shape = s;
  }

  @Override
  public void addCommand(Command command) {
    // not sure if this is right
    throw new IllegalArgumentException("Shouldn't need to add actions");
  }

  @Override
  public void applyCommands(int tick) {

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
  public cs3500.animator.shapes.ShapeAttributes asRenderItem(int tick) {
    return null;
  }

  @Override
  public AnimationObject copyMe() {
    return new ShapeAdapter(this.shape.clone());
  }

  @Override
  public String acceptVisitor(AnimationObjectVisitor visitor) {
    return null;
  }

  @Override
  public List<Command> getCommandCopies() {
    // make into clones, then make into CommandAdapter
    return this.shape.getActions();
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

  }
}
