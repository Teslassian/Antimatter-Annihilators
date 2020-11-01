import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.*;
import java.io.*;
import sun.audio.*;


public class GameRunner
{
  //class variables
  private static final int noNemeses = 1;
  
  //instance variables
  static private int maxAmmo = 0;
  private static int gameCase = 0;
  private static boolean enemyWin = false;
  private static int noPlayers = 1;
  private static int noMinions = 0;
  private static int arsenal = maxAmmo;
  private static ArrayList<Missile> firedMissiles = new ArrayList<Missile>();
  private static ArrayList<Missile> enemyMissiles = new ArrayList<Missile>();
  private static ArrayList<Minion> minions = new ArrayList<Minion>();
  private static ArrayList<Player> players = new ArrayList<Player>();
  private static ArrayList<Bunker> bunkers = new ArrayList<Bunker>();
  private static ArrayList<Nemesis> nemeses = new ArrayList<Nemesis>();
  
  
  //instance methods
  public int startgame(int level)
  {
    reset(level);
    createEntities(level);
    StdDraw.enableDoubleBuffering();
    
    //game loop start{
    do
    {
      //this causes goslow
      //StdDraw.picture(0, 0, "gameRunnerScreen.gif", Annihilators.boundary*2, Annihilators.boundary*2);
      StdDraw.clear(StdDraw.BLACK);
      
      stats(level);
      
      movePlayer();
      drawPlayer();
      
      reloadMissiles();
      fireMissiles();
      drawMissiles();
      
      fireEnemyMissiles(); 
      drawEnemyMissiles();
      
      moveMinion();
      drawMinion();
      
      moveNemesis();
      drawNemesis(); 
      
      drawBunker();  
      
      checkCoincidence();
      
      explode();
      
      removeExplodedEntities();
      
      quit(); 
      
      //StdDraw.pause(2);
      StdDraw.show();
    } while (gameCase == 0);
    //game loop end}
    
    StdDraw.disableDoubleBuffering();
    return (gameCase);
    //}
  };  
  
  
  
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  
  private static void stats(int level)
  {
    StdDraw.setPenColor(StdDraw.BLUE);
    Font font = new Font("Cooper Black", Font.BOLD, 20);
    StdDraw.setFont(font);
    
    StdDraw.text(-800, 900, "Quit: Q");
    StdDraw.text(-400, 900, "Level: " + level);
    if (players.get(0).livesAccessor() >= 0)
    {
      StdDraw.text(0, 900, "Lives: " + players.get(0).livesAccessor());
    }
    else
    {
      StdDraw.text(0, 900, "Lives: 0");
    }
    StdDraw.text(400, 900, "Kills: " + HomeScreen.morts);
    StdDraw.text(800, 900, "Ammo: " + arsenal);
  }
  
  
  
  private static void explode()
  {
    for (int i=minions.size()-1;i>=0;i--)
    {
      if (!minions.get(i).extantAccessor()) minions.get(i).explosionExtentMutator(minions.get(i).explosionExtentAccessor() + 5);
    }
    if (!players.get(0).extantAccessor()) 
    {
      players.get(0).explosionExtentMutator(players.get(0).explosionExtentAccessor() + 20);
      if (players.get(0).explodedAccessor()) enemyWin = true;
    }
    for (int i=bunkers.size()-1;i>=0;i--)
    {
      if (!bunkers.get(i).extantAccessor()) bunkers.get(i).explosionExtentMutator(bunkers.get(i).explosionExtentAccessor() + 5);
    }
  }
  
  private static void createEntities(int level)
  {
    for (int i=0;i<noMinions;i++)          ////////////these into functions
    {
      appendMinion(minions, (-Annihilators.boundary-1*Minion.extent-i*Minion.extent*2), level);  //rather pass variables like Annihilators.boundary via arguments 
    }
    
    for (int i=0;i<noPlayers;i++)
    {
      appendPlayer(players, level+3);
      players.get(i).livesMutator(1+level);
    }
    
    for (int i=1;i<level;i++)
    {
      appendBunker(bunkers, (-Annihilators.boundary+((i*Annihilators.boundary*2) / (level))));
    }
    
    for (int i=0;i<noNemeses;i++)
    {
      appendNemesis(nemeses, level);
    }
  }
  
  private static void removeExplodedEntities()
  {  
    for (int i=minions.size()-1;i>=0;i--)
    {
      if (minions.get(i).explodedAccessor()){ 
        minions.remove(i);
        
      }
    }
    
    for (int i=players.size()-1;i>=0;i--)
    {
      if (players.get(i).explodedAccessor()){ players.remove(i);
        
        
      }
    }
    
    for (int i=bunkers.size()-1;i>=0;i--)
    {
      if (bunkers.get(i).explodedAccessor()){
        bunkers.remove(i);
        
      }
    }
    
    for (int i=firedMissiles.size()-1;i>=0;i--)
    {
      if (!firedMissiles.get(i).extantAccessor()) firedMissiles.remove(i);
    }
    
    
    for (int i=nemeses.size()-1;i>=0;i--)
    {
      if (nemeses.get(i).explodedAccessor()) nemeses.remove(i);
    }
  }
  
  private static void appendMissile(ArrayList<Missile> missiles)
  {
    Missile missile = new Missile();
    missiles.add(missile);
  }
  
  private static void appendBunker(ArrayList<Bunker> bunkers, double x)
  {
    Bunker bunker = new Bunker(x);
    bunkers.add(bunker);
  }
  
  private static void appendPlayer(ArrayList<Player> players, int level)
  {
    Player player = new Player(5 + level);
    players.add(player);
  }
  
  private static void appendMinion(ArrayList<Minion> minions, int x, int level)
  {
    Minion minion = new Minion(x, 3+level);  //change the first Minion to Entity as in lecture slides
    minions.add(minion);
  }
  
  private static void appendNemesis(ArrayList<Nemesis> nemeses, int level)
  {
    Nemesis nemesis = new Nemesis(level);
    nemeses.add(nemesis);
  }
  
  private static void drawMissiles()
  {
    for (int i=firedMissiles.size()-1;i>=0;i--)
    {
      firedMissiles.get(i).draw(false);
      if (firedMissiles.get(i).deltaAccessor() > 2100) firedMissiles.remove(i);
    }
  }
  
  private static void drawEnemyMissiles()
  {
    if (minions.size() == 0)
    {
      for (int i=enemyMissiles.size()-1;i>=0;i--)
      {
        enemyMissiles.get(i).draw(true);
        if (enemyMissiles.get(i).deltaAccessor() < -2000) enemyMissiles.remove(i);
      }
    }
  }
  
  
  private static void drawNemesis()
  {
    if (minions.size() == 0)
    {
      for (int i=nemeses.size()-1;i>=0;i--)
      {
        nemeses.get(i).draw();
      }
    }
  }
  
  
  private static void moveNemesis()
  {
    if (minions.size() == 0)
    {
      nemeses.get(0).moveRight();
      nemeses.get(0).moveLeft(); 
    }
  }
  
  
  private static void drawMinion()
  {
    for (int i=minions.size()-1;i>=0;i--)
    {
      minions.get(i).draw();
    }
  }
  
  private static void drawBunker()
  {
    for (int i=bunkers.size()-1;i>=0;i--)
    {
      bunkers.get(i).draw();
    }
  }
  
  
  private static void drawPlayer()
  { 
    for (int i=players.size()-1;i>=0;i--)
    {
      players.get(0).draw();
    }
  }
  
  private static void fireMissiles()
  {
    Missile.fireCounter--;
    if ((StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) && (arsenal > 0) && (Missile.fireCounter < 0))
    {
      Missile.fireCounter = Missile.fireInterval;
      arsenal--;
      appendMissile(firedMissiles);
      firedMissiles.get(firedMissiles.size()-1).exMutator(players.get(0).xAccessor());
      firedMissiles.get(firedMissiles.size()-1).eyMutator(players.get(0).yAccessor());
      firedMissiles.get(firedMissiles.size()-1).thetaMutator(players.get(0).thetaAccessor());
      
      // inserted sound here
      AudioPlayer MGP = AudioPlayer.player;
      AudioStream BGM = null;
      try
      {
        InputStream test = new FileInputStream("/"+System.getProperty("user.dir")+"/Sounds/laserShoot.wav");
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
  
  
  private static void fireEnemyMissiles()
  {
    if (minions.size() == 0)
    {
      Missile.enemyFireCounter--;
      if (Missile.enemyFireCounter < 0)
      {
        Missile.enemyFireCounter = Missile.enemyFireInterval;
        appendMissile(enemyMissiles);
        enemyMissiles.get(enemyMissiles.size()-1).exMutator(nemeses.get(0).xAccessor());
        enemyMissiles.get(enemyMissiles.size()-1).eyMutator(nemeses.get(0).yAccessor());
      }
    }
  }
  
  private static void reloadMissiles()
  {
    Missile.reloadCounter =- 1;
    if ((Missile.reloadCounter < 0) && (arsenal < maxAmmo))  
    {
      Missile.reloadCounter = Missile.reloadInterval;
      arsenal++;
    }
  }
  
  private static void movePlayer()
  {
    if (players.get(0).extantAccessor()) 
    {
      if (StdDraw.isKeyPressed(KeyEvent.VK_A)) players.get(0).moveLeft(-Annihilators.boundary+Player.extent/2, players.get(0).velocityAccessor());
      if (StdDraw.isKeyPressed(KeyEvent.VK_D)) players.get(0).moveRight(+Annihilators.boundary-Player.extent/2,players.get(0).velocityAccessor());
      if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) players.get(0).angleLeft();
      if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) players.get(0).angleRight();
    }    
  }
  
  private static void quit()
  {
    if (!nemeses.get(0).extantAccessor() && minions.size() == 0)
    {
      gameCase = 1;
    }
    if (enemyWin)
    {
      gameCase = 2;
    }
    if (StdDraw.isKeyPressed(KeyEvent.VK_Q))
    {
      gameCase = 3;
    }
  }
  private static void reset(int level)     ///check this very well    ///////check that everything resets -- something doesnt, check gameover on multiple loops
  {
    
    for (int i=firedMissiles.size()-1;i>=0;i--)
    {
      firedMissiles.remove(i); 
    }
    for (int i=enemyMissiles.size()-1;i>=0;i--)
    {
      enemyMissiles.remove(i); 
    }
    for (int i=minions.size()-1;i>=0;i--)
    {
      minions.remove(i);
    }
    for (int i=players.size()-1;i>=0;i--)
    {
      players.remove(i);
    }
    for (int i=bunkers.size()-1;i>=0;i--)
    {
      bunkers.remove(i);
    }
    for (int i=nemeses.size()-1;i>=0;i--)
    {
      nemeses.remove(i);
    }
    maxAmmo = 5+level*5;
    arsenal = maxAmmo;
    gameCase = 0;
    enemyWin = false;
    noPlayers = 1;
    noMinions = level*2;
  }
  
  private static void moveMinion()
  {
    for (int i=minions.size()-1;i>=0;i--)
    {
      minions.get(i).moveLeft();
      minions.get(i).moveRight();
    }
  }
  
  private static void checkCoincidence()
  {
    if (players.get(0).extantAccessor())
    {
      
      //minion-player collisions
      for (int i=minions.size()-1;i>=0;i--)   
      {
        if (minions.get(i).extantAccessor())
        {
          if (minions.get(i).yAccessor() < players.get(0).yAccessor() + Player.extent * 0.7)
          {
            if (minions.get(i).xAccessor() > players.get(0).xAccessor() - Player.extent * 0.7)
            {
              if (minions.get(i).xAccessor() < players.get(0).xAccessor() + Player.extent * 0.7) 
              {
                if (minions.get(i).yAccessor() > players.get(0).yAccessor() - Player.extent * 0.7)
                {               
                  minions.get(i).extantMutator(false);
                  minions.get(i).exMutator(minions.get(i).xAccessor());
                  minions.get(i).eyMutator(minions.get(i).yAccessor());
                  players.get(0).livesMutator(players.get(0).livesAccessor() - 1);
                  players.get(0).exMutator(players.get(0).xAccessor());
                  players.get(0).eyMutator(players.get(0).yAccessor());
                  // inserted sound here
                  AudioPlayer MGP = AudioPlayer.player;
                  AudioStream BGM = null;
                  
                  
                  
                  
                  try
                  {
                    InputStream test = new FileInputStream("/"+System.getProperty("user.dir")+"/Sounds/playerOneHurt.wav");
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
            }
          }
        }
      }
      
      //minion-ground collisions
      for (int i=minions.size()-1;i>=0;i--)
      {
        if ((minions.get(i).yAccessor() < players.get(0).yAccessor() - Player.extent * 0.7) && (minions.get(i).extantAccessor()))
        {
          enemyWin = true;
        }
      }
      
      //minion-missile collisions
      for (int i=minions.size()-1;i>=0;i--)
      {
        if (minions.get(i).extantAccessor())
        {
          for (int j=minions.size()-1;j>=0;j--)
          {
            if (j < firedMissiles.size() && i < minions.size() && minions.get(i).extantAccessor()) /////////////////////
            {
              if (firedMissiles.get(j).yAccessor() > minions.get(i).yAccessor() - Minion.extent * 0.7)
              {
                if (firedMissiles.get(j).xAccessor() < minions.get(i).xAccessor() + Minion.extent * 0.7)
                {
                  if (firedMissiles.get(j).xAccessor() > minions.get(i).xAccessor() - Minion.extent * 0.7)
                  {
                    if (firedMissiles.get(j).yAccessor() < minions.get(i).yAccessor() + Minion.extent * 0.7)
                    {
                      firedMissiles.get(j).extantMutator(false);
                      minions.get(i).hitMutator(true);
                      minions.get(i).exMutator(minions.get(i).xAccessor());
                      minions.get(i).eyMutator(minions.get(i).yAccessor());
                    }
                  }
                }    
              }
            }
          }
        }
      }
      
      //nemesis-missile collisions
      if (minions.size() == 0)
      {
        for (int j=firedMissiles.size()-1;j>=0;j--)
        {
          if (j < firedMissiles.size() && nemeses.get(0).extantAccessor()) /////////////////////
          {
            if (firedMissiles.get(j).yAccessor() > nemeses.get(0).yAccessor() - Nemesis.extent * 0.7)
            {
              if (firedMissiles.get(j).xAccessor() < nemeses.get(0).xAccessor() + Nemesis.extent * 0.7)
              {
                if (firedMissiles.get(j).xAccessor() > nemeses.get(0).xAccessor() - Nemesis.extent * 0.7)
                {
                  if (firedMissiles.get(j).yAccessor() < nemeses.get(0).yAccessor() + Nemesis.extent * 0.7)
                  {
                    firedMissiles.get(j).extantMutator(false);
                    nemeses.get(0).hitMutator(true);
                    nemeses.get(0).exMutator(nemeses.get(0).xAccessor());
                    nemeses.get(0).eyMutator(nemeses.get(0).yAccessor());
                  }
                }
              }    
            }
          }
        }
      }
      
      //enemyMissile-player collision
      for (int i=enemyMissiles.size()-1;i>=0;i--)
      {
        if (enemyMissiles.get(i).extantAccessor())
        {
          if (enemyMissiles.get(i).yAccessor() < players.get(0).yAccessor() + Player.extent * 0.7)
          {
            if (enemyMissiles.get(i).xAccessor() > players.get(0).xAccessor() - Player.extent * 0.7)
            {
              if (enemyMissiles.get(i).xAccessor() < players.get(0).xAccessor() + Player.extent * 0.7) 
              {
                if (enemyMissiles.get(i).yAccessor() > players.get(0).yAccessor() - Player.extent * 0.7)
                {               
                  enemyMissiles.get(i).extantMutator(false);
                  enemyMissiles.get(i).exMutator(enemyMissiles.get(i).xAccessor());
                  enemyMissiles.get(i).eyMutator(enemyMissiles.get(i).yAccessor());
                  players.get(0).livesMutator(players.get(0).livesAccessor() - 1);
                  players.get(0).exMutator(players.get(0).xAccessor());
                  players.get(0).eyMutator(players.get(0).yAccessor());
                  // inserted sound here
                  AudioPlayer MGP = AudioPlayer.player;
                  AudioStream BGM = null;
                  try
                  {
                    InputStream test = new FileInputStream("/"+System.getProperty("user.dir")+"/Sounds/playerOneHurt.wav");
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
            }
          }
        }
      }
      
      //missile-bunker collisions
      for (int i=bunkers.size()-1;i>=0;i--)
      {
        if (bunkers.get(i).extantAccessor())
        {
          for (int j=bunkers.size()-1;j>=0;j--)
          {
            if (j < firedMissiles.size() && i < bunkers.size() && bunkers.get(i).extantAccessor()) /////////////////////
            {
              if (firedMissiles.get(j).yAccessor() > bunkers.get(i).yAccessor() - Minion.extent * 0.7)
              {
                if (firedMissiles.get(j).xAccessor() < bunkers.get(i).xAccessor() + Minion.extent * 0.7)
                {
                  if (firedMissiles.get(j).xAccessor() > bunkers.get(i).xAccessor() - Minion.extent * 0.7)
                  {
                    if (firedMissiles.get(j).yAccessor() < bunkers.get(i).yAccessor() + Minion.extent * 0.7)
                    {
                      firedMissiles.get(j).extantMutator(false);
                      bunkers.get(i).extantMutator(false);                      
                    }
                  }
                }    
              }
            }
          }
        }
      }
      
    }
  }
  
  
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  
}