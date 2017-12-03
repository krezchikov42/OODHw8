package cs3500.our_animator.adapters;

import cs3500.animator.model.Posn;
import cs3500.animator.model.command.Command;
import cs3500.animator.model.command.CommandType;

public class MoveActiontoCommand extends AbstractActiontoCommand<Posn> {
  @Override
  public Posn getState(int tick) {
    return null;
  }

  @Override
  public CommandType getCommandType() {
    return null;
  }

  @Override
  public Command copyMe() {
    return null;
  }
}
