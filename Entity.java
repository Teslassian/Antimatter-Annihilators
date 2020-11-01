public class Entity implements EntityAPI
{
  protected double x;
  protected double y;
  protected double ex;
  protected double ey;
  protected boolean extant;
  protected boolean hit;
  protected int explosionExtent = 0;
  protected double velocity = 0;
  
  public Entity(double x, double y, double ex, double ey, boolean extant, int explosionExtent, double velocity)//, double velocity, int extent, String picture)
  {
    this.x = x;
    this.y = y;
    this.ex = ex;
    this.ey = ey;
    this.extant = extant;
    this.explosionExtent = explosionExtent;
    this.velocity = velocity;
  }
  
  public void moveLeft(int limit, double velocity)
  {
    if (x > limit) x -= velocity;
  };
  
  public void moveRight(int limit, double velocity)
  {
    if (x < limit) x += velocity;
  };
  
}