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
public abstract class Consumable {
    
    //Private General Attributes
    private String name = "None"; 
    private String type;
    private int maxCharges; //Stores the maximum charges or uses of the consumable
    private int chargesLeft; //Stores the current charges or uses left
    private String holderConsumableDescription; //Stores the description of how the consumable affects the player
    private String targetConsumableDescription; //Stores the description of how the consumable affects the enemy

    //Private Logic Attributes
    private boolean affectsHolder; //Stores whether the consumable affects the player
    private boolean affectsTarget; //Stores whether the consumable affects the enemy
    private boolean isTemporary; //Stores whether the consumable provides a temporary effect (False is permanent)
    private int affectingTurns; //Stores how long the temporary effect will last (Storing 0 means permanent)
    private int turnCounter = 0; //Stores how long the current lifetime of the temporary effect (Increments each turn)
    
    //Private Stat Change Attributes
    private String[] statsToAffectHolder; //Stores the Player stats that will be affected
    private int[] statValuesToAffectHolder; //Stores the value of how the Player stats will be affected 
    private String[] statsToAffectTarget; //Stores the Enemy stats that will be affected
    private int[] statValuesToAffectTarget; //Stores the value of how the Enemy stats will be affected    


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
    public Consumable(String name, String type, int maxCharges, boolean affectsHolder, boolean affectsTarget, String[] statsToEffect,
                      int[] statValuesToEffect, int affectingTurns){

        this.name = name;
        this.type = type;
        this.maxCharges = maxCharges;
        chargesLeft = this.maxCharges;
        this.affectsHolder = affectsHolder;
        this.affectsTarget = affectsTarget;

        //If conditions check whether this consumable either affects the player or enemy
        if(affectsHolder && !affectsTarget){ //Checks if it affects player
            
            statsToAffectHolder = statsToEffect;
            statValuesToAffectHolder = statValuesToEffect;

        }
        else if(!affectsHolder && affectsTarget){ //Checks if it affects enemy

            statsToAffectTarget = statsToEffect;
            statValuesToAffectTarget = statValuesToEffect;
            
        }

        //Checks if the affectingTurns is greater than 0 (Determines if the effect is temporary or permanent)
        if(affectingTurns > 0){

            this.affectingTurns = affectingTurns;
            isTemporary = true;

        }

        //If conditions check if player or enemy array has stats to affect
        if(statsToAffectHolder.length > 0){ //Checks if player has stats to affect

            createHolderConsumableDescription(); //Creates Player consumable effect description
            targetConsumableDescription = "None";

        }
        else if(statsToAffectTarget.length > 0){ //Checks if enemy has stats to affect

            holderConsumableDescription = "None"; //Checks Enemy consumable effect description
            createTargetConsumableDescription();

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
    public Consumable(String name, String type, int maxCharges, String[] statsToAffectHolder, int[] statValuesToAffectHolder,
                      String[] statsToAffectTarget, int[] statValuesToAffectTarget, int affectingTurns){

        this.name = name;
        this.type = type;
        this.maxCharges = maxCharges;
        chargesLeft = this.maxCharges;
        this.statsToAffectHolder = statsToAffectHolder;
        this.statValuesToAffectHolder = statValuesToAffectHolder;
        this.statsToAffectTarget = statsToAffectTarget;
        this.statValuesToAffectTarget = statValuesToAffectTarget;

        this.affectingTurns = affectingTurns;
        
        //Make both booleans true since this Constructor assumes that it affects both Player and Enemy
        affectsHolder = true;
        affectsTarget = true;

        //Checks if the affectingTurns is greater than 0 (Determines if the effect is temporary or permanent)
        if(affectingTurns > 0){

            this.affectingTurns = affectingTurns;
            isTemporary = true;

        }

        //Creates descriptions for both
        createHolderConsumableDescription();
        createTargetConsumableDescription();
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

        return holderConsumableDescription;

    }

    /**
     * Getter method to retrieve the consumable's effect on the enemy
     * @return brief description the consumable's effect on the enemy
     */
    public String getEnemyConsumableDescription(){

        return targetConsumableDescription;

    }

    /**
     * Getter method to determine if the consumable affects the player
     * @return consumable's ability to affect the player (true or false)
     */
    public boolean getAffectsHolder(){

        return affectsHolder;

    }

    /**
     * Getter method to determine if the consumable affects the enemy
     * @return consumable's ability to affect the enemy (true or false)
     */
    public boolean getAffectsTarget(){

        return affectsTarget;

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

    /**
     * Getter method to retrieve what stats will be affected for the player
     * @return amount of turns consumable has remained active
     */
    public String[] getStatsToAffectHolder(){

        return statsToAffectHolder;

    }

    /**
     * Getter method to retrieve how many turns have elapsed since the consumable was first used
     * @return amount of turns consumable has remained active
     */
    public int[] getStatValuesToAffectHolder(){

        return statValuesToAffectHolder;

    }

    /**
     * Getter method to retrieve how many turns have elapsed since the consumable was first used
     * @return amount of turns consumable has remained active
     */
    public String[] getStatsToAffectTarget(){

        return statsToAffectTarget;

    }

    /**
     * Getter method to retrieve how many turns have elapsed since the consumable was first used
     * @return amount of turns consumable has remained active
     */
    public int[] getStatValuesToAffectTarget(){

        return statValuesToAffectTarget;
        
    }

    //Consumable Methods


    public abstract void useConsumable(Character holder, Character target);


    //Creates the description for playerConsumableDescription
    private void createHolderConsumableDescription(){

        int size = statsToAffectHolder.length; //Gets length of Player array

        holderConsumableDescription = "Holder"; //Starts description with Player

        //For loop will iterate through the array to check the Stats stored in each index
        for(int i = 0; i < size; i++){

            //Checks the value at the index and checks if its positive or negative 
            if(statValuesToAffectHolder[i] > 0)
                holderConsumableDescription += " gains " + statValuesToAffectHolder[i]; //Positive
            else
                holderConsumableDescription += " loses " + -statValuesToAffectHolder[i]; //Negative

            
            //Switch statement checks the Stat stored in each index and updates the description depending on Stat 
            switch(statsToAffectHolder[i]){

                case "HitPoints":

                    holderConsumableDescription += " HitPoints"; //Adds HitPoints
                    break;               
                    
                case "Attack":

                    holderConsumableDescription += " Attack"; //Adds Attack
                    break;  

                case "Defense":

                    holderConsumableDescription += " Defense"; //Adds Defense
                    break;  

                case "Speed":

                    holderConsumableDescription += " Speed"; //Adds Speed

            }

            //Checks the current index and will update the description based on it.
            if(i + 1 == size - 1 && size > 1)
                holderConsumableDescription += " and"; //When index is size - 1
            else if (i + 1 != size)
                holderConsumableDescription += ","; //When index is below size - 1

        }

        holderConsumableDescription += "."; //Ends description

    }


    //Creates the description for enemyConsumableDescription
    private void createTargetConsumableDescription(){

        int size = statsToAffectTarget.length; //Gets length of Target array

        targetConsumableDescription = "Target"; //Starts description with Target

        //For loop will iterate through the array to check the Stats stored in each index
        for(int i = 0; i < size; i++){

            //Checks the value at the index and checks if its positive or negative 
            if(statValuesToAffectTarget[i] > 0)
                targetConsumableDescription += " gains " + statValuesToAffectTarget[i]; //Positive
            else
                targetConsumableDescription += " loses " + -statValuesToAffectTarget[i]; //Negative


            //Switch statement checks the Stat stored in each index and updates the description depending on Stat 
            switch(statsToAffectTarget[i]){

                case "HitPoints":

                    targetConsumableDescription += " HitPoints"; //Adds HitPoints
                    break;               
                    
                case "Attack":

                    targetConsumableDescription += " Attack"; //Adds Attack
                    break;   

                case "Defense":

                    targetConsumableDescription += " Defense"; //Adds Defense
                    break;  

                case "Speed":

                    targetConsumableDescription += " Speed"; //Adds Speed

            }

            //Checks the current index and will update the description based on it.
            if(i + 1 == size - 1 && size > 1)
                targetConsumableDescription += " and"; //When index is size - 1
            else if (i + 1 != size)
                targetConsumableDescription += ","; //When index is below size - 1
        
        }

        
        targetConsumableDescription += "."; //Ends description

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




    //Method increments turnCounter and calls restoreStats method once it is equal to affectingTurns

    /**
     * Method to keep track of the amount of turns the consumable has remained active, once it reaches the same
     * value as affectingTurns, it restores the stat effects
     * @param player player object whose stats are to be restored
     * @param enemy player object whose stats are to be restored
     */
    public void countAffectingTurns(Character holder, Character target){

        //Checks if the instance is a consumable
        if(isTemporary){
            
            turnCounter++; //Increments

            //Checks if the turnCounter has reached the value of affectingTurns
            if(turnCounter == affectingTurns)
                restoreStats(holder, target); //Call method to restore Player and/or Enemy stats

        }
        else
            System.out.printf("\nERROR: Consumable Changes are permanent!\n");

    }


    //Method restore the stats from temporary effects
    private void restoreStats(Character holder, Character target){

        int size = 0;

        //Checks if the consumable affects the Player
        if(affectsHolder)
            size = statsToAffectTarget.length;


        //For loop with iterate through the array to restore Player's stats from temporary effects
        for(int i = 0; i < size && affectsHolder; i++){

            //Switch statement checks the current stat stored in that index and finds the appropraite case to restore Player's stat
            switch(statsToAffectHolder[i]){

                case "HitPoints":

                    holder.setHitPoints(holder.getHitPoints() - statValuesToAffectHolder[i]); //Restore HitPoints
                    break; 

                case "Attack":

                    holder.setAttack(holder.getAttack() - statValuesToAffectHolder[i]); //Restore Attack
                    break; 

                case "Defense":

                    holder.setDefense(holder.getDefense() - statValuesToAffectHolder[i]); //Restore Defense
                    break; 

                case "Speed":

                    holder.setSpeed(holder.getSpeed() - statValuesToAffectHolder[i]); //Restore Speed

            }

        }


        //Checks if the consumable affects the Enemy
        if(affectsTarget)
            size = statsToAffectTarget.length;

        //For loop with iterate through the array to restore Enemy's stats from temporary effects
        for(int i = 0; i < size && affectsTarget; i++){

            //Switch statement checks the current stat stored in that index and finds the appropraite case to restore Enemy's stat
            switch(statsToAffectTarget[i]){

                case "HitPoints":
                    
                    target.setHitPoints(target.getHitPoints() - statValuesToAffectTarget[i]); //Restore HitPoints
                    break;

                case "Attack":

                    target.setAttack(target.getAttack() - statValuesToAffectTarget[i]); //Restore Attack
                    break;

                case "Defense":

                    target.setDefense(target.getDefense() - statValuesToAffectTarget[i]); //Restore Defense
                    break;

                case "Speed":

                    target.setSpeed(target.getSpeed() - statValuesToAffectTarget[i]); //Restore Speed

            }

        }

        turnCounter = 0; //Resets counter
        holder.setHasConsumeTemp(false); //Sets players consumeTemp to false
        
    }


    //Method to reset charges

    /**
     * Method for resetting the charges of the consumable
     */
    public void resetCharges(){

        chargesLeft = maxCharges;
        turnCounter = 0;
        
    }

}
