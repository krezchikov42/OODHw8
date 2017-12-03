package cs3500.our_animator.adapters;

import java.awt.*;

import cs3500.animator.model.command.Command;
import cs3500.animator.model.command.CommandType;
import cs3500.animator.view.CommandVisitor;

import cs3500.our_animator.ColorAction;

public class ColorActiontoCommand extends AbstractActiontoCommand<Color> {
  private ColorAction colorAction;
  private cs3500.our_animator.Color startColor;
  private cs3500.our_animator.Color endColor;
  private double redIncrement;
  private double blueIncrement;
  private double greenIncrement;


  public ColorActiontoCommand(ColorAction colorAction) {
    super(colorAction);
    this.colorAction = colorAction;
    startColor = colorAction.getStartColor();
    endColor = colorAction.getEndColor();

    setIncrements();
  }

  private void setIncrements() {
    int duration = this.getEndTime() - this.getStartTime();
    this.redIncrement = Math.min(1, (this.endColor.getRed() - this.startColor.getRed()) / duration);
    this.blueIncrement = Math.min(1, (this.endColor.getBlue() - this.startColor.getBlue())
            / duration);
    this.greenIncrement =
            Math.min(1, (this.endColor.getGreen() - this.startColor.getGreen()) / duration);
  }

  @Override
  public Color getState(int tick) {
    double newRed = Math.min(1, (this.startColor.getRed()) + this.redIncrement * tick);
    double newBlue = Math.min(1, (this.endColor.getBlue()) +
            this.blueIncrement * tick);
    double newGreen = Math.min(1,
            (this.startColor.getGreen()) + this.greenIncrement * tick);
    newRed = Math.max(0, newRed);
    newGreen = Math.max(0, newGreen);
    newBlue = Math.max(0, newBlue);
    return new Color((float)newRed,(float)newGreen,(float)newBlue);

  }


  @Override
  public CommandType getCommandType() {
    return CommandType.CHANGECOLOR;
  }


  @Override
  public Command copyMe() {
    return new ColorActiontoCommand((ColorAction)colorAction.clone());
  }

  @Override
  public String showVisitor(CommandVisitor visitor) {
    return null;
  }
}
