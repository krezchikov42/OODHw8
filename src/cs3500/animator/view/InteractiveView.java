package cs3500.animator.view;

import java.util.Set;
import java.util.function.Consumer;

/**
 * Represents an interactive view for an animation.
 */
public interface InteractiveView extends MultiFrameView {

  /**
   * Set the names of the shapes that can be selected in the animation.
   *
   * @param names The unique names of each object in the animation.
   */
  void setShapeNames(Set<String> names);

  /**
   * Set the speed that the animation is running. This is largely meant to initialize the
   * speed in the view to a value that matches the initial tick rate of the animation.
   *
   * @param tickRate  The rate in ticks per second at which the animation is running.
   */
  void setSpeed(int tickRate);

  /**
   * Set the callback that is used whenever the user changes the speed of the animation.
   *
   * @param c The callback function object to react to changes in speed.
   */
  void setSpeedCallback(Consumer<Integer> c);

  /**
   * Set the callback that is used whenever the user toggles a control button. Control buttons
   * are:
   * <ul>
   *   <li>
   *     Play/Pause
   *   </li>
   *   <li>
   *     Restart
   *   </li>
   *   <li>
   *     Loop
   *   </li>
   *   <li>
   *     Export SVG
   *   </li>
   * </ul>
   *
   * @param c The callback function object to react to any of these controls.
   */
  void setControlCallback(Consumer<String> c);

  /**
   * Set the callback that will respond to a user selecting/deselecting an object in the animation.
   *
   * @param nameCallback  The callback function object to react to any of these controls.
   */
  void setNameCallback(Consumer<String> nameCallback);

}
