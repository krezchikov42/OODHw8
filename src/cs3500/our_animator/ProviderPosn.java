package cs3500.our_animator;

import cs3500.animator.model.Posn;
import java.security.Provider;

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
