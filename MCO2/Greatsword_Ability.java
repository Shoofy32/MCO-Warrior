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
     * @param entity entity object that has equipped the Greatsword
     */
    public void checkAbilityCondition(Entity entity){

        if(entity.getTurnInputAction() == 'C' && !abilityIsActive){

            setHasMetConditions(true);
            abilityIsActive = true;
    
        }
        else
            setHasMetConditions(false);

    }

    /**
     * Concrete implementation of the abstract method <i>activateAbility</i>
     * @param entity entity object that has equipped the Greatsword
     * @param target entity object that is the target for the Greatsword's ability
     */
    public void activateAbility(Entity entity, Entity target){

        checkAbilityCondition(entity);

        if(getHasMetConditions())
            entity.setChargeMultipler(entity.getChargeMultiplier() + 2);

    }

    /**
     * Concrete implementation of abstract method <i>deactivateAbility</i>
     * @param entity entity object that has equipped the Greatsword
     */
    public void deactivateAbility(Entity entity){

        if(!entity.getIsCharging()){
            
            setHasMetConditions(false);
            abilityIsActive = false;
            entity.setChargeMultipler(entity.getChargeMultiplier() - 2);

        }

    }

}
