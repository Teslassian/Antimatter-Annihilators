import java.io.*; //Input/Output
import sun.audio.*; //Audio
public final class Minion extends Entity
  
{
  //class variables
  public final static int extent = 200;
  
  //instance variables
  private boolean orientation = true; //true => R; false => L
  private int delta = 0;
  private boolean exploded = false;
  private int hitpoints = 100;
  private boolean hit = false;
  
  //constructors
  public Minion(int x, int velocity)
  {
    super(x, Annihilators.boundary-300, 0, 0, true, 0, velocity*progressionMultiplier);
  }
  
  //instance methods
  
  
  /**Javadocs
    * 
    * 
    * 
    * 
    *
    */  
  public void draw()    
  {
    if (this.hit)
    {
      hitpoints -= 40;
      this.hit = false;
      if (hitpoints < 0)
      {
        HomeScreen.morts++;
        this.extant = false;
        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM = null;
      
        try
        {
            InputStream test = new FileInputStream("/"+System.getProperty("user.dir")+"/Sounds/enemyKilled.wav");
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
        
      }
    }
    
    if (this.extant) 
    {
      StdDraw.picture(this.x, this.y, "/"+System.getProperty("user.dir")+"/Graphics/minion.png", extent, extent);
      StdDraw.setPenColor(StdDraw.GREEN);
      StdDraw.filledRectangle(this.x, this.y + 0.7*extent, hitpoints*extent/250, 5);
    }
    else
    {
      if (explosionExtent < extent*1.5)
      {
        StdDraw.picture(ex, ey, "/"+System.getProperty("user.dir")+"/Graphics/explosion.png", explosionExtent, explosionExtent);
      }
      else
      {
        exploded = true;
      }
    }
  }
  
  public void moveRight()
  {
    if (orientation == true)
    {
      if ((this.x > Annihilators.boundary - 0.5*extent) || (this.y < -600 + 0.7*Player.extent))
      {
        this.y -= velocity;
        delta += velocity;
        if (delta > 400)
        {
          orientation = false;
          delta = 0;
        }
      }
      else
      {
        this.x += velocity;
      }
      
    }
  }
  
  public void moveLeft()
  {
    if (orientation == false)
    {
      if ((this.x < -Annihilators.boundary + 0.5*extent) || (this.y < -600 + 0.7*Player.extent))
      {
        this.y -= velocity;
        delta += velocity;
        if (delta > 400)
        {
          orientation = true;
          delta = 0;
        }
      }
      else
      {
        this.x -= velocity;
      }
    }
  }
  
  
  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////A&M
  public double xAccessor() {
    return this.x;
  }
  
  public double yAccessor() {
    return this.y;
  }
  
  public void xMutator(double x) {
    this.x = x;
  }
  public void exMutator(double ex) {
    this.ex = ex;
  }
  public void eyMutator(double ey) {
    this.ey = ey;
  }
  
  public void yMutator(double y) {
    this.y = y;
  }
  
  public boolean explodedAccessor() {
    return this.exploded;
  }
  
  public boolean extantAccessor() {
    return this.extant;
  }
  public int explosionExtentAccessor() {
    return this.explosionExtent;
  }
  
  public void explosionExtentMutator(int explosionExtent) {
    this.explosionExtent = explosionExtent;
  }
  
  public void extantMutator(boolean extant) {
    this.extant = extant;
  }
  
  public void hitMutator(boolean hit) {
    this.hit = hit;
  }
  
  public void explodedMutator(boolean exploded) {
    this.exploded = exploded;
  }
  
  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////A&M
  
}
