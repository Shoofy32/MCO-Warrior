/**
 * Class file for the Sword class, this is a subclass of the abstract Weapon class
 * @see Weapon
 * @author Stefan_Martin
 */
public class Sword extends Weapon{

    /**
     * Constructor for the Sword class
     * @param name name of the weapon
     * @param attack attack value of the weapon, additive to the character's base attack
     * @param speedPenalty speed penalty of the weapon, this value deducted from the character's base speed
     */
    public Sword(String name, int attack, int speedPenalty){

        super(name, attack, speedPenalty); 
        type = "Sword";
        setAbility(new Sword_Ability());

    }

}
