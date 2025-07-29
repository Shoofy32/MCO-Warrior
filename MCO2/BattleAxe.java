import javax.swing.ImageIcon;

public class BattleAxe extends Weapon{
    

    public BattleAxe(String name, int attack, int speedPenalty, ImageIcon image){

        super(name, attack, speedPenalty, image);
        type = "Battle Axe";
        setAbility(new BattleAxe_Ability());

    }

}
