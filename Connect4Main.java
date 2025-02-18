/** Connect 4 Main
  * Connect 4 Main class
  * @since Jan 12, 2023
  * @author Ahmad Bhutta
  */ 
import javax.swing.*;
import java.awt.*;

public class Connect4Main {
  
  public static void main (String[] args)
  {
    Connect4Model model = new Connect4Model();
    Connect4GUI mainScreen = new Connect4GUI(model);
    
    //Initialize the Frame
    JFrame frame = new JFrame("Connect 4");
    frame.setSize (450,300);
    frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(mainScreen);
    frame.setVisible (true);
    frame.pack();
  }
}

