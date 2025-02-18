/** Connect4Controller Class
  * Class used for processing button clicks.
  * @since Jan 12, 2023
  * @author Ahmad Bhutta
  */

import javax.swing.*;
import java.awt.event.*;

public class Connect4Controller extends Object implements ActionListener
{
  private Connect4Model game; //The Model used to describe the game 
  /* Default constructor
   * Links the component to the Model
   * @param gui          The Model describing game behaviour
   * @param aTextField     The component being interacted with
   */
    public Connect4Controller(Connect4Model gameModel)
  {
  this.game = gameModel;   
  }
  
  /** Receives userInput and runs the buttonPressed Method from 
    * @param e The event sent from the textbox component
    */ 
  public void actionPerformed(ActionEvent e)
  {
    this.game.buttonPressed(e.getActionCommand());
  }// end of actionPerformed
}//end of class

