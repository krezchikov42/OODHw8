package cs3500.animator.shapes;

import cs3500.animator.model.ShapeAttributes;
import cs3500.animator.view.ShapeAttributesVisitor;

public interface RectangleAttributes extends ShapeAttributes {

    @Override
    void drawMe(ShapeAttributesVisitor view);
}
