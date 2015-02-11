/*
||  Program name:     CompassPoint.java
||  Created by:       Michael McLaughlin | Copyright 2002
||  Creation date:    10/15/02
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
public abstract class CompassPoint extends Panel
{
  // -------------------------- Class Variables ------------------------------/

  // Define and initialize valid compass point directions.
  private final static int EAST  = CompassDirections.EAST;
  private final static int SOUTH = CompassDirections.SOUTH;
  private final static int WEST  = CompassDirections.WEST;
  private final static int NORTH = CompassDirections.NORTH;

  // Define meaningful error exception String.
  private static String errMsg = "\n\n\t" +
                                 "Valid getInstance(int direction)\n\t" +
                                 "================================\n\t" +
                                 "- CompassDirections.EAST\n\t" +
                                 "- CompassDirections.SOUTH\n\t" +
                                 "- CompassDirections.WEST\n\t" +
                                 "- CompassDirections.NORTH\n\t" +
                                 "--------------------------------\n\t" +
                                 "- Attempted direction: ";

  // ------------------------- Begin Constructor -----------------------------/

  /*
  || The constructors of the class are:
  || =========================================================================
  ||  Access     Constructor Type  Constructor
  ||  ---------  ----------------  -------------------------------------------
  ||  protected  Default           CompassPoint()
  */

  // Define default constructor.
  protected CompassPoint() {}

  // -------------------------- End Constructor ------------------------------/

  // --------------------------- Begin Methods -------------------------------/

  /*
  || The static main instantiates a test instance of the class:
  || =========================================================================
  ||  Return Type  Method Name                    Access     Parameter List
  ||  -----------  -----------------------------  ---------  -----------------
  ||  void         buildPanel()                   private
  ||  Panel        getPanel()                     protected
  ||  boolean      getCompassPoint()              protected
  ||  CompassPoint getInstance()                  public     int direction
  ||  void         paint()                        public     Graphics g
  ||  void         setCompassPoint()              protected  boolean enable
  */

  // -------------------------------------------------------------------------/

  // Define buildPanel() method.
  protected abstract void buildPanel();

  // -------------------------------------------------------------------------/

  // Define getCompassDirection() method.
  protected abstract boolean getCompassPoint(int direction);

  // -------------------------------------------------------------------------/

  // Define getInstance() method.
  public static CompassPoint getInstance(int direction)
  {
		// Set valid input flag to false.
    boolean valid = false;

    // Validate input is in range and set input flag to true.
    for (int i = EAST;i <= NORTH;i++) {if (i == direction) {valid = true;} }

    // If valid input flag is false, throw runtime exception.
    if (!valid)
    {
			// Try throwing exception.
      try
      {
			  // Throw RuntimeException.
			  throw new CompassDirectionException(errMsg + "[" + direction + "]\n");

			}
			catch(Exception e)
			{
				// Print StackTrace.
				e.printStackTrace();

				// Force exit on critical failure.
				System.exit(1);

			} // End of catch block.

	  } // End of if valid input flag.

    // Determine the subclass to return.
    switch (direction)
    {
      case EAST:
        return new EastPoint();

      case SOUTH:
        return new SouthPoint();

      case WEST:
        return new WestPoint();

      case NORTH:
        return new NorthPoint();

      // Provided because the method must have a return value in all
      // cases, even if the the case is not possible because the
      // compiler can't guarantee that the statement is not reachable.
      default:
        return new EastPoint();

    } // End of subclass selection.

  } // End of getInstance() method.

  // -------------------------------------------------------------------------/

  // Define getPanel() method..
  protected abstract Panel getPanel();

  // -------------------------------------------------------------------------/

  // Define paint method.
  public abstract void paint(Graphics g);

  // -------------------------------------------------------------------------/

  // Define method to return commands.
  protected abstract void setCompassPoint(boolean enable);

  // ---------------------------- End Methods --------------------------------/

  // ------------------------- Begin Static Main -----------------------------/

  // -------------------------- End Static Main ------------------------------/

} // End of TurtleNorthPoint class.

// ------------------------------- End Class ---------------------------------/