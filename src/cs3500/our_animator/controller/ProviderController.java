package cs3500.our_animator.controller;

import cs3500.animator.model.ShapeAttributes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import java.util.Set;
import java.util.function.Consumer;
import javax.swing.*;

import cs3500.animator.model.animationobjects.AnimationObject;
import cs3500.animator.view.MultiFrameView;
import cs3500.animator.view.SimpleAnimationView;
import cs3500.animator.view.SingleTimeView;
import cs3500.animator.view.svg.SVGView;
import cs3500.animator.view.text.VerboseView;
import cs3500.our_animator.Action;
import cs3500.our_animator.EasyShape;
import cs3500.our_animator.adapters.ShapeToAttributesAdapter;
import cs3500.our_animator.adapters.ShapeToAnimationObjectAdapter;
import cs3500.our_animator.model.EasyAnimatorOperations;
import cs3500.animator.view.InteractiveView;
import cs3500.our_animator.view.TextView;

public class ProviderController implements Controller {
  private EasyAnimatorOperations model;
  private List<EasyShape> initialModelShapes;
  private SimpleAnimationView view;
  private Timer timer;
  private List<String> shapeNames;
  private boolean loop;

  private int currentTime;
  private float rate;
  private boolean running;

  public ProviderController(EasyAnimatorOperations model, SimpleAnimationView view, float rate) {
    this.model = model;
    this.initialModelShapes = model.getShapesCopy();
    this.view = view;
    this.shapeNames = new ArrayList<String>();

    this.currentTime = 0;
    this.rate = rate;
    this.running = false;
    this.loop = false;
  }

  @Override
  public void runViewWithVisualComponent() {

    if (this.view instanceof InteractiveView) {
      this.initializeCallbacks();
      // Because witout this setVisible was never being called
      Set<String> names = new HashSet<String>();
      for (EasyShape s: this.model.getShapes()) {
        names.add(s.getName());
      }
      ((InteractiveView) this.view).setShapeNames(names);

    }

    timer = new javax.swing.Timer((int) this.rate / 100, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (running) {
          model.updateAnimation(currentTime);

          //converts our shapes to their AnimationObjects
          List<AnimationObject> shapes = new ArrayList<>();
          List<ShapeAttributes> attributes = new ArrayList<>();
          for(EasyShape s: makeInvisble(model.getShapes())){
            AnimationObject ao = new ShapeToAnimationObjectAdapter(s);
            shapes.add(ao);
            attributes.add(ao.asRenderItem(currentTime));
          }

          //doesn't work because shapes is not of type shape Attributes
          ((MultiFrameView) view).show(attributes);

          //makes the time loop or not
          if (loop && currentTime == model.getEndTime()) {
            currentTime = 0;
          } else {
            currentTime++;
          }
        }
      }
    });
    timer.start();
    while (true) {}
  }

  private void exportToSVG() {
    JFrame frame = new JFrame("Save to an SVG file");

    // prompt the user for the name of the output file
    String name = JOptionPane.showInputDialog(frame, "Name of the output file");

    saveSVGWithOutFileName(name);
  }

  private void saveSVGWithOutFileName(String fileName) {
    try {
      FileWriter write = new FileWriter(fileName, true);
      PrintWriter print = new PrintWriter(write);
      print.print(this.getSVG());
      print.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("Can't write to file");
    }
  }

  private String getSVG() {
    setActionShapeLinks(initialModelShapes);

    String text = "<svg width=\"700\" height=\"500\" version=\"1.1\"\n"
        + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "<animate id=\"base\" begin=\"0;base.end\" dur=\"" + model.getEndTime() / (rate/1000)
        + "s\"" +
        " attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>\n";

    for (EasyShape s : makeInvisble(initialModelShapes)) {
      text += s.getSVG(rate / 1000,this.loop);
    }
    text += "\n</svg>";


    List<EasyShape> copy = new ArrayList<EasyShape>(); // = new ArrayList<>(initialModelShapes);
    for (EasyShape s : initialModelShapes) {
      copy.add(s.clone());
    }
    //copy.forEach(EasyShape::clone);

    this.initialModelShapes = copy;
    setActionShapeLinks(this.model.getShapes());

    return text;
  }

  @Override
  public String getTextFromTextualView() {
    //last minute

    if(view instanceof VerboseView){
      return new TextView().getText(model.getShapes(),model.getActions(),rate,model.getEndTime());
    }
    else if(view instanceof SVGView){
      return new cs3500.our_animator.view.SVGView().getText(model.getShapes(), model.getActions(),rate,model.getEndTime());
    }
    else{
      throw new IllegalArgumentException("Unsupported view");
    }
    /*
    //convert our actions to Animation Objects
    List<AnimationObject> animationObjects = new ArrayList<>();
    for(EasyShape shape: model.getShapes()){
      animationObjects.add(new ShapeToAnimationObjectAdapter(shape));
     // System.out.print("nahhhhhhhhhhhhhhh");
    }

    //we need to cast our float rate to an int
    return ((SingleTimeView) view).produce(animationObjects,(int) rate,model.getEndTime());
    */
  }

  @Override
  public boolean isLoop() {
    return loop;
  }

  @Override
  public float getRate() {
    return rate;
  }

  @Override
  public boolean isRunning() {
    return running;
  }

  //makes shapes invisible that the user has selected by removing them from the list
  private List<EasyShape> makeInvisble(List<EasyShape> shapes) {
    List<EasyShape> ret = new ArrayList<>();
    for (EasyShape shape : shapes) {
      ret.add(shape.clone());
    }

    ret.removeIf(easyShape -> {
      boolean match = false;
      for (String s : shapeNames) {
        match |= easyShape.getName().equals(s);
      }
      return match;
    });

    return ret;
  }

  private void setActionShapeLinks(List<EasyShape> shapes) {
    for (EasyShape s : shapes) {
      for (Action a : s.getActions()) {
        a.setShape(s);
      }
    }
  }

  private void rewindToStart() {
    this.running = false;
    this.currentTime = 0;
    // Make new clone

    setActionShapeLinks(initialModelShapes);

    //converts our shapes to their AnimationObjects
    List<AnimationObject> shapes = new ArrayList<>();
    List<ShapeAttributes> attributes = new ArrayList<>();
    for(EasyShape s: makeInvisble(model.getShapes())){
      AnimationObject ao = new ShapeToAnimationObjectAdapter(s);
      shapes.add(ao);
      attributes.add(ao.asRenderItem(currentTime));
    }
    ((MultiFrameView) view).show(attributes);
    this.model.setShapes(initialModelShapes);

    List<EasyShape> copy = new ArrayList<EasyShape>(); // = new ArrayList<>(initialModelShapes);
    for (EasyShape s : initialModelShapes) {
      copy.add(s.clone());
    }

    this.initialModelShapes = copy;
    this.running = true;
  }

  //makes all the shapes in the animation visible
  private void makeVisible() {
    shapeNames = new ArrayList<>();
  }

  private void initializeCallbacks() {

    Consumer<String> controlCallBack = (s) -> {
      switch (s) {
        case "PlayToggle": this.running = !this.running; break;
        case "Restart": this.rewindToStart(); break;
        case "LoopToggle": this.loop = !this.loop; break;
        case "MakeSVG": this.exportToSVG(); break;
        default: System.out.print("nah bro");
      }
    };

    Consumer<Integer> speedCallBack = (n) -> {
      this.rate = n;
      timer.setDelay((int) (rate) / 100);
      ((InteractiveView) this.view).setSpeed((int) this.rate);
    };

    Consumer<String> nameCallBack = (s) -> {
      if (shapeNames.contains(s)) {
        shapeNames.remove(s);
      }
      else {
        shapeNames.add(s);
      }
    };

    ((InteractiveView) this.view).setControlCallback(controlCallBack);
    ((InteractiveView) this.view).setSpeedCallback(speedCallBack);
    ((InteractiveView) this.view).setNameCallback(nameCallBack);

  }

}
