public class AdrenalineRush_Skill extends Skill{
    
    private double defenseMultiplier;
    private int baseTurnsForSkill;

    public AdrenalineRush_Skill(double defenseMultiplier, int baseTurnsForSkill, int requiredConditionCounter){

        super("Defense increased by " + defenseMultiplier + "x for " + baseTurnsForSkill + " turns.",
              "Attack continuously for " + requiredConditionCounter + " turns.");
        this.defenseMultiplier = defenseMultiplier;
        this.baseTurnsForSkill = baseTurnsForSkill;
        setIsMultipleTurns(true);
        setTurnsLeft(baseTurnsForSkill);
        setRequiredConditionCounter(requiredConditionCounter);
        
    }

    public void createActiveSkillResultDescription(Character character, Character target){

        String result = null;

        if(character.getTurnInputAction() == 'F')
            result = character.getName() + " is in an Andrenaline Rush! " + character.getName() + 
                    " defense increased by " + defenseMultiplier + "x.";
        else if(character.getTurnInputAction() != 'F' && getTurnsActive() && getTurnsLeft() != 0)
            result = character.getName() + " has " + (getTurnsLeft()) + " left before Adrenaline Rush ends.";

        setActiveSkillResultDescription(result);

    }


    public void checkSkillConditionCounter(Character character, Character target){

        if(character.getTurnInputAction() == 'A' && !getTurnsActive())
            setConditionCounter(getConditionCounter() + 1);
        else
            setConditionCounter(0);

        if(getConditionCounter() >= getRequiredConditionCounter()){

            setHasMetConditions(true);  
            setConditionCounter(getRequiredConditionCounter());
            setTurnsLeft(baseTurnsForSkill);
            
        }


    }


    public  void activateSkill(Character character, Character target){

        if(getHasMetConditions()){

            setTurnsActive(true);
            character.setDefense((int) Math.round(character.getDefense() * defenseMultiplier));
            createActiveSkillResultDescription(character, target);

        }

    }

    public void deactivateSkill(Character character, Character target){

        if(getHasMetConditions() && character.getTurnInputAction() == 'F'){

            setHasMetConditions(false);

        }
        else if((getTurnsLeft() == 0 && getTurnsActive()) || character.getHitPoints() == 0 || target.getHitPoints() == 0){

            setConditionCounter(0);
            character.setDefense((int) Math.round(character.getDefense() / defenseMultiplier)); 
                
        }
        else
            setTurnsLeft(getTurnsLeft() - 1);

        if(getTurnsLeft() == 0 && getTurnsActive())
            setTurnsActive(false);

    }

}
