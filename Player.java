import java.io.*; //Input/Output
import sun.audio.*; //Audio

public final class Player extends Entity
{
  //class variables
  private final static double omega = 0.75*progressionMultiplier;
  public final static int extent = 400;
  
  //instance variables
  private double theta = 0;
  private boolean exploded = false;
  private int lives = 0;
  
  //constructors
  public Player(double velocity)
  {
    super(0, -600, 0, 0, true, 0, velocity*progressionMultiplier);
  }
  
  //instance methods
  
  public void reset()
  {
    
    
  }
  
  public void draw()    
  {
    if (lives == 0)
    {
      this.extant = false;
      // inserted sound here 
      AudioPlayer MGP = AudioPlayer.player;
      AudioStream BGM = null;
      
      try
      {
        InputStream test = new FileInputStream("/"+System.getProperty("user.dir")+"/Sounds/playerKilled.wav");
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
    
    if (this.extant)
    {
      StdDraw.picture(x, y, "/"+System.getProperty("user.dir")+"/Graphics/spaceship.png", extent, extent, theta); //needs to draw an angle, therefore it overrides the parent class
    }
    else
    {
      if (explosionExtent < extent*2)
      {
        StdDraw.picture(ex, ey, "/"+System.getProperty("user.dir")+"/Graphics/explosion.png", explosionExtent, explosionExtent);
      }
      else
      {
        exploded = true;
      }
    }
  }
  
  public void angleLeft()
  {
    if (theta < 90){
      theta += omega;
    }
  }
  
  public void angleRight()
  {
    if (theta > -90){
      theta -= omega;
    }
  }
  
  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////A&M
  
  public double xAccessor() {          ///////////////check whether all of these are being used, try to make them less, rather call functions with arguments
    return this.x;
  }
  
  public void xMutator(double x) {/////////////////////////make this neat and sort this out
    this.x = x;
  }
  
  public double yAccessor() {
    return this.y;
  }
  
  public void yMutator(double y) {
    this.y = y;
  }
  
  public double thetaAccessor() {
    return this.theta;
  }
  
  public boolean extantAccessor() {
    return this.extant;
  }
  
  public int explosionExtentAccessor() {
    return this.explosionExtent;
  }
  
  public void thetaMutator(int theta) {
    this.theta = theta;
  }
  
  public void extantMutator(boolean extant) {
    this.extant = extant;
  }
  
  public boolean explodedAccessor() {
    return this.exploded;
  }
  
  public void livesMutator(int lives) {
    this.lives = lives;
  }
  
  public int livesAccessor() {
    return this.lives;
  }
  
  public void explosionExtentMutator(int explosionExtent) {
    this.explosionExtent = explosionExtent;
  }
  public void exMutator(double ex) {
    this.ex = ex;
  }
  public void eyMutator(double ey) {
    this.ey = ey;
  }
  
  public void velocityMutator(int velocity) {
    this.velocity = velocity;
  }
  public double velocityAccessor() {
    return this.velocity;
  }
  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////A&M
  
}