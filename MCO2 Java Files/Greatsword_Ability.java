public class Greatsword_Ability extends Ability{

    private boolean abilityIsActive = false;

    public Greatsword_Ability(){

        super("Charging is a 5x multiplier to attack.", 
              " activated Greatsword passive! Their charge attack is multiplied by 5 instead of 3!");

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

        if(getHasMetConditions())
            character.setChargeMultipler(character.getChargeMultiplier() + 2);

    }

    public void deactivateAbility(Character character){

        if(!character.getIsCharging()){
            
            setHasMetConditions(false);
            abilityIsActive = false;
            character.setChargeMultipler(character.getChargeMultiplier() - 2);

        }

    }

}
