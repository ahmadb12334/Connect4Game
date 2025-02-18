/** Connect4MouseListener Class
  * Class used for observing clicks on the GUI.
  * @since Jan 20, 2023
  * @author Ahmad Bhutta
  */
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Connect4ControllerMouse implements MouseListener {
  private Connect4Model model; //Model of the game
  private JPanel [] rowCircle = new JPanel [7]; //Array of JPanels on the board

    /* Default constructor
   * Used to observe clicks on the GUI
   * @param modelGui          The Connect 4 Model
   * @param circlePanel     Array of Jpanels which are observed for clicks
   */
  public Connect4ControllerMouse( Connect4Model modelGui, JPanel[] circlePanel)
    
  {
    this.model = modelGui;
    this.rowCircle = circlePanel;
 
  }
  

   /* Used when panel is clicked runs the mouseProcess method from the model
   * @param e         indicates when a mouse action command occurs
   */
  @Override
  public void mouseClicked(MouseEvent e) {       
    for (int x =0; x<rowCircle.length; x++) 
    {
      if (e.getSource() == rowCircle[x]) {
        model.mouseProcess(x);
        break;  
      }
    }
  }
   /* Not used
   * @param e         indicates when a mouse action command occurs
   */
  @Override
  public void mousePressed(MouseEvent e) {
  }
   /* Not used
   * @param e         indicates when a mouse action command occurs
   */
  @Override
  public void mouseReleased(MouseEvent e) {
  }
   /* Not used
   * @param e         indicates when a mouse action command occurs
   */
  @Override
  public void mouseEntered(MouseEvent e) { 
  }
   /* Not used
   * @param e         indicates when a mouse action command occurs
   */
  @Override
  public void mouseExited(MouseEvent e) {
  }
}