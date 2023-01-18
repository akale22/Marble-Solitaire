import org.junit.Before;
import org.junit.Test;

import java.io.IOException;


import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;
import helpers.CorruptAppendable;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for {@link TriangleSolitaireTextViewTest}s.
 */
public class TriangleSolitaireTextViewTest {

  private MarbleSolitaireModelState tsm1 =
          new TriangleSolitaireModel();
  private MarbleSolitaireModelState tsm2 =
          new TriangleSolitaireModel(6);
  private MarbleSolitaireModelState tsm3 =
          new TriangleSolitaireModel(3, 3);
  private MarbleSolitaireModelState tsm4 =
          new TriangleSolitaireModel(10, 9, 8);
  private MarbleSolitaireModel tsmWithMoveDone =
          new TriangleSolitaireModel();

  private MarbleSolitaireView tsmView1;
  private MarbleSolitaireView tsmView2;
  private MarbleSolitaireView tsmView3;
  private MarbleSolitaireView tsmView4;
  private MarbleSolitaireView tsmViewWithMoveDone;

  private MarbleSolitaireView tsmViewWithModelAppendable;
  private MarbleSolitaireView tsmViewWithModelAppendableMoveDone;

  private Appendable goodAppendable;
  private Appendable badAppendable;


  @Before
  public void init() {

    tsmView1 =
            new TriangleSolitaireTextView(tsm1);
    tsmView2 =
            new TriangleSolitaireTextView(tsm2);
    tsmView3 =
            new TriangleSolitaireTextView(tsm3);
    tsmView4 =
            new TriangleSolitaireTextView(tsm4);

    // performing a move on one of the models and then passing that into the view
    tsmWithMoveDone.move(2, 0, 0, 0);

    tsmViewWithMoveDone = new TriangleSolitaireTextView(tsmWithMoveDone);

    // views constructed using the constructor that also takes in a specific appendable
    goodAppendable = new StringBuilder();
    badAppendable = new CorruptAppendable();
    tsmViewWithModelAppendable =
            new TriangleSolitaireTextView(this.tsm1, goodAppendable);
    tsmViewWithModelAppendableMoveDone =
            new TriangleSolitaireTextView(this.tsmWithMoveDone, goodAppendable);
  }



  // testing properties of a view with the constructor that was constructed properly when given a
  // model
  @Test
  public void testValidConstructor() {
    // since the fields are private, we must use the public methods in the interface to ensure
    // that the object was constructed properly (this is a little redundant with the tests for
    // the public methods in the MarbleSolitaireView interface)
    assertEquals(
            "    _\n" +
                    "   O O\n" +
                    "  O O O\n" +
                    " O O O O\n" +
                    "O O O O O",
            this.tsmView1.toString());
  }

  // testing for an exception when the view is passed a model state which is null
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    MarbleSolitaireView view = new TriangleSolitaireTextView(null);
  }

  // testing properties of a view with the constructor that was constructor properly when given a
  // model and an appendable
  @Test
  public void testValidConstructorModelAndAppendable() {
    // since the fields are private, we must use the public methods in the interface to ensure
    // that the object was constructed properly (this is a little redundant with the tests for
    // the public methods in the MarbleSolitaireView interface)
    assertEquals(
            "    _\n" +
                    "   O O\n" +
                    "  O O O\n" +
                    " O O O O\n" +
                    "O O O O O",
            this.tsmViewWithModelAppendable.toString());
  }

  // testing for an exception when the view is passed a model state which is null but an
  // appendable which is not null
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor2() {
    MarbleSolitaireView view = new TriangleSolitaireTextView(null, goodAppendable);
  }

  // testing for an exception when the view is passed a model state which is not null but an
  // appendable which is null
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor3() {
    MarbleSolitaireView view = new TriangleSolitaireTextView(this.tsm1, null);
  }

  // testing for an exception when the view is passed a model state and an appendable which are
  // both null
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor4() {
    MarbleSolitaireView view = new TriangleSolitaireTextView(null, null);
  }


  // testing the toString method on a view containing a model made by the default constructor
  @Test
  public void testToStringForDefaultConstructor() {
    assertEquals(
            "    _\n" +
                    "   O O\n" +
                    "  O O O\n" +
                    " O O O O\n" +
                    "O O O O O",
            this.tsmView1.toString());
  }

  // testing the toString method on a view containing a model with an armLength of 6
  @Test
  public void testToStringForSpecifiedArmThicknessConstructor() {
    assertEquals(
            "     _\n" +
                    "    O O\n" +
                    "   O O O\n" +
                    "  O O O O\n" +
                    " O O O O O\n" +
                    "O O O O O O",
            this.tsmView2.toString());
  }

  // testing the toString method on a view containing a model with a specified empty slot
  @Test
  public void testToStringForSpecifiedEmptySlotConstructor() {
    assertEquals(
            "    O\n" +
                    "   O O\n" +
                    "  O O O\n" +
                    " O O O _\n" +
                    "O O O O O",
            this.tsmView3.toString());
  }

  // testing the toString method on a view containing a model with an armLength of 10 and a
  // specified empty slot
  @Test
  public void testToStringForSpecifiedArmLengthAndEmptySlotConstructor() {
    assertEquals(
            "         O\n" +
                    "        O O\n" +
                    "       O O O\n" +
                    "      O O O O\n" +
                    "     O O O O O\n" +
                    "    O O O O O O\n" +
                    "   O O O O O O O\n" +
                    "  O O O O O O O O\n" +
                    " O O O O O O O O O\n" +
                    "O O O O O O O O _ O",
            this.tsmView4.toString());
  }

  // testing the toString method on a view containing a model where a valid move has been done
  // and there are now more than 1 empty slots
  @Test
  public void testToStringForViewWithValidMoveDone() {
    assertEquals(
            "    O\n" +
                    "   _ O\n" +
                    "  _ O O\n" +
                    " O O O O\n" +
                    "O O O O O",
            this.tsmViewWithMoveDone.toString());
  }

  // testing the toString method on a view constructed with a model and an appendable
  @Test
  public void testToStringForViewWithModelAndAppendable() {
    assertEquals(
            "    _\n" +
                    "   O O\n" +
                    "  O O O\n" +
                    " O O O O\n" +
                    "O O O O O",
            this.tsmViewWithModelAppendable.toString());
  }

  // testing the toString method on a view constructed with a model and an appendable where the
  // model has had a valid move done
  @Test
  public void testToStringForViewWithModelAndAppendableWithValidMoveDone() {
    assertEquals(
            "    O\n" +
                    "   _ O\n" +
                    "  _ O O\n" +
                    " O O O O\n" +
                    "O O O O O",
            this.tsmViewWithModelAppendableMoveDone.toString());
  }


  // testing that renderBoard works correctly at the start of a game
  @Test
  public void testRenderBoard() throws IOException {
    tsmViewWithModelAppendable.renderBoard();
    assertEquals("    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O", goodAppendable.toString());
  }

  // testing that renderBoard works correctly after a move has been performed on the model
  @Test
  public void testRenderBoardAfterMove() throws IOException {
    tsmViewWithModelAppendableMoveDone.renderBoard();
    assertEquals("    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O", goodAppendable.toString());
  }

  // testing for an exception when renderBoard has trouble transmitting an output
  @Test(expected = IOException.class)
  public void testFailedRenderBoard() throws IOException {
    MarbleSolitaireView view = new TriangleSolitaireTextView(tsm1,
            badAppendable);
    view.renderBoard();
  }


  // testing that renderMessage works with an empty string
  @Test
  public void testRenderMessage() throws IOException {
    MarbleSolitaireView view = new TriangleSolitaireTextView(tsm1,
            goodAppendable);
    view.renderMessage("");
    assertEquals("", goodAppendable.toString());
  }

  // testing that renderMessage works with a word
  @Test
  public void testRenderMessage2() throws IOException {
    MarbleSolitaireView view = new TriangleSolitaireTextView(tsm1,
            goodAppendable);
    view.renderMessage("hello");
    assertEquals("hello", goodAppendable.toString());
  }

  // testing that renderMessage works with a full sentence
  @Test
  public void testRenderMessage3() throws IOException {
    MarbleSolitaireView view = new TriangleSolitaireTextView(tsm1,
            goodAppendable);
    view.renderMessage("hi I am aryan and I love the celtics");
    assertEquals("hi I am aryan and I love the celtics", goodAppendable.toString());
  }

  // testing for an exception when renderMessage has trouble transmitting an output
  @Test(expected = IOException.class)
  public void testFailedRenderMessage() throws IOException {
    MarbleSolitaireView view = new TriangleSolitaireTextView(tsm1,
            badAppendable);
    view.renderMessage("hi");
  }
}



