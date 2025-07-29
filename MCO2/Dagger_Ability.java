/**
 * Class file for the Dagger_Ability class, this is a subclass of the abstract Ability class
 * @see Ability
 * @author Stefan_Martin
 */
public class Dagger_Ability extends Ability{
    
    private int numOfDefend = 0;

    /**
     * Constructor for the Dagger_Ability class
     */
    public Dagger_Ability(){

        super("When defending, every other defend will become a 100% evade.", 
              " activated Dagger passive! Their defend turns into an evade!");

    }

    /**
     * Constructor for the Dagger_Ability class
     * @param entity entity object that has equipped the Dagger
     */
    public void checkAbilityCondition(Entity entity){

        if(entity.getTurnInputAction() == 'D')
            numOfDefend++;

        if(numOfDefend == 2){

            setHasMetConditions(true);
            numOfDefend = 0;

        }

    }

    /**
     * Concrete implementation of the abstract method <i>checkAbilityCondition</i>
     * @param entity entity object that has equipped the Dagger
     * @param target entity object that is the target for the Dagger's ability
     */
    public void activateAbility(Entity entity, Entity target){

        checkAbilityCondition(entity);
        
        if(getHasMetConditions())
            entity.setIsInvulnerable(getHasMetConditions());

    }

    /**
     * Concrete implementation of the abstract method <i>activateAbility</i>
     * @param entity entity object that has equipped the Dagger
     */
    public void deactivateAbility(Entity entity){

        if(entity.getIsInvulnerable()){

            setHasMetConditions(false);
            entity.setIsInvulnerable(getHasMetConditions());

        }


    }
}
