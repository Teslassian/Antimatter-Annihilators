import java.awt.event.KeyEvent;
import java.awt.Font;
import java.util.ArrayList;

public class Leaderboard
{
  public static void display(ArrayList<Integer> highScore)
  {
    StdDraw.enableDoubleBuffering();
    Font font1 = new Font("Cooper Black", Font.BOLD, 50);
    Font font3 = new Font("Cooper Black", Font.BOLD, 20);
    
    StdDraw.clear(StdDraw.BLACK);
    StdDraw.picture(0, 0, "/"+System.getProperty("user.dir")+"/Graphics/earth.png", 2000, 2000);
    
    StdDraw.setPenColor(StdDraw.WHITE);
    StdDraw.setFont(font1);
    StdDraw.text(0, 700, "Highscore:");
    for (int i=0;i<highScore.size();i++)
    {
      if (highScore.size() < 10) StdDraw.text(0, (500-i*100), Integer.toString(highScore.get(i)));
    }
    
    StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
    StdDraw.filledRectangle(-750, 800, 200, 100);
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.rectangle(-750, 800, 200, 100);
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.setFont(font3);
    StdDraw.text(-750, 800, "BACK (Q)");
    
    StdDraw.show();
    StdDraw.disableDoubleBuffering();
    
    while (true) 
    {
      if (backButtonPressed())
      {
        HomeScreen.display();
      } 
    }
  }
  
  public static boolean backButtonPressed(){
    if((StdDraw.isKeyPressed(KeyEvent.VK_Q)) || (StdDraw.isMousePressed() && StdDraw.mouseX()>-950 && StdDraw.mouseX()<-550 && StdDraw.mouseY()>700 && StdDraw.mouseY()<900)){
      return true;
    }
    return false;
  }
  
}

