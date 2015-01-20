/*
||  Program name:     TurtleMonitorPanelSix.java
||  Created by:       Michael McLaughlin | Copyright 2002
||  Creation date:    02/12/02
||  History:
|| ----------------------------------------------------------------------
||  Date       Author                   Purpose
||  --------   ----------------------   ---------------------------------
||  dd/mm/yy   {Name}                   {Brief statement of change.}
|| ----------------------------------------------------------------------
||  Execution method: Instantiated as a class within a class.
||  Program purpose:  Designed as an AWT Frame for a drawing program;
||                    based on Java: How to Program, 4th Edition, by
||                    Deitel & Deitel, Chapter 11, Problem 27.
*/

// Class imports.
import java.awt.*;            // Required for AWT widgets.

// ------------------------------ Begin Class --------------------------------/

// Class definition.
public class TurtleMonitorPanelSix extends Panel
{
  // ------------------------- Reference Classes -----------------------------/

  // Define objects on dependent classes.
  private CompassPanel compassPanel = new CompassPanel();

  // -------------------------- Class Variables ------------------------------/

  // Define and initialize compass points.
  private final int EAST  = CompassDirections.EAST;
  private final int SOUTH = CompassDirections.SOUTH;
  private final int WEST  = CompassDirections.WEST;
  private final int NORTH = CompassDirections.NORTH;

  // Define String variables for initialized state with the default
  // constructor, override constructor allows initialized grid location.
  private String beginPointString = "(  0,  0)";
  private String endPointString   = "(  0,  0)";

  // Define standard Fonts.
  private Font labelFont  = new Font("San Serif",Font.PLAIN|Font.ITALIC,12);
  private Font textFont   = new Font("Monospaced",Font.PLAIN,11);

  // Define labels.
  private Label beginPointLabel = new Label("Begin Point");
  private Label directionLabel  = new Label("Direction");
  private Label endPointLabel   = new Label("End Point");
  private Label linesDrawnLabel = new Label("Lines Drawn");

  // Define a widget panel for monitoring details.
  private Panel widgetPanel  = new Panel();

  // Define a text area for drawn line arrays.
  private TextArea linesDrawn = new TextArea(null,2,20,
                                      TextArea.SCROLLBARS_VERTICAL_ONLY);

  // Define AWT objects.
  private TextField beginPoint = new TextField(null,7);
  private TextField endPoint   = new TextField(null,7);

  // ------------------------- Begin Constructor -----------------------------/

  /*
  || The constructors of the class are:
  || =========================================================================
  ||  Access     Constructor Type  Constructor
  ||  ---------  ----------------  -------------------------------------------
  ||  protected  Default           TurtleMonitorPanelSix()
  ||  protected  Override          TurtleMonitorPanelSix(String beginString
  ||                                                    ,String endString)
  */

  // -------------------------------------------------------------------------/

  // Define default constructor.
  protected TurtleMonitorPanelSix()
  {
    // Build Frame.
    buildFrame();

  } // End of default constructor.

  // -------------------------------------------------------------------------/

  // Define override constructor.
  protected TurtleMonitorPanelSix(String beginString,String endString)
  {
    // Override default grid coordinate constructor.
    beginPointString = beginString;
    endPointString   = endString;

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
  ||  Panel        getPanel()                     protected
  ||  void         setBeginPoint()                protected  String begin
  ||  void         setCompass()                   protected  int direction
  ||  void         setEndPoint()                  protected  String end
  */

  // ------------------- API Component Accessor Methods ----------------------/

  // Method to access Panel.
  protected Panel getPanel()   { return this; }

  // Methods to access AWT and Swing components.
  protected TextArea getLinesDrawn()  { return linesDrawn; }
  protected TextField getBeginPoint() { return beginPoint; }
  protected TextField getEndPoint()   { return endPoint; }

  // -------------------------------------------------------------------------/

  // Define method to return commands.
  protected void buildFrame()
  {
    // Set layout for frame.
    setLayout(new BorderLayout());

    // Define fonts.
    linesDrawn.setFont(textFont);
    beginPoint.setFont(textFont);
    endPoint.setFont(textFont);

    // Define label properties.
    beginPointLabel.setFont(labelFont);
    beginPointLabel.setForeground(Color.blue);
    directionLabel.setFont(labelFont);
    directionLabel.setForeground(Color.blue);
    endPointLabel.setFont(labelFont);
    endPointLabel.setForeground(Color.blue);
    linesDrawnLabel.setFont(labelFont);
    linesDrawnLabel.setForeground(Color.blue);

    // Define initial text and properties of text fields.
    beginPoint.setText(beginPointString);
    beginPoint.setEnabled(false);
    endPoint.setText(endPointString);
    endPoint.setEnabled(false);
    linesDrawn.setEditable(false);
    linesDrawn.setBackground(Color.white);
    linesDrawn.setForeground(Color.gray);

    // Set background color.
    setBackground(Color.lightGray);

    // Add components to widgetPanel
    widgetPanel.add(directionLabel);
    widgetPanel.add(compassPanel.getPanel());
    widgetPanel.add(linesDrawnLabel);
    widgetPanel.add(linesDrawn);
    widgetPanel.add(beginPointLabel);
    widgetPanel.add(beginPoint);
    widgetPanel.add(endPointLabel);
    widgetPanel.add(endPoint);

    // Add component panel to class panel.
    add(widgetPanel,BorderLayout.CENTER);

  } // End of buildPanel() method.

  // -------------------------------------------------------------------------/

  // Define method to set point String.
  protected void setBeginPoint(String begin)
  {
    // Set TextField String value.
    beginPoint.setText(begin);

  } // End of setEndButton() method.

  // -------------------------------------------------------------------------/

  // Define method to return direction.
  protected void setCompass(int direction)
  {
    // Set compass direction.
    compassPanel.setCompass(direction);

  } // End of setCompass() method.

  // -------------------------------------------------------------------------/

  // Define method to set point String.
  protected void setEndPoint(String end)
  {
    // Set TextField String value.
    endPoint.setText(end);

  } // End of setEndButton() method.

  // -------------------------------------------------------------------------/

  // Define method to return direction.
  protected void setLinesDrawn(String list)
  {
    // Set compass direction.
    linesDrawn.replaceRange(list,0,linesDrawn.getText().length());

  } // End of setCompass() method.

  // ---------------------------- End Methods --------------------------------/

  // ------------------------- Begin Static Main -----------------------------/

  // -------------------------- End Static Main ------------------------------/

} // End of TurtleMonitorPanelSix class.

// ------------------------------- End Class ---------------------------------/