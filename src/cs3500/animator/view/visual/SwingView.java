package cs3500.animator.view.visual;


import java.awt.*;
import java.util.List;

import javax.swing.*;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.model.ShapeAttributes;
import cs3500.animator.view.MultiFrameView;

/**
 * This class provides a way to view our animation cs3500.animator.model through Java's Swing GUI
 * framework.
 */
public class SwingView extends JFrame implements MultiFrameView {
  private final AnimationPanel animationPanel;

  /**
   * Constructs a new SwingView and begins initializing the values of our JFrame.
   */
  public SwingView() {
    super("Simple Animator");

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());

    // Set the panel for the animation
    animationPanel = new AnimationPanel();
    animationPanel.setPreferredSize(new Dimension(800, 800));
    this.add(animationPanel);

    // Set the pane for scrolling
    JScrollPane scrollPane = new JScrollPane(animationPanel);
    this.add(scrollPane);

    this.setVisible(true);

    this.pack();
  }

  @Override
  public void howShouldIBeShown(AnimationController controller) {
    controller.showMultiFrame(this);
  }

  @Override
  public void show(List<ShapeAttributes> shapes) {
    this.animationPanel.setShapes(shapes);
    this.repaint();
  }
}
