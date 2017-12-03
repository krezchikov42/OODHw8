package cs3500.animator.model;

import cs3500.animator.Action;
import cs3500.animator.Color;
import cs3500.animator.ColorAction;
import cs3500.animator.EasyShape;
import cs3500.animator.MoveAction;
import cs3500.animator.Oval;
import cs3500.animator.Point;
import cs3500.animator.Rectangle;
import cs3500.animator.ScaleAction;
import cs3500.animator.util.TweenModelBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Implements the cs3500.animator.model.EasyAnimatorOperations interface and models an Animation
 * over a specified time.
 */
public class EasyAnimatorModel implements EasyAnimatorOperations {

  private List<Action> actions;
  private List<EasyShape> shapes;
  private int endTime;


  /**
   * Constructs a instance of this class with a given time period and an initial list of actions and
   * shapes.
   */
  public EasyAnimatorModel() {

    this.actions = new ArrayList<>();
    this.shapes = new ArrayList<>();

  }

  private EasyAnimatorModel(List<Action> actions, List<EasyShape> shapes) {
    this.actions = actions;
    this.shapes = shapes;
  }

  @Override
  public void updateAnimation(int time) {

    ArrayList<EasyShape> newShapes = new ArrayList<>();


    for (Action a : this.actions) {
      if (a.isCurrent(time)) {
        a.applyToShape(time);
        //newShapes.add(a.applyToShape(time));
      }
    }
    //return new EasyAnimatorModel(actions,newShapes);
  }

  @Override
  public boolean animationOver() {
    //not sure what to do about this
    return false; //this.time >= this.endTime;
  }

  @Override
  public void addAction(Action a) {
    this.actions.add(a);
  }

  @Override
  public void addShape(EasyShape s) {
    this.shapes.add(s);
    //Finds the shape with the max hide time and gets it's hide time and sets it to end time
    endTime = Collections.max(shapes, Comparator.comparing(EasyShape::getHideTime)).getHideTime();
  }

  @Override
  public String getTextDescription() {
    String desc = "Shapes\n";
    for (EasyShape s : shapes) {
      desc += s.toString();
      desc += "\n";
    }

    for (Action a : actions) {
      desc += a.toString();
      desc += "\n";
    }

    return desc;
  }

  @Override
  public ArrayList<EasyShape> getShapes() {
    ArrayList<EasyShape> ret = new ArrayList<>();
    for (EasyShape s : shapes) {
      ret.add(s);
    }

    ret.sort(Comparator.comparing(EasyShape::getAppearTime));

    return ret;
  }

  @Override
  public ArrayList<Action> getActions() {
    ArrayList<Action> ret = new ArrayList<>();
    for (Action s : actions) {
      ret.add(s);
    }
    return ret;
  }

  /**
   * Returns a copy of the list of shapes in this model.
   *
   * @return the shapes
   */
  public ArrayList<EasyShape> getShapesCopy() {
    ArrayList<EasyShape> ret = new ArrayList<>();
    for (EasyShape s : shapes) {
      ret.add(s.clone());
    }
    return ret;
  }

  @Override
  public void setShapes(List<EasyShape> shapes) {
    this.shapes = shapes;
  }

  @Override
  public int getEndTime() {
    return endTime;
  }

  public static final class Builder implements TweenModelBuilder<EasyAnimatorOperations> {

    EasyAnimatorModel model;

    public Builder() {
      this.model = new EasyAnimatorModel();
    }

    /**
     * Find the shape in model with the given name, else throw an exception.
     *
     * @param name The name of the model to be found
     * @return The shape in the builder's model with the given name
     * @throws Exception If the shape isn't found
     */
    private EasyShape getShapeForName(String name) throws IllegalArgumentException {
      for (EasyShape s : this.model.getShapes()) {
        if (s.getName().equals(name)) {
          return s;
        }
      }
      throw new IllegalArgumentException("Shape not found under name given in Action declaration");
    }

    @Override
    public TweenModelBuilder<EasyAnimatorOperations> addOval(String name, float cx, float cy,
                                                             float xRadius, float yRadius,
                                                             float red, float green, float blue,
                                                             int startOfLife,
                                                             int endOfLife) {
      Oval o = new Oval(yRadius, xRadius, new Point( cx,  cy), EasyShape.PinHole.Top,
              name, startOfLife, endOfLife, new Color((double) red, (double) green, (double) blue));
      this.model.addShape(o);
      return this;
    }

    @Override
    public TweenModelBuilder<EasyAnimatorOperations> addRectangle(String name, float lx, float ly,
                                                                  float width, float height,
                                                                  float red, float green,
                                                                  float blue, int startOfLife,
                                                                  int endOfLife) {
      Rectangle r = new Rectangle(width, height, new Point(lx,  ly),
              EasyShape.PinHole.BottomLeft,
              name, startOfLife, endOfLife, new Color((double) red, (double) green, (double) blue));
      this.model.addShape(r);
      return this;
    }

    @Override
    public TweenModelBuilder<EasyAnimatorOperations> addMove(String name, float moveFromX,
                                                             float moveFromY, float moveToX,
                                                             float moveToY, int startTime,
                                                             int endTime) {

      EasyShape s = this.getShapeForName(name);

      Action a = new MoveAction(s, new Point(moveFromX, moveFromY),
              new Point(moveToX, moveToY), startTime, endTime);
      this.model.addAction(a);
      return this;
    }

    @Override
    public TweenModelBuilder<EasyAnimatorOperations> addColorChange(String name, float oldR,
                                                                    float oldG, float oldB,
                                                                    float newR, float newG,
                                                                    float newB, int startTime,
                                                                    int endTime) {

      EasyShape s = this.getShapeForName(name);

      Action a = new ColorAction(s, new Color((double) oldR, (double) oldG, (double) oldB),
              new Color((double) newR, (double) newG, (double) newB), startTime, endTime);
      this.model.addAction(a);
      return this;
    }

    @Override
    public TweenModelBuilder<EasyAnimatorOperations> addScaleToChange(String name, float fromSx,
                                                                      float fromSy,
                                                                      float toSx, float toSy,
                                                                      int startTime, int endTime) {

      EasyShape s = this.getShapeForName(name);
      Action a = new ScaleAction(s, fromSx, fromSy, toSx, toSy, startTime, endTime);
      this.model.addAction(a);
      return this;
    }

    @Override
    public EasyAnimatorOperations build() {
      return this.model;
    }
  }
}
