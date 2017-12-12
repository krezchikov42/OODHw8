package cs3500.ouranimator.view;

import cs3500.ouranimator.Action;
import cs3500.ouranimator.EasyShape;
import java.awt.Color;
import java.util.List;

/**
 * Represents a SVG view of our animation.
 */
public class SVGView extends AView {

  Color bgColor = new Color(255, 255, 255);


  public void setBgColor(Color bgColor) {
    this.bgColor = bgColor;
  }

  /**
   * Returns a String containing an SVG representation of the animation.
   *
   * @return an svg representation of the animation.
   */
  public String getText(List<EasyShape> shapes, List<Action> actions, float rate, int endTime) {
    String text = "<svg width=\"700\" height=\"500\" version=\"1.1\"\n"
        + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
        + String.format("<rect width=\"700\" height=\"500\" style=\"fill:rgb(%d, %d, %d)\" />",
                        this.bgColor.getRed(), this.bgColor.getGreen(), this.bgColor.getBlue());

    for (EasyShape s : shapes) {
      text += s.getSVG(rate, false);
    }
    text += "\n</svg>";

    return text;
  }
}