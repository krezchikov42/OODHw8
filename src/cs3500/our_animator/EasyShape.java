package cs3500.animator;



import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a 2D shape.
 */
public abstract class EasyShape implements Textable, CanBeCloned<EasyShape> {


  /**
   * The location on the image where the position is tied to.
   */
  public enum PinHole {
    Top("top"), TopLeft("top-left"), Left("left"), BottomLeft("bottom-left"), Bottom("bottom"),
    BottomRight("bottom-right"), Right("right"), TopRight("top-right");

    private final String name;

    PinHole(final String value) {
      name = value;
    }

    public String getName() {
      return name;
    }
  }

  protected Point position;
  protected PinHole pinhole;
  protected String name;
  protected int appearTime;
  protected int hideTime;
  protected double width;
  protected double height;
  protected Color color;
  static String shapeType;
  protected List<Action> actions;

  /**
   * Creates a new EasyShape.
   *
   * @param pos        the position of the shape.
   * @param pinHole    the pinhole of the shape.
   * @param name       the name of the shape.
   * @param appearTime when the shape appears.
   * @param hideTime   when the shape dissapears.
   * @param color      the color of the shape.
   * @param width      the widht
   */
  protected EasyShape(Point pos, PinHole pinHole, String name,
                      int appearTime, int hideTime, Color color, double width, double height) {
    this.position = pos;
    this.pinhole = pinHole;
    this.name = name;
    this.appearTime = appearTime;
    this.hideTime = hideTime;
    this.color = color;
    actions = new ArrayList<>();
    this.width = width;
    this.height = height;
  }

  /**
   * Converts the size of this shape to an understandable and descriptive string. For example, a
   * circle might say "radius: 20". To be used in order to print out the entire state of an
   * animation.
   *
   * @return The size of this shape as a string
   */
  abstract String sizeToString();

  /**
   * Returns the dimensions of this shape as an ArrayList in order specified in the documentatoion
   * of the shapes that extends this class. This exists, along with getDimensionsAsList, to provide
   * an easy portal in and out of the shape for cs3500.animator.Action classes to modify a shape
   * without
   * knowing it's types of dimensions or number of dimensions.
   *
   * @return A list of the dimensions of this shape
   */


  /**
   * Adds an Action to a shape.
   */
  public void addAction(Action action) {
    checkLegAct(action);
    actions.add(action);
  }

  //checks if Action are legal.
  private void checkLegAct(Action action) {
    for (Action legact : actions) {
      int legstart = legact.getStartTime();
      int legend = legact.getEndTime();
      int start = action.getStartTime();
      int end = action.getEndTime();
      //checks to see if the given action starts and ends while the shape is alive
      if (!Usefull.checkBetweenInc(start, appearTime, hideTime)
              || !Usefull.checkBetweenInc(end, appearTime, hideTime)) {
        throw new IllegalArgumentException("cs3500.Animation.cs3500.Animation happens" +
                " while shape has not appeard");
      }
      if (legact.getClass().equals(action.getClass())) {

        if (Usefull.checkBetweenInc(start, legstart, legend)
                || Usefull.checkBetweenInc(end, legstart, legend)) {
          //something is wrong with this
          //throw new IllegalArgumentException("2 Animations happening at the same time.");
        }

      }
    }
  }

  //////////////////////////////////
  ///  GETTER FUNCTIONS   //////////
  //////////////////////////////////

  public PinHole getPinhole() {
    return this.pinhole;
  }

  public int getAppearTime() {
    return this.appearTime;
  }

  public int getHideTime() {
    return this.hideTime;
  }

  public Color getColor() {
    return this.color;
  }

  public String getName() {
    return this.name;
  }

  public String getShapeType() {
    return this.shapeType;
  }

  public Point getPostition() {
    return this.position;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public double getWidth() {
    return width;
  }

  public double getHeight() {
    return height;
  }

  public List<Action> getActions() {
    return actions;
  }

  /**
   * Creates a readable english representation of this shape.
   *
   * @return A string representation over multiple lines
   */


  @Override

  public String getText(float ticksOverSeconds) {
    String s = String.format("Name: %s\n"
                    + "Type: %s\n"
                    + "%s: (200.0,200.0), %s, Color: %s\n"
                    + "Appears at t=%.2f\n"
                    + "Disappears at t=%.2f", this.getName(),
            this.getShapeType(),
            this.pinhole.getName(),
            this.sizeToString(), this.getColor().toString(),
            this.getAppearTime() / ticksOverSeconds,
            this.getHideTime() / ticksOverSeconds);
    return s;
  }

  public abstract String getSVG(float ticksOverSeconds, boolean shouldLoop);

  public void setPosition(Point position) {
    this.position = position;
  }

  public void setAppearTime(int appearTime) {
    this.appearTime = appearTime;
  }

  public void setHideTime(int hideTime) {
    this.hideTime = hideTime;
  }

  public void setWidth(double width) {
    this.width = width;
  }

  public void setHeight(double height) {
    this.height = height;
  }

  /**
   * Draws the shape on the graphics.
   *
   * @param g the
   */
  public abstract void draw(Graphics g);

  public abstract EasyShape clone();
}