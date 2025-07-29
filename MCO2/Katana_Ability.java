public class Katana_Ability extends Ability{

    int numOfAttack = 0;

    public Katana_Ability(){

        super("Deal an additional attack (affected by buffs) every other attack.", 
              " activated Katana passive! They attack an additional time!");

    }


    public void checkAbilityCondition(Character character){

        if(character.getTurnInputAction() == 'A')
            numOfAttack++;

        if(numOfAttack == 2){

            setHasMetConditions(true);
            numOfAttack = 0;

        }

    }

    public void activateAbility(Character character, Character target){

        checkAbilityCondition(character);
        
        if(getHasMetConditions())
            character.setTimesToAttack(character.getTimesToAttack() + 1);


    }

    public void deactivateAbility(Character character){

        setHasMetConditions(false);
        character.setTimesToAttack(character.getTimesToAttack() - 1);

    }

}
