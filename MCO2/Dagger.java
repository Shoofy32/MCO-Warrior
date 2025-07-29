import javax.swing.ImageIcon;

public class Dagger extends Weapon{
    

    public Dagger(String name, int attack, int speedPenalty, ImageIcon image){

        super(name, attack, speedPenalty, image);
        type = "Dagger";
        setAbility(new Dagger_Ability());

    }

}
