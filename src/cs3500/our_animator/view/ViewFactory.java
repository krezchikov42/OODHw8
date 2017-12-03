package cs3500.animator.view;

/**
 * Represents a factory that will make a view.
 */
public class ViewFactory {


  /**
   * Constructs a view.
   *
   * @param s specifier for which type of view to create
   * @return the view
   */
  public View create(String s) {
    if (s.equals("text")) {
      return new TextView();
    } else if (s.equals("svg")) {
      //not done
      return new SVGView();
    } else if (s.equals("visual")) {
      //not done
      return new VisualView();
    } else if (s.equals("interactive")) {
      return new HybridView();
    } else {
      throw new IllegalArgumentException("Not Valid View for Factory");
    }

  }
}
