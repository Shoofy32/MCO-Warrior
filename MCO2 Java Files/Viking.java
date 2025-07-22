public class Viking extends Enemy{
    
    public Viking(String name, String type, int hitPoints, int attack, int defense, int speed){

        super(name, type, hitPoints, attack, defense, speed, "ADA");
        totalEnemies++; //Increment
        
    }

    public void think(Character target){


        if(getTurnInputAction() == 'A'){

            setLastCharacterAction("Attack");
            attack(target);
            
        }
        else{

            setLastCharacterAction("Defend");
            defend();
            
        }

    }

    
}
