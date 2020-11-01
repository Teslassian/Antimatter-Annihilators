import java.awt.Font;
import java.io.*;
import sun.audio.*;
  
public class GameOver //add message saying that the collision caused a black hole
{
  public static void display()
  {
    Font font = new Font("Cooper Black", Font.BOLD, 100);
    StdDraw.clear(StdDraw.BLACK);
    StdDraw.picture(0, 0, "/"+System.getProperty("user.dir")+"/Graphics/blackhole.jpg", 2000, 2000);
    
    StdDraw.setPenColor(StdDraw.ORANGE);
    StdDraw.setFont(font);
    StdDraw.text(0, 600, "GAME OVER!");
    
    
    // inserted sound here
 AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM = null;
        

        

        try
        {
            InputStream test = new FileInputStream("/"+System.getProperty("user.dir")+"/Sounds/gameOver.wav");
            BGM = new AudioStream(test);
            //AudioPlayer.player.start(BGM);
           
            

        }
        catch(FileNotFoundException e){
            System.out.print(e.toString());
        }
        catch(IOException error)
        {
            System.out.print(error.toString());
        }
        MGP.start(BGM);
        
    StdDraw.pause(1000);
  }
  
}