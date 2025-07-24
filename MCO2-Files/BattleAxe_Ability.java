/**
 * Class file for the BattleAxe_Ability class, this is a subclass of the Ability class
 * @see Ability
 * @author Stefan_Martin
 */
public class BattleAxe_Ability extends Ability{
    
    private boolean abilityIsActive = false;

    /**
     * Constructor for the BattleAxe_Ability class
     */
    public BattleAxe_Ability(){

        super("When charging, gain 5 speed and 5 attack in the next turn.", 
              " activated passive! Gain 5 Speed and Attack in their next turn!");

    }

    /**
     * Concrete implementation of abstract method <i>checkAbilityCondition</i>
     * @param character Character object that has equipped the Battle Axe
     */
    public void checkAbilityCondition(Character character){

        if(character.getTurnInputAction() == 'C' && !abilityIsActive){

            setHasMetConditions(true);
            abilityIsActive = true;
    
        }
        else
            setHasMetConditions(false);
            
    }

    /**
     * Concrete implementation of abstract method <i>activateAbility</i>
     * @param character Character object that has equipped the Battle Axe
     * @param target Character object that is the target for the Battle Axe's ability
     */
    public void activateAbility(Character character, Character target){

        checkAbilityCondition(character);

        if(getHasMetConditions()){

            character.setAttack(character.getAttack() + 5);
            character.setSpeed(character.getSpeed() + 5);

        }

    }

    /**
     * Concrete implementation of abstract method <i>deactivateAbility</i>
     * @param character Character object that has equipped the Battle Axe
     */
    public void deactivateAbility(Character character){

        if(!character.getIsCharging()){
            
            setHasMetConditions(false);
            abilityIsActive = false;
            character.setAttack(character.getAttack() - 5);
            character.setSpeed(character.getSpeed() - 5);

        }

    }

}
