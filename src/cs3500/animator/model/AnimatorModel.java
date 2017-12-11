package cs3500.animator.model;

import cs3500.animator.model.animationobjects.AnimationObject;
import cs3500.animator.model.command.Command;
import java.util.List;

/**
 * Represents a cs3500.animator.model of a simple animator. This animator will maintain the state of
 * cs3500.animator.shapes in the world. The cs3500.animator.model receives prompts to create objects
 * and how they should update. The cs3500.animator.model can then print out a printout of the state
 * of the objects.
 */
public interface AnimatorModel {

  /**
   * Create a {@link AnimationObject} that matches the given description and add it to the state of
   * the world.
   */
  void addAnimationObject(AnimationObject item);

  /**
   * Retroactively add the given cs3500.animator.command to the item with the given name.
   *
   * @param name The name of the {@link AnimationObject} to add to.
   * @param command The cs3500.animator.command to be added to the {@link AnimationObject}.
   */
  void addCommandToItem(String name, Command command);

  /**
   * Produce the world as a list of {@link ShapeAttributes}s to be rendered.
   *
   * @param tick The tick of the world to produce.
   * @return A list of render-ready items.
   */
  List<ShapeAttributes> toRender(int tick);

  /**
   * Produce the total length of the animation. This would generally be the disappearance time of
   * the last {@link AnimationObject} visible on screen.
   *
   * @return A number describing the length of the animation in ticks.
   */
  int getAnimationLength();

  /**
   * This function returns a deep copy of every AnimationObject and its Commands.
   *
   * @return A list of the AnimationObjects to be displayed.
   */
  List<AnimationObject> copyAllAnimationObjects();

}
