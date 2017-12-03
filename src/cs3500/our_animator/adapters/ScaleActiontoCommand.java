package cs3500.our_animator.adapters;

import cs3500.animator.model.Tuple;
import cs3500.animator.model.command.Command;
import cs3500.animator.model.command.CommandType;
import cs3500.our_animator.Action;
import cs3500.our_animator.ProviderTuple;
import cs3500.our_animator.ScaleAction;

public class ScaleActiontoCommand extends AbstractActiontoCommand<Tuple> {
  ScaleAction scaleAction;
  private double origX;
  private double origY;
  private double targetX;
  private double targetY;
  private double xincr;
  private double yincr;

  public ScaleActiontoCommand( ScaleAction scaleAction) {
    super(scaleAction);
    this.scaleAction = scaleAction;
    this.origX = scaleAction.getOrigX();
    this.origY = scaleAction.getOrigY();
    this.targetX = scaleAction.getTargetX();
    this.targetY = scaleAction.getTargetY();
    int duration = this.getEndTime() - this.getStartTime();
    xincr = (targetX - origX) / duration;
    yincr = (targetY - origY) / duration;

  }

  @Override
  public Tuple getState(int tick) {
    double newX = origX + xincr * tick;
    double newY = origY + yincr * tick;
    return new ProviderTuple(newY,newX);
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
