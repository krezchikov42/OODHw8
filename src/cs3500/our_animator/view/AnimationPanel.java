package cs3500.animator.view;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import cs3500.animator.EasyShape;

class AnimationPanel extends JPanel {

  List<EasyShape> shapes;

  public AnimationPanel(List<EasyShape> shapes) {
    this.shapes = shapes;

    setSize(800, 600);
    setMinimumSize(new Dimension(800, 600));
    setVisible(true);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    modelToPaintFunctionCalls(g);
  }

  private void modelToPaintFunctionCalls(Graphics g) {
    for (EasyShape s : shapes  ) {
      s.draw(g);
    }
  }

  public void setShapes(List<EasyShape> shapes) {
    this.shapes = shapes;
  }

  /*@Override
  public void actionPerformed(ActionEvent e) {
    this.revalidate();
    this.repaint();
  }*/
}
