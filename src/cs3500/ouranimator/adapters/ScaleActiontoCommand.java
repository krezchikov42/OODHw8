package cs3500.ouranimator.adapters;

import cs3500.animator.model.Tuple;
import cs3500.animator.model.command.Command;
import cs3500.animator.model.command.CommandType;
import cs3500.ouranimator.ProviderTuple;
import cs3500.ouranimator.ScaleAction;

/**
 * Represents an Adapter from Scale Action to Command.
 */
public class ScaleActiontoCommand extends AbstractActiontoCommand<Tuple> {

  private ScaleAction scaleAction;
  private double origX;
  private double origY;
  private double xincr;
  private double yincr;

  /**
   * Creates a new ScaleActiontoCommand.
   *
   * @param scaleAction the action to be adapted.
   */
  public ScaleActiontoCommand(ScaleAction scaleAction) {
    super(scaleAction);
    this.scaleAction = scaleAction;
    this.origX = scaleAction.getOrigX();
    this.origY = scaleAction.getOrigY();
    double targetX = scaleAction.getTargetX();
    double targetY = scaleAction.getTargetY();
    int duration = this.getEndTime() - this.getStartTime();
    xincr = (targetX - origX) / duration;
    yincr = (targetY - origY) / duration;

  }

  @Override
  public Tuple getState(int tick) {
    double newX = origX + xincr * tick;
    double newY = origY + yincr * tick;
    return new ProviderTuple(newY, newX);
  }

  @Override
  public CommandType getCommandType() {
    return CommandType.SCALE;
  }

  @Override
  public Command copyMe() {
    return new ScaleActiontoCommand((ScaleAction) scaleAction.clone());
  }
}