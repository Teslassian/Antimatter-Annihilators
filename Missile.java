public class Missile extends Entity {
  
  //class variables
  private final static int length = 100;
  public static double reloadInterval = 150/progressionMultiplier;
  public static double reloadCounter = 0;
  public static double fireInterval = 30/progressionMultiplier;
  public static double fireCounter = 0;
  public static double enemyFireInterval = 300/progressionMultiplier;
  public static double enemyFireCounter =300;
  
  //instance variables
  private double theta = 0;
  private double delta = 0;
  
  //constructors
  public Missile()
  {
    super(0, 0, 0, 0, true, 0, 20*progressionMultiplier);
  }
  
  //instance methods
  public void draw(boolean enemy)
  {
    if (extant)
    {
      if (enemy)
      {
        delta -= velocity/3;
        this.x = this.ex;
        this.y = this.ey + delta;
      StdDraw.picture(x, y, "/"+System.getProperty("user.dir")+"/Graphics/electron.png", length, length, theta);
      }
      else 
      {
        delta += velocity;
        this.x = this.ex + delta*Math.cos((theta+90)/180*Math.PI);
        this.y = this.ey + delta*Math.sin((theta+90)/180*Math.PI);
      StdDraw.picture(x, y, "/"+System.getProperty("user.dir")+"/Graphics/proton.png", length, length, theta);
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
  
  public void exMutator(double ex) {
    this.ex = ex;
  }
  
  public void eyMutator(double ey) {
    this.ey = ey;
  }
  
  public void thetaMutator(double theta) {
    this.theta = theta;
  }
  
  public double deltaAccessor() {
    return this.delta;
  }
  public boolean extantAccessor() {
    return this.extant;
  }
  public void extantMutator(boolean extant) {
    this.extant = extant;
  }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////A&M
  
}