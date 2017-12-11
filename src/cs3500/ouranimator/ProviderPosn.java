package cs3500.ouranimator;

import cs3500.animator.model.Posn;

public class ProviderPosn implements Posn {

  double x;
  double y;

  public ProviderPosn(double x, double y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public double getX() {
    return x;
  }

  @Override
  public double getY() {
    return y;
  }

  public String toString() {
    return String.format("(%0.2f, %0.2f)", getX(), getY());
  }
}
