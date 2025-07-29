import javax.swing.ImageIcon;

public class Minotaur extends Enemy{
    
    private int counter = 0;

    public Minotaur(String name, String type, ImageIcon image, int hitPoints, int attack, int defense, int speed){

        super(name, type, image, hitPoints, attack, defense, speed, "ACA");
        totalEnemies++; //Increment
        
    }

    public void think(Character target){

        if(counter == 0 || counter == 2){

            setLastCharacterAction("Attack");
            attack(target);
        }
        else{

            setLastCharacterAction("Charge");
            charge();

        }

            
        if(counter == 2)
            counter = 0;
        else
            counter++;

    }


}
