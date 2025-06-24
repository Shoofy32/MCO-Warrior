public class CLIViewer {


    //Private Attributes
    private Player player; //Stores Player for getting information for display
    private Enemy enemy; //Stores Enemy for getting information for display
    private Environment environment; //Stores Environment for getting information for display

    //Logic Attributes
    boolean isCharging = false; //Checks whether Player is charging or not


    //Setters
    public void setPlayer(Player player){

        this.player = player;

    }

    public void setEnemy(Enemy enemy){

        this.enemy = enemy;

    }

    public void setEnvironment(Environment environment){

        this.environment = environment;

    }


    //Display Methods

    //Display main menu
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

        }


    }


    //Display player choices
    public void displayChoices(){

        System.out.printf("==============================================================================\n");                                                                   
        System.out.printf("|                              PLAYER'S CHOICE                               |\n");
        System.out.printf("|----------------------------------------------------------------------------|\n");     
        System.out.printf("|    [A] Attack                   [D] Defend                   [C] Charge    |\n");                                                                   
        System.out.printf("==============================================================================\n"); 

    }

    
    //Dispaly game stats
    public void displayGameBar(){

        System.out.printf("==============================================================================\n");
        System.out.printf("|   %-15s  |           IN PROGRESS            |   %-15s  |\n", player.getName(), enemy.getName());                                    
        System.out.printf("|   HitPoints: %-3d   |       ----Environment----        |   HitPoints: %3d   |\n", player.getHitPoints(), enemy.getHitPoints());
        System.out.printf("|      Attack: %-3d   |           %-15s        |      Attack: %-3d   |\n", player.getAttack(), environment.getName(), enemy.getAttack());
        System.out.printf("|     Defense: %-3d   |                                  |     Defense: %-3d   |\n", player.getDefense(), enemy.getDefense());
        System.out.printf("|       Speed: %-3d   |                                  |       Speed: %-3d   |\n", player.getSpeed(), enemy.getSpeed());
        System.out.printf("|----------------------------------------------------------------------------|\n"); 
        System.out.printf("|       STATUS / EFFECTS           |               EQUIPMENT                 |\n"); 

        if(player.getWeapon() != null)
            System.out.printf("|       Charging:   %-5s          |    Weapon:     %-20s     |\n", player.getIsCharging(), player.getWeapon().getName()); 
        else
            System.out.printf("|       Charging:   %-5s          |    Weapon:     None                     |\n", player.getIsCharging()); 
        
        if(player.getArmor() != null )
            System.out.printf("|                                  |    Armor:      %-20s     |\n", player.getArmor().getName()); 
        else
            System.out.printf("|                                  |    Armor:      None                     |\n"); 

        System.out.printf("|                                  |    Consumable: None                     |\n"); 
        System.out.printf("|----------------------------------------------------------------------------|\n"); 
        System.out.printf("|   CURRENT ENVIRONMENT   |           ENVIRONMENT EFFECTS                    |\n"); 
        System.out.printf("|   %-15s       |  Player: %-40s|\n", environment.getName(), environment.getPlayerEffectDescription()); 
        System.out.printf("|                         |  Enemy:  %-40s|\n", environment.getEnemyEffectDescription()); 
        System.out.printf("==============================================================================\n"); 
    }

    
    //Display result of player's turn
    private void displayPlayerResult(String playerChoice){
        
        System.out.printf("------------------------------------------------------------------------------\n");
        System.out.printf("|                              PLAYER'S RESULT                               |\n");
        System.out.printf("------------------------------------------------------------------------------\n");

        //Switch statement that will display depending on String playerChoice
        switch(playerChoice){

            case "Attack":
                
                //If condition to check if Player was charging or not. And display corresponding results.
                if(isCharging){

                    System.out.printf("%s Charge Attacks %s!\n", player.getName(), enemy.getName());      
                    System.out.printf("%s Dealt %-3d Damage! (Triple Damage!)\n", player.getName(),  player.obtainLastAttackDealt(this));
                    isCharging = false;

                }
                else{

                    System.out.printf("%s Attacks %s!\n", player.getName(), enemy.getName());      
                    System.out.printf("%s Dealt %-3d Damage!\n", player.getName(),  player.obtainLastAttackDealt(this));

                } 

                break;

            case "Defend":

                System.out.printf("%s Defends Against %s!\n", player.getName(), enemy.getName());      
                System.out.printf("Next Attack Against %s is Halved!\n", player.getName()); 
                isCharging = false;
                break;

            case "Charge":

                System.out.printf("%s Prepares to Charge!\n", player.getName());      
                System.out.printf("%s Next Attack Turn Will Deal Triple Damage!\n", player.getName()); 
                isCharging = true;  

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
            System.out.printf("%s Dealt %-3d Damage! (Damage Halved!)\n", enemy.getName(), enemy.obtainLastAttackDealt(this));

        }
        else{

            System.out.printf("%s Attacks %s!\n", enemy.getName(), player.getName()); 
            System.out.printf("%s Dealt %-3d Damage!\n", enemy.getName(), enemy.obtainLastAttackDealt(this));

        }
        
    }


    //Displays result of both Player's and Enemy's turn
    private void displayBothResult(String playerChoice){

        System.out.printf("------------------------------------------------------------------------------\n");
        System.out.printf("|                         PLAYER'S AND ENEMY'S RESULT                        |\n");
        System.out.printf("------------------------------------------------------------------------------\n");


        //Switch statement that will display depending on String playerChoice
        switch(playerChoice){

            case "Attack":
                
                //If condition to check if Player was charing or not. And display corresponding results.
                if(isCharging){

                    System.out.printf("%s Charge Attacks and %s Attacks!\n", player.getName(), enemy.getName());      
                    System.out.printf("%s Dealt %-3d Damage! (Triple Damage!)\n", player.getName(),  player.obtainLastAttackDealt(this));
                    System.out.printf("%s Dealt %-3d Damage!\n", enemy.getName(), enemy.obtainLastAttackDealt(this));
                    isCharging = false;

                }
                else{

                    System.out.printf("%s and %s Attacks!\n", player.getName(), enemy.getName());      
                    System.out.printf("%s Dealt %-3d Damage!\n", player.getName(),  player.obtainLastAttackDealt(this));
                    System.out.printf("%s Dealt %-3d Damage!\n", enemy.getName(), enemy.obtainLastAttackDealt(this));

                } 

                break;

            case "Charge":

                System.out.printf("%s Prepares to Charge and %s Attacks!\n", player.getName());      
                System.out.printf("%s Next Attack Turn Will Deal Triple Damage!\n", player.getName()); 
                System.out.printf("%s Dealt %-3d Damage!\n", enemy.getName(), enemy.obtainLastAttackDealt(this));
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
                System.out.printf("%s's %s Is Increased by %d!\n", player.getName(), environment.getEnemyStatToEffect(), environment.getEnemyStatEffect()); 
            else
                System.out.printf("%s's %s Is Decreased By %d!\n", player.getName(), environment.getEnemyStatToEffect(), -environment.getEnemyStatEffect());  

        }
        else
            System.out.printf("%s Has No Effect!\n", environment.getName());         
    
            


    }


    //Display the total turn result of both Player, Enemy, and Environment
    public void displayTurnResult(String playerChoice){

        System.out.printf("\n\n==============================================================================\n");
        System.out.printf("|                                TURN RESULT                                 |\n");
        System.out.printf("==============================================================================\n");


        //If condition checks for who goes first(Same conditions for turnSystem)
        if(enemy.getSpeed() < player.getSpeed() || player.getIsDefending()){
            
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
    public void displayEndMainMenu(){

        System.out.printf("\n\n=======================================================================\n");                                                                   
        System.out.printf("|                              MAIN MENU                              |\n");
        System.out.printf("|---------------------------------------------------------------------|\n");     
        System.out.printf("|       [R] Retry                                      [Q] Quit       |\n");                                                                   
        System.out.printf("=======================================================================\n"); 

    }

}
