public class Dagger extends Weapon{
    

    public Dagger(String name, int attack, int speedPenalty){

        super(name, attack, speedPenalty);
        type = "Dagger";
        setAbility(new Dagger_Ability());

    }

}
