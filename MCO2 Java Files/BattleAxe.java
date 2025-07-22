public class BattleAxe extends Weapon{
    

    public BattleAxe(String name, int attack, int speedPenalty){

        super(name, attack, speedPenalty);
        type = "Battle Axe";
        setAbility(new BattleAxe_Ability());

    }

}
