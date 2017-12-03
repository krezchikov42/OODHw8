package cs3500.animator.model.command;

import cs3500.animator.model.animationobjects.AnimationObject;
import cs3500.animator.view.CommandVisitor;

/**
 * Represents a {@code Command} for an
 * {@link cs3500.animator.model.animationobjects.AnimationObject}.
 */
public interface Command<T> {

  /**
   * Get the current state of the {@code Command}.
   * An example for a {@link Move} cs3500.animator.command would be: (100.0,100.0)
   * An example for a {@link ChangeColor} cs3500.animator.command would be: (1.0,0.0,0.0)
   *
   * @param tick  The state of the world.
   * @return      A deep copy describing the current state.
   */
  T getState(int tick);

  /**
   * Get the start time of this {@code Command}.
   *
   * @return  The time that this {@code Command} starts.
   */
  int getStartTime();

  /**
   * Get the end time of this {@code Command}.
   *
   * @return  The time that this {@code Command} ends.
   */
  int getEndTime();

  /**
   * Get the type of cs3500.animator.command of this {@code Command}.
   *
   * @return  This {@link CommandType}.
   */
  CommandType getCommandType();

  /**
   * Visit the given {@link AnimationObject} and call the correct method to place this
   * {@code Command} in the way to the {@link AnimationObject}.
   *
   * @param item  The item to add to.
   */
  void visitAnimationObject(AnimationObject item, int tick);

  /**
   * Determine if this {@code Command} conflicts with a given cs3500.animator.command.
   * The two commands conflict if two conditions are true:
   * <ul>
   *   <li>The two commands are of the same type.</li>
   *   <li>The two commands' active times overlap.</li>
   *</ul>
   *
   * @param other Another {@code Command} to compare against.
   * @return If this {@code Command} and that {@code Command} are of the same type and their
   *         durations overlap.
   */
  boolean conflict(Command other);

  /**
   * Determine if this {@code Command} conflicts with the given {@link AbstractCommand}.
   *
   * @param other The {@link AbstractCommand} object to compare with.
   * @return  If the two types of cs3500.animator.command conflict with each other.
   */
  boolean conflictingType(Command other);

  /**
   * Returns a copy of this Command.
   *
   * @return A copy of this Command.
   */
  Command copyMe();

  /**
   * Direct a visitor to the correct method for this Command.
   *
   * @param visitor The visitor dealing with these commands.
   */
  String showVisitor(CommandVisitor visitor);


}