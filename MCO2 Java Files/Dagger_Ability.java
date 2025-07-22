public class Dagger_Ability extends Ability{
    
    int numOfDefend = 0;

    public Dagger_Ability(){

        super("When defending, every other defend will become a 100% evade.", 
              " activated Dagger passive! Their defend turns into an evade!");

    }


    public void checkAbilityCondition(Character character){

        if(character.getTurnInputAction() == 'D')
            numOfDefend++;

        if(numOfDefend == 2){

            setHasMetConditions(true);
            numOfDefend = 0;

        }

    }

    public void activateAbility(Character character, Character target){

        checkAbilityCondition(character);
        
        if(getHasMetConditions())
            character.setIsInvulnerable(getHasMetConditions());

    }

    public void deactivateAbility(Character character){

        if(character.getIsInvulnerable()){

            setHasMetConditions(false);
            character.setIsInvulnerable(getHasMetConditions());

        }


    }
}
