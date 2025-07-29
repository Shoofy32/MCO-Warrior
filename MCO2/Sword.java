import javax.swing.ImageIcon;

public class Sword extends Weapon{
    

    public Sword(String name, int attack, int speedPenalty, ImageIcon image){

        super(name, attack, speedPenalty, image); 
        type = "Sword";
        setAbility(new Sword_Ability());

    }

}
