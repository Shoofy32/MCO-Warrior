import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.util.Scanner;


/**
 * Class file that controls the events currently running in the program.
 * <p>
 *     This class manages the currently running events/processes in the program.
 * </p>
 * <p>
 *     This class also handles the input from the user either while in the Main Menu or while in a battle.
 * </p>
 * <p>
 *     Events this class handles include ones like the selection of weapons, armor, consumables, enemies, environments.
 * </p>
 * @see SelectionController
 * @author Stefan_Martin
 */
public class EventController{
    
    //Main Attributes
    private Player player; //Stores player that will be used in the program
    private SelectionController selection; //Stores controller dealing with selection
    private CLIViewer cliView; //Stores Class that will be doing display
    private Enemy enemy; //Stores enemy that will be used in the program
    private Environment environment; //Stores the environment that will be used in the program

    //Logic Attributes
    private boolean isRunning = true; //Stores whether the game is still running or not

    //Copy Attributes
    private int enemyHitPointsCopy; //Stores a copy of enemy hitpoints chosen for reset purposes
    private int enemyAttackCopy; //Stores a copy of enemy attack for reset purposes
    private int enemyDefenseCopy; //Stores a copy of enemy defense for reset purposes
    private int enemySpeedCopy; //Stores a copy of enemy speed chosen for reset purposes

    //Constructor

    /**
     * Constructor for the EventController Class
     */
    public EventController(CLIViewer cliView, GUIViewer guiView){

        this.cliView = cliView;

        selection = new SelectionController(cliView);
        
    }

    
    //Getters

    /**
     * Getter method to return the selection controller
     * @return the selection controller class
     * @see SelectionController
     */
    public SelectionController getSelectionController(){

        return selection;

    }

    /**
     * Getter method to return the CLIViewer
     * @return the CLIViewer class
     * @see CLIViewer
     */
    public CLIViewer getDisplay(){

        return cliView;

    }

    /**
     * Method to run the initial starting menu of the program
     * @param input user's choice
     */
    public void start(Scanner input){

        char choiceInput = '\u0000'; //Sets choiceInput to null
        
        choiceInput = mainMenu(input); //Starts the main menu

        //Do-While loop that checks if the Player wishes to quit or not
        do{

            //While loop controls the game as long isRunning is true

            while(isRunning){

                cliView.displayGameBar(player, enemy, environment); //Displays Game Stats

                playerChoice(input); //Asks for Player choice for their turn

                turnSystem(); //Controls the turn system and the results

            }

            //Checks if player didn't choose Q (Quit Game)
            if(choiceInput != 'Q')
                choiceInput = retry(input); //Asks user if they wish to retry or quit

        }while(choiceInput != 'Q');

    }


    //EventController Methods

    /**
     * Method to manage the input coming in from the Main Menu
     * @param input The user's input
     * @return The user's choice to be used later on
     */
    public char mainMenu(Scanner input){

        char menuInput = 'Z';
        String name;

        //Do-while loop for invalid input
        do{

            cliView.displayMainMenu(); //Display main menu

            System.out.printf("Input (Char): ");
            try{
                menuInput = input.nextLine().toUpperCase().charAt(0); //Gets player input for main menu by getting the character in the index 0
            } catch (StringIndexOutOfBoundsException e) {
                System.out.printf("\nCannot enter an empty character\n");
            }
            if(menuInput != 'A' && menuInput != 'Q')
                System.out.printf("\nPlease provide a valid input.\n"); //Display invalid input

        }while(menuInput != 'A' && menuInput != 'Q');

        
        //If condition to check whether the player wished to start the game or quit
        if(menuInput == 'A'){

            //Do-while loop to check whether player exceeded character limit or not
            do{

                System.out.printf("\n\nEnter Player Name (15 Character Limit): ");
                name = input.nextLine(); //Asks for Player name input
                
                player = new Player(name, "Assets/Player/Player_Temp.png");

                //Checks whether Player name length is higher than 15
                if(player.getName().length() > 15)
                     System.out.printf("\nPlease provide a name within the character limit!\n"); //Display overflow error          

            
            }while(player.getName().length() > 15);


            selectChoices(input); //Calls method for selecting different choices for player.

        }

        else{

            isRunning = false;
            System.out.printf("\n\nTHANKS FOR PLAYING! :)\n"); //Prints out thank you message

        }

        return menuInput;
           
    }


    //Method for Player to select Weapon, Armor, Enemy, Consumable, and Environment
    private void selectChoices(Scanner input){

        //Weapon and Armor attributes that store selected choice
        Weapon equipWeapon = selection.selectWeapon(input); 
        Armor equipArmor = selection.selectArmor(input);
        Consumable equipConsumable = selection.selectConsumable(input);

        player.equipWeapon(equipWeapon); //Calls method for Player to equip chosen Weapon

        player.equipArmor(equipArmor); //Calls method for Player to equip chosen Armor

        player.equipConsumable(equipConsumable);

        enemy = selection.selectEnemy(input); //Updates enemy attribute to chosen Enemy
        
        //Copies the stats of the enemy to a seperate attribute storage in case of resetting
        enemyHitPointsCopy = enemy.getHitPoints();
        enemyAttackCopy = enemy.getAttack();
        enemyDefenseCopy = enemy.getDefense();
        enemySpeedCopy = enemy.getSpeed();

        environment = selection.selectEnvironment(input); //Updates environment attribute to chosen Environment

    }


    //Method to control the Turn System of the game
    //Parameter is the choice of the player during the game (Whether to attack, defend, charge, or consume)

    /**
     * Method to control actual running battle
     */
    public void turnSystem(){

        //Checks if the player has previously consumed a potion that provides temporary effects.
        if(player.getHasConsumeTemp())
            player.getConsumable().countAffectingTurns(player, enemy); //Calls counter and restores stats once timer ends

        //Checks if the enemy has previously consumed a potion that provides temporary effects.
        if(enemy.getHasConsumeTemp())
            enemy.getConsumable().countAffectingTurns(enemy, player); //Calls counter and restores stats once timer ends

        if(player.getTurnInputAction() == 'C')
            player.setChargeBase(player.getAttack());

        enemy.setChargeBase(enemy.getAttack());

        //Checks if the environment chosen has any effects or not
        if(environment.getHasEffect())
            environmentEffect(); //Call method to update Player and Enemy stats depending on effect
        
        //NEW
        if(player.getWeapon() != null)
            player.getWeapon().usePassiveAbility(player, enemy);

        if(enemy.getWeapon() != null)
            enemy.getWeapon().usePassiveAbility(enemy, player);


        /* If conditions to check the turn system of the game (Determines who goes first)
         * Turn is determined by the checkWinner() method. (If theres a checkWin() method before their turn, then their turn was second)
         */
        if((enemy.getSpeed() < player.getSpeed() || player.getTurnInputAction() == 'D' || player.getTurnInputAction() == 'U') && !enemy.getIsDefending()){ //Checks if player is faster, defending, or consumed
            

            player.think(enemy); //Player goes first

            checkWinner(); //Check for win condition

            //Checks if game is still running to prevent unecessary Enemy turns
            if(isRunning){

                enemy.think(player); //Enemy goes next
                checkWinner(); //Check for win condition

            }

        }
        else if(enemy.getSpeed() > player.getSpeed()){ //Checks if enemy is faster

            enemy.think(player); //Enemy goes first
            checkWinner(); //Check for win condition

            //Checks if game is still running to prevent unecessary Player turns
            if(isRunning){

                player.think(enemy); //Player goes next
                checkWinner(); //Check for win condition

            }

        }
        else{ //Both have the same speed

            //Player and enemy go at the same time
            player.think(enemy); 
            enemy.think(player);

            checkWinner(); //Check for win condition

        }

        //Check whether the game is still running in order to display turn results
        if(isRunning){

            if(player.getWeapon() instanceof EnchantedWeapon){

                ((EnchantedWeapon) player.getWeapon()).getWeaponSkill().checkSkillConditionCounter(player, enemy);

                if(player.getTurnInputAction() != 'F' && ((EnchantedWeapon) player.getWeapon()).getWeaponSkill().getTurnsActive())
                    ((EnchantedWeapon) player.getWeapon()).getWeaponSkill().createActiveSkillResultDescription(player, enemy);
            }


            if(enemy.getWeapon() instanceof EnchantedWeapon){

                ((EnchantedWeapon) enemy.getWeapon()).getWeaponSkill().checkSkillConditionCounter(enemy, player);
                
                if(enemy.getTurnInputAction() != 'F' && ((EnchantedWeapon) enemy.getWeapon()).getWeaponSkill().getTurnsActive())
                    ((EnchantedWeapon) enemy.getWeapon()).getWeaponSkill().createActiveSkillResultDescription(enemy, player);

            }


            
            cliView.displayTurnResult(player, enemy, environment, player.getLastCharacterAction(), enemy.getLastCharacterAction());
            postTurnEventChecks();
  
        }

    }

  
    private void postTurnEventChecks(){

        if(player.getIsDefending())
            player.stopDefending();
        
        if(enemy.getIsDefending())
            enemy.stopDefending();

        if(player.getWeapon() != null){

            player.getWeapon().getAbility().deactivateAbility(player);

            if(player.getWeapon() instanceof EnchantedWeapon)
                ((EnchantedWeapon) player.getWeapon()).getWeaponSkill().deactivateSkill(player, enemy);  

        }

        if(enemy.getWeapon() != null){

            enemy.getWeapon().getAbility().deactivateAbility(enemy);

            if(enemy.getWeapon() instanceof EnchantedWeapon)
                ((EnchantedWeapon) enemy.getWeapon()).getWeaponSkill().deactivateSkill(enemy, player);   

        }

        if(isRunning)
            enemy.updateTurnAction();
        else
            enemy.resetTurnAction();

    }


    //Method for Player's turn and their choice during this turn.

    /**
     * Method for validating user input for their actions in the game, this includes error and exception handling
     * @param input The user's input
     * @return the user's chosen action
     */
    public void playerChoice(Scanner input){

        char choiceInput = 'Z'; //Sets default choiceInput to newline

        //Display player choices for their turn depending if the weapon they used has a skill or not.
        if(player.getWeapon() instanceof EnchantedWeapon)
            cliView.displayChoices(((EnchantedWeapon) player.getWeapon()).getWeaponSkill().getHasMetConditions()); 
        else
            cliView.displayChoices();

        

        //Do-while to check for invalid input
        do{

    
            System.out.printf("Input: ");
          
            try{
                choiceInput = input.nextLine().toUpperCase().charAt(0); //Gets character input
            } catch (StringIndexOutOfBoundsException e) {
                
                System.out.printf("\n\n\n\n"); 
                cliView.displayGameBar(player, enemy, environment);
                System.out.printf("\nCannot enter an empty character\n");
                cliView.displayChoices(); //Display player choices for their turn

            }

            //Checks for invalid input
            if(choiceInput != 'A' && choiceInput != 'D' && choiceInput != 'C' && choiceInput != 'U' && choiceInput != 'F'){

                System.out.printf("\n\n\n\n"); 
                cliView.displayGameBar(player, enemy, environment);                
                System.out.printf("\nERROR: Please provide a valid input.\n"); 

                if(player.getWeapon() instanceof EnchantedWeapon)
                    cliView.displayChoices(((EnchantedWeapon) player.getWeapon()).getWeaponSkill().getHasMetConditions()); 
                else
                    cliView.displayChoices();

                choiceInput = 'Z'; //Returns to deafault value

            }
            else if(choiceInput == 'U' && player.getConsumable() == null){ //Checks if player tries to use a nonexistient consumable

                System.out.printf("\n\n\n\n"); 
                cliView.displayGameBar(player, enemy, environment);
                System.out.printf("\nERROR: No consumable equipped!\n");

                if(player.getWeapon() instanceof EnchantedWeapon)
                    cliView.displayChoices(((EnchantedWeapon) player.getWeapon()).getWeaponSkill().getHasMetConditions()); 
                else
                    cliView.displayChoices();

                choiceInput = 'Z'; //Returns to deafult value

            }
            else if(choiceInput == 'U' && player.getConsumable() != null && player.getConsumable().getChargesLeft() == 0){
                //Checks if player tries to use a consumable that is out of charges.

                System.out.printf("\n\n\n\n"); 
                cliView.displayGameBar(player, enemy, environment);
                System.out.printf("\nERROR: No charges left!\n");  

                if(player.getWeapon() instanceof EnchantedWeapon)
                    cliView.displayChoices(((EnchantedWeapon) player.getWeapon()).getWeaponSkill().getHasMetConditions()); 
                else
                    cliView.displayChoices();

                cliView.displayChoices(); //Display player choices for their turn
                choiceInput = 'Z'; //Returns to deafult value

            }
            else if(choiceInput == 'F' && (!(player.getWeapon() instanceof EnchantedWeapon) || !((EnchantedWeapon) player.getWeapon()).getWeaponSkill().getHasMetConditions())){

                System.out.printf("\n\n\n\n"); 
                cliView.displayGameBar(player, enemy, environment);
                System.out.printf("\nERROR: Skill is not available!\n");  

                if(player.getWeapon() instanceof EnchantedWeapon)
                    cliView.displayChoices(((EnchantedWeapon) player.getWeapon()).getWeaponSkill().getHasMetConditions()); 
                else
                    cliView.displayChoices();

                cliView.displayChoices(); //Display player choices for their turn
                choiceInput = 'Z'; //Returns to deafult value

            }

            

        }while(choiceInput == 'Z');  
        
        player.setTurnInputAction(choiceInput);

    }


    //Method that uses environment effects to update Player and Enemy stats
    private void environmentEffect(){

        environment.affectPlayer(player); //Affect Player stats
        environment.affectEnemy(enemy); //Affect Enemy stats

    }


    //Method checks for win conditions
    private void checkWinner(){

        //If condition checks if player and/or enemy have 0 hitpoints
        if(player.getHitPoints() <= 0 || enemy.getHitPoints() <= 0){

            isRunning = false; //Updates boolean value to false
            cliView.displayGameBar(player, enemy, environment); //Diplays final values of the game one last time

            //If condition checks who reached 0 hitpoints
            if(player.getHitPoints() > 0 && enemy.getHitPoints() <= 0)
                cliView.displayWinner("Player"); //Display win
            else if(player.getHitPoints() <= 0 && enemy.getHitPoints() > 0)
                cliView.displayWinner("Enemy"); //Display loss
            else if(player.getHitPoints() <= 0 && enemy.getHitPoints() <= 0)
                cliView.displayWinner("Tie"); //Display tie

        }

    }


    //Method for Player choice after game has ended

    /**
     * Method that allows the user to retry the battle or quit the program
     * @param input The user's input
     * @return The user's chosen action
     */
    public char retry(Scanner input){

        char choiceInput = 'Z'; //Sets default choiceInput to Z

        //Do-while to check for invalid input
        do{

            cliView.displayEndMainMenu(); //Display ending main menu
            System.out.printf("Input (Char): ");

            try {
                choiceInput = input.nextLine().toUpperCase().charAt(0); //Gets character input
            }
            catch (StringIndexOutOfBoundsException e) {
                System.out.printf("\nCannot enter an empty character\n");
            }

            //Checks for invalid input
            if(choiceInput != 'R' && choiceInput != 'Q'){

                System.out.printf("\nERROR: Please Provide a valid input.\n");
                choiceInput = 'Z'; //Returns to deafault value

            }

        }while(choiceInput != 'R' && choiceInput != 'Q');


        //If condition to check if they picked to restart the game
        if(choiceInput == 'R'){

            resetValues(); //Resets the selected choices
            selectChoices(input); //Calls selection again

        }
        else
            System.out.printf("\n\nTHANKS FOR PLAYING! :)\n"); //Prints out thank you message

        return choiceInput;

    }

    
    //Method to reset values if the Player wishes to play again
    private void resetValues(){

        environment = null;



        postTurnEventChecks();

        //Removes any existing equipment and resets character to default sats

        //Checks if Armor exists before resetting charges
        if(player.getArmor() != null)
            player.unequipArmor();

        //Checks if Weapon exists before resetting charges
        if(player.getWeapon() != null)
            player.unequipWeapon();

        //Checks if Consumable exists before resetting charges
        if(player.getConsumable() != null){

            player.getConsumable().resetCharges();
            player.setHasConsumeTemp(false);

        }


        player.unequipConsumable();
        player.setHitPoints(100);
        player.setSpeed(50);
        player.stopCharging();
        player.stopDefending();
        enemy.stopDefending();

        //Resets the enemy to its original values
        enemy.setHitPoints(enemyHitPointsCopy); 
        enemy.setAttack(enemyAttackCopy);
        enemy.setDefense(enemyDefenseCopy);
        enemy.setSpeed(enemySpeedCopy);

        if(enemy.getConsumable() != null){

            enemy.getConsumable().resetCharges();
            enemy.setHasConsumeTemp(false);
            
        }
        enemy = null;

        isRunning = true;
    
    }

}
