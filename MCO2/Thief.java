import javax.swing.ImageIcon;

/**
 * Class file for the Thief class, this is a subclass of the abstract Enemy class
 * <p>
 *     The Thief class has an attack pattern of "Attack" represented by the string "A"
 * </p>
 * @see Enemy
 * @author Stefan_Martin
 */
public class Thief extends Enemy{

    /**
     * Constructor for the thief class
     * @param name name of the thief
     * @param type type of the thief
     * @param image image of the thief
     * @param hitPoints HP value of the thief
     * @param attack Atk value of the thief
     * @param defense Def value of the thief
     * @param speed Spd value of the thief
     */
    public Thief(String name, String type, ImageIcon image, int hitPoints, int attack, int defense, int speed){

        super(name, type, image, hitPoints, attack, defense, speed, "A");
        totalEnemies++; //Increment
        
    }

    /**
     * Concrete implementation of the <i>think</i> method
     * Method that dictates the action of the thief in a given turn
     * @param target Entity object targeted by the action
     */
    public void think(Entity target){

        setLastCharacterAction("Attack");
        attack(target);

    }


}
