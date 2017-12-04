package cs3500.our_animator.controller;

import static org.junit.Assert.*;

import cs3500.animator.view.SimpleAnimationView;
import cs3500.animator.view.visual.InteractiveSwingView;
import cs3500.animator.view.visual.SwingView;
import cs3500.our_animator.Action;
import cs3500.our_animator.Color;
import cs3500.our_animator.EasyShape.PinHole;
import cs3500.our_animator.MoveAction;
import cs3500.our_animator.Point;
import cs3500.our_animator.Rectangle;
import cs3500.our_animator.model.EasyAnimatorModel;
import cs3500.our_animator.model.EasyAnimatorOperations;
import org.junit.Test;

public class ProviderControllerTest {

  @Test
  public void testRunHybrid() {
    EasyAnimatorOperations m = new EasyAnimatorModel();

    Rectangle r = new Rectangle(20, 20, new Point(100, 100), PinHole.Top,
        "rectangle", 0, 100, new Color(0.5, 0.5, 0.5));

    m.addShape(r);

    Action a = new MoveAction(r, new Point(100,100), new Point(300, 300), 0, 10);

    m.addAction(a);

    SimpleAnimationView v = new InteractiveSwingView();

    Controller c = new ProviderController(m, v, 1);

    c.runViewWithVisualComponent();
  }

  @Test
  public void testRunVisual() {
    EasyAnimatorOperations m = new EasyAnimatorModel();

    Rectangle r = new Rectangle(20, 20, new Point(100, 100), PinHole.Top,
        "rectangle", 0, 100, new Color(0.5, 0.5, 0.5));

    Action a = new MoveAction(r, new Point(100,100), new Point(300, 300), 0, 10);
    m.addAction(a);
    m.addShape(r);



    SimpleAnimationView v = new SwingView();

    Controller c = new ProviderController(m, v, 10);

    c.runViewWithVisualComponent();
  }
}