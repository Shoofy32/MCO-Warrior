import javax.swing.ImageIcon;

/**
 * Class file for the Minotaur class, this is a subclass of the abstract Enemy class
 * <p>
 *     The Minotaur class has an attack pattern of "Attack->Charge->Attack" represented by the string "ACA"
 * </p>
 * @see Enemy
 * @author Stefan_Martin
 */
public class Minotaur extends Enemy{
    
    private int counter = 0;

    /**
     * Constructor for the minotaur class
     * @param name name of the minotaur
     * @param type type of the minotaur
     * @param image image of the minotaur
     * @param hitPoints HP value of the minotaur
     * @param attack Atk value of the minotaur
     * @param defense Def value of the minotaur
     * @param speed Spd value of the minotaur
     */
    public Minotaur(String name, String type, ImageIcon image, int hitPoints, int attack, int defense, int speed){

        super(name, type, image, hitPoints, attack, defense, speed, "ACA");
        totalEnemies++; //Increment
        
    }

    /**
     * Concrete implementation of the <i>think</i> method
     * Method that dictates the action of the minotaur in a given turn
     * @param target Entity object targeted by the action
     */
    public void think(Entity target){

        if(counter == 0 || counter == 2){

            setLastCharacterAction("Attack");
            attack(target);
        }
        else{

            setLastCharacterAction("Charge");
            charge();

        }

            
        if(counter == 2)
            counter = 0;
        else
            counter++;

    }


}
