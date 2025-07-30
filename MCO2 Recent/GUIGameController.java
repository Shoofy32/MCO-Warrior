import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class file for the GUIGameController class
 * <p>
 *     This class manages and controls the flow of the battle itself
 * </p>
 */
public class GUIGameController implements ActionListener{
    
    private GUIViewer guiView; //Stores the GUIViewer object

    //Logic Attributes
    private boolean isRunning = true; //Stores whether the game is still running or not

    //Copy Attributes
    private int enemyHitPointsCopy; //Stores a copy of enemy hitpoints chosen for reset purposes
    private int enemyAttackCopy; //Stores a copy of enemy attack for reset purposes
    private int enemyDefenseCopy; //Stores a copy of enemy defense for reset purposes
    private int enemySpeedCopy; //Stores a copy of enemy speed chosen for reset purposes

    //Model Instances of Player, Enemy, and Environment
    private Player player;
    private Enemy enemy;
    private Environment environment;



    //Getters

    /**
     * Sets the GUIViewer to handle this class' display
     * @param guiView GUIViewer class
     * @see GUIViewer
     */
    public void setGUIView(GUIViewer guiView){

        this.guiView = guiView;

    }

    /**
     * Sets the player instance to be used in the battle
     * @param player player object to fight
     */
    public void setPlayer(Player player){

        this.player = player;

    }

    /**
     * Sets the enemy object to fight the player
     * @param enemy user's chosen enemy
     */
    public void setEnemy(Enemy enemy){

        this.enemy = enemy;

        enemyHitPointsCopy = enemy.getHitPoints();
        enemyAttackCopy = enemy.getAttack();
        enemyDefenseCopy = enemy.getDefense();
        enemySpeedCopy = enemy.getSpeed();
        
    }

    /**
     * Sets the environment the battle will take place in
     * @param environment user's chosen environment
     */
    public void setEnvironment(Environment environment){

        this.environment = environment;
        
    }


    //Method to control the Turn System of the game

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

        //Checks if the turn action is for charge
        if(player.getTurnInputAction() == 'C')
            player.setChargeBase(player.getAttack()); //Stores the player current attack to be used for a charge attack

        enemy.setChargeBase(enemy.getAttack()); //Stores the enemy current attack to be used for a charge attack

        //Checks if the environment chosen has any effects or not
        if(environment.getHasEffect())
            environmentEffect(); //Call method to update Player and Enemy stats depending on effect
        
        //Checks if player has a weapon and if they have, call the weapon if passive can be activated
        if(player.getWeapon() != null)
            player.getWeapon().usePassiveAbility(player, enemy);

        //Checks if enemy has a weapon and if they have, call the weapon if passive can be activated
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
        else{ //Both have the same speed or have no actions that are required to go furst

            //Player and enemy go at the same time
            player.think(enemy); 
            enemy.think(player);

            checkWinner(); //Check for win condition

        }

        //Check whether the game is still running in order to display turn results
        if(isRunning){

            //Checks if the player has a weapon has a skill
            if(player.getWeapon() instanceof EnchantedWeapon){

                //Calls the condition counter to allow activation of skill
                ((EnchantedWeapon) player.getWeapon()).getWeaponSkill().checkSkillConditionCounter(player, enemy);

                //Calls a method to create the result of the skill used
                if(player.getTurnInputAction() != 'F' && ((EnchantedWeapon) player.getWeapon()).getWeaponSkill().getTurnsActive())
                    ((EnchantedWeapon) player.getWeapon()).getWeaponSkill().createActiveSkillResultDescription(player, enemy);

            }

            //Checks if the enemy has a weapon has a skill
            if(enemy.getWeapon() instanceof EnchantedWeapon){

                ((EnchantedWeapon) enemy.getWeapon()).getWeaponSkill().checkSkillConditionCounter(enemy, player);
                
                if(enemy.getTurnInputAction() != 'F' && ((EnchantedWeapon) enemy.getWeapon()).getWeaponSkill().getTurnsActive())
                    ((EnchantedWeapon) enemy.getWeapon()).getWeaponSkill().createActiveSkillResultDescription(enemy, player);

            }

  
        }

        //Update GUI Display and go for postTurnEventChecks
        updateDisplay();
        postTurnEventChecks();

    }


    //Calls GUI to update game display
    private void updateDisplay(){

        int playerSkillCooldown = 0;
        int enemySkillCooldown = 0;

        if(player.getWeapon() instanceof EnchantedWeapon)
            playerSkillCooldown = ((EnchantedWeapon) player.getWeapon()).getWeaponSkill().getConditionCounter();
        
        if(enemy.getWeapon() instanceof EnchantedWeapon)
            enemySkillCooldown = ((EnchantedWeapon) enemy.getWeapon()).getWeaponSkill().getConditionCounter(); 

        //Stores player and enemy stats to be used to update display
        int[] playerStats = new int[]{player.getHitPoints(), playerSkillCooldown, player.getAttack(), player.getDefense(), player.getSpeed()};
        int[] enemyStats = new int[]{enemy.getHitPoints(), enemySkillCooldown, enemy.getAttack(), enemy.getDefense(), enemy.getSpeed()};

        guiView.updateGamePanel(playerStats, enemyStats); //Calls GUI method to update the game panel

    }


    //Checks and updates logic after a turn has been done
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

            //If condition checks who reached 0 hitpoints
            if(player.getHitPoints() > 0 && enemy.getHitPoints() <= 0){ //Display win

                guiView.setGameOverLabel("YOU WIN!");
                guiView.showPanel("Game Over");

            }

            else if(player.getHitPoints() <= 0 && enemy.getHitPoints() > 0){ //Display loss

                guiView.setGameOverLabel("YOU LOST!");
                guiView.showPanel("Game Over");

            }

            else if(player.getHitPoints() <= 0 && enemy.getHitPoints() <= 0){ //Display tie

                guiView.setGameOverLabel("TIE!");
                guiView.showPanel("Game Over");

            }


        }

    }


    //Method for Player choice after game has ended

    /**
     * Method to reset values if the Player wishes to play again
     */
    public void resetValues(){

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

        //Disables player and enemy defend or charge if still active
        if(player.getIsDefending())
            player.stopDefending();

        if(player.getIsCharging())
            player.stopCharging();

        if(enemy.getIsDefending())
            enemy.stopDefending();

        if(enemy.getIsCharging())
            enemy.stopCharging();


        //Resets the enemy to its original values
        enemy.setHitPoints(enemyHitPointsCopy); 
        enemy.setAttack(enemyAttackCopy);
        enemy.setDefense(enemyDefenseCopy);
        enemy.setSpeed(enemySpeedCopy);

        if(enemy.getConsumable() != null){

            enemy.getConsumable().resetCharges();
            enemy.setHasConsumeTemp(false);
            
        }

        enemy = null; //Removes enemy object

        isRunning = true; //Runs the game again
    
    }


    /**
     * Method for button input
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand(); //Get String equivalent of the action command


        //Switch statement to check the command and do the corresponding action
        switch(command){

            case "Attack":
                
                player.setTurnInputAction('A');
                turnSystem();
                guiView.updateTurnHistory();
                break;

            case "Defend":

                player.setTurnInputAction('D');
                turnSystem();
                guiView.updateTurnHistory();
                break;

            case "Charge":

                player.setTurnInputAction('C');
                turnSystem();
                guiView.updateTurnHistory();
                break;

            case "Consume":
                
                //Checking if consumable exists
                if(player.getConsumable() == null)
                    guiView.showErrorMessage("No Consumable");
                else if(player.getConsumable() != null && player.getConsumable().getChargesLeft() == 0) //Checking if no charges
                    guiView.showErrorMessage("No Charges");
                else{

                    player.setTurnInputAction('U');
                    turnSystem();
                    guiView.updateTurnHistory();

                }

                break;

            case "Skill":

                //Checking if weapon is a Enchanted Weapon object
                if(!(player.getWeapon() instanceof EnchantedWeapon))
                    guiView.showErrorMessage("No Skill");
                //Checking if skill condition has been met
                else if(player.getWeapon() instanceof EnchantedWeapon && !((EnchantedWeapon) player.getWeapon()).getWeaponSkill().getHasMetConditions())
                    guiView.showErrorMessage("Skill Condition False");
                else{

                    player.setTurnInputAction('F');
                    turnSystem();
                    guiView.updateTurnHistory();

                }

                break;

            case "Information":
                //Makes the information panel visible to check for player and enemy equipment and the turn history
                guiView.getGamePanel().getInformationPanel().setVisible(!guiView.getGamePanel().getInformationPanel().isVisible());

                

        }

    }

}
