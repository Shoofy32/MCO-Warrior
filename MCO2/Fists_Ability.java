public class Fists_Ability extends Ability{

    int numOfAttack = 0;
    int counter = 0;
    int enemyHealth;

    public Fists_Ability(){

        super("5 stacking attack for each consecutive attack (Resets if non-attack action).", 
              " activated Fists passive! They gain 5 more Attack!");

    }


    public void checkAbilityCondition(Character character){

        if(character.getTurnInputAction() == 'A'){

            numOfAttack++;
            counter++;

        }

        else
            numOfAttack = 0;

        if(numOfAttack > 0)
            setHasMetConditions(true);     
        else{

            setHasMetConditions(false);        
            character.setAttack(character.getAttack() - (5 * counter));
            counter = 0;   

        }

    }

    public void activateAbility(Character character, Character target){

        enemyHealth = target.getHitPoints();

        checkAbilityCondition(character);
        
        if(getHasMetConditions())
            character.setAttack(character.getAttack() + 5);

    }

    public void deactivateAbility(Character character){

        if(character.getHitPoints() == 0 || enemyHealth == 0){

            counter = 0;
            setHasMetConditions(false);

        }


    }

}
