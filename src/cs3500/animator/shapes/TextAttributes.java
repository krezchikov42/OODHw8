package cs3500.animator.shapes;

import cs3500.animator.model.ShapeAttributes;
import cs3500.animator.view.ShapeAttributesVisitor;

public interface TextAttributes extends ShapeAttributes {

    /**
     * Allows access to the fontSize.
     *
     * @return  The font size of this text.
     */
    int getFontSize();

    /**
     * Allows access to the actual string itself.
     *
     * @return The contents String of this TextAttributes.
     */
    String getContents();

    @Override
    void drawMe(ShapeAttributesVisitor view);
}
