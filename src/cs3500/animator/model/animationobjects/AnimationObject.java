package cs3500.animator.model.animationobjects;

import cs3500.animator.model.command.Command;
import cs3500.animator.shapes.ShapeAttributes;
import cs3500.animator.view.AnimationObjectVisitor;
import java.util.List;

/**
 * This interface represents an object onscreen for our easy animation program.
 */
public interface AnimationObject extends Transformable {

  /**
   * Adds the given Command to this object's list of Commands.
   *
   * @param command The tranformation to be added.
   */
  void addCommand(Command command);

  /**
   * Applies every Command in this AnimationObject's list according to the current Tick.
   *
   * @param tick The current tick of the animation when this function is called.
   */
  void applyCommands(int tick);

  /**
   * Returns the given name of this animation object.
   *
   * @return The name of this AnimationObject.
   */
  String getName();

  /**
   * Gives the appearance time onscreen of this AnimationObject.
   *
   * @return The time this AnimationObject appears onscreen.
   */
  int getAppearanceTime();

  /**
   * Gives the disappearance time onscreen of this AnimationObject.
   *
   * @return The time this AnimationObject disappears from the screen.
   */
  int getDisappearanceTime();

  /**
   * Produce a {@link ShapeAttributes} that describes this {@code AnimationItem} at the given time.
   *
   * @param tick  The given time this {@code AnimationItem} is in.
   * @return      A descriptive object of this item in order to be rendered.
   */
  ShapeAttributes asRenderItem(int tick);

  /**
   * Returns a deep copy of this AnimationObject.
   *
   * @return A copy of this AnimationObject.
   */
  AnimationObject copyMe();

  /**
   * Accept a visitor of an {@code AnimationObject} that ultimately will return a String describing
   * this {@code AnimationObject}.
   *
   * @param visitor The visitor to accept.
   * @return  A String describing this object decided on by the visitor.
   */
  String acceptVisitor(AnimationObjectVisitor visitor);

  /**
   * Returns a copy of all the commands pertaining to this AnimationObject.
   *
   * @return A copy of the commands that act upon this AnimationObject.
   */
  List<Command> getCommandCopies();

}
