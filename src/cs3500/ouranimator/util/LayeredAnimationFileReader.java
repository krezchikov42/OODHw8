package cs3500.ouranimator.util;


import cs3500.ouranimator.model.EasyAnimatorModel;
import cs3500.ouranimator.model.EasyAnimatorModel.Builder;
import cs3500.ouranimator.model.EasyAnimatorOperations;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class LayeredAnimationFileReader extends AnimationFileReader {

  public List<List<String>> layers = new ArrayList<List<String>>();

  public <T> T readFile(String fileName, TweenModelBuilder<T> builder) throws
      FileNotFoundException, IllegalStateException, InputMismatchException {



    /// Read from the file using a basic reader

    AnimationFileReader reader = new AnimationFileReader();
    EasyAnimatorModel.Builder builderPreSort = new EasyAnimatorModel.Builder();

    try {
      reader.readFile(fileName, builderPreSort);
    }
    catch (Exception e) {
      // do nothing, this is to ignore the AnimationFileReader's exception when it encounters
      //  layer content in the input file without having to alter the orginal given code
    }


    // Extract the layer information from the file

    Scanner sc;

    sc = new Scanner(new FileInputStream(fileName));

    while (sc.hasNext()) {
      String command = sc.next();
      if (command.equals("layer")) {
        this.layers.add(new ArrayList<String>());
        readLayer(sc);
      }
    }

    // sort the shapes within the builders
    builderPreSort.sortShapesIntoLayers(this.layers);

    return (T) builderPreSort.build();
  }

  private void readLayer(Scanner sc) {
    while (sc.hasNext()) {
      String name = sc.next();

      if (name.equals("endlayer")) {
        return;
      }

      this.layers.get(this.layers.size() - 1).add(name);
    }
  }
}
