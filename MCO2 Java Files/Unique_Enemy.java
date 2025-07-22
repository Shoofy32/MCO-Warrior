public class Unique_Enemy extends Enemy{
    
    private int counter = 0;
    private String pattern;
    char currentPatternTurn;

    public Unique_Enemy(String name, String type, int hitPoints, int attack, int defense, int speed, String pattern, Weapon weapon, Consumable consumable){

        super(name, type, hitPoints, attack, defense, speed, pattern);
        this.pattern = pattern;
        totalEnemies++; //Increment
        equipWeapon(weapon);
        equipConsumable(consumable);

    }

    public void think(Character target){

        switch(getTurnInputAction()){

            case 'A':
                
                setLastCharacterAction("Attack");
                attack(target);
                break;

            case 'D':

                setLastCharacterAction("Defend");
                defend();
                break;

            case 'C':

                setLastCharacterAction("Charge");
                charge();
                break;

            case 'U':

                setLastCharacterAction("Consume");
                setTurnInputAction(currentPatternTurn);
                consume(target);          

                break;

            case 'F':

                setLastCharacterAction("Skill");
                useSkill(target);

                break;
            
            default:
                System.out.printf("\nCurrent choice does not exist within the given.\n");

        }

            
        if(counter == pattern.length())
            counter = 0;
        else
            counter++;

    }


}
