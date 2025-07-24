/**
 * Class file for the Fists_Ability class, this is a subclass of the abstract Ability class
 * @see Ability
 * @author Martin
 */
public class Fists_Ability extends Ability{

    int numOfAttack = 0;
    int counter = 0;
    int enemyHealth;

    /**
     * Constructor for the Fists_Ability class
     */
    public Fists_Ability(){

        super("5 stacking attack for each consecutive attack (Resets if non-attack action).", 
              " activated Fists passive! They gain 5 more Attack!");

    }

    /**
     * Concrete implementation of the abstract method <i>checkAbilityCondition</i>
     * @param character Character object that has equipped the Fists
     */
    public void checkAbilityCondition(Character character){

        if(character.getTurnInputAction() == 'A'){

            numOfAttack++;
            counter++;

        }

        else
            numOfAttack = 0;

        if(numOfAttack > 0)
            setHasMetConditions(true);     
        else{

            setHasMetConditions(false);        
            character.setAttack(character.getAttack() - (5 * counter));
            counter = 0;   

        }

    }

    /**
     * Concrete implementation of the abstract method <i>activateAbility</i>
     * @param character Character object that has equipped the Fists
     * @param target Character object that is the target for the Fists' ability
     */
    public void activateAbility(Character character, Character target){

        enemyHealth = target.getHitPoints();

        checkAbilityCondition(character);
        
        if(getHasMetConditions())
            character.setAttack(character.getAttack() + 5);

    }

    /**
     * Concrete implementation of abstract method <i>deactivateAbility</i>
     * @param character Character object that has equipped the Fists
     */
    public void deactivateAbility(Character character){

        if(character.getHitPoints() == 0 || enemyHealth == 0){

            counter = 0;
            setHasMetConditions(false);

        }


    }

}
