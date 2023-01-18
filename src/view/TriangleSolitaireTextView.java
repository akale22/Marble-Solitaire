package view;

import marblesolitaire.model.MarbleSolitaireModelState;

/**
 * A class representing the view component of a Marble solitaire game. Contains methods to
 * display the game board as text correctly for a board that has a triangle shape and transmit this
 * board or another message to a specified destination.
 */
public class TriangleSolitaireTextView extends AbstractMarbleView {

  /**
   * Constructor which takes in a model and calls upon the super constructor to initialize the
   * model to what it takes in and the appendable to the system's out.
   *
   * @param model the model representing the game with the methods needed to draw the game.
   * @throws IllegalArgumentException if the model is null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model)
          throws IllegalArgumentException {
    super(model);
  }

  /**
   * Constructor which takes in both a model and an appendable and calls the super
   * constructor to initialize the fields to these parameters.
   *
   * @param model the model representing the game with the methods needed to draw the game.
   * @param out  the destination of this view
   * @throws IllegalArgumentException if the model or destination is null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model, Appendable out)
          throws IllegalArgumentException {
    super(model, out);
  }

  /**
   * Generates the starting string for each row of a triangle view which ensures that the
   * tip of the triangle is centered along the base when it is displayed.
   *
   * @param i           the current row we are on
   * @param boardSize   the size of the board of the model
   * @return            a string representing the starting string for each row of the triangle view
   */
  @Override
  protected String generateStartingRow(int i, int boardSize) {
    String starting = " ";
    return starting.repeat(boardSize - i - 1);
  }
}


