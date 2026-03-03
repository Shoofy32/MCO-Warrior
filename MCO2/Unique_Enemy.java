import javax.swing.ImageIcon;

/**
 * Class file for the Unique Enemy class, this is a subclass of the abstract Enemy class
 * <p>
 *     Unique enemy is a bonus Enemy subclass.
 * </p>
 * <p>
 *     Unique enemies are more powerful enemy types capable of using the bonus consumable, skill classes.
 * </p>
 * @see Enemy
 */
public class Unique_Enemy extends Enemy{
    
    private int counter = 0;
    private String pattern;
    char currentPatternTurn;

    /**
     * Constructor for the unique enemy class
     * @param name name of the enemy, all names are assumed valid
     * @param type type of the enemy, all types are assumed valid
     * @param image image of the enemy
     * @param hitPoints base HP of the enemy, all values are assumed valid
     * @param attack attack value of the enemy, all values are assumed valid
     * @param defense defense value of the enemy, all values are assumed valid
     * @param speed speed value of the enemy, all values are assumed valid
     * @param pattern attack pattern of the enemy
     * @param weapon weapon to be equipped by the enemy
     * @param consumable consumable to be equipped by the enemy
     */
    public Unique_Enemy(String name, String type, ImageIcon image, int hitPoints, 
            int attack, int defense, int speed, String pattern, Weapon weapon, Consumable consumable){

        super(name, type, image, hitPoints, attack, defense, speed, pattern);
        this.pattern = pattern;
        totalEnemies++; //Increment
        equipWeapon(weapon);
        equipConsumable(consumable);

    }

    /**
     * Concrete implementation of the <i>think</i> method
     * Method that dictates the action of the minotaur in a given turn
     * @param target Entity object targeted by the action
     */
    public void think(Entity target){

        switch(getTurnInputAction()){

            case 'A':
                
                setLastCharacterAction("Attack");
                attack(target);
                break;

            case 'D':

                setLastCharacterAction("Defend");
                defend();
                break;

            case 'C':

                setLastCharacterAction("Charge");
                charge();
                break;

            case 'U':

                setLastCharacterAction("Consume");
                setTurnInputAction(currentPatternTurn);
                consume(target);          

                break;

            case 'F':

                setLastCharacterAction("Skill");
                useSkill(target);

                break;
            
            default:
                System.out.printf("\nCurrent choice does not exist within the given.\n");

        }

            
        if(counter == pattern.length())
            counter = 0;
        else
            counter++;

    }


}
