public abstract class Ability {

    private boolean hasMetConditions = false;

    private String abilityDescription;
    private String abilityDisplayDescription;



    public Ability(String abilityDescription, String abilityDisplayDescription){

        this.abilityDescription = abilityDescription;
        this.abilityDisplayDescription = abilityDisplayDescription;

    }
    
    public void setHasMetConditions(boolean condition){

        hasMetConditions = condition;

    }

    public boolean getHasMetConditions(){

        return hasMetConditions;

    }

    public String getAbilityDecsription(){

        return abilityDescription;

    }

    public String getAbilityDisplayDescription(){

        return abilityDisplayDescription;
        
    }

    public abstract void checkAbilityCondition(Character character);

    public abstract void activateAbility(Character character, Character target);

    public abstract void deactivateAbility(Character character);



}
