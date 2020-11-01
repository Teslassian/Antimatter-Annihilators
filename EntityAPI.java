public interface EntityAPI
{
  static public final double progressionMultiplier = 1;  //this determines the running speed of the game, +/- 5 at home, 2 at firga 
  public void moveLeft(int limit, double velocity);
  public void moveRight(int limit, double velocity);
} 