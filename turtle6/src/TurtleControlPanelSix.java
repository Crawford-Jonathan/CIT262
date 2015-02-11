/*
||  Program name:     TurtleControlPanelSix.java
||  Created by:       Michael McLaughlin | Copyright 2002
||  Creation date:    02/12/02
||  History:
|| ----------------------------------------------------------------------
||  Date       Author                   Purpose
||  --------   ----------------------   ---------------------------------
||  dd/mm/yy   {Name}                   {Brief statement of change.}
|| ----------------------------------------------------------------------
||  Execution method: Instantiated as a class within a class.
||  Program purpose:  Designed as an AWT toolbar for a drawing program;
||                    based on Java: How to Program, 4th Edition, by
||                    Deitel & Deitel, Chapter 11, Problem 27.
*/

// Class imports.
import java.awt.*;            // Required for AWT widgets.
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.*;       // Required for Java Graphics2D Libraries.
import java.lang.*;           // Required for general Java language use.

import javax.swing.JOptionPane;

// ------------------------------ Begin Class --------------------------------/

// Class definition.
public class TurtleControlPanelSix extends Panel
{
  // ------------------------- Reference Classes -----------------------------/

  // Drawing panel.
  private TurtleCanvasPanelSix drawingPanel  = new TurtleCanvasPanelSix();
  private TurtleMonitorPanelSix monitorPanel = new TurtleMonitorPanelSix();


  // -------------------------- Class Variables ------------------------------/

  // Define and initialize compass points.
  private final int EAST  = CompassDirections.EAST;
  private final int SOUTH = CompassDirections.SOUTH;
  private final int WEST  = CompassDirections.WEST;
  private final int NORTH = CompassDirections.NORTH;

  // Define and initialize compass logical direction values.
  private boolean east  = true;
  private boolean south = false;
  private boolean west  = false;
  private boolean north = false;

  // Define and initialize dynamic class control variable(s).
  private int moveDistance     = 0;
  private int currentDirection = EAST;

  // Define Graphics 2D object(s).
  private Line2D drawLine;
  private Point2D beginPoint   = new Point2D.Double(drawingPanel.getBeginX(),
                                   drawingPanel.getBeginY());
  private Point2D currentPoint = new Point2D.Double(drawingPanel.getBeginX(),
                                   drawingPanel.getBeginY());
  private Point2D endPoint;

  // Define and initialize Font(s).
  private Font buttonFont = new Font("San Serif",Font.BOLD,11);
  private Font labelFont  = new Font("San Serif",Font.PLAIN|Font.ITALIC,12);
  private Font textFont   = new Font("San Serif",Font.PLAIN,11);

  // Define and initialize static String(s).
  private String penUp     = new String("Pen Up");
  private String penDown   = new String("Pen Down");
  private String turnRight = new String("Turn Right");
  private String turnLeft  = new String("Turn Left");
  private String move      = new String("Move");
  private String print     = new String("Print");
  private String end       = new String("End");

  // Define and initialize Button(s).
  private Button turnRightButton = new Button(turnRight);
  private Button turnLeftButton  = new Button(turnLeft);
  private Button moveButton      = new Button(move);
  private Button printButton     = new Button(print);
  private Button endButton       = new Button(end);

  // Define and initialize Checkbox(s).
  private Checkbox penCheckbox = new Checkbox(penDown,true);

  // Define and initialize Label(s).
  private Label labelDistance = new Label();

  // Define and initialize Panel(s).
  private Panel toolPanel = new Panel();

  // Define and initialize TextArea(s).
  private TextArea linesDrawn = monitorPanel.getLinesDrawn();

  // Define and initialize TextField(s).
  private TextField textDistance        = new TextField();
  private TextField beginPointTextField = monitorPanel.getBeginPoint();
  private TextField endPointTextField   = monitorPanel.getEndPoint();


  // ------------------------- Begin Constructor -----------------------------/

  /*
  || The constructors of the class are:
  || =========================================================================
  ||  Access     Constructor Type  Constructor
  ||  ---------  ----------------  -------------------------------------------
  ||  protected  Default           ComboBox()
  */

  // Define default constructor.
  protected TurtleControlPanelSix()
  {
    // Initialize default set current grid point.
    setCurrentPoint();

    // Initialize set methods.
    buildPanel();

  } // End of default constructor.

  // -------------------------- End Constructor ------------------------------/

  // --------------------------- Begin Methods -------------------------------/

   /*
  || The static main instantiates a test instance of the class:
  || =========================================================================
  ||  Return Type  Method Name                    Access     Parameter List
  ||  -----------  -----------------------------  ---------  -----------------
  ||  void         buildPanel()                   protected
  ||  Point2D      getBeginPoint()                protected
  ||  String       getBeginPointString()          protected
  ||  TextField    getBeginPointTextField()       protected
  ||  Point2D      getCurrentPoint()              protected
  ||  String       getCurrentPointString()        protected
  ||  int          getDirection()                 protected
  ||  Panel        getDrawingPanel()              protected
  ||  Line2D       getDrawLine()                  protected
  ||  Button       getEndButton()                 protected
  ||  boolean      getEndButtonState()            protected
  ||  Point2D      getEndPoint()                  protected
  ||  TextField    getEndPointTextField()         protected
  ||  int          getLimit()                     protected  int direction
  ||  String       getLineArray()                 protected
  ||  TextArea     getLinesDrawn()                protected
  ||  int          getMoveDistance()              protected
  ||  Panel        getMonitorPanel()              protected
  ||  boolean      getPenDown()                   protected
  ||  Checkbox     getPen()                       protected
  ||  String       getPointString()               private    Point2D point
  ||  Button       getPrintButton()               protected
  ||  TextField    getTextDistance()              protected
  ||  Panel        getToolPanel()                 protected
  ||  Button       getTurnLeftButton()            protected
  ||  Button       getTurnRightButton()           protected
  ||  void         setBeginPoint()                protected  int x
  ||                                                         int y
  ||  void         setBeginPoint()                protected  Point2D point
  ||  void         setButtonWakeup()              protected
  ||  void         setCurrentPoint()              protected
  ||  void         setCurrentPoint()              protected  int x
  ||                                                         int y
  ||  void         setDirection()                 protected  int direction
  ||  void         setDrawLine()                  protected
  ||  void         setEndButton()                 protected
  ||  void         setEndPoint()                  protected  int x
  ||                                                         int y
  ||  void         setEndPoint()                  protected  Point2D point
  ||  void         setEndPointTextField()         protected
  ||  void         setLabelDistanceWakeup()       protected
  ||  void         setLineArray()                 protected  Line2D line
  ||  void         setLinesDrawn()                protected  String list
  ||  void         setMoveButton()                protected
  ||  void         setMoveDistance()              protected  int distance
  ||  void         setPen()                       protected  boolean state
  ||  void         setPrintButton()               protected
  ||  void         setRepaintDrawingPanel()       protected
  ||  void         setTextDistance()              protected
  */

  // ------------------- API Component Accessor Methods ----------------------/

  // Method to access AWT and Swing widgets.
  // ---------------------------------------
  // Button(s).
  protected Button getEndButton()              { return endButton; }
  protected Button getMoveButton()             { return moveButton; }
  protected Button getPrintButton()            { return printButton; }
  protected Button getTurnRightButton()        { return turnRightButton; }
  protected Button getTurnLeftButton()         { return turnLeftButton; }

  // Checkbox(s).
  protected Checkbox getPenCheckbox()          { return penCheckbox; }

  // Panel(s).
  protected Panel  getToolPanel()              { return toolPanel; }

  // TextArea(s).
  protected TextArea getLinesDrawn()           { return linesDrawn; }

  // TextField(s).
  protected TextField getTextDistance()        { return textDistance; }
  protected TextField getBeginPointTextField() { return beginPointTextField; }
  protected TextField getEndPointTextField()   { return endPointTextField; }

  // -------------------------------------------------------------------------/

  // Define method to return commands.
  protected void buildPanel()
  {
    // Initialize elements in the JComboBoxes and set defaults.

    // Initialize JComboBoxes.
    setButtonWakeup();
    setLabelDistanceWakeup();
    setTextDistance();

    // Add components to the panel.
    toolPanel.add(penCheckbox);
    toolPanel.add(turnRightButton);
    toolPanel.add(turnLeftButton);
    toolPanel.add(moveButton);
    toolPanel.add(labelDistance);
    toolPanel.add(textDistance);
    toolPanel.add(printButton);
    toolPanel.add(endButton);
    toolPanel.setBackground(Color.lightGray);
    setActionListeners();

  } // End of buildPanel() method.

  // -------------------------------------------------------------------------/
  
  protected void setActionListeners()
  {
	// Define an event listener for the penUpButton.
	    penCheckbox.addItemListener(
	      new ItemListener()
	      {
	        public void itemStateChanged(ItemEvent e)
	        {
	          // Alter state of button and related buttons.
	          penCheckbox.setState(!penCheckbox.getState());
	          setPen();

	          // It is possible that a move may be a compound move
	          // consisting of two moves.  The first move enables
	          // the endButton and a subsequent move should not disable
	          // the moveButton.  Further, it is possible that the first
	          // move may be made with the pen down before the user
	          // recognizes they need to put the pen up.  This disables
	          // the end button if it was previously enabled by a prior
	          // move with the pen down.

	          // If end button is enabled, disable and reset begin point.
	          if (getEndButtonState())
	          {
	            // Enable the endButton to signal and end to a line draw.
	            setEndButton();

	            // If a compound move is interrupted by selecting pulling
	            // the pen up, the begin point must be reset.

	            // Move the beginning point.
	            setBeginPoint(getCurrentPoint());

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
	          setDirection(
	            ((getDirection() + 1) % 4));

	        } // End of actionPerformed method.
	      }); // End of commands action listener.
	    
	 // Define an event listener for the turnLeftButton.
	    turnLeftButton.addActionListener(
	      new ActionListener()
	      {
	        public void actionPerformed(ActionEvent e)
	        {
	          // Set current direction, if constant is zero set manually.
	          if (getDirection() == EAST)
	          {
	            // Set direction to highest value.
	            setDirection(NORTH);
	          }
	          else
	          {
	            // Set current direction by decrementing and applying modulus.
	            setDirection(
	              ((getDirection() - 1) % 4));
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
	            setMoveDistance(
	              Integer.parseInt(textDistance.getText()));

	            try
	            {
	              // Assign current direction to switch condition.
	              int direction = getDirection();

	              // Evaluate space based on direction.
	              switch (direction)
	              {
	                case EAST:
	                  // Determine if enough space to move point.
	                  if (((int) getCurrentPoint().getX() +
	                          getMoveDistance()) <=
	                            getLimit(direction))
	                  {
	                    // Reset current working point.
	                    resetCurrentPoint(direction);

	                  }
	                  else
	                  {
	                    // Define message for JOptionPane on inadequate space.
	                    spaceMessage = "You may only move [" +
	                      (getLimit(direction) -
	                        (int) getCurrentPoint().getX()) +
	                      "] pixels.\n";

	                    // Throw exception if unable to move as requested.
	                    throw new NumberFormatException();

	                  }
	                  break;

	                case SOUTH:
	                  // Determine if enough space to move point.
	                  if (((double) getCurrentPoint().getY() +
	                          getMoveDistance()) <=
	                            getLimit(direction))
	                  {
	                    // Reset current working point.
	                    resetCurrentPoint(direction);

	                  }
	                  else
	                  {
	                    // Define message for JOptionPane on inadequate space.
	                    spaceMessage = "You may only move [" +
	                      (getLimit(direction) -
	                        (int) getCurrentPoint().getY()) +
	                      "] pixels.\n";

	                    // Throw exception if unable to move as requested.
	                    throw new NumberFormatException();

	                  }
	                  break;

	                case WEST:
	                  // Determine if enough space to move point.
	                  if (((double) getCurrentPoint().getX() -
	                          getMoveDistance()) >=
	                            getLimit(direction))
	                  {
	                    // Reset current working point.
	                    resetCurrentPoint(direction);

	                  }
	                  else
	                  {
	                    // Define message for JOptionPane on inadequate space.
	                    spaceMessage = "You may only move [" +
	                      ((int) getCurrentPoint().getX() -
	                        getLimit(direction)) +
	                      "] pixels.\n";

	                    // Throw exception if unable to move as requested.
	                    throw new NumberFormatException();

	                  }
	                  break;

	                case NORTH:
	                  // Determine if enough space to move point.
	                  if (((double) getCurrentPoint().getY() -
	                          getMoveDistance()) >=
	                            getLimit(direction))
	                  {
	                    // Reset current working point.
	                    resetCurrentPoint(direction);

	                  }
	                  else
	                  {
	                    // Define message for JOptionPane on inadequate space.
	                    spaceMessage = "You may only move [" +
	                      ((int) getCurrentPoint().getY() -
	                        getLimit(direction)) +
	                      "] pixels.\n";

	                    // Throw exception if unable to move as requested.
	                    throw new NumberFormatException();

	                  }
	                  break;

	              } // End of switch statement.

	              // If pen is up reset begin point display.
	              if (!getPenDown())
	              {
	                // Move the beginning point.
	                setBeginPoint(getCurrentPoint());

	              } // End if pen up reset begin point display.
	              else
	              {
	                // It is possible that a move may be a compound move
	                // consisting of two moves.  The first move enables
	                // the endButton and a subsequent move should not disable
	                // the moveButton.
	                if (!getEndButtonState())
	                {
	                  // Enable the endButton to signal and end to a line draw.
	                  setEndButton();

	                } // If button is not enabled, enable it.

	              } // End of if-else pen is up or down reset begin point display.

	              // Reset the current point.
	              setEndPointTextField();

	            } // End of try block for enough directional space.
	            catch (NumberFormatException ne)
	            {
	              // Display message that an integer value must be entered.
	              JOptionPane.showMessageDialog(null,spaceMessage);

	              // Reinitialize the default class variable value.
	              setTextDistance();

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
	            setTextDistance();

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
	          setPrintButton();

	          // Repaint the graphics array.
	          setRepaintDrawingPanel();

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
	          setEndButton();

	          // If the pen is down, the line will be drawn.
	          if (getPenDown())
	          {
	            // Set the end point for the line.
	            setEndPoint(getCurrentPoint());

	            // Initialize the line.
	            setDrawLine();

	            // Add the line to the graphic panel array to be drawn.
	            setLineArray(getDrawLine());

	            // Refresh the displayed array of lines.
	            setLinesDrawn(getLineArray());

	            // Move the beginning point.
	            setBeginPoint(getCurrentPoint());

	          } // End of if the pen is down.

	        } // End of actionPerformed method.
	      }); // End of commands action listener.

	    // ------------------- End Action ActionListener -------------------------/
	    
	    drawingPanel.getPanel().addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	          // Set local variables.
	          int x = e.getX();
	          int y = e.getY();
	     
	          // Add code here ...
	          System.out.println("X: " + x);
	     
	        }
	      });
  } // ------------------- End SetActionListener -------------------------/
  
//Reset current point value.
 private void resetCurrentPoint(int direction)
 {
   // Evaluate space based on direction.
   switch (direction)
   {
     case EAST:

       // Use control panel to reset current point.
       setCurrentPoint(
         ((int) getCurrentPoint().getX() +
           getMoveDistance()),
         (int) getCurrentPoint().getY());
         break;

     case SOUTH:

       // Use control panel to reset current point.
       setCurrentPoint(
         (int) getCurrentPoint().getX(),
         ((int) getCurrentPoint().getY() +
           getMoveDistance()));
         break;

     case WEST:

        // Use control panel to reset current point.
       setCurrentPoint(
         ((int) getCurrentPoint().getX() -
           getMoveDistance()),
         (int) getCurrentPoint().getY());
         break;

     case NORTH:

       // Use control panel to reset current point.
       setCurrentPoint(
         (int) getCurrentPoint().getX(),
         ((int) getCurrentPoint().getY() -
           getMoveDistance()));
         break;

   } // End of switch on direction.

 } // End of resetCurrentPoint() method.
  
  // Define method to get beginning point for line.
  protected Point2D getBeginPoint()
  {
    // Return point value.
    return beginPoint;

  } // End of getBeginPoint() method.

  // -------------------------------------------------------------------------/

  // Define method to get beginning point for line.
  protected String getBeginPointString()
  {
    // Return point value.
    return getPointString(beginPoint);

  } // End of getBeginPointString() method.

  // -------------------------------------------------------------------------/

  // Define method to get current point.
  protected Point2D getCurrentPoint()
  {
    // Return point value.
    return currentPoint;

  } // End of getCurrentPoint() method.

  // -------------------------------------------------------------------------/

  // Define method to get beginning point for line.
  protected String getCurrentPointString()
  {
    // Return point value.
    return getPointString(currentPoint);

  } // End of getCurrentPointString() method.

  // -------------------------------------------------------------------------/

  // Define method to return compass direction.
  protected int getDirection()
  {
    // Return current direction.
    return currentDirection;

  } // End of getDirection() method.

  // -------------------------------------------------------------------------/

  // Define method to get drawing panel.
  protected Panel getDrawingPanel()
  {
    // Initialize current panel.
    return drawingPanel.getPanel();

  } // End of getDrawingPanel() method.

  // -------------------------------------------------------------------------/

  // Define method to get draw line.
  protected Line2D getDrawLine()
  {
    // Initialize current line.
    return drawLine;

  } // End of getDrawLine() method.

  // -------------------------------------------------------------------------/

  // Define method to get end button state.
  protected boolean getEndButtonState()
  {
    // Return boolean state for button.
    return endButton.isEnabled();

  } // End of getEndButtonState() method.

  // -------------------------------------------------------------------------/

  // Define method to get ending point for line.
  protected Point2D getEndPoint()
  {
    // Return point value.
    return endPoint;

  } // End of getEndPoint() method.

  // -------------------------------------------------------------------------/

  // Define method to return commands.
  protected int getLimit(int direction)
  {
    // Set the return value from the drawing panel.
    int retValue = drawingPanel.getLimit(direction);

    // Return only a valid number compass direction value.
    return retValue;

  } // End of getLimit() method.

  // -------------------------------------------------------------------------/

  // Define method to return a string for an array of Line2D.
  protected String getLineArray()
  {
    // Set the return value from the drawing panel.
    String retValue = drawingPanel.getLineArray();

    // Return the String value.
    return retValue;

  } // End of getLineArray() method.

  // -------------------------------------------------------------------------/

  // Define method to get drawing panel.
  protected Panel getMonitorPanel()
  {
    // Initialize current panel.
    return monitorPanel.getPanel();

  } // End of getMonitorPanel() method.

  // -------------------------------------------------------------------------/

  // Define method to get moveDistance class variable.
  protected int getMoveDistance()
  {
    // Return the moveDistance class variable.
    return moveDistance;

  } // End of getMoveDistance() method.

  // -------------------------------------------------------------------------/

  // Define method to set penDown boolean state.
  protected boolean getPenDown()
  {
    // Return penIsDown state variable.
    return penCheckbox.getState();

  } // End of getPenDown() method.

  // -------------------------------------------------------------------------/

  // Define method to return an sting of x and y coordinates for Point2D.
  private String getPointString(Point2D point)
  {
    // Define a set of arrays to convert integers to right aligned strings.
    int[] num    = new int[2];
    String[] val = new String[2];

    // Define a return value with a header value.
    String s = new String();

    // Define and initialize format spaces.
    String spaceOne = " ";
    String spaceTwo = "  ";

    // Assign values to the integer array.
    num[0] = ((int) point.getX() - 5);
    num[1] = ((int) point.getY() - 5);

    // Run through a for-loop to format numbers.
    for (int i = 0;i < num.length;i++)
    {
      // If number value is 0 to 9.
      if (num[i] < 10)
      {
        val[i] = spaceTwo + num[i];

      }
      // Else if number value is 10 to 99.
      else if (num[i] < 100)
      {
        val[i] = spaceOne + num[i];

      }
      // Else number is 100 or greater.
      else
      {
        val[i] = new Integer(num[i]).toString();

      } // End of if-elseif-else tree.

      // Increment another row for the string.
      s = "(" + val[0] + "," + val[1] + ")";

    } // End of for-loop to populate the array.

    // Return formatted string value.
    return s;

  } // End of getPointString() method.

  // -------------------------------------------------------------------------/

  // Define method to set beginning point for line.
  protected void setBeginPoint(int x, int y)
  {
    // Initialize point value if within valid grid coordinate range.
    if (((x >= drawingPanel.getBeginX()) &&
         (x <= drawingPanel.getEndX())) &&
        ((y >= drawingPanel.getBeginY()) &&
         (y <= drawingPanel.getEndY())))
    {
      // Initialize begin point.
      beginPoint = new Point2D.Double(x,y);

      // Initialize or reinitialize begin point display String.
      monitorPanel.setBeginPoint(getBeginPointString());

    }
    else
    {
      // If a value is submitted outside the range throw and exception.
      throw new NumberFormatException();

    } // End of initialization point with grid coordinate range.

  } // End of setBeginPoint() method.

  // -------------------------------------------------------------------------/

  // Define method to set beginning point for line.
  protected void setBeginPoint(Point2D point)
  {
    // Initialize point value if within valid grid coordinate range.
    if (((point.getX() >= drawingPanel.getBeginX()) &&
         (point.getX() <= drawingPanel.getEndX())) &&
        ((point.getY() >= drawingPanel.getBeginY()) &&
         (point.getY() <= drawingPanel.getEndY())))
     {
      // Initialize begin point.
      beginPoint = point;

      // Initialize or reinitialize begin point display String.
      monitorPanel.setBeginPoint(getBeginPointString());

    }
    else
    {
      // If a value is submitted outside the range throw and exception.
      throw new NumberFormatException();

    } // End of initialization point with grid coordinate range.

  } // End of setBeginPoint() method.

  // -------------------------------------------------------------------------/

  // Define initial button settings.
  protected void setButtonWakeup()
  {
    // Configure buttons from left to right:
    // -------------------------------------
    // The pen checkbox - enabled.
    penCheckbox.setFont(buttonFont);

    // The turn right button - enabled.
    turnRightButton.setEnabled(true);
    turnRightButton.setFont(buttonFont);
    turnRightButton.setSize(100,25);

    // The turn left button - enabled.
    turnLeftButton.setEnabled(true);
    turnLeftButton.setFont(buttonFont);
    turnLeftButton.setSize(100,25);

    // The move button - enabled.
    moveButton.setEnabled(true);
    moveButton.setFont(buttonFont);
    moveButton.setSize(100,25);

    // The print button - disabled.
    printButton.setEnabled(false);
    printButton.setFont(buttonFont);
    printButton.setSize(100,25);

    // The end button - disabled.
    endButton.setEnabled(false);
    endButton.setFont(buttonFont);
    endButton.setSize(100,25);

  } // End of setButtonWakeup() method.

  // -------------------------------------------------------------------------/

  // Define default method to set current point.
  protected void setCurrentPoint()
  {
    // Initialize point value to a default grid coordinate.
    currentPoint  = new Point2D.Double(drawingPanel.getBeginX(),
                                       drawingPanel.getBeginY());

  } // End of default setCurrentPoint() method.

  // -------------------------------------------------------------------------/

  // Define overloaded method to set current point.
  protected void setCurrentPoint(int x, int y)
  {
    // Initialize point value if within valid grid coordinate range.
    if (((x >= drawingPanel.getBeginX()) &&
         (x <= drawingPanel.getEndX())) &&
        ((y >= drawingPanel.getBeginY()) &&
         (y <= drawingPanel.getEndY())))
    {
      // Initialize begin point.
      currentPoint = new Point2D.Double(x,y);

    }
    else
    {
      // If a value is submitted outside the range throw and exception.
      throw new NumberFormatException();

    } // End of initialization point with grid coordinate range.

  } // End of overloaded setCurrentPoint() method.

  // -------------------------------------------------------------------------/

  // Define method to return compass direction.
  protected void setDirection(int direction)
  {
    // Set current direction.
    currentDirection = direction;

    // Set compass direction.
    monitorPanel.setCompass(getDirection());

  } // End of setDirection() method.

  // -------------------------------------------------------------------------/

  // Define method to set draw line.
  protected void setDrawLine()
  {
    // Initialize current line.
    drawLine = new Line2D.Double(beginPoint,endPoint);

  } // End of setDrawLine() method.

  // -------------------------------------------------------------------------/

  // Define method to set button enablement state.
  protected void setEndButton()
  {
    // Evaluate state of button enablement and alter it.
    if (endButton.isEnabled())
    {
      // Disable endButton and enable printButton.
      endButton.setEnabled(false);

      // Enable the print button if the pen was down.
      if (penCheckbox.getState()) { setPrintButton(); }

    }
    else
    {
      // Enable endButton.
      endButton.setEnabled(true);

    } // End of if to alter enabled state.

  } // End of setEndButton() method.

  // -------------------------------------------------------------------------/

  // Define method to set ending point for line.
  protected void setEndPoint(int x, int y)
  {
    // Initialize point value if within valid grid coordinate range.
    if (((x >= drawingPanel.getBeginX()) &&
         (x <= drawingPanel.getEndX())) &&
        ((y >= drawingPanel.getBeginY()) &&
         (y <= drawingPanel.getEndY())))
    {
      // Initialize begin point.
      endPoint = new Point2D.Double(x,y);

       // Initialize or reinitialize begin point display String.
      monitorPanel.setEndPoint(getCurrentPointString());

   }
    else
    {
      // If a value is submitted outside the range throw and exception.
      throw new NumberFormatException();

    } // End of initialization point with grid coordinate range.

  } // End of setEndPoint() method.

  // -------------------------------------------------------------------------/

  // Define method to set ending point for line.
  protected void setEndPoint(Point2D point)
  {
    // Initialize point value if within valid grid coordinate range.
    if (((point.getX() >= drawingPanel.getBeginX()) &&
         (point.getX() <= drawingPanel.getEndX())) &&
        ((point.getY() >= drawingPanel.getBeginY()) &&
         (point.getY() <= drawingPanel.getEndY())))
    {
      // Initialize begin point.
      endPoint = point;

      // Initialize or reinitialize begin point display String.
      monitorPanel.setEndPoint(getCurrentPointString());

    }
    else
    {
      // If a value is submitted outside the range throw and exception.
      throw new NumberFormatException();

    } // End of initialization point with grid coordinate range.

  } // End of setEndPoint() method.

  // -------------------------------------------------------------------------/

  // Define method to return direction.
  protected void setEndPointTextField()
  {
    // Set compass direction.
    monitorPanel.setEndPoint(getCurrentPointString());

  } // End of setEndPointTextField() method.

  // -------------------------------------------------------------------------/

  // Define method to set the labelDistance widget.
  protected void setLabelDistanceWakeup()
  {
    // Initialize the textfield for moves.
    labelDistance.setText("Pixel Distance");
    labelDistance.setFont(labelFont);
    labelDistance.setForeground(Color.blue);

  } // End of setLabelDistanceWakeup() method.

  // -------------------------------------------------------------------------/

  // Define method to set a line array.
  protected void setLineArray(Line2D line)
  {
    // Set the line array.
    drawingPanel.setLineArray(line);

  } // End of setLineArray() method.

  // -------------------------------------------------------------------------/

  // Define method to return direction.
  protected void setLinesDrawn(String list)
  {
    // Set compass direction.
    monitorPanel.setLinesDrawn(list);

  } // End of setLinesDrawn() method.

  // -------------------------------------------------------------------------/

  // Define method to set button enablement state.
  protected void setMoveButton()
  {
    // Evaluate state of button enablement and alter it.
    if (moveButton.isEnabled())
    {
      // Disable moveButton.
      moveButton.setEnabled(false);

    }
    else
    {
      // Enable moveButton.
      moveButton.setEnabled(true);

    } // End of if to alter enabled state.

  } // End of setMoveButton() method.

  // -------------------------------------------------------------------------/

  // Define method to set moveDistance class variable.
  protected void setMoveDistance(int distance)
  {
    // Set the moveDistance class variable.
    moveDistance = distance;

  } // End of setMoveDistance() method.

  // -------------------------------------------------------------------------/

  // Define method to set penDown boolean state.
  protected void setPen()
  {
    // Evaluate current state and effect change.
    if (penCheckbox.getState())
    {
      // Alter state of penCheckbox to false.
      penCheckbox.setState(!penCheckbox.getState());
      penCheckbox.setLabel(penUp);

    }
    else
    {
      // Alter state of penCheckbox to true.
      penCheckbox.setState(!penCheckbox.getState());
      penCheckbox.setLabel(penDown);

    } // End of if penCheckbox state change.

  } // End of setPen() method.

  // -------------------------------------------------------------------------/

  // Define method to set button enablement state.
  protected void setPrintButton()
  {
    // Evaluate state of button enablement and alter it.
    if (printButton.isEnabled())
    {
      // Disable printButton.
      printButton.setEnabled(false);
      setMoveButton();

    }
    else
    {
      // Enable printButton.
      printButton.setEnabled(true);
      setMoveButton();

    } // End of if to alter enabled state.

  } // End of setPrintButton() method.

  // -------------------------------------------------------------------------/

  // Define method to repaint component.
  protected void setRepaintDrawingPanel()
  {
    // Repaint the graphics array.
    drawingPanel.repaint();

  } // End of setRepaintDrawingPanel() method.

  // -------------------------------------------------------------------------/

  // Define method to set the textDistance widget.
  protected void setTextDistance()
  {
    // Initialize the textfield for distance.
    textDistance.setText("0");
    textDistance.setColumns(3);
    textDistance.setFont(textFont);
    textDistance.setEditable(true);
    textDistance.setBackground(Color.white);

  } // End of setTextDistance() method.

  // ---------------------------- End Methods --------------------------------/

  // ------------------------- Begin Static Main -----------------------------/

  // -------------------------- End Static Main ------------------------------/

} // End of TurtleControlPanelSix class.

// ------------------------------- End Class ---------------------------------/