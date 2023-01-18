package cs3500.marblesolitaire;


import java.io.InputStreamReader;

import marblesolitaire.controller.MarbleSolitaireController;
import marblesolitaire.controller.MarbleSolitaireControllerImpl;
import marblesolitaire.view.MarbleSolitaireTextView;
import marblesolitaire.view.MarbleSolitaireView;
import marblesolitaire.view.TriangleSolitaireTextView;
import marblesolitaire.model.EnglishSolitaireModel;
import marblesolitaire.model.EuropeanSolitaireModel;
import marblesolitaire.model.MarbleSolitaireModel;
import marblesolitaire.model.TriangleSolitaireModel;

/**
 * Represents a program to run and play a game of marble solitaire.
 */
public final class MarbleSolitaireProgram {

  /**
   * A method for running the program.
   *
   * @param args the command-line arguments for this program.
   */
  public static void main(String[] args) {

    // instantiating these to null so that they can be changed to something not null if the
    // command-line specifies it which will inform us about what constructor to call
    Integer size = null;
    Integer emptyRow = null;
    Integer emptyCol = null;

    // searching for size
    for (int i = 1; i < args.length - 1; i++) {
      if (args[i].equals("-size")) {
        size = Integer.parseInt(args[i + 1]);
      }
    }
    // searching for a hole
    for (int i = 1; i < args.length - 2; i++) {
      if (args[i].equals("-hole")) {
        emptyRow = Integer.parseInt(args[i + 1]);
        emptyCol = Integer.parseInt(args[i + 2]);
      }
    }

    if (args[0].equals("english")) {
      generateEnglishModelAndView(size, emptyRow, emptyCol);
    }
    else if (args[0].equals("european")) {
      generateEuropeanModelAndView(size, emptyRow, emptyCol);
    }
    else if (args[0].equals("triangle")) {
      generateTriangleModelAndView(size, emptyRow, emptyCol);
    }
  }

  /**
   * Determines the type of english model based on the parameters.
   *
   * @param size  the size of the armThickness if specified, null otherwise
   * @param sRow  the row of the hole if specified, null otherwise
   * @param sCol  the column of the hole if specified, null otherwise
   */
  private static void generateEnglishModelAndView(Integer size, Integer sRow, Integer sCol) {
    MarbleSolitaireModel model;
    if (size == null && sRow == null) {
      model = new EnglishSolitaireModel();
    } else if (size == null && sRow != null) {
      model = new EnglishSolitaireModel(sRow, sCol);
    } else if (size != null && sRow == null) {
      model = new EnglishSolitaireModel(size);
    } else {
      model = new EnglishSolitaireModel(size, sRow, sCol);
    }

    MarbleSolitaireView view = new MarbleSolitaireTextView(model);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view,
            new InputStreamReader(System.in));
    controller.playGame();
  }

  /**
   * Determines the type of european model based on the parameters.
   *
   * @param size  the size of the armThickness if specified, null otherwise
   * @param sRow  the row of the hole if specified, null otherwise
   * @param sCol  the column of the hole if specified, null otherwise
   */
  private static void generateEuropeanModelAndView(Integer size, Integer sRow, Integer sCol) {
    MarbleSolitaireModel model;
    if (size == null && sRow == null) {
      model = new EuropeanSolitaireModel();
    } else if (size == null && sRow != null) {
      model = new EuropeanSolitaireModel(sRow, sCol);
    } else if (size != null && sRow == null) {
      model = new EuropeanSolitaireModel(size);
    } else {
      model = new EuropeanSolitaireModel(size, sRow, sCol);
    }

    MarbleSolitaireView view = new MarbleSolitaireTextView(model);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view,
            new InputStreamReader(System.in));
    controller.playGame();
  }

  /**
   * Determines the type of triangle model based on the parameters.
   *
   * @param size  the size of the armThickness if specified, null otherwise
   * @param sRow  the row of the hole if specified, null otherwise
   * @param sCol  the column of the hole if specified, null otherwise
   */
  private static void generateTriangleModelAndView(Integer size, Integer sRow, Integer sCol) {
    MarbleSolitaireModel model;
    if (size == null && sRow == null) {
      model = new TriangleSolitaireModel();
    } else if (size == null && sRow != null) {
      model = new TriangleSolitaireModel(sRow, sCol);
    } else if (size != null && sRow == null) {
      model = new TriangleSolitaireModel(size);
    } else {
      model = new TriangleSolitaireModel(size, sRow, sCol);
    }

    MarbleSolitaireView view = new TriangleSolitaireTextView(model);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view,
            new InputStreamReader(System.in));
    controller.playGame();
  }
}