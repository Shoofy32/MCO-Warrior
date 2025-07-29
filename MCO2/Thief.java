import javax.swing.ImageIcon;

public class Thief extends Enemy{
   
    public Thief(String name, String type, ImageIcon image, int hitPoints, int attack, int defense, int speed){

        super(name, type, image, hitPoints, attack, defense, speed, "A");
        totalEnemies++; //Increment
        
    }

    public void think(Character target){

        setLastCharacterAction("Attack");
        attack(target);

    }


}
