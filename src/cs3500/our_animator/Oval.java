package cs3500.animator;



import java.awt.Graphics;
import java.util.List;

/**
 * Represents a 2D oval.
 */
public class Oval extends EasyShape {

  /**
   * Constructs a new oval.
   *
   * @param pos        the position of the oval
   * @param pinHole    the pinhole of the position
   * @param name       the name of the shape
   * @param appearTime the time this shape appears
   * @param hideTime   the time this shape disappears
   * @param color      the color of this shape
   */
  public Oval(float yRadius, float xRadius, Point pos, PinHole pinHole, String name,
              int appearTime, int hideTime, Color color) {
    super(pos, pinHole, name, appearTime, hideTime, color, 2 * xRadius, 2 * yRadius);
    this.shapeType = "oval";
  }

  private Oval(Oval shape, List<Action> a) {
    this((float) shape.getHeight() / 2, (float) shape.getWidth() / 2,
            shape.getPostition().clone(), shape.getPinhole(),
            shape.getName(), shape.getAppearTime(),
            shape.getHideTime(), shape.getColor().clone());
    actions = a;
  }

  @Override
  String sizeToString() {
    return String.format("X radius: %f, Y radius: %f", this.width / 2, this.height / 2);
  }

  @Override
  public String getSVG(float ticksOverSeconds, boolean shouldLoop) {
    // opening ellipse tag
    String svg = String.format("<ellipse id=\"%s\" cx=\"%.2f\" cy=\"%.2f\" rx=\"%.0f\" "
                    + "ry=\"%.0f\" fill=\"rgb(%d,%d,%d)\" visibility=\"hidden\" >\n",
            this.name, this.position.getX(), this.position.getY(), this.width / 2, this.height / 2,
            getColor().getRed255(), getColor().getGreen255(), getColor().getBlue255());

    // Add visibility animation
    String looper = (shouldLoop) ? "base.begin+" : "";
    svg += String.format("<set attributeName=\"visibility\" attributeType=\"CSS\" to=\"visible\""
                    + " begin=\"%s%.2fs\" dur=\"%.2fs\" fill=\"remove\" />\n", looper,
            appearTime / ticksOverSeconds, (hideTime - appearTime) / ticksOverSeconds);

    if (shouldLoop) {
      svg += String.format("<set attributeName=\"cx\" attributeType=\"XML\" to=\"%.2f\" " +
      "begin=\"base.begin\" />\n", this.position.getX());
      svg += String.format("<set attributeName=\"cy\" attributeType=\"XML\" to=\"%.2f\" " +
          "begin=\"base.begin\" />\n", this.position.getY());
      svg += String.format("<set attributeName=\"fill\" attributeType=\"XML\" "
              + "to=\"rgb(%d,%d,%d)\" begin=\"base.begin\" />\n",
          getColor().getRed255(), getColor().getGreen255(), getColor().getBlue255());
    }

    // Add all other animations
    for (Action a : this.actions) {
      svg += a.getSVG(ticksOverSeconds, shouldLoop) + "\n";
    }

    // closing rect tag
    svg += "</ellipse>";
    return svg;
  }

  @Override
  public void draw(Graphics g) {
    g.setColor(new java.awt.Color((float) this.color.getRed(), (float) this.color.getGreen(),
            (float) this.color.getBlue()));
    g.fillOval((int) this.position.getX(), (int) this.position.getY(),
            (int) this.width, (int) this.height);
  }

  @Override
  public EasyShape clone() {
    return new Oval(this, actions);
  }
}
