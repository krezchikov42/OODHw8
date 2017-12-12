package cs3500.ouranimator.view;


import cs3500.ouranimator.Action;
import cs3500.ouranimator.EasyShape;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Hashtable;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;

/**
 * Represents an interactive view in which the user can view the animation or export it to SVG.
 * Supports pausing, resuming, restarting, changing speed with a slider, selecting certain shapes
 * that should not be viewed or exported.
 */
public class HybridView extends AView {

  private boolean window;
  private AnimationPanel animator = null;
  ActionListener listener = null;

  JTextField textField = null;
  JTextField colorField = null;
  public JCheckBox loopCheckBox = null;

  Color bgColor = new Color(255, 255, 255);


  /**
   * Returns a String containing an SVG representation of the animation.
   *
   * @return an svg representation of the animation.
   */
  @Override
  public String getText(List<EasyShape> shapes, List<Action> actions, float rate, int endTime) {
    System.out.print("getting text");
    String text = "<svg width=\"700\" height=\"500\" version=\"1.1\"\n"
        + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
        + String.format("<rect width=\"700\" height=\"500\" style=\"fill:rgb(%d, %d, %d)\" />",
            this.bgColor.getRed(), this.bgColor.getGreen(), this.bgColor.getBlue())
        + "<rect>\n" +
        "   <animate id=\"base\" begin=\"0;base.end\" dur=\"" + endTime / rate + "s\"" +
        " attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>\n" +
        "</rect>";

    for (EasyShape s : shapes) {
      text += s.getSVG(rate, loopCheckBox.isSelected());
    }
    text += "\n</svg>";

    return text;
  }

  public void setListener(ActionListener l) {
    this.listener = l;
  }

  /**
   * Extracts the text from the shape-exclusion text field.
   *
   * @return The value of the field
   */
  public String getTextFromTextField() {
    String t = this.textField.getText();
    this.textField.setText("");
    return t;
  }

  /**
   * Gets the text from the color box; Also sets background color.
   * @return the number from the box.
   */
  public int getIntFromColorField(){
    int ret = Integer.parseInt(this.colorField.getText());
    animator.setBackground(new Color(ret));
    this.colorField.setText("");
    this.bgColor = new Color(ret);
    return ret;

  }

  @Override
  public void run(List<EasyShape> shapes) {
    int time = 0;

    //makes sure the constructor only happens once
    if (!window) {
      JFrame frame = new JFrame();
      animator = new AnimationPanel(shapes);

      frame.setSize(1000, 700);
      frame.setMinimumSize(new Dimension(1000, 700));
      frame.add((JPanel) animator, BorderLayout.CENTER);
      // add other buttons and functionalities
      this.addButtons(frame);
      this.addSliders(frame);
      this.addShapeSelectionPanel(frame);

      frame.setVisible(true);
      frame.pack();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      window = true;
    }
    //animator.revalidate();
    animator.setShapes(shapes);
    animator.repaint();
  }

  /**
   * Adds the play, start, and pause button to the main JFrame.
   *
   * @param frame the JFrame to add the buttons to
   */
  private void addButtons(JFrame frame) {

    JPanel buttonsPanels = new JPanel();
    buttonsPanels.setBackground(Color.lightGray);

    JButton playButton = new JButton("Play/Resume");
    playButton.addActionListener(this.listener);
    JButton pauseButton = new JButton("Pause");
    pauseButton.addActionListener(this.listener);
    JButton restartButton = new JButton("Restart");
    restartButton.addActionListener(this.listener);

    //mycode that adds the color field
    colorField = new JTextField(20);

    JButton goButton = new JButton("Set Color");
    goButton.addActionListener(this.listener);

    buttonsPanels.add(pauseButton);
    buttonsPanels.add(playButton);
    buttonsPanels.add(restartButton);
    buttonsPanels.add(colorField);
    buttonsPanels.add(goButton);

    frame.add(buttonsPanels, BorderLayout.PAGE_END);
  }

  /**
   * Adds the speed variation slider to the top of the given frame.
   *
   * @param frame The frame that is added to
   */
  private void addSliders(JFrame frame) {

    JPanel sliderPanel = new JPanel();
    sliderPanel.setLayout(new GridLayout(1, 2));

    ////// Speed Slider
    JSlider ticksPerSecond = new JSlider(JSlider.HORIZONTAL,
        1, 50, 15);
    ticksPerSecond.addChangeListener((ChangeListener) this.listener);

    Hashtable speedLabels = new Hashtable();
    speedLabels.put(1, new JLabel("slow"));
    speedLabels.put(50, new JLabel("fast"));

    ticksPerSecond.setLabelTable(speedLabels);
    ticksPerSecond.setMajorTickSpacing(20);
    ticksPerSecond.setMinorTickSpacing(5);
    ticksPerSecond.setPaintTicks(true);
    ticksPerSecond.setPaintLabels(true);
    ticksPerSecond.setName("speed");

    ////// Scrubbing Slider
    JSlider scrubbingSlider = new JSlider(JSlider.HORIZONTAL, 0, 10000, 0);
    scrubbingSlider.addChangeListener((ChangeListener) this.listener);

    Hashtable scrubbingLabels = new Hashtable();
    scrubbingLabels.put(0, new JLabel("start"));
    scrubbingLabels.put(10000, new JLabel("end"));

    scrubbingSlider.setLabelTable(scrubbingLabels);
    scrubbingSlider.setPaintLabels(true);
    scrubbingSlider.setName("scrubbing");

    sliderPanel.add(ticksPerSecond);
    sliderPanel.add(scrubbingSlider);

    frame.add(sliderPanel, BorderLayout.PAGE_START);
  }

  /**
   * Adds the shape selection panel to the frame.
   *
   * @param frame the frame that is added to
   */
  private void addShapeSelectionPanel(JFrame frame) {

    // Label prompt
    JLabel prompt = new JLabel("Enter a shape name into the text field below");

    // The entire right-side panel
    JPanel selectionPanel = new JPanel();
    selectionPanel.setBackground(Color.lightGray);

    // the text field where shape names are inputted
    textField = new JTextField(20);

    // the button that sends the contents of text field to the controller
    JButton goButton = new JButton("add shape");
    goButton.addActionListener(this.listener);
    goButton.setBackground(Color.DARK_GRAY);

    // the button that resets all visibility to every shape
    JButton resetVisibilityButton = new JButton("reset visibility");
    resetVisibilityButton.addActionListener(this.listener);
    resetVisibilityButton.setBackground(Color.DARK_GRAY);

    // The Button to export to SVG format
    JButton svgButton = new JButton("export to SVG");
    svgButton.addActionListener(this.listener);
    svgButton.setBackground(Color.DARK_GRAY);

    // The Looping checkbox
    loopCheckBox = new JCheckBox("Looping on:");
    loopCheckBox.addActionListener(this.listener);

    selectionPanel.setLayout(new GridLayout(6, 1));

    selectionPanel.add(prompt);
    selectionPanel.add(textField);
    selectionPanel.add(goButton);
    selectionPanel.add(resetVisibilityButton);
    selectionPanel.add(svgButton);
    selectionPanel.add(loopCheckBox);

    frame.add(selectionPanel, BorderLayout.LINE_END);

  }

  /**
   * Sets the background of the animator panel.
   * @param color the color to be set to.
   */
  public void setBackground(Color color){
    animator.setBackground(color);
  }
}
