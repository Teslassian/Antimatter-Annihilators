//import java.io.*; //Input/Output
//import sun.audio.*; //Audio
public class Bunker extends Entity
{
  private static final int extent = 150;
  private boolean exploded = false;
  
  public Bunker(double x)
  {
    super(x, 110, 0, 0, true, 0, 0);
  }
  
  public void draw()
  {
    if ((x > -Annihilators.boundary + 1.5*extent) && (x < Annihilators.boundary - 1.5*extent))
    if (this.extant)
    {
      StdDraw.picture(x, y, "/"+System.getProperty("user.dir")+"/Graphics/bunker.png", extent, extent);
    }
    else
    {
      ex = x;
      ey = y;
      if (explosionExtent < extent*1.5)
      {
      StdDraw.picture(ex, ey, "/"+System.getProperty("user.dir")+"/Graphics/explosion.png", explosionExtent, explosionExtent);
    /* AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM = null;
      
        try
        {
            InputStream test = new FileInputStream("/"+System.getProperty("user.dir")+"/Sounds/enemyHit3.wav");
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
        */
      }
      else
      {
        exploded = true;
      }
    }
  }
  
  public boolean extantAccessor() {
    return this.extant;
  }
  
  public double xAccessor() {
    return this.x;
  }
  
  public double yAccessor() {
    return this.y;
  }
  
  public void extantMutator(boolean extant) {
    this.extant = extant;
  }
  
  public int explosionExtentAccessor() {
    return this.explosionExtent;
  }
  
  public void explosionExtentMutator(int explosionExtent) {
    this.explosionExtent = explosionExtent;
  }
  public boolean explodedAccessor() {
    return this.exploded;
  }
}