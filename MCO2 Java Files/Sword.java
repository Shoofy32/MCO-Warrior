public class Sword extends Weapon{
    

    public Sword(String name, int attack, int speedPenalty){

        super(name, attack, speedPenalty); 
        type = "Sword";
        setAbility(new Sword_Ability());

    }

}
