package cs3500.animator.view.visual;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import javax.swing.*;
import javax.swing.event.ChangeEvent;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.model.ShapeAttributes;
import cs3500.animator.view.InteractiveView;

/**
 * Represents an interactive view.
 */
public class InteractiveSwingView extends JFrame implements InteractiveView {
  private AnimationPanel animationPanel; // The animation panel that will render the shapes.
  private JPanel namePanel; // A panel of check-boxes selecting/deselecting objects
  private Consumer<String> nameCallback; // A callback to accept when names are toggled.
  private Consumer<String> controlCallback; // A callback to react to control commands.
  private Consumer<Integer> speedCallback; // A callback to react to changes in speed.

  private JButton playToggleButton;
  private JButton loopToggleButton;
  private JSlider speedSlider;

  /**
   * Constructs an {@code InteractiveSwingView} object.
   */
  public InteractiveSwingView() {
    super();
    this.setTitle("Interactive woahh!!");
    this.setSize(1000, 1000);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    // Set layout of the view
    this.setLayout(new BorderLayout());

    // Animation panel
    animationPanel = new AnimationPanel();
    animationPanel.setPreferredSize(new Dimension(800, 800));
    JScrollPane scrollPane = new JScrollPane(animationPanel);
    this.add(scrollPane, BorderLayout.CENTER);

    // Name panel
    namePanel = new JPanel();
    //namePanel.setPreferredSize(new Dimension(100, 800));
    namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));
    JScrollPane nameScrollPane = new JScrollPane(namePanel);
    nameScrollPane.setPreferredSize(new Dimension(200, 800));
    this.add(nameScrollPane, BorderLayout.WEST);

    // Control panel
    JPanel controlPanel = new JPanel();
    controlPanel.setLayout(new FlowLayout());
    this.add(controlPanel, BorderLayout.SOUTH);

    // Control panel buttons
    playToggleButton = new JButton("Play");
    playToggleButton.addActionListener((ActionEvent e) -> {
      if (controlCallback != null) {
        controlCallback.accept("PlayToggle");
        if (playToggleButton.getText().equals("Play")) {
          playToggleButton.setText("Pause");
        } else {
          playToggleButton.setText("Play");
        }
      }
    });
    controlPanel.add(playToggleButton);

    JButton restartButton = new JButton("Restart");
    restartButton.addActionListener((ActionEvent e) -> {
      if (controlCallback != null) {
        controlCallback.accept("Restart");
      }
    });
    controlPanel.add(restartButton);

    loopToggleButton = new JButton("Loop");
    loopToggleButton.addActionListener((ActionEvent e) -> {
      if (controlCallback != null) {
        controlCallback.accept("LoopToggle");
        if (loopToggleButton.getText().equals("Loop")) {
          loopToggleButton.setText("No Loop");
        } else {
          loopToggleButton.setText("Loop");
        }
      }
    });
    controlPanel.add(loopToggleButton);

    JButton makeSVGButton = new JButton("MakeSVG");
    makeSVGButton.addActionListener((ActionEvent e) -> {
      if (controlCallback != null) {
        controlCallback.accept("MakeSVG");
      }
    });
    controlPanel.add(makeSVGButton);

    speedSlider = new JSlider(1000, 8000);
    speedSlider.addChangeListener((ChangeEvent e) -> {
      if (speedCallback != null) {
        speedCallback.accept(speedSlider.getValue());
      }
    });
    controlPanel.add(speedSlider);

    this.pack();

    nameCallback = null;
    controlCallback = null;
    speedCallback = null;
  }

  @Override
  public void show(List<ShapeAttributes> shapes) {
    animationPanel.setShapes(shapes);
    this.repaint();
  }

  @Override
  public void howShouldIBeShown(AnimationController controller) {
    controller.showInteractiveView(this);
  }

  @Override
  public void setShapeNames(Set<String> names) {

    JCheckBox[] checkBoxes = new JCheckBox[names.size()];

    int count = 0;

    for (String name : names) {

      checkBoxes[count] = new JCheckBox(name);
      checkBoxes[count].setSelected(true);
      checkBoxes[count].setActionCommand(name);
      int finalCount = count;
      checkBoxes[count].addItemListener((ItemEvent e) -> {
        if (this.nameCallback != null) {
          nameCallback.accept(checkBoxes[finalCount].getActionCommand());
        }
      });
      namePanel.add(checkBoxes[count]);

      count++;

    }

    this.setVisible(true);

  }

  @Override
  public void setNameCallback(Consumer<String> nameCallback) {
    this.nameCallback = nameCallback;
  }

  @Override
  public void setSpeed(int speed) {
    this.speedSlider.setValue(speed);
  }

  @Override
  public void setSpeedCallback(Consumer<Integer> c) {
    this.speedCallback = c;
  }

  @Override
  public void setControlCallback(Consumer<String> c) {
    this.controlCallback = c;
  }
}
