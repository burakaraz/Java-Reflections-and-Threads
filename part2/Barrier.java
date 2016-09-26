
import java.util.logging.Level;
import java.util.logging.Logger;

//***************************************************************************************************************************************************

// Given class (needs further implementation)

public class Barrier
{
  //=================================================================================================================================================

  // Given entities

  private int numberOfThreadsCurrentlyWaiting    ;
  private int numberOfThreadsToReachBarrierPoint ;
  private int totalNumberOfThreads;

  //=================================================================================================================================================

  public Barrier ( int numberOfThreadsToReachBarrierPoint )
  {
      this.numberOfThreadsToReachBarrierPoint = numberOfThreadsToReachBarrierPoint;
  }

  //=================================================================================================================================================

  public synchronized void await ()
  {
	  notifyAll();
      this.numberOfThreadsCurrentlyWaiting = 0;

  }
  
  public synchronized void waitTurn ()
  {
    this.numberOfThreadsCurrentlyWaiting++;
    try {
        wait();
    } catch (InterruptedException ex) {
        Logger.getLogger(Barrier.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  //=================================================================================================================================================

    /**
     * @return the numberOfThreadsCurrentlyWaiting
     */
    public int getNumberOfThreadsCurrentlyWaiting() {
        return numberOfThreadsCurrentlyWaiting;
    }

    /**
     * @param numberOfThreadsCurrentlyWaiting the numberOfThreadsCurrentlyWaiting to set
     */
    public void setNumberOfThreadsCurrentlyWaiting(int numberOfThreadsCurrentlyWaiting) {
        this.numberOfThreadsCurrentlyWaiting = numberOfThreadsCurrentlyWaiting;
    }

    /**
     * @return the numberOfThreadsToReachBarrierPoint
     */
    public int getNumberOfThreadsToReachBarrierPoint() {
        return numberOfThreadsToReachBarrierPoint;
    }

    /**
     * @param numberOfThreadsToReachBarrierPoint the numberOfThreadsToReachBarrierPoint to set
     */
    public void setNumberOfThreadsToReachBarrierPoint(int numberOfThreadsToReachBarrierPoint) {
        this.numberOfThreadsToReachBarrierPoint = numberOfThreadsToReachBarrierPoint;
    }

    /**
     * @return the totalNumberOfThreads
     */
    public int getTotalNumberOfThreads() {
        return totalNumberOfThreads;
    }

    /**
     * @param totalNumberOfThreads the totalNumberOfThreads to set
     */
    public void setTotalNumberOfThreads(int totalNumberOfThreads) {
        this.totalNumberOfThreads = totalNumberOfThreads;
    }
}

//***************************************************************************************************************************************************

