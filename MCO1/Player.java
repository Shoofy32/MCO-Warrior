public class Player {
    
    //String attributes
    private String name = "Player"; //Name of Player

    //Stat attributes
    private int hitPoints = 100; //Health of the Player
    private int attack = 1; //How much damage they will deal
    private int defense = 1; //How much damage can be resisted
    private int speed = 50; //How fast they will attack (Determines who goes first)

    //Weapon and Armor attributes
    private Armor armor = null; //Armor that the player can equip
    private Weapon weapon = null; //Weapon that the player can equip

    //State attributes
    private boolean isCharging = false; //Boolean check to see whether Player is charging
    private boolean isDefending = false; //Boolean check to see whether Player is defending

    //Attributes for display
    private int getLastAttackDone; //Stores the amount of damage that was last done

    
    //Setters
    public void setName(String pName){

        name = pName;

    }

    public void setHitPoints(int hitPoints){
        
        //Checks whether hitpoints provided is greater than 0
        if(hitPoints >= 0)
            this.hitPoints = hitPoints;
        else
            this.hitPoints = 0; //Forces it to be 0 to avoid any negative values   

    }

    public void setAttack(int attack){

        this.attack = attack;

    }

    public void settSpeed(int speed){

        this.speed = speed;

    }

    public void setDefense(int defense){

        this.defense = defense;
        
    }


    //Getters
    public String getName(){

        return name;

    }

    public int getHitPoints(){
        
        return hitPoints;

    }

    public int getAttack(){

        return attack;

    }

    public int getSpeed(){

        return speed;

    }

    public int getDefense(){

        return defense;
        
    }

    public Armor getArmor(){

        return armor;

    }

    public Weapon getWeapon(){

        return weapon;

    }

    public boolean getIsCharging(){

        return isCharging;

    }

    public boolean getIsDefending(){

        return isDefending;

    }


    //Methods for Equipping Gear
    public void equipArmor(Armor armor){

        //If condition to check whether the parameter object it not null
        if(armor != null){

            //Equip the armor and set new stats of the player
            this.armor = armor;
            defense = armor.getDefense();
            speed -= armor.getSpeedPenalty();

        }

    }


    public void equipWeapon(Weapon weapon){

        //If condition to check whether the parameter object it not null
        if(weapon != null){

            //Equip the armor and set new stats of the player
            this.weapon = weapon;
            attack = weapon.getAttack();
            speed -= weapon.getSpeedPenalty();

        }

    }


    public void unequipArmor(){

        defense = 1;
        speed += armor.getSpeedPenalty();
        armor = null;

    }


    public void unequipWeapon(){

        attack = 1;
        speed += weapon.getSpeedPenalty();
        weapon = null;

    }


    //Turn-based Methods

    /* -Think Method-
     * Description: Method for the Player to choose what to do in their turn. Their choice will call the corresponding method
     * and end their turn.
     * @param input - Scanner instance that will be used to gather input.
     * Precondition: Currently Players Turn
     * Return Value: String of the players choice that will be used by CLIViewer for display
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
                if(!isCharging){

                    playerChoice = "Charge";
                    //C - Charge Player
                    charge(); 

                }        

        }

        return playerChoice; //Return the choice of the player via String

    }


    /* -Attack Method-
     * Description: Method to attack the enemy target and deal an amount of damage depending on the attack stat of the player and 
     * the enemies defense. If charging was done last turn, then damage dealt will be triple.
     * @param target - Enemy instance that the player will target to deal damage to.
     * Precondition: Currently Players Turn
     * Return Value: None
     */
    public void attack(Enemy target){

        int damage; //Stores the amount of damage dont to the enemy

        //If condition to check whether the Player was charging at the time of their attack
        if(isCharging){

            //Ternary Operator to calculate damage wether damage was dealt or not while charging (Triple damage)
            damage = (attack * 3 - target.getDefense() > 0) ? attack * 3 - target.getDefense() : 0;
            target.setHitPoints(target.getHitPoints() - damage); //Updates the targets hitpoints after taking damage

            stopCharging();
            getLastAttackDone = damage; //Stores the damage that was done by the Player for display

        }
        else{

            //Ternary Operator to calculate damage wether damage was dealt or not
            damage = (attack - target.getDefense() > 0) ? attack - target.getDefense() : 0;
            target.setHitPoints(target.getHitPoints() - damage); //Updates the targets hitpoints after taking damage

            getLastAttackDone = damage; //Stores the damage that was done by the Player for display

        }

    }


    /* -Defend Method-
     * Description: Method to defend the player by making isDefending true. Making it prioritize 
     * their remove and halving any damage dealt to them.
     * Precondition: Currently Players Turn
     * Return Value: None
     */
    public void defend(){

        isDefending = true;

        if(isCharging)
            stopCharging();

    }


    public void stopDefending(){

        //If condition checks whether Player was defending
        if(isDefending)
            isDefending = false;
        else
            System.out.printf("\nERROR: Player is already not defending!\n"); //Error display if false

    }

    /* -Charge Method-
     * Description: Method for the player to charge and skip their damage. In the next turn, if they deal damage, it will deal
     * triple the original amount.
     * Precondition: Currently Players Turn and is not currently charging (isCharging is false).
     * Return Value: None
     */
    public void charge(){

        isCharging = true;

    }


    public void stopCharging(){

        //If condition checks whether Player was charging
        if(isCharging)
            isCharging = false;
        else
            System.out.printf("\nERROR: Player is already not charging!\n"); //Error display if false

    }




    public int obtainLastAttackDealt(CLIViewer display){

        //If condition to check whether display is an instance of CLIViewer
        if(display instanceof CLIViewer)
            return getLastAttackDone;
        else 
            return 0;

    }


}
