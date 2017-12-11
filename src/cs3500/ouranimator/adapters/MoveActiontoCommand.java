package cs3500.ouranimator.adapters;

import cs3500.animator.model.Posn;
import cs3500.animator.model.command.Command;
import cs3500.animator.model.command.CommandType;
import cs3500.ouranimator.MoveAction;
import cs3500.ouranimator.Point;
import cs3500.ouranimator.ProviderPosn;

/**
 * An adapter from Move action to Command.
 */
public class MoveActiontoCommand extends AbstractActiontoCommand<Posn> {

  private MoveAction moveAction;
  private Point startPos;
  private double dx;
  private double dy;

  /**
   * Creates a MoveActiontoCommand.
   *
   * @param moveAction the MoveAction to be adapted.
   */
  public MoveActiontoCommand(MoveAction moveAction) {
    super(moveAction);
    this.moveAction = moveAction;
    Point endPos = moveAction.getEndPos();
    startPos = moveAction.getStartPos();
    int duration = this.getEndTime() - this.getStartTime();
    dx = (endPos.getX() - startPos.getX()) / duration;
    dy = (endPos.getY() - startPos.getY()) / duration;

  }

  @Override
  public Posn getState(int tick) {
    double timeElapsed = tick - this.getStartTime();
    double moveDX = timeElapsed * dx;
    double moveDY = timeElapsed * dy;
    double newX = this.startPos.getX() + moveDX;
    double newY = this.startPos.getY() + moveDY;
    return new ProviderPosn(newX, newY);
  }

  @Override
  public CommandType getCommandType() {
    return CommandType.MOVE;
  }

  @Override
  public Command copyMe() {
    return new MoveActiontoCommand((MoveAction) moveAction.clone());
  }
}