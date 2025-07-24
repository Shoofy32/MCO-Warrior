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
     * @param character character object that has the skill equipped
     * @param target character object that is the target of the skill
     */
    public void createActiveSkillResultDescription(Character character, Character target){

        String result;

        if(target.getTurnInputAction() == 'A')
            result = character.getName() + " parried " + target.getName() + "'s attack! " + (target.getAttack() - target.getDefense()) 
                     + " Damage is reflected back!";
        else
            result = character.getName() + " try to parry but " + target.getName() + " did not attack!";
            
        setActiveSkillResultDescription(result);

    }

    /**
     * Concrete implementation of the <i>checkSkillConditionCounter</i> method
     * The skill activates when a weapon's passive has activated an x amount of times since either the start of the battle or the last time the skill has been used
     * @param character Character object that has the skill equipped
     * @param target Character object that is the target of the skill
     */
    public void checkSkillConditionCounter(Character character, Character target){

        if(character.getWeapon().getAbility().getHasMetConditions())
            setConditionCounter(getConditionCounter() + 1);

        if(getConditionCounter() >= getRequiredConditionCounter()){

            setHasMetConditions(true);
            setConditionCounter(getRequiredConditionCounter());

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

            character.setIsInvulnerable(getHasMetConditions());

            if(target.getTurnInputAction() == 'A'){

                target.setAttack(target.getAttack() + target.getDefense());
                target.attack(target);

            }

            createActiveSkillResultDescription(character, target);

        }

    }

    /**
     * Concrete implementation of the <i>deactivateSkill</i> method
     * The condition count is reset upon skill activation, whether any damage was reflected or not
     * @param character Character object using the skill
     * @param target Character object that is the target of the skill
     */
    public void deactivateSkill(Character character, Character target){

        if(getHasMetConditions() && character.getTurnInputAction() == 'F'){

            target.setAttack(target.getAttack() - target.getDefense());
            setConditionCounter(0);
            setHasMetConditions(false);
            character.setIsInvulnerable(getHasMetConditions());

        }

    }
}
