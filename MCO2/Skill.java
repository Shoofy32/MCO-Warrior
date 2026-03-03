/**
 * Class file for the abstract Skill class
 * <p>
 *     This is a bonus abstract class for special skills to be used by the bonus EnchantedWeapon class.
 * </p>
 * <p>
 *     Weapon skills or active abilities are a special action a entity can take during their turn.
 * </p>
 * <p>
 *     Weapon skills range from powerful attacks to buffing the weapon holder.
 * </p>
 * <p>
 *     These skills have an activation condition that needs to be met before becoming available to use.
 * </p>
 * @see EnchantedWeapon
 * @author Stefan_Martin
 */
public abstract class Skill {
  
    private boolean hasMetConditions = false;
    private boolean isMultipleTurns = false;
    private boolean turnsActive = false;
    private int conditionCounter;
    private int requiredConditionCounter;
    private int turnsLeft;
    

    private String skillDescription;
    private String skillConditionDescription;
    private String activeSkillResultDescription;

    /**
     * Constructor for the Skill class
     * @param skillDescription string detailing a brief description of the skill
     * @param skillConditionDescription string detailing a brief description of the activation requirements of a skill
     */
    public Skill(String skillDescription, String skillConditionDescription){

        this.skillDescription = skillDescription;
        this.skillConditionDescription = skillConditionDescription;

    }

    /**
     * Setter method to indicate the state of the weapon's skill activation.
     * @param condition boolean to set the skill activation to either true or false
     */
    public void setHasMetConditions(boolean condition){

        hasMetConditions = condition;

    }

    /**
     * Setter method to set the conditionCount of the skill.
     * A skill's condition count is a counter for actions like turns or attacks.
     * @param conditionCounter amount to set the conditionCounter
     */
    public void setConditionCounter(int conditionCounter){

        this.conditionCounter = conditionCounter;

    }

    /**
     * Setter method to set the RequiredConditionCount of the skill.
     * A skill's required condition count is the value the condition count must reach before a skill becomes available to use.
     * @param conditionCounter value condition count must reach
     */
    public void setRequiredConditionCounter(int conditionCounter){

        requiredConditionCounter = conditionCounter;

    }

    /**
     * Setter method to set the text to be displayed when the skill is activated.
     * @param description text to be displayed upon the skill's activation.
     */
    public void setActiveSkillResultDescription(String description){

        activeSkillResultDescription = description;

    }

    /**
     * Setter method to set whether the skill lasts for multiple turns or not.
     * This is assumed false and must be changed to otherwise in the subclass's constructor.
     * @param isMultipleTurns boolean to indicate whether a skill's effect will last for multiple terms.
     */
    public void setIsMultipleTurns(boolean isMultipleTurns){

        this.isMultipleTurns = isMultipleTurns;

    }

    /**
     * Setter method to indicate if the skill is active or not
     * This is assumed false and must be changed to otherwise in the activateSkill method.
     * @param turnsActive boolean to indicate whether a skill's effect is active or not
     */
    public void setTurnsActive(boolean turnsActive){

        this.turnsActive = turnsActive;

    }

    /**
     * Setter method to set the amount of turns left where a skill's effects remain active
     * @param turnsLeft amount of turns left where a skill's effects remain active
     */
    public void setTurnsLeft(int turnsLeft){

        this.turnsLeft = turnsLeft;

    }

    /**
     * Getter method to indicate whether a skill is available to use or not
     * @return boolean on whether the skill can be used or not
     */
    public boolean getHasMetConditions(){

        return hasMetConditions;

    }

    /**
     * Getter method to return the current condition count of the skill
     * @return condition count value
     */
    public int getConditionCounter(){

        return conditionCounter;
        
    }

    /**
     * Getter method to return the required condition count of the skill
     * @return required condition count value
     */
    public int getRequiredConditionCounter(){

        return requiredConditionCounter;

    }

    /**
     * Getter method to return the skill's description
     * @return skill description
     */
    public String getskillDescription(){

        return skillDescription;

    }

    /**
     * Getter method to return the skill's condition description
     * @return skill condition description
     */
    public String getskillConditionDescription(){

        return skillConditionDescription;
        
    }

    /**
     * Getter method to return the skill's result description
     * @return skill result description
     */
    public String getActiveSkillResultDescription(){

        return activeSkillResultDescription;

    }

    /**
     * Getter method to return whether a skill's effects are active or not
     * @return boolean on whether a skill's effects are currently active or not
     */
    public boolean getIsMultipleTurns(){

        return isMultipleTurns;
        
    }

    /**
     * Getter method to return whether a skill's effects are active or not
     * @return boolean on whether a skill's effects are currently active or not
     */
    public boolean getTurnsActive(){

        return turnsActive;

    }

    /**
     * Getter method to return the remaining amount of turns a skill's effects will remain active
     * @return turns a skill's effect will remain active
     */
    public int getTurnsLeft(){

        return turnsLeft;

    }

    /**
     * Abstract method to be implemented by subclasses, generates the text to be displayed when the skill is used
     * @param entity entity object that has the skill equipped
     * @param target entity object that is the target of the skill
     */
    public abstract void createActiveSkillResultDescription(Entity entity, Entity target);

    /**
     * Abstract method to be implemented by subclasses, checks if the skill's activation condition/s have been met
     * @param entity entity object that has the skill equipped
     * @param target entity object that is the target of the skill
     */
    public abstract void checkSkillConditionCounter(Entity entity, Entity target);

    /**
     * Abstract method to be implemented by subclasses, activates the skill
     * Skill activation can happen any amount of turns after it has been made available to use
     * @param entity entity object using the skill
     * @param target entity object that is the target of the skill
     */
    public abstract void activateSkill(Entity entity, Entity target);

    /**
     * Abstract method to be implemented by subclasses, deactivates the skill
     * Skill deactivation happens either right after a skill is used or it has elapsed its active turn time
     * @param entity entity object using the skill
     * @param target entity object that is the target of the skill
     */
    public abstract void deactivateSkill(Entity entity, Entity target);


}
