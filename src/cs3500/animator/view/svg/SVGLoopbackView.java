package cs3500.animator.view.svg;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.model.animationobjects.AnimationObject;
import cs3500.animator.view.AnimationObjectVisitor;
import cs3500.animator.view.SingleTimeView;
import java.util.List;

/**
 * A view that produces an SVG with loopback.
 */
public class SVGLoopbackView implements SingleTimeView {

  @Override
  public void howShouldIBeShown(AnimationController controller) {
    controller.produceSingleTime(this);
  }

  @Override
  public String produce(List<AnimationObject> animations, int tickRate, int totalAnimationLength) {
    StringBuilder svg = new StringBuilder();

    AnimationObjectVisitor svgLbObjectVisitor = new SVGLoopbackObjectVisitor(tickRate);

    // Make the header for the svg
    svg.append("<svg width=\"1500\" height=\"1500\" version=\"1.1\" " +
        "xmlns=\"http://www.w3.org/2000/svg\">\n\n");

    svg.append("<rect>\n");

    svg.append("\t<animate id=\"base\" begin=\"0;base.end\" dur=\"" +
        (int) (((double) totalAnimationLength / tickRate) * 1000) +
        "ms\" attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>\n");

    svg.append("</rect>\n\n");

    for (AnimationObject animation : animations) {
      svg.append(animation.acceptVisitor(svgLbObjectVisitor)).append("\n");
    }

    // Close the svg tag
    svg.append("</svg>");

    return svg.toString();
  }

}
