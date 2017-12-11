package cs3500.animator.shapes;

import cs3500.animator.model.ShapeAttributes;
import cs3500.animator.model.Tuple;
import cs3500.animator.model.animationobjects.AnimationObject;
import cs3500.animator.view.AnimationObjectVisitor;

/**
 * This class represents an Ellipse shape in our Animator.
 */
public interface Ellipse extends AnimationObject {

  @Override
  void applyScale(Tuple<Double, Double> scale);

  @Override
  ShapeAttributes asRenderItem(int tick);

  @Override
  AnimationObject copyMe();

  @Override
  String acceptVisitor(AnimationObjectVisitor v);
}
