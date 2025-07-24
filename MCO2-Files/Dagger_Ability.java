/**
 * Class file for the Dagger_Ability class, this is a subclass of the abstract Ability class
 * @see Ability
 * @author Stefan_Martin
 */
public class Dagger_Ability extends Ability{
    
    int numOfDefend = 0;

    /**
     * Constructor for the Dagger_Ability class
     */
    public Dagger_Ability(){

        super("When defending, every other defend will become a 100% evade.", 
              " activated Dagger passive! Their defend turns into an evade!");

    }

    /**
     * Concrete implementation of the abstract method <i>checkAbilityCondition</i>
     * @param character Character object that has equipped the Dagger
     */
    public void checkAbilityCondition(Character character){

        if(character.getTurnInputAction() == 'D')
            numOfDefend++;

        if(numOfDefend == 2){

            setHasMetConditions(true);
            numOfDefend = 0;

        }

    }

    /**
     * Concrete implementation of the abstract method <i>activateAbility</i>
     * @param character Character object that has equipped the Dagger
     * @param target Character object that is the target for the Dagger's ability
     */
    public void activateAbility(Character character, Character target){

        checkAbilityCondition(character);
        
        if(getHasMetConditions())
            character.setIsInvulnerable(getHasMetConditions());

    }

    /**
     * Concrete implementation of the abstract method <i>deactivateAbility</i>
     * @param character Character object that has equipped the Dagger
     */
    public void deactivateAbility(Character character){

        if(character.getIsInvulnerable()){

            setHasMetConditions(false);
            character.setIsInvulnerable(getHasMetConditions());

        }


    }
}
