/**
 * Class file for the DreamSlash_Skill class, this is a subclass of the abstract Skill class
 * <p>
 *     Dream Slash restores the user's HP by a percentage of damage dealt to the enemy while also increasing their speed by a set amount of turns
 * </p>
 * <p>
 *     The HP restored, speed bonus, and duration of the speed bonus are arbitrarily set upon initialization
 * </p>
 * <p>
 *     The skill becomes available to use after the user has dealt a set amount of damage to the enemy
 * </p>
 * @see Skill
 * @author Martin
 */
public class DreamSlash_Skill extends Skill{
  
    private double attackMultiplier;
    private double lifeStealMultiplier;
    private int speedAdditive;
    private int baseTurnsForSkill;

    /**
     * Constructor for the Dream Slash Skill
     * @param attackMultiplier value to multiply attack by when skill is used, all values are assumed valid
     * @param lifeStealMultiplier percentage of damage dealt to be converted into HP, all values are assumed valid
     * @param speedAdditive value to increase speed by, all values are assumed valid
     * @param baseTurnsForSkill amount of turns speed increase will remain active, all values are assumed valid
     * @param requiredConditionCounter amount of damage that needs to be dealt in order for the skill to become available
     */
    public DreamSlash_Skill(double attackMultiplier, double lifeStealMultiplier, 
                            int speedAdditive, int baseTurnsForSkill, int requiredConditionCounter){

        super(attackMultiplier + "x attack that lifesteals " + lifeStealMultiplier + "x of damage dealt with +" + speedAdditive + " speed for " + baseTurnsForSkill + " turns.", 
              "Deal " + requiredConditionCounter + " worth of attack to the enemy.");
        this.attackMultiplier = attackMultiplier;
        this.lifeStealMultiplier = lifeStealMultiplier;
        this.speedAdditive = speedAdditive;
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
            result = entity.getName() + " strikes " + target.getName() + " with Dream Slash ("  + entity.getLastAttackDealt() + " Damage)!"
                    + " and lifesteals " + lifeStealMultiplier + "x of damage (" + ((int) Math.round(entity.getLastAttackDealt() * lifeStealMultiplier))
                    +  " Hitpoints gained) and gains +" + speedAdditive + " speed for " + baseTurnsForSkill + " turns." ;
        else if(entity.getTurnInputAction() != 'F' && getTurnsActive() && getTurnsLeft() != 0)
            result = entity.getName() + " has " + (getTurnsLeft()) + " left before Dream Slash speed buff ends.";

        setActiveSkillResultDescription(result);

    }

    /**
     * Concrete implementation of the <i>checkSkillConditionCounter</i> method
     * The skill activates when a user has dealt an x amount of total damage to the enemy
     * @param entity entity object that has the skill equipped
     * @param target entity object that is the target of the skill
     */
    public void checkSkillConditionCounter(Entity entity, Entity target){

        if(entity.getTurnInputAction() == 'A')
            setConditionCounter(getConditionCounter() + entity.getLastAttackDealt());

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

            setConditionCounter(0);
            entity.setAttack((int) Math.round(entity.getAttack() * attackMultiplier));
            entity.attack(target);
            entity.setAttack((int) Math.round(entity.getAttack() / attackMultiplier));
            entity.setHitPoints(entity.getHitPoints() + (int) Math.round(entity.getLastAttackDealt() * lifeStealMultiplier));
            setTurnsActive(true);
            entity.setSpeed(entity.getSpeed() + speedAdditive);
            createActiveSkillResultDescription(entity, target);

        }

    }

    /**
     * Concrete implementation of the <i>deactivateSkill</i> method
     * The condition count is reset upon skill activation, however the speed increase will remain for the amount of turns set within the constructor
     * @param entity entity object using the skill
     * @param target entity object that is the target of the skill
     */
    public void deactivateSkill(Entity entity, Entity target){

        if(getHasMetConditions() && entity.getTurnInputAction() == 'F')
            setHasMetConditions(false);
        else if((getTurnsLeft() == 0 && getTurnsActive())|| entity.getHitPoints() == 0 || target.getHitPoints() == 0){

            setConditionCounter(0);
            entity.setSpeed(entity.getSpeed() - speedAdditive);

        }
        else
            setTurnsLeft(getTurnsLeft() - 1);

        if(getTurnsLeft() == 0 && getTurnsActive())
            setTurnsActive(false);


    }
}
