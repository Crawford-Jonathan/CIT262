/*
||  Program name:     TurtleFrameSix.java
||  Created by:       Michael McLaughlin | Copyright 2002
||  Creation date:    02/12/02
||  History:
|| ----------------------------------------------------------------------
||  Date       Author                   Purpose
||  --------   ----------------------   ---------------------------------
||  dd/mm/yy   {Name}                   {Brief statement of change.}
|| ----------------------------------------------------------------------
||  Execution method: Invoking class through static main method.
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
public class TurtleFrameSix extends Frame
{
  // ------------------------- Reference Classes -----------------------------/

  // Define objects on dependent classes.
  private TurtleControlPanelSix controlPanel = new TurtleControlPanelSix();

  // -------------------------- Class Variables ------------------------------/

  // Define and initialize compass points.
  private final int EAST  = CompassDirections.EAST;
  private final int SOUTH = CompassDirections.SOUTH;
  private final int WEST  = CompassDirections.WEST;
  private final int NORTH = CompassDirections.NORTH;

  // Define and initialize AWT buttons.
  private Button turnRightButton = controlPanel.getTurnRightButton();
  private Button turnLeftButton  = controlPanel.getTurnLeftButton();
  private Button moveButton      = controlPanel.getMoveButton();
  private Button printButton     = controlPanel.getPrintButton();
  private Button endButton       = controlPanel.getEndButton();

  // Define and initialize Checkbox(s).
  private Checkbox penCheckbox = controlPanel.getPenCheckbox();

  // Define a standard Fonts.
  private Font labelFont  = new Font("San Serif",Font.PLAIN|Font.ITALIC,12);
  private Font textFont   = new Font("Monospaced",Font.PLAIN,11);

  // Define a text area for drawn line arrays.
  private TextArea linesDrawn = controlPanel.getLinesDrawn();

  // Define AWT objects.
  private TextField textDistance        = controlPanel.getTextDistance();
  private TextField beginPointTextField = controlPanel.getBeginPointTextField();
  private TextField endPointTextField   = controlPanel.getEndPointTextField();

  // ------------------------- Begin Constructor -----------------------------/

  /*
  || The constructors of the class are:
  || =========================================================================
  ||  Access     Constructor Type  Constructor
  ||  ---------  ----------------  -------------------------------------------
  ||  protected  Default           TurtleFrameSix()
  */

  // Define default constructor.
  protected TurtleFrameSix()
  {
    // Set window title, layout method and user interface.
    super("Dynamic Turtle");

    // Build Frame.
    buildFrame();

  } // End of default constructor.

  // -------------------------- End Constructor ------------------------------/

  // --------------------------- Begin Methods -------------------------------/

  /*
  || The static main instantiates a test instance of the class:
  || =========================================================================
  ||  Return Type  Method Name                    Access     Parameter List
  ||  -----------  -----------------------------  ---------  -----------------
  ||  void         buildFrame()                   protected
  ||  void         setActionListeners()           protected
  ||  void         resetCurrentPoint()            protected  int direction
  */

  // -------------------------------------------------------------------------/

  // Define method to return commands.
  protected void buildFrame()
  {
    // Set layout for frame.
    setLayout(new BorderLayout());

    // Add user interface components Frame, which is the content pane in AWT.
    add(controlPanel.getToolPanel(),BorderLayout.NORTH);
    add(controlPanel.getDrawingPanel(),BorderLayout.CENTER);
    add(controlPanel.getMonitorPanel(),BorderLayout.SOUTH);

    // Initialize default class variables.
    controlPanel.setBeginPoint(controlPanel.getCurrentPoint());

    // Define and add action listeners.
    setActionListeners();

    // ------------------ Begin Window ActionListener ------------------------/

    addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent closingEvent)
      {
        System.exit(0);
      }
    });

    // ------------------- End Window ActionListener -------------------------/

    // Set frame size, disable resizing and display the frame.
    setSize(820,630);
    setResizable(false);
    show();

  } // End of buildPanel() method.

  // -------------------------------------------------------------------------/

  // Define method set action listeners to AWT widgets.
  protected void setActionListeners()
  {
    // ------------------ Begin Action ActionListener ------------------------/

    // Define an event listener for the penUpButton.
    penCheckbox.addItemListener(
      new ItemListener()
      {
        public void itemStateChanged(ItemEvent e)
        {
          // Alter state of button and related buttons.
          penCheckbox.setState(!penCheckbox.getState());
          controlPanel.setPen();

          // It is possible that a move may be a compound move
          // consisting of two moves.  The first move enables
          // the endButton and a subsequent move should not disable
          // the moveButton.  Further, it is possible that the first
          // move may be made with the pen down before the user
          // recognizes they need to put the pen up.  This disables
          // the end button if it was previously enabled by a prior
          // move with the pen down.

          // If end button is enabled, disable and reset begin point.
          if (controlPanel.getEndButtonState())
          {
            // Enable the endButton to signal and end to a line draw.
            controlPanel.setEndButton();

            // If a compound move is interrupted by selecting pulling
            // the pen up, the begin point must be reset.

            // Move the beginning point.
            controlPanel.setBeginPoint(controlPanel.getCurrentPoint());

          } // If end button is enabled, disable it and reset begin point.


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
          controlPanel.setDirection(
            ((controlPanel.getDirection() + 1) % 4));

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
          if (controlPanel.getDirection() == EAST)
          {
            // Set direction to highest value.
            controlPanel.setDirection(NORTH);
          }
          else
          {
            // Set current direction by decrementing and applying modulus.
            controlPanel.setDirection(
              ((controlPanel.getDirection() - 1) % 4));
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
            controlPanel.setMoveDistance(
              Integer.parseInt(textDistance.getText()));

            try
            {
              // Assign current direction to switch condition.
              int direction = controlPanel.getDirection();

              // Evaluate space based on direction.
              switch (direction)
              {
                case EAST:
                  // Determine if enough space to move point.
                  if (((int) controlPanel.getCurrentPoint().getX() +
                          controlPanel.getMoveDistance()) <=
                            controlPanel.getLimit(direction))
                  {
                    // Reset current working point.
                    resetCurrentPoint(direction);

                  }
                  else
                  {
                    // Define message for JOptionPane on inadequate space.
                    spaceMessage = "You may only move [" +
                      (controlPanel.getLimit(direction) -
                        (int) controlPanel.getCurrentPoint().getX()) +
                      "] pixels.\n";

                    // Throw exception if unable to move as requested.
                    throw new NumberFormatException();

                  }
                  break;

                case SOUTH:
                  // Determine if enough space to move point.
                  if (((double) controlPanel.getCurrentPoint().getY() +
                          controlPanel.getMoveDistance()) <=
                            controlPanel.getLimit(direction))
                  {
                    // Reset current working point.
                    resetCurrentPoint(direction);

                  }
                  else
                  {
                    // Define message for JOptionPane on inadequate space.
                    spaceMessage = "You may only move [" +
                      (controlPanel.getLimit(direction) -
                        (int) controlPanel.getCurrentPoint().getY()) +
                      "] pixels.\n";

                    // Throw exception if unable to move as requested.
                    throw new NumberFormatException();

                  }
                  break;

                case WEST:
                  // Determine if enough space to move point.
                  if (((double) controlPanel.getCurrentPoint().getX() -
                          controlPanel.getMoveDistance()) >=
                            controlPanel.getLimit(direction))
                  {
                    // Reset current working point.
                    resetCurrentPoint(direction);

                  }
                  else
                  {
                    // Define message for JOptionPane on inadequate space.
                    spaceMessage = "You may only move [" +
                      ((int) controlPanel.getCurrentPoint().getX() -
                        controlPanel.getLimit(direction)) +
                      "] pixels.\n";

                    // Throw exception if unable to move as requested.
                    throw new NumberFormatException();

                  }
                  break;

                case NORTH:
                  // Determine if enough space to move point.
                  if (((double) controlPanel.getCurrentPoint().getY() -
                          controlPanel.getMoveDistance()) >=
                            controlPanel.getLimit(direction))
                  {
                    // Reset current working point.
                    resetCurrentPoint(direction);

                  }
                  else
                  {
                    // Define message for JOptionPane on inadequate space.
                    spaceMessage = "You may only move [" +
                      ((int) controlPanel.getCurrentPoint().getY() -
                        controlPanel.getLimit(direction)) +
                      "] pixels.\n";

                    // Throw exception if unable to move as requested.
                    throw new NumberFormatException();

                  }
                  break;

              } // End of switch statement.

              // If pen is up reset begin point display.
              if (!controlPanel.getPenDown())
              {
                // Move the beginning point.
                controlPanel.setBeginPoint(controlPanel.getCurrentPoint());

              } // End if pen up reset begin point display.
              else
              {
                // It is possible that a move may be a compound move
                // consisting of two moves.  The first move enables
                // the endButton and a subsequent move should not disable
                // the moveButton.
                if (!controlPanel.getEndButtonState())
                {
                  // Enable the endButton to signal and end to a line draw.
                  controlPanel.setEndButton();

                } // If button is not enabled, enable it.

              } // End of if-else pen is up or down reset begin point display.

              // Reset the current point.
              controlPanel.setEndPointTextField();

            } // End of try block for enough directional space.
            catch (NumberFormatException ne)
            {
              // Display message that an integer value must be entered.
              JOptionPane.showMessageDialog(null,spaceMessage);

              // Reinitialize the default class variable value.
              controlPanel.setTextDistance();

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
            controlPanel.setTextDistance();

          } // End of finally that resets default distance.

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
          controlPanel.setPrintButton();

          // Repaint the graphics array.
          controlPanel.setRepaintDrawingPanel();

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
          controlPanel.setEndButton();

          // If the pen is down, the line will be drawn.
          if (controlPanel.getPenDown())
          {
            // Set the end point for the line.
            controlPanel.setEndPoint(controlPanel.getCurrentPoint());

            // Initialize the line.
            controlPanel.setDrawLine();

            // Add the line to the graphic panel array to be drawn.
            controlPanel.setLineArray(controlPanel.getDrawLine());

            // Refresh the displayed array of lines.
            controlPanel.setLinesDrawn(controlPanel.getLineArray());

            // Move the beginning point.
            controlPanel.setBeginPoint(controlPanel.getCurrentPoint());

          } // End of if the pen is down.

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
        controlPanel.setCurrentPoint(
          ((int) controlPanel.getCurrentPoint().getX() +
            controlPanel.getMoveDistance()),
          (int) controlPanel.getCurrentPoint().getY());
          break;

      case SOUTH:

        // Use control panel to reset current point.
        controlPanel.setCurrentPoint(
          (int) controlPanel.getCurrentPoint().getX(),
          ((int) controlPanel.getCurrentPoint().getY() +
            controlPanel.getMoveDistance()));
          break;

      case WEST:

         // Use control panel to reset current point.
        controlPanel.setCurrentPoint(
          ((int) controlPanel.getCurrentPoint().getX() -
            controlPanel.getMoveDistance()),
          (int) controlPanel.getCurrentPoint().getY());
          break;

      case NORTH:

        // Use control panel to reset current point.
        controlPanel.setCurrentPoint(
          (int) controlPanel.getCurrentPoint().getX(),
          ((int) controlPanel.getCurrentPoint().getY() -
            controlPanel.getMoveDistance()));
          break;

    } // End of switch on direction.

  } // End of resetCurrentPoint() method.

  // ---------------------------- End Methods --------------------------------/

  // ---------------------------- Static Main --------------------------------/

  // Static main program for executing class.
  public static void main(String args[])
  {
    // Define an instance of the class.
    TurtleFrameSix turtleFrame = new TurtleFrameSix();

  } // End of static main.

} // End of TurtleFrameSix class.

// ------------------------------- End Class ---------------------------------/