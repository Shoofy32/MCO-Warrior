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
    public Consumable(String name, String type, int charges, boolean affectsPlayer, boolean affectsEnemy, String[] statsToEffect, 
                      int[] statValuesToEffect, int affectingTurns){

        this.name = name;
        this.type = type;
        this.maxCharges = charges;
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
    public Consumable(String name, String type, int charges, String[] statsToAffectPlayer, int[] statValuesToAffectPlayer,
                      String[] statsToAffectEnemy, int[] statValuesToAffectEnemy, int affectingTurns){

        this.name = name;
        this.type = type;
        this.maxCharges = charges;
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
    public String getName(){

        return name;

    }

    public String getType(){

        return type;

    }

    public int getMaxCharges(){

        return maxCharges;

    }    

    public int getChargesLeft(){

        return chargesLeft;

    }

    public String getPlayerConsumableDescription(){

        return playerConsumableDescription;

    }

    public String getEnemyConsumableDescription(){

        return enemyConsumableDescription;

    }
    public boolean getAffectsPlayer(){

        return affectsPlayer;

    }

    public boolean getAffectsEnemy(){

        return affectsEnemy;

    }

    public boolean getIsTemporary(){

        return isTemporary;

    }

    public int getAffectingTurns(){

        return affectingTurns;

    }

    public int getTurnCounter(){

        return turnCounter;

    }

    public static int getTotalConsumables(){

        return totalConsumables;

    }


    //Consumable Methods

    //Creates the description for playerConsumableDescription
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
            if(i == size - 1 && size > 1)
                playerConsumableDescription += " and"; //When index is size - 1
            else if(size > 1)
                enemyConsumableDescription += ","; //When index is below size - 1
        
        }

        playerConsumableDescription += "."; //Ends description

    }


    //Creates the description for enemyConsumableDescription
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
            if(i == size - 1 && size > 1)
                enemyConsumableDescription += " and"; //When index is size - 1
            else if(size > 1)
                enemyConsumableDescription += ","; //When index is below size - 1
        
        }

        
        enemyConsumableDescription += "."; //Ends description

    }


    //Method decrements the number of charges
    public void useCharges(){

        //Checks if there are any charges left
        if(chargesLeft > 0)
            chargesLeft--;
        else
            System.out.printf("\nERROR: No charges remaining!\n");

    }


    //Method updates Player stats based on statsToAffectPlayer and its values
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

}
