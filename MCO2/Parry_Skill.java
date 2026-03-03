/**
 * Class file for the Parry_Skill class, this is a subclass of the abstract Skill class
 * <p>
 *     Parry reflects the damage an enemy would've dealt to the user back to the enemy, this damage ignores defense
 * </p>
 * <p>
 *     However if the enemy did not attack the turn parry was used then it is effectively wasted
 * </p>
 * <p>
 *     This skill only becomes available to use once the weapon's passive has triggered an x amount of times
 * </p>
 * @see Skill
 * @author Stefan
 */
public class Parry_Skill extends Skill{

    /**
     * Constructor for the parry skill class
     * @param requiredConditionCounter amount of times the passive must trigger in order to activate the skill, all values are assumed valid
     */
    public Parry_Skill(int requiredConditionCounter){

        super("Reflect a direct attack back dealt by enemy to themselves (Ignores Defense).", 
              "Activate passive " + requiredConditionCounter + " time/s." );

        setRequiredConditionCounter(requiredConditionCounter);        

    }

    /**
     * Concrete implementation of the <i>createActiveSkillResult Description</i> method
     * Generates the text to be displayed when the user activates the skill
     * @param entity entity object that has the skill equipped
     * @param target entity object that is the target of the skill
     */
    public void createActiveSkillResultDescription(Entity entity, Entity target){

        String result;

        if(target.getTurnInputAction() == 'A')
            result = entity.getName() + " parried " + target.getName() + "'s attack! " + (target.getAttack() - target.getDefense())
                     + " Damage is reflected back!";
        else
            result = entity.getName() + " tries to parry but " + target.getName() + " did not attack!";
            
        setActiveSkillResultDescription(result);

    }

    /**
     * Concrete implementation of the <i>checkSkillConditionCounter</i> method
     * The skill activates when a weapon's passive has activated an x amount of times since either the start of the battle or the last time the skill has been used
     * @param entity entity object that has the skill equipped
     * @param target entity object that is the target of the skill
     */
    public void checkSkillConditionCounter(Entity entity, Entity target){

        if(entity.getWeapon().getAbility().getHasMetConditions())
            setConditionCounter(getConditionCounter() + 1);

        if(getConditionCounter() >= getRequiredConditionCounter()){

            setHasMetConditions(true);
            setConditionCounter(getRequiredConditionCounter());

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

            entity.setIsInvulnerable(getHasMetConditions());

            if(target.getTurnInputAction() == 'A' || target.getTurnInputAction() == 'F'){


                target.setAttack(target.getAttack() + target.getDefense());
                target.attack(target);

            }

            createActiveSkillResultDescription(entity, target);

        }

    }

    /**
     * Concrete implementation of the <i>deactivateSkill</i> method
     * The condition count is reset upon skill activation, whether any damage was reflected or not
     * @param entity entity object using the skill
     * @param target entity object that is the target of the skill
     */
    public void deactivateSkill(Entity entity, Entity target){

        if(getHasMetConditions() && entity.getTurnInputAction() == 'F'){

            target.setAttack(target.getAttack() - target.getDefense());
            setConditionCounter(0);
            setHasMetConditions(false);
            entity.setIsInvulnerable(getHasMetConditions());

        }

    }
}
