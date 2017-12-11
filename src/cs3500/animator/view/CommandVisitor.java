package cs3500.animator.view;

import cs3500.animator.model.Posn;
import cs3500.animator.model.Tuple;
import cs3500.animator.model.command.Command;
import java.awt.Color;

/**
 * This interface represents a visitor that works with Commands for String-based views.
 */
public interface CommandVisitor {

  /**
   * Visits a Move Command and properly formats it as a string.
   *
   * @param c The Move Command to be formatted.
   * @return The properly formatted Move Command.
   */
  String visitMove(Command<Posn> c);

  /**
   * Visits a ChangeColor Command and properly formats it as a string.
   *
   * @param c The ChangeColor Command to be formatted.
   * @return The properly formatted ChangeColor Command.
   */
  String visitChangeColor(Command<Color> c);

  /**
   * Visits a Scale Command and properly formats it as a string.
   *
   * @param c The Scale Command to be formatted.
   * @return The properly formatted Scale Command.
   */
  String visitScale(Command<Tuple<Double, Double>> c);
}
