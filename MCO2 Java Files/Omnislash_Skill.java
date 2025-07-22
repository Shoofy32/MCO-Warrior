public class Omnislash_Skill extends Skill{
    
    private int multiAttackTimes;
    private double multiAttackMultiplier;

    public Omnislash_Skill(int multiAttackTimes, double multiAttackMultiplier, int requiredConditionCounter){

        super("Hit " + multiAttackTimes + " times dealing " + multiAttackMultiplier + "x of character's attack per hit.", 
              requiredConditionCounter + " turns have elapsed.");

        this.multiAttackTimes = multiAttackTimes;
        this.multiAttackMultiplier = multiAttackMultiplier;
        setRequiredConditionCounter(requiredConditionCounter);
        

    }

    public void createActiveSkillResultDescription(Character character, Character target){

        String result;

        result = character.getName() + " uses Omnislash against " + target.getName() + "!";

        setActiveSkillResultDescription(result);

    }


    public void checkSkillConditionCounter(Character character, Character target){

        setConditionCounter(getConditionCounter() + 1);

        if(getConditionCounter() >= getRequiredConditionCounter()){

            setHasMetConditions(true);
            setConditionCounter(getRequiredConditionCounter());

        }


    }


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

    public void deactivateSkill(Character character, Character target){

        if(getHasMetConditions() && character.getTurnInputAction() == 'F'){

            setConditionCounter(0);
            setHasMetConditions(false);
            character.setAttack((int) Math.round(character.getAttack() / multiAttackMultiplier));

        }


    }

}
