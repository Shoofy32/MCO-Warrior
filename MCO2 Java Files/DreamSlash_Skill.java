public class DreamSlash_Skill extends Skill{
  
    private double attackMultiplier;
    private double lifeStealMultiplier;
    private int speedAdditive;
    private int baseTurnsForSkill;

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


    public void checkSkillConditionCounter(Character character, Character target){

        if(character.getTurnInputAction() == 'A')
            setConditionCounter(getConditionCounter() + character.getLastAttackDealt());

        if(getConditionCounter() >= getRequiredConditionCounter()){

            setHasMetConditions(true);    
            setConditionCounter(getRequiredConditionCounter());
            setTurnsLeft(baseTurnsForSkill);
            
        }
 

    }


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
