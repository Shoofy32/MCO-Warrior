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

    //Logic Attributes
    private boolean playerIsCharging = false; //Checks whether Player is charging or not
    private boolean enemyIsCharging = false; //Checks whether Enemy is charging or not
    private boolean playerPassiveActive = false; 
    private boolean enemyPassiveActive = false; 

    //Display Methods

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

                System.out.printf("\n\n\n=========================================================================================================================\n"); 
                System.out.printf("|                                             WEAPON SELECTION [%-2d CHOICES]                                             |\n", Weapon.getTotalWeapons() + 1); 
                System.out.printf("|-----------------------------------------------------------------------------------------------------------------------|\n"); 


                System.out.printf("|                                   | Type:         None            Attack:       None     SpeedPenalty: None           |\n"); 
                System.out.printf("|  [0] No Weapon                    | Passive Effect:   None                                                            |\n"); 
                System.out.printf("|                                   | Active Effect:    None                                                            |\n"); 
                System.out.printf("|-----------------------------------------------------------------------------------------------------------------------|\n"); 

                //For loop to display the different Weapons available and their stats from the ArrayList
                for(int i = 0; i < listSize; i++){

                    System.out.printf("|                                   | Type:         %-10s      Attack:       %-3d      SpeedPenalty: %-3d            |\n", selection.getWeapons().get(i).getType(), selection.getWeapons().get(i).getAttack(), selection.getWeapons().get(i).getSpeedPenalty()); 
                    System.out.printf("|  [%d] %-20s         | Passive Effect:                                                                   |\n", i + 1, selection.getWeapons().get(i).getName()); 
                    System.out.printf("|                                   | %-80s  |\n", selection.getWeapons().get(i).getAbility().getAbilityDecsription());
                    System.out.printf("|                                   | Active Effect:                                                                    |\n"); 
                    if(selection.getWeapons().get(i) instanceof EnchantedWeapon){

                        System.out.printf("|                                   | %-80s  |\n", ((EnchantedWeapon) selection.getWeapons().get(i)).getWeaponSkill().getskillDescription());
                        System.out.printf("|                                   | Skill Condition:                                                                  |\n"); 
                        System.out.printf("|                                   | %-80s  |\n", ((EnchantedWeapon) selection.getWeapons().get(i)).getWeaponSkill().getskillConditionDescription());

                    }
                    else
                        System.out.printf("|                                   | Active Effect:    None                                                            |\n");                     
                    System.out.printf("|-----------------------------------------------------------------------------------------------------------------------|\n"); 

                }

                System.out.printf("|                                                                                                                       |\n"); 
                System.out.printf("=========================================================================================================================\n"); 

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

                System.out.printf("\n\n\n=========================================================================================================================\n"); 
                System.out.printf("|                                            ENEMY SELECTION [%-2d CHOICES]                                               |\n", Enemy.getTotalEnemies() ); 
                System.out.printf("|-----------------------------------------------------------------------------------------------------------------------|\n"); 

                //For loop to display the different Enemies available and their stats from the ArrayList

                
                for(int i = 0; i < listSize; i++){

                    String enemyWeapon = (selection.getEnemies().get(i).getWeapon() != null) ? selection.getEnemies().get(i).getWeapon().getName()  : "None";
                    String enemyConsumable = (selection.getEnemies().get(i).getConsumable() != null) ? selection.getEnemies().get(i).getConsumable().getName()  : "None";

                    System.out.printf("|                                   | Type:         %-10s     Weapon:        %-20s                  |\n", selection.getEnemies().get(i).getType(), enemyWeapon); 
                    System.out.printf("|                                   | HitPoints:    %-10d     Consumable:    %-20s                  |\n", selection.getEnemies().get(i).getHitPoints(), enemyConsumable); 
                    System.out.printf("|  [%d] %-20s         | Attack:       %-3d                                                                 |\n", i + 1, selection.getEnemies().get(i).getName(), selection.getEnemies().get(i).getAttack()); 
                    System.out.printf("|                                   | Defense:      %-3d                                                                 |\n", selection.getEnemies().get(i).getDefense()); 
                    System.out.printf("|                                   | Speed:        %-3d                                                                 |\n", selection.getEnemies().get(i).getSpeed());
                    System.out.printf("|-----------------------------------------------------------------------------------------------------------------------|\n");       

                }

                System.out.printf("|                                                                                                                       |\n"); 
                System.out.printf("=========================================================================================================================\n");

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

        System.out.printf("=========================================================================================================================\n");                                                                   
        System.out.printf("|                                                    PLAYER'S CHOICE                                                    |\n");
        System.out.printf("|-----------------------------------------------------------------------------------------------------------------------|\n");     
        System.out.printf("|    [A] Attack          [D] Defend           [C] Charge         [U] Use Consumable         [F] No Skill Available      |\n");                                                                   
        System.out.printf("=========================================================================================================================\n"); 

    }


    /**
     * Method for displaying possible player actions in a given turn w/ Skill available
     * @param hasMetConditions dictates whether to display a skill is available to use or not
     */
    public void displayChoices(boolean hasMetConditions){

        String isSkillActive = (hasMetConditions) ? "Active" : "Deactivated";
        System.out.printf("=========================================================================================================================\n");                                                                   
        System.out.printf("|                                                    PLAYER'S CHOICE                                                    |\n");
        System.out.printf("|-----------------------------------------------------------------------------------------------------------------------|\n");     
        System.out.printf("|    [A] Attack          [D] Defend          [C] Charge         [U] Use Consumable        [F] Use Skill (%-11s)   |\n", isSkillActive);                                                                   
        System.out.printf("=========================================================================================================================\n"); 

    }

    
    //Dispaly game stats

    /**
     * Method for displaying current relevant combat data
     * @param enemy enemy object that is fighting the player
     * @param environment environment wherein battle is currently being held
     * @param player player object being controlled by the user
     */
    public void displayGameBar(Player player, Enemy enemy, Environment environment){

        String playerWeaponName = "None";
        String playerSkillActive = "Disabled";
        int playerRequiredSkillCondition = 0;
        int playerCurrentSkillCondition = 0;

        if(player.getWeapon() != null){
            
            playerWeaponName = player.getWeapon().getName();

            if(player.getWeapon() instanceof EnchantedWeapon){

                playerSkillActive = ((EnchantedWeapon) player.getWeapon()).getWeaponSkill().getHasMetConditions() ?  "Active" : "Disabled";
                playerRequiredSkillCondition = ((EnchantedWeapon) player.getWeapon()).getWeaponSkill().getRequiredConditionCounter();
                playerCurrentSkillCondition = ((EnchantedWeapon) player.getWeapon()).getWeaponSkill().getConditionCounter();

            }

            

        }

        String playerArmorName = (player.getArmor() != null) ? player.getArmor().getName() : "None";
        String playerConsumableName = (player.getConsumable() != null) ? player.getConsumable().getName() : "None";
        int playerConsumableCharges = 0;
        int playerTempEffectTurns = 0;

        if(player.getConsumable() != null){

            playerConsumableCharges = player.getConsumable().getChargesLeft();
            playerTempEffectTurns = (player.getHasConsumeTemp()) ? player.getConsumable().getAffectingTurns() - player.getConsumable().getTurnCounter() : 0;
        }

        String enemyWeaponName = "None";        
        String enemySkillActive = "Disabled";
        int enemyRequiredSkillCondition = 0;
        int enemyCurrentSkillCondition = 0;

        if(enemy.getWeapon() != null){

            enemyWeaponName = enemy.getWeapon().getName();

            if(enemy.getWeapon() instanceof EnchantedWeapon){

                enemySkillActive = ((EnchantedWeapon) enemy.getWeapon()).getWeaponSkill().getHasMetConditions() ?  "Active" : "Disabled";
                enemyRequiredSkillCondition = ((EnchantedWeapon) enemy.getWeapon()).getWeaponSkill().getRequiredConditionCounter();
                enemyCurrentSkillCondition = ((EnchantedWeapon) enemy.getWeapon()).getWeaponSkill().getConditionCounter();

            }


        }

        String enemyConsumableName = (enemy.getConsumable() != null) ? enemy.getConsumable().getName() : "None";
        int enemyConsumableCharges = 0;
        int enemyTempEffectTurns = 0;

        if(enemy.getConsumable() != null){

            enemyConsumableCharges = enemy.getConsumable().getChargesLeft();
            enemyTempEffectTurns = (enemy.getHasConsumeTemp()) ? enemy.getConsumable().getAffectingTurns() - enemy.getConsumable().getTurnCounter() : 0;
        }

        System.out.printf("\n\n=========================================================================================================================\n");
        System.out.printf("|               %-15s    STATUS / EFFECTS          |               %-15s    STATUS / EFFECTS        |\n", player.getName(), enemy.getName());                                    
        System.out.printf("|           HitPoints: %-3d         Charging:     %-5s       |           HitPoints: %-4d        Charging:   %-5s       |\n", player.getHitPoints(), player.getIsCharging(), enemy.getHitPoints(), enemy.getIsCharging());
        System.out.printf("|           Attack:    %-3d         Skill: %-8s [%02d/%02d]   |           Attack:    %-3d         Skill: %-8s [%02d/%02d] |\n", player.getAttack(), playerSkillActive, playerCurrentSkillCondition, playerRequiredSkillCondition,
                          enemy.getAttack(), enemySkillActive, enemyCurrentSkillCondition, enemyRequiredSkillCondition);
        System.out.printf("|           Defense:   %-3d         Temp Effects: %-2d          |           Defense:   %-3d         Temp Effects: %-2d        |\n", player.getDefense(), playerTempEffectTurns, enemy.getDefense(), enemyTempEffectTurns);
        System.out.printf("|           Speed:     %-3d                                   |           Speed:     %-3d                                 |\n", player.getSpeed(), enemy.getSpeed());
        System.out.printf("|-----------------------------------------------------------------------------------------------------------------------|\n"); 
        System.out.printf("|               PLAYER EQUIPMENT                             |               ENEMY EQUIPMENT                            |\n"); 
        System.out.printf("|               Weapon:     %-20s             |               Weapon:     %-20s           |\n", playerWeaponName, enemyWeaponName); 
        System.out.printf("|               Armor:      %-20s             |               Consumable: %-20s [%d]       |\n", playerArmorName, enemyConsumableName, enemyConsumableCharges); 
        System.out.printf("|               Consumable: %-20s [%d]         |                                                          |\n", playerConsumableName, playerConsumableCharges); 
        System.out.printf("|-----------------------------------------------------------------------------------------------------------------------|\n"); 
        System.out.printf("|        CURRENT ENVIRONMENT             |                             ENVIRONMENT EFFECTS                              |\n"); 
        System.out.printf("|         %-30s |  Player: %-60s        |\n", environment.getName(), environment.getPlayerEffectDescription()); 
        System.out.printf("|                                        |  Enemy:  %-60s        |\n", environment.getEnemyEffectDescription()); 
        System.out.printf("=========================================================================================================================\n"); 

    }

    
    //Display result of player's turn
    private void displayPlayerResult(Player player, Enemy enemy, String playerChoice, String enemyChoice){
      
        if(playerPassiveActive)
            System.out.printf("%s %s\n", player.getName() , player.getWeapon().getAbility().getAbilityDisplayDescription());

        if(player.getWeapon() != null && player.getWeapon() instanceof EnchantedWeapon && (player.getTurnInputAction() == 'F' || (player.getTurnInputAction() != 'F' && ((EnchantedWeapon) player.getWeapon()).getWeaponSkill().getTurnsActive())))
            System.out.printf("%s\n", ((EnchantedWeapon) player.getWeapon()).getWeaponSkill().getActiveSkillResultDescription());

        //If conditions to check playerChoice and display corresponding result depdning on String value
        if(playerChoice.equals("Attack")){

            for(int i = 0; i < player.getTimesToAttack(); i++){

                //If condition to check if Player was charging or not. And display corresponding results.
                if(playerIsCharging)
                    System.out.printf("%s Charge Attacks %s! (Attack is multiplied by %d!)\n", player.getName(), enemy.getName(), player.getChargeMultiplier());      
                else
                    System.out.printf("%s Attacks %s!\n", player.getName(), enemy.getName());      


                if(enemyChoice.equals("Defense"))
                    System.out.printf("%s Defends Against %s! (Next Attack Against %s is Halved!)\n", enemy.getName(), player.getName(), enemy.getName()); 


                if(playerChoice.equals("Attack") && playerIsCharging && enemyChoice.equals("Defend")){

                    System.out.printf("%s Dealt %d Damage! (w/ Charge) (w/ Enemy Defending)\n", player.getName(),  player.getLastAttackDealt());
                    
                }
                else if(playerChoice.equals("Attack") && !playerIsCharging && enemyChoice.equals("Defend"))
                    System.out.printf("%s Dealt %d Damage! (w/ Enemy Defending)\n", player.getName(),  player.getLastAttackDealt());
                else if(playerChoice.equals("Attack") && playerIsCharging)
                    System.out.printf("%s Dealt %d Damage! (w/ Charge)\n", player.getName(),  player.getLastAttackDealt());       
                else
                    System.out.printf("%s Dealt %d Damage!\n", player.getName(),  player.getLastAttackDealt()); 

            }
 
                
            playerIsCharging = false;


        }
        else if(playerChoice.equals("Defend")){

            System.out.printf("%s Defends Against %s! (Next Attack Against %s is Halved!)\n", player.getName(), enemy.getName(), player.getName()); 
            playerIsCharging = false;
                
        }
        else if(playerChoice.equals("Charge")){

            //Checks if player is Charging or not to display a unique dialogue
            if(playerIsCharging)
                System.out.printf("%s Charges Again????\n", player.getName());  
            else        
                System.out.printf("%s Prepares to Charge!\n", player.getName());    

            System.out.printf("%s Next Attack Turn Will Deal %dx Damage!\n", player.getName(), player.getChargeMultiplier());

            playerIsCharging = true;  

        }
        else if(playerChoice.equals("Consume")){

            System.out.printf("%s Consumes %s!\n", player.getName(), player.getConsumable().getName());  

            //If conditions to check to check if the Consumable that the player has is temporary or not.
            if(player.getConsumable().getIsTemporary())    
                System.out.printf("%s Provides a Temporary Effect for %d Turns!\n", player.getConsumable().getName(), 
                                    player.getConsumable().getAffectingTurns() - player.getConsumable().getTurnCounter()); 
            else
                System.out.printf("%s Provides a Premanent Effect!\n", player.getConsumable().getName());
            
            //If condition checks if the Consumable affects the Player and gets the Player description if so
            if(player.getConsumable().getAffectsHolder())
                System.out.printf(" %s\n", player.getConsumable().getPlayerConsumableDescription());  

            //If condition checks if the Consumable affects the Enemy and gets the Enemy description if so
            if(player.getConsumable().getAffectsTarget())
                System.out.printf("%s\n", player.getConsumable().getEnemyConsumableDescription());                


            playerIsCharging = false; 

        }

    }


    //Display result of Enemy's turn
    private void displayEnemyResult(Player player, Enemy enemy, String playerChoice, String enemyChoice){

        if(enemyPassiveActive)
            System.out.printf("%s %s\n", enemy.getName(), enemy.getWeapon().getAbility().getAbilityDisplayDescription());

        if(enemy.getWeapon() != null && (enemy.getTurnInputAction() == 'F' || (enemy.getTurnInputAction() != 'F' && ((EnchantedWeapon) enemy.getWeapon()).getWeaponSkill().getTurnsActive())))
            System.out.printf("%s\n", ((EnchantedWeapon) enemy.getWeapon()).getWeaponSkill().getActiveSkillResultDescription());

        //If conditions to check playerChoice and display corresponding result depdning on String value
        if(enemyChoice.equals("Attack")){

            for(int i = 0; i < enemy.getTimesToAttack(); i++){

                //If condition to check if Player was charging or not. And display corresponding results.
                if(enemyIsCharging)
                    System.out.printf("%s Charge Attacks %s! (Attack is multiplied by %d!)\n", enemy.getName(), player.getName(), enemy.getChargeMultiplier());      
                else
                    System.out.printf("%s Attacks %s!\n", enemy.getName(), player.getName());      


                if(enemyChoice.equals("Defense"))
                    System.out.printf("%s Defends Against %s! (Next Attack Against %s is Halved!)\n", enemy.getName(), player.getName(), player.getName()); 


                if(enemyChoice.equals("Attack") && enemyIsCharging && playerChoice.equals("Defend")){

                    System.out.printf("%s Dealt %d Damage! (w/ Charge) (w/ Player Defending)\n", enemy.getName(),  enemy.getLastAttackDealt());


                }
                else if(enemyChoice.equals("Attack") && !enemyIsCharging && playerChoice.equals("Defend"))
                    System.out.printf("%s Dealt %d Damage! (w/ Player Defending)\n", enemy.getName(),  enemy.getLastAttackDealt());        
                else if(enemyChoice.equals("Attack") && enemyIsCharging)
                    System.out.printf("%s Dealt %d Damage! (w/ Charge)\n", enemy.getName(),  enemy.getLastAttackDealt());        
                else
                    System.out.printf("%s Dealt %d Damage!\n", enemy.getName(),  enemy.getLastAttackDealt()); 
                    
            }
                
            enemyIsCharging = false;


        }
        else if(enemyChoice.equals("Defend")){

            System.out.printf("%s Defends Against %s! (Next Attack Against %s is Halved!)\n", enemy.getName(), player.getName(), enemy.getName()); 
            enemyIsCharging = false;
                
        }
        else if(enemyChoice.equals("Charge")){

            //Checks if player is Charging or not to display a unique dialogue
            if(enemyIsCharging)
                System.out.printf("%s Charges Again????\n", enemy.getName());  
            else        
                System.out.printf("%s Prepares to Charge!\n", enemy.getName());    

            System.out.printf("%s Next Attack Turn Will Deal %dx Damage!\n", enemy.getName(), enemy.getChargeMultiplier()); 
            enemyIsCharging = true;  

        }
        else if(enemyChoice.equals("Consume")){

            System.out.printf("%s Consumes %s!\n", enemy.getName(), enemy.getConsumable().getName());  

            //If conditions to check to check if the Consumable that the player has is temporary or not.
            if(enemy.getConsumable().getIsTemporary())    
                System.out.printf("%s Provides a Temporary Effect for %d Turns!\n", enemy.getConsumable().getName(), 
                                    enemy.getConsumable().getAffectingTurns() - enemy.getConsumable().getTurnCounter()); 
            else
                System.out.printf("%s Provides a Premanent Effect!\n", enemy.getConsumable().getName());
            
            //If condition checks if the Consumable affects the Player and gets the Player description if so
            if(enemy.getConsumable().getAffectsHolder())
                System.out.printf(" %s\n", enemy.getConsumable().getPlayerConsumableDescription());  

            //If condition checks if the Consumable affects the Enemy and gets the Enemy description if so
            if(enemy.getConsumable().getAffectsTarget())
                System.out.printf("%s\n", enemy.getConsumable().getEnemyConsumableDescription());                


            enemyIsCharging = false; 

        }
        
    }


    //Display Environment effect's result
    private void displayEnvironmentResult(Player player, Enemy enemy, Environment environment){

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
     * Method for displaying the outcome of a turn
     * @param enemy enemy object that is fighting the player
     * @param environment environment wherein battle is currently being held
     * @param player player object being controlled by the user
     * @param playerChoice player's chosen action in the turn
     * @param enemyChoice enemy's chosen action in the turn
     */
    public void displayTurnResult(Player player, Enemy enemy, Environment environment, String playerChoice, String enemyChoice){

        if(player.getWeapon() != null)
            playerPassiveActive = player.getWeapon().getAbility().getHasMetConditions();
        
        if(enemy.getWeapon() != null)
            enemyPassiveActive = enemy.getWeapon().getAbility().getHasMetConditions();

        System.out.printf("\n\n==============================================================================\n");
        System.out.printf("|                                TURN RESULT                                 |\n");
        System.out.printf("==============================================================================\n");

        displayEnvironmentResult(player, enemy, environment); //Display effects of the environment
        
        //If condition checks for who goes first(Same conditions for turnSystem)
        if((enemy.getSpeed() < player.getSpeed() || player.getIsDefending() || player.getTurnInputAction() == 'C') && !enemy.getIsDefending()){
            
            System.out.printf("------------------------------------------------------------------------------\n");
            System.out.printf("|                              PLAYER'S RESULT                               |\n");
            System.out.printf("------------------------------------------------------------------------------\n");
            displayPlayerResult(player, enemy, playerChoice, enemyChoice);

            System.out.printf("------------------------------------------------------------------------------\n");
            System.out.printf("|                               ENEMY'S RESULT                               |\n");
            System.out.printf("------------------------------------------------------------------------------\n");
            displayEnemyResult(player, enemy, playerChoice, enemyChoice);

        }
        else if((enemy.getSpeed() > player.getSpeed() || enemy.getIsDefending() || player.getTurnInputAction() == 'C') && !player.getIsDefending()){

            System.out.printf("------------------------------------------------------------------------------\n");
            System.out.printf("|                               ENEMY'S RESULT                               |\n");
            System.out.printf("------------------------------------------------------------------------------\n");
            displayEnemyResult(player, enemy, playerChoice, enemyChoice);

            System.out.printf("------------------------------------------------------------------------------\n");
            System.out.printf("|                              PLAYER'S RESULT                               |\n");
            System.out.printf("------------------------------------------------------------------------------\n");
            displayPlayerResult(player, enemy, playerChoice, enemyChoice);

        }
        else{

            System.out.printf("------------------------------------------------------------------------------\n");
            System.out.printf("|                         PLAYER'S AND ENEMY'S RESULT                        |\n");
            System.out.printf("------------------------------------------------------------------------------\n");
            displayPlayerResult(player, enemy, playerChoice, enemyChoice);
            displayEnemyResult(player, enemy, playerChoice, enemyChoice);

        }

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

        playerIsCharging = false;
        enemyIsCharging = false;

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
