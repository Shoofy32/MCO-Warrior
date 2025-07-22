public class Sword_Ability extends Ability{
    
    public Sword_Ability(){

        super("When attacking, gain an additional +10 attack.", 
              " activated Sword passive! Their attack deals 10 more attack!");

    }


    public void checkAbilityCondition(Character character){

        if(character.getTurnInputAction() == 'A')
            setHasMetConditions(true);

    }

    public void activateAbility(Character character, Character target){

        checkAbilityCondition(character);

        if(getHasMetConditions())
            character.setAttack(character.getAttack() + 10);

    }

    public void deactivateAbility(Character character){

        setHasMetConditions(false);

        if(character.getTurnInputAction() == 'A')
            character.setAttack(character.getAttack() - 10);

    }

}
