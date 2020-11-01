import java.awt.event.KeyEvent;
import java.awt.Font;

public class Controls
{
  public static void display()
  {
    StdDraw.enableDoubleBuffering();
    Font font1 = new Font("Cooper Black", Font.BOLD, 50);
    Font font2 = new Font("Cooper Black", Font.BOLD, 38);
    Font font3 = new Font("Cooper Black", Font.BOLD, 20);
    
    StdDraw.clear(StdDraw.BLACK);
    StdDraw.picture(0, 0, "/"+System.getProperty("user.dir")+"/Graphics/earth.png", 2000, 2000);
    
    StdDraw.setPenColor(StdDraw.WHITE);
    StdDraw.setFont(font1);
    StdDraw.text(0, 700, "CONTROLS:");
    StdDraw.textLeft(-850, 450, "KEY:");
    StdDraw.textRight(850, 450, "ACTION:");
    
    StdDraw.setFont(font2);
    StdDraw.textLeft(-850, 300, "A");
    StdDraw.textRight(850, 300, "- Move Left");
    StdDraw.textLeft(-850, 200, "S");
    StdDraw.textRight(850, 200, "- Move Right");
    StdDraw.textLeft(-850, 100, "Left Arrow");
    StdDraw.textRight(850, 100, "- Rotate Left");
    StdDraw.textLeft(-850, 0,"Right Arrow");
    StdDraw.textRight(850, 0, "- Rotate Right");
    StdDraw.textLeft(-850, -100,"Spacebar");
    StdDraw.textRight(850, -100, "- Shoot");
    StdDraw.textLeft(-850, -200, "Enter");
    StdDraw.textRight(850, -200, "-Start Game");
    StdDraw.textLeft(-850, -300, "ESC");
    StdDraw.textRight(850, -300, "-Exit Game");
    
    StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
    StdDraw.filledRectangle(-750, 800, 200, 100);
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.rectangle(-750, 800, 200, 100);
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.setFont(font3);
    StdDraw.text(-750, 800, "Back (Q)");
    
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

