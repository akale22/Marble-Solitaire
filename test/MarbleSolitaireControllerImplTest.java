import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import controller.MarbleSolitaireController;
import controller.MarbleSolitaireControllerImpl;
import helpers.ConfirmInputsMock;
import helpers.CorruptAppendable;
import helpers.CorruptReadable;
import model.EnglishSolitaireModel;
import model.EuropeanSolitaireModel;
import model.MarbleSolitaireModel;
import model.TriangleSolitaireModel;
import view.MarbleSolitaireTextView;
import view.MarbleSolitaireView;
import view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for {@link MarbleSolitaireControllerImpl}s.
 */
public class MarbleSolitaireControllerImplTest {

  //////////////////// ENGLISH SOLITAIRE MODEL TESTS WITH THE CONTROLLER  ////////////////////

  private MarbleSolitaireModel englishModel;
  private MarbleSolitaireView englishView;
  private MarbleSolitaireView badEnglishView;
  private Readable englishReadable;
  private Readable badEnglishReadable;

  @Before
  public void initEnglish() {
    englishModel = new EnglishSolitaireModel();
    englishView = new MarbleSolitaireTextView(englishModel, new StringBuilder());
    badEnglishView = new MarbleSolitaireTextView(englishModel, new CorruptAppendable());
    englishReadable = new StringReader("");
    badEnglishReadable = new CorruptReadable("this is a corrupt readable");
  }


  ////////////////////////////// tests for the constructor //////////////////////////////

  // testing to see if a controller is constructed properly by running the only public method
  // available to us on a specific controller constructed in the method and ensuring it works
  @Test
  public void testValidConstructorEnglish() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("q");
    MarbleSolitaireView view = new MarbleSolitaireTextView(englishModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(englishModel, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to" +
            " represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", ap.toString());

  }

  // testing for an exception when the constructor has a null model
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidController1English() {
    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(null, englishView, englishReadable);
  }

  // testing for an exception when the constructor has a null view
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidController2English() {
    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(englishModel, null, englishReadable);
  }

  // testing for an exception when the constructor has a null readable
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidController3English() {
    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(englishModel, englishView, null);
  }

  // testing for an exception when the constructor has a null model and view
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidController4English() {
    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(null, null, englishReadable);
  }

  // testing for an exception when the constructor has a null model and readable
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidController5English() {
    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(null, englishView, null);
  }

  // testing for an exception when the constructor has a null view and readable
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidController6English() {
    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(englishModel, null, null);
  }

  // testing for an exception when the constructor has a null model, view, and readable
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidController7English() {
    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(null, null, null);
  }


  ////////////////////////////// tests for the playGame method //////////////////////////////

  // testing for an exception when a controller tries to read from a corrupt readable when the
  // playGame method is called
  @Test(expected = IllegalStateException.class)
  public void testBadReadableEnglish() {
    MarbleSolitaireController badController = new MarbleSolitaireControllerImpl(englishModel,
            englishView, badEnglishReadable);
    badController.playGame();
  }

  // testing for an exception when a controller tries to read from a corrupt readable when the
  // playGame method is called
  @Test(expected = IllegalStateException.class)
  public void testBadTransmissionEnglish() {
    MarbleSolitaireController badController = new MarbleSolitaireControllerImpl(englishModel,
            badEnglishView, englishReadable);
    badController.playGame();
  }


  /////////////////// testing different sequences with no quit and no game over ////////////////

  // testing a valid move with no q at the end
  @Test(expected = IllegalStateException.class)
  public void testValidMoveNoEndEnglish() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("4 2 4 4");
    MarbleSolitaireView view = new MarbleSolitaireTextView(englishModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(englishModel, view, rd);
    c.playGame();
  }

  // testing an invalid move with no q at the end
  @Test(expected = IllegalStateException.class)
  public void testInvalidMoveNoEndEnglish() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("1 1 1 1");
    MarbleSolitaireView view = new MarbleSolitaireTextView(englishModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(englishModel, view, rd);
    c.playGame();
  }

  // testing a negative input with no q at the end
  @Test(expected = IllegalStateException.class)
  public void testNegativeInputNoEndEnglish() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("-20");
    MarbleSolitaireView view = new MarbleSolitaireTextView(englishModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(englishModel, view, rd);
    c.playGame();
  }

  // testing an error string with no q at the end
  @Test(expected = IllegalStateException.class)
  public void testErrorStringNoEndEnglish() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("aryan");
    MarbleSolitaireView view = new MarbleSolitaireTextView(englishModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(englishModel, view, rd);
    c.playGame();
  }

  // testing multiple different input types with no end
  @Test(expected = IllegalStateException.class)
  public void testMultipleInputTypesNoEndEnglish() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("aryan -20 4 2 4 4 1 3 10 17 25");
    MarbleSolitaireView view = new MarbleSolitaireTextView(englishModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(englishModel, view, rd);
    c.playGame();
  }

  // testing an incomplete move with no q at the end
  @Test(expected = IllegalStateException.class)
  public void testIncompleteMoveNoEndEnglish() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("4 2 4");
    MarbleSolitaireView view = new MarbleSolitaireTextView(englishModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(englishModel, view, rd);
    c.playGame();
  }


  ////////////////////////// testing different sequences with a q at the end /////////////////////

  // testing quitting the game
  @Test
  public void testQuitEnglish() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("q");
    MarbleSolitaireView view = new MarbleSolitaireTextView(englishModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(englishModel, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that" +
            " represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", ap.toString());
  }

  // testing quitting the game with a capital q
  @Test
  public void testQuitCapitalQEnglish() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("Q");
    MarbleSolitaireView view = new MarbleSolitaireTextView(englishModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(englishModel, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that" +
            " represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", ap.toString());
  }

  // testing quitting the game after a valid number is inputted
  @Test
  public void testQuitAfter1InputEnglish() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("4 q");
    MarbleSolitaireView view = new MarbleSolitaireTextView(englishModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(englishModel, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that" +
            " represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", ap.toString());
  }

  // testing quitting the game after 2 valid numbers are inputted
  @Test
  public void testQuitAfter2InputsEnglish() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("4 2 q");
    MarbleSolitaireView view = new MarbleSolitaireTextView(englishModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(englishModel, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that" +
            " represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", ap.toString());
  }

  // testing quitting the game after 3 valid numbers are inputted
  @Test
  public void testQuitAfter3InputsEnglish() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("4 2 4 q");
    MarbleSolitaireView view = new MarbleSolitaireTextView(englishModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(englishModel, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that" +
            " represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", ap.toString());
  }

  // testing a q in the toRow position
  @Test
  public void testQuitInToRowPositionEnglish() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("4 2 q 4");
    MarbleSolitaireView view = new MarbleSolitaireTextView(englishModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(englishModel, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to" +
            " represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", ap.toString());
  }

  // testing a q in the fromRow position
  @Test
  public void testQuitInFromRowPositionEnglish() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("q 2 4 4");
    MarbleSolitaireView view = new MarbleSolitaireTextView(englishModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(englishModel, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to" +
            " represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", ap.toString());
  }

  // testing a q in the fromCol position
  @Test
  public void testQuitInFromColPositionEnglish() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("4 q 4 4");
    MarbleSolitaireView view = new MarbleSolitaireTextView(englishModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(englishModel, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to" +
            " represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", ap.toString());
  }

  // testing a valid move
  @Test
  public void testValidMoveEnglish() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("4 2 4 4 q");
    MarbleSolitaireView view = new MarbleSolitaireTextView(englishModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(englishModel, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n", ap.toString());
  }

  // testing a valid move with returns in between the readable inputs
  @Test
  public void testValidMoveWithReturnSequenceEnglish() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("4\n2\n4\n4\nq");
    MarbleSolitaireView view = new MarbleSolitaireTextView(englishModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(englishModel, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n", ap.toString());
  }

  // testing an invalid move
  @Test
  public void testInvalidMoveEnglish() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("1 2 3 4 q");
    MarbleSolitaireView view = new MarbleSolitaireTextView(englishModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(englishModel, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid move. Play again.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", ap.toString());
  }

  // testing a negative number input
  @Test
  public void testNegativeNumberInputEnglish() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("-5 q");
    MarbleSolitaireView view = new MarbleSolitaireTextView(englishModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(englishModel, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid Input: -5. Please re-enter that value again.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", ap.toString());
  }

  // test a string input that isn't q
  @Test
  public void testErrorStringInputEnglish() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("aryan q");
    MarbleSolitaireView view = new MarbleSolitaireTextView(englishModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(englishModel, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to" +
            " represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid Input: aryan. Please re-enter that value again.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", ap.toString());
  }

  // test a string input that isn't q
  @Test
  public void testMultipleInputsWithEndEnglish() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("aryan 4 2 4 4 1 1 1 1 -45 a b c 5 100 q");
    MarbleSolitaireView view = new MarbleSolitaireTextView(englishModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(englishModel, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that" +
            " represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to" +
            " represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid Input: aryan. Please re-enter that value again.\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Invalid move. Play again.\n" +
            "Invalid Input: -45. Please re-enter that value again.\n" +
            "Invalid Input: a. Please re-enter that value again.\n" +
            "Invalid Input: b. Please re-enter that value again.\n" +
            "Invalid Input: c. Please re-enter that value again.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n", ap.toString());
  }

  // testing when we have input after a q
  @Test
  public void testInputAfterQEnglish() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("q aryan 1 1 1 1 4 2 4 4 wow did I win -25");
    MarbleSolitaireView view = new MarbleSolitaireTextView(englishModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(englishModel, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to" +
            " represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", ap.toString());
  }

  // testing when we have input after a q and before a q
  @Test
  public void testInputAfterQAndBeforeQEnglish() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader(" 4 2 4 4 hi -9 0 q aryan 1 1 1 1 4 2 4 4 wow did I win -25");
    MarbleSolitaireView view = new MarbleSolitaireTextView(englishModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(englishModel, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Invalid Input: hi. Please re-enter that value again.\n" +
            "Invalid Input: -9. Please re-enter that value again.\n" +
            "Invalid Input: 0. Please re-enter that value again.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n", ap.toString());
  }

  // testing when we have an invalid input amongst 4 numbers of a valid move
  @Test
  public void testInvalidAmongstValidEnglish() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader(" 4 2 apple 4 4 q");
    MarbleSolitaireView view = new MarbleSolitaireTextView(englishModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(englishModel, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that" +
            " represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid Input: apple. Please re-enter that value again.\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n", ap.toString());
  }


  // testing a game over input with correct and valid inputs
  @Test
  public void testGameOverEnglish() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("4 2 4 4 2 3 4 3 2 5 2 3 1 3 3 3 1 5 1 3 4 3 2 3 1 3 3 3 4 4 "
            + "2 4 3 2 3 4 3 4 1 4 3 6 3 4 5 6 3 6 3 7 3 5 5 7 3 7 3 4 3 6 3 7 3 5 4 5 2 5 6 5 4 "
            + "5 5 3 5 5 5 5 3 5 3 5 1 5 1 5 1 3 7 4 5 4 7 3 5 3 5 3 5 5 5 1 5 3 3 1 5 1");
    MarbleSolitaireView view = new MarbleSolitaireTextView(englishModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(englishModel, view, rd);
    c.playGame();
    assertTrue(englishModel.isGameOver());
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to represent the" +
            " second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "    O O O\n" +
            "    _ O O\n" +
            "O O _ O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "    O O O\n" +
            "    O _ _\n" +
            "O O _ O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 29\n" +
            "    _ O O\n" +
            "    _ _ _\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 28\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 27\n" +
            "    O _ _\n" +
            "    O _ _\n" +
            "O O _ O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 26\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 25\n" +
            "    _ _ _\n" +
            "    _ O _\n" +
            "O O O _ O O O\n" +
            "O _ _ _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 24\n" +
            "    _ _ _\n" +
            "    _ O _\n" +
            "O _ _ O O O O\n" +
            "O _ _ _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 23\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ _ O O O\n" +
            "O _ _ _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 22\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ O _ _ O\n" +
            "O _ _ _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 21\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ O _ O O\n" +
            "O _ _ _ O _ O\n" +
            "O O O O O _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 20\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ O O _ _\n" +
            "O _ _ _ O _ O\n" +
            "O O O O O _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 19\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ O O _ O\n" +
            "O _ _ _ O _ _\n" +
            "O O O O O _ _\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 18\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ _ _ O O\n" +
            "O _ _ _ O _ _\n" +
            "O O O O O _ _\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 17\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ _ O _ _\n" +
            "O _ _ _ O _ _\n" +
            "O O O O O _ _\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 16\n" +
            "    _ O _\n" +
            "    _ _ O\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O O O O _ _\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 15\n" +
            "    _ O _\n" +
            "    _ _ O\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ O _ _\n" +
            "O O O O _ _ _\n" +
            "    O O _\n" +
            "    O O O\n" +
            "Score: 14\n" +
            "    _ O _\n" +
            "    _ _ O\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ O _ _\n" +
            "O O _ _ O _ _\n" +
            "    O O _\n" +
            "    O O O\n" +
            "Score: 13\n" +
            "    _ O _\n" +
            "    _ _ O\n" +
            "O _ _ _ O _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ _ _ _ _\n" +
            "    O O _\n" +
            "    O O O\n" +
            "Score: 12\n" +
            "    _ O O\n" +
            "    _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ _ _ _ _\n" +
            "    O O _\n" +
            "    O O O\n" +
            "Score: 11\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ _ _ _ _\n" +
            "    O O _\n" +
            "    O O O\n" +
            "Score: 10\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ O _ _ _\n" +
            "    O _ _\n" +
            "    O _ O\n" +
            "Score: 9\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O O O _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 8\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 7\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "_ _ O _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 6\n" +
            "Game over!\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "O _ O _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 5\n", ap.toString());
  }

  // testing a game over with invalid moves embedded
  @Test
  public void testGameOverWithInvalidMovesEnglish() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("1 1 1 1 4 2 4 4 2 3 4 3 2 5 2 3 1 3 3 3 1 5 1 3 4 3 2 3 1 3 3" +
            " 3 4 4 2 4 3 2 3 4 3 4 1 4 3 6 3 4 5 6 3 6 3 7 3 5 5 7 3 7 3 4 3 6 3 7 3 5 4 5 2 5 " +
            "6 5 4 "
            + "5 5 3 5 5 5 5 3 5 3 5 1 5 1 5 1 3 7 4 5 4 7 3 5 3 5 3 5 5 5 1 5 3 3 1 5 1");
    MarbleSolitaireView view = new MarbleSolitaireTextView(englishModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(englishModel, view, rd);
    c.playGame();
    assertTrue(englishModel.isGameOver());
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid move. Play again.\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "    O O O\n" +
            "    _ O O\n" +
            "O O _ O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "    O O O\n" +
            "    O _ _\n" +
            "O O _ O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 29\n" +
            "    _ O O\n" +
            "    _ _ _\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 28\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 27\n" +
            "    O _ _\n" +
            "    O _ _\n" +
            "O O _ O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 26\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 25\n" +
            "    _ _ _\n" +
            "    _ O _\n" +
            "O O O _ O O O\n" +
            "O _ _ _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 24\n" +
            "    _ _ _\n" +
            "    _ O _\n" +
            "O _ _ O O O O\n" +
            "O _ _ _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 23\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ _ O O O\n" +
            "O _ _ _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 22\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ O _ _ O\n" +
            "O _ _ _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 21\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ O _ O O\n" +
            "O _ _ _ O _ O\n" +
            "O O O O O _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 20\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ O O _ _\n" +
            "O _ _ _ O _ O\n" +
            "O O O O O _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 19\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ O O _ O\n" +
            "O _ _ _ O _ _\n" +
            "O O O O O _ _\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 18\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ _ _ O O\n" +
            "O _ _ _ O _ _\n" +
            "O O O O O _ _\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 17\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ _ O _ _\n" +
            "O _ _ _ O _ _\n" +
            "O O O O O _ _\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 16\n" +
            "    _ O _\n" +
            "    _ _ O\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O O O O _ _\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 15\n" +
            "    _ O _\n" +
            "    _ _ O\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ O _ _\n" +
            "O O O O _ _ _\n" +
            "    O O _\n" +
            "    O O O\n" +
            "Score: 14\n" +
            "    _ O _\n" +
            "    _ _ O\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ O _ _\n" +
            "O O _ _ O _ _\n" +
            "    O O _\n" +
            "    O O O\n" +
            "Score: 13\n" +
            "    _ O _\n" +
            "    _ _ O\n" +
            "O _ _ _ O _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ _ _ _ _\n" +
            "    O O _\n" +
            "    O O O\n" +
            "Score: 12\n" +
            "    _ O O\n" +
            "    _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ _ _ _ _\n" +
            "    O O _\n" +
            "    O O O\n" +
            "Score: 11\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ _ _ _ _\n" +
            "    O O _\n" +
            "    O O O\n" +
            "Score: 10\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ O _ _ _\n" +
            "    O _ _\n" +
            "    O _ O\n" +
            "Score: 9\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O O O _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 8\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 7\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "_ _ O _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 6\n" +
            "Game over!\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "O _ O _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 5\n", ap.toString());
  }

  // testing a game over with error inputs embedded
  @Test
  public void testGameOverWithErrorInputsEnglish() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("4 hi 2 4 4 2 3 4 3 2 5 2 3 thirty 1 3 3 3 1 5 1 3 4 " +
            "3 2 3 1 3 3" +
            " 3 4 4 2 4 3 2 lola 3 4 3 4 1 4 3 6 3 4 5 6 3 6 3 7 3 5 5 7 3 7 3 4 3 6 3 7 3 5 4 5 " +
            "2 5 6 5 4 "
            + "5 5 3 5 5 -10 5 5 3 5 3 5 1 5 0 1 5 1 3 7 4 5 4 7 3 5 3 5 3 5 5 5 1 5 3 3 1 5 1");
    MarbleSolitaireView view = new MarbleSolitaireTextView(englishModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(englishModel, view, rd);
    c.playGame();
    assertTrue(englishModel.isGameOver());
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid Input: hi. Please re-enter that value again.\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "    O O O\n" +
            "    _ O O\n" +
            "O O _ O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "    O O O\n" +
            "    O _ _\n" +
            "O O _ O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 29\n" +
            "Invalid Input: thirty. Please re-enter that value again.\n" +
            "    _ O O\n" +
            "    _ _ _\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 28\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 27\n" +
            "    O _ _\n" +
            "    O _ _\n" +
            "O O _ O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 26\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 25\n" +
            "    _ _ _\n" +
            "    _ O _\n" +
            "O O O _ O O O\n" +
            "O _ _ _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 24\n" +
            "Invalid Input: lola. Please re-enter that value again.\n" +
            "    _ _ _\n" +
            "    _ O _\n" +
            "O _ _ O O O O\n" +
            "O _ _ _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 23\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ _ O O O\n" +
            "O _ _ _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 22\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ O _ _ O\n" +
            "O _ _ _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 21\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ O _ O O\n" +
            "O _ _ _ O _ O\n" +
            "O O O O O _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 20\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ O O _ _\n" +
            "O _ _ _ O _ O\n" +
            "O O O O O _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 19\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ O O _ O\n" +
            "O _ _ _ O _ _\n" +
            "O O O O O _ _\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 18\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ _ _ O O\n" +
            "O _ _ _ O _ _\n" +
            "O O O O O _ _\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 17\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ _ O _ _\n" +
            "O _ _ _ O _ _\n" +
            "O O O O O _ _\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 16\n" +
            "    _ O _\n" +
            "    _ _ O\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O O O O _ _\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 15\n" +
            "    _ O _\n" +
            "    _ _ O\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ O _ _\n" +
            "O O O O _ _ _\n" +
            "    O O _\n" +
            "    O O O\n" +
            "Score: 14\n" +
            "    _ O _\n" +
            "    _ _ O\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ O _ _\n" +
            "O O _ _ O _ _\n" +
            "    O O _\n" +
            "    O O O\n" +
            "Score: 13\n" +
            "Invalid Input: -10. Please re-enter that value again.\n" +
            "    _ O _\n" +
            "    _ _ O\n" +
            "O _ _ _ O _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ _ _ _ _\n" +
            "    O O _\n" +
            "    O O O\n" +
            "Score: 12\n" +
            "    _ O O\n" +
            "    _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ _ _ _ _\n" +
            "    O O _\n" +
            "    O O O\n" +
            "Score: 11\n" +
            "Invalid Input: 0. Please re-enter that value again.\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ _ _ _ _\n" +
            "    O O _\n" +
            "    O O O\n" +
            "Score: 10\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ O _ _ _\n" +
            "    O _ _\n" +
            "    O _ O\n" +
            "Score: 9\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O O O _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 8\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 7\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "_ _ O _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 6\n" +
            "Game over!\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "O _ O _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 5\n", ap.toString());
  }

  // testing a game over with invalid moves and error inputs embedded
  @Test
  public void testGameOverWithInvalidMovesAndErrorInputsEnglish() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("4 hi 2 4 4 1 1 1 1 2 3 4 3 2 5 2 3 thirty 1 3 3 3 1 5 1 3 4 " +
            "3 2 3 1 3 3" +
            " 3 4 4 2 4 3 2 lola 3 4 3 4 1 4 3 6 3 4 5 6 3 6 3 7 3 5 5 7 3 7 3 4 3 6 3 7 3 5 4 5 " +
            "2 5 6 5 4 "
            + "5 5 3 5 5 -10 5 5 3 5 3 5 1 5 0 1 5 1 3 7 4 5 4 7 3 5 3 5 3 5 5 5 1 5 3 3 1 5 1");
    MarbleSolitaireView view = new MarbleSolitaireTextView(englishModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(englishModel, view, rd);
    c.playGame();
    assertTrue(englishModel.isGameOver());
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid Input: hi. Please re-enter that value again.\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Invalid move. Play again.\n" +
            "    O O O\n" +
            "    _ O O\n" +
            "O O _ O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "    O O O\n" +
            "    O _ _\n" +
            "O O _ O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 29\n" +
            "Invalid Input: thirty. Please re-enter that value again.\n" +
            "    _ O O\n" +
            "    _ _ _\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 28\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 27\n" +
            "    O _ _\n" +
            "    O _ _\n" +
            "O O _ O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 26\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 25\n" +
            "    _ _ _\n" +
            "    _ O _\n" +
            "O O O _ O O O\n" +
            "O _ _ _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 24\n" +
            "Invalid Input: lola. Please re-enter that value again.\n" +
            "    _ _ _\n" +
            "    _ O _\n" +
            "O _ _ O O O O\n" +
            "O _ _ _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 23\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ _ O O O\n" +
            "O _ _ _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 22\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ O _ _ O\n" +
            "O _ _ _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 21\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ O _ O O\n" +
            "O _ _ _ O _ O\n" +
            "O O O O O _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 20\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ O O _ _\n" +
            "O _ _ _ O _ O\n" +
            "O O O O O _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 19\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ O O _ O\n" +
            "O _ _ _ O _ _\n" +
            "O O O O O _ _\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 18\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ _ _ O O\n" +
            "O _ _ _ O _ _\n" +
            "O O O O O _ _\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 17\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ _ O _ _\n" +
            "O _ _ _ O _ _\n" +
            "O O O O O _ _\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 16\n" +
            "    _ O _\n" +
            "    _ _ O\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O O O O _ _\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 15\n" +
            "    _ O _\n" +
            "    _ _ O\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ O _ _\n" +
            "O O O O _ _ _\n" +
            "    O O _\n" +
            "    O O O\n" +
            "Score: 14\n" +
            "    _ O _\n" +
            "    _ _ O\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ O _ _\n" +
            "O O _ _ O _ _\n" +
            "    O O _\n" +
            "    O O O\n" +
            "Score: 13\n" +
            "Invalid Input: -10. Please re-enter that value again.\n" +
            "    _ O _\n" +
            "    _ _ O\n" +
            "O _ _ _ O _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ _ _ _ _\n" +
            "    O O _\n" +
            "    O O O\n" +
            "Score: 12\n" +
            "    _ O O\n" +
            "    _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ _ _ _ _\n" +
            "    O O _\n" +
            "    O O O\n" +
            "Score: 11\n" +
            "Invalid Input: 0. Please re-enter that value again.\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ _ _ _ _\n" +
            "    O O _\n" +
            "    O O O\n" +
            "Score: 10\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ O _ _ _\n" +
            "    O _ _\n" +
            "    O _ O\n" +
            "Score: 9\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O O O _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 8\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 7\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "_ _ O _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 6\n" +
            "Game over!\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "O _ O _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 5\n", ap.toString());
  }

  // testing a game over with no invalid sequences but extra text after the game has ended
  @Test
  public void testGameOverWithNoInvalidMovesAndExtraTextAtEndEnglish() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("4 2 4 4 2 3 4 3 2 5 2 3 1 3 3 3 1 5 1 3 4 3 2 3 1 3 3 3 4 4 "
            + "2 4 3 2 3 4 3 4 1 4 3 6 3 4 5 6 3 6 3 7 3 5 5 7 3 7 3 4 3 6 3 7 3 5 4 5 2 5 6 5 4 "
            + "5 5 3 5 5 5 5 3 5 3 5 1 5 1 5 1 3 7 4 5 4 7 3 5 3 5 3 5 5 5 1 5 3 3 1 5 1 10 100 " +
            "-45 abc aryan is good at coding 5 4 3 2 1");
    MarbleSolitaireView view = new MarbleSolitaireTextView(englishModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(englishModel, view, rd);
    c.playGame();
    assertTrue(englishModel.isGameOver());
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "    O O O\n" +
            "    _ O O\n" +
            "O O _ O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "    O O O\n" +
            "    O _ _\n" +
            "O O _ O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 29\n" +
            "    _ O O\n" +
            "    _ _ _\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 28\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 27\n" +
            "    O _ _\n" +
            "    O _ _\n" +
            "O O _ O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 26\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 25\n" +
            "    _ _ _\n" +
            "    _ O _\n" +
            "O O O _ O O O\n" +
            "O _ _ _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 24\n" +
            "    _ _ _\n" +
            "    _ O _\n" +
            "O _ _ O O O O\n" +
            "O _ _ _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 23\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ _ O O O\n" +
            "O _ _ _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 22\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ O _ _ O\n" +
            "O _ _ _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 21\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ O _ O O\n" +
            "O _ _ _ O _ O\n" +
            "O O O O O _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 20\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ O O _ _\n" +
            "O _ _ _ O _ O\n" +
            "O O O O O _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 19\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ O O _ O\n" +
            "O _ _ _ O _ _\n" +
            "O O O O O _ _\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 18\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ _ _ O O\n" +
            "O _ _ _ O _ _\n" +
            "O O O O O _ _\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 17\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ _ O _ _\n" +
            "O _ _ _ O _ _\n" +
            "O O O O O _ _\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 16\n" +
            "    _ O _\n" +
            "    _ _ O\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O O O O _ _\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 15\n" +
            "    _ O _\n" +
            "    _ _ O\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ O _ _\n" +
            "O O O O _ _ _\n" +
            "    O O _\n" +
            "    O O O\n" +
            "Score: 14\n" +
            "    _ O _\n" +
            "    _ _ O\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ O _ _\n" +
            "O O _ _ O _ _\n" +
            "    O O _\n" +
            "    O O O\n" +
            "Score: 13\n" +
            "    _ O _\n" +
            "    _ _ O\n" +
            "O _ _ _ O _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ _ _ _ _\n" +
            "    O O _\n" +
            "    O O O\n" +
            "Score: 12\n" +
            "    _ O O\n" +
            "    _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ _ _ _ _\n" +
            "    O O _\n" +
            "    O O O\n" +
            "Score: 11\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ _ _ _ _\n" +
            "    O O _\n" +
            "    O O O\n" +
            "Score: 10\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ O _ _ _\n" +
            "    O _ _\n" +
            "    O _ O\n" +
            "Score: 9\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O O O _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 8\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 7\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "_ _ O _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 6\n" +
            "Game over!\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "O _ O _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 5\n", ap.toString());
  }

  // testing a game over with no invalid sequences but a quit sequence after
  @Test
  public void testGameOverWithNoInvalidMovesAndQuitSequenceAfterEnglish() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("4 2 4 4 2 3 4 3 2 5 2 3 1 3 3 3 1 5 1 3 4 3 2 3 1 3 3 3 4 4 "
            + "2 4 3 2 3 4 3 4 1 4 3 6 3 4 5 6 3 6 3 7 3 5 5 7 3 7 3 4 3 6 3 7 3 5 4 5 2 5 6 5 4 "
            + "5 5 3 5 5 5 5 3 5 3 5 1 5 1 5 1 3 7 4 5 4 7 3 5 3 5 3 5 5 5 1 5 3 3 1 5 1 q");
    MarbleSolitaireView view = new MarbleSolitaireTextView(englishModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(englishModel, view, rd);
    c.playGame();
    assertTrue(englishModel.isGameOver());
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "    O O O\n" +
            "    _ O O\n" +
            "O O _ O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "    O O O\n" +
            "    O _ _\n" +
            "O O _ O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 29\n" +
            "    _ O O\n" +
            "    _ _ _\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 28\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 27\n" +
            "    O _ _\n" +
            "    O _ _\n" +
            "O O _ O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 26\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 25\n" +
            "    _ _ _\n" +
            "    _ O _\n" +
            "O O O _ O O O\n" +
            "O _ _ _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 24\n" +
            "    _ _ _\n" +
            "    _ O _\n" +
            "O _ _ O O O O\n" +
            "O _ _ _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 23\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ _ O O O\n" +
            "O _ _ _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 22\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ O _ _ O\n" +
            "O _ _ _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 21\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ O _ O O\n" +
            "O _ _ _ O _ O\n" +
            "O O O O O _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 20\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ O O _ _\n" +
            "O _ _ _ O _ O\n" +
            "O O O O O _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 19\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ O O _ O\n" +
            "O _ _ _ O _ _\n" +
            "O O O O O _ _\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 18\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ _ _ O O\n" +
            "O _ _ _ O _ _\n" +
            "O O O O O _ _\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 17\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ _ O _ _\n" +
            "O _ _ _ O _ _\n" +
            "O O O O O _ _\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 16\n" +
            "    _ O _\n" +
            "    _ _ O\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O O O O _ _\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 15\n" +
            "    _ O _\n" +
            "    _ _ O\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ O _ _\n" +
            "O O O O _ _ _\n" +
            "    O O _\n" +
            "    O O O\n" +
            "Score: 14\n" +
            "    _ O _\n" +
            "    _ _ O\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ O _ _\n" +
            "O O _ _ O _ _\n" +
            "    O O _\n" +
            "    O O O\n" +
            "Score: 13\n" +
            "    _ O _\n" +
            "    _ _ O\n" +
            "O _ _ _ O _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ _ _ _ _\n" +
            "    O O _\n" +
            "    O O O\n" +
            "Score: 12\n" +
            "    _ O O\n" +
            "    _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ _ _ _ _\n" +
            "    O O _\n" +
            "    O O O\n" +
            "Score: 11\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ _ _ _ _\n" +
            "    O O _\n" +
            "    O O O\n" +
            "Score: 10\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ O _ _ _\n" +
            "    O _ _\n" +
            "    O _ O\n" +
            "Score: 9\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O O O _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 8\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 7\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "_ _ O _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 6\n" +
            "Game over!\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "O _ O _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 5\n", ap.toString());
  }

  // testing a game over with a q embedded inside nd a Q at the end
  @Test
  public void testGameOverSequenceWithQEmbeddedInsideAndQAtEndEnglish() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("4 2 4 4 2 3 4 3 2 5 2 3 1 3 3 3 1 5 1 3 4 3 2 3 1 3 3 3 4 4 "
            + "2 4 3 2 3 4 3 4 1 4 3 6 3 4 5 6 3 6 3 7 3 5 q 5 7 3 7 3 4 3 6 3 7 3 5 4 5 2 5 6 5 4 "
            + "5 5 3 5 5 5 5 3 5 3 5 1 5 1 5 1 3 7 4 5 4 7 3 5 3 5 3 5 5 5 1 5 3 3 1 5 1 Q");
    MarbleSolitaireView view = new MarbleSolitaireTextView(englishModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(englishModel, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "    O O O\n" +
            "    _ O O\n" +
            "O O _ O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "    O O O\n" +
            "    O _ _\n" +
            "O O _ O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 29\n" +
            "    _ O O\n" +
            "    _ _ _\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 28\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 27\n" +
            "    O _ _\n" +
            "    O _ _\n" +
            "O O _ O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 26\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 25\n" +
            "    _ _ _\n" +
            "    _ O _\n" +
            "O O O _ O O O\n" +
            "O _ _ _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 24\n" +
            "    _ _ _\n" +
            "    _ O _\n" +
            "O _ _ O O O O\n" +
            "O _ _ _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 23\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ _ O O O\n" +
            "O _ _ _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 22\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ O _ _ O\n" +
            "O _ _ _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 21\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ O _ O O\n" +
            "O _ _ _ O _ O\n" +
            "O O O O O _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 20\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ O O _ _\n" +
            "O _ _ _ O _ O\n" +
            "O O O O O _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 19\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "O _ _ O O _ _\n" +
            "O _ _ _ O _ O\n" +
            "O O O O O _ O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 19\n", ap.toString());
  }


  //////////////////// EUROPEAN SOLITAIRE MODEL TESTS WITH THE CONTROLLER  ////////////////////

  private MarbleSolitaireModel europeanModel;
  private MarbleSolitaireView europeanView;
  private MarbleSolitaireView badEuropeanView;
  private Readable europeanReadable;
  private Readable badEuropeanReadable;

  @Before
  public void initEuropean() {
    europeanModel = new EuropeanSolitaireModel();
    europeanView = new MarbleSolitaireTextView(europeanModel, new StringBuilder());
    badEuropeanView = new MarbleSolitaireTextView(europeanModel, new CorruptAppendable());
    europeanReadable = new StringReader("");
    badEuropeanReadable = new CorruptReadable("this is a corrupt readable");
  }


  // testing to see if a controller is constructed properly by running the only public method
  // available to us on a specific controller constructed in the method and ensuring it works
  @Test
  public void testValidConstructorEuropean() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("q");
    MarbleSolitaireView view = new MarbleSolitaireTextView(europeanModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(europeanModel, view, rd);
    c.playGame();
    assertEquals("\n" +
            "Welcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n", ap.toString());

  }

  // testing for an exception when the constructor has a null model
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidController1European() {
    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(null, europeanView, europeanReadable);
  }

  // testing for an exception when the constructor has a null view
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidController2European() {
    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(europeanModel, null, europeanReadable);
  }

  // testing for an exception when the constructor has a null readable
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidController3European() {
    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(europeanModel, europeanView, null);
  }

  // testing for an exception when the constructor has a null model and view
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidController4European() {
    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(null, null, europeanReadable);
  }

  // testing for an exception when the constructor has a null model and readable
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidController5European() {
    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(null, europeanView, null);
  }

  // testing for an exception when the constructor has a null view and readable
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidController6European() {
    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(europeanModel, null, null);
  }

  ////////////////////////////// tests for the playGame method //////////////////////////////

  // testing for an exception when a controller tries to read from a corrupt readable when the
  // playGame method is called
  @Test(expected = IllegalStateException.class)
  public void testBadReadableEuropean() {
    MarbleSolitaireController badController = new MarbleSolitaireControllerImpl(europeanModel,
            europeanView,
            badEuropeanReadable);
    badController.playGame();
  }

  // testing for an exception when a controller tries to read from a corrupt readable when the
  // playGame method is called
  @Test(expected = IllegalStateException.class)
  public void testBadTransmissionEuropean() {
    MarbleSolitaireController badController = new MarbleSolitaireControllerImpl(europeanModel,
            badEuropeanView,
            europeanReadable);
    badController.playGame();
  }


  /////////////////// testing different sequences with no quit and no game over ////////////////

  // testing a valid move with no q at the end
  @Test(expected = IllegalStateException.class)
  public void testValidMoveNoEndEuropean() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("4 2 4 4");
    MarbleSolitaireView view = new MarbleSolitaireTextView(europeanModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(europeanModel, view, rd);
    c.playGame();
  }

  // testing an invalid move with no q at the end
  @Test(expected = IllegalStateException.class)
  public void testInvalidMoveNoEndEuropean() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("1 1 1 1");
    MarbleSolitaireView view = new MarbleSolitaireTextView(europeanModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(europeanModel, view, rd);
    c.playGame();
  }

  // testing a negative input with no q at the end
  @Test(expected = IllegalStateException.class)
  public void testNegativeInputNoEndEuropean() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("-20");
    MarbleSolitaireView view = new MarbleSolitaireTextView(europeanModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(europeanModel, view, rd);
    c.playGame();
  }

  // testing an error string with no q at the end
  @Test(expected = IllegalStateException.class)
  public void testErrorStringNoEndEuropean() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("aryan");
    MarbleSolitaireView view = new MarbleSolitaireTextView(europeanModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(europeanModel, view, rd);
    c.playGame();
  }

  // testing multiple different input types with no end
  @Test(expected = IllegalStateException.class)
  public void testMultipleInputTypesNoEndEuropean() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("aryan -20 4 2 4 4 1 3 10 17 25");
    MarbleSolitaireView view = new MarbleSolitaireTextView(europeanModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(europeanModel, view, rd);
    c.playGame();
  }

  // testing an incomplete move with no q at the end
  @Test(expected = IllegalStateException.class)
  public void testIncompleteMoveNoEndEuropean() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("4 2 4");
    MarbleSolitaireView view = new MarbleSolitaireTextView(europeanModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(europeanModel, view, rd);
    c.playGame();
  }


  ////////////////////////// testing different sequences with a q at the end /////////////////////

  // testing quitting the game
  @Test
  public void testQuitEuropean() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("q");
    MarbleSolitaireView view = new MarbleSolitaireTextView(europeanModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(europeanModel, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that" +
            " represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n", ap.toString());
  }

  // testing quitting the game with a capital q
  @Test
  public void testQuitCapitalQEuropean() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("Q");
    MarbleSolitaireView view = new MarbleSolitaireTextView(europeanModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(europeanModel, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that" +
            " represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n", ap.toString());
  }

  // testing quitting the game after a valid number is inputted
  @Test
  public void testQuitAfter1InputEuropean() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("4 q");
    MarbleSolitaireView view = new MarbleSolitaireTextView(europeanModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(europeanModel, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n", ap.toString());
  }

  // testing quitting the game after 2 valid numbers are inputted
  @Test
  public void testQuitAfter2InputsEuropean() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("4 2 q");
    MarbleSolitaireView view = new MarbleSolitaireTextView(europeanModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(europeanModel, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n", ap.toString());
  }

  // testing quitting the game after 3 valid numbers are inputted
  @Test
  public void testQuitAfter3InputsEuropean() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("4 2 4 q");
    MarbleSolitaireView view = new MarbleSolitaireTextView(europeanModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(europeanModel, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n", ap.toString());
  }

  // testing a q in the toRow position
  @Test
  public void testQuitInToRowPositionEuropean() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("4 2 q 4");
    MarbleSolitaireView view = new MarbleSolitaireTextView(europeanModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(europeanModel, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n", ap.toString());
  }

  // testing a q in the fromRow position
  @Test
  public void testQuitInFromRowPositionEuropean() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("q 2 4 4");
    MarbleSolitaireView view = new MarbleSolitaireTextView(europeanModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(europeanModel, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n", ap.toString());
  }

  // testing a q in the fromCol position
  @Test
  public void testQuitInFromColPositionEuropean() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("4 q 4 4");
    MarbleSolitaireView view = new MarbleSolitaireTextView(europeanModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(europeanModel, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n", ap.toString());
  }

  // testing a valid move
  @Test
  public void testValidMoveEuropean() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("4 2 4 4 q");
    MarbleSolitaireView view = new MarbleSolitaireTextView(europeanModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(europeanModel, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n", ap.toString());
  }

  // testing a valid move with returns in between the readable inputs
  @Test
  public void testValidMoveWithReturnSequenceEuropean() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("4\n2\n4\n4\nq");
    MarbleSolitaireView view = new MarbleSolitaireTextView(europeanModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(europeanModel, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n", ap.toString());
  }

  // testing an invalid move
  @Test
  public void testInvalidMoveEuropean() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("1 2 3 4 q");
    MarbleSolitaireView view = new MarbleSolitaireTextView(europeanModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(europeanModel, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that" +
            " represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Invalid move. Play again.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n", ap.toString());
  }

  // testing a negative number input
  @Test
  public void testNegativeNumberInputEuropean() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("-5 q");
    MarbleSolitaireView view = new MarbleSolitaireTextView(europeanModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(europeanModel, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to" +
            " represent the" +
            " second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Invalid Input: -5. Please re-enter that value again.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n", ap.toString());
  }

  // test a string input that isn't q
  @Test
  public void testErrorStringInputEuropean() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("aryan q");
    MarbleSolitaireView view = new MarbleSolitaireTextView(europeanModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(europeanModel, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Invalid Input: aryan. Please re-enter that value again.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n", ap.toString());
  }

  // test a string input that isn't q
  @Test
  public void testMultipleInputsWithEndEuropean() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("aryan 4 2 4 4 1 1 1 1 -45 a b c 5 100 q");
    MarbleSolitaireView view = new MarbleSolitaireTextView(europeanModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(europeanModel, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Invalid Input: aryan. Please re-enter that value again.\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "Invalid move. Play again.\n" +
            "Invalid Input: -45. Please re-enter that value again.\n" +
            "Invalid Input: a. Please re-enter that value again.\n" +
            "Invalid Input: b. Please re-enter that value again.\n" +
            "Invalid Input: c. Please re-enter that value again.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n", ap.toString());
  }

  // testing when we have input after a q
  @Test
  public void testInputAfterQEuropean() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("q aryan 1 1 1 1 4 2 4 4 wow did I win -25");
    MarbleSolitaireView view = new MarbleSolitaireTextView(europeanModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(europeanModel, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n", ap.toString());
  }

  // testing when we have an invalid input amongst 4 numbers of a valid move
  @Test
  public void testInvalidAmongstValidEuropean() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader(" 4 2 apple 4 4 q");
    MarbleSolitaireView view = new MarbleSolitaireTextView(europeanModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(europeanModel, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Invalid Input: apple. Please re-enter that value again.\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n", ap.toString());
  }


  ////////////////////////////// testing different sequences of game over //////////////////////////

  // testing a game over input with correct and valid inputs
  @Test
  public void testGameOverEuropean() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("4 2 4 4 2 3 4 3 2 5 2 3 1 3 3 3 1 5 1 3 4 3 2 3 1 3 3" +
            " 3 4 4 2 4 3 2 3 4 3 4 1 4 3 6 3 4 5 6 3 6 3 7 3 5 5 7 3 7 3 4 3 6 3 7 3 5 4 5" +
            " 2 5 6 5 4 5 5 3 5 5 5 5 3 5 3 5 1 5 1 5 1 3 7 4 5 4 7 3 5 3 6 2 4 2 5 4 5 2 5 2" +
            " 3 2 2 2 4 2 4 1 4 3");
    MarbleSolitaireView view = new MarbleSolitaireTextView(europeanModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(europeanModel, view, rd);
    c.playGame();
    assertTrue(europeanModel.isGameOver());
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "    O O O\n" +
            "  O _ O O O\n" +
            "O O _ O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 34\n" +
            "    O O O\n" +
            "  O O _ _ O\n" +
            "O O _ O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 33\n" +
            "    _ O O\n" +
            "  O _ _ _ O\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O _ _\n" +
            "  O _ _ _ O\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "    O _ _\n" +
            "  O O _ _ O\n" +
            "O O _ O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "    _ _ _\n" +
            "  O _ _ _ O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 29\n" +
            "    _ _ _\n" +
            "  O _ O _ O\n" +
            "O O O _ O O O\n" +
            "O _ _ _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 28\n" +
            "    _ _ _\n" +
            "  O _ O _ O\n" +
            "O _ _ O O O O\n" +
            "O _ _ _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 27\n" +
            "    _ O _\n" +
            "  O _ _ _ O\n" +
            "O _ _ _ O O O\n" +
            "O _ _ _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 26\n" +
            "    _ O _\n" +
            "  O _ _ _ O\n" +
            "O _ _ O _ _ O\n" +
            "O _ _ _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 25\n" +
            "    _ O _\n" +
            "  O _ _ _ O\n" +
            "O _ _ O _ O O\n" +
            "O _ _ _ O _ O\n" +
            "O O O O O _ O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 24\n" +
            "    _ O _\n" +
            "  O _ _ _ O\n" +
            "O _ _ O O _ _\n" +
            "O _ _ _ O _ O\n" +
            "O O O O O _ O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 23\n" +
            "    _ O _\n" +
            "  O _ _ _ O\n" +
            "O _ _ O O _ O\n" +
            "O _ _ _ O _ _\n" +
            "O O O O O _ _\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 22\n" +
            "    _ O _\n" +
            "  O _ _ _ O\n" +
            "O _ _ _ _ O O\n" +
            "O _ _ _ O _ _\n" +
            "O O O O O _ _\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 21\n" +
            "    _ O _\n" +
            "  O _ _ _ O\n" +
            "O _ _ _ O _ _\n" +
            "O _ _ _ O _ _\n" +
            "O O O O O _ _\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 20\n" +
            "    _ O _\n" +
            "  O _ _ O O\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O O O O _ _\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 19\n" +
            "    _ O _\n" +
            "  O _ _ O O\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ O _ _\n" +
            "O O O O _ _ _\n" +
            "  O O O _ O\n" +
            "    O O O\n" +
            "Score: 18\n" +
            "    _ O _\n" +
            "  O _ _ O O\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ O _ _\n" +
            "O O _ _ O _ _\n" +
            "  O O O _ O\n" +
            "    O O O\n" +
            "Score: 17\n" +
            "    _ O _\n" +
            "  O _ _ O O\n" +
            "O _ _ _ O _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ _ _ _ _\n" +
            "  O O O _ O\n" +
            "    O O O\n" +
            "Score: 16\n" +
            "    _ O O\n" +
            "  O _ _ _ O\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ _ _ _ _\n" +
            "  O O O _ O\n" +
            "    O O O\n" +
            "Score: 15\n" +
            "    O _ _\n" +
            "  O _ _ _ O\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ _ _ _ _\n" +
            "  O O O _ O\n" +
            "    O O O\n" +
            "Score: 14\n" +
            "    O _ _\n" +
            "  O _ _ _ O\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ O _ _ _\n" +
            "  O O _ _ O\n" +
            "    O _ O\n" +
            "Score: 13\n" +
            "    O _ _\n" +
            "  O _ _ _ O\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O O O _ _ _\n" +
            "  O _ _ _ O\n" +
            "    _ _ O\n" +
            "Score: 12\n" +
            "    O _ _\n" +
            "  O _ _ _ O\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ _ _ _ _\n" +
            "O _ O O _ _ _\n" +
            "  _ _ _ _ O\n" +
            "    _ _ O\n" +
            "Score: 11\n" +
            "    O _ _\n" +
            "  O _ _ _ O\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ _ _ _ _\n" +
            "O O _ _ _ _ _\n" +
            "  _ _ _ _ O\n" +
            "    _ _ O\n" +
            "Score: 10\n" +
            "    O _ _\n" +
            "  O _ _ _ O\n" +
            "O O _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "  _ _ _ _ O\n" +
            "    _ _ O\n" +
            "Score: 9\n" +
            "    O _ _\n" +
            "  _ _ _ _ O\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "  _ _ _ _ O\n" +
            "    _ _ O\n" +
            "Score: 8\n" +
            "Game over!\n" +
            "    O _ _\n" +
            "  _ _ _ _ O\n" +
            "O _ _ _ _ _ _\n" +
            "_ _ O _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "  _ _ _ _ O\n" +
            "    _ _ O\n" +
            "Score: 7\n", ap.toString());
  }


  // testing a game over with invalid moves and error inputs embedded
  @Test
  public void testGameOverWithInvalidMovesAndErrorInputsEuropean() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("4 2 4 4 2 3 4 3 yolo 2 5 2 3 -10 1 3 3 3 1 5 1 3 4 3 2 3 " +
            "1 3 " +
            "3 3 4 4 2 4 3 2 3 4 3 4 1 4 3 6 3 4 5 6 3 banana 6 3 7 3 5 5 7 3 7 3 4 3 6 3 7 3 5 " +
            "4 5 2 5 6 5 4 5 5 3 5 5 5 5 3 5 3 5 1 aryan 5 1 5 1 3 7 4 0 5 4 7 3 5 toyota 3 6 2 4" +
            " 2 5 4 5 2 5 2 3 2 2 2 4 2 4 1 go celtics 4 3");
    MarbleSolitaireView view = new MarbleSolitaireTextView(europeanModel, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(europeanModel, view, rd);
    c.playGame();
    assertTrue(europeanModel.isGameOver());
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "    O O O\n" +
            "  O _ O O O\n" +
            "O O _ O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 34\n" +
            "Invalid Input: yolo. Please re-enter that value again.\n" +
            "    O O O\n" +
            "  O O _ _ O\n" +
            "O O _ O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 33\n" +
            "Invalid Input: -10. Please re-enter that value again.\n" +
            "    _ O O\n" +
            "  O _ _ _ O\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O _ _\n" +
            "  O _ _ _ O\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "    O _ _\n" +
            "  O O _ _ O\n" +
            "O O _ O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "    _ _ _\n" +
            "  O _ _ _ O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 29\n" +
            "    _ _ _\n" +
            "  O _ O _ O\n" +
            "O O O _ O O O\n" +
            "O _ _ _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 28\n" +
            "    _ _ _\n" +
            "  O _ O _ O\n" +
            "O _ _ O O O O\n" +
            "O _ _ _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 27\n" +
            "    _ O _\n" +
            "  O _ _ _ O\n" +
            "O _ _ _ O O O\n" +
            "O _ _ _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 26\n" +
            "    _ O _\n" +
            "  O _ _ _ O\n" +
            "O _ _ O _ _ O\n" +
            "O _ _ _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 25\n" +
            "Invalid Input: banana. Please re-enter that value again.\n" +
            "    _ O _\n" +
            "  O _ _ _ O\n" +
            "O _ _ O _ O O\n" +
            "O _ _ _ O _ O\n" +
            "O O O O O _ O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 24\n" +
            "    _ O _\n" +
            "  O _ _ _ O\n" +
            "O _ _ O O _ _\n" +
            "O _ _ _ O _ O\n" +
            "O O O O O _ O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 23\n" +
            "    _ O _\n" +
            "  O _ _ _ O\n" +
            "O _ _ O O _ O\n" +
            "O _ _ _ O _ _\n" +
            "O O O O O _ _\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 22\n" +
            "    _ O _\n" +
            "  O _ _ _ O\n" +
            "O _ _ _ _ O O\n" +
            "O _ _ _ O _ _\n" +
            "O O O O O _ _\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 21\n" +
            "    _ O _\n" +
            "  O _ _ _ O\n" +
            "O _ _ _ O _ _\n" +
            "O _ _ _ O _ _\n" +
            "O O O O O _ _\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 20\n" +
            "    _ O _\n" +
            "  O _ _ O O\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O O O O _ _\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 19\n" +
            "    _ O _\n" +
            "  O _ _ O O\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ O _ _\n" +
            "O O O O _ _ _\n" +
            "  O O O _ O\n" +
            "    O O O\n" +
            "Score: 18\n" +
            "    _ O _\n" +
            "  O _ _ O O\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ O _ _\n" +
            "O O _ _ O _ _\n" +
            "  O O O _ O\n" +
            "    O O O\n" +
            "Score: 17\n" +
            "    _ O _\n" +
            "  O _ _ O O\n" +
            "O _ _ _ O _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ _ _ _ _\n" +
            "  O O O _ O\n" +
            "    O O O\n" +
            "Score: 16\n" +
            "Invalid Input: aryan. Please re-enter that value again.\n" +
            "    _ O O\n" +
            "  O _ _ _ O\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ _ _ _ _\n" +
            "  O O O _ O\n" +
            "    O O O\n" +
            "Score: 15\n" +
            "    O _ _\n" +
            "  O _ _ _ O\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ _ _ _ _\n" +
            "  O O O _ O\n" +
            "    O O O\n" +
            "Score: 14\n" +
            "Invalid Input: 0. Please re-enter that value again.\n" +
            "    O _ _\n" +
            "  O _ _ _ O\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ O _ _ _\n" +
            "  O O _ _ O\n" +
            "    O _ O\n" +
            "Score: 13\n" +
            "Invalid Input: toyota. Please re-enter that value again.\n" +
            "    O _ _\n" +
            "  O _ _ _ O\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O O O O _ _ _\n" +
            "  O _ _ _ O\n" +
            "    _ _ O\n" +
            "Score: 12\n" +
            "    O _ _\n" +
            "  O _ _ _ O\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ _ _ _ _\n" +
            "O _ O O _ _ _\n" +
            "  _ _ _ _ O\n" +
            "    _ _ O\n" +
            "Score: 11\n" +
            "    O _ _\n" +
            "  O _ _ _ O\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ _ _ _ _\n" +
            "O O _ _ _ _ _\n" +
            "  _ _ _ _ O\n" +
            "    _ _ O\n" +
            "Score: 10\n" +
            "    O _ _\n" +
            "  O _ _ _ O\n" +
            "O O _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "  _ _ _ _ O\n" +
            "    _ _ O\n" +
            "Score: 9\n" +
            "    O _ _\n" +
            "  _ _ _ _ O\n" +
            "O _ _ _ _ _ _\n" +
            "O O _ _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "  _ _ _ _ O\n" +
            "    _ _ O\n" +
            "Score: 8\n" +
            "Invalid Input: go. Please re-enter that value again.\n" +
            "Invalid Input: celtics. Please re-enter that value again.\n" +
            "Game over!\n" +
            "    O _ _\n" +
            "  _ _ _ _ O\n" +
            "O _ _ _ _ _ _\n" +
            "_ _ O _ _ _ _\n" +
            "O _ _ _ _ _ _\n" +
            "  _ _ _ _ O\n" +
            "    _ _ O\n" +
            "Score: 7\n", ap.toString());
  }





  //////////////////// TRIANGLE SOLITAIRE MODEL TESTS WITH THE CONTROLLER  ////////////////////

  private MarbleSolitaireModel tsm;
  private MarbleSolitaireView tsv;
  private MarbleSolitaireView badTsv;
  private Readable triangleReadable;
  private Readable badTriangleReadable;

  @Before
  public void initTriangle() {
    tsm = new TriangleSolitaireModel();
    tsv = new TriangleSolitaireTextView(tsm, new StringBuilder());
    badTsv = new TriangleSolitaireTextView(tsm, new CorruptAppendable());
    triangleReadable = new StringReader("");
    badTriangleReadable = new CorruptReadable("this is a corrupt readable");
  }


  // testing to see if a controller is constructed properly by running the only public method
  // available to us on a specific controller constructed in the method and ensuring it works
  @Test
  public void testValidConstructorTriangle() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("q");
    MarbleSolitaireView view = new TriangleSolitaireTextView(tsm, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(tsm, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n", ap.toString());

  }

  // testing for an exception when the constructor has a null model
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidController1Triangle() {
    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(null, tsv, triangleReadable);
  }

  // testing for an exception when the constructor has a null view
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidController2Triangle() {
    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(tsm, null, triangleReadable);
  }

  // testing for an exception when the constructor has a null readable
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidController3Triangle() {
    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(tsm, tsv, null);
  }

  // testing for an exception when the constructor has a null model and view
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidController4Triangle() {
    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(null, null, triangleReadable);
  }

  // testing for an exception when the constructor has a null model and readable
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidController5Triangle() {
    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(null, tsv, null);
  }

  // testing for an exception when the constructor has a null view and readable
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidController6Triangle() {
    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(tsm, null, null);
  }

  ////////////////////////////// tests for the playGame method //////////////////////////////

  // testing for an exception when a controller tries to read from a corrupt readable when the
  // playGame method is called
  @Test(expected = IllegalStateException.class)
  public void testBadReadableTriangle() {
    MarbleSolitaireController badController = new MarbleSolitaireControllerImpl(tsm,
            tsv, badTriangleReadable);
    badController.playGame();
  }

  // testing for an exception when a controller tries to read from a corrupt readable when the
  // playGame method is called
  @Test(expected = IllegalStateException.class)
  public void testBadTransmissionTriangle() {
    MarbleSolitaireController badController = new MarbleSolitaireControllerImpl(tsm,
            badTsv, triangleReadable);
    badController.playGame();
  }


  /////////////////// testing different sequences with no quit and no game over ////////////////

  // testing a valid move with no q at the end
  @Test(expected = IllegalStateException.class)
  public void testValidMoveNoEndTriangle() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("3 1 1 1");
    MarbleSolitaireView view = new TriangleSolitaireTextView(tsm, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(tsm, view, rd);
    c.playGame();
  }

  // testing an invalid move with no q at the end
  @Test(expected = IllegalStateException.class)
  public void testInvalidMoveNoEndTriangle() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("1 1 1 2");
    MarbleSolitaireView view = new TriangleSolitaireTextView(tsm, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(tsm, view, rd);
    c.playGame();
  }

  // testing a negative input with no q at the end
  @Test(expected = IllegalStateException.class)
  public void testNegativeInputNoEndTriangle() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("-20");
    MarbleSolitaireView view = new TriangleSolitaireTextView(tsm, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(tsm, view, rd);
    c.playGame();
  }

  // testing an error string with no q at the end
  @Test(expected = IllegalStateException.class)
  public void testErrorStringNoEndTriangle() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("aryan");
    MarbleSolitaireView view = new TriangleSolitaireTextView(tsm, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(tsm, view, rd);
    c.playGame();
  }

  // testing multiple different input types with no end
  @Test(expected = IllegalStateException.class)
  public void testMultipleInputTypesNoEndTriangle() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("aryan -20 3 1 1 1 1 3 10 17 25");
    MarbleSolitaireView view = new TriangleSolitaireTextView(tsm, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(tsm, view, rd);
    c.playGame();
  }

  // testing an incomplete move with no q at the end
  @Test(expected = IllegalStateException.class)
  public void testIncompleteMoveNoEndTriangle() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("3 1 1");
    MarbleSolitaireView view = new TriangleSolitaireTextView(tsm, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(tsm, view, rd);
    c.playGame();
  }


  ////////////////////////// testing different sequences with a q at the end /////////////////////

  // testing quitting the game
  @Test
  public void testQuitTriangle() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("q");
    MarbleSolitaireView view = new TriangleSolitaireTextView(tsm, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(tsm, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n", ap.toString());
  }

  // testing quitting the game with a capital q
  @Test
  public void testQuitCapitalQTriangle() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("Q");
    MarbleSolitaireView view = new TriangleSolitaireTextView(tsm, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(tsm, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n", ap.toString());
  }

  // testing quitting the game after a valid number is inputted
  @Test
  public void testQuitAfter1InputTriangle() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("3 q");
    MarbleSolitaireView view = new TriangleSolitaireTextView(tsm, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(tsm, view, rd);
    c.playGame();
    assertEquals("\n" +
            "Welcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n", ap.toString());
  }

  // testing quitting the game after 2 valid numbers are inputted
  @Test
  public void testQuitAfter2InputsTriangle() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("3 1 q");
    MarbleSolitaireView view = new TriangleSolitaireTextView(tsm, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(tsm, view, rd);
    c.playGame();
    assertEquals("\n" +
            "Welcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to" +
            " represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n", ap.toString());
  }

  // testing quitting the game after 3 valid numbers are inputted
  @Test
  public void testQuitAfter3InputsTriangle() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("3 1 1 q");
    MarbleSolitaireView view = new TriangleSolitaireTextView(tsm, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(tsm, view, rd);
    c.playGame();
    assertEquals("\n" +
            "Welcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that" +
            " represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n", ap.toString());
  }

  // testing a q in the toRow position
  @Test
  public void testQuitInToRowPositionTriangle() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("3 1 q 1");
    MarbleSolitaireView view = new TriangleSolitaireTextView(tsm, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(tsm, view, rd);
    c.playGame();
    assertEquals("\n" +
            "Welcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n", ap.toString());
  }

  // testing a q in the fromRow position
  @Test
  public void testQuitInFromRowPositionTriangle() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("q 1 1 1");
    MarbleSolitaireView view = new TriangleSolitaireTextView(tsm, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(tsm, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n", ap.toString());
  }

  // testing a q in the fromCol position
  @Test
  public void testQuitInFromColPositionTriangle() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("3 q 1 1");
    MarbleSolitaireView view = new TriangleSolitaireTextView(tsm, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(tsm, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n", ap.toString());
  }

  // testing a valid move
  @Test
  public void testValidMoveTriangle() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("3 1 1 1 q");
    MarbleSolitaireView view = new TriangleSolitaireTextView(tsm, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(tsm, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n", ap.toString());
  }

  // testing a valid move with returns in between the readable inputs
  @Test
  public void testValidMoveWithReturnSequenceTriangle() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("3\n1\n1\n1\nq");
    MarbleSolitaireView view = new TriangleSolitaireTextView(tsm, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(tsm, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n", ap.toString());
  }

  // testing an invalid move
  @Test
  public void testInvalidMoveTriangle() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("1 2 3 4 q");
    MarbleSolitaireView view = new TriangleSolitaireTextView(tsm, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(tsm, view, rd);
    c.playGame();
    assertEquals("\n" +
            "Welcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to" +
            " represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Invalid move. Play again.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n", ap.toString());
  }

  // testing a negative number input
  @Test
  public void testNegativeNumberInputTriangle() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("-5 q");
    MarbleSolitaireView view = new TriangleSolitaireTextView(tsm, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(tsm, view, rd);
    c.playGame();
    assertEquals("\n" +
            "Welcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that" +
            " represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to" +
            " represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Invalid Input: -5. Please re-enter that value again.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n", ap.toString());
  }

  // test a string input that isn't q
  @Test
  public void testErrorStringInputTriangle() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("aryan q");
    MarbleSolitaireView view = new TriangleSolitaireTextView(tsm, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(tsm, view, rd);
    c.playGame();
    assertEquals("\n" +
            "Welcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that" +
            " represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Invalid Input: aryan. Please re-enter that value again.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n", ap.toString());
  }

  // test a string input that isn't q
  @Test
  public void testMultipleInputsWithEndTriangle() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("aryan 3 1 1 1 1 1 1 1 -45 a b c 5 100 q");
    MarbleSolitaireView view = new TriangleSolitaireTextView(tsm, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(tsm, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to" +
            " represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Invalid Input: aryan. Please re-enter that value again.\n" +
            "    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n" +
            "Invalid move. Play again.\n" +
            "Invalid Input: -45. Please re-enter that value again.\n" +
            "Invalid Input: a. Please re-enter that value again.\n" +
            "Invalid Input: b. Please re-enter that value again.\n" +
            "Invalid Input: c. Please re-enter that value again.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n", ap.toString());
  }

  // testing when we have input after a q
  @Test
  public void testInputAfterQTriangle() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("q aryan 1 1 1 1 3 1 1 1 wow did I win -25");
    MarbleSolitaireView view = new TriangleSolitaireTextView(tsm, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(tsm, view, rd);
    c.playGame();
    assertEquals("\n" +
            "Welcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that" +
            " represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n", ap.toString());
  }

  // testing when we have an invalid input amongst 4 numbers of a valid move
  @Test
  public void testInvalidAmongstValidTriangle() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader(" 3 1 apple 1 1 q");
    MarbleSolitaireView view = new TriangleSolitaireTextView(tsm, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(tsm, view, rd);
    c.playGame();
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that" +
            " represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Invalid Input: apple. Please re-enter that value again.\n" +
            "    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n", ap.toString());
  }


  ////////////////////////////// testing different sequences of game over /////////////////////////

  // testing a game over input with correct and valid inputs
  @Test
  public void testGameOverTriangle() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("3 1 1 1 3 3 3 1 1 1 3 3 4 1 2 1 4 4 2 2 5 2 3 2 5 4 5 2 3 2 5" +
            " 4 5 1 5 3 5 4 5 2 q");
    MarbleSolitaireView view = new TriangleSolitaireTextView(tsm, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(tsm, view, rd);
    c.playGame();
    assertTrue(tsm.isGameOver());
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that" +
            " represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to " +
            "represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n" +
            "    O\n" +
            "   _ O\n" +
            "  O _ _\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 12\n" +
            "    _\n" +
            "   _ _\n" +
            "  O _ O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 11\n" +
            "    _\n" +
            "   O _\n" +
            "  _ _ O\n" +
            " _ O O O\n" +
            "O O O O O\n" +
            "Score: 10\n" +
            "    _\n" +
            "   O O\n" +
            "  _ _ _\n" +
            " _ O O _\n" +
            "O O O O O\n" +
            "Score: 9\n" +
            "    _\n" +
            "   O O\n" +
            "  _ O _\n" +
            " _ _ O _\n" +
            "O _ O O O\n" +
            "Score: 8\n" +
            "    _\n" +
            "   O O\n" +
            "  _ O _\n" +
            " _ _ O _\n" +
            "O O _ _ O\n" +
            "Score: 7\n" +
            "    _\n" +
            "   O O\n" +
            "  _ _ _\n" +
            " _ _ _ _\n" +
            "O O _ O O\n" +
            "Score: 6\n" +
            "    _\n" +
            "   O O\n" +
            "  _ _ _\n" +
            " _ _ _ _\n" +
            "_ _ O O O\n" +
            "Score: 5\n" +
            "Game over!\n" +
            "    _\n" +
            "   O O\n" +
            "  _ _ _\n" +
            " _ _ _ _\n" +
            "_ O _ _ O\n" +
            "Score: 4\n", ap.toString());
  }


  // testing a game over with invalid moves and error inputs embedded
  @Test
  public void testGameOverWithInvalidMovesAndErrorInputsTriangle() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("3 1 1 1 3 apple 3 3 1 1 banana 1 3 3 4 1 -10 2 1 4 4 2 2 5 2" +
            " 0 3 2 5 4 -900 5 2 3 2 5 4 5 1 celtics 5 3 5 4 5 2 q");
    MarbleSolitaireView view = new TriangleSolitaireTextView(tsm, ap);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(tsm, view, rd);
    c.playGame();
    assertTrue(tsm.isGameOver());
    assertEquals("\nWelcome to Marble Solitaire!\n" +
            "In order to move, enter 4 numbers with a space or return between them that " +
            "represent the following:\n" +
            "1. fromRow  2. fromColumn  3. toRow  4. toColumn.\n" +
            "The numbers should begin at 1 to represent the first row/column, 2 to" +
            " represent the second row/column, and so on.\n" +
            "Enter q at any moment to quit the game. Have fun!\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n" +
            "Invalid Input: apple. Please re-enter that value again.\n" +
            "    O\n" +
            "   _ O\n" +
            "  O _ _\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 12\n" +
            "Invalid Input: banana. Please re-enter that value again.\n" +
            "    _\n" +
            "   _ _\n" +
            "  O _ O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 11\n" +
            "Invalid Input: -10. Please re-enter that value again.\n" +
            "    _\n" +
            "   O _\n" +
            "  _ _ O\n" +
            " _ O O O\n" +
            "O O O O O\n" +
            "Score: 10\n" +
            "    _\n" +
            "   O O\n" +
            "  _ _ _\n" +
            " _ O O _\n" +
            "O O O O O\n" +
            "Score: 9\n" +
            "Invalid Input: 0. Please re-enter that value again.\n" +
            "    _\n" +
            "   O O\n" +
            "  _ O _\n" +
            " _ _ O _\n" +
            "O _ O O O\n" +
            "Score: 8\n" +
            "Invalid Input: -900. Please re-enter that value again.\n" +
            "    _\n" +
            "   O O\n" +
            "  _ O _\n" +
            " _ _ O _\n" +
            "O O _ _ O\n" +
            "Score: 7\n" +
            "    _\n" +
            "   O O\n" +
            "  _ _ _\n" +
            " _ _ _ _\n" +
            "O O _ O O\n" +
            "Score: 6\n" +
            "Invalid Input: celtics. Please re-enter that value again.\n" +
            "    _\n" +
            "   O O\n" +
            "  _ _ _\n" +
            " _ _ _ _\n" +
            "_ _ O O O\n" +
            "Score: 5\n" +
            "Game over!\n" +
            "    _\n" +
            "   O O\n" +
            "  _ _ _\n" +
            " _ _ _ _\n" +
            "_ O _ _ O\n" +
            "Score: 4\n", ap.toString());
  }





  ///////////////// tests for the mock ////////////////////////

  // Things to note:
  // -  The controller asks the model for the score at the beginning
  // -  The controller asks the model if the game is over every time it reads an input

  // testing that the correct inputs were passed to the model for a valid move
  @Test
  public void testInputsOfValidMove() {
    Readable in = new StringReader("4 2 4 4 q");
    StringBuilder log = new StringBuilder();
    MarbleSolitaireModel mock = new ConfirmInputsMock(log);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(mock, englishView, in);

    controller.playGame();
    assertEquals("Controller obtained the score from the model.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    // MOVE REPRESENTED BY THE LINE BELOW
                    "A move was attempted from row: 3 and column: 1 to row: 3 and column: 3.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller obtained the score from the model.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller obtained the score from the model.\n",
            log.toString());
  }

  // testing inputs of moves when the game is ended AND MANUALLY INPUTTING Q AT THE END since
  // isGameOver always returns false after logging the method call
  @Test
  public void testInputsOfGameOverSequence() {
    Readable in = new StringReader("4 2 4 4 2 3 4 3 2 5 2 3 1 3 3 3 1 5 1 3 4 3 2 3 1 3 3 3 4 4 "
            + "2 4 3 2 3 4 3 4 1 4 3 6 3 4 5 6 3 6 3 7 3 5 5 7 3 7 3 4 3 6 3 7 3 5 4 5 2 5 6 5 4 "
            + "5 5 3 5 5 5 5 3 5 3 5 1 5 1 5 1 3 7 4 5 4 7 3 5 3 5 3 5 5 5 1 5 3 3 1 5 1 q");
    StringBuilder log = new StringBuilder();
    MarbleSolitaireModel mock = new ConfirmInputsMock(log);
    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(mock, europeanView, in);

    controller.playGame();
    assertEquals("Controller obtained the score from the model.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "A move was attempted from row: 3 and column: 1 to row: 3 and column: 3.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller obtained the score from the model.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "A move was attempted from row: 1 and column: 2 to row: 3 and column: 2.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller obtained the score from the model.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "A move was attempted from row: 1 and column: 4 to row: 1 and column: 2.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller obtained the score from the model.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "A move was attempted from row: 0 and column: 2 to row: 2 and column: 2.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller obtained the score from the model.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "A move was attempted from row: 0 and column: 4 to row: 0 and column: 2.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller obtained the score from the model.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "A move was attempted from row: 3 and column: 2 to row: 1 and column: 2.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller obtained the score from the model.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "A move was attempted from row: 0 and column: 2 to row: 2 and column: 2.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller obtained the score from the model.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "A move was attempted from row: 3 and column: 3 to row: 1 and column: 3.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller obtained the score from the model.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "A move was attempted from row: 2 and column: 1 to row: 2 and column: 3.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller obtained the score from the model.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "A move was attempted from row: 2 and column: 3 to row: 0 and column: 3.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller obtained the score from the model.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "A move was attempted from row: 2 and column: 5 to row: 2 and column: 3.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller obtained the score from the model.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "A move was attempted from row: 4 and column: 5 to row: 2 and column: 5.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller obtained the score from the model.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "A move was attempted from row: 2 and column: 6 to row: 2 and column: 4.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller obtained the score from the model.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "A move was attempted from row: 4 and column: 6 to row: 2 and column: 6.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller obtained the score from the model.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "A move was attempted from row: 2 and column: 3 to row: 2 and column: 5.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller obtained the score from the model.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "A move was attempted from row: 2 and column: 6 to row: 2 and column: 4.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller obtained the score from the model.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "A move was attempted from row: 3 and column: 4 to row: 1 and column: 4.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller obtained the score from the model.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "A move was attempted from row: 5 and column: 4 to row: 3 and column: 4.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller obtained the score from the model.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "A move was attempted from row: 4 and column: 2 to row: 4 and column: 4.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller obtained the score from the model.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "A move was attempted from row: 4 and column: 4 to row: 2 and column: 4.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller obtained the score from the model.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "A move was attempted from row: 2 and column: 4 to row: 0 and column: 4.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller obtained the score from the model.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "A move was attempted from row: 0 and column: 4 to row: 0 and column: 2.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller obtained the score from the model.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "A move was attempted from row: 6 and column: 3 to row: 4 and column: 3.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller obtained the score from the model.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "A move was attempted from row: 6 and column: 2 to row: 4 and column: 2.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller obtained the score from the model.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "A move was attempted from row: 4 and column: 2 to row: 4 and column: 4.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller obtained the score from the model.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "A move was attempted from row: 4 and column: 0 to row: 4 and column: 2.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller obtained the score from the model.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "A move was attempted from row: 2 and column: 0 to row: 4 and column: 0.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller obtained the score from the model.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller obtained the score from the model.\n",
            log.toString());
  }

  // testing that nothing is passed to the model to move while there are only 3 valid numbers
  @Test
  public void testEmptyInputWhenOnly3Numbers() {
    Readable in = new StringReader("4 2 4 -10 q");
    StringBuilder log = new StringBuilder();
    MarbleSolitaireModel mock = new ConfirmInputsMock(log);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(mock, englishView, in);

    controller.playGame();
    assertEquals("Controller obtained the score from the model.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller obtained the score from the model.\n",
            log.toString());
  }

  // testing that invalid numbers and inputs are ignored amongst valid numbers and only the valid
  // numbers are passed to the model
  @Test
  public void testControllerNotPassingInvalidInputsToModel() {
    Readable in = new StringReader("4 -10 2 aryan 4 banana yolo quit 4 q");
    StringBuilder log = new StringBuilder();
    MarbleSolitaireModel mock = new ConfirmInputsMock(log);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(mock, englishView, in);

    controller.playGame();
    assertEquals("Controller obtained the score from the model.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    // MOVE REPRESENTED BY THE LINE BELOW
                    "A move was attempted from row: 3 and column: 1 to row: 3 and column: 3.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller obtained the score from the model.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller obtained the score from the model.\n",
            log.toString());
  }

  // testing that invalid numbers and inputs are ignored completely when there are no valid inputs
  @Test
  public void testControllerNotPassingOnlyInvalidInputsToModel() {
    Readable in = new StringReader("hi my name is aryan and i am -10 years old but my friend is 0" +
            " and my other friend is 0.5 years old! q");
    StringBuilder log = new StringBuilder();
    MarbleSolitaireModel mock = new ConfirmInputsMock(log);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(mock, englishView, in);

    controller.playGame();
    assertEquals("Controller obtained the score from the model.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller asked if the game is over.\n" +
                    "Controller obtained the score from the model.\n",
            log.toString());
  }
}

















