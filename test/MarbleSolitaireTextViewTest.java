import org.junit.Before;
import org.junit.Test;

import java.io.IOException;


import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import helpers.CorruptAppendable;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for {@link MarbleSolitaireTextView}s.
 */
public class MarbleSolitaireTextViewTest {

  private MarbleSolitaireModelState englishModel1 =
          new EnglishSolitaireModel();
  private MarbleSolitaireModelState englishModel2 =
          new EnglishSolitaireModel(5);
  private MarbleSolitaireModelState englishModel3 =
          new EnglishSolitaireModel(4, 1);
  private MarbleSolitaireModelState englishModel4 =
          new EnglishSolitaireModel(5, 9, 8);
  private MarbleSolitaireModel englishModelWithMoveDone =
          new EnglishSolitaireModel();


  private MarbleSolitaireView englishView1;
  private MarbleSolitaireView englishView2;
  private MarbleSolitaireView englishView3;
  private MarbleSolitaireView englishView4;
  private MarbleSolitaireView englishViewWithMoveDone;

  private MarbleSolitaireView englishViewWithModelAppendable;
  private MarbleSolitaireView englishViewWithModelAppendableMoveDone;

  private Appendable goodEnglishAppendable;
  private Appendable badEnglishAppendable;



  @Before
  public void initEnglish() {

    englishView1 =
            new MarbleSolitaireTextView(this.englishModel1);
    englishView2 =
            new MarbleSolitaireTextView(englishModel2);
    englishView3 =
            new MarbleSolitaireTextView(englishModel3);
    englishView4 =
            new MarbleSolitaireTextView(englishModel4);

    // performing a move on one of the models and then passing that into the view
    englishModelWithMoveDone.move(3, 1, 3, 3);

    englishViewWithMoveDone = new MarbleSolitaireTextView(englishModelWithMoveDone);

    // views constructed using the constructor that also takes in a specific appendable
    goodEnglishAppendable = new StringBuilder();
    badEnglishAppendable = new CorruptAppendable();
    englishViewWithModelAppendable =
            new MarbleSolitaireTextView(this.englishModel1, goodEnglishAppendable);
    englishViewWithModelAppendableMoveDone =
            new MarbleSolitaireTextView(this.englishModelWithMoveDone, goodEnglishAppendable);

  }


  ////////////////////////// Tests with English Solitaire Models /////////////////////////

  // testing properties of a view with the constructor that was constructed properly when given a
  // model
  @Test
  public void testValidConstructorWithEnglishModel() {
    // since the fields are private, we must use the public methods in the interface to ensure
    // that the object was constructed properly (this is a little redundant with the tests for
    // the public methods in the MarbleSolitaireView interface)
    assertEquals(
            "    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O O O _ O O O\n"
                    + "O O O O O O O\n"
                    + "    O O O\n"
                    + "    O O O",
            this.englishView1.toString());
  }

  // testing for an exception when the view is passed a model state which is null
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorEnglish() {
    MarbleSolitaireView view = new MarbleSolitaireTextView(null);
  }

  // testing properties of a view with the constructor that was constructor properly when given a
  // model and an appendable
  @Test
  public void testValidConstructorModelAndAppendableEnglish() {
    // since the fields are private, we must use the public methods in the interface to ensure
    // that the object was constructed properly (this is a little redundant with the tests for
    // the public methods in the MarbleSolitaireView interface)
    assertEquals(
            "    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O O O _ O O O\n"
                    + "O O O O O O O\n"
                    + "    O O O\n"
                    + "    O O O",
            this.englishViewWithModelAppendable.toString());
  }

  // testing for an exception when the view is passed a model state which is null but an
  // appendable which is not null
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor2English() {
    MarbleSolitaireView view = new MarbleSolitaireTextView(null, goodEnglishAppendable);
  }

  // testing for an exception when the view is passed a model state which is not null but an
  // appendable which is null
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor3English() {
    MarbleSolitaireView view = new MarbleSolitaireTextView(this.englishModel1, null);
  }

  // testing for an exception when the view is passed a model state and an appendable which are
  // both null
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor4English() {
    MarbleSolitaireView view = new MarbleSolitaireTextView(null, null);
  }




  // testing the toString method on a view containing a model made by the default constructor
  @Test
  public void testToStringForDefaultConstructorEnglish() {
    assertEquals(
             "    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O O O _ O O O\n"
                    + "O O O O O O O\n"
                    + "    O O O\n"
                    + "    O O O", this.englishView1.toString());
  }

  // testing the toString method on a view containing a model with an armLength of 5
  @Test
  public void testToStringForSpecifiedArmThicknessConstructorEnglish() {
    assertEquals(
            "        O O O O O\n"
                   + "        O O O O O\n"
                   + "        O O O O O\n"
                   + "        O O O O O\n"
                   + "O O O O O O O O O O O O O\n"
                   + "O O O O O O O O O O O O O\n"
                   + "O O O O O O _ O O O O O O\n"
                   + "O O O O O O O O O O O O O\n"
                   + "O O O O O O O O O O O O O\n"
                   + "        O O O O O\n"
                   + "        O O O O O\n"
                   + "        O O O O O\n"
                   + "        O O O O O",
            this.englishView2.toString());
  }

  // testing the toString method on a view containing a model with a specified empty slot
  @Test
  public void testToStringForSpecifiedEmptySlotConstructorEnglish() {
    assertEquals(
            "    O O O\n"
                   + "    O O O\n"
                   + "O O O O O O O\n"
                   + "O O O O O O O\n"
                   + "O _ O O O O O\n"
                   + "    O O O\n"
                   + "    O O O",
            this.englishView3.toString());
  }

  // testing the toString method on a view containing a model with an armLength of 5 and a
  // specified empty slot
  @Test
  public void testToStringForSpecifiedArmLengthAndEmptySlotConstructorEnglish() {
    assertEquals(
            "        O O O O O\n"
                   + "        O O O O O\n"
                   + "        O O O O O\n"
                   + "        O O O O O\n"
                   + "O O O O O O O O O O O O O\n"
                   + "O O O O O O O O O O O O O\n"
                   + "O O O O O O O O O O O O O\n"
                   + "O O O O O O O O O O O O O\n"
                   + "O O O O O O O O O O O O O\n"
                   + "        O O O O _\n"
                   + "        O O O O O\n"
                   + "        O O O O O\n"
                   + "        O O O O O",
            this.englishView4.toString());
  }

  // testing the toString method on a view containing a model where a valid move has been done
  // and there are now more than 1 empty slots
  @Test
  public void testToStringForViewWithValidMoveDoneEnglish() {
    assertEquals(
            "    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O _ _ O O O O\n"
                    + "O O O O O O O\n"
                    + "    O O O\n"
                    + "    O O O",
            this.englishViewWithMoveDone.toString());
  }

  // testing the toString method on a view constructed with a model and an appendable
  @Test
  public void testToStringForViewWithModelAndAppendableEnglish() {
    assertEquals(
            "    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O O O _ O O O\n"
                    + "O O O O O O O\n"
                    + "    O O O\n"
                    + "    O O O",
            this.englishViewWithModelAppendable.toString());
  }

  // testing the toString method on a view constructed with a model and an appendable where the
  // model has had a valid move done
  @Test
  public void testToStringForViewWithModelAndAppendableWithValidMoveDoneEnglish() {
    assertEquals(
            "    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O _ _ O O O O\n"
                    + "O O O O O O O\n"
                    + "    O O O\n"
                    + "    O O O",
            this.englishViewWithModelAppendableMoveDone.toString());
  }






  // testing that renderBoard works correctly at the start of a game
  @Test
  public void testRenderBoardEnglish() throws IOException {
    englishViewWithModelAppendable.renderBoard();
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", goodEnglishAppendable.toString());
  }

  // testing that renderBoard works correctly after a move has been performed on the model
  @Test
  public void testRenderBoardAfterMoveEnglish() throws IOException {
    englishViewWithModelAppendableMoveDone.renderBoard();
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", goodEnglishAppendable.toString());
  }

  // testing for an exception when renderBoard has trouble transmitting an output
  @Test(expected = IOException.class)
  public void testFailedRenderBoardEnglish() throws IOException {
    MarbleSolitaireView view = new MarbleSolitaireTextView(englishModelWithMoveDone,
            badEnglishAppendable);
    view.renderBoard();
  }





  // testing that renderMessage works with an empty string
  @Test
  public void testRenderMessageEnglish() throws IOException {
    MarbleSolitaireView view = new MarbleSolitaireTextView(englishModel1,
            goodEnglishAppendable);
    view.renderMessage("");
    assertEquals("", goodEnglishAppendable.toString());
  }

  // testing that renderMessage works with a word
  @Test
  public void testRenderMessage2English() throws IOException {
    MarbleSolitaireView view = new MarbleSolitaireTextView(englishModel1,
            goodEnglishAppendable);
    view.renderMessage("hello");
    assertEquals("hello", goodEnglishAppendable.toString());
  }

  // testing that renderMessage works with a full sentence
  @Test
  public void testRenderMessage3English() throws IOException {
    MarbleSolitaireView view = new MarbleSolitaireTextView(englishModel1,
            goodEnglishAppendable);
    view.renderMessage("hi I am aryan and I love the celtics");
    assertEquals("hi I am aryan and I love the celtics", goodEnglishAppendable.toString());
  }

  // testing for an exception when renderMessage has trouble transmitting an output
  @Test(expected = IOException.class)
  public void testFailedRenderMessageEnglish() throws IOException {
    MarbleSolitaireView view = new MarbleSolitaireTextView(englishModelWithMoveDone,
            badEnglishAppendable);
    view.renderMessage("hi");
  }









  ////////////////////////// Tests with European Solitaire Models /////////////////////////


  private MarbleSolitaireModelState europeanModel1 =
          new EuropeanSolitaireModel();
  private MarbleSolitaireModelState europeanModel2 =
          new EuropeanSolitaireModel(5);
  private MarbleSolitaireModelState europeanModel3 =
          new EuropeanSolitaireModel(4, 1);
  private MarbleSolitaireModelState europeanModel4 =
          new EuropeanSolitaireModel(5, 9, 8);
  private MarbleSolitaireModel europeanModelWithMoveDone =
          new EuropeanSolitaireModel();


  private MarbleSolitaireView europeanView1;
  private MarbleSolitaireView europeanView2;
  private MarbleSolitaireView europeanView3;
  private MarbleSolitaireView europeanView4;
  private MarbleSolitaireView europeanViewWithMoveDone;

  private MarbleSolitaireView europeanViewWithModelAppendable;
  private MarbleSolitaireView europeanViewWithModelAppendableMoveDone;
  private Appendable goodEuropeanAppendable;
  private Appendable badEuropeanAppendable;

  @Before
  public void initEuropean() {

    goodEuropeanAppendable = new StringBuilder();
    badEuropeanAppendable = new CorruptAppendable();

    europeanView1 =
            new MarbleSolitaireTextView(this.europeanModel1);
    europeanView2 =
            new MarbleSolitaireTextView(europeanModel2);
    europeanView3 =
            new MarbleSolitaireTextView(europeanModel3);
    europeanView4 =
            new MarbleSolitaireTextView(europeanModel4);

    // performing a move on one of the models and then passing that into the view
    europeanModelWithMoveDone.move(3, 1, 3, 3);

    europeanViewWithMoveDone = new MarbleSolitaireTextView(europeanModelWithMoveDone);

    // views constructed using the constructor that also takes in a specific appendable
    europeanViewWithModelAppendable =
            new MarbleSolitaireTextView(this.europeanModel1, goodEuropeanAppendable);
    europeanViewWithModelAppendableMoveDone =
            new MarbleSolitaireTextView(this.europeanModelWithMoveDone, goodEuropeanAppendable);


  }

  // testing properties of a view with the constructor that was constructed properly when given a
  // model
  @Test
  public void testValidConstructorOnlyModelEuropean() {
    // since the fields are private, we must use the public methods in the interface to ensure
    // that the object was constructed properly (this is a little redundant with the tests for
    // the public methods in the MarbleSolitaireView interface)
    assertEquals(
            "    O O O\n"
                    + "  O O O O O\n"
                    + "O O O O O O O\n"
                    + "O O O _ O O O\n"
                    + "O O O O O O O\n"
                    + "  O O O O O\n"
                    + "    O O O",
            this.europeanView1.toString());
  }

  // testing properties of a view with the constructor that was constructor properly when given a
  // model and an appendable
  @Test
  public void testValidConstructorModelAndAppendableEuropeam() {
    // since the fields are private, we must use the public methods in the interface to ensure
    // that the object was constructed properly (this is a little redundant with the tests for
    // the public methods in the MarbleSolitaireView interface)
    assertEquals(
            "    O O O\n"
                    + "  O O O O O\n"
                    + "O O O O O O O\n"
                    + "O O O _ O O O\n"
                    + "O O O O O O O\n"
                    + "  O O O O O\n"
                    + "    O O O",
            this.europeanViewWithModelAppendable.toString());
  }


  // testing for an exception when the view is passed a model state which is not null but an
  // appendable which is null
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorEuropean() {
    MarbleSolitaireView view = new MarbleSolitaireTextView(this.europeanModel1, null);
  }




  // testing the toString method on a view containing a model made by the default constructor
  @Test
  public void testToStringForDefaultConstructorEuropean() {
    assertEquals(
            "    O O O\n"
                    + "  O O O O O\n"
                    + "O O O O O O O\n"
                    + "O O O _ O O O\n"
                    + "O O O O O O O\n"
                    + "  O O O O O\n"
                    + "    O O O", this.europeanView1.toString());
  }

  // testing the toString method on a view containing a model with an armLength of 5
  @Test
  public void testToStringForSpecifiedArmThicknessConstructorEuropean() {
    assertEquals(
            "        O O O O O\n" +
                    "      O O O O O O O\n" +
                    "    O O O O O O O O O\n" +
                    "  O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O _ O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "  O O O O O O O O O O O\n" +
                    "    O O O O O O O O O\n" +
                    "      O O O O O O O\n" +
                    "        O O O O O",
            this.europeanView2.toString());
  }

  // testing the toString method on a view containing a model with a specified empty slot
  @Test
  public void testToStringForSpecifiedEmptySlotConstructorEuropean() {
    assertEquals(
            "    O O O\n" +
                    "  O O O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "O _ O O O O O\n" +
                    "  O O O O O\n" +
                    "    O O O",
            this.europeanView3.toString());
  }

  // testing the toString method on a view containing a model with an armLength of 5 and a
  // specified empty slot
  @Test
  public void testToStringForSpecifiedArmLengthAndEmptySlotConstructorEuropean() {
    assertEquals(
            "        O O O O O\n" +
                    "      O O O O O O O\n" +
                    "    O O O O O O O O O\n" +
                    "  O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "  O O O O O O O _ O O O\n" +
                    "    O O O O O O O O O\n" +
                    "      O O O O O O O\n" +
                    "        O O O O O",
            this.europeanView4.toString());
  }

  // testing the toString method on a view containing a model where a valid move has been done
  // and there are now more than 1 empty slots
  @Test
  public void testToStringForViewWithValidMoveDoneEuropean() {
    assertEquals(
            "    O O O\n"
                    + "  O O O O O\n"
                    + "O O O O O O O\n"
                    + "O _ _ O O O O\n"
                    + "O O O O O O O\n"
                    + "  O O O O O\n"
                    + "    O O O",
            this.europeanViewWithMoveDone.toString());
  }

  // testing the toString method on a view constructed with a model and an appendable
  @Test
  public void testToStringForViewWithModelAndAppendableEuropean() {
    assertEquals(
            "    O O O\n"
                    + "  O O O O O\n"
                    + "O O O O O O O\n"
                    + "O O O _ O O O\n"
                    + "O O O O O O O\n"
                    + "  O O O O O\n"
                    + "    O O O",
            this.europeanViewWithModelAppendable.toString());
  }

  // testing the toString method on a view constructed with a model and an appendable where the
  // model has had a valid move done
  @Test
  public void testToStringForViewWithModelAndAppendableWithValidMoveDoneEuropean() {
    assertEquals(
            "    O O O\n"
                    + "  O O O O O\n"
                    + "O O O O O O O\n"
                    + "O _ _ O O O O\n"
                    + "O O O O O O O\n"
                    + "  O O O O O\n"
                    + "    O O O",
            this.europeanViewWithModelAppendableMoveDone.toString());
  }






  // testing that renderBoard works correctly at the start of a game
  @Test
  public void testRenderBoardEuropean() throws IOException {
    europeanViewWithModelAppendable.renderBoard();
    assertEquals("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O", goodEuropeanAppendable.toString());
  }

  // testing that renderBoard works correctly after a move has been performed on the model
  @Test
  public void testRenderBoardAfterMoveEuropean() throws IOException {
    europeanViewWithModelAppendableMoveDone.renderBoard();
    assertEquals("    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O", goodEuropeanAppendable.toString());
  }

  // testing for an exception when renderBoard has trouble transmitting an output
  @Test(expected = IOException.class)
  public void testFailedRenderBoardEuropean() throws IOException {
    MarbleSolitaireView view = new MarbleSolitaireTextView(europeanModel1,
            badEuropeanAppendable);
    view.renderBoard();
  }





  // testing that renderMessage works with an empty string
  @Test
  public void testRenderMessageEuropean() throws IOException {
    MarbleSolitaireView view = new MarbleSolitaireTextView(europeanModel1,
            goodEuropeanAppendable);
    view.renderMessage("");
    assertEquals("", goodEuropeanAppendable.toString());
  }

  // testing that renderMessage works with a word
  @Test
  public void testRenderMessage2European() throws IOException {
    MarbleSolitaireView view = new MarbleSolitaireTextView(europeanModel1,
            goodEuropeanAppendable);
    view.renderMessage("hello");
    assertEquals("hello", goodEuropeanAppendable.toString());
  }

  // testing that renderMessage works with a full sentence
  @Test
  public void testRenderMessage3European() throws IOException {
    MarbleSolitaireView view = new MarbleSolitaireTextView(europeanModel1,
            goodEuropeanAppendable);
    view.renderMessage("hi I am aryan and I love the celtics");
    assertEquals("hi I am aryan and I love the celtics", goodEuropeanAppendable.toString());
  }

  // testing for an exception when renderMessage has trouble transmitting an output
  @Test(expected = IOException.class)
  public void testFailedRenderMessageEuropean() throws IOException {
    MarbleSolitaireView view = new MarbleSolitaireTextView(europeanModel1,
            badEuropeanAppendable);
    view.renderMessage("hi");
  }
}




