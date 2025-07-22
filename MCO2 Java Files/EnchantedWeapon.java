public class EnchantedWeapon extends Weapon{
    
    private Skill weaponSkill;

    public EnchantedWeapon(String name, String type, int attack, int speedPenalty, String abilityName){

        super(name, attack, speedPenalty);

        this.type = type;

        switch(type){

            case "Dagger": setAbility(new Dagger_Ability()); break;
            case "Sword": setAbility(new Sword_Ability()); break;
            case "BattleAxe": setAbility(new BattleAxe_Ability()); break;
            case "Greatsword": setAbility(new Greatsword_Ability()); break;
            case "Katana": setAbility(new Katana_Ability()); break;
            case "Fists": setAbility(new Fists_Ability());
                
        }

        switch(abilityName){

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
