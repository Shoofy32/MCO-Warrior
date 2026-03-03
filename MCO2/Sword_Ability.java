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
     * @param entity entity object that has equipped the Sword
     */
    public void checkAbilityCondition(Entity entity){

        if(entity.getTurnInputAction() == 'A')
            setHasMetConditions(true);

    }

    /**
     * Concrete implementation of the abstract method <i>activateAbility</i>
     * @param entity entity object that has equipped the Sword
     * @param target entity object that is the target for the Sword's ability
     */
    public void activateAbility(Entity entity, Entity target){

        checkAbilityCondition(entity);

        if(getHasMetConditions())
            entity.setAttack(entity.getAttack() + 10);

    }

    /**
     * Concrete implementation of abstract method <i>deactivateAbility</i>
     * @param entity entity object that has equipped the Sword
     */
    public void deactivateAbility(Entity entity){

        setHasMetConditions(false);

        if(entity.getTurnInputAction() == 'A')
            entity.setAttack(entity.getAttack() - 10);

    }

}
