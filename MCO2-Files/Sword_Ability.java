/**
 * Class file for Sword_Ability, this is a subclass of the abstract Ability class
 * @see Ability
 * @author Stefan_Martin
 */
public class Sword_Ability extends Ability{

    /**
     * Constructor for the Sword_Ability class
     */
    public Sword_Ability(){

        super("When attacking, gain an additional +10 attack.", 
              " activated Sword passive! Their attack deals 10 more attack!");

    }

    /**
     * Concrete implementation of the abstract method <i>checkAbilityCondition</i>
     * @param character Character object that has equipped the Sword
     */
    public void checkAbilityCondition(Character character){

        if(character.getTurnInputAction() == 'A')
            setHasMetConditions(true);

    }

    /**
     * Concrete implementation of the abstract method <i>activateAbility</i>
     * @param character Character object that has equipped the Sword
     * @param target Character object that is the target for the Sword's ability
     */
    public void activateAbility(Character character, Character target){

        checkAbilityCondition(character);

        if(getHasMetConditions())
            character.setAtkBoost(10);

    }

    /**
     * Concrete implementation of abstract method <i>deactivateAbility</i>
     * @param character Character object that has equipped the Sword
     */
    public void deactivateAbility(Character character){

        setHasMetConditions(false);

        if(character.getTurnInputAction() == 'A')
            character.setAtkBoost(0);

    }

}
