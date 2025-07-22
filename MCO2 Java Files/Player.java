/**
 * Class file for the player class, the user's controllable fighter in the battle
 * <p>
 *     The player is a fighter whose equipment the user can customize
 * </p>
 * <p>
 *     The player is capable of attacking, defending, charging, and using a consumable if available
 * </p>
 * @author Stefan_Martin
 */
public class Player extends Character{
    

    public Player(String name){

        super(name, 100, 1, 1, 50);
        
    }


    /**
     * Method for the player to choose what they wish to do during their turn, the player can choose to do only
     * one action per turn
     * @param target The enemy object the player is currently fighting
     * @return the given action the player will perform in the turn
     */
    protected void think(Character target){

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
