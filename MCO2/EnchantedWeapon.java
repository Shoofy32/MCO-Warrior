import javax.swing.ImageIcon;

/**
 * Class file for the EnchantedWeapon class, this is a subclass of the abstract Weapon class
 * <p>
 *     This is a special bonus weapon subclass that can make use of special active abilities/skills.
 * </p>
 * @see Weapon
 * @see Skill
 * @author Stefan_Martin
 */
public class EnchantedWeapon extends Weapon{
    
    private Skill weaponSkill;

    /**
     * Constructor for the EnchantedWeapon class
     * @param name name of the weapon, all names are assumed valid
     * @param type type of the weapon, all types are assumed valid
     * @param attack attack value of the weapon, additive to the character's base attack
     * @param speedPenalty speed penalty of the weapon, this value deducted from the character's base speed
     * @param image image of the enchanted weapon
     * @param skillName skill to be applied/equipped to the weapon, assumed all ability names are valid
     */
    public EnchantedWeapon(String name, String type, int attack, int speedPenalty, ImageIcon image, String skillName){

        super(name, attack, speedPenalty, image);

        this.type = type;

        switch(type){

            case "Dagger": setAbility(new Dagger_Ability()); break;
            case "Sword": setAbility(new Sword_Ability()); break;
            case "BattleAxe": setAbility(new BattleAxe_Ability()); break;
            case "Greatsword": setAbility(new Greatsword_Ability()); break;
            case "Katana": setAbility(new Katana_Ability()); break;
            case "Fists": setAbility(new Fists_Ability());
                
        }

        switch(skillName){

            case "Parry": weaponSkill = new Parry_Skill(1); break;
            case "OmniSlash": weaponSkill = new Omnislash_Skill(3, 0.75, 3); break;
            case "AdrenalineRush": weaponSkill = new AdrenalineRush_Skill(1.5, 3, 3); break;
            case "DreamSlash": weaponSkill = new DreamSlash_Skill(2.5, 0.85, 10, 2, 75);

        }

    }

    /**
     * Getter method for the weapon type
     * @return the type of the weapon object
     */
    public String getType(){

        return type;

    }

    /**
     * Getter method for the weapon skill
     * @return the type of the weapon skill
     */
    public Skill getWeaponSkill(){

        return weaponSkill;

    }

}
