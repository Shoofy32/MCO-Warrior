import javax.swing.ImageIcon;

/**
 * Class file for the Viking class, this is a subclass of the abstract Enemy class
 * <p>
 *     The Viking class has an attack pattern of "Attack->Defend->Attack" represented by the string "ADA"
 * </p>
 * @see Enemy
 * @author Stefan_Martin
 */
public class Viking extends Enemy{

    /**
     * Constructor for the viking class
     * @param name name of the viking
     * @param type type of the viking
     * @param image image of the viking
     * @param hitPoints HP value of the viking
     * @param attack Atk value of the viking
     * @param defense Def value of the viking
     * @param speed Spd value of the viking
     */
    public Viking(String name, String type, ImageIcon image, int hitPoints, int attack, int defense, int speed){

        super(name, type, image, hitPoints, attack, defense, speed, "ADA");
        totalEnemies++; //Increment
        
    }

    /**
     * Concrete implementation of the <i>think</i> method
     * Method that dictates the action of the viking in a given turn
     * @param target Entity object targeted by the action
     */
    public void think(Entity target){


        if(getTurnInputAction() == 'A'){

            setLastCharacterAction("Attack");
            attack(target);
            
        }
        else{

            setLastCharacterAction("Defend");
            defend();
            
        }

    }

    
}
