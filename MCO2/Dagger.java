import javax.swing.ImageIcon;

/**
 * Class file for the Dagger class, this is a subclass of the abstract Weapon class
 * @see Weapon
 * @author Stefan_Martin
 */
public class Dagger extends Weapon{

    /**
     * Constructor for the Dagger class
     * @param name name of the weapon
     * @param attack attack value of the weapon, additive to the character's base attack
     * @param speedPenalty speed penalty of the weapon, this value deducted from the character's base speed
     * @param image image of the Dagger
     */
    public Dagger(String name, int attack, int speedPenalty, ImageIcon image){

        super(name, attack, speedPenalty, image);
        type = "Dagger";
        setAbility(new Dagger_Ability());

    }

}
