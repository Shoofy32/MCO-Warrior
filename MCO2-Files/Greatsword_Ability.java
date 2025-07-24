/**
 * Class file for Greatsword_Ability, this is a subclass of the abstract Ability class
 * @see Ability
 * @author Stefan
 */
public class Greatsword_Ability extends Ability{

    private boolean abilityIsActive = false;

    /**
     * Constructor for the Greatsword_Ability class
     */
    public Greatsword_Ability(){

        super("Charging is a 5x multiplier to attack.", 
              " activated Greatsword passive! Their charge attack is multiplied by 5 instead of 3!");

    }

    /**
     * Concrete implementation of the abstract method <i>checkAbilityCondition</i>
     * @param character Character object that has equipped the Greatsword
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
     * Concrete implementation of the abstract method <i>activateAbility</i>
     * @param character Character object that has equipped the Greatsword
     * @param target Character object that is the target for the Greatsword's ability
     */
    public void activateAbility(Character character, Character target){

        checkAbilityCondition(character);

        if(getHasMetConditions())
            character.setChargeMultipler(character.getChargeMultiplier() + 2);

    }

    /**
     * Concrete implementation of abstract method <i>deactivateAbility</i>
     * @param character Character object that has equipped the Greatsword
     */
    public void deactivateAbility(Character character){

        if(!character.getIsCharging()){
            
            setHasMetConditions(false);
            abilityIsActive = false;
            character.setChargeMultipler(character.getChargeMultiplier() - 2);

        }

    }

}
