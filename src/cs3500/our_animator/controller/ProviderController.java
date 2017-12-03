package cs3500.our_animator.controller;

import cs3500.animator.model.ShapeAttributes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import cs3500.animator.model.animationobjects.AnimationObject;
import cs3500.animator.view.MultiFrameView;
import cs3500.animator.view.SimpleAnimationView;
import cs3500.animator.view.SingleTimeView;
import cs3500.our_animator.Action;
import cs3500.our_animator.EasyShape;
import cs3500.our_animator.adapters.ActiontoAnimationObjectAdapter;
import cs3500.our_animator.adapters.ShapeToAttributesAdapter;
import cs3500.our_animator.adapters.ShapeToAnimationObjectAdapter;
import cs3500.our_animator.model.EasyAnimatorOperations;

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
    this.running = true;

    timer = new javax.swing.Timer((int) (1000.0f / this.rate), new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (running) {
          model.updateAnimation(currentTime);

          //converts our shapes to their AnimationObjects
          List<AnimationObject> shapes = new ArrayList<>();
          List<ShapeAttributes> attributes = new ArrayList<>();
          for(EasyShape s: makeInvisble(model.getShapes())){
            shapes.add(new ShapeToAnimationObjectAdapter(s));
            attributes.add(new ShapeToAttributesAdapter(s));
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
  }

  @Override
  public String getTextFromTextualView() {

    //convert our actions to Animation Objects
    List<AnimationObject> animationObjects = new ArrayList<>();
    for(Action action: model.getActions()){
      animationObjects.add(new ActiontoAnimationObjectAdapter(action));
    }

    //we need to cast our float rate to an int
    return ((SingleTimeView) view).produce(animationObjects,(int) rate,model.getEndTime());
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

  //makes all the shapes in the animation visible
  private void makeVisible() {
    shapeNames = new ArrayList<>();
  }

  private void initializeCallbacks() {



  }

}
