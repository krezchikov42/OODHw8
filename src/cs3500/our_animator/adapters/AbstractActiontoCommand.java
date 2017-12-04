package cs3500.our_animator.adapters;

import cs3500.animator.model.animationobjects.AnimationObject;
import cs3500.animator.model.command.Command;
import cs3500.animator.model.command.CommandType;
import cs3500.animator.view.CommandVisitor;
import cs3500.our_animator.Action;

public abstract class AbstractActiontoCommand<T> implements Command<T> {
  private Action action;

  public AbstractActiontoCommand(Action action) {
    this.action = action;
  }

  @Override
  public abstract T getState(int tick);


  @Override
  public int getStartTime() {
    return action.getStartTime();
  }

  @Override
  public int getEndTime() {
    return action.getEndTime();
  }

  @Override
  public abstract CommandType getCommandType();

  @Override
  public void visitAnimationObject(AnimationObject item, int tick) {
    action.applyToShape(tick);
  }

  @Override
  public boolean conflict(Command other) {
    if(conflictingType(other)){
      //checks if this command starts or ends during the other command
      if((this.getStartTime() < other.getEndTime() && this.getStartTime() > other.getStartTime())
          || (this.getEndTime() < other.getEndTime() && this.getEndTime() > other.getStartTime())){
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean conflictingType(Command other) {
    //why do they pass in an AbstractCommand?????
    return this.getCommandType().equals(other.getCommandType());
  }

  @Override
  public abstract Command copyMe();

  @Override
  public String showVisitor(CommandVisitor visitor) {
    // not sure what this should do
    return null;
  }
}