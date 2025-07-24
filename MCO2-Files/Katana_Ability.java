/**
 * Class file for Katana_Ability, this is a subclass of the abstract Ability class
 * @see Ability
 * @author Stefan
 */
public class Katana_Ability extends Ability{

    int numOfAttack = 0;

    /**
     * Constructor for the Katana_Ability class
     */
    public Katana_Ability(){

        super("Deal an additional attack (affected by buffs) every other attack.", 
              " activated Katana passive! They attack an additional time!");

    }

    /**
     * Concrete implementation of the abstract method <i>checkAbilityCondition</i>
     * @param character Character object that has equipped the Katana
     */
    public void checkAbilityCondition(Character character){

        if(character.getTurnInputAction() == 'A')
            numOfAttack++;

        if(numOfAttack == 2){

            setHasMetConditions(true);
            numOfAttack = 0;

        }

    }

    /**
     * Concrete implementation of the abstract method <i>activateAbility</i>
     * @param character Character object that has equipped the Katana
     * @param target Character object that is the target for the Katana's ability
     */
    public void activateAbility(Character character, Character target){

        checkAbilityCondition(character);
        
        if(getHasMetConditions())
            character.setTimesToAttack(character.getTimesToAttack() + 1);


    }

    /**
     * Concrete implementation of abstract method <i>deactivateAbility</i>
     * @param character Character object that has equipped the Katana
     */
    public void deactivateAbility(Character character){

        setHasMetConditions(false);
        character.setTimesToAttack(character.getTimesToAttack() - 1);

    }

}
