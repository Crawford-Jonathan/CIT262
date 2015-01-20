/*
||  Program name:     CompassPanel.java
||  Created by:       Michael McLaughlin | Copyright 2002
||  Creation date:    02/12/02
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
public class CompassPanel extends Panel
{
  // -------------------------- Class Variables ------------------------------/

  // Define and initialize private panel constants.
  private final int OFFSET  =   1;
  private final int BEGIN_X =   0;
  private final int BEGIN_Y =   0;
  private final int END_X   = 200;
  private final int END_Y   = 200;

  // Define and initialize compass points.
  private final int EAST  = CompassDirections.EAST;
  private final int SOUTH = CompassDirections.SOUTH;
  private final int WEST  = CompassDirections.WEST;
  private final int NORTH = CompassDirections.NORTH;

  // Define and initialize compass direction.
  private int compassDirection = EAST;

  // Define and initialize compass point labels for user interface.
  private final String EAST_LABEL  = "Right";
  private final String SOUTH_LABEL = "Down";
  private final String WEST_LABEL  = "Left";
  private final String NORTH_LABEL = "Up";

  // Define and initialize compass direction label.
  private TextField compassTextField   = new TextField(EAST_LABEL,3);

  // ------------------------- Reference Classes -----------------------------/

  // Define a CompassPoint array.
  private CompassPoint[] compassPoint =
    { CompassPoint.getInstance(EAST),
      CompassPoint.getInstance(SOUTH),
      CompassPoint.getInstance(WEST),
      CompassPoint.getInstance(NORTH) };

  // ------------------------- Begin Constructor -----------------------------/

  /*
  || The constructors of the class are:
  || =========================================================================
  ||  Access     Constructor Type  Constructor
  ||  ---------  ----------------  -------------------------------------------
  ||  protected  Default           CompassPanel()
  */

  // Define default constructor.
  protected CompassPanel()
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
  ||  void         buildPanel()                   private
  ||  Panel        getPanel()                     public
  ||  TextField    getCompassText()               public
  ||  void         setCompass()                   public     int direction
  ||  void         setCompassLabel()              private    int direction
  ||  void         setPaint()                     private
  */

  // -------------------------------------------------------------------------/

  // Define method to return commands.
  private void buildPanel()
  {
    setLayout(new BorderLayout());

    // Set a label font and position it.
    compassTextField.setFont(new Font("San Serif",Font.BOLD,11));
    compassTextField.setBackground(Color.white);
    compassTextField.setEditable(false);
    add(compassTextField,BorderLayout.CENTER);

    // Add compass points.
    add(compassPoint[EAST].getPanel(),BorderLayout.EAST);
    add(compassPoint[SOUTH].getPanel(),BorderLayout.SOUTH);
    add(compassPoint[WEST].getPanel(),BorderLayout.WEST);
    add(compassPoint[NORTH].getPanel(),BorderLayout.NORTH);

    // Set default arrow.
    setPaint();

    // Set background color.
    setBackground(Color.lightGray);

    // Set panel size.
    getPanel().setSize(END_X,END_Y);

  } // End of buildPanel() method.

  // -------------------------------------------------------------------------/

  // Define getCompassText() method.
  public TextField getCompassText() { return compassTextField; }

  // -------------------------------------------------------------------------/

  // Define getPanel() method.
  public Panel getPanel() { return this; }

  // -------------------------------------------------------------------------/

  // Define method to return direction.
  public void setCompass(int direction)
  {
    // Set compass direction.
    compassDirection = direction;
    setCompassLabel(compassDirection);
    setPaint();

  } // End of setCompass() method.

  // -------------------------------------------------------------------------/

  // Define method to return direction label.
  private void setCompassLabel(int direction)
  {
    // Set compass direction label.
    switch (direction)
    {
      case EAST:
        compassTextField.setText(EAST_LABEL);
        break;

      case SOUTH:
        compassTextField.setText(SOUTH_LABEL);
        break;

      case WEST:
        compassTextField.setText(WEST_LABEL);
        break;

      case NORTH:
        compassTextField.setText(NORTH_LABEL);
        break;

    } // End case statement to set compass label.

  } // End of setCompassLabel method.

  // -------------------------------------------------------------------------/

  // Define setPaint method for compass points.
  private void setPaint()
  {
    // To reset all colors of the compass points.
    for (int i = EAST;i <= NORTH;i++)
    {
      // Set color based on current direction.
      compassPoint[i].setCompassPoint(
                        compassPoint[i].getCompassPoint(compassDirection));
      compassPoint[i].getPanel().repaint();

    } // End of for loop to draw four arrows.

  } // End of setPaint() method.

  // ---------------------------- End Methods --------------------------------/

  // ------------------------- Begin Static Main -----------------------------/

  // -------------------------- End Static Main ------------------------------/

} // End of CompassPanel class.

// ------------------------------- End Class ---------------------------------/