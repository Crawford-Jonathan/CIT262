/*
||  Program name:     WestPoint.java
||  Created by:       Michael McLaughlin | Copyright 2002
||  Creation date:    01/19/02
||  History:
|| ----------------------------------------------------------------------
||  Date       Author                   Purpose
||  --------   ----------------------   ---------------------------------
||  dd/mm/yy   {Name}                   {Brief statement of change.}
|| ----------------------------------------------------------------------
||  Execution method: Instantiated as a class within a class.
||  Program purpose:  Designed as an AWT Panel for a drawing program;
||                    based on Java: How to Program, 4th Edition, by
||                    Deitel & Deitel, Chapter 11, Problem 27.  This
||                    extends the solution to provide a visual depiction
||                    of direction.
*/

// Class imports.
import java.awt.*;            // Required for AWT widgets.

// ------------------------------ Begin Class --------------------------------/

// Class definition.
public class WestPoint extends CompassPoint
{
  // -------------------------- Class Variables ------------------------------/

  // Define and initialize private panel constants.
  protected final static int BEGIN_X =  0;
  protected final static int BEGIN_Y =  0;
  protected final static int END_X   = 10;
  protected final static int END_Y   = 20;

  // Define and initialize compass direction.
  private int compassPoint = CompassDirections.WEST;

  // Define and initialize compass direction.
  private boolean compassDirection = false;

  // Define and initialize polygon constructor arguments.
  int   nPoints = 3;
  int[] xPoints = { 10,  0, 10};
  int[] yPoints = {  0, 10, 20};

  // ------------------------- Begin Constructor -----------------------------/

  /*
  || The constructors of the class are:
  || =========================================================================
  ||  Access     Constructor Type  Constructor
  ||  ---------  ----------------  -------------------------------------------
  ||  protected  Default           WestPoint()
  */

  // Define default constructor.
  protected WestPoint()
  {
    // Build the Panel.
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
  ||  Panel        getPanel()                     protected
  ||  void         paint()                        public     Graphics g
  ||  void         setCompassPoint()              protected  boolean enable
  */

  // -------------------------------------------------------------------------/

  // Define method to return commands.
  protected void buildPanel()
  {
    // Set background color.
    setBackground(Color.lightGray);

    // Set panel size.
    setSize(END_X,END_Y);

  } // End of buildPanel() method.

  // -------------------------------------------------------------------------/

  // Define getCompassDirection() method.
  protected boolean getCompassPoint(int direction)
  {
    // If direction target direction, return true.
    if (compassPoint == direction) { return true; } else { return false; }

  } // End of getCompassDirection() method.

  // -------------------------------------------------------------------------/

  // Define getPanel() method.
  protected Panel getPanel()   { return this; }

  // -------------------------------------------------------------------------/

  // Define paint method.
  public void paint(Graphics g)
  {
    // Set color based on current direction.
    if (compassDirection) { g.setColor(Color.green); }
    else                  { g.setColor(Color.red);   }

    // Draw East direction point.
    g.drawPolygon(new Polygon(xPoints,yPoints,nPoints));
    g.fillPolygon(new Polygon(xPoints,yPoints,nPoints));

  } // End of paint() method.

  // -------------------------------------------------------------------------/

  // Define setCompassPoint() method.
  protected void setCompassPoint(boolean enable)
  {
    // Set compass direction.
    compassDirection = enable;

  } // End of setCompassPoint() method.

  // ---------------------------- End Methods --------------------------------/

  // ------------------------- Begin Static Main -----------------------------/

  // -------------------------- End Static Main ------------------------------/

} // End of WestPoint class.

// ------------------------------- End Class ---------------------------------/