import java.awt.*;
import java.io.*; //Input/Output
import sun.audio.*; //Audio

public class Annihilators 
{
  static public final int boundary = 1000;
  static public final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
  static public final int height = (int)(size.getHeight()*0.9);
  
  public static void main(String[] args)
  {
    // inserted sound here
        AudioPlayer MGP = AudioPlayer.player; // creates audio player functionality from imported audio package 
        AudioStream BGM;// allows music files to be read in a format applicable for computers
        AudioData MD;// allows music files to be stored in a format applicable for computers

        ContinuousAudioDataStream loop = null;// allows for a continous sound loop

        try
        {
            InputStream test = new FileInputStream( "/"+System.getProperty("user.dir")+"/Sounds/backgroundMusic.wav"); //stores the retrieved file to be used later 
            BGM = new AudioStream(test);
            //AudioPlayer.player.start(BGM);
            MD = BGM.getData();// needed when using a music loop
            loop = new ContinuousAudioDataStream(MD);

        }
        catch(FileNotFoundException e){
            System.out.print(e.toString());
        }
        catch(IOException error)
        {
            System.out.print(error.toString());
        }
        MGP.start(loop);// starts music loop
    displayCanvas();
    while (true) HomeScreen.display(); ///sometimes screen is white/gray/black upon launch
  }
  
  private static void displayCanvas(){
    StdDraw.setCanvasSize(height, height);
    StdDraw.setXscale(-boundary, boundary);
    StdDraw.setYscale(-boundary, boundary);
  }
  
}
