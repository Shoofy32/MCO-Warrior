public class Parry_Skill extends Skill{
    
    
    public Parry_Skill(int requiredConditionCounter){

        super("Reflect a direct attack back dealt by enemy to themselves (Ignores Defense).", 
              "Activate passive " + requiredConditionCounter + " time/s." );

        setRequiredConditionCounter(requiredConditionCounter);        

    }

    public void createActiveSkillResultDescription(Character character, Character target){

        String result;

        if(target.getTurnInputAction() == 'A')
            result = character.getName() + " parried " + target.getName() + "'s attack! " + (target.getAttack() - target.getDefense()) 
                     + " Damage is reflected back!";
        else
            result = character.getName() + " try to parry but " + target.getName() + " did not attack!";
            
        setActiveSkillResultDescription(result);

    }


    public void checkSkillConditionCounter(Character character, Character target){

        if(character.getWeapon().getAbility().getHasMetConditions())
            setConditionCounter(getConditionCounter() + 1);

        if(getConditionCounter() >= getRequiredConditionCounter()){

            setHasMetConditions(true);
            setConditionCounter(getRequiredConditionCounter());

        }

    }


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

    public void deactivateSkill(Character character, Character target){

        if(getHasMetConditions() && character.getTurnInputAction() == 'F'){

            target.setAttack(target.getAttack() - target.getDefense());
            setConditionCounter(0);
            setHasMetConditions(false);
            character.setIsInvulnerable(getHasMetConditions());

        }

    }
}
