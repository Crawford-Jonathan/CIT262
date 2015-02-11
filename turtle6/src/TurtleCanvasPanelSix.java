/*
||  Program name:     TurtleCanvasPanelSix.java
||  Created by:       Michael McLaughlin | Copyright 2002
||  Creation date:    02/12/02
||  History:
|| ----------------------------------------------------------------------
||  Date       Author                   Purpose
||  --------   ----------------------   ---------------------------------
||  dd/mm/yy   {Name}                   {Brief statement of change.}
|| ----------------------------------------------------------------------
||  Execution method: Instantiated as a class within a class.
||  Program purpose:  Designed as an AWT canvas for a drawing program;
||                    based on Java: How to Program, 4th Edition, by
||                    Deitel & Deitel, Chapter 11, Problem 27.
*/

// Class imports.
import java.awt.*;            // Required for AWT widgets.
import java.awt.geom.*;       // Required for Java Graphics2D Libraries.
import java.lang.*;           // Required for general Java language use.
import java.util.*;           // Required for the Vector class.

// ------------------------------ Begin Class --------------------------------/

// Class definition.
public class TurtleCanvasPanelSix extends Panel
{
  // -------------------------- Class Variables ------------------------------/

  // Define and initialize private panel constants.
  private final static int OFFSET  =   1;
  private final static int BEGIN_X =   5;
  private final static int BEGIN_Y =   5;
  private final static int END_X   = 810;
  private final static int END_Y   = 510;

  // Define Graphics2D objects.
  private Line2D[] lineArray    = new Line2D.Double[0];
  private Point2D  currentPoint = new Point2D.Double(BEGIN_X,BEGIN_Y);

  // Define AWT objects.
  private Panel canvasPanel = new Panel();

  // Define and initialize compass points.
  private final int EAST  = CompassDirections.EAST;
  private final int SOUTH = CompassDirections.SOUTH;
  private final int WEST  = CompassDirections.WEST;
  private final int NORTH = CompassDirections.NORTH;

  // ------------------------- Begin Constructor -----------------------------/

  /*
  || The constructors of the class are:
  || =========================================================================
  ||  Access     Constructor Type  Constructor
  ||  ---------  ----------------  -------------------------------------------
  ||  protected  Default           TurtleCanvasPanelSix()
  */

  // Define default constructor.
  public TurtleCanvasPanelSix()
  {
    // Initiate set methods.
    buildPanel();

  } // End of default constructor.

  // -------------------------- End Constructor ------------------------------/

  // --------------------------- Begin Methods -------------------------------/

  /*
  || The static main instantiates a test instance of the class:
  || =========================================================================
  ||  Return Type  Method Name                    Access     Parameter List
  ||  -----------  -----------------------------  ---------  -----------------
  ||  void         buildPanel()                   public
  ||  int          getBeginX()                    public
  ||  int          getBeginY()                    public
  ||  int          getEndX()                      public
  ||  int          getEndY()                      public
  ||  int          getLimit()                     public     int direction
  ||  String       getLineArray()                 public
  ||  Panel        getPanel()                     public
  ||  void         paint()                        public     Graphics g
  ||  void         setLineArray()                 public     Line2D line
  */

  // ------------------- API Component Accessor Methods ----------------------/

  // Method to access AWT and Swing widgets.
  public Panel getPanel()   { return this; }

  // -------------------------------------------------------------------------/

  // Define buildPanel() method.
  public void buildPanel()
  {
    // Set background color.
    setBackground(Color.lightGray);

    // Set panel size.
    setSize((END_X + (2 * BEGIN_X)),(END_Y + (2 * BEGIN_Y)));

  } // End of buildPanel() method.

  // -------------------------------------------------------------------------/

  // Define getBeginX() method.
  public int getBeginX()
  {
    // Return Beginning X coordinate.
    return BEGIN_X;

  } // End of getBeginX() method.

  // -------------------------------------------------------------------------/

  // Define getBeginY() method.
  public int getBeginY()
  {
    // Return Beginning X coordinate.
    return BEGIN_Y;

  } // End of getBeginY() method.

  // -------------------------------------------------------------------------/

  // Define getEndX() method.
  public int getEndX()
  {
    // Return Beginning X coordinate.
    return END_X;

  } // End of getEndX() method.

  // -------------------------------------------------------------------------/

  // Define getEndY() method.
  public int getEndY()
  {
    // Return Beginning X coordinate.
    return END_Y;

  } // End of getEndY() method.

  // -------------------------------------------------------------------------/

  // Define method to return commands.
  public int getLimit(int direction)
  {
    // Set return variable.
    int retValue;

    // Switch to set the current direction.
    switch (direction)
    {
      case EAST:
        retValue = (END_X - BEGIN_X);
        break;

      case SOUTH:
        retValue = (END_Y - BEGIN_Y);
        break;

      case WEST:
        retValue = BEGIN_X;
        break;

      case NORTH:
        retValue = BEGIN_Y;
        break;

      // If a direction that is not in the set of acceptable constants
      // is used as the argument to the function, raise an exception
      // because the function must return a correct value.
      default:
        throw new NumberFormatException();

    } // End of switch to set current direction.

    // Return only a valid number compass direction value.
    return retValue;

  } // End of getLimit() method.

  // -------------------------------------------------------------------------/

  // Define method to return a string for an array of Line2D.
  public String getLineArray()
  {
    // Define a set of arrays to convert integers to right aligned strings.
    int[] num    = new int[4];
    String[] val = new String[4];

    // Define a return value with a header value.
    String s = new String();

    // Define and initialize format spaces.
    String spaceOne = " ";
    String spaceTwo = "  ";

    // Loop through the sets of points.
    for (int i = 0;i < lineArray.length;i++)
    {
      // Assign values to the integer array.
      num[0] = ((int) lineArray[i].getP1().getX() - 5);
      num[1] = ((int) lineArray[i].getP1().getY() - 5);
      num[2] = ((int) lineArray[i].getP2().getX() - 5);
      num[3] = ((int) lineArray[i].getP2().getY() - 5);

      // Run through a for-loop to format numbers.
      for (int j = 0;j < num.length;j++)
      {
        // If number value is 0 to 9.
        if (num[j] < 10)
        {
          val[j] = spaceTwo + num[j];

        }
        // Else if number value is 10 to 99.
        else if (num[j] < 100)
        {
          val[j] = spaceOne + num[j];

        }
        // Else number is 100 or greater.
        else
        {
          val[j] = new Integer(num[j]).toString();

        } // End of if-elseif-else tree.

      } // End of nested loop validation.

      // Increment another row for the string.
      s += "(" + val[0] +
           "," + val[1] + ") " +
           "(" + val[2] +
           "," + val[3] + ")\n";

    } // End of for-loop to populate the array.

    // Return formatted string.
    return s;

  } // End of getLineArray() method.

  // -------------------------------------------------------------------------/

  // Define paint method.
  public void paint(Graphics g)
  {
    // Cast the object.
    Graphics2D g2d = (Graphics2D) g;

    // Draw the frame, offset right border two pixels for balance.
    g.setColor(Color.white);
    g.fillRect((BEGIN_X - OFFSET),(BEGIN_Y - OFFSET),
               (END_X - (BEGIN_X - OFFSET)),(END_Y - BEGIN_Y));

    // Draw the border, offset right border two pixels for balance.
    g.setColor(Color.blue);
    g.drawRect((BEGIN_X - (2 * OFFSET)),(BEGIN_Y - (2 * OFFSET)),
               (END_X - BEGIN_X),(END_Y - BEGIN_Y));

    // Set color for dynamic lines.
    g.setColor(Color.black);

    // Loop to draw lines.
    for (int i = 0;i < lineArray.length;i++)
    {
      // If to demonstrate that Graphics2D objects cannot draw in a single
      // point or pixel of space and Point2D is not drived from Shape and
      // therefore cannot be drawn.
      if ((lineArray[i].getP1().getX() == lineArray[i].getP2().getX()) &&
          (lineArray[i].getP1().getY() == lineArray[i].getP2().getY()))
      {
        // Set background color.
        g.setColor(Color.yellow);

        // Fill oval around the point for two pixels in each direction.
        g2d.draw(new Ellipse2D.Double(lineArray[i].getP1().getX() - 2,
                                      lineArray[i].getP1().getY() - 2,
                                      5,5));

        // Reset color to black.
        g.setColor(Color.black);

      } // End of if to demonstrate Point.equals() method.

      // Draw a line.
      g2d.draw(lineArray[i]);

    } // End of for-loop to draw the array of lines.

  } // End of paint() method.

  // -------------------------------------------------------------------------/

  // Define method to set a line array.
  protected void setLineArray(Line2D line)
  {
    // Define and constructor a local Vector.
    Vector v = new Vector();

    // Initialize or reinitialize a vector.
    for (int i = 0;i < lineArray.length;i++)
    {
      // Add an object to the vector until all lines are added.
      v.add((Object) lineArray[i]);

    } // End of for-loop to populate the array.

    // Add the new line to the vector.
    v.add((Object) line);

    // Define, size and initialize the String array.
    lineArray = new Line2D.Double[v.size()];

    // The copyInto method will use the instanceOf to determine the right
    // subtype and does not require explicit casting.
    v.copyInto(lineArray);

  } // End of setLineArray() method.

  // ---------------------------- End Methods --------------------------------/

  // ------------------------- Begin Static Main -----------------------------/

  // -------------------------- End Static Main ------------------------------/

} // End of TurtleCanvasPanelSix class.

// ------------------------------- End Class ---------------------------------/