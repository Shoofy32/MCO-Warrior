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
     * @param character character object that has the skill equipped
     * @param target character object that is the target of the skill
     */
    public void createActiveSkillResultDescription(Character character, Character target){

        String result = null;

        if(character.getTurnInputAction() == 'F')
            result = character.getName() + " strikes " + target.getName() + " with Dream Slash ("  + character.getLastAttackDealt() + " Damage)!"
                    + " and lifesteals " + lifeStealMultiplier + "x of damage (" + ((int) Math.round(character.getLastAttackDealt() * lifeStealMultiplier))  
                    +  " Hitpoints gained) and gains +" + speedAdditive + " speed for " + baseTurnsForSkill + " turns." ;
        else if(character.getTurnInputAction() != 'F' && getTurnsActive() && getTurnsLeft() != 0)
            result = character.getName() + " has " + (getTurnsLeft()) + " left before Dream Slash speed buff ends.";

        setActiveSkillResultDescription(result);

    }

    /**
     * Concrete implementation of the <i>checkSkillConditionCounter</i> method
     * The skill activates when a user has dealt an x amount of total damage to the enemy
     * @param character Character object that has the skill equipped
     * @param target Character object that is the target of the skill
     */
    public void checkSkillConditionCounter(Character character, Character target){

        if(character.getTurnInputAction() == 'A')
            setConditionCounter(getConditionCounter() + character.getLastAttackDealt());

        if(getConditionCounter() >= getRequiredConditionCounter()){

            setHasMetConditions(true);    
            setConditionCounter(getRequiredConditionCounter());
            setTurnsLeft(baseTurnsForSkill);
            
        }
 

    }

    /**
     * Concrete implementation of the <i>activateSkill</i> method
     * This method only becomes available to use when the skill's condition has been met
     * @param character Character object using the skill
     * @param target Character object that is the target of the skill
     */
    public  void activateSkill(Character character, Character target){

        if(getHasMetConditions()){

            setConditionCounter(0);
            character.setAttack((int) Math.round(character.getAttack() * attackMultiplier));
            character.attack(target);
            character.setAttack((int) Math.round(character.getAttack() / attackMultiplier)); 
            character.setHitPoints(character.getHitPoints() + (int) Math.round(character.getLastAttackDealt() * lifeStealMultiplier));
            setTurnsActive(true);
            character.setSpeed(character.getSpeed() + speedAdditive);
            createActiveSkillResultDescription(character, target);

        }

    }

    /**
     * Concrete implementation of the <i>deactivateSkill</i> method
     * The condition count is reset upon skill activation, however the speed increase will remain for the amount of turns set within the constructor
     * @param character Character object using the skill
     * @param target Character object that is the target of the skill
     */
    public void deactivateSkill(Character character, Character target){

        if(getHasMetConditions() && character.getTurnInputAction() == 'F')
            setHasMetConditions(false);
        else if((getTurnsLeft() == 0 && getTurnsActive())|| character.getHitPoints() == 0 || target.getHitPoints() == 0){

            setConditionCounter(0);
            character.setSpeed(character.getSpeed() - speedAdditive);

        }
        else
            setTurnsLeft(getTurnsLeft() - 1);

        if(getTurnsLeft() == 0 && getTurnsActive())
            setTurnsActive(false);


    }
}
