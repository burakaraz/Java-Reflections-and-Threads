import java.util.Vector ;

public class Obstacle extends Entity
{
  private Vector speed ;

  public Boolean bounce ( int n , float deformation )
  {
    return true ;
  }
  
  public void step ( double deltaTime )
  {
  }

  public void join ( Unit u )
  {
  }
}
