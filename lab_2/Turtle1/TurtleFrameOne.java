/*
||  Program name:     TurtleFrameOne.java
||  Created by:       Michael McLaughlin
||  Creation date:    12/18/01
||  History:
|| ----------------------------------------------------------------------
||  Date       Author                   Purpose
||  --------   ----------------------   ---------------------------------
||  dd/mm/yy   {Name}                   {Brief statement of change.}
|| ----------------------------------------------------------------------
||  Execution method: Instantiated as a class within a class.
||  Program purpose:  Designed as an AWT Frame for a drawing program;
||                    based on Java: How to Program, 4th Edition, by
||                    Deitel & Deitel, Chapter 11, Problem 27.
*/

// Class imports.
import java.awt.*;            // Required for AWT widgets.
import java.awt.event.*;      // Required for Java events.
import java.awt.geom.*;       // Required for Java Graphics2D Libraries.
import java.lang.*;           // Required for general Java language use.
import javax.swing.*;         // Required for JOptionPane use.

// ------------------------------ Begin Class --------------------------------/

// Class definition.
public class TurtleFrameOne extends Frame
{
  // ------------------------- Reference Classes -----------------------------/

  // Define objects on dependent classes.
  private TurtleControlPanelOne toolControlPanel = new TurtleControlPanelOne();
  private TurtleCanvasPanelOne  drawingPanel     = new TurtleCanvasPanelOne();

  // -------------------------- Class Variables ------------------------------/

  // Define and initialize compass points.
  private final int EAST  = TurtleConstantsOne.EAST;
  private final int SOUTH = TurtleConstantsOne.SOUTH;
  private final int WEST  = TurtleConstantsOne.WEST;
  private final int NORTH = TurtleConstantsOne.NORTH;

  // Define and initialize AWT buttons.
  private Button penUpButton     = toolControlPanel.getPenUpButton();
  private Button penDownButton   = toolControlPanel.getPenDownButton();
  private Button turnRightButton = toolControlPanel.getTurnRightButton();
  private Button turnLeftButton  = toolControlPanel.getTurnLeftButton();
  private Button moveButton      = toolControlPanel.getMoveButton();
  private Button printButton     = toolControlPanel.getPrintButton();
  private Button endButton       = toolControlPanel.getEndButton();

  // Define AWT objects.
  private TextField textDistance = toolControlPanel.getTextDistance();

  // ------------------------- Begin Constructor -----------------------------/

  /*
  || The constructors of the class are:
  || =========================================================================
  ||  Access     Constructor Type  Constructor
  ||  ---------  ----------------  -------------------------------------------
  ||  protected  Default           TurtleFrameOne()
  */

  // Define default constructor.
  protected TurtleFrameOne()
  {
    // Set window title, layout method and user interface.
    super("Dynamic Turtle");

    // Build Frame.
    buildFrame();

  } // End of default constructor.

  // -------------------------- End Constructor ------------------------------/

  // --------------------------- Begin Methods -------------------------------/

  // Define method to return commands.
  protected void buildFrame()
  {
    // Set layout for frame.
    setLayout(new BorderLayout());

    // Add user interface to content pane.
    add(toolControlPanel.getToolPanel(),BorderLayout.NORTH);
    add(drawingPanel,BorderLayout.CENTER);

    // Initialize default class variables.
    toolControlPanel.setBeginPoint(toolControlPanel.getCurrentPoint());

    // Define and add action listeners.
    setActionListeners();

    // --------------------- Window ActionListener ---------------------------/

    addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent closingEvent)
      {
        System.exit(0);
      }
    });

    // Set frame size, disable resizing and display the frame.
    setSize(820,575);
    setResizable(false);
    show();

  } // End of buildPanel() method.

  // -------------------------------------------------------------------------/

  // Define method set action listeners to AWT widgets.
  protected void setActionListeners()
  {
    // ------------------ Begin Action ActionListener ------------------------/

    // Define an event listener for the penUpButton.
    penUpButton.addActionListener(
      new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          // Alter state of button and related buttons.
          toolControlPanel.setPenUpButton();

        } // End of actionPerformed method.
      }); // End of commands action listener.

    // -----------------------------------------------------------------------/

    // Define an event listener for the penDownButton.
    penDownButton.addActionListener(
      new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          // Alter state of button and related buttons.
          toolControlPanel.setPenDownButton();

        } // End of actionPerformed method.
      }); // End of commands action listener.

    // -----------------------------------------------------------------------/

    // Define an event listener for the turnRightButton.
    turnRightButton.addActionListener(
      new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          // Set current direction by incrementing and applying modulus.
          toolControlPanel.setDirection(
            ((toolControlPanel.getDirection() + 1) % 4));

        } // End of actionPerformed method.
      }); // End of commands action listener.

    // -----------------------------------------------------------------------/

    // Define an event listener for the turnLeftButton.
    turnLeftButton.addActionListener(
      new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          // Set current direction, if constant is zero set manually.
          if (toolControlPanel.getDirection() == EAST)
          {
            // Set direction to highest value.
            toolControlPanel.setDirection(NORTH);
          }
          else
          {
            // Set current direction by decrementing and applying modulus.
            toolControlPanel.setDirection(
              ((toolControlPanel.getDirection() - 1) % 4));
          }

        } // End of actionPerformed method.
      }); // End of commands action listener.

    // -----------------------------------------------------------------------/

    // Define an event listener for the moveButton.
    moveButton.addActionListener(
      new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          // Define message for JOptionPane on integer parse failure.
          String message = "Distance entered for pixels must be an\n" +
                           "integer not [" + textDistance.getText() + "].\n";
          String spaceMessage = new String();

          // Try-catch block to parse integer.
          try
          {
            // Assign the String to the control panel move distance variable.
            toolControlPanel.setMoveDistance(
              Integer.parseInt(textDistance.getText()));

            try
            {
              // Assign current direction to switch condition.
              int direction = toolControlPanel.getDirection();

              // Evaluate space based on direction.
              switch (direction)
              {
                case EAST:
                  // Determine if enough space to move point.
                  if (((int) toolControlPanel.getCurrentPoint().getX() +
                          toolControlPanel.getMoveDistance()) <=
                            drawingPanel.getLimit(direction))
                  {
                    // It is possible that an move may be a compound move
                    // consisting of two moves.  The first move enables
                    // the endButton and a second move should not disable
                    // the moveButton.
                    if (!toolControlPanel.getEndButtonState())
                    {
                      // Enable the endButton to signal and end to a line draw.
                      toolControlPanel.setEndButton();
                    }

                    // Reset current working point.
                    resetCurrentPoint(direction);

                  }
                  else
                  {
                    // Define message for JOptionPane on inadequate space.
                    spaceMessage = "You may only move [" +
                      (drawingPanel.getLimit(direction) -
                        (int) toolControlPanel.getCurrentPoint().getX()) +
                      "] pixels.\n";

                    // Throw exception if unable to move as requested.
                    throw new NumberFormatException();

                  }
                  break;

                case SOUTH:
                  // Determine if enough space to move point.
                  if (((double) toolControlPanel.getCurrentPoint().getY() +
                          toolControlPanel.getMoveDistance()) <=
                            drawingPanel.getLimit(direction))
                  {
                    // It is possible that an move may be a compound move
                    // consisting of two moves.  The first move enables
                    // the endButton and a second move should not disable
                    // the moveButton.
                    if (!toolControlPanel.getEndButtonState())
                    {
                      // Enable the endButton to signal and end to a line draw.
                      toolControlPanel.setEndButton();
                    }

                    // Reset current working point.
                    resetCurrentPoint(direction);

                  }
                  else
                  {
                    // Define message for JOptionPane on inadequate space.
                    spaceMessage = "You may only move [" +
                      (drawingPanel.getLimit(direction) -
                        (int) toolControlPanel.getCurrentPoint().getY()) +
                      "] pixels.\n";

                    // Throw exception if unable to move as requested.
                    throw new NumberFormatException();

                  }
                  break;

                case WEST:
                  // Determine if enough space to move point.
                  if (((double) toolControlPanel.getCurrentPoint().getX() -
                          toolControlPanel.getMoveDistance()) >=
                            drawingPanel.getLimit(direction))
                  {
                    // It is possible that an move may be a compound move
                    // consisting of two moves.  The first move enables
                    // the endButton and a second move should not disable
                    // the moveButton.
                    if (!toolControlPanel.getEndButtonState())
                    {
                      // Enable the endButton to signal and end to a line draw.
                      toolControlPanel.setEndButton();
                    }

                    // Reset current working point.
                    resetCurrentPoint(direction);

                  }
                  else
                  {
                    // Define message for JOptionPane on inadequate space.
                    spaceMessage = "You may only move [" +
                      ((int) toolControlPanel.getCurrentPoint().getX() -
                        drawingPanel.getLimit(direction)) +
                      "] pixels.\n";

                    // Throw exception if unable to move as requested.
                    throw new NumberFormatException();

                  }
                  break;

                case NORTH:
                  // Determine if enough space to move point.
                  if (((double) toolControlPanel.getCurrentPoint().getY() -
                          toolControlPanel.getMoveDistance()) >=
                            drawingPanel.getLimit(direction))
                  {
                    // It is possible that an move may be a compound move
                    // consisting of two moves.  The first move enables
                    // the endButton and a second move should not disable
                    // the moveButton.
                    if (!toolControlPanel.getEndButtonState())
                    {
                      // Enable the endButton to signal and end to a line draw.
                      toolControlPanel.setEndButton();
                    }

                    // Reset current working point.
                    resetCurrentPoint(direction);

                  }
                  else
                  {
                    // Define message for JOptionPane on inadequate space.
                    spaceMessage = "You may only move [" +
                      ((int) toolControlPanel.getCurrentPoint().getY() -
                        drawingPanel.getLimit(direction)) +
                      "] pixels.\n";

                    // Throw exception if unable to move as requested.
                    throw new NumberFormatException();

                  }
                  break;

              } // End of switch statement.

            } // End of try block for enough directional space.
            catch (NumberFormatException ne)
            {
              // Display message that an integer value must be entered.
              JOptionPane.showMessageDialog(null,spaceMessage);

              // Reinitialize the default class variable value.
              toolControlPanel.setTextDistance();

            } // End of catch for thrown exception due to inadequate space.

          } // End of try for integer parse.
          catch (NumberFormatException nfe)
          {
            // Display message that an integer value must be entered.
            JOptionPane.showMessageDialog(null,message);

          } // End try-catch block on field entry being an integer.
          finally
          {
            // Reinitialize the default class variable value.
            toolControlPanel.setTextDistance();

          }

        } // End of actionPerformed method.
      }); // End of commands action listener.

    // -----------------------------------------------------------------------/

    // Define an event listener for the printButton.
    printButton.addActionListener(
      new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          // After refreshing the display self-disable.
          toolControlPanel.setPrintButton();

          // Repaint the graphics array.
          drawingPanel.repaint();

        } // End of actionPerformed method.
      }); // End of commands action listener.

    // -----------------------------------------------------------------------/

    // Define an event listener for the endButton.
    endButton.addActionListener(
      new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          // Enable printButton to repaint drawing panel.
          toolControlPanel.setEndButton();

          // If the pen is down, the line will be drawn.
          if (toolControlPanel.getPenDown())
          {
            // Set the end point for the line.
            toolControlPanel.setEndPoint(toolControlPanel.getCurrentPoint());

            // Initialize the line.
            toolControlPanel.setDrawLine();

            // Add the line to the graphic panel array to be drawn.
            drawingPanel.setLineArray(toolControlPanel.getDrawLine());

          } // End of if the pen is down.

          // Move the beginning point.
          toolControlPanel.setBeginPoint(toolControlPanel.getCurrentPoint());

        } // End of actionPerformed method.
      }); // End of commands action listener.

    // ------------------- End Action ActionListener -------------------------/

  } // End of setActionListeners() method.

  // -------------------------------------------------------------------------/

  // Reset current point value.
  private void resetCurrentPoint(int direction)
  {
    // Evaluate space based on direction.
    switch (direction)
    {
      case EAST:

        // Use control panel to reset current point.
        toolControlPanel.setCurrentPoint(
          ((int) toolControlPanel.getCurrentPoint().getX() +
            toolControlPanel.getMoveDistance()),
          (int) toolControlPanel.getCurrentPoint().getY());
          break;

      case SOUTH:

        // Use control panel to reset current point.
        toolControlPanel.setCurrentPoint(
          (int) toolControlPanel.getCurrentPoint().getX(),
          ((int) toolControlPanel.getCurrentPoint().getY() +
            toolControlPanel.getMoveDistance()));
          break;

      case WEST:

         // Use control panel to reset current point.
        toolControlPanel.setCurrentPoint(
          ((int) toolControlPanel.getCurrentPoint().getX() -
            toolControlPanel.getMoveDistance()),
          (int) toolControlPanel.getCurrentPoint().getY());
          break;

      case NORTH:

        // Use control panel to reset current point.
        toolControlPanel.setCurrentPoint(
          (int) toolControlPanel.getCurrentPoint().getX(),
          ((int) toolControlPanel.getCurrentPoint().getY() -
            toolControlPanel.getMoveDistance()));
          break;

    } // End of switch on direction.

  } // End of resetCurrentPoint() method.

  // ---------------------------- End Methods --------------------------------/

  // ---------------------------- Static Main --------------------------------/

  // Static main program for executing class.
  public static void main(String args[])
  {
    // Define an instance of the class.
    TurtleFrameOne turtleFrame = new TurtleFrameOne();

  } // End of static main.

} // End of ComboBox class.

// ------------------------------- End Class ---------------------------------/