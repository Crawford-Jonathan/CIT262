/*
||  Program name:     CompassDirections.java
||  Created by:       Michael McLaughlin | Copyright 2002
||  Creation date:    02/12/02
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
public abstract class CompassDirections
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
  ||  protected  Default           TurtleConstantsSix()
  */

  // Define default constructor.
  protected CompassDirections() {}

  // --------------------------- Begin Methods -------------------------------/

  // ---------------------------- End Methods --------------------------------/

  // ------------------------- Begin Static Main -----------------------------/

  // -------------------------- End Static Main ------------------------------/

} // End of CompassDirections class.

// ------------------------------- End Class ---------------------------------/