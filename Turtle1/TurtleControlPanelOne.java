/*
||  Program name:     TurtleControlPanelOne.java
||  Created by:       Michael McLaughlin
||  Creation date:    12/18/01
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
import java.awt.geom.*;       // Required for Java Graphics2D Libraries.
import java.lang.*;           // Required for general Java language use.

// ------------------------------ Begin Class --------------------------------/

// Class definition.
public class TurtleControlPanelOne extends Panel
{
  // ------------------------- Reference Classes -----------------------------/

  // None used.

  // -------------------------- Class Variables ------------------------------/

  // Define and initialize compass points.
  private final int EAST  = TurtleConstantsOne.EAST;
  private final int SOUTH = TurtleConstantsOne.SOUTH;
  private final int WEST  = TurtleConstantsOne.WEST;
  private final int NORTH = TurtleConstantsOne.NORTH;

  // Define and initialize compass logical direction values.
  private boolean east  = true;
  private boolean south = false;
  private boolean west  = false;
  private boolean north = false;

  // Define and initialize dynamic class variables.
  private boolean penIsDown    = true;
  private int moveDistance     = 0;
  private int currentDirection = EAST;

  // Define Graphics 2D objects.
  private Line2D drawLine;
  private Point2D beginPoint;
  private Point2D currentPoint;
  private Point2D endPoint;

  // Define a standard Fonts.
  private Font buttonFont = new Font("San Serif",Font.BOLD,11);
  private Font labelFont  = new Font("San Serif",Font.PLAIN|Font.ITALIC,12);
  private Font textFont   = new Font("San Serif",Font.PLAIN,11);

  // Define and initialize static strings.
  private String penUp     = new String("Pen Up");
  private String penDown   = new String("Pen Down");
  private String turnRight = new String("Turn Right");
  private String turnLeft  = new String("Turn Left");
  private String move      = new String("Move");
  private String print     = new String("Print");
  private String end       = new String("End");

  // Define and initialize AWT buttons.
  private Button penUpButton     = new Button(penUp);
  private Button penDownButton   = new Button(penDown);
  private Button turnRightButton = new Button(turnRight);
  private Button turnLeftButton  = new Button(turnLeft);
  private Button moveButton      = new Button(move);
  private Button printButton     = new Button(print);
  private Button endButton       = new Button(end);

  // Define and initialize other AWT objects.
  private TextField textDistance  = new TextField();
  private Label     labelDistance = new Label();
  private Panel     toolPanel     = new Panel();

  // ------------------- API Component Accessor Methods ----------------------/

  // Method to access AWT and Swing widgets.
  // ---------------------------------------
  // Button.
  protected Button getPenDownButton()   { return penDownButton; }
  protected Button getPenUpButton()     { return penUpButton; }
  protected Button getTurnRightButton() { return turnRightButton; }
  protected Button getTurnLeftButton()  { return turnLeftButton; }
  protected Button getMoveButton()      { return moveButton; }
  protected Button getPrintButton()     { return printButton; }
  protected Button getEndButton()       { return endButton; }

  // Panel.
  protected Panel  getToolPanel()       { return toolPanel; }

  // TextField.
  protected TextField getTextDistance() { return textDistance; }

  // ------------------------- Begin Constructor -----------------------------/

  /*
  || The constructors of the class are:
  || =========================================================================
  ||  Access     Constructor Type  Constructor
  ||  ---------  ----------------  -------------------------------------------
  ||  protected  Default           ComboBox()
  */

  // Define default constructor.
  protected TurtleControlPanelOne()
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
  ||  Point2D      getCurrentPoint()              protected
  ||  int          getDirection()                 protected
  ||  Line2D       getDrawLine()                  protected
  ||  boolean      getEndButtonState()            protected
  ||  Point2D      getEndPoint()                  protected
  ||  int          getMoveDistance()              protected
  ||  boolean      getPenDown()                   protected
  ||  void         setBeginPoint()                protected  int x
  ||                                                         int y
  ||  void         setBeginPoint()                protected  Point2D point
  ||  void         setButtonWakeup()              protected
  ||  void         setCurrentPoint()              protected  int x
  ||                                                         int y
  ||  void         setDirection()                 protected  int direction
  ||  void         setDrawLine()                  protected
  ||  void         setEndButton()                 protected
  ||  void         setEndPoint()                  protected  int x
  ||                                                         int y
  ||  void         setEndPoint()                  protected  Point2D point
  ||  void         setLabelDistanceWakeup()       protected
  ||  void         setMoveButton()                protected
  ||  void         setMoveDistance()              protected  int distance
  ||  void         setPenDownButton()             protected
  ||  void         setPenDown()                   protected
  ||  void         setPenUpButton()               protected
  ||  void         setPrintButton()               protected
  ||  void         setTextDistance()              protected
  */

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
    toolPanel.add(penUpButton);
    toolPanel.add(penDownButton);
    toolPanel.add(turnRightButton);
    toolPanel.add(turnLeftButton);
    toolPanel.add(moveButton);
    toolPanel.add(labelDistance);
    toolPanel.add(textDistance);
    toolPanel.add(printButton);
    toolPanel.add(endButton);
    toolPanel.setBackground(Color.lightGray);

  } // End of buildPanel() method.

  // -------------------------------------------------------------------------/

  // Define method to get beginning point for line.
  protected Point2D getBeginPoint()
  {
    // Return point value.
    return beginPoint;

  } // End of getBeginPoint() method.

  // -------------------------------------------------------------------------/

  // Define method to get current point.
  protected Point2D getCurrentPoint()
  {
    // Return point value.
    return currentPoint;

  } // End of getCurrentPoint() method.

  // -------------------------------------------------------------------------/

  // Define method to return compass direction.
  protected int getDirection()
  {
    // Return current direction.
    return currentDirection;

  } // End of getDirection() method.

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
    return penIsDown;

  } // End of getPenDown() method.

  // -------------------------------------------------------------------------/

  // Define method to set beginning point for line.
  protected void setBeginPoint(int x, int y)
  {
    // Initialize point value if within valid grid coordinate range.
    if (((x >= TurtleCanvasPanelOne.BEGIN_X) &&
         (x <= TurtleCanvasPanelOne.END_X)) &&
        ((y >= TurtleCanvasPanelOne.BEGIN_Y) &&
         (y <= TurtleCanvasPanelOne.END_Y)))
    {
      // Initialize begin point.
      beginPoint = new Point2D.Double(x,y);

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
    if (((point.getX() >= TurtleCanvasPanelOne.BEGIN_X) &&
         (point.getX() <= TurtleCanvasPanelOne.END_X)) &&
        ((point.getY() >= TurtleCanvasPanelOne.BEGIN_Y) &&
         (point.getY() <= TurtleCanvasPanelOne.END_Y)))
     {
      // Initialize begin point.
      beginPoint = point;

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
    // The penUp button - enabled.
    penUpButton.setEnabled(true);
    penUpButton.setFont(buttonFont);
    penUpButton.setSize(100,25);

    // The penDown button - disabled.
    penDownButton.setEnabled(false);
    penDownButton.setFont(buttonFont);
    penDownButton.setSize(100,25);

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
    currentPoint  = new Point2D.Double(TurtleCanvasPanelOne.BEGIN_X,
                                       TurtleCanvasPanelOne.BEGIN_Y);

  } // End of default setCurrentPoint() method.

  // -------------------------------------------------------------------------/

  // Define overloaded method to set current point.
  protected void setCurrentPoint(int x, int y)
  {
    // Initialize point value if within valid grid coordinate range.
    if (((x >= TurtleCanvasPanelOne.BEGIN_X) &&
         (x <= TurtleCanvasPanelOne.END_X)) &&
        ((y >= TurtleCanvasPanelOne.BEGIN_Y) &&
         (y <= TurtleCanvasPanelOne.END_Y)))
    {
      // Initialize begin point.
      currentPoint = new Point2D.Double(x,y);

      // Debug information.
      System.out.println("(" + (int) (getCurrentPoint().getX() -
                                        TurtleCanvasPanelOne.BEGIN_X) +
                         "," + (int) (getCurrentPoint().getY() -
                                        TurtleCanvasPanelOne.BEGIN_Y) + ")");
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
      if (penIsDown) { setPrintButton(); }

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
    if (((x >= TurtleCanvasPanelOne.BEGIN_X) &&
         (x <= TurtleCanvasPanelOne.END_X)) &&
        ((y >= TurtleCanvasPanelOne.BEGIN_Y) &&
         (y <= TurtleCanvasPanelOne.END_Y)))
    {
      // Initialize begin point.
      endPoint = new Point2D.Double(x,y);

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
    if (((point.getX() >= TurtleCanvasPanelOne.BEGIN_X) &&
         (point.getX() <= TurtleCanvasPanelOne.END_X)) &&
        ((point.getY() >= TurtleCanvasPanelOne.BEGIN_Y) &&
         (point.getY() <= TurtleCanvasPanelOne.END_Y)))
    {
      // Initialize begin point.
      endPoint = point;

    }
    else
    {
      // If a value is submitted outside the range throw and exception.
      throw new NumberFormatException();

    } // End of initialization point with grid coordinate range.

  } // End of setEndPoint() method.

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
      // Enable penDownButton.
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

  // Define method to set button enablement state.
  protected void setPenDownButton()
  {
    // Evaluate state of button enablement and alter it.
    if (penDownButton.isEnabled())
    {
      // Disable penDownButton and enable penUpButton.
      penDownButton.setEnabled(false);
      setPenDown();
      setPenUpButton();

    }
    else
    {
      // Enable penDownButton.
      penDownButton.setEnabled(true);
      setPenDown();

    } // End of if to alter enabled state.

  } // End of setPenDownButton() method.

  // -------------------------------------------------------------------------/

  // Define method to set penDown boolean state.
  protected void setPenDown()
  {
    // Evaluate state of button enablement and alter it.
    if (penIsDown) { penIsDown = false; } else { penIsDown = true; }

  } // End of setPenDown() method.

  // -------------------------------------------------------------------------/

  // Define method to set button enablement state.
  protected void setPenUpButton()
  {
    // Evaluate state of button enablement and alter it.
    if (penUpButton.isEnabled())
    {
      // Disable penUpButton and enable penDownButton.
      penUpButton.setEnabled(false);
      setPenDownButton();

    }
    else
    {
      // Enable penUpButton.
      penUpButton.setEnabled(true);

    } // End of if to alter enabled state.

  } // End of setPenUpButton() method.

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

} // End of TurtleControlPanelOne class.

// ------------------------------- End Class ---------------------------------/