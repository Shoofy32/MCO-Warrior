/**
 * Class file for the Enemy Class, will later be updated in MCO2.
 * <p>
 *     This class serves as the Player's opponent in battle.
 * </p>
 * <p>
 *     Currently the only actions this class can do is attack continuously.
 * </p>
 * <p>
 *     This class also contains a static attribute to keep track of the total Enemy objects generated.
 * </p>
 * @author Stefan & Martin
 */
public class Enemy {
  
    //String attributes
    private String name = "Enemy";
    private String type;

    //Stat attributes
    private int hitPoints; //Health of the Player
    private int attack; //How much damage they will deal
    private int defense; //How much damage can be resisted
    private int speed; //How fast they will attack (Determines who goes first)

    //Static attributes
    private static int totalEnemies = 0; //Stores the amount of Enemy instances

    //Attributes for display
    private int getLastAttackDone; //Stores the amount of damage that was last done

    //Constructor

    /**
     * Constructor method for the Enemy class
     * @param name The enemy's unique name, assumed all names are valid
     * @param type The class/type of enemy, assumed all types are valid
     * @param hitPoints Starting HP value of the enemy, assumed any value as valid
     * @param attack Starting atk value of the enemy, assumed any value as valid
     * @param defense Starting def value of the enemy, assumed any value as valid
     * @param speed Starting spd value of the enemy, assumed any value as valid
     */
    public Enemy(String name, String type, int hitPoints, int attack, int defense, int speed){

        this.name = name;
        this.type = type;
        this.hitPoints = hitPoints;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        totalEnemies++; //Increment counter

    }


    //Getters

    /**
     * Getter method for the enemy's unique name
     * @return the enemy's name
     */
    public String getName(){

        return name;

    }

    /**
     * Getter method for the enemy's type
     * @return the enemy's type
     */
    public String getType(){

        return type;

    }

    /**
     * Getter method for the enemy's hp value
     * @return the enemy's current hp
     */
    public int getHitPoints(){
        
        return hitPoints;

    }

    /**
     * Getter method for the enemy's atk value
     * @return the enemy's current atk
     */
    public int getAttack(){

        return attack;

    }

    /**
     * Getter method for the enemy's spd value
     * @return the enemy's current spd
     */
    public int getSpeed(){

        return speed;

    }

    /**
     * Getter method for the enemy's def value
     * @return the enemy's def
     */
    public int getDefense(){

        return defense;
        
    }

    //Returns total amount of Enemy instances

    /**
     * Getter method for the total enemy objects constructed
     * @return the current amount of enemy objects constructed
     */
    public static int getTotalEnemies(){

        return totalEnemies;

    }


    //Setters

    /**
     * Setter method to change the enemy's current hp
     * @param hitPoints enemy's new updated hp value
     */
    public void setHitPoints(int hitPoints){
        
        this.hitPoints = hitPoints;

        //Checks if hitPoints has turned into negative values
        if(this.hitPoints < 0)
            this.hitPoints = 0; //Forces it to be 0 to avoid any negative values   

    }

    /**
     * Setter method to change the enemy's current atk
     * @param attack enemy's new updated atk value
     */
    public void setAttack(int attack){

        //Checks whether attack provided is greater or equal than 0
        if(attack >= 0)
            this.attack = attack;
        else
            this.attack = 0; //Forces it to be 0 to avoid any negative values   

    }

    /**
     * Setter method to change the enemy's current spd
     * @param speed enemy's new updated spd value
     */
    public void setSpeed(int speed){

        if(speed >= 0)
            this.speed = speed;
        else
            this.speed = 0; //Forces it to be 0 to avoid any negative values  

    }

    /**
     * Setter method to change the enemy's current def
     * @param defense enemy's new updated def value
     */
    public void setDefense(int defense){

        //Checks whether defense provided is greater or equal than 0
        if(defense >= 0)
            this.defense = defense;
        else
            this.speed = 0; //Forces it to be 0 to avoid any negative values  
        
    }


    //Turn-based Methods

    /**
     * Method for attacking the player object
     * @param target the player object the enemy is currently fighting
     */
    public void attack(Player target){

        int damage; 

        //If condition to check whether target was defending at the time of the attack
        if(target.getIsDefending()){

            //Ternary Operator to calculate damage whether damage was dealt or not with defending target (Half Damage)
            damage = ((attack - target.getDefense()) / 2 > 0) ? ((attack - target.getDefense()) / 2) : 0;
            target.setHitPoints(target.getHitPoints() - damage); //Updates the targets hitpoints after taking damag
            target.stopDefending(); 

            getLastAttackDone = damage; //Stores the damage that was done by the Player for display 

        }
        else{

            //Ternary Operator to calculate damage whether damage was dealt or not
            damage = (attack - target.getDefense() > 0) ? attack - target.getDefense() : 0;
            target.setHitPoints(target.getHitPoints() - damage); //Updates the targets hitpoints after taking damag
            
            getLastAttackDone = damage; //Stores the damage that was done by the Player for display

        }


    }


    //Method allows display to get the last attack's amount

    /**
     * Method for displaying the last attack's value
     * @param display the CLI Viewer class
     * @see CLIViewer
     * @return the value of the latest attack
     */
    public int obtainLastAttackDealt(CLIViewer display){

        //If condition to check whether display is an instance of CLIViewer
        if(display instanceof CLIViewer)
            return getLastAttackDone;
        else 
            return 0;

    }

}
