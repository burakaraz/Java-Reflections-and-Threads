import java.awt.Color ;

public abstract class Entity implements Unit
{
  public           int   x     ;
  protected static Color color ;
  private          int   count ;

  public abstract void step ( double deltaTime ) ;
}
