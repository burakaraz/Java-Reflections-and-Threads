
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

//***************************************************************************************************************************************************
// Given class (needs further implementation)
public class MusicalChairsGame {
  //=================================================================================================================================================

    // Given physical entities
    private List<Chair> chairs;
    private int numberOfPlayers;


    public static int flag = 0;
    public static int finish = 0;

    // ...
    //=================================================================================================================================================
    public MusicalChairsGame(int numberOfPlayers) {
        // ...
        chairs = new ArrayList<Chair>();
        for (int i = 0; i < numberOfPlayers - 1; i++) {
            Chair ch = new Chair(i + 1);
            chairs.add(ch);
        }
    }

    //=================================================================================================================================================
    // Suggested service
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    //=================================================================================================================================================
    // Suggested service
    public synchronized void playMusicForARandomDuration() {
        Random r = new Random();
        int Low = 500;
        int High = 1000;
        int sleepDuration = r.nextInt(High - Low) + Low;
        try {
            Thread.sleep(sleepDuration);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    //=================================================================================================================================================
    // Suggested service
    public synchronized void sitDownOnAnAvailableChair(Student student) {
        int sit = 0;
        for (int i = 0; i < chairs.size(); i++) {
            if (chairs.get(i).isAvailable()) {
                System.out.println(student.getNameStudent() + " : Phew, I got chair " + chairs.get(i).getID() + ".");
                chairs.get(i).setAvailable(false);
                sit = 1;
                student.getSatChair().add(chairs.get(i).getID());
                break;
            }
        }
        if (sit == 0) {
            System.out.println(student.getNameStudent() + " : I couldn't sit. Sigh...");
            Teacher.numberOfStudents = Teacher.numberOfStudents - 1;
            student.setStand(1);
            student.setLostRound(Teacher.round - 1);
        }
        if (chairs.size() == 1 && sit == 1) {
            student.setWinner(1);
            student.setLostRound(Teacher.round - 1);
        }
    }

    //=================================================================================================================================================
    // Suggested service
    public void standUpFromChair() {
        for (int i = 0; i < chairs.size(); i++) {
            chairs.get(i).setAvailable(true);
        }
    }

    //=================================================================================================================================================
    // Suggested service
    public void removeOneChair() {
        if (chairs.size() != 1) {
            System.out.println("Teacher : I am removing one chair.");
        }
        chairs.remove(chairs.size() - 1);
    }

    //=================================================================================================================================================
    public static synchronized void printReport(Student st) {
        if (st.getWinner() != 1) {
            System.out.print(st.getNameStudent() + " : I lost the game in round " + st.getLostRound() + "! :( ");
            if (st.getSatChair().size() == 0) {
                System.out.println("I never got any chairs.");
            } else if (st.getSatChair().size() == 1) {
                System.out.print("I sat on chair ");
                System.out.println(st.getSatChair().get(0) + ".");
            } else {
                System.out.print("I sat on chair ");
                for (int i = 0; i < st.getSatChair().size(); i++) {
                    if (st.getSatChair().size() - 1 == i) {
                        System.out.println(st.getSatChair().get(i) + ".");
                    } else {
                        System.out.print(st.getSatChair().get(i) + ", ");
                    }
                }
            }
        } else {
            System.out.print(st.getNameStudent() + " : I won the game in round " + st.getLostRound() + "! :) ");
            if (st.getSatChair().size() == 0) {
                System.out.println("I never got any chairs.");
            } else if (st.getSatChair().size() == 1) {
                System.out.print("I sat on chair ");
                System.out.println(st.getSatChair().get(0) + ".");
            } else {
                System.out.print("I sat on chair ");
                for (int i = 0; i < st.getSatChair().size(); i++) {
                    if (st.getSatChair().size() - 1 == i) {
                        System.out.println(st.getSatChair().get(i) + ".");
                    } else {
                        System.out.print(st.getSatChair().get(i) + ", ");
                    }
                }
            }
        }
    }
}

//***************************************************************************************************************************************************

