/** Connect 4 Model
  * Connect 4 Class for the process of the game
  * @since Jan 12, 2023
  * @author Ahmad Bhutta
  */
import java.io.*;
import javax.swing.*;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.ArrayList;

public class Connect4Model extends Object {
  private Connect4GUI mGui; // this is a reference to the GUI class which will be used to update the game board display
  public int[][] board = new int [7][6]; // this 2D array represents the game board, with 7 columns and 6 rows
  public String playerTurn = "p1"; // this string represents the current player's turn, initially set to "p1"
  public int currentRound; // this variable keeps track of the current round
  public int roundsInput; // this variable holds the total number of rounds the players want to play
  public int player1Score; // this variable keeps track of player 1's score
  public int player2Score; // this variable keeps track of player 2's score
  public String player1Name="Player 1"; // this variable holds the name of player 1, initially set to "Player 1"
  public String player2Name; // this variable holds the name of player 2
  public boolean gameOver; // this boolean indicates whether the game is over or not
  private String finalWinner; //String Variable for the winner 
  private ArrayList<Connect4Player> players = new ArrayList<Connect4Player>(); // 100 is the max amount of players that can play the game
  
  private File file; //File object
  private PrintWriter writer; //Print writer, used to output to a file 
  private int gameCounter ; //Game counter int
  
  
  
  /* Model of the game sets the roundInput, gameCounter to 0
   * Sets currentRound to 1
   */
  public Connect4Model()
  {
    super(); // calling the super class's constructor
    this.roundsInput=0; // initially setting the total number of rounds to 0
    this.gameCounter=0;
    this.currentRound=1; // setting the current round to 1
  
    
    this.gameOver = false; // initially setting gameOver to false
  }
  
  
  /** Method for checking a StaleMate */
  public boolean checkStaleMate() {
    
    if(!this.winCondtion()) {
      for(int i = 0; i < 7; i++) {
        for(int j = 0; j < 6; j++) {
          if(board[i][j] == 0) {
            return true;
          }
        }
      }
      return true;
    }     
    return false;
  }
  
  /*Displays the Final winner on the End game Screen 
   */
  public String finalGameWinner() {
    
    if(this.getPlayer1Score()>this.getPlayer2Score()) {
      finalWinner = player1Name + " HOLY SMOKES DUDE," + player2Name+ " YOU GOT DESTROYED " + " \n Score: " +this.getPlayer1Score()+ " - " +this.getPlayer2Score();
      
      
    }
    
    else if (this.getPlayer2Score()>this.getPlayer1Score()) {
      finalWinner = player2Name + " IS ACTUALLY TOO GOOD,  " + player1Name + " GET GOOD!" +" \n Score: " +this.getPlayer2Score()+ " - " + this.getPlayer1Score(); 
      
      
    }
    
    else if (this.getPlayer1Score()==this.getPlayer2Score())  {
      finalWinner = "You guys are both bad, it was a Draw"+" \n Score: " +this.getPlayer1Score()+ " - " + this.getPlayer2Score(); 
      
      
    }
    return finalWinner;
  }
  
  
  /** Method for setting the GUI*/
  public void setGUI(Connect4GUI refMouse) {
    this.mGui = refMouse;
  }
  
  /** Accessor Method that gets amount of Rounds user wants to play */
  public int getRounds() {
    return this.roundsInput; 
    
  }
  
  /** Accessor Method that gets player 1 Name */
  public String getPlayer1Name() {
    
    
    return this.player1Name;
  }
  
  /**Accessor Method that gets player 2 Name */
  public String getPlayer2Name() {
    
    return this.player2Name; 
    
  }
  
  /** Accessor Method that gets player 1 Score */
  public int getPlayer1Score() {
    
    return this.player1Score;
    
  }
  
  /**Accessor Method that gets player 2 Score */
  public int getPlayer2Score() {
    
    return this.player2Score;
    
  }
  
  /*Resets everything when new Game is ran*/
  public void newGame()
  {
    this.roundsInput=0; // initially setting the total number of rounds to 0
    this.gameCounter=0;
    this.player1Score=0;
    this.player2Score=0;
    this.currentRound=1;
    this.gameOver=false;
    this.clearBoard();
  }
  
  
  /** Method that Sets the rounds 
    *@param rounds - Amount of rounds */
  public void setRounds(int rounds) {
    this.roundsInput=rounds;    
  }
  
  /** Method that Sets player 1 Name 
    *@param play1Name - player 1 Name */
  public void setPlayer1Name(String play1Name) {
    this.player1Name = play1Name; 
    
    
  }
  /** Method that Sets player 1 Name 
    *@param play2Name - player 2 Name */
  public void setPlayer2Name(String play2Name) {
    this.player2Name = play2Name; 
    
  }
  
  
  
  /** Method that sets Icons to emptyCircle for all JLabels
    *@param labels - 2D array of JLabels */
  public void circle(JLabel[] labels) {
    
    labels = new JLabel[6];
    for (int i = 0; i < labels.length; i++) {
      labels[i] = new JLabel();
      labels[i].setIcon(new ImageIcon("emptyCircle.png"));
    }   
  }
  
  /** Method that Clears the board array*/
  public void clearBoard() {
    for(int x=0; x<board.length; x++) {
      for(int y=0; y<board[x].length; y++) { 
        this.board[x][y]=0;
      }
    }
  }
  /*Sorting all players using selection sort from Highest score to lowest score */
  public ArrayList<Connect4Player> scoreBoard()
  {
    for (int i=0; i<this.players.size(); i++)
    {
      Connect4Player player = this.players.get(i);
      for(int x=i; x<this.players.size(); x++) {
        if (player.getPlayerScore() < this.players.get(x).getPlayerScore()) {
          Connect4Player swapping = this.players.get(x);
          this.players.remove(x);
          this.players.add(i, swapping);
        }
      }
    }
    return this.players;
  }
  
  /*Method that writes to file the game information*/
  public void fileOutput() {
    String boardString = "";
    try {
      FileWriter writer = new FileWriter("ScoreOutput.txt", true);
      writer.write("*************************NEW GAME RUN***********************************\n");
      writer.write("LAST ROUND:\n");
      writer.write("GAME: "+this.gameCounter);
      writer.write("\n\n");
      writer.write(player1Name +": " +this.player1Score+"   ");
      writer.write(player2Name +": " +this.player2Score );
      System.out.println("Saved Score");
      
      writer.write("\n");
      for(int col = 5; col >= 0; col--) {
        for(int row = 0; row < 7; row++){
          boardString += new Integer(board[row][col]).toString() + "  ";
          
        }
        writer.write(boardString + "\n");
        boardString = "";
      }
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  /*Checks which Button is pressed, runs the approriate method
   * @param String action - the e.getActioncommand when its pressed*/
  public void buttonPressed(String action) {
    String playerChoice = "";
    playerChoice =  action;
    
    System.out.println(playerChoice);
    if(playerChoice.equalsIgnoreCase("Play Game"))
    {
      mGui.playGameRounds();
    }
    if (playerChoice.equalsIgnoreCase("Go Back"))
    {
      mGui.endGameMenu();
    }
    if(playerChoice.equalsIgnoreCase("Help")) 
    {
      mGui.helpView(); 
    }
    if(playerChoice.equalsIgnoreCase(" <- Return"))
    {
      mGui.returnToMenu(); 
    }
    if(playerChoice.equalsIgnoreCase("Quit")) {
      System.exit(0);
    }   
    if(playerChoice.equalsIgnoreCase("Start")) {
      try {
        
        this.roundsInput = Integer.parseInt(this.mGui.getRound().getText());
        this.setRounds(this.roundsInput);
        if(this.mGui.getPlayer1Name().getText().equals("")) {
          this.setPlayer1Name("Player 1");
        }
        else {
          this.player1Name = this.mGui.getPlayer1Name().getText();
          this.setPlayer1Name(this.player1Name);
        }
        if(this.mGui.getPlayer2Name().getText().equals("")) {
          this.setPlayer2Name("Player 2");  
        }
        else { 
          this.player2Name = this.mGui.getPlayer2Name().getText();
          this.setPlayer2Name(this.player2Name);
        }
        this.mGui.removeMenu();
        mGui.playGame();
      } catch (NumberFormatException ex)
      {
        this.mGui.getRound().selectAll();
        this.mGui.getRound().setText("Invalid");
      }
    }
    
    if(playerChoice.equalsIgnoreCase("Next Round")) {
      mGui.nextRound(); 
    }
    if(playerChoice.equalsIgnoreCase("Return to Menu")){
      mGui.endGameMenu(); 
    }
    if(playerChoice.equalsIgnoreCase("End Game"))
    {
      System.exit(0);
    }
    if(playerChoice.equalsIgnoreCase("Restart Game"))
    {
      mGui.restartRounds();
    }
    if(playerChoice.equalsIgnoreCase("Start Game"))
    {
      try {
        this.roundsInput= Integer.parseInt(this.mGui.getRoundsRestart().getText());
        this.setRounds(this.roundsInput);
        this.restartGame();
        mGui.clearMenu();
      }
      catch (NumberFormatException ex)
      {
        this.mGui.getRoundsRestart().selectAll();
        this.mGui.getRoundsRestart().setText("Invalid");
      }
    }
    if(playerChoice.equalsIgnoreCase("Score Board"))
    {
      mGui.scoreBoard(this.scoreBoard());
    }
    if(playerChoice.equalsIgnoreCase("New Game"))
    {
      this.restartGame();
      mGui.newGame();
    }
  }
  
  /** Method processes the clicks 
    *@param rowPressed - determines which row was clicked */
  public void mouseProcess (int rowPressed)
  {
    int y = updateBoard(rowPressed, this.playerTurn);
    int[] point = {rowPressed, y};
    mGui.placementPiece(point, this.playerTurn);
    if (y!=-1 && !this.winCondtion())
    {
      //Implments which player is taking their turn 
      if (this.playerTurn.equals("p1"))
      {
        this.playerTurn="p2";
      }
      else
      {
        this.playerTurn="p1";
      }
    }
    else
    {
      this.roundOver();
      mGui.roundOverG(this.playerTurn,this.player1Score, this.player2Score, this.currentRound, this.gameOver, this.playerTurn);
    }
  }
  /** Method that Updates the board depending on which player's turn
    *@param row- Updates the row clicked
    @param player- Which player clicked it*/ 
    public int updateBoard (int row, String player) { //removed , boolean boardFull from parameters
      int boardFull=-1;
      for (int i = 0; i < this.board[row].length; i++) {
        if (this.board[row][i] == 0) {
          if (player.equals("p1")) {
            this.board[row][i] = 1;
          }
          if (player.equals("p2")) {
            this.board[row][i] = 2;
          }
          return i;
        }
      }
      return boardFull;
    }
    /** Method that checks when the round is over*/
    public void roundOver()
    {
      if (this.winCondtion()) {
        if (this.playerTurn == "p1") {
          this.player1Score++;
        } else if (this.playerTurn == "p2") {
          this.player2Score++;
        }
      }
      if (this.currentRound < this.roundsInput) {
        for (int x = 0; x < board.length; x++) {
          for (int col = 0; col < board[x].length; col++) {
            board[x][col] = 0;
          }
        }
        this.currentRound++;
      }
      else if(this.currentRound == this.roundsInput) 
      {
        boolean playerFound = false;
        this.gameCounter++;
        for (int i=0; i<this.players.size(); i++)
        {
          if(this.players.get(i).getPlayerName().equals(this.player1Name))
          {
            this.players.get(i).updateScore(this.player1Score);
            playerFound = true;
          }
          if (this.players.get(i).getPlayerName().equals((this.player2Name)))
          {
            this.players.get(i).updateScore(this.player2Score);
            playerFound = true;
          }
        }
        if(!playerFound) {
          Connect4Player p1 = new Connect4Player(this.player1Name, this.player1Score, this.player2Name);
          Connect4Player p2 = new Connect4Player(this.player2Name, this.player2Score, this.player1Name);
          this.players.add(p1);
          this.players.add(p2);
        }
        this.gameOver=true;
      }
    }
    /*Restarts the game, making all the values back to the default 0 */
    public void restartGame()
    {
      this.player1Score=0;
      this.player2Score=0;
      this.currentRound=1;
      this.gameOver=false;
      for (int x = 0; x < board.length; x++) {
        for (int col = 0; col < board[x].length; col++) {
          board[x][col] = 0;
        }
      } 
    }
    /** Method that checks for wins on the board*/
    public boolean winCondtion() {
      //Horizontal Win Condtions
      for (int i = 0; i < 6; i++) {
        if (board[0][i] != 0 && board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == board[3][i]) {
          return true;
        }
        if (board[1][i] != 0 && board[1][i] == board[2][i] && board[1][i] == board[3][i] && board[1][i] == board[4][i]) {
          return true;
        }
        if (board[2][i] != 0 && board[2][i] == board[3][i] && board[2][i] == board[4][i] && board[2][i] == board[5][i]) {
          return true;
        }
        if (board[3][i] != 0 && board[3][i] == board[4][i] && board[3][i] == board[5][i] && board[3][i] == board[6][i]) {
          return true;
        }
      }
      // Check for vertical wins
      for (int j = 0; j < 7; j++) {
        if (board[j][0] != 0 && board[j][0] == board[j][1] && board[j][0] == board[j][2] && board[j][0] == board[j][3]) {
          return true;
        }
        if (board[j][1] != 0 && board[j][1] == board[j][2] && board[j][1] == board[j][3] && board[j][1] == board[j][4]) {
          return true;
        }
        if (board[j][2] != 0 && board[j][2] == board[j][3] && board[j][2] == board[j][4] && board[j][2] == board[j][5]) {
          return true;
        }
      }
      // Check for diagonal wins (top-left to bottom-right)
      if (board[0][0] != 0 && board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == board[3][3]) {
        return true;
      }
      if (board[1][0] != 0 && board[1][0] == board[2][1] && board[1][0] == board[3][2] && board[1][0] == board[4][3]) {
        return true;
      }
      if (board[2][0] != 0 && board[2][0] == board[3][1] && board[2][0] == board[4][2] && board[2][0] == board[5][3]) {
        return true;
      }
      if (board[3][0] != 0 && board[3][0] == board[4][1] && board[3][0] == board[5][2] && board[3][0] == board[6][3]) {
        return true;
      }
      if (board[0][1] != 0 && board[0][1] == board[1][2] && board[0][1] == board[2][3] && board[0][1] == board[3][4]) {
        return true;
      }
      if (board[1][1] != 0 && board[1][1] == board[2][2] && board[1][1] == board[3][3] && board[1][1] == board[4][4]) {
        return true;
      }
      if (board[2][1] != 0 && board[2][1] == board[3][2] && board[2][1] == board[4][3] && board[2][1] == board[5][4]) {
        return true;
      }
      if (board[3][1] != 0 && board[3][1] == board[4][2] && board[3][1] == board[5][3] && board[3][1] == board[6][4]) {
        return true;
      }
      if (board[0][2] != 0 && board[0][2] == board[1][3] && board[0][2] == board[2][4] && board[0][2] == board[3][5]) {
        return true;
      }
      if (board[1][2] != 0 && board[1][2] == board[2][3] && board[1][2] == board[3][4] && board[1][2] == board[4][5]) {
        return true;
      }
      if (board[2][2] != 0 && board[2][2] == board[3][3] && board[2][2] == board[4][4] && board[2][2] == board[5][5]) {
        return true;
      }
      // Check for diagonal wins (bottom-left to top-right)
      if (board[6][0] != 0 && board[6][0] == board[5][1] && board[6][0] == board[4][2] && board[6][0] == board[3][3]) {
        return true;
      }
      if (board[6][1] != 0 && board[6][1] == board[5][2] && board[6][1] == board[4][3] && board[6][1] == board[3][4]) {
        return true;
      }
      if (board[6][2] != 0 && board[6][2] == board[5][3] && board[6][2] == board[4][4] && board[6][2] == board[3][5]) {
        return true;
      }
      if (board[5][0] != 0 && board[5][0] == board[4][1] && board[5][0] == board[3][2] && board[5][0] == board[2][3]) {
        return true;
      }
      if (board[5][1] != 0 && board[5][1] == board[4][2] && board[5][1] == board[3][3] && board[5][1] == board[2][4]) {
        return true;
      }
      if (board[5][2] != 0 && board[5][2] == board[4][3] && board[5][2] == board[3][4] && board[5][2] == board[2][5]) {
        return true;
      }
      if (board[4][0] != 0 && board[4][0] == board[3][1] && board[4][0] == board[2][2] && board[4][0] == board[1][3]) {
        return true;
      }
      if (board[4][1] != 0 && board[4][1] == board[3][2] && board[4][1] == board[2][3] && board[4][1] == board[1][4]) {
        return true;
      }
      if (board[4][2] != 0 && board[4][2] == board[3][3] && board[4][2] == board[2][4] && board[4][2] == board[1][5]) {
        return true;
      }
      if (board[3][0] != 0 && board[3][0] == board[2][1] && board[3][0] == board[1][2] && board[3][0] == board[0][3]) {
        return true;
      }
      if (board[3][1] != 0 && board[3][1] == board[2][2] && board[3][1] == board[1][3] && board[3][1] == board[0][4]) {
        return true;
      }
      if (board[3][2] != 0 && board[3][2] == board[2][3] && board[3][2] == board[1][4] && board[3][2] == board[0][5]) {
        return true;
      }
      // If no win conditions are met, return false
      return false;
    }
}