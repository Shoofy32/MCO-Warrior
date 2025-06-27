/**
 * Class file for the Consumable class.
 * <p>
 *     Consumables are highly customizable equipment that can affect either the player, the enemy, or both.
 * </p>
 * <p>
 *     They are capable of serving as player/enemy buffs and player/enemy debuffs.
 * </p>
 * <p>
 *     This class also contains a static attribute to keep track of the total Consumable objects generated.
 * </p>
 * @author Stefan_Martin
 */
public class Consumable {
    
    //Private General Attributes
    private String name = "None"; 
    private String type;
    private int maxCharges; //Stores the maximum charges or uses of the consumable
    private int chargesLeft; //Stores the current charges or uses left
    private String playerConsumableDescription; //Stores the description of how the consumable affects the player
    private String enemyConsumableDescription; //Stores the description of how the consumable affects the enemy

    //Private Logic Attributes
    private boolean affectsPlayer; //Stores whether the consumable affects the player
    private boolean affectsEnemy; //Stores whether the consumable affects the enemy
    private boolean isTemporary; //Stores whether the consumable provides a temporary effect (False is permanent)
    private int affectingTurns; //Stores how long the temporary effect will last (Storing 0 means permanent)
    private int turnCounter = 0; //Stores how long the current lifetime of the temporary effect (Increments each turn)
    
    //Private Stat Change Attributes
    private String[] statsToAffectPlayer; //Stores the Player stats that will be affected
    private int[] statValuesToAffectPlayer; //Stores the value of how the Player stats will be affected 
    private String[] statsToAffectEnemy; //Stores the Enemy stats that will be affected
    private int[] statValuesToAffectEnemy; //Stores the value of how the Enemy stats will be affected    


    //Private static attributes
    private static int totalConsumables = 0; //Stores the amount of Enemy instances


    //Constructors

    //Constructor for stat changes for Player or Enemy only

    /**
     * Constructor for consumables that affect either the player or enemy
     * @param name unique name of the consumable
     * @param type unique type of the consumable, assumed all types are valid
     * @param maxCharges amount of times the consumable can be used
     * @param affectsPlayer determines whether the consumable will affect the player
     * @param affectsEnemy determines whether the consumable will affect the enemy
     * @param statsToEffect array of stats to be affected, 1 to 1 with the int array
     * @param statValuesToEffect array of values that will affect the corresponding stats, 1 to 1 with the String array
     * @param affectingTurns amount of turns the consumable remains active
     */
    public Consumable(String name, String type, int maxCharges, boolean affectsPlayer, boolean affectsEnemy, String[] statsToEffect,
                      int[] statValuesToEffect, int affectingTurns){

        this.name = name;
        this.type = type;
        this.maxCharges = maxCharges;
        chargesLeft = this.maxCharges;
        this.affectsPlayer = affectsPlayer;
        this.affectsEnemy = affectsEnemy;

        //If conditions check whether this consumable either affects the player or enemy
        if(affectsPlayer && !affectsEnemy){ //Checks if it affects player
            
            statsToAffectPlayer = statsToEffect;
            statValuesToAffectPlayer = statValuesToEffect;

        }
        else if(!affectsPlayer && affectsEnemy){ //Checks if it affects enemy

            statsToAffectEnemy = statsToEffect;
            statValuesToAffectEnemy = statValuesToEffect;
            
        }

        //Checks if the affectingTurns is greater than 0 (Determines if the effect is temporary or permanent)
        if(affectingTurns > 0){

            this.affectingTurns = affectingTurns;
            isTemporary = true;

        }

        //If conditions check if player or enemy array has stats to affect
        if(statsToAffectPlayer.length > 0){ //Checks if player has stats to affect

            createPlayerConsumableDescription(); //Creates Player consumable effect description
            enemyConsumableDescription = "None";

        }
        else if(statsToAffectEnemy.length > 0){ //Checks if enemy has stats to affect

            playerConsumableDescription = "None"; //Checks Enemy consumable effect description
            createEnemyConsumableDescription();

        }

        totalConsumables++; //Increments count

    }

    //Constructor for stat changes for both Player and Enemy

    /**
     * Constructor for consumables that affect either the player or enemy
     * @param name unique name of the consumable
     * @param type unique type of the consumable, assumed all types are valid
     * @param maxCharges amount of times the consumable can be used
     * @param statsToAffectPlayer array of player stats to be affected, 1 to 1 with the statValuesToAffectPlayer array
     * @param statValuesToAffectPlayer array of values that will affect the corresponding player stats
     * @param statsToAffectEnemy array of enemy stats to be affected, 1 to 1 with the statValuesToAffectEnemy array
     * @param statValuesToAffectEnemy array of values that will affect the corresponding enemy stats
     * @param affectingTurns amount of turns the consumable remains active
     */
    public Consumable(String name, String type, int maxCharges, String[] statsToAffectPlayer, int[] statValuesToAffectPlayer,
                      String[] statsToAffectEnemy, int[] statValuesToAffectEnemy, int affectingTurns){

        this.name = name;
        this.type = type;
        this.maxCharges = maxCharges;
        chargesLeft = this.maxCharges;
        this.statsToAffectPlayer = statsToAffectPlayer;
        this.statValuesToAffectPlayer = statValuesToAffectPlayer;
        this.statsToAffectEnemy = statsToAffectEnemy;
        this.statValuesToAffectEnemy = statValuesToAffectEnemy;

        this.affectingTurns = affectingTurns;
        
        //Make both booleans true since this Constructor assumes that it affects both Player and Enemy
        affectsPlayer = true;
        affectsEnemy = true;

        //Checks if the affectingTurns is greater than 0 (Determines if the effect is temporary or permanent)
        if(affectingTurns > 0){

            this.affectingTurns = affectingTurns;
            isTemporary = true;

        }

        //Creates descriptions for both
        createPlayerConsumableDescription();
        createEnemyConsumableDescription();
        totalConsumables++; //Increments count

    }
    

    //Getters

    /**
     * Getter method to retrieve the consumable's unique name
     * @return consumable's name
     */
    public String getName(){

        return name;

    }

    /**
     * Getter method to retrieve the consumable's type
     * @return consumable's type
     */
    public String getType(){

        return type;

    }

    /**
     * Getter method to retrieve the consumable's max charges
     * @return consumable's max charges
     */
    public int getMaxCharges(){

        return maxCharges;

    }

    /**
     * Getter method to retrieve the consumable's current charges
     * @return consumable's current charges
     */
    public int getChargesLeft(){

        return chargesLeft;

    }

    /**
     * Getter method to retrieve the consumable's effect on the player
     * @return brief description the consumable's effect on the player
     */
    public String getPlayerConsumableDescription(){

        return playerConsumableDescription;

    }

    /**
     * Getter method to retrieve the consumable's effect on the enemy
     * @return brief description the consumable's effect on the enemy
     */
    public String getEnemyConsumableDescription(){

        return enemyConsumableDescription;

    }

    /**
     * Getter method to determine if the consumable affects the player
     * @return consumable's ability to affect the player (true or false)
     */
    public boolean getAffectsPlayer(){

        return affectsPlayer;

    }

    /**
     * Getter method to determine if the consumable affects the enemy
     * @return consumable's ability to affect the enemy (true or false)
     */
    public boolean getAffectsEnemy(){

        return affectsEnemy;

    }

    /**
     * Getter method to determine if the consumable's effects are temporary
     * @return consumable's permanence (true or false)
     */
    public boolean getIsTemporary(){

        return isTemporary;

    }

    /**
     * Getter method to determine if how many turns the consumable will remain active
     * @return the total amount of turns the consumable's effects will remain actve
     */
    public int getAffectingTurns(){

        return affectingTurns;

    }

    /**
     * Getter method to retrieve how many turns have elapsed since the consumable was first used
     * @return amount of turns consumable has remained active
     */
    public int getTurnCounter(){

        return turnCounter;

    }

    /**
     * Static getter method for the total enemy objects constructed
     * @return the current amount of enemy objects constructed
     */
    public static int getTotalConsumables(){

        return totalConsumables;

    }


    //Consumable Methods

    //Creates the description for playerConsumableDescription

    /**
     * Method to generate a brief description detailing the consumable's effect on the player
     */
    private void createPlayerConsumableDescription(){

        int size = statsToAffectPlayer.length; //Gets length of Player array

        playerConsumableDescription = "Player"; //Starts description with Player

        //For loop will iterate through the array to check the Stats stored in each index
        for(int i = 0; i < size; i++){

            //Checks the value at the index and checks if its positive or negative 
            if(statValuesToAffectPlayer[i] > 0)
                playerConsumableDescription += " gains " + statValuesToAffectPlayer[i]; //Positive
            else
                playerConsumableDescription += " loses " + -statValuesToAffectPlayer[i]; //Negative

            
            //Switch statement checks the Stat stored in each index and updates the description depending on Stat 
            switch(statsToAffectPlayer[i]){

                case "HitPoints":

                    playerConsumableDescription += " HitPoints"; //Adds HitPoints
                    break;               
                    
                case "Attack":

                    playerConsumableDescription += " Attack"; //Adds Attack
                    break;  

                case "Defense":

                    playerConsumableDescription += " Defense"; //Adds Defense
                    break;  

                case "Speed":

                    playerConsumableDescription += " Speed"; //Adds Speed

            }

            //Checks the current index and will update the description based on it.
            if(i + 1 == size - 1 && size > 1)
                playerConsumableDescription += " and"; //When index is size - 1
            else if (i + 1 != size)
                playerConsumableDescription += ","; //When index is below size - 1

        }

        playerConsumableDescription += "."; //Ends description

    }


    //Creates the description for enemyConsumableDescription
    /**
     * Method to generate a brief description detailing the consumable's effect on the enemy
     */
    private void createEnemyConsumableDescription(){

        int size = statsToAffectEnemy.length; //Gets length of Enemy array

        enemyConsumableDescription = "Enemy"; //Starts description with Enemy

        //For loop will iterate through the array to check the Stats stored in each index
        for(int i = 0; i < size; i++){

            //Checks the value at the index and checks if its positive or negative 
            if(statValuesToAffectEnemy[i] > 0)
                enemyConsumableDescription += " gains " + statValuesToAffectEnemy[i]; //Positive
            else
                enemyConsumableDescription += " loses " + -statValuesToAffectEnemy[i]; //Negative


            //Switch statement checks the Stat stored in each index and updates the description depending on Stat 
            switch(statsToAffectEnemy[i]){

                case "HitPoints":

                    enemyConsumableDescription += " HitPoints"; //Adds HitPoints
                    break;               
                    
                case "Attack":

                    enemyConsumableDescription += " Attack"; //Adds Attack
                    break;   

                case "Defense":

                    enemyConsumableDescription += " Defense"; //Adds Defense
                    break;  

                case "Speed":

                    enemyConsumableDescription += " Speed"; //Adds Speed

            }

            //Checks the current index and will update the description based on it.
            if(i + 1 == size - 1 && size > 1)
                enemyConsumableDescription += " and"; //When index is size - 1
            else if (i + 1 != size)
                enemyConsumableDescription += ","; //When index is below size - 1
        
        }

        
        enemyConsumableDescription += "."; //Ends description

    }


    //Method decrements the number of charges

    /**
     * Method that decrements the current amount of charges of the consumable
     */
    public void useCharges(){

        //Checks if there are any charges left
        if(chargesLeft > 0)
            chargesLeft--;
        else
            System.out.printf("\nERROR: No charges remaining!\n");

    }


    //Method updates Player stats based on statsToAffectPlayer and its values

    /**
     * Method that applies the stat effects of the consumable to the player
     * @param player player object whose stats are to be updated/effected
     */
    public void affectPlayer(Player player){

        int size = statsToAffectPlayer.length; //Gets size of Player Array

        //For loop will iterate through the array to check the Stats stored in each index
        for(int i = 0; i < size; i++){

            //Switch statement checks the Stat stored in each index and updates the values of the corresponding Stat 
            switch(statsToAffectPlayer[i]){

                case "HitPoints":

                    player.setHitPoints(player.getHitPoints() + statValuesToAffectPlayer[i]); //Affect HitPoints
                    break; 

                case "Attack":

                    player.setAttack(player.getAttack() + statValuesToAffectPlayer[i]); //Affect Attack
                    break; 

                case "Defense":

                    player.setDefense(player.getDefense() + statValuesToAffectPlayer[i]); //Affect Defense
                    break; 

                case "Speed":

                    player.setSpeed(player.getSpeed() + statValuesToAffectPlayer[i]); //Affect Speed

            }

        }

    }


    //Method updates Enemy stats based on statsToAffectEnemy and its values
    /**
     * Method that applies the stat effects of the consumable to the enemy
     * @param enemy enemy object whose stats are to be updated/effected
     */
    public void affectEnemy(Enemy enemy){

        int size = statsToAffectEnemy.length; //Gets size of Enemy array

        //For loop will iterate through the array to check the Stats stored in each index
        for(int i = 0; i < size; i++){

            //Switch statement checks the Stat stored in each index and updates the values of the corresponding Stat 
            switch(statsToAffectEnemy[i]){

                case "HitPoints":
                    
                    enemy.setHitPoints(enemy.getHitPoints() + statValuesToAffectEnemy[i]); //Affect HitPoints
                    break;

                case "Attack":

                    enemy.setAttack(enemy.getAttack() + statValuesToAffectEnemy[i]); //Affect Attack
                    break;

                case "Defense":

                    enemy.setDefense(enemy.getDefense() + statValuesToAffectEnemy[i]); //Affect Defense
                    break;

                case "Speed":

                    enemy.setSpeed(enemy.getSpeed() + statValuesToAffectEnemy[i]); //Affect Speed

            }

        }

    }


    //Method increments turnCounter and calls restoreStats method once it is equal to affectingTurns

    /**
     * Method to keep track of the amount of turns the consumable has remained active, once it reaches the same
     * value as affectingTurns, it restores the stat effects
     * @param player player object whose stats are to be restored
     * @param enemy player object whose stats are to be restored
     */
    public void countAffectingTurns(Player player, Enemy enemy){

        //Checks if the instance is a consumable
        if(isTemporary){
            
            turnCounter++; //Increments

            //Checks if the turnCounter has reached the value of affectingTurns
            if(turnCounter == affectingTurns)
                restoreStats(player, enemy); //Call method to restore Player and/or Enemy stats

        }
        else
            System.out.printf("\nERROR: Consumable Changes are permanent!\n");

    }


    //Method restore the stats from temporary effects
    private void restoreStats(Player player, Enemy enemy){

        int size = 0;

        //Checks if the consumable affects the Player
        if(affectsPlayer)
            size = statsToAffectPlayer.length;


        //For loop with iterate through the array to restore Player's stats from temporary effects
        for(int i = 0; i < size && affectsPlayer; i++){

            //Switch statement checks the current stat stored in that index and finds the appropraite case to restore Player's stat
            switch(statsToAffectPlayer[i]){

                case "HitPoints":

                    player.setHitPoints(player.getHitPoints() - statValuesToAffectPlayer[i]); //Restore HitPoints
                    break; 

                case "Attack":

                    player.setAttack(player.getAttack() - statValuesToAffectPlayer[i]); //Restore Attack
                    break; 

                case "Defense":

                    player.setDefense(player.getDefense() - statValuesToAffectPlayer[i]); //Restore Defense
                    break; 

                case "Speed":

                    player.setSpeed(player.getSpeed() - statValuesToAffectPlayer[i]); //Restore Speed

            }

        }


        //Checks if the consumable affects the Enemy
        if(affectsEnemy)
            size = statsToAffectEnemy.length;

        //For loop with iterate through the array to restore Enemy's stats from temporary effects
        for(int i = 0; i < size && affectsEnemy; i++){

            //Switch statement checks the current stat stored in that index and finds the appropraite case to restore Enemy's stat
            switch(statsToAffectEnemy[i]){

                case "HitPoints":
                    
                    enemy.setHitPoints(enemy.getHitPoints() - statValuesToAffectEnemy[i]); //Restore HitPoints
                    break;

                case "Attack":

                    enemy.setAttack(enemy.getAttack() - statValuesToAffectEnemy[i]); //Restore Attack
                    break;

                case "Defense":

                    enemy.setDefense(enemy.getDefense() - statValuesToAffectEnemy[i]); //Restore Defense
                    break;

                case "Speed":

                    enemy.setSpeed(enemy.getSpeed() - statValuesToAffectEnemy[i]); //Restore Speed

            }

        }

        turnCounter = 0; //Resets counter
        player.setHasConsumeTemp(false); //Sets players consumeTemp to false
        
    }


    //Method to reset charges

    /**
     * Method for resetting the charges of the consumable
     */
    public void resetCharges(){

        chargesLeft = maxCharges;
        
    }

}
