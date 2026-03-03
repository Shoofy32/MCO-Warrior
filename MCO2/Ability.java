/**
 *Class file for the abstract Ability class
 * <p>
 *     This abstract class implements general methods all ability subclasses share, these are mainly getter and setter methods
 * </p>
 * <p>
 *     Abilities are passive effects that activate on their own once their conditions have been met
 * </p>
 * @author Stefan_Martin
 */
public abstract class Ability {

    private boolean hasMetConditions = false;

    private String abilityDescription;
    private String abilityDisplayDescription;


    /**
     * Constructor for the abstract Ability class
     * @param abilityDescription brief description of the weapon's ability
     * @param abilityDisplayDescription text to display when the weapon's ability activates
     */
    public Ability(String abilityDescription, String abilityDisplayDescription){

        this.abilityDescription = abilityDescription;
        this.abilityDisplayDescription = abilityDisplayDescription;

    }

    /**
     * Setter method to indicate the state of the weapon's ability
     * @param condition boolean to set ability activation to either true or false
     */
    public void setHasMetConditions(boolean condition){

        hasMetConditions = condition;

    }

    /**
     * Getter method to indicate if the weapon's ability is active or not
     * @return boolean regarding the state of the weapon's ability
     */
    public boolean getHasMetConditions(){

        return hasMetConditions;

    }

    /**
     * Getter method to retrieve the weapon's ability description
     * @return string detailing a brief description of the weapon's ability
     */
    public String getAbilityDecsription(){

        return abilityDescription;

    }

    /**
     * Getter method to retrieve the weapon's ability display description
     * @return text to display when the weapon's ability activates
     */
    public String getAbilityDisplayDescription(){

        return abilityDisplayDescription;
        
    }

    /**
     * Abstract method to be implemented by subclasses, checks if the weapon's ability conditions have been met
     * @param entity entity object that has equipped the weapon
     */
    public abstract void checkAbilityCondition(Entity entity);

    /**
     * Abstract method to be implemented by subclasses, activates the weapon's ability
     * @param entity entity object that has equipped the weapon
     * @param target entity object that is the target for the weapon's ability
     */
    public abstract void activateAbility(Entity entity, Entity target);

    /**
     * Abstract method to be implemented by subclasses, deactivates the weapon's ability
     * @param entity entity object that has equipped the weapon
     */
    public abstract void deactivateAbility(Entity entity);



}
