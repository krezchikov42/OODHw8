package cs3500.ouranimator.view;

import cs3500.ouranimator.Action;
import cs3500.ouranimator.EasyShape;
import java.util.List;

/**
 * The abstract class for views.
 */
public abstract class AView implements View {


  @Override
  public String getText(List<EasyShape> shapes, List<Action> actions, float rate, int endTime) {
    throw new UnsupportedOperationException(this.getClass() + " Does not support Text View");
  }

  @Override
  public void run(List<EasyShape> shapes) {
    throw new UnsupportedOperationException(this.getClass() + " Does not support Visual View");
  }
}
