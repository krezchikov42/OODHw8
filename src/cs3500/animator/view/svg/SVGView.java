package cs3500.animator.view.svg;

import java.util.List;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.model.animationobjects.AnimationObject;
import cs3500.animator.view.AnimationObjectVisitor;
import cs3500.animator.view.SingleTimeView;

/**
 * Represents an SVG view for an animator program.
 */
public class SVGView implements SingleTimeView {

  @Override
  public void howShouldIBeShown(AnimationController controller) {
    controller.produceSingleTime(this);
  }

  @Override
  public String produce(List<AnimationObject> animations, int tickRate, int totalAnimationLength) {
    StringBuilder svg = new StringBuilder();

    AnimationObjectVisitor svgObjectVisitor = new SVGObjectVisitor(tickRate);

    // Make the header for the svg
    svg.append("<svg width=\"1500\" height=\"1500\" version=\"1.1\" " +
            "xmlns=\"http://www.w3.org/2000/svg\">");

    for (AnimationObject animation : animations) {
      svg.append(animation.acceptVisitor(svgObjectVisitor)).append("\n");
    }

    // Close the svg tag
    svg.append("</svg>");

    return svg.toString();
  }

}
