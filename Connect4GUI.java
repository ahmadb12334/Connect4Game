/** Connect4GUI Class
  * Class used for displaying the game board, and the game itself.
  * @since Jan 21, 2023
  * @author Ahmad Bhutta
  */
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Objects;

public class Connect4GUI extends JPanel {
//JText Fields for input of player names, and rounds
  private JTextField roundsPlay = new JTextField(7);
  private JTextField player1Name = new JTextField(7);
  private JTextField player2Name = new JTextField(7);
  
  
  private Connect4Model models;
  
  //Game buttons 
  private JButton playGame = new JButton("Play Game"); //Button for playing the game 
  private JButton quitGame = new JButton("Quit"); //Button for quitting Game
  private JButton helpButton = new JButton ("Help"); //Button for help panel
  private JButton startGame = new JButton ("Start" ); //Button for starting game
  private JButton nextRound = new JButton ("Next Round"); //Button for Next round to start next round
  private JButton returnToMenuButton = new JButton(" <- Return"); // Button to return back to menu
  private JButton returnToGameMenuButton=  new JButton ("Return to Menu"); //Button to return to end game Menu
//JLabels for the view
  private JLabel enterRounds = new JLabel ("Enter The amount of Rounds: "); //Label to tell the user to enter rounds
  private JPanel scoreBoard = new JPanel(); //Panel used to Display score board 
  //Image Icons 
  private ImageIcon connect4Logo= new ImageIcon(new ImageIcon("connect4Logo.png").getImage().getScaledInstance(800, 600, Image.SCALE_DEFAULT)); // Connect4 menu Logo 
  private ImageIcon gameOverLogoImage= new ImageIcon(new ImageIcon("GameOver.png").getImage().getScaledInstance(600, 400, Image.SCALE_DEFAULT));//Game over logo
  private ImageIcon scoreBoardI= new ImageIcon(new ImageIcon("scoreBoard.png").getImage().getScaledInstance(600, 400, Image.SCALE_DEFAULT)); //Score board logo
  private JLabel gameOverLogo = new JLabel(); //Used to display the score board image
  private JLabel logo = new JLabel (); //used to display the connect 4 logo
  private  JTextArea helpArea = new JTextArea("\tConnect 4 Rules\n" +
                                              "\tOBJECTIVE:\n" +
                                              "\tTo be the first player to connect 4 of the same colored discs in a row \n" +
                                              "\t(either vertically, horizontally, or diagonally\n" +
                                              "\tHOW TO PLAY:\n" +
                                              "\tFirst, decide who goes first and what color each player will have.\n" +
                                              "\tPlayers must alternate turns, and only one disc can be dropped in each turn.\n" +
                                              "\tOn your turn, drop one of your colored discs from the top into any of the \n \tseven slots.\n" +
                                              "\tThe game ends when there is a 4-in-a-row or a stalemate.\n" +
                                              "\tThe starter of the previous game goes second on the next game", 20,20); //JText area of Instructions of game 
  
  private JTextArea endGameText; // Displays who won, score and message 
  private Color red = new Color(240,118,102); //Custom Color
  private Color yellow = new Color(243, 245, 11);//Custom Colo
  private Color green = new Color(90,166 ,44);//Custom Colo
  private  Color blue = new Color(0,128,255);//Custom Colo
  private  boolean roundOver=false; //Used to determine when round is over 
  private JPanel [] rowCircle = new JPanel[7]; //Array of panels for each row 
  private JLabel[][] boardCircles = new JLabel[7][6]; //2D array of labels for each circle on board
  //player Names & Num of rounds played
  private JLabel player1Score = new JLabel("Player 1 Score: ");
  private JLabel player2Score = new JLabel("Player 2 Score: ");
  public JLabel currentRound = new JLabel();
  private JLabel player1NameLabel;
  private JLabel player2NameLabel;
  
  //images used for board display
  private ImageIcon yellowCircle = new ImageIcon(new ImageIcon("yellowCircle.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
  private ImageIcon emptyCircle =new ImageIcon(new ImageIcon("emptyCircle.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
  private ImageIcon redCircle =new ImageIcon(new ImageIcon("redCircle.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
  
  //JButtons
  private JButton exit = new JButton("Go Back"); //Go back button to menu 
  private JButton restartGame = new JButton("Restart Game"); //Restarts the game when pressed 
  private JButton endGame = new JButton("End Game"); //Closes the game
  private JButton viewScore = new JButton("Score Board"); //Shows the score board 
  private JButton newGame = new JButton("New Game"); //Launches a new game 
  
  //Board display JPanels
  private JPanel boardMain = new JPanel();
  private JPanel boardNorth = new JPanel();
  private JPanel boardSouth = new JPanel();
  private JPanel boardCenter = new JPanel();
  
  //Menu JPanels
  private JPanel menuMain = new JPanel();
  private JPanel menuNorth = new JPanel();
  private JPanel menuCenter = new JPanel();
  private JPanel menuSouth = new JPanel();
  private JPanel menuWest = new JPanel();
  private JPanel menuEast = new JPanel();
  JPanel newGamePanel = new JPanel();
  
  //Game over JPanel
  private JPanel gameOverMain = new JPanel();
  private JPanel menu = new JPanel();
  private JPanel endGameWest= new JPanel();
  private JPanel endGameCenter= new JPanel();
  
  private JPanel southPanel = new JPanel(); //Restart Panels
  private JPanel northPanel = new JPanel();
  
  private JButton start = new JButton("Start Game");
  private JTextField roundsRestart = new JTextField(7);
  private JPanel resetRound = new JPanel();
// New Game input JPanels
  private JPanel northPanelEnd = new JPanel();
  private JPanel centerPanelEnd = new JPanel();
  
  /** Default constructor for the GUI.  Assigns Model and View, identifies controllers
    * and draws the layout
    * @param model        The Model for the game
    */
  public Connect4GUI(Connect4Model model) {
    this.models = model;
    models.setGUI(this);
    this.player1NameLabel= new JLabel("Player 1 ");
    this.player2NameLabel= new JLabel("Player 2 ");
    for(int i=0; i<rowCircle.length; i++)
    {
      this.rowCircle[i] = new JPanel();
    }
    this.menu();
    this.registerControllers();
    this.mouseRegisterControllers();
  }
  //Method registers the Buttons to the Connect4Controller class
  private void registerControllers() {
    Connect4Controller controller = new Connect4Controller(this.models);
    this.playGame.addActionListener(controller);
    this.restartGame.addActionListener(controller);
    this.endGame.addActionListener(controller);
    this.viewScore.addActionListener(controller);
    this.newGame.addActionListener(controller);
    this.helpButton.addActionListener(controller);
    this.quitGame.addActionListener(controller);
    this.returnToMenuButton.addActionListener(controller);
    this.startGame.addActionListener(controller);
    this.nextRound.addActionListener(controller);
    this.returnToGameMenuButton.addActionListener(controller);
    this.start.addActionListener(controller);
    this.newGame.addActionListener(controller);
    this.exit.addActionListener(controller);
  }
//This method Gets players from model, and displays a scoreboard
  public void scoreBoard(ArrayList<Connect4Player> players)
  {
    JPanel leaderBoard = new JPanel();
    JPanel exitPanel = new JPanel();
    JPanel scoreBoardLogo = new JPanel();
    JLabel scoreImage = new JLabel();
    
    scoreImage.setIcon(this.scoreBoardI);
    
    this.removeAll();
    this.remove(boardMain);
    this.revalidate();
    this.repaint();
    this.scoreBoard.removeAll();
    this.scoreBoard.setVisible(true);
    //Colors
    Color reds = new Color(150,200,100);
    Color yellow = new Color(243, 245, 11);
    Color green = new Color(90,166 ,44);
    Color blue = new Color(0,128,255);
    JLabel scoreTitle = new JLabel("SCORE BOARD:");
    Color[] colors = {reds,yellow,green,blue,Color.magenta,Color.CYAN,Color.LIGHT_GRAY,Color.pink};
    GridLayout scoreLayout = new GridLayout(players.size(),0);
    BorderLayout finalLayout = new BorderLayout();
    leaderBoard.setLayout(scoreLayout);
    scoreBoard.add(scoreBoardLogo);
    exit.setBackground(Color.RED);
    exit.setPreferredSize(new Dimension(140,75));
    exit.setOpaque(true);
    exit.setBorderPainted(false);
    exit.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 13));
    //Gets the players and outputs to score board
    for(int i=0; i<players.size(); i++)
    {
      String formattedName = "Name:"+players.get(i).getPlayerName() +"\nScore: "+ players.get(i).getPlayerScore()+ "\n" + players.get(i).getPlayerName()+" vs "+players.get(i).getOponent();
      JTextArea player = new JTextArea(formattedName);
      int randomColor = (int) Math.floor(Math.random() * 8);//Randomly selects a color to display 
      player.setBackground(colors[randomColor]);
      player.setOpaque(true);
      player.setEditable(false);
      scoreTitle.setFont(new Font("Stencil",Font.BOLD, 20));
      player.setFont(new Font("Stencil",Font.BOLD, 15));
      leaderBoard.add(player);
    }
    this.scoreBoard.setLayout(finalLayout);
    exitPanel.add(exit);
    scoreBoardLogo.add(scoreImage);
    scoreBoard.add(scoreBoardLogo, BorderLayout.NORTH);
    scoreBoard.add(leaderBoard, BorderLayout.CENTER);
    scoreBoard.add(exitPanel, BorderLayout.SOUTH);
    this.add(scoreBoard);
  } 
//This method is used if both players wish to play another game
  public void restartRounds()
  {
    this.removeMenu();
    this.removeAll();
    this.remove(boardMain);
    this.revalidate();
    this.repaint();
    this.resetRound.setVisible(true);
    this.southPanel.setVisible(true);
    this.northPanel.setVisible(true);
    
    JLabel enterRoundLabel = new JLabel("Enter Rounds");
    this.start.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 13));
    this.start.setBackground(green);
    this.start.setPreferredSize(new Dimension(140, 75));
    northPanel.add(enterRoundLabel);
    northPanel.add(this.roundsRestart);
    southPanel.add(start);
    this.resetRound.setLayout(new BorderLayout());
    this.resetRound.add(northPanel,BorderLayout.NORTH );
    this.resetRound.add(southPanel, BorderLayout.CENTER);
    this.removeAll();
    this.add(this.resetRound);
  }  
  //This method is used to get the user input for PlayerNames and the Number of rounds they want to play
  public void playGameRounds()
  {
    this.menuWest.setVisible(true);
    this.menuEast.setVisible(true);
    JComponent[] componentsFalse = {playGame, quitGame, helpButton, logo};
    JComponent[] componentsTrue = {roundsPlay, enterRounds, player1NameLabel, player2NameLabel, player1Name, player2Name, startGame, returnToMenuButton};
    for(int x=0; x<componentsFalse.length; x++) {
      componentsFalse[x].setVisible(false);
    }
    for(int x=0; x<componentsTrue.length; x++) {
      componentsTrue[x].setVisible(true);
    }
  }
  //This method displays the connect4 board and starts the game
  public void playGame() {
    this.resetRound.setVisible(false);
    this.menuMain.setVisible(false);
    this.wipeBoard();
  }
// this method is used to refresh the frame and display the gameOver Menu
  public void returnToGameMenu() {
    this.gameOverMain.removeAll();
    this.revalidate();
    this.repaint();
    this.menuMain.setVisible(true);
  }
  // this method is used to refresh the frame and display the initial welcome to game Menu
  public void returnToMenu () {
    this.menuWest.setVisible(false);
    this.menuEast.setVisible(false);
    this.returnToMenuButton.setVisible(false);
    this.helpArea.setVisible(false);
    this.startGame.setVisible(false);
    this.enterRounds.setVisible(false);
    this.roundsPlay.setVisible(false);
    this.playGame.setVisible(true);
    this.helpButton.setVisible(true);
    this.quitGame.setVisible(true);
    this.logo.setVisible(true);
  }
  
  //this method used to update the frame  when rounds over
  public void nextRound() {
    this.wipeBoard();
    this.nextRound.setVisible(false);
    
    
  }
//This Method displays the Instructions for the user
  public void helpView()
  {
    this.playGame.setVisible(false);
    this.quitGame.setVisible(false);
    this.helpButton.setVisible(false);
    this.logo.setVisible(false);
    this.returnToMenuButton.setVisible(true);
    this.helpArea.setVisible(true);
    this.helpArea.setEditable(false);
    this.helpArea.setBackground(null);
    
  }
  //Helper method used to clear the frame of the menu panels
  public void clearMenu()
  {
    this.southPanel.setVisible(false);
    this.northPanel.setVisible(false);
    roundOver =false;
    this.revalidate();
    this.repaint();
    this.menu.removeAll();
    this.wipeBoard();
  }
  // Accessor methods used to access rounds, player names
  public JTextField getRound()
  {
    return this.roundsPlay;
  }
  public JTextField getPlayer1Name()
  {
    return this.player1Name;
  }
  public JTextField getPlayer2Name()
  {
    return this.player2Name;
  }
  public JTextField getRoundsRestart()
  {
    return this.roundsRestart;
  }
  //***********************************
  //Helper method used to clear the frame of the menu
  public void removeMenu()
  {
    this.newGamePanel.setVisible(false);
    this.centerPanelEnd.setVisible(false);
    this.northPanelEnd.setVisible(false);
  }
//Method used for user to start a new Game once the previous game has ended, This method resets the names entered & rounds
  // and prompts the user to enter in  new info.
  public void newGame()
  {
    this.removeAll();
    this.menu.removeAll();
    this.remove(boardMain);
    roundOver =false;
    this.revalidate();
    this.repaint();
    this.newGamePanel.setVisible(true);
    this.centerPanelEnd.setVisible(true);
    this.northPanelEnd.setVisible(true);
    this.player1Name.setText("");
    this.roundsPlay.setText("");
    this.player2Name.setText("");
    this.northPanelEnd.add(this.player1NameLabel);
    this.northPanelEnd.add(this.player1Name);
    this.northPanelEnd.add(this.enterRounds);
    this.northPanelEnd.add(this.roundsPlay);
    this.northPanelEnd.add(this.player2NameLabel);
    this.northPanelEnd.add(this.player2Name);
    this.centerPanelEnd.add(this.startGame);
    newGamePanel.setLayout(new BorderLayout());
    newGamePanel.add(this.northPanelEnd, BorderLayout.NORTH );
    newGamePanel.add( this.centerPanelEnd, BorderLayout.CENTER);
    this.add(newGamePanel);
  }
  //This method is responsible for displaying the connect4 game
  public void board()
  {
    this.setPreferredSize(new Dimension(100, 100));
    gameOverMain = new JPanel(); 
    GridLayout northLayout = new GridLayout(2,1);
    GridLayout topLayout = new GridLayout(1,3);
    this.boardNorth.setLayout(topLayout);
    this.player1Score = new JLabel( ""+ this.models.player1Name +" Score:"+this.models.getPlayer1Score());
    this.player2Score = new JLabel(this.models.player2Name+" Score: "+this.models.getPlayer2Score());
    this.currentRound = new JLabel("Round: "+ this.models.currentRound+ " out of "+this.models.roundsInput);
    this.boardNorth.add(this.player1Score);
    this.boardNorth.add(this.currentRound);
    this.boardNorth.add(this.player2Score);
    this.boardSouth.add(this.returnToMenuButton);
    this.returnToMenuButton.setVisible(false);
    for (int i = 0; i < boardCircles.length; i++) {
      for(int x=0; x<boardCircles[i].length; x++) {
        boardCircles[i][x] = new JLabel();
        boardCircles[i][x].setIcon(emptyCircle);
      }
    }
    this.boardSouth.add(this.nextRound);
    this.nextRound.setVisible(false);
    this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    for(int x=0; x<rowCircle.length; x++) {
      this.rowCircle[x].setLayout(new BoxLayout(this.rowCircle[x], BoxLayout.PAGE_AXIS));
    }
    for(int x=0; x<rowCircle.length; x++) {
      rowCircle[x].setBackground(blue);
    }
    for(int i=0; i<this.boardCircles.length; i++) {
      int rowCount =0;
      for(int x=this.boardCircles[i].length-1; x>=0; x--) {
        this.rowCircle[i].add(this.boardCircles[i][x]);
      }
    }
    for(int x=0; x<rowCircle.length; x++) {
      this.boardCenter.add(rowCircle[x]);
    }
    this.boardMain.setLayout(new BorderLayout());
    this.boardMain.add(this.boardNorth, BorderLayout.NORTH);
    this.boardMain.add(this.boardCenter, BorderLayout.CENTER);
    this.boardMain.add(this.boardSouth, BorderLayout.SOUTH);
    this.add(boardMain);
  }
  /** Takes the player won and their score and the rounds played, refreshes the JLabels based on this information
    * @param playerWon        String that repersents the player won
    * @param player1Score     player 1 score
    * @param player2Score     player 2 score
    * @param roundCount       Number of rounds played
    */
  public void wonRound(String playerWon, int player1Score, int player2Score, int roundCount) {  
    if (this.models.winCondtion()) {
      if (playerWon == "p1") {
        this.player1Score = new JLabel("" + player1Score);
      } else if (playerWon == "p2") {
        this.player2Score = new JLabel("" + player2Score);
      } 
      else if (!this.models.winCondtion()) {
        this.models.checkStaleMate();
      }
    }  
    else {
      this.currentRound = new JLabel("" + roundCount);
    }
    this.nextRound.setVisible(true);
  }
//This resets the GUI board
  public void wipeBoard()
  {
    this.boardMain.removeAll();
    this.boardNorth.removeAll();
    this.boardSouth.removeAll();
    this.boardCenter.removeAll();
    this.removeAll();
    this.revalidate();
    if(this.models.currentRound<this.models.roundsInput) {
      this.models.clearBoard();
    }
    roundOver =false;
    for(int i=0; i<rowCircle.length; i++)
    {
      this.rowCircle[i] = new JPanel();
    }
    this.board();
    this.mouseRegisterControllers();
  }
  //Method displays the initial game menu
  public void menu() {
    logo.setIcon (this.connect4Logo);
    this.quitGame.setPreferredSize(new Dimension(140,75));
    this.helpButton.setPreferredSize(new Dimension(140, 75));
    this.playGame.setPreferredSize(new Dimension(140, 75));
    this.startGame.setPreferredSize(new Dimension(140, 75));
    this.returnToMenuButton.setPreferredSize(new Dimension(140, 75));
    this.playGame.setFont(new Font("Copperplate Gothic Bold",Font.BOLD, 15));
    this.helpButton.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 13));
    this.quitGame.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 13));
    this.returnToMenuButton.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 13));
    this.startGame.setFont(new Font("Copperplate Gothic Bold",Font.BOLD, 15));
    this.helpArea.setFont(new Font("Copperplate Gothic Bold",Font.BOLD, 15));
    this.quitGame.setBackground(red);
    this.playGame.setBackground(green);
    this.startGame.setBackground(green);
    this.helpButton.setBackground(yellow);
    this.returnToMenuButton.setBackground(Color.ORANGE);
    // ****** mac stuff******/
    this.quitGame.setOpaque(true);
    this.playGame.setOpaque(true);
    this.startGame.setOpaque(true);
    this.helpButton.setOpaque(true);
    this.returnToMenuButton.setOpaque(true);
    this.quitGame.setBorderPainted(false);
    this.playGame.setBorderPainted(false);
    this.startGame.setBorderPainted(false);
    this.helpButton.setBorderPainted(false);
    this.returnToMenuButton.setBorderPainted(false);
    //**************************
    this.menuNorth.add(logo);
    logo.setVisible(true);
    this.menuNorth.add(this.enterRounds);
    this.menuNorth.add(this.roundsPlay);
    this.menuWest.add(this.player1NameLabel);
    this.menuEast.add(this.player2NameLabel);
    this.menuWest.add(this.player1Name);
    this.menuEast.add(this.player2Name);
    this.player1NameLabel.setVisible(false);
    this.player2NameLabel.setVisible(false);
    this.player1Name.setVisible(false);
    this.player2Name.setVisible(false);
    this.menuCenter.add(this.playGame);
    this.menuCenter.add(this.helpButton);
    this.menuCenter.add(this.startGame);
    this.menuCenter.add(this.quitGame);
    this.roundsPlay.setVisible(false);
    this.enterRounds.setVisible(false);
    helpArea.setVisible(false);
    this.returnToMenuButton.setVisible(false);
    this.menuNorth.add(helpArea);
    this.menuCenter.add(this.returnToMenuButton);
    this.startGame.setVisible(false);
    this.menuMain.setLayout(new BorderLayout());
    this.menuMain.add(this.menuCenter);
    this.menuMain.add(this.menuNorth);
    this.setLayout(new BorderLayout());
    this.menuMain.add(this.menuNorth, BorderLayout.NORTH);
    this.menuMain.add(this.menuCenter, BorderLayout.CENTER);
    this.menuMain.add(this.menuWest, BorderLayout.WEST);
    this.menuMain.add(this.menuEast, BorderLayout.EAST);
    this.menuMain.add(this.menuSouth, BorderLayout.SOUTH);
    this.add( this.menuMain);
  }
  // Method is used to register mouse Action listeners
  private void mouseRegisterControllers() {
    Connect4ControllerMouse controller = new Connect4ControllerMouse(this.models, rowCircle);
    for(int x=0; x<rowCircle.length; x++) {
      this.rowCircle[x].addMouseListener(controller);
    }
  } 
  /** This method checks if the round is over
    * @param CurrentPlayer represents the string of the current players
    *  player1Score player 1's score
    *  Player2Score player 2's score
    *  rounds       number of rounds played
    *  gameOver     boolean value representing if the game is over 
    *  playerTurn   Reperesnts the players turn 
    */
  public void roundOverG(String currentPlayer,int player1Score, int player2Score, int rounds, boolean gameOver, String playerTurn)
  {
    if(!gameOver)
    {
      this.wonRound(currentPlayer,player1Score,player2Score,rounds);
      roundOver = true;
      playerTurn="p1";
    }
    else
    {
      this.endGame();
    }
  }
  //This is the Menu shows an endGame Menu after a game is over (differs from initial menu)
  public void endGameMenu()
  {
    this.scoreBoard.setVisible(false);
    this.revalidate();
    this.removeAll();
    this.menu.removeAll();
    this.endGameCenter.removeAll();
    this.endGameWest.removeAll();
    this.revalidate();
    this.repaint();
    menu.setLayout(new BorderLayout());
    JLabel gameLogo = new JLabel();
    gameLogo.setIcon(this.connect4Logo);
    endGameCenter.add(gameLogo);
    restartGame.setPreferredSize(new Dimension(143,75));
    endGame.setPreferredSize(new Dimension(140, 75));
    viewScore.setPreferredSize(new Dimension(140, 75));
    newGame.setPreferredSize(new Dimension(140, 75));
    restartGame.setFont(new Font("Copperplate Gothic Bold",Font.BOLD, 12));
    endGame.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 13));
    newGame.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 13));
    viewScore.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 13));
    restartGame.setBackground(red);
    endGame.setBackground(green);
    viewScore.setBackground(green);
    newGame.setBackground(yellow);
    restartGame.setBorderPainted(false);
    endGame.setBorderPainted(false);
    viewScore.setBorderPainted(false);
    newGame.setBorderPainted(false);
    restartGame.setOpaque(true);
    endGame.setOpaque(true);
    viewScore.setOpaque(true);
    newGame.setOpaque(true);
    endGameWest.add(restartGame);
    endGameWest.add(endGame);
    endGameWest.add(viewScore);
    endGameWest.add(newGame);
    menu.add(endGameCenter,BorderLayout.CENTER);
    menu.add(endGameWest, BorderLayout.SOUTH);
    this.add(menu);
  }
  //This displays who won and the score once the game is over
  public void endGame()
  {
    JPanel endGameSouth= new JPanel();
    JPanel endGameCenter= new JPanel();
    JPanel gameOverPanel = new JPanel();
    this.remove(boardMain);
    this.revalidate();
    this.repaint();
    this.models.fileOutput();
    this.gameOverLogo.setIcon(gameOverLogoImage);
    gameOverPanel.add(gameOverLogo);
    this.gameOverLogo.setVisible(true);
    gameOverPanel.setBackground(new Color(152,251,152));
    endGameCenter.setBackground(new Color(152,251,152));
    endGameSouth.setBackground(new Color(152,251,152));
    this.endGameText =new JTextArea (this.models.finalGameWinner()+ "\n\nSCORES WITH THE MOST RECENT BOARD HAVE BEEN OUTPUTTED TO\nScoreoutput.txt, FLEX TO YOUR FRIENDS");
    this.endGameText.setBackground(null);
    this.endGameText.setEditable(false);
    this.endGameText.setFont(new Font("ShowCard Gothic",Font.BOLD, 20));
    endGameSouth.add(this.returnToGameMenuButton);
    endGameCenter.add(this.endGameText );
    gameOverMain.setLayout(new BorderLayout());
    gameOverMain.add(gameOverPanel, BorderLayout.NORTH);
    gameOverMain.add(endGameCenter, BorderLayout.CENTER);
    gameOverMain.add(endGameSouth,BorderLayout.SOUTH);
    this.add(gameOverMain);
  }
  /** Graphically Places the piece on the board  
    * @param point        Stores the point where to place the piece
    * @param player       Stores the player
    */
  public void placementPiece(int[] point, String player)  {
    if (player.equals("p1") && roundOver==false) {
      boardCircles[point[0]][point[1]].setIcon(yellowCircle);
    }
    else if (player.equals("p2") && roundOver==false) {
      boardCircles[point[0]][point[1]].setIcon(redCircle);
    }
  }
}