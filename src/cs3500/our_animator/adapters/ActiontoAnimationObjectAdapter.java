package cs3500.our_animator.adapters;

import java.awt.*;
import java.util.List;

import cs3500.animator.model.Posn;
import cs3500.animator.model.Tuple;
import cs3500.animator.model.animationobjects.AnimationObject;
import cs3500.animator.model.command.Command;
import cs3500.animator.view.AnimationObjectVisitor;
import cs3500.our_animator.Action;

/**
 * Adapts from an Action to an Animation Object.
 */
public class ActiontoAnimationObjectAdapter implements AnimationObject{
  private Action action;

  public ActiontoAnimationObjectAdapter(Action action) {
    this.action = action;
  }

  @Override
  public void addCommand(Command command) {
    throw new IllegalArgumentException("Shouldn't need to add actions");

  }

  @Override
  public void applyCommands(int tick) {
    action.applyToShape(tick);

  }

  @Override
  public String getName() {
    return action.getShape().getName();
  }

  @Override
  public int getAppearanceTime() {
    return action.getShape().getAppearTime();
  }

  @Override
  public int getDisappearanceTime() {
    return action.getShape().getHideTime();
  }

  @Override
  public cs3500.animator.shapes.ShapeAttributes asRenderItem(int tick) {
    return null;
  }

  @Override
  public AnimationObject copyMe() {
    return new ActiontoAnimationObjectAdapter(action.clone());
  }

  @Override
  public String acceptVisitor(AnimationObjectVisitor visitor) {
    throw new IllegalArgumentException("We dont't have visitors in our action");
  }

  @Override
  public List<Command> getCommandCopies() {
    throw new IllegalArgumentException("Not sure if we need Commands in our action");
  }

  @Override
  public void applyMove(Posn p) {
    action.getShape().setPosition(new cs3500.our_animator.Point(p.getX(),p.getY()));
  }

  @Override
  public void applyColor(Color c) {
    action.getShape().setColor(new cs3500.our_animator.Color(c.getRed(),c.getBlue(),c.getGreen()));
  }


  //I think it goes height and width but I'm not sure because comments don't explicily say it.
  @Override
  public void applyScale(Tuple<Double, Double> scale) {
    action.getShape().setHeight(scale.first());
    action.getShape().setWidth(scale.second());
  }
}
