package cs3500.our_animator;

import cs3500.animator.model.Tuple;

public class ProviderTuple<X, Y> implements Tuple {

  private X first;
  private Y second;

  public ProviderTuple(X first, Y second) {
    this.first = first;
    this.second = second;
  }

  @Override
  public X first() {
    return first;
  }

  @Override
  public Y second() {
    return second;
  }
}
