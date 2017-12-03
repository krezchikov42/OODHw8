package cs3500.animator.view;

import javax.swing.*;

/**
 * A view for our program that can be displayed when an error occurs while using the program.
 */
public class ErrorView extends JFrame {

  /**
   * Constructs a new {@code ErrorView} for displaying errors to the user.
   *
   * @param message The message to be shown by this ErrorView.
   */
  public ErrorView(String message) {

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JOptionPane.showMessageDialog(this, message, "Alert",
            JOptionPane.WARNING_MESSAGE);
  }
}
