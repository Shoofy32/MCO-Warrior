public class Environment {
    
    //Private Attributes
    private String name; 
    private boolean hasEffect = false; //Stores whether the environment will deal an effect or not

    //Player Affecting Attributes
    private int playerStatEffect = 0; //Stores the value of how much the Player stat will be affected
    private String playerStatToEffect; //Stores a String of what Player stat to affect
    private String playerEffectDescription = "None"; //Stores the description of the Player effects
    
    //Enemy Affecting Attributes
    private int enemyStatEffect = 0; //Stores the value of how much the Enemy stat will be affected
    private String enemyStatToEffect; //Stores a String of what Enemy stat to affect 
    private String enemyEffectDescription = "None"; //Stores the description of the Enemy effects

    //Static Attributes
    private static int totalEnvironments = 0; //Stores the amount of Environment instances


    //Constructors

    //Constructor with no effets
    public Environment(String name, boolean hasEffect){

        this.name = name;
        this.hasEffect = hasEffect;
        totalEnvironments++; //Increment counter

    }

    //Constructor with effets
    public Environment(String name, boolean hasEffect, int playerStatEffect, String pStatToEffect, 
                       int enemyStatEffect, String eStatToEffect){

        this.name = name;
        this.hasEffect = hasEffect;
        this.playerStatEffect = playerStatEffect;
        this.playerStatToEffect = pStatToEffect;
        this.enemyStatEffect = enemyStatEffect;
        this.enemyStatToEffect = eStatToEffect;
        totalEnvironments++; //Increment counter

        createEnemyEnvironmentDescription(); //Creates a description for enemyEffectDescription
        createPlayerEnvironmentDescription(); //Creates a description for playerEffectDescription

    }


    //Getters
    public String getName(){

        return name;

    }

    public boolean getHasEffect(){

        return hasEffect;

    }

    public int getPlayerStatEffect(){

        return playerStatEffect;

    }

    public String getPlayerStatToEffect(){

        return playerStatToEffect;

    }

    public String getPlayerEffectDescription(){

        return playerEffectDescription;

    }

    public int getEnemyStatEffect(){

        return enemyStatEffect;

    }

    public String getEnemyStatToEffect(){

        return enemyStatToEffect;
        
    }

    public String getEnemyEffectDescription(){            

        return enemyEffectDescription;

    }

    //Returns total amount of Environment instances 
    public static int getTotalEnvironments(){

        return totalEnvironments;

    }


    //Environment effect Methods

    //Creates the description for playerEffectDescription
    public void createPlayerEnvironmentDescription(){

        playerEffectDescription = "Player"; 

        //Checks the value of playerStatEffect and the playerStatToEffect and will add a specific String depending on value
        if(playerStatEffect > 0) 
            playerEffectDescription += " gains "; //Label for gaining stats 
        else if(playerStatEffect < 0 && playerStatToEffect == "HitPoints") 
            playerEffectDescription += " takes "; //Label for taking damage
        else
            playerEffectDescription += " loses "; //Label for losing stats
            
        //Ternary Operator to check whether the playerStatEffect is negative or positive
        playerEffectDescription += (playerStatEffect < 0) ? -playerStatEffect : playerStatEffect; 


        //Switch statement to determine the final string to be added to playerEffectDesccription depending on playerStatToEffect
        switch(playerStatToEffect){

            case "HitPoints":
                
                playerEffectDescription += " damage every turn.";
                break;

            case "Attack":

                playerEffectDescription += " attack every turn.";
                break;

            case "Defense":

                playerEffectDescription += " defense every turn.";
                break;

            case "Speed":

                playerEffectDescription += " speed every turn.";

        }

    }


    //Creates the description for enemyEffectDescription
    public void createEnemyEnvironmentDescription(){

       enemyEffectDescription = "Enemy"; 

        //Checks the value of enemyStatEffect and the enemyStatToEffect and will add a specific String depending on value
        if(enemyStatEffect > 0)
            enemyEffectDescription += " gains "; //Label for gaining stats 
        else if(enemyStatEffect < 0 && enemyStatToEffect == "HitPoints")
            enemyEffectDescription += " takes "; //Label for taking damage
        else
            enemyEffectDescription += " loses "; //Label for losing stats   
            

        //Ternary Operator to check whether the enemyStatEffect is negative or positive
        enemyEffectDescription += (enemyStatEffect < 0) ? -enemyStatEffect : enemyStatEffect; //Ternary Operator


        //Switch statement to determine the final string to be added to enemyEffectDesccription depending on enemyStatToEffect
        switch(enemyStatToEffect){

            case "HitPoints":
                
                //Checks specifically for HitPoints whether statEffect is positive or negative and updates description.
                if(enemyStatEffect < 0)
                    enemyEffectDescription += " damage every turn.";
                else if(enemyStatEffect > 0)
                    enemyEffectDescription += " health every turn.";    
                
                break;


            case "Attack":

                enemyEffectDescription += " attack every turn.";
                break;

            case "Defense":

                enemyEffectDescription += " defense every turn.";
                break;

            case "Speed":

                enemyEffectDescription += " speed every turn.";
                break;

        }

    }


    //Affects the Player's stat depending on values
    public void effectPlayer(Player player){

        //Switch statement determines which stat to effect depending on playerStatToEffect
        switch(playerStatToEffect){

            case "HitPoints":
                
                player.setHitPoints(player.getHitPoints() + playerStatEffect); //Affect HitPoints
                break;

            case "Attack":

                player.setAttack(player.getAttack() + playerStatEffect); //Affect Attack
                break;

            case "Defense":

                player.setDefense(player.getDefense() + playerStatEffect); //Affect Defense
                break;

            case "Speed":

                player.setSpeed(player.getSpeed() + playerStatEffect); //Affect Speed


        }

    }
    

    //Affects the Enemy's stat depending on values
    public void effectEnemy(Enemy enemy){

        //Switch statement determines which stat to effect depending on enemyStatToEffect
        switch(enemyStatToEffect){

            case "HitPoints":
                
                enemy.setHitPoints(enemy.getHitPoints() + enemyStatEffect); //Affect HitPoints
                break;

            case "Attack":

                enemy.setAttack(enemy.getAttack() + enemyStatEffect); //Affect Attack
                break;

            case "Defense":

                enemy.setDefense(enemy.getDefense() + enemyStatEffect); //Affect Defense
                break;

            case "Speed":

                enemy.setSpeed(enemy.getSpeed() + enemyStatEffect); //Affect Speed

        }
        
    }
}
