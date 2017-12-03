package cs3500.animator;

/**
 * Interface for objects that can return a text representation.
 */
public interface Textable {

  /**
   * Returns the text representation.
   *
   * @param ticksOverSeconds the rate of the animation.
   * @return the text representation.
   */
  String getText(float ticksOverSeconds);

  /**
   * Returns the SVG representation.
   *
   * @param ticksOverSeconds is the rate of the animation.
   * @param looping represents if the svg loops.
   * @return the SVG representaiton.
   */
  String getSVG(float ticksOverSeconds, boolean looping);
}
