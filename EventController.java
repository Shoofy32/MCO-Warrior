import java.util.Scanner;

/**
 * Class file that controls the events currently running in the program.
 * <p>
 *     This class manages the currently running events/processes in the program.
 * </p>
 * <p>
 *     These events include the selections the user will to customize their battle.
 * </p>
 * @see SelectionController
 * <p>
 *     This class also handles the input from the user either while in the Main Menu or while in a battle.
 * </p>
 * @author Stefan_Martin
 */
public class EventController {
    
    //Main Attributes
    private Player player; //Stores player that will be used in the program
    private SelectionController selection; //Stores controller dealing with selection
    private CLIViewer display; //Stores Class that will be doing display
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
    public EventController(){

        display = new CLIViewer();
        selection = new SelectionController(display);
        
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

        return display;

    }

    /**
     * Getter method to determine if the game is still running
     * @return the boolean of the game running, when this returns false the game has ended
     */
    public boolean getIsRunning(){

        return isRunning;

    }


    //EventController Methods

    /**
     * Method to manage the input coming in from the Main Menu
     * @param input The user's input
     * @return The user's choice to be used later on
     */
    public char mainMenu(Scanner input){

        char menuInput='Z';
        String name;

        //Do-while loop for invalid input
        do{

            display.displayMainMenu(); //Display main menu

            System.out.printf("Input: ");
            try{
                menuInput = input.nextLine().toUpperCase().charAt(0); //Gets Player for main menu
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
                
                player = new Player(name);

                //Checks whether Player name length is higher than 15
                if(player.getName().length() > 15)
                     System.out.printf("\nPlease provide a name within the character limit!\n"); //Display overflow error          

            
            }while(player.getName().length() > 15);


            selectChoices(input); //Calls method for selecting different choices for player.

        }

        else{

            isRunning = false;
            System.out.printf("Thank you for playing! :)\n"); //Prints out thank you message

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

        //Sets the Player and their choices to the controller for display later
        display.setPlayer(player);
        display.setEnvironment(environment);
        display.setEnemy(enemy);

    }


    //Method to control the Turn System of the game
    //Parameter is the choice of the player during the game (Whether to attack, defend, charge, or consume)

    /**
     * Method to control actual running battle
     * @param choice The user's choice of a given action they want to do in one turn
     */
    public void turnSystem(char choice){

        String playerTurnChoice = null; //Gets the choice of the Player that gets returned from the Player think method

        //Checks if the player has previously consumed a potion that provides temporary effects.
        if(player.getHasConsumeTemp())
            player.getConsumable().countAffectingTurns(player, enemy); //Calls counter and restores stats once timer ends


        /* If conditions to check the turn system of the game (Determines who goes first)
         * Turn is determined by the checkWinner() method. (If theres a checkWin() method before their turn, then their turn was second)
         */
        if(enemy.getSpeed() < player.getSpeed() || player.getIsDefending() || choice == 'U'){ //Checks if player is faster, defending, or consumed
            
            playerTurnChoice = player.think(choice, enemy); //Player goes first
            checkWinner(); //Check for win condition

            //Checks if game is still running to prevent unecessary Enemy turns
            if(isRunning){

                enemyTurn(); //Enemy goes next
                checkWinner(); //Check for win condition

            }

        }
        else if(enemy.getSpeed() > player.getSpeed()){ //Checks if enemy is faster

            enemyTurn(); //Enemy goes first
            checkWinner(); //Check for win condition

            //Checks if game is still running to prevent unecessary Player turns
            if(isRunning){

                playerTurnChoice = player.think(choice, enemy); //Player goes next
                checkWinner(); //Check for win condition

            }

        }
        else{ //Both have the same speed

            //Player and enemy go at the same time
            playerTurnChoice = player.think(choice, enemy); 
            enemyTurn();

            checkWinner(); //Check for win condition

        }

        //Checks if the environment chosen has any effects or not
        if(environment.getHasEffect())
            environmentEffect(); //Call method to update Player and Enemy stats depending on effect

        //Check whether the game is still running in order to display turn results
        if(isRunning)
            display.displayTurnResult(playerTurnChoice);

    }

    
    //Method for Player's turn and their choice during this turn.

    /**
     * Method for validating user input for their actions in the game, this includes error and exception handling
     * @param input The user's input
     * @return the user's chosen action
     */
    public char playerChoice(Scanner input){

        char choiceInput = 'Z'; //Sets default choiceInput to newline

        //Do-while to check for invalid input
        do{

            display.displayChoices(); //Display player choices for their turn
    
            System.out.printf("Input: ");
          
            try{
                choiceInput = input.nextLine().toUpperCase().charAt(0); //Gets character input
            } catch (StringIndexOutOfBoundsException e) {
                System.out.printf("\nCannot enter an empty character\n");
            }

            //Checks for invalid input
            if(choiceInput != 'A' && choiceInput != 'D' && choiceInput != 'C' && choiceInput != 'I' && choiceInput != 'U'){

                System.out.printf("\nERROR: Please provide a valid input.\n");   
                choiceInput = 'Z'; //Returns to deafault value
                display.displayGameBar();

            }
            else if(choiceInput == 'U' && player.getConsumable() == null){ //Checks if player tries to use a nonexistient consumable

                System.out.printf("\nERROR: No consumable equipped!\n");  
                choiceInput = 'Z'; //Returns to deafult value
                display.displayGameBar();

            }
            else if(choiceInput == 'U' && player.getConsumable() != null && player.getConsumable().getChargesLeft() == 0){
                //Checks if player tries to use a consumable that is out of charges.

                System.out.printf("\nERROR: No charges left!\n");  
                choiceInput = 'Z'; //Returns to deafult value
                display.displayGameBar();

            }
            

        }while(choiceInput == 'Z');      
    

        return choiceInput;

    }


    //Method for Enemy's turn
    private void enemyTurn(){

        enemy.attack(player);

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
            display.displayGameBar(); //Diplays final values of the game one last time

            //If condition checks who reached 0 hitpoints
            if(player.getHitPoints() >= 0 && enemy.getHitPoints() <= 0)
                display.displayWinner("Player"); //Display win
            else if(player.getHitPoints() <= 0 && enemy.getHitPoints() >= 0)
                display.displayWinner("Enemy"); //Display loss
            else
                display.displayWinner("Tie"); //Display tie

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

            display.displayEndMainMenu(); //Display ending main menu
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
            System.out.printf("Thank you for playing! :)\n"); //Prints out thank you message

        return choiceInput;

    }

    
    //Method to reset values if the Player wishes to play again
    private void resetValues(){

        environment = null;

        //Removes any existing equipment and resets character to default sats
        player.unequipArmor();
        player.unequipWeapon();
        
        //Checks if Consumable exist before resetting charges
        if(player.getConsumable() != null)
            player.getConsumable().resetCharges();

        player.unequipConsumable();
        player.setHitPoints(100);
        player.setSpeed(50);

        //Resets the enemy to its original values
        enemy.setHitPoints(enemyHitPointsCopy); 
        enemy.setAttack(enemyAttackCopy);
        enemy.setDefense(enemyDefenseCopy);
        enemy.setSpeed(enemySpeedCopy);
        enemy = null;

        isRunning = true;
    
    }

}
