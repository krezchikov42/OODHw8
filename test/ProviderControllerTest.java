
import static org.junit.Assert.*;

import cs3500.animator.view.SimpleAnimationView;
import cs3500.animator.view.svg.SVGView;
import cs3500.animator.view.text.VerboseView;
import cs3500.animator.view.visual.InteractiveSwingView;
import cs3500.animator.view.visual.SwingView;
import cs3500.our_animator.Action;
import cs3500.our_animator.Color;
import cs3500.our_animator.EasyShape.PinHole;
import cs3500.our_animator.MoveAction;
import cs3500.our_animator.Point;
import cs3500.our_animator.Rectangle;
import cs3500.our_animator.controller.AnimationController;
import cs3500.our_animator.controller.Controller;
import cs3500.our_animator.controller.ProviderController;
import cs3500.our_animator.model.EasyAnimatorModel;
import cs3500.our_animator.model.EasyAnimatorOperations;
import cs3500.our_animator.view.HybridView;
import cs3500.our_animator.view.View;

import org.junit.Test;

public class ProviderControllerTest {

  //tests hybrid view
  @Test
  public void testRunHybridProvider() {
    EasyAnimatorOperations m = new EasyAnimatorModel();

    Rectangle r = new Rectangle(20, 20, new Point(100, 100), PinHole.Top,
        "rectangle", 0, 10, new Color(0.5, 0.5, 0.5));

    m.addShape(r);

    Action a = new MoveAction(r, new Point(100,100), new Point(300, 300), 0, 10);

    m.addAction(a);

    SimpleAnimationView v = new InteractiveSwingView();

    Controller c = new ProviderController(m, v, 1000);

    c.runViewWithVisualComponent();
  }

  //tests hybrid view
  @Test
  public void testRunHybrid() {
    EasyAnimatorOperations m = new EasyAnimatorModel();

    Rectangle r = new Rectangle(20, 20, new Point(100, 100), PinHole.Top,
            "rectangle", 0, 10, new Color(0.5, 0.5, 0.5));

    m.addShape(r);

    Action a = new MoveAction(r, new Point(100,100), new Point(300, 300), 0, 10);

    m.addAction(a);

    View v = new HybridView();

    Controller c = new AnimationController(m, v, 10);

    c.runViewWithVisualComponent();
  }

  //tests visual view
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

  //tests text view
  @Test
  public void testText(){
    EasyAnimatorOperations m = new EasyAnimatorModel();

    Rectangle r = new Rectangle(20, 20, new Point(100, 100), PinHole.Top,
            "rectangle", 0, 100, new Color(0.5, 0.5, 0.5));

    Action a = new MoveAction(r, new Point(100,100), new Point(300, 300), 0, 10);
    m.addAction(a);
    m.addShape(r);
    SimpleAnimationView  v = new VerboseView();
    Controller c = new ProviderController(m,v,10);
    assertEquals("Name: rectangle\n" +
            "Type: rectangle\n" +
            "top: (200.0,200.0), Width: 20.000000, Height: 20.000000, Color: 0.50,0.50,0.50\n" +
            "Appears at t=0.00\n" +
            "Disappears at t=10.00\n" +
            "rectangle moves from (100.00,100.00) to (300.00,300.00) from t=0.00 to t=1.00\n",
            c.getTextFromTextualView());


  }

  //tests SVG
  @Test
  public void testSvg(){
    EasyAnimatorOperations m = new EasyAnimatorModel();

    Rectangle r = new Rectangle(20, 20, new Point(100, 100), PinHole.Top,
            "rectangle", 0, 100, new Color(0.5, 0.5, 0.5));

    Action a = new MoveAction(r, new Point(100,100), new Point(300, 300), 0, 10);
    m.addAction(a);
    m.addShape(r);
    SimpleAnimationView  v = new SVGView();
    Controller c = new ProviderController(m,v,10);
    assertEquals("<svg width=\"700\" height=\"500\" version=\"1.1\"\n" +
            "     xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "<rect id=\"rectangle\" x=\"100.00\" y=\"100.00\" width=\"20.00\" height=\"20.00\" " +
            "fill=\"rgb(127,127,127)\" visibility=\"hidden\" >\n" +
            "<set attributeName=\"visibility\" attributeType=\"CSS\" to=\"visible\" begin=\"0.00s\"" +
            " dur=\"10.00s\" fill=\"remove\" />\n" +
            "<animate attributeType=\"xml\" begin=\"0.00s\" dur=\"1.00s\" attributeName=\"x\" " +
            "from=\"100.00\" to=\"300.00\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"0.00s\" dur=\"1.00s\" attributeName=\"y\"" +
            " from=\"100.00\" to=\"300.00\" fill=\"freeze\" />\n" +
            "</rect>\n" +
            "\n" +
            "</svg>",c.getTextFromTextualView());
  }


}