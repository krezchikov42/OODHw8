package cs3500.ouranimator.view;

import cs3500.ouranimator.Action;
import cs3500.ouranimator.EasyShape;
import java.util.Comparator;
import java.util.List;

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
