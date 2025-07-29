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



    public Skill(String skillDescription, String skillConditionDescription){

        this.skillDescription = skillDescription;
        this.skillConditionDescription = skillConditionDescription;

    }
    
    public void setHasMetConditions(boolean condition){

        hasMetConditions = condition;

    }

    public void setConditionCounter(int conditionCounter){

        this.conditionCounter = conditionCounter;

    }

    public void setRequiredConditionCounter(int conditionCounter){

        requiredConditionCounter = conditionCounter;

    }

    public void setActiveSkillResultDescription(String description){

        activeSkillResultDescription = description;

    }

    public void setIsMultipleTurns(boolean isMultipleTurns){

        this.isMultipleTurns = isMultipleTurns;

    }

    public void setTurnsActive(boolean turnsActive){

        this.turnsActive = turnsActive;

    }

    public void setTurnsLeft(int turnsLeft){

        this.turnsLeft = turnsLeft;

    }

    public boolean getHasMetConditions(){

        return hasMetConditions;

    }

    public int getConditionCounter(){

        return conditionCounter;
        
    }

    public int getRequiredConditionCounter(){

        return requiredConditionCounter;

    }

    public String getskillDescription(){

        return skillDescription;

    }

    public String getskillConditionDescription(){

        return skillConditionDescription;
        
    }

    public String getActiveSkillResultDescription(){

        return activeSkillResultDescription;

    }

    public boolean getIsMultipleTurns(){

        return isMultipleTurns;
        
    }

    public boolean getTurnsActive(){

        return turnsActive;

    }


    public int getTurnsLeft(){

        return turnsLeft;

    }
    

    public abstract void createActiveSkillResultDescription(Character character, Character target);
    public abstract void checkSkillConditionCounter(Character character, Character target);
    public abstract void activateSkill(Character character, Character target);
    public abstract void deactivateSkill(Character character, Character target);


}
