import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.Font;
import java.util.Collections;

public class HomeScreen
{
  private static ArrayList<GameRunner> gameRunner = new ArrayList<GameRunner>();
  private static ArrayList<Integer> highScore = new ArrayList<Integer>();
  public static int morts = 0;   ///make private
  private static int level = 1;  //max is 10
  private static int gameCase = 1;  //0: Run the game; 1: You won, next level; 2: You lost, display game over; 3: Pressed Q, quit
  
  public static void display()
  {
    StdDraw.enableDoubleBuffering();
    Font fontCB = new Font("Cooper Black", Font.BOLD, 15);
    StdDraw.setFont(fontCB);
    StdDraw.clear(StdDraw.DARK_GRAY);
    StdDraw.picture(0, 0, "/"+System.getProperty("user.dir")+"/Graphics/homescreenFinal.png", 2000, 2000);
    
    StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
    StdDraw.filledRectangle(-500, -500, 300, 100);
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.rectangle(-500, -500, 300, 100);
    StdDraw.text(-500, -475, "SINGLE PLAYER");
    StdDraw.text(-500, -525, "(ENTER)");
    
    /*
     StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
     StdDraw.filledRectangle(0, -500, 200, 100);
     StdDraw.setPenColor(StdDraw.BLACK);
     StdDraw.rectangle(0, -500, 200, 100);
     StdDraw.text(0, -475, "START MULTI-");
     StdDraw.text(0, -525, "PLAYER (CTRL)");
     */
    
    StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
    StdDraw.filledRectangle(+500, -500, 300, 100);
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.rectangle(+500, -500, 300, 100);
    StdDraw.text(500, -500, "QUIT (ESC)");
    
    /*
     StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
     StdDraw.filledRectangle(-500, -800, 200, 100);
     StdDraw.setPenColor(StdDraw.BLACK);
     StdDraw.rectangle(-500, -800, 200, 100);
     StdDraw.text(-500, -800, "MISSION STATEMENT");  
     */
    
    StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
    StdDraw.filledRectangle(500, -800, 300, 100);
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.rectangle(500, -800, 300, 100);
    StdDraw.text(500, -800, "HIGHSCORE (H)");
    
    StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
    StdDraw.filledRectangle(-500, -800, 300, 100);
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.rectangle(-500, -800, 300, 100);
    StdDraw.text(-500, -800, "CONTROLS (SPACE)");
    
    StdDraw.show();
    StdDraw.disableDoubleBuffering();
    
    
    while (gameCase != 3)
    {
      if (startButtonPressed())
      {
        while (gameCase != 3)
        {
          gameCase = 1;
          while ((level <= 10) && (gameCase == 1))
          {
            GameRunner gameInstance = new GameRunner();
            gameRunner.add(gameInstance);
            gameCase = gameRunner.get(0).startgame(level);
            gameRunner.remove(0);
            level++;
          }
          if (gameCase == 2) GameOver.display();
          level = 1;
          highScore.add(morts);
          Collections.sort(highScore);
          Collections.reverse(highScore);
            morts = 0;
        }
      }
      
      if (endButtonPressed())
      {
        System.exit(0);
      }
      
      if (highButtonPressed())
      {
        Leaderboard.display(highScore);
      }
      
      if (controlButtonPressed())
      {
        Controls.display();
      }
    }
    gameCase = 1;
  }
  
  
  public static boolean startButtonPressed(){
    if ((StdDraw.isKeyPressed(KeyEvent.VK_ENTER)) || (StdDraw.isMousePressed() && StdDraw.mouseX()>-700 && StdDraw.mouseX()<-300 && StdDraw.mouseY()>-600 && StdDraw.mouseY()<-400)){
      return true;
    }
    return false;      
  }
  
  
  public static boolean endButtonPressed(){
    if ((StdDraw.isKeyPressed(KeyEvent.VK_ESCAPE)) || (StdDraw.isMousePressed() && StdDraw.mouseX()>300 && StdDraw.mouseX()<700 && StdDraw.mouseY()>-600 && StdDraw.mouseY()<-400)){
      return true;
    }
    return false;
  }
  
  public static boolean highButtonPressed(){
    if ((StdDraw.isKeyPressed(KeyEvent.VK_H)) || (StdDraw.isMousePressed() && StdDraw.mouseX()>300 && StdDraw.mouseX()<700 && StdDraw.mouseY()>-900 && StdDraw.mouseY()<-700)){
      return true;
    }
    return false;
  }
  
  public static boolean controlButtonPressed(){
    if((StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) || (StdDraw.isMousePressed() && StdDraw.mouseX()>-700 && StdDraw.mouseX()<-300 && StdDraw.mouseY()>-900 && StdDraw.mouseY()<-700)){
      return true;
    }
    return false;
  }
}