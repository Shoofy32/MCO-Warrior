/**
 * Class file for the Fists_Ability class, this is a subclass of the abstract Ability class
 * @see Ability
 * @author Martin
 */
public class Fists_Ability extends Ability{

    private int numOfAttack = 0;
    private int counter = 0;
    private int enemyHealth;

    /**
     * Constructor for the Fists_Ability class
     */
    public Fists_Ability(){

        super("5 stacking attack for each consecutive attack (Resets if non-attack action).", 
              " activated Fists passive! They gain 5 more Attack!");

    }

    /**
     * Concrete implementation of the abstract method <i>checkAbilityCondition</i>
     * @param entity entity object that has equipped the Fists
     */
    public void checkAbilityCondition(Entity entity){

        if(entity.getTurnInputAction() == 'A'){

            numOfAttack++;
            counter++;

        }

        else
            numOfAttack = 0;

        if(numOfAttack > 0)
            setHasMetConditions(true);     
        else{

            setHasMetConditions(false);        
            entity.setAttack(entity.getAttack() - (5 * counter));
            counter = 0;   

        }

    }

    /**
     * Concrete implementation of the abstract method <i>activateAbility</i>
     * @param entity entity object that has equipped the Fists
     * @param target entity object that is the target for the Fists' ability
     */
    public void activateAbility(Entity entity, Entity target){

        enemyHealth = target.getHitPoints();

        checkAbilityCondition(entity);
        
        if(getHasMetConditions())
            entity.setAttack(entity.getAttack() + 5);

    }

    /**
     * Concrete implementation of abstract method <i>deactivateAbility</i>
     * @param entity entity object that has equipped the Fists
     */
    public void deactivateAbility(Entity entity){

        if(entity.getHitPoints() == 0 || enemyHealth == 0){

            counter = 0;
            setHasMetConditions(false);

        }


    }

}
