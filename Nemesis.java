public final class Nemesis extends Entity
  
{
  //class variables
  public final static int extent = 400;
  
  //instance variables
  private boolean orientation = true; //true => R; false => L
  private boolean exploded = false;
  private int hitpoints = 300;
  private boolean hit = false;
  private double velocityFactor = 5;
  
  //constructors
  public Nemesis(int velocity)
  {
    super(-1200, Annihilators.boundary-300, 0, 0, true, 0, velocity*progressionMultiplier);
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
      }
    }
    
    if (this.extant) 
    {
      StdDraw.picture(this.x, this.y, "/"+System.getProperty("user.dir")+"/Graphics/nemesis.png", extent, extent);
      StdDraw.setPenColor(StdDraw.GREEN);
      StdDraw.filledRectangle(this.x, this.y + 0.7*extent, hitpoints*extent/500, 5);
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
      if (this.x > Annihilators.boundary - 0.5*extent)
      {
        orientation = false;
        double rand = 0;
        while (rand < 0.1 || rand > 0.5) rand = Math.random(); 
        velocityFactor = rand * 2 * progressionMultiplier;
      }
      else
      {
        this.x += velocityFactor*velocity;
      }
    }
  }
  
  public void moveLeft()
  {
    if (orientation == false)
    {
      if (this.x < -Annihilators.boundary + 0.5*extent)
      {
        orientation = true;
        double rand = 0;
        while (rand < 0.1 || rand > 0.5) rand = Math.random(); 
        velocityFactor = rand * 2 * progressionMultiplier;
      }
      else
      {
        this.x -= velocityFactor*velocity;
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
}
