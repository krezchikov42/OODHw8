package cs3500.our_animator.adapters;

import cs3500.animator.model.Posn;
import cs3500.animator.model.command.Command;
import cs3500.animator.model.command.CommandType;
import cs3500.our_animator.Action;
import cs3500.our_animator.MoveAction;
import cs3500.our_animator.Point;
import cs3500.our_animator.ProviderPosn;

public class MoveActiontoCommand extends AbstractActiontoCommand<Posn> {
  MoveAction moveAction;
  private Point endPos;
  private Point startPos;
  private double dx;
  private double dy;

  public MoveActiontoCommand( MoveAction moveAction) {
    super(moveAction);
    this.moveAction = moveAction;
    endPos = moveAction.getEndPos();
    startPos = moveAction.getStartPos();
    int duration = this.getEndTime() - this.getStartTime();
    dx = (endPos.getX() - startPos.getX()) / duration;
    dy = (endPos.getY() - startPos.getY()) / duration;

  }

  @Override
  public Posn getState(int tick) {
    double timeElapsed = tick - this.getStartTime();
    double moveDX = timeElapsed * dx;
    double moveDY = timeElapsed * dy;;
    double newX = this.startPos.getX() + moveDX;
    double newY = this.startPos.getY() + moveDY;
    return new ProviderPosn(newX,newY);
  }

  @Override
  public CommandType getCommandType() {
    return CommandType.MOVE;
  }

  @Override
  public Command copyMe() {
    return new MoveActiontoCommand((MoveAction)moveAction.clone());
  }
}
