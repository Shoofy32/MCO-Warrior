/**
 * Class file for the player class, the user's controllable fighter in the battle
 * <p>
 *     The player is a fighter whose equipment the user can customize
 * </p>
 * <p>
 *     The player is capable of attacking, defending, charging, and using a consumable if available
 * </p>
 * @author Stefan_Martin
 */
public class Player {
    
    //String attributes
    private final String name; //Name of Player

    //Stat attributes
    private int hitPoints = 100; //Health of the Player
    private int attack = 1; //How much damage they will deal
    private int defense = 1; //How much damage can be resisted
    private int speed = 50; //How fast they will attack (Determines who goes first)

    //Weapon and Armor attributes
    private Armor armor = null; //Armor that the player can equip
    private Weapon weapon = null; //Weapon that the player can equip
    private Consumable consumable = null; //Consumable that the player can use

    //State attributes
    private boolean isCharging = false; //Boolean check to see whether Player is charging
    private boolean isDefending = false; //Boolean check to see whether Player is defending
    private boolean hasConsumeTemp = false; //Boolean check to see whether Player used a consumable that is temporary

    //Attributes for display
    private int getLastAttackDone; //Stores the amount of damage that was last done

    
    //Constructor

    /**
     * Constructor for the player object
     * @param name a unique name the player will be identified with
     */
    public Player(String name){

       this.name = name;
;
    }


    //Setters

    /**
     * Updates the player's health value
     * @param hitPoints new health value
     */
    public void setHitPoints(int hitPoints){
        
        this.hitPoints = hitPoints;

        //Checks if hitPoints has gone over the limit or has turned into negative values
        if(this.hitPoints > 100) 
            this.hitPoints = 100; //Forces it to be 100 
        else if(this.hitPoints < 0)
            this.hitPoints = 0; //Forces it to be 0 to avoid any negative values   

    }

    /**
     * Updates the player's atk value
     * @param attack new atk value
     */
    public void setAttack(int attack){

        //Checks whether attack provided is greater or equal than 0
        if(attack >= 0)
            this.attack = attack;
        else
            this.attack = 0; //Forces it to be 0 to avoid any negative values   

    }

    /**
     * Updates the player's spd value
     * @param speed new spd value
     */
    public void setSpeed(int speed){

        if(speed >= 0)
            this.speed = speed;
        else
            this.speed = 0; //Forces it to be 0 to avoid any negative values  

    }

    /**
     * Updates the player's def value
     * @param defense new def value
     */
    public void setDefense(int defense){

        //Checks whether defense provided is greater or equal than 0
        if(defense >= 0)
            this.defense = defense;
        else
            this.defense = 0; //Forces it to be 0 to avoid any negative values  
        
    }

    /**
     * Updates the player's current consumable usage
     * @param hasConsumeTemp new state of consumable usage (true or false)
     */
    public void setHasConsumeTemp(boolean hasConsumeTemp){

        this.hasConsumeTemp = hasConsumeTemp;

    }


    //Getters

    /**
     * Getter method to retrieve the player's name
     * @return the player's name
     */
    public String getName(){

        return name;

    }

    /**
     * Getter method to retrieve the player's current HP value
     * @return the player's current HP
     */
    public int getHitPoints(){
        
        return hitPoints;

    }

    /**
     * Getter method to retrieve the player's current atk value
     * @return the player's current atk
     */
    public int getAttack(){

        return attack;

    }

    /**
     * Getter method to retrieve the player's current spd value
     * @return the player's current spd
     */
    public int getSpeed(){

        return speed;

    }

    /**
     * Getter method to retrieve the player's current def value
     * @return the player's current def
     */
    public int getDefense(){

        return defense;
        
    }

    /**
     * Getter method to retrieve the player's currently equipped armor object
     * @return the player's current armor
     */
    public Armor getArmor(){

        return armor;

    }

    /**
     * Getter method to retrieve the player's currently equipped weapon object
     * @return the player's current weapon
     */
    public Weapon getWeapon(){

        return weapon;

    }

    /**
     * Getter method to retrieve the player's currently equipped consumable object
     * @return the player's current consumable
     */
    public Consumable getConsumable(){

        return consumable;

    }

    /**
     * Getter method to determine if the player is currently charging their attack
     * @return player's current state of charging (true or false)
     */
    public boolean getIsCharging(){

        return isCharging;

    }

    /**
     * Getter method to determine if the player is currently defending
     * @return player's current state of defending (true or false)
     */
    public boolean getIsDefending(){

        return isDefending;

    }

    /**
     * Getter method to determine if the player is capable of using another consumable
     * @return player's current state of consumable use (true or false)
     */
    public boolean getHasConsumeTemp(){

        return hasConsumeTemp;

    }


    //Methods for Equipping Gear

    /**
     * Equips the chosen armor object
     * @param armor armor to be equipped
     */
    public void equipArmor(Armor armor){

        //If condition to check whether the parameter object it not null
        if(armor != null){

            //Equip the armor and set new stats of the player
            this.armor = armor;
            defense = armor.getDefense();
            speed -= armor.getSpeedPenalty();

        }

    }

    /**
     * Equips the chosen weapon object
     * @param weapon weapon to be equipped
     */
    public void equipWeapon(Weapon weapon){

        //If condition to check whether the parameter object it not null
        if(weapon != null){

            //Equip the armor and set new stats of the player
            this.weapon = weapon;
            attack = weapon.getAttack();
            speed -= weapon.getSpeedPenalty();

        }

    }

    /**
     * Equips the chosen consumable object
     * @param consumable consumable to be equipped
     */
    public void equipConsumable(Consumable consumable){

        //If condition to check whether the parameter object it not null
        if(consumable != null)
            this.consumable = consumable; //Equip the consumable and set new stats of the player

    }

    /**
     * Unequips the currently equipped armor
     */
    public void unequipArmor(){

        defense = 1;
        speed += armor.getSpeedPenalty();
        armor = null;

    }

    /**
     * Unequips the currently equipped weapon
     */
    public void unequipWeapon(){

        attack = 1;
        speed += weapon.getSpeedPenalty();
        weapon = null;

    }

    /**
     * Unequips the currently equipped consumable
     */
    public void unequipConsumable(){

        consumable = null;

    }


    //Turn-based Methods

    /**
     * Method for the player to choose what they wish to do during their turn, the player can choose to do only
     * one action per turn
     * @param choice The user's input to determine the player's action
     * @param target The enemy object the player is currently fighting
     * @return the given action the player will perform in the turn
     */
    public String think(char choice, Enemy target){

        String playerChoice = null; //Set playerChoice to null at the start

        //Switch statement that will call the corresponding method depending on user choice
        switch(choice){

            case 'A':

                playerChoice = "Attack";
                attack(target); //A - Attack Target 
                break;


            case 'D': 

                playerChoice = "Defend";
                defend(); //D - Defend against Target
                break;


            case 'C': 

                //If condition to check whether the player is charging 
                playerChoice = "Charge";
                if(!isCharging)
                    charge(); //C - Charge Player 
                     
                break;
                
            case 'U':

                //If condition to check whether the player is charging 
                if(consumable.getChargesLeft() > 0){

                    playerChoice = "Consume";
                    consume(target); //U - Charge Player 

                }    

        }

        return playerChoice; //Return the choice of the player via String

    }


    /**
     * Method to attack the enemy object, attacking is affected by player atk, enemy def, and the player's charging state
     * @param target the enemy object to be attacked.
     */
    public void attack(Enemy target){

        int damage; //Stores the amount of damage dont to the enemy

        //If condition to check whether the Player was charging at the time of their attack
        if(isCharging){

            //Ternary Operator to calculate damage whether damage was dealt or not while charging (Triple damage)
            damage = (attack * 3 - target.getDefense() > 0) ? attack * 3 - target.getDefense() : 0;
            target.setHitPoints(target.getHitPoints() - damage); //Updates the targets hitpoints after taking damage

            stopCharging();
            getLastAttackDone = damage; //Stores the damage that was done by the Player for display

        }
        else{

            //Ternary Operator to calculate damage whether damage was dealt or not
            damage = (attack - target.getDefense() > 0) ? attack - target.getDefense() : 0;
            target.setHitPoints(target.getHitPoints() - damage); //Updates the targets hitpoints after taking damage

            getLastAttackDone = damage; //Stores the damage that was done by the Player for display

        }

    }


    /**
     * Method for allowing the player to defend, defending takes priority regardless of the player's spd and will
     * halve all damage dealt to the player in that turn. Set's the player's defending state to true
     */
    public void defend(){

        isDefending = true;

        if(isCharging)
            stopCharging();

    }

    /**
     * Method for setting the player's defending state to false
     */
    public void stopDefending(){

        //If condition checks whether Player was defending
        if(isDefending)
            isDefending = false;
        else
            System.out.printf("\nERROR: Player is already not defending!\n"); //Error display if false

    }

    /**
     * Method for allowing the player to charge, charging triples the damage of the player's atk, this is
     * done before enemy def is taken into account
     */
    public void charge(){

        isCharging = true;

    }

    /**
     * Method for setting the player's charging state to false
     */
    public void stopCharging(){

        //If condition checks whether Player was charging
        if(isCharging)
            isCharging = false;
        else
            System.out.printf("\nERROR: Player is already not charging!\n"); //Error display if false

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


    //Method uses the consumable equipped and gets corresponding effects for both Player and Enemy

    /**
     * Method that uses the currently equipped consumable can either affect the enemy, player, or both
     * @param target Enemy object to be affected by the consumable
     */
    public void consume(Enemy target){

        //Checks if there are any charges left
        if(consumable.getChargesLeft() > 0){

            //Checks if Consumable affects Player
            if(consumable.getAffectsPlayer())
                consumable.affectPlayer(this); //Calls method to affect Player stats
            
            //Checks if Consumable affects Enemy
            if(consumable.getAffectsEnemy())
                consumable.affectEnemy(target); //Calls method to affect Enemy stats

            consumable.useCharges(); //Decrement charge

            //Checks if Consumable is Temporary
            if(consumable.getIsTemporary())
                hasConsumeTemp = true;

        }

    }


}
