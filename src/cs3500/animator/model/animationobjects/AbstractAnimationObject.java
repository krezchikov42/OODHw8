package cs3500.animator.model.animationobjects;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import cs3500.animator.command.Command;
import cs3500.animator.model.ConcretePosn;
import cs3500.animator.model.Posn;

/**
 * An abstract class for the representation of an animation object in our easy animator.
 */
public abstract class   AbstractAnimationObject implements AnimationObject {

  private final String name;
  private final Posn centerLocation;
  private final Color color;
  private final int appearanceTime;
  private final int disappearanceTime;
  CurrentState currentState;
  private final List<Command> commands;
  private final List<List<Command>> commandTimeline;


  /**
   * Constructs a new AbstractAnimationObject.
   *
   * @param name              The name of the AO given to it by the user.
   * @param centerLocation    The location of the center of the object.
   * @param color             The color of the object.
   * @param appearanceTime    The tick at which the object appears onscreen.
   * @param disappearanceTime The tick at which the object disappears.
   * @param commands          A list of Commands to act on this.
   */
  AbstractAnimationObject(String name, Posn centerLocation, Color color, int appearanceTime,
                          int disappearanceTime, List<Command> commands) {

    this.name = name;
    this.centerLocation = centerLocation;
    this.color = color;
    this.appearanceTime = appearanceTime;
    this.disappearanceTime = disappearanceTime;
    this.commandTimeline = new ArrayList<>();

    for (int i = 0; i <= disappearanceTime; i++) {

      commandTimeline.add(new ArrayList<>());

    }

    this.commands = new ArrayList<>();

    for (Command c : commands) {

      this.addCommand(c);

    }

    this.currentState = new CurrentState();
  }

  @Override
  public void addCommand(Command command) {

    for (Command currentCommand : this.commands) {
      if (currentCommand.conflict(command)) {
        throw new IllegalArgumentException("Cannot add Command "
                + command.getCommandType().toString() + " from "
                + Integer.toString(command.getStartTime()) + " to "
                + Integer.toString(command.getEndTime()) + " to the AnimationObject "
                + this.name + "!");
      }
    }

    this.commands.add(command);

    //Expands the command timeline if it cannot hold this new Command.
    if (this.commandTimeline.size() - 1 < command.getEndTime()) {
      for (int i = this.commandTimeline.size(); i <= command.getEndTime(); i++) {

        this.commandTimeline.add(new ArrayList<>());

      }
    }

    /*
    //Adds transformation's existence to each tick where it should exist.
    for (int i = command.getStartTime(); i <= command.getEndTime(); i++) {

      this.commandTimeline.get(i).add(command);

    }

    boolean dominantCommandFound = false;
    boolean anySameTypeFound = false;
    int ind = command.getEndTime() + 1;

    //Checks if this Command should extend into the timeline until it encounters a command of the
    //same kind that end after it.
    while ((!dominantCommandFound) && (ind < commandTimeline.size())) {

      List<Command> currTime = this.commandTimeline.get(ind);

      for (int i = 0; i < currTime.size(); i++) {

        Command c = currTime.get(i);

        if (command.getCommandType() == c.getCommandType()) {

          anySameTypeFound = true;
          if (command.getEndTime() < c.getEndTime()) {

            dominantCommandFound = true;
          } else {

            currTime.remove(c);
            currTime.add(command);
          }

          break;

        }

      }

      if (!anySameTypeFound) {

        currTime.add(command);

      }

      ind++;
      anySameTypeFound = false;

    }
    */

    for (int i = command.getStartTime(); i < commandTimeline.size(); i++) {
      boolean anySameTypeFound = false;

      List<Command> currTime = this.commandTimeline.get(i);

      for (int j = 0; j < currTime.size(); j++) {
        if (currTime.get(j).getCommandType() == command.getCommandType()) {
          if (currTime.get(j).getStartTime() == i) {
            return;
          }
          currTime.set(j, command);
          anySameTypeFound = true;
          break;
        }
      }
      if (!anySameTypeFound) {
        currTime.add(command);
      }
    }
  }

  @Override
  public void applyCommands(int tick) {

    for (Command c : this.commandTimeline.get(tick)) {

      c.visitAnimationObject(this, tick);

    }

  }


  @Override
  public void applyMove(Posn p) {
    currentState.setCurrLocation(p);
  }

  @Override
  public void applyColor(Color c) {
    currentState.setCurrColor(c);
  }

  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Returns a copy of this's center location.
   *
   * @return A copy of the center location Posn for this AnimationObject.
   */
  Posn getCenterLocation() {
    return new ConcretePosn(this.centerLocation.getX(), this.centerLocation.getY());
  }

  /**
   * Returns a copy of this AnimationObject's center location.
   *
   * @return A copy of the center location for this AnimationObject.
   */
  protected Color getColor() {
    return new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
  }

  /**
   * Returns the appearance time of the AnimationObject on the screen.
   *
   * @return The tine this object appears from the screen.
   */
  public int getAppearanceTime() {
    return this.appearanceTime;
  }

  /**
   * Returns the disappearance time of the AnimationObject on the screen.
   *
   * @return The time this object disappears from the screen.
   */
  public int getDisappearanceTime() {
    return this.disappearanceTime;
  }

  /**
   * Returns a copy of all the commands pertaining to this AnimationObject.
   *
   * @return A copy of the commands that act upon this AnimationObject.
   */
  public List<Command> getCommandCopies() {

    List<Command> retList = new ArrayList<>();

    for (Command command : this.commands) {

      retList.add(command.copyMe());

    }

    return retList;

  }

  /**
   * This class is a builder for the current state so that our starting values can remain immutable
   * for restarting.
   */
  protected class CurrentState {

    private Posn currLocation;
    private Color currColor;

    /**
     * Constructs a new CurrentState, setting the values equal to the animation's initial values
     * at first.
     */
    public CurrentState() {
      currLocation = AbstractAnimationObject.this.centerLocation;
      currColor = AbstractAnimationObject.this.color;
    }

    /**
     * Sets the current location of the CurrentState.
     * @param p The new location to set.
     */
    private void setCurrLocation(Posn p) {
      currLocation = p;
    }

    /**
     * Gets the current location of this CurrentState.
     *
     * @return The current location of the CurrentState.
     */
    public Posn getCurrLocation() {
      return new ConcretePosn(currLocation.getX(), currLocation.getY());
    }

    /**
     * Sets the current color of this CurrentState.
     *
     * @param c The new Color to set.
     */
    private void setCurrColor(Color c) {
      currColor = c;
    }

    /**
     * Returns the current color of this CurrentState.
     *
     * @return The color of this CurrentState.
     */
    public Color getCurrColor() {
      return new Color(currColor.getRed(), currColor.getGreen(), currColor.getBlue());
    }
  }

}
