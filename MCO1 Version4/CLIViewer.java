/**
 * Class file that handles rudimentary displaying of information in the command line.
 * <p>
 *     This class handles purely display related tasks.
 * </p>
 * <p>
 *     Setter methods are used to store objects whose data is to be displayed.
 * </p>
 * @author Martin
 */
public class CLIViewer {


    //Private Attributes
    private Player player; //Stores Player for getting information for display
    private Enemy enemy; //Stores Enemy for getting information for display
    private Environment environment; //Stores Environment for getting information for display

    //Logic Attributes
    boolean isCharging = false; //Checks whether Player is charging or not


    //Setters

    /**
     * Setter for storing a Player object to access its information
     * @param player Player object whose relevant info will be retrieved and displayed
     */
    public void setPlayer(Player player){

        this.player = player;

    }
    /**
     * Setter for storing an Enemy object to access its information
     * @param enemy Enemy object whose relevant info will be retrieved and displayed
     */
    public void setEnemy(Enemy enemy){

        this.enemy = enemy;

    }
    /**
     * Setter for storing an Environment object to access its information
     * @param environment Environment object whose relevant info will be retrieved and displayed
     */
    public void setEnvironment(Environment environment){

        this.environment = environment;

    }


    //Display Methods

    //Display main menu

    /**
     * Method for displaying the Main Menu of the game
     */
    public void displayMainMenu(){

        
        System.out.printf("\n\n__       __                                __\n");                     
        System.out.printf("/  |  _  /  |                              /  |\n");                   
        System.out.printf("$$ | / \\ $$ |  ______    ______    ______  $$/   ______    ______\n");  
        System.out.printf("$$ |/$  \\$$ | /      \\  /      \\  /      \\ /  | /      \\  /      \\ \n");
        System.out.printf("$$ /$$$  $$ | $$$$$$  |/$$$$$$  |/$$$$$$  |$$ |/$$$$$$  |/$$$$$$  |\n");
        System.out.printf("$$ $$/$$ $$ | /    $$ |$$ |  $$/ $$ |  $$/ $$ |$$ |  $$ |$$ |  $$/\n");
        System.out.printf("$$$$/  $$$$ |/$$$$$$$ |$$ |      $$ |      $$ |$$ \\__$$ |$$ |\n");      
        System.out.printf("$$$/    $$$ |$$    $$ |$$ |      $$ |      $$ |$$    $$/ $$ |\n");      
        System.out.printf("$$/      $$/  $$$$$$$/ $$/       $$/       $$/  $$$$$$/  $$/\n\n\n");      
                                                                   
        System.out.printf("=======================================================================\n");                                                                   
        System.out.printf("|                              MAIN MENU                              |\n");
        System.out.printf("|---------------------------------------------------------------------|\n");     
        System.out.printf("|       [A] Start Game                            [Q] Quit Game       |\n");                                                                   
        System.out.printf("=======================================================================\n"); 
                        
    }


    //Display selections display

    /**
     * Method for displaying the relevant selection of objects
     * @param type object type to be displayed (Weapon, Armor, Enemy, Consumable, Environment)
     * @param selection object which holds the ArrayList containing all the object data to be displayed
     */
    public void displaySelection(String type, SelectionController selection){

        int listSize; //Gets size of the ArrayList

        //Switch statement determines what selection to display depending on String type
        switch(type){

            case "Weapon":

                listSize = selection.getWeapons().size(); //Get size of Weapon ArrayList

                System.out.printf("\n\n\n=======================================================================\n"); 
                System.out.printf("|                     WEAPON SELECTION [%-2d CHOICES]                   |\n", Weapon.getTotalWeapons() + 1); 
                System.out.printf("|---------------------------------------------------------------------|\n"); 


                System.out.printf("|                                   | Type:         None              |\n"); 
                System.out.printf("|  [0] No Weapon                    | Attack:       None              |\n"); 
                System.out.printf("|                                   | SpeedPenalty: None              |\n"); 
                System.out.printf("|---------------------------------------------------------------------|\n"); 

                //For loop to display the different Weapons available and their stats from the ArrayList
                for(int i = 0; i < listSize; i++){

                    System.out.printf("|                                   | Type:         %-10s        |\n", selection.getWeapons().get(i).getType()); 
                    System.out.printf("|  [%d] %-20s         | Attack:       %-3d               |\n", i + 1, selection.getWeapons().get(i).getName(), selection.getWeapons().get(i).getAttack()); 
                    System.out.printf("|                                   | SpeedPenalty: %-3d               |\n", selection.getWeapons().get(i).getSpeedPenalty()); 
                    System.out.printf("|---------------------------------------------------------------------|\n"); 

                }

                System.out.printf("|                                                                     |\n"); 
                System.out.printf("=======================================================================\n"); 

                break;

            case "Armor":

                listSize = selection.getArmors().size(); //Get size of Armor ArrayList

                System.out.printf("\n\n\n=======================================================================\n"); 
                System.out.printf("|                      ARMOR SELECTION [%-2d CHOICES]                   |\n", Armor.getTotalArmor() + 1); 
                System.out.printf("|---------------------------------------------------------------------|\n"); 


                System.out.printf("|                                   | Type:         None              |\n"); 
                System.out.printf("|  [0] No Armor                     | Defense:      None              |\n"); 
                System.out.printf("|                                   | SpeedPenalty: None              |\n"); 
                System.out.printf("|---------------------------------------------------------------------|\n"); 

                //For loop to display the different Armors available and their stats from the ArrayList
                for(int i = 0; i < listSize; i++){

                    System.out.printf("|                                   | Type:         %-10s        |\n", selection.getArmors().get(i).getType()); 
                    System.out.printf("|  [%d] %-20s         | Defense:      %-3d               |\n", i + 1, selection.getArmors().get(i).getName(), selection.getArmors().get(i).getDefense()); 
                    System.out.printf("|                                   | SpeedPenalty: %-3d               |\n", selection.getArmors().get(i).getSpeedPenalty()); 
                    System.out.printf("|---------------------------------------------------------------------|\n");            

                }

                System.out.printf("|                                                                     |\n"); 
                System.out.printf("=======================================================================\n");

                break;

            case "Enemy":

                listSize = selection.getEnemies().size(); //Get size of Enemy ArrayList

                System.out.printf("\n\n\n=======================================================================\n"); 
                System.out.printf("|                     ENEMY SELECTION [%-2d CHOICES]                    |\n", Enemy.getTotalEnemies() ); 
                System.out.printf("|---------------------------------------------------------------------|\n"); 

                //For loop to display the different Enemies available and their stats from the ArrayList

                
                for(int i = 0; i < listSize; i++){

                    System.out.printf("|                                   | Type:         %-10s        |\n", selection.getEnemies().get(i).getType()); 
                    System.out.printf("|                                   | HitPoints:    %-10d        |\n", selection.getEnemies().get(i).getHitPoints()); 
                    System.out.printf("|  [%d] %-20s         | Attack:       %-3d               |\n", i + 1, selection.getEnemies().get(i).getName(), selection.getEnemies().get(i).getAttack()); 
                    System.out.printf("|                                   | Defense:      %-3d               |\n", selection.getEnemies().get(i).getDefense()); 
                    System.out.printf("|                                   | Speed:        %-3d               |\n", selection.getEnemies().get(i).getSpeed()); 
                    System.out.printf("|---------------------------------------------------------------------|\n");       

                }

                System.out.printf("|                                                                     |\n"); 
                System.out.printf("=======================================================================\n");

                break;

            case "Environment":

                listSize = selection.getEnvironments().size(); //Get size of Environment ArrayList

                System.out.printf("\n\n\n=======================================================================\n"); 
                System.out.printf("|                  ENVIRONMENT SELECTION [%-2d CHOICES]                 |\n", Environment.getTotalEnvironments()); 
                System.out.printf("|---------------------------------------------------------------------|\n"); 

                //For loop to display the different Enemies available and their stats from the ArrayList
                for(int i = 0; i < listSize; i++){
                    
                    System.out.printf("|  [%d] %-20s                                           |\n", i + 1, selection.getEnvironments().get(i).getName()); 
                    System.out.printf("|                                                                     |\n"); 
                    System.out.printf("|  Player Effects: %-40s           |\n", selection.getEnvironments().get(i).getPlayerEffectDescription()); 
                    System.out.printf("|  Enemy Effects:  %-40s           |\n", selection.getEnvironments().get(i).getEnemyEffectDescription()); 
                    System.out.printf("|---------------------------------------------------------------------|\n");              

                }

                System.out.printf("|                                                                     |\n"); 
                System.out.printf("=======================================================================\n");

                break;

            case "Consumable":

                listSize = selection.getConsumables().size(); //Get size of Environment ArrayList

                System.out.printf("\n\n\n=======================================================================\n"); 
                System.out.printf("|                   CONSUMABLES SELECTION [%-2d CHOICES]                |\n", Consumable.getTotalConsumables() + 1); 
                System.out.printf("|---------------------------------------------------------------------|\n"); 

                System.out.printf("|  [0] No Consumable                                                  |\n"); 
                System.out.printf("|                                                                     |\n"); 
                System.out.printf("|  Type:             None                                             |\n"); 
                System.out.printf("|  Player Effects:                                                    |\n"); 
                System.out.printf("|     [] None                                                         |\n");
                System.out.printf("|  Enemy Effects:                                                     |\n");
                System.out.printf("|     [] None                                                         |\n"); 
                System.out.printf("|  Turns Effect:     None                                             |\n");
                System.out.printf("|  Charges:          None                                             |\n");
                System.out.printf("|---------------------------------------------------------------------|\n");
 
                //For loop to display the different Consumables available and their stats from the ArrayList
                for(int i = 0; i < listSize; i++){

                    System.out.printf("|  [%d] %-20s                                           |\n", i + 1, selection.getConsumables().get(i).getName()); 
                    System.out.printf("|                                                                     |\n"); 
                    System.out.printf("|  Type:             %-15s                                  |\n", selection.getConsumables().get(i).getType()); 
                    System.out.printf("|  Player Effects:                                                    |\n"); 
                    System.out.printf("|     [] %-60s |\n", selection.getConsumables().get(i).getPlayerConsumableDescription()); 
                    System.out.printf("|  Enemy Effects:                                                     |\n");
                    System.out.printf("|     [] %-60s |\n", selection.getConsumables().get(i).getEnemyConsumableDescription());  

                    //Checks whether consumable is temporary or permanent and show display depending on condition.
                    if(selection.getConsumables().get(i).getAffectingTurns() > 0)
                        System.out.printf("|  Turns Effect:     %-2d                                               |\n", selection.getConsumables().get(i).getAffectingTurns());
                    else
                        System.out.printf("|  Turns Effect:     Permanent                                        |\n");
                    
                    System.out.printf("|  Charges:          %-2d                                               |\n", selection.getConsumables().get(i).getMaxCharges());
                    System.out.printf("|---------------------------------------------------------------------|\n");       

                }

                System.out.printf("|                                                                     |\n"); 
                System.out.printf("=======================================================================\n");

        }


    }


    //Display player choices

    /**
     * Method for displaying possible player actions in a given turn
     */
    public void displayChoices(){

        System.out.printf("==============================================================================\n");                                                                   
        System.out.printf("|                              PLAYER'S CHOICE                               |\n");
        System.out.printf("|----------------------------------------------------------------------------|\n");     
        System.out.printf("|  [A] Attack        [D] Defend         [C] Charge       [U] Use Consumable  |\n");                                                                   
        System.out.printf("==============================================================================\n"); 

    }

    
    //Dispaly game stats

    /**
     * Method for displaying current relevant combat data
     */
    public void displayGameBar(){

        System.out.printf("\n\n==============================================================================\n");
        System.out.printf("|   %-15s  |           IN PROGRESS            |   %-15s  |\n", player.getName(), enemy.getName());                                    
        System.out.printf("|   HitPoints: %-3d   |       ----Environment----        |   HitPoints: %-4d  |\n", player.getHitPoints(), enemy.getHitPoints());
        System.out.printf("|      Attack: %-3d   |           %-20s   |      Attack: %-3d   |\n", player.getAttack(), environment.getName(), enemy.getAttack());
        System.out.printf("|     Defense: %-3d   |                                  |     Defense: %-3d   |\n", player.getDefense(), enemy.getDefense());
        System.out.printf("|       Speed: %-3d   |                                  |       Speed: %-3d   |\n", player.getSpeed(), enemy.getSpeed());
        System.out.printf("|----------------------------------------------------------------------------|\n"); 
        System.out.printf("|       STATUS / EFFECTS           |               EQUIPMENT                 |\n"); 


        //Checks if player has a weapon equipped or not.
        if(player.getWeapon() != null)
            System.out.printf("|       Charging:   %-5s          |    Weapon:     %-20s     |\n", player.getIsCharging(), player.getWeapon().getName()); 
        else
            System.out.printf("|       Charging:   %-5s          |    Weapon:     None                     |\n", player.getIsCharging()); 
        

        //Statements check if player has armor and/or used a consumable with temporary effects.
        if(player.getArmor() != null && player.getHasConsumeTemp()) //Display content if player has armor and temporary consumable is used
            System.out.printf("|   Temp Effects:   %-2d             |    Armor:      %-20s     |\n", player.getConsumable().getAffectingTurns() - player.getConsumable().getTurnCounter(),  
                              player.getArmor().getName()); 
        else if(player.getArmor() == null && player.getHasConsumeTemp()) //Display content if no armor but temporary consumable is used
            System.out.printf("|   Temp Effects:   %-2d           |    Armor:      None                     |\n", 
                              player.getConsumable().getAffectingTurns() - player.getConsumable().getTurnCounter()); 
        else if(player.getArmor() != null && !player.getHasConsumeTemp()) //Display content if player has armor but no temporary consumable
            System.out.printf("|                                  |    Armor:      %-20s     |\n", player.getArmor().getName());         
        else //Display if neither exist
            System.out.printf("|                                  |    Armor:      None                     |\n"); 


        //Checks if player has a consumable equipped or not.
        if(player.getConsumable() != null)
            System.out.printf("|                                  |    Consumable: %-20s [%d] |\n", player.getConsumable().getName(), player.getConsumable().getChargesLeft()); 
        else
            System.out.printf("|                                  |    Consumable: None                     |\n");


        System.out.printf("|----------------------------------------------------------------------------|\n"); 
        System.out.printf("|   CURRENT ENVIRONMENT   |           ENVIRONMENT EFFECTS                    |\n"); 
        System.out.printf("|   %-20s  |  Player: %-40s|\n", environment.getName(), environment.getPlayerEffectDescription()); 
        System.out.printf("|                         |  Enemy:  %-40s|\n", environment.getEnemyEffectDescription()); 
        System.out.printf("==============================================================================\n"); 
    }

    
    //Display result of player's turn
    private void displayPlayerResult(String playerChoice){
        
        System.out.printf("------------------------------------------------------------------------------\n");
        System.out.printf("|                              PLAYER'S RESULT                               |\n");
        System.out.printf("------------------------------------------------------------------------------\n");

        if(playerChoice.equals("Attack")){

            //If condition to check if Player was charging or not. And display corresponding results.
            if(isCharging){

                System.out.printf("%s Charge Attacks %s!\n", player.getName(), enemy.getName());      
                System.out.printf("%s Dealt %d Damage! (Triple Damage!)\n", player.getName(),  player.obtainLastAttackDealt(this));
                isCharging = false;

            }
            else{

                System.out.printf("%s Attacks %s!\n", player.getName(), enemy.getName());      
                System.out.printf("%s Dealt %d Damage!\n", player.getName(),  player.obtainLastAttackDealt(this));

            } 

        }
        else if(playerChoice.equals("Defend")){

            System.out.printf("%s Defends Against %s!\n", player.getName(), enemy.getName());      
            System.out.printf("Next Attack Against %s is Halved!\n", player.getName()); 
            isCharging = false;
                
        }
        else if(playerChoice.equals("Charge")){

            if(isCharging)
                System.out.printf("%s Charges Again????\n", player.getName());  
            else        
                System.out.printf("%s Prepares to Charge!\n", player.getName());    

            System.out.printf("%s Next Attack Turn Will Deal Triple Damage!\n", player.getName()); 
            isCharging = true;  

        }
        else if(playerChoice.equals("Consume")){

            System.out.printf("%s Consumes %s!\n", player.getName(), player.getConsumable().getName());  

            if(player.getConsumable().getIsTemporary())    
                System.out.printf("%s Provides a Temporary Effect for %d Turns!\n", player.getConsumable().getName(), 
                                    player.getConsumable().getAffectingTurns() - player.getConsumable().getTurnCounter()); 
            else
                System.out.printf("%s Provides a Premanent Effect!\n", player.getConsumable().getName());
            
            if(player.getConsumable().getAffectsPlayer())
                System.out.printf(" %s\n", player.getConsumable().getPlayerConsumableDescription());  

            if(player.getConsumable().getAffectsEnemy())
                System.out.printf("%s\n", player.getConsumable().getEnemyConsumableDescription());                


            isCharging = false; 

        }

    }


    //Display result of Enemy's turn
    private void displayEnemyResult(String playerChoice){

        System.out.printf("------------------------------------------------------------------------------\n");
        System.out.printf("|                               ENEMY'S RESULT                               |\n");
        System.out.printf("------------------------------------------------------------------------------\n");

        //If condition to check if Player was charing or not. And display corresponding results.
        if(playerChoice.equals("Defend")){

            System.out.printf("%s Attacks %s But They Were Defending!\n", enemy.getName(), player.getName());      
            System.out.printf("%s Dealt %d Damage! (Damage Halved!)\n", enemy.getName(), enemy.obtainLastAttackDealt(this));

        }
        else{

            System.out.printf("%s Attacks %s!\n", enemy.getName(), player.getName()); 
            System.out.printf("%s Dealt %d Damage!\n", enemy.getName(), enemy.obtainLastAttackDealt(this));

        }
        
    }


    //Displays result of both Player's and Enemy's turn
    private void displayBothResult(String playerChoice){

        System.out.printf("------------------------------------------------------------------------------\n");
        System.out.printf("|                         PLAYER'S AND ENEMY'S RESULT                        |\n");
        System.out.printf("------------------------------------------------------------------------------\n");


        //Switch statement that will display depending on String playerChoice

        if(playerChoice.equals("Attack")){

            if(isCharging){

                System.out.printf("%s Charge Attacks and %s Attacks!\n", player.getName(), enemy.getName());      
                System.out.printf("%s Dealt %d Damage! (Triple Damage!)\n", player.getName(),  player.obtainLastAttackDealt(this));
                System.out.printf("%s Dealt %d Damage!\n", enemy.getName(), enemy.obtainLastAttackDealt(this));
                isCharging = false;

            }
            else{

                System.out.printf("%s and %s Attacks!\n", player.getName(), enemy.getName());      
                System.out.printf("%s Dealt %d Damage!\n", player.getName(),  player.obtainLastAttackDealt(this));
                System.out.printf("%s Dealt %d Damage!\n", enemy.getName(), enemy.obtainLastAttackDealt(this));

            }

        }
        else if(playerChoice.equals("Charge")){

            if(isCharging)
                System.out.printf("%s Charges Again????\n", player.getName());  
            else        
                System.out.printf("%s Prepares to Charge and %s Attacks!\n", player.getName(), enemy.getName());

            System.out.printf("%s Next Attack Turn Will Deal Triple Damage!\n", player.getName()); 
            System.out.printf("%s Dealt %d Damage!\n", enemy.getName(), enemy.obtainLastAttackDealt(this));
            isCharging = true;  

        }
            
    }


    //Display Environment effect's result
    private void displayEnvironmentResult(){

        System.out.printf("------------------------------------------------------------------------------\n");
        System.out.printf("|                             ENVIRONMENT RESULT                             |\n");
        System.out.printf("------------------------------------------------------------------------------\n");

        //If condition to check if environment has effects. Display result depending on condition.
        if(environment.getHasEffect()){

            System.out.printf("%s Has Affected %s and %s!\n", environment.getName(), player.getName(), enemy.getName()); 

            //Check if positive or negative values of playerStatEffect
            if(environment.getPlayerStatEffect() > 0)
                System.out.printf("%s's %s Is Increased by %d!\n", player.getName(), environment.getPlayerStatToEffect(), environment.getPlayerStatEffect()); 
            else
                System.out.printf("%s's %s Is Decreased By %d!\n", player.getName(), environment.getPlayerStatToEffect(), -environment.getPlayerStatEffect());            

            //Check if positive or negative values of enemyStatEffect
            if(environment.getEnemyStatEffect() > 0)
                System.out.printf("%s's %s Is Increased by %d!\n", enemy.getName(), environment.getEnemyStatToEffect(), environment.getEnemyStatEffect()); 
            else
                System.out.printf("%s's %s Is Decreased By %d!\n", enemy.getName(), environment.getEnemyStatToEffect(), -environment.getEnemyStatEffect());  

        }
        else
            System.out.printf("%s Has No Effect!\n", environment.getName());         
    
            


    }


    //Display the total turn result of both Player, Enemy, and Environment

    /**
     * Method for displaying the outcome of a given turn
     * @param playerChoice variable that is passed on to private display methods
     */
    public void displayTurnResult(String playerChoice){

        System.out.printf("\n\n==============================================================================\n");
        System.out.printf("|                                TURN RESULT                                 |\n");
        System.out.printf("==============================================================================\n");


        //If condition checks for who goes first(Same conditions for turnSystem)
        if(enemy.getSpeed() < player.getSpeed() || player.getIsDefending() || playerChoice.equals("Consume")){
            
            displayPlayerResult(playerChoice);
            displayEnemyResult(playerChoice);

        }
        else if(enemy.getSpeed() > player.getSpeed()){

            displayEnemyResult(playerChoice);
            displayPlayerResult(playerChoice);

        }
        else{

            displayBothResult(playerChoice);

        }

        displayEnvironmentResult(); //Display effects of the environment

        System.out.printf("==============================================================================\n\n\n");

    }


    //Display ending screens

    /**
     * Method for displaying the result of a given battle
     * @param winner Indicates who is the victor of the match, this can either be the player, enemy, or a tie
     */
    public void displayWinner(String winner){

        //Switch statement checks for String winner and display ending screen depending on result.
        switch(winner){

            case "Player":

                System.out.printf("\n\n\n __      __                         __       __  __            __ \n");
                System.out.printf("/  \\    /  |                       /  |  _  /  |/  |          /  |\n");
                System.out.printf("$$  \\  /$$/______   __    __       $$ | / \\ $$ |$$/  _______  $$ |\n");
                System.out.printf(" $$  \\/$$//      \\ /  |  /  |      $$ |/$  \\$$ |/  |/       \\ $$ |\n");
                System.out.printf("  $$  $$//$$$$$$  |$$ |  $$ |      $$ /$$$  $$ |$$ |$$$$$$$  |$$ |\n");
                System.out.printf("   $$$$/ $$ |  $$ |$$ |  $$ |      $$ $$/$$ $$ |$$ |$$ |  $$ |$$/\n"); 
                System.out.printf("    $$ | $$ \\__$$ |$$ \\__$$ |      $$$$/  $$$$ |$$ |$$ |  $$ | __\n");  
                System.out.printf("    $$ | $$    $$/ $$    $$/       $$$/    $$$ |$$ |$$ |  $$ |/  |\n");
                System.out.printf("    $$/   $$$$$$/   $$$$$$/        $$/      $$/ $$/ $$/   $$/ $$/\n"); 
                break;

            case "Enemy":

                System.out.printf("\n\n\n __      __                         __                                      __ \n");
                System.out.printf("/  \\    /  |                       /  |                                    /  |\n");
                System.out.printf("$$  \\  /$$/______   __    __       $$ |        ______    _______   ______  $$ |\n");
                System.out.printf(" $$  \\/$$//      \\ /  |  /  |      $$ |       /      \\  /       | /      \\ $$ |\n");
                System.out.printf("  $$  $$//$$$$$$  |$$ |  $$ |      $$ |      /$$$$$$  |/$$$$$$$/ /$$$$$$  |$$ |\n");
                System.out.printf("   $$$$/ $$ |  $$ |$$ |  $$ |      $$ |      $$ |  $$ |$$      \\ $$    $$ |$$/\\\n"); 
                System.out.printf("    $$ | $$ \\__$$ |$$ \\__$$ |      $$ |_____ $$ \\__$$ | $$$$$$  |$$$$$$$$/  __\n"); 
                System.out.printf("    $$ | $$    $$/ $$    $$/       $$       |$$    $$/ /     $$/ $$       |/  |\n");
                System.out.printf("    $$/   $$$$$$/   $$$$$$/        $$$$$$$$/  $$$$$$/  $$$$$$$/   $$$$$$$/ $$/\n");
                break;

            case "Tie":

                System.out.printf(" \n\n\n________  __            __ \n");
                System.out.printf("/        |/  |          /  |\n");
                System.out.printf("$$$$$$$$/ $$/   ______  $$ |\n");
                System.out.printf("   $$ |   /  | /      \\ $$ |\n");
                System.out.printf("   $$ |   $$ |/$$$$$$  |$$ |\n");
                System.out.printf("   $$ |   $$ |$$    $$ |$$/\n"); 
                System.out.printf("   $$ |   $$ |$$$$$$$$/  __\n"); 
                System.out.printf("   $$ |   $$ |$$       |/  |\n");
                System.out.printf("   $$/    $$/  $$$$$$$/ $$/\n"); 

        }

    }


    //Display ending choices for Player

    /**
     * Method for displaying the ending prompt giving the player the option to quit or retry a new battle
     */
    public void displayEndMainMenu(){

        System.out.printf("\n\n=======================================================================\n");                                                                   
        System.out.printf("|                              MAIN MENU                              |\n");
        System.out.printf("|---------------------------------------------------------------------|\n");     
        System.out.printf("|       [R] Retry                                      [Q] Quit       |\n");                                                                   
        System.out.printf("=======================================================================\n"); 

    }


}
