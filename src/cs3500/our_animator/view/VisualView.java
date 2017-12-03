package cs3500.our_animator.view;


import java.awt.*;
import java.util.List;

import javax.swing.*;

import cs3500.our_animator.EasyShape;

public class VisualView extends AView {


  boolean window;
  JPanel animator = null;

  @Override
  public void run(List<EasyShape> shapes) {
    int time = 0;

    //makes sure the constructor only happens once
    if (!window) {
      JFrame frame = new JFrame();
      animator = new AnimationPanel(shapes);

      frame.setSize(1500, 1000);
      frame.setMinimumSize(new Dimension(1500, 1000));
      frame.add((JPanel) animator);
      frame.setVisible(true);
      frame.setLayout(new BorderLayout());
      frame.pack();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      window = true;
    }
    animator.revalidate();
    animator.repaint();
  }
}

