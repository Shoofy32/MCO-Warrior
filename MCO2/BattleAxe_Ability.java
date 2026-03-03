/**
 * Class file for the BattleAxe_Ability class, this is a subclass of the Ability class
 * @see Ability
 * @author Stefan_Martin
 */
public class BattleAxe_Ability extends Ability{
    
    private boolean abilityIsActive = false;
    private boolean hasCharged = false;

    /**
     * Constructor for the BattleAxe_Ability class
     */
    public BattleAxe_Ability(){

        super("When charging, gain 5 speed and 5 attack in the next turn.", 
              " activated passive! Gain 5 Speed and Attack in their next turn!");

    }

    /**
     * Concrete implementation of abstract method <i>checkAbilityCondition</i>
     * @param entity entity object that has equipped the Battle Axe
     */
    public void checkAbilityCondition(Entity entity){

        if(entity.getTurnInputAction() == 'C' && !abilityIsActive){

            setHasMetConditions(true);
            abilityIsActive = true;
            hasCharged = true;
    
        }
        else
            setHasMetConditions(false);
            
    }

    /**
     * Concrete implementation of abstract method <i>activateAbility</i>
     * @param entity entity object that has equipped the Battle Axe
     * @param target entity object that is the target for the Battle Axe's ability
     */
    public void activateAbility(Entity entity, Entity target){

        checkAbilityCondition(entity);

        if(getHasMetConditions()){

            entity.setAttack(entity.getAttack() + 5);
            entity.setSpeed(entity.getSpeed() + 5);

        }

    }

    /**
     * Concrete implementation of abstract method <i>deactivateAbility</i>
     * @param entity entity object that has equipped the Battle Axe
     */
    public void deactivateAbility(Entity entity){

        if(hasCharged && !entity.getIsCharging()){
            
            setHasMetConditions(false);
            abilityIsActive = false;
            hasCharged = false;
            entity.setAttack(entity.getAttack() - 5);
            entity.setSpeed(entity.getSpeed() - 5);

        }

    }

}
