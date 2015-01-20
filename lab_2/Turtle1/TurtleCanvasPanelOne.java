/*
||  Program name:     TurtleCanvasPanelOne.java
||  Created by:       Michael McLaughlin
||  Creation date:    12/18/01
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
public class TurtleCanvasPanelOne extends Panel
{
  // -------------------------- Class Variables ------------------------------/

  // Define and initialize private panel constants.
  private   final static int OFFSET  =   1;
  protected final static int BEGIN_X =   5;
  protected final static int BEGIN_Y =   5;
  protected final static int END_X   = 810;
  protected final static int END_Y   = 510;

  // Define Graphics2D objects.
  private Line2D[] lineArray    = new Line2D.Double[0];
  private Point2D  currentPoint = new Point2D.Double(BEGIN_X,BEGIN_Y);

  // Define AWT objects.
  private Panel    canvasPanel = new Panel();

  // Define and initialize compass points.
  private   final int INVALID = -1;
  // Define and initialize compass points.
  private final int EAST  = TurtleConstantsOne.EAST;
  private final int SOUTH = TurtleConstantsOne.SOUTH;
  private final int WEST  = TurtleConstantsOne.WEST;
  private final int NORTH = TurtleConstantsOne.NORTH;

  // ------------------- API Component Accessor Methods ----------------------/

  // Method to access AWT and Swing widgets.
  protected Panel getPanel()   { return canvasPanel; }

  // ------------------------- Begin Constructor -----------------------------/

  /*
  || The constructors of the class are:
  || =========================================================================
  ||  Access     Constructor Type  Constructor
  ||  ---------  ----------------  -------------------------------------------
  ||  protected  Default           TurtleCanvasPanelOne()
  */

  // Define default constructor.
  protected TurtleCanvasPanelOne()
  {
    // Initiate set methods.
    buildPanel();

    /*
    || One of the points of the exercise is to expose the student to inner
    || classes as constructors of containing classes.  The Graphics2D
    || constructor requires a shape, which can be any of the following:
    ||
    ||  1) Arc2D
    ||  2) CubicCurve2D
    ||  3) Ellipse2D
    ||  4) Line2D
    ||  5) Point2D
    ||  6) Polygon
    ||  7) QuadCurve2D
    ||  8) Rectangle2D
    ||  9) RoundRectangle2D
    ||
    || The object types postpended with 2D have abstract constructors for
    || the containing or named class (file name at compilation time).  They
    || are constructed by using either of their inner class constructors,
    || which are Double and Float.  For example, you can construct a new
    || Point2D or Line2D as follows:
    ||
    ||   Point2D.Double p2d = new Point2D.Double(double x, double y);
    ||
    ||   Line2D.Double l2d  = new Line2D.Double(new Point2D.Double(10D,10D),
    ||                                          new Point2D.Double(90D,90D));
    ||
    */

  } // End of default constructor.

  // -------------------------- End Constructor ------------------------------/

  // --------------------------- Begin Methods -------------------------------/

  /*
  || The static main instantiates a test instance of the class:
  || =========================================================================
  ||  Return Type  Method Name                    Access     Parameter List
  ||  -----------  -----------------------------  ---------  -----------------
  ||  void         buildPanel()                   protected
  ||  int          getLimit()                     protected  int direction
  ||  void         paint()                        public
  ||  void         setLineArray()                 protected  Line line
  */

  // -------------------------------------------------------------------------/

  // Define method to return commands.
  protected void buildPanel()
  {
    // Set background color.
    setBackground(Color.lightGray);

    // Set panel size.
    setSize((END_X + (2 * BEGIN_X)),(END_Y + (2 * BEGIN_Y)));

  } // End of buildPanel() method.

  // -------------------------------------------------------------------------/

  // Define method to return commands.
  protected int getLimit(int direction)
  {
    // Set return variable.
    int retValue = INVALID;

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
      case INVALID:
        throw new NumberFormatException();

    } // End of switch to set current direction.

    // Return only a valid number compass direction value.
    return retValue;

  } // End of getLimit() method.

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

} // End of turtleCanvasPanelOne class.

// ------------------------------- End Class ---------------------------------/