/**
 * Class file for the AdrenalineRush_Skill class, this is a subclass of the abstract SKill class
 * <p>
 *     Adrenaline rush increases the user's defense after attacking continuously for a set amount of turns.
 * </p>
 * <p>
 *     The amount defense is increased and the amount of turns it remains active are arbitrarily set upon initialization.
 * </p>
 * @see Skill
 * @author Martin
 */
public class AdrenalineRush_Skill extends Skill{
    
    private double defenseMultiplier;
    private int baseTurnsForSkill;

    /**
     * Constructor for the Adrenaline Rush skill
     * @param defenseMultiplier Multiplier to increase defense by when skill becomes active, assumed all values are valid
     * @param baseTurnsForSkill Set amount of turns defense increase will remain active, assumed all values are valid
     * @param requiredConditionCounter Set amount of attacks that need to be done continuously in order to activate skill, assumed all values are valid
     */
    public AdrenalineRush_Skill(double defenseMultiplier, int baseTurnsForSkill, int requiredConditionCounter){

        super("Defense increased by " + defenseMultiplier + "x for " + baseTurnsForSkill + " turns.",
              "Attack continuously for " + requiredConditionCounter + " turns.");
        this.defenseMultiplier = defenseMultiplier;
        this.baseTurnsForSkill = baseTurnsForSkill;
        setIsMultipleTurns(true);
        setTurnsLeft(baseTurnsForSkill);
        setRequiredConditionCounter(requiredConditionCounter);
        
    }

    /**
     * Concrete implementation of the <i>createActiveSkillResult Description</i> method
     * Generates the text to be displayed when the user activates the skill
     * @param entity entity object that has the skill equipped
     * @param target entity object that is the target of the skill
     */
    public void createActiveSkillResultDescription(Entity entity, Entity target){

        String result = null;

        if(entity.getTurnInputAction() == 'F')
            result = entity.getName() + " is in an Andrenaline Rush! " + entity.getName() +
                    " defense increased by " + defenseMultiplier + "x.";
        else if(entity.getTurnInputAction() != 'F' && getTurnsActive() && getTurnsLeft() != 0)
            result = entity.getName() + " has " + (getTurnsLeft()) + " left before Adrenaline Rush ends.";

        setActiveSkillResultDescription(result);

    }

    /**
     * Concrete implementation of the <i>checkSkillConditionCounter</i> method
     * The skill activates when a user chooses to attack x amount of times continuously
     * The counter is reset when they choose to do another action besides attacking e.g. defending, charging, or using a consumable
     * @param entity entity object that has the skill equipped
     * @param target entity object that is the target of the skill
     */
    public void checkSkillConditionCounter(Entity entity, Entity target){

        if(entity.getTurnInputAction() == 'A' && !getTurnsActive())
            setConditionCounter(getConditionCounter() + 1);
        else
            setConditionCounter(0);

        if(getConditionCounter() >= getRequiredConditionCounter()){

            setHasMetConditions(true);  
            setConditionCounter(getRequiredConditionCounter());
            setTurnsLeft(baseTurnsForSkill);
            
        }


    }

    /**
     * Concrete implementation of the <i>activateSkill</i> method
     * This method only becomes available to use when the skill's condition has been met
     * @param entity entity object using the skill
     * @param target entity object that is the target of the skill
     */
    public  void activateSkill(Entity entity, Entity target){

        if(getHasMetConditions()){

            setTurnsActive(true);
            entity.setDefense((int) Math.round(entity.getDefense() * defenseMultiplier));
            createActiveSkillResultDescription(entity, target);

        }

    }

    /**
     * Concrete implementation of the <i>deactivateSkill</i> method
     * The condition count is reset upon skill activation, however the defense increase will remain for the amount of turns set within the constructor
     * @param entity entity object using the skill
     * @param target entity object that is the target of the skill
     */
    public void deactivateSkill(Entity entity, Entity target){

        if(getHasMetConditions() && entity.getTurnInputAction() == 'F'){

            setHasMetConditions(false);

        }
        else if((getTurnsLeft() == 0 && getTurnsActive()) || entity.getHitPoints() == 0 || target.getHitPoints() == 0){

            setConditionCounter(0);
            entity.setDefense((int) Math.round(entity.getDefense() / defenseMultiplier));
                
        }
        else
            setTurnsLeft(getTurnsLeft() - 1);

        if(getTurnsLeft() == 0 && getTurnsActive())
            setTurnsActive(false);

    }

}
