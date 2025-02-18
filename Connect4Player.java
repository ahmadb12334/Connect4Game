/**  Connect4 Player 
*    Provides player data to the scoreBoard in connect 4 Game
*    Date Created: 1/21/2022
*    @author Ahmad Bhutta
*/

public class Connect4Player
{
  //Instance Variables 
  private int playerScore; //Player score 
  private String playerName; //Player Name
  private String opponent; //Opponent name 
  
  
  public Connect4Player(String player,int score, String opponentName){
    this.playerScore = score;
    this.playerName = player;
    this.opponent=opponentName;
  }
  
  
   /** Accesor methods
    */ 
  public String getPlayerName() {
    return this.playerName;
  }
  public String getOponent() {
    return this.opponent;
  }
  
  public  int getPlayerScore(){
    return this.playerScore;
  }
  public  void updateScore(int currentScore){
    this.playerScore +=currentScore;
  }
  
}