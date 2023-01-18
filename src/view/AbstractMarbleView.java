package view;

import java.io.IOException;

import model.MarbleSolitaireModelState;

/**
 * An abstract class for the view of a marble solitaire game. Contains fields and methods common
 * to all implementations of views of marble solitaire.
 */
public abstract class AbstractMarbleView implements MarbleSolitaireView {

  protected final MarbleSolitaireModelState model;
  protected final Appendable out;

  /**
   * Constructor which takes in a model to what it takes in and initializes the destination to
   * the console's out.
   *
   * @param model the model representing the game with the methods needed to draw the game.
   * @throws IllegalArgumentException if the model is null
   */
  public AbstractMarbleView(MarbleSolitaireModelState model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null!");
    }
    this.model = model;
    this.out = System.out;
  }

  /**
   * Constructor which takes in both a model and an appendable and initializes the fields to
   * these parameters.
   *
   * @param model the model representing the game with the methods needed to draw the game.
   * @param out  the destination of this view
   * @throws IllegalArgumentException if the model or destination is null
   */
  public AbstractMarbleView(MarbleSolitaireModelState model, Appendable out)
          throws IllegalArgumentException {
    if (model == null || out == null) {
      throw new IllegalArgumentException("Model or destination cannot be null!");
    }
    this.model = model;
    this.out = out;
  }

  @Override
  public String toString() {
    int boardSize = this.model.getBoardSize();
    StringBuilder output = new StringBuilder();

    for (int i = 0; i < boardSize; i++) {
      String row = this.generateStartingRow(i, boardSize);

      for (int j = 0; j < boardSize; j++) {
        if (model.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Invalid) {
          row += "  ";
        } else if (model.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Empty) {
          row += "_ ";
        } else {
          row += "O ";
        }
      }

      // removing the ending spaces at the end of the row
      row = row.stripTrailing();

      // adding a newline, as long as we are not on the last row
      if (i < boardSize - 1) {
        row += "\n";
      }

      // appending the string for each row to the output string
      output.append(row);
    }
    return output.toString();
  }


  /**
   * Generates a string representing the beginning of a row based on the type of implementation and
   * the current row we are looking at.
   *
   * @param i           the current row we are on
   * @param boardSize   the size of the board of the model
   * @return            a string representing the current start of the row before we interpret it
   */
  protected abstract String generateStartingRow(int i, int boardSize);

  @Override
  public void renderBoard() throws IOException {
    this.out.append(this.toString());
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.out.append(message);
  }
}
