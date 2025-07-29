import javax.swing.ImageIcon;

/**
 * Class file for the Enemy Class, will later be updated in MCO2.
 * <p>
 *     This class serves as the Player's opponent in battle.
 * </p>
 * <p>
 *     Currently the only actions this class can do in battle is attack continuously.
 * </p>
 * <p>
 *     This class also contains a static attribute to keep track of the total Enemy objects generated.
 * </p>
 * @author Stefan_Martin
 */
public abstract class Enemy extends Character{
  
    private String type;
    private String pattern;
    private int counter = 0;
    public static int totalEnemies = 0;

    public Enemy(String name, String type, ImageIcon image, int hitPoints, int attack, int defense, int speed, String pattern){

        super(name, image, hitPoints, attack, defense, speed);
        this.type = type;
        this.pattern = pattern;
        setTurnInputAction(pattern.charAt(counter));

    }

    //Getters
    
    public String getType(){

        return type;

    }


    public int getCounter(){

        return counter;

    }

    /**
     * Static getter method for the total enemy objects constructed
     * @return the current amount of enemy objects constructed
     */
    public static int getTotalEnemies(){

        return totalEnemies;

    }

    
    public void updateTurnAction(){


            
        counter++;

        if(counter == pattern.length())
            counter = 0;

        if((pattern.charAt(counter) == 'U' && getConsumable().getChargesLeft() == 0) || 
        (pattern.charAt(counter) == 'F' && (getWeapon() == null || !(getWeapon() instanceof EnchantedWeapon) 
        || !(((EnchantedWeapon) getWeapon()).getWeaponSkill().getHasMetConditions()))))

            setTurnInputAction('A');
        else
            setTurnInputAction(pattern.charAt(counter));
    
    }

    public void resetTurnAction(){

        if(counter != 0)
            counter = 0;
    
    }

}
