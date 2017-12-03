package cs3500.our_animator.view;

import java.util.Comparator;
import java.util.List;

import cs3500.our_animator.Action;
import cs3500.our_animator.EasyShape;

/**
 * Represents a view that gives a text representation of an animation.
 */
public class TextView extends AView {




  @Override
  public String getText(List<EasyShape> shapes, List<Action> actions, float rate, int endTime) {
    String ret = "";
    for (EasyShape shape : shapes) {
      ret += shape.getText(rate);
    }
    ret += "\n";

    //yay java 8 stuff
    actions.sort(Comparator.comparing(Action::getStartTime));
    for (Action action : actions) {
      ret += action.getText(rate);
    }
    return ret;
  }
}
