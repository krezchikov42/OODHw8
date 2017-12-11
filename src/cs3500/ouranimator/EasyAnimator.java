package cs3500.ouranimator;

import cs3500.animator.view.visual.InteractiveSwingView;
import cs3500.ouranimator.controller.AnimationController;
import cs3500.ouranimator.controller.ProviderController;
import cs3500.ouranimator.model.EasyAnimatorModel;
import cs3500.ouranimator.model.EasyAnimatorOperations;
import cs3500.ouranimator.util.AnimationFileReader;
import cs3500.ouranimator.util.LayeredAnimationFileReader;
import cs3500.ouranimator.view.HybridView;
import cs3500.ouranimator.view.View;
import cs3500.ouranimator.view.ViewFactory;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 * Class that puts together the view and the model and runs them to create an animation.
 */
public final class EasyAnimator {

  /**
   * Method that runs the class.
   *
   * @param args the command line arguments that will be used in the model.
   */
  public static void main(String[] args) {
    String view = null;
    float rate = 1;
    EasyAnimatorOperations model = null;
    boolean ifhap = false;
    boolean ivhap = false;
    // boolean print = false;
    String output = "out";
    for (int i = 0; i < args.length; i++) {
      String id = args[i];
      if (id.equals("-if")) {
        //open file and use it as the input
        try {
          model = (new LayeredAnimationFileReader()).readFile(args[i + 1],
              new EasyAnimatorModel.Builder());
          ifhap = true;
        } catch (FileNotFoundException e) {
          throw new IllegalArgumentException("File does not exist to read from");
        }
      } else if (id.equals("-iv")) {
        view = args[i + 1];
        ivhap = true;
      } else if (id.equals("-o")) {
        //send output to a file
        output = args[i + 1];
        //     print = true;
      } else if (id.equals("-speed")) {
        rate = Float.valueOf(args[i + 1]);
      } else {
        endGame();
      }
      i++;
    }
    if (ifhap && ivhap) {
      AnimationController control = null;

      if (view.equals("visual")) {
        View view1 = (new ViewFactory()).create(view);
        control = new AnimationController(model, view1, rate);
        control.runViewWithVisualComponent();
      } else if (view.equals("interactive")) {
        View view1 = (new ViewFactory()).create(view);
        control = new AnimationController(model, view1, rate);
        ((HybridView) view1).setListener(control);
        control.runViewWithVisualComponent();
      } else if (view.equals("provider")) {
        //creates new Conter with their view and runs it
        (new ProviderController(model, new InteractiveSwingView(), rate))
            .runViewWithVisualComponent();
      } else {

        if (output.equals("out")) {
          System.out.print(control.getTextFromTextualView());
        } else {
          try {
            FileWriter write = new FileWriter(output, true);
            PrintWriter print = new PrintWriter(write);
            print.print(control.getTextFromTextualView());
            print.close();
          } catch (IOException e) {
            throw new IllegalArgumentException("Can't write to file");
          }
        }

      }
    } else {
      throw new IllegalArgumentException("No view or input file seelcted");
    }
  }


  // pops up a Jpanel and ends the propgram.
  private static void endGame() {
    JOptionPane.showMessageDialog(null, "Incorrect command");
    System.exit(0);
  }

}
