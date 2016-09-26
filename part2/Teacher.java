
import java.util.logging.Level;
import java.util.logging.Logger;

//***************************************************************************************************************************************************

// Given class (needs further implementation)
public class Teacher extends Thread {
    //=================================================================================================================================================    
    public static Barrier roundAboutToBegin = new Barrier(0); // first barrier
    public static Barrier waitingForMusic = new Barrier(0);
    public static Barrier startsMusic = new Barrier(0);
    public static Barrier walkingAround = new Barrier(0);
    public static Barrier stop = new Barrier(0);
    public static Barrier sit = new Barrier(0);
    public static Barrier report = new Barrier(0);
    //public static Barrier finish = new Barrier(0);
    
    public int flag = 0;
    public static int numberOfStudents;
    public static int fixedSizeStudents;
    
    public static int round = 1;
    
    private MusicalChairsGame game;
    
    public Teacher(MusicalChairsGame game, int numberofStudents) {
        this.game = game;
        this.numberOfStudents = numberofStudents;
        this.fixedSizeStudents = numberofStudents;
    }

  //=================================================================================================================================================
    @Override
    public synchronized void run() {
        
        while(true)
        {
            if(Teacher.roundAboutToBegin.getNumberOfThreadsCurrentlyWaiting() == numberOfStudents)
            {
                System.out.println("Teacher : Round " + Teacher.round + " is about to begin now.");
                Teacher.roundAboutToBegin.await();
                round++;
            }
            if(Teacher.waitingForMusic.getNumberOfThreadsCurrentlyWaiting() == numberOfStudents)
            {
                Teacher.waitingForMusic.await();
            }
            if(Teacher.startsMusic.getNumberOfThreadsCurrentlyWaiting() == numberOfStudents)
            {
            	System.out.println("Teacher : I started the music right now!");
                Teacher.startsMusic.await();
                System.out.println("Teacher : I will keep playing music for a random amount of time...");
                game.playMusicForARandomDuration();         	
            }
            if(Teacher.stop.getNumberOfThreadsCurrentlyWaiting() == numberOfStudents)
            {
            	System.out.println("Teacher : I stopped the music!");
                Teacher.stop.await();  
            }
            if(Teacher.sit.getNumberOfThreadsCurrentlyWaiting() == numberOfStudents)
            {
                Teacher.sit.await();
                System.out.println();
                game.removeOneChair();
                game.standUpFromChair();
            }
            if(Teacher.report.getNumberOfThreadsCurrentlyWaiting() == fixedSizeStudents - 1 && numberOfStudents == 1)
            {
                System.out.println();
                System.out.println("Teacher : Now tell me about your game experiences now.");
                Teacher.report.await();
                break;
            }
            /*if(Teacher.sit.getNumberOfThreadsCurrentlyWaiting() == 4)
            {
            	System.out.println(Teacher.numberOfStudents);
            	
            }*/
            
        	
        }
        
    }

  //=================================================================================================================================================
  // main method that is going to be used during evaluation
    //
    // (If you plan to have bonus extensions like GUIs which require a differently structured main method, please inform the staff beforehand.)
    public static void main(String[] args) {
        //-----------------------------------------------------------------------------------------------------------------------------------------------

       
        if (args.length == 0) {
            args = new String[]{"Student1", "Student2","Student3","Student4","Student5","Student6","Student7","Student8","Student9","Student10","Student11","Student12","Student13","Student14","Student15","Student16","Student17","Student18","Student19","Student20","Student21","Student22"};
        }

    //-----------------------------------------------------------------------------------------------------------------------------------------------
        int numberOfStudents = args.length;
        MusicalChairsGame game = new MusicalChairsGame(numberOfStudents);

    //-----------------------------------------------------------------------------------------------------------------------------------------------        
        roundAboutToBegin.setTotalNumberOfThreads(numberOfStudents+1);
        for (int i = 0; i < numberOfStudents; i++) {
            new Student(args[i], game).start();
        }

        new Teacher(game,numberOfStudents).start();

        
        //-----------------------------------------------------------------------------------------------------------------------------------------------
    }

  //=================================================================================================================================================
}

//***************************************************************************************************************************************************

