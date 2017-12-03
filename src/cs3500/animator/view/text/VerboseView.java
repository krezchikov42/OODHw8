package cs3500.animator.view.text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import cs3500.animator.command.Command;
import cs3500.animator.controller.AnimationController;
import cs3500.animator.model.ConcreteTuple;
import cs3500.animator.model.Tuple;
import cs3500.animator.model.animationobjects.AnimationObject;
import cs3500.animator.view.AnimationObjectVisitor;
import cs3500.animator.view.CommandVisitor;
import cs3500.animator.view.SingleTimeView;

/**
 * Represents a verbose view that outputs the entire timeline of the program in one large block
 * of text.
 */
public class VerboseView implements SingleTimeView {

  @Override
  public void howShouldIBeShown(AnimationController controller) {
    controller.produceSingleTime(this);
  }

  @Override
  public String produce(List<AnimationObject> animations, int tickRate, int totalAnimationLength) {
    StringBuilder result = new StringBuilder();
    AnimationObjectVisitor aoVisitor = new VerboseObjectVisitor(tickRate);

    if (animations.size() > 0) {
      result.append("Shapes:\n");

      for (AnimationObject shape : animations) {
        result.append(shape.acceptVisitor(aoVisitor)).append("\n");
      }
    }

    List<Tuple<Integer, String>> timeline = new ArrayList<>();

    for (AnimationObject animation : animations) {
      CommandVisitor cVisitor = new VerboseCommandVisitor(animation, tickRate);

      for (Command c : animation.getCommandCopies()) {
        timeline.add(new ConcreteTuple<>(c.getStartTime(), c.showVisitor(cVisitor)));
      }
    }

    Collections.sort(timeline, Comparator.comparing(Tuple::first));

    Iterator<Tuple<Integer, String>> iter = timeline.iterator();

    while (iter.hasNext()) {
      result.append(iter.next().second());

      if (iter.hasNext()) {
        result.append("\n");
      }
    }

    return result.toString();
  }
}
