import javax.swing.ImageIcon;

/**
 * Class file for the BattleAxe class, this is a subclass of the abstract Weapon class
 * @see Weapon
 * @author Stefan_Martin
 */
public class BattleAxe extends Weapon{

    /**
     * Constructor for the BattleAxe class
     * @param name name of the weapon
     * @param attack attack value of the weapon, additive to the character's base attack
     * @param speedPenalty speed penalty of the weapon, this value deducted from the character's base speed
     * @param image image of the BattleAxe
     */
    public BattleAxe(String name, int attack, int speedPenalty, ImageIcon image){

        super(name, attack, speedPenalty, image);
        type = "Battle Axe";
        setAbility(new BattleAxe_Ability());

    }

}
