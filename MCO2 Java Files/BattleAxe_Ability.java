public class BattleAxe_Ability extends Ability{
    
    private boolean abilityIsActive = false;

    public BattleAxe_Ability(){

        super("When charging, gain 5 speed and 5 attack in the next turn.", 
              " activated passive! Gain 5 Speed and Attack in their next turn!");

    }

    
    public void checkAbilityCondition(Character character){

        if(character.getTurnInputAction() == 'C' && !abilityIsActive){

            setHasMetConditions(true);
            abilityIsActive = true;
    
        }
        else
            setHasMetConditions(false);
            
    }

    public void activateAbility(Character character, Character target){

        checkAbilityCondition(character);

        if(getHasMetConditions()){

            character.setAttack(character.getAttack() + 5);
            character.setSpeed(character.getSpeed() + 5);

        }

    }

    public void deactivateAbility(Character character){

        if(!character.getIsCharging()){
            
            setHasMetConditions(false);
            abilityIsActive = false;
            character.setAttack(character.getAttack() - 5);
            character.setSpeed(character.getSpeed() - 5);

        }

    }

}
