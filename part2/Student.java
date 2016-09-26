
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//***************************************************************************************************************************************************
// Given class (needs further implementation)
public class Student extends Thread {
  //=================================================================================================================================================

    // Given entities
    private String name;
    private MusicalChairsGame game;
    
    private int winner = 0;
    private int stand = 0;
    private int lostRound = 0;
    private ArrayList<Integer> satChair;

    //=================================================================================================================================================
    public Student(String name, MusicalChairsGame game) {
        // ...
        this.name = name;
        this.game = game;
        this.satChair = new ArrayList<Integer>();
    }

    //=================================================================================================================================================
    @Override
    public synchronized void run() {

        while(true)
        {
            Teacher.roundAboutToBegin.waitTurn();
            System.out.println(name + " : I'm waiting for the music to start.");
            Teacher.waitingForMusic.waitTurn();
            Teacher.startsMusic.waitTurn();
            System.out.println(name + " : I'm walking around the chairs.");
            Teacher.stop.waitTurn();
            game.sitDownOnAnAvailableChair(this);
            if(this.getWinner() == 1)
            {
                MusicalChairsGame.finish = 1;
                setStand(1);
            }
            if (getStand() == 1) {
                Teacher.report.waitTurn();
                MusicalChairsGame.printReport(this);
                break;
            } else {
                Teacher.sit.waitTurn();
            }
        }

    }

    //=================================================================================================================================================
    /**
     * @return the name
     */
    public String getNameStudent() {
        return name;
    }

    /**
     * @return the winner
     */
    public int getWinner() {
        return winner;
    }

    /**
     * @param winner the winner to set
     */
    public void setWinner(int winner) {
        this.winner = winner;
    }

    /**
     * @return the stand
     */
    public int getStand() {
        return stand;
    }

    /**
     * @param stand the stand to set
     */
    public void setStand(int stand) {
        this.stand = stand;
    }

    /**
     * @return the lostRound
     */
    public int getLostRound() {
        return lostRound;
    }

    /**
     * @param lostRound the lostRound to set
     */
    public void setLostRound(int lostRound) {
        this.lostRound = lostRound;
    }

    /**
     * @return the satChair
     */
    public ArrayList<Integer> getSatChair() {
        return satChair;
    }

    /**
     * @param satChair the satChair to set
     */
    public void setSatChair(ArrayList<Integer> satChair) {
        this.satChair = satChair;
    }
}

//***************************************************************************************************************************************************

