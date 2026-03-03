import javax.swing.ImageIcon;

/**
 * Class file for the player class, the user's controllable fighter in the battle, this is a subclass of the abstract character class
 * <p>
 *     The player is a fighter whose equipment the user can customize
 * </p>
 * <p>
 *     The player is capable of attacking, using weapon skills if available, defending, charging, and using a consumable if available
 * </p>
 * @see Entity
 * @author Stefan_Martin
 */
public class Player extends Entity {

    /**
     * Constructor for the player class
     * @param name name of the player, name must be 0-15 characters long
     * @param image image of te player
     */
    public Player(String name, String image){

        super(name, new ImageIcon(image), 100, 1, 1, 50);
        
    }


    /**
     * Method for the player to choose what they wish to do during their turn, the player can choose to do only
     * one action per turn
     * @param target The enemy object the player is currently fighting
     */
    protected void think(Entity target){

        //Switch statement that will call the corresponding method depending on user choice
        switch(getTurnInputAction()){

            case 'A':

                setLastCharacterAction("Attack");
                attack(target); //A - Attack Target 
                break;


            case 'D': 

                setLastCharacterAction("Defend");
                defend(); //D - Defend against Target
                break;


            case 'C': 


                setLastCharacterAction("Charge");
                if(!getIsCharging()) //If condition to check whether the player is charging 
                    charge(); //C - Charge Player 
                     
                break;
                
            case 'U':

                //If condition to check whether the player is charging 
                if(getConsumable().getChargesLeft() > 0){

                    setLastCharacterAction("Consume");
                    consume(target); //U - Charge Player 

                }    

                break;

            case 'F':

                if(getWeapon() != null && getWeapon() instanceof EnchantedWeapon && 
                   ((EnchantedWeapon) getWeapon()).getWeaponSkill().getHasMetConditions()){

                        setLastCharacterAction("Skill");
                        useSkill(target);        
                        
                }
        

        }

    }
}
