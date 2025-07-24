/**
 * Class file for the Omnislash_Skill class, this is a subclass of the abstract Skill class
 * <p>
 *     Omnislash is a multi hit skill wherein each hit deals a percentage of the user's damage, it becomes available to use after a set amount of turns have elapsed.
 * </p>
 * <p>
 *     The amount of hits, damage percentage, and amount of turns that need to have elapsed are all arbitrarily set upon initialization.
 * </p>
 * @see Skill
 * @author Stefan
 */
    public class Omnislash_Skill extends Skill{
    
    private int multiAttackTimes;
    private double multiAttackMultiplier;

    /**
     * Constructor for the Omnislash skill
     * @param multiAttackTimes amount of times the skill will attack in a single turn, all values are assumed valid
     * @param multiAttackMultiplier percentage of damage each hit will deal, all values are assumed valid
     * @param requiredConditionCounter amount of turns that need to pass before skill becomes available, all values are assumed valid
     */
    public Omnislash_Skill(int multiAttackTimes, double multiAttackMultiplier, int requiredConditionCounter){

        super("Hit " + multiAttackTimes + " times dealing " + multiAttackMultiplier + "x of character's attack per hit.", 
              requiredConditionCounter + " turns have elapsed.");

        this.multiAttackTimes = multiAttackTimes;
        this.multiAttackMultiplier = multiAttackMultiplier;
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

        result = character.getName() + " uses Omnislash against " + target.getName() + "!";

        setActiveSkillResultDescription(result);

    }

    /**
     * Concrete implementation of the <i>checkSkillConditionCounter</i> method
     * The skill activates when an x amount of turns have passed since either the start of the battle or the last time the skill has been used
     * @param character Character object that has the skill equipped
     * @param target Character object that is the target of the skill
     */
    public void checkSkillConditionCounter(Character character, Character target){

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
    public void activateSkill(Character character, Character target){

        if(getHasMetConditions()){

            createActiveSkillResultDescription(character, target);
            character.setAttack((int) Math.round((character.getAttack() * multiAttackMultiplier)));
            character.setTimesToAttack(multiAttackTimes);
            character.setTurnInputAction('A');
            character.think(target);
            character.setTurnInputAction('F');

        }

    }

    /**
     * Concrete implementation of the <i>deactivateSkill</i> method
     * The condition count is reset upon skill activation
     * @param character Character object using the skill
     * @param target Character object that is the target of the skill
     */
    public void deactivateSkill(Character character, Character target){

        if(getHasMetConditions() && character.getTurnInputAction() == 'F'){

            setConditionCounter(0);
            setHasMetConditions(false);
            character.setAttack((int) Math.round(character.getAttack() / multiAttackMultiplier));

        }


    }

}
