/*
||  Program name:     TurtleConstantsOne.java
||  Created by:       Michael McLaughlin
||  Creation date:    12/18/01
||  History:
|| ----------------------------------------------------------------------
||  Date       Author                   Purpose
||  --------   ----------------------   ---------------------------------
||  dd/mm/yy   {Name}                   {Brief statement of change.}
|| ----------------------------------------------------------------------
||  Execution method: Instantiated as a class within a class.
||  Program purpose:  Designed as an constants repository for an AWT
||                    drawing program; based on Java: How to Program,
||                    4th Edition, by Deitel & Deitel, Chapter 11,
||                    Problem 27.
*/

// Class imports.
import java.lang.*;           // Required for general Java language use.

// ------------------------------ Begin Class --------------------------------/

// Class definition.
public class TurtleConstantsOne
{
  // ------------------------- Reference Classes -----------------------------/


  // -------------------------- Class Variables ------------------------------/

  // Define and initialize compass points.
  protected final static int EAST  = 0;
  protected final static int SOUTH = 1;
  protected final static int WEST  = 2;
  protected final static int NORTH = 3;

  // ------------------------- Begin Constructor -----------------------------/

  /*
  || The constructors of the class are:
  || =========================================================================
  ||  Access     Constructor Type  Constructor
  ||  ---------  ----------------  -------------------------------------------
  ||  protected  Default           TurtleConstantsOne()
  */

  // Define default constructor.
  protected TurtleConstantsOne() {}

} // End of TurtleConstantsOne class.

// ------------------------------- End Class ---------------------------------/