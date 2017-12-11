package cs3500.animator.shapes;

import cs3500.animator.model.ShapeAttributes;
import cs3500.animator.model.Tuple;
import cs3500.animator.model.animationobjects.AnimationObject;
import cs3500.animator.view.AnimationObjectVisitor;

/**
 * This Rectangle class represents a rectangle to be drawn in our Animator.
 */
public interface Rectangle extends AnimationObject {

  @Override
  void applyScale(Tuple<Double, Double> scale);

  @Override
  ShapeAttributes asRenderItem(int tick);

  @Override
  AnimationObject copyMe();

  @Override
  String acceptVisitor(AnimationObjectVisitor v);
}
