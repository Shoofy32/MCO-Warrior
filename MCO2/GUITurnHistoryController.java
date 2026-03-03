import java.util.ArrayList;

/**
 * Class file for controlling and updating the turn history in a battle
 * <p>
 *     Turn history is a list of all actions performed in a battle, this history is contained within the current battle and is not saved anywhere
 * </p>
 * @author Martin
 */
public class GUITurnHistoryController {

    private ArrayList<String> currentTurnHistory;
    private GUIEventController event;

    //Logic Attributes
    private boolean playerIsCharging = false; //Checks whether Player is charging or not
    private boolean enemyIsCharging = false; //Checks whether Enemy is charging or not
    private boolean playerPassiveActive = false; 
    private boolean enemyPassiveActive = false;

    /**
     * Constructor for the GUIEventController class
     * @param event GUI Event Controller to manage this class
     * @see GUIEventController
     */
    public GUITurnHistoryController(GUIEventController event){

        currentTurnHistory = new ArrayList<>();
        this.event = event;

    }


    /**
     * Updates the turn history of the gamePanel by calling turnResult and using that result to update the view
     * @param gamePanel Game Panel object
     * @see GUIGamePanel
     */
    public void updateTurnHistory(GUIGamePanel gamePanel){

        turnResult();
        gamePanel.updateTurnHistory(currentTurnHistory);

    }


    //Get result of Player's turn
    private void playerTurnResult(){
      
        if(playerPassiveActive)
            currentTurnHistory.add(String.format("%s %s\n", event.getPlayer().getName() , event.getPlayer().getWeapon().getAbility().getAbilityDisplayDescription()));

        if(event.getPlayer().getWeapon() != null && (event.getPlayer().getTurnInputAction() == 'F' || (event.getPlayer().getTurnInputAction() != 'F' && 
                event.getPlayer().getWeapon() instanceof EnchantedWeapon && ((EnchantedWeapon) event.getPlayer().getWeapon()).getWeaponSkill().getTurnsActive())))
            currentTurnHistory.add(String.format("%s\n", ((EnchantedWeapon) event.getPlayer().getWeapon()).getWeaponSkill().getActiveSkillResultDescription()));

        //If conditions to check event.getPlayer().getLastCharacterAction() and display corresponding result depdning on String value
        if(event.getPlayer().getLastCharacterAction().equals("Attack")){

            for(int i = 0; i < event.getPlayer().getTimesToAttack(); i++){

                //If condition to check if Player was charging or not. And display corresponding results.
                if(playerIsCharging)
                    currentTurnHistory.add(String.format("%s Charge Attacks %s! (Attack is multiplied by %d!)\n", event.getPlayer().getName(), event.getEnemy().getName(), event.getPlayer().getChargeMultiplier()));      
                else
                    currentTurnHistory.add(String.format("%s Attacks %s!\n", event.getPlayer().getName(), event.getEnemy().getName()));      


                if(event.getPlayer().getLastCharacterAction().equals("Defense"))
                    currentTurnHistory.add(String.format("%s Defends Against %s! (Next Attack Against %s is Halved!)\n", event.getEnemy().getName(), event.getPlayer().getName(), event.getEnemy().getName())); 


                if(event.getPlayer().getLastCharacterAction().equals("Attack") && playerIsCharging && event.getEnemy().getLastCharacterAction().equals("Defend")){

                    currentTurnHistory.add(String.format("%s Dealt %d Damage! (w/ Charge) (w/ Enemy Defending)\n", event.getPlayer().getName(),  event.getPlayer().getLastAttackDealt()));
                    
                }
                else if(event.getPlayer().getLastCharacterAction().equals("Attack") && !playerIsCharging && event.getEnemy().getLastCharacterAction().equals("Defend"))
                    currentTurnHistory.add(String.format("%s Dealt %d Damage! (w/ Enemy Defending)\n", event.getPlayer().getName(),  event.getPlayer().getLastAttackDealt()));
                else if(event.getPlayer().getLastCharacterAction().equals("Attack") && playerIsCharging)
                    currentTurnHistory.add(String.format("%s Dealt %d Damage! (w/ Charge)\n", event.getPlayer().getName(),  event.getPlayer().getLastAttackDealt()));       
                else
                    currentTurnHistory.add(String.format("%s Dealt %d Damage!\n", event.getPlayer().getName(),  event.getPlayer().getLastAttackDealt())); 

            }
 
                
            playerIsCharging = false;


        }
        else if(event.getPlayer().getLastCharacterAction().equals("Defend")){

            currentTurnHistory.add(String.format("%s Defends Against %s! (Next Attack Against %s is Halved!)\n", event.getPlayer().getName(), event.getEnemy().getName(), event.getPlayer().getName())); 
            playerIsCharging = false;
                
        }
        else if(event.getPlayer().getLastCharacterAction().equals("Charge")){

            //Checks if player is Charging or not to display a unique dialogue
            if(playerIsCharging)
                currentTurnHistory.add(String.format("%s Charges Again????\n", event.getPlayer().getName()));  
            else        
                currentTurnHistory.add(String.format("%s Prepares to Charge!\n", event.getPlayer().getName()));    

            currentTurnHistory.add(String.format("%s Next Attack Turn Will Deal %dx Damage!\n", event.getPlayer().getName(), event.getPlayer().getChargeMultiplier()));

            playerIsCharging = true;  

        }
        else if(event.getPlayer().getLastCharacterAction().equals("Consume")){

            currentTurnHistory.add(String.format("%s Consumes %s!\n", event.getPlayer().getName(), event.getPlayer().getConsumable().getName()));  

            //If conditions to check to check if the Consumable that the event.getPlayer() has is temporary or not.
            if(event.getPlayer().getConsumable().getIsTemporary())    
                currentTurnHistory.add(String.format("%s Provides a Temporary Effect for %d Turns!\n", event.getPlayer().getConsumable().getName(), 
                                    event.getPlayer().getConsumable().getAffectingTurns() - event.getPlayer().getConsumable().getTurnCounter())); 
            else
                currentTurnHistory.add(String.format("%s Provides a Premanent Effect!\n", event.getPlayer().getConsumable().getName()));
            
            //If condition checks if the Consumable affects the Player and gets the Player description if so
            if(event.getPlayer().getConsumable().getAffectsHolder())
                currentTurnHistory.add(String.format(" %s\n", event.getPlayer().getConsumable().getPlayerConsumableDescription()));  

            //If condition checks if the Consumable affects the Enemy and gets the Enemy description if so
            if(event.getPlayer().getConsumable().getAffectsTarget())
                currentTurnHistory.add(String.format("%s\n", event.getPlayer().getConsumable().getEnemyConsumableDescription()));                

            playerIsCharging = false; 

        }

    }


    //Get result of Enemy's turn
    private void enemyTurnResult(){

        if(enemyPassiveActive)
            currentTurnHistory.add(String.format("%s %s\n", event.getEnemy().getName(), event.getEnemy().getWeapon().getAbility().getAbilityDisplayDescription()));

        if(event.getEnemy().getWeapon() != null && (event.getEnemy().getTurnInputAction() == 'F' || (event.getEnemy().getTurnInputAction() != 'F' && 
                event.getEnemy().getWeapon() instanceof EnchantedWeapon && ((EnchantedWeapon) event.getEnemy().getWeapon()).getWeaponSkill().getTurnsActive())))
            currentTurnHistory.add(String.format("%s\n", ((EnchantedWeapon) event.getEnemy().getWeapon()).getWeaponSkill().getActiveSkillResultDescription()));

        //If conditions to check event.getPlayer().getLastCharacterAction() and display corresponding result depdning on String value
        if(event.getEnemy().getLastCharacterAction().equals("Attack")){

            for(int i = 0; i < event.getEnemy().getTimesToAttack(); i++){

                //If condition to check if Player was charging or not. And display corresponding results.
                if(enemyIsCharging)
                    currentTurnHistory.add(String.format("%s Charge Attacks %s! (Attack is multiplied by %d!)\n", event.getEnemy().getName(), event.getPlayer().getName(), event.getEnemy().getChargeMultiplier()));      
                else
                    currentTurnHistory.add(String.format("%s Attacks %s!\n", event.getEnemy().getName(), event.getPlayer().getName()));      


                if(event.getEnemy().getLastCharacterAction().equals("Defense"))
                    currentTurnHistory.add(String.format("%s Defends Against %s! (Next Attack Against %s is Halved!)\n", event.getEnemy().getName(), event.getPlayer().getName(), event.getPlayer().getName())); 


                if(event.getEnemy().getLastCharacterAction().equals("Attack") && enemyIsCharging && event.getPlayer().getLastCharacterAction().equals("Defend")){

                    currentTurnHistory.add(String.format("%s Dealt %d Damage! (w/ Charge) (w/ Player Defending)\n", event.getEnemy().getName(),  event.getEnemy().getLastAttackDealt()));


                }
                else if(event.getEnemy().getLastCharacterAction().equals("Attack") && !enemyIsCharging && event.getPlayer().getLastCharacterAction().equals("Defend"))
                    currentTurnHistory.add(String.format("%s Dealt %d Damage! (w/ Player Defending)\n", event.getEnemy().getName(),  event.getEnemy().getLastAttackDealt()));        
                else if(event.getEnemy().getLastCharacterAction().equals("Attack") && enemyIsCharging)
                    currentTurnHistory.add(String.format("%s Dealt %d Damage! (w/ Charge)\n", event.getEnemy().getName(),  event.getEnemy().getLastAttackDealt()));        
                else
                    currentTurnHistory.add(String.format("%s Dealt %d Damage!\n", event.getEnemy().getName(),  event.getEnemy().getLastAttackDealt())); 
                    
            }
                
            enemyIsCharging = false;


        }
        else if(event.getEnemy().getLastCharacterAction().equals("Defend")){

            currentTurnHistory.add(String.format("%s Defends Against %s! (Next Attack Against %s is Halved!)\n", event.getEnemy().getName(), event.getPlayer().getName(), event.getEnemy().getName())); 
            enemyIsCharging = false;
                
        }
        else if(event.getEnemy().getLastCharacterAction().equals("Charge")){

            //Checks if player is Charging or not to display a unique dialogue
            if(enemyIsCharging)
                currentTurnHistory.add(String.format("%s Charges Again????\n", event.getEnemy().getName()));  
            else        
                currentTurnHistory.add(String.format("%s Prepares to Charge!\n", event.getEnemy().getName()));    

            currentTurnHistory.add(String.format("%s Next Attack Turn Will Deal %dx Damage!\n", event.getEnemy().getName(), event.getEnemy().getChargeMultiplier())); 
            enemyIsCharging = true;  

        }
        else if(event.getEnemy().getLastCharacterAction().equals("Consume")){

            currentTurnHistory.add(String.format("%s Consumes %s!\n", event.getEnemy().getName(), event.getEnemy().getConsumable().getName()));  

            //If conditions to check to check if the Consumable that the event.getPlayer() has is temporary or not.
            if(event.getEnemy().getConsumable().getIsTemporary())    
                currentTurnHistory.add(String.format("%s Provides a Temporary Effect for %d Turns!\n", event.getEnemy().getConsumable().getName(), 
                        event.getEnemy().getConsumable().getAffectingTurns() - event.getEnemy().getConsumable().getTurnCounter())); 
            else
                currentTurnHistory.add(String.format("%s Provides a Premanent Effect!\n", event.getEnemy().getConsumable().getName()));
            
            //If condition checks if the Consumable affects the Player and gets the Player description if so
            if(event.getEnemy().getConsumable().getAffectsHolder())
                currentTurnHistory.add(String.format(" %s\n", event.getEnemy().getConsumable().getPlayerConsumableDescription()));  

            //If condition checks if the Consumable affects the Enemy and gets the Enemy description if so
            if(event.getEnemy().getConsumable().getAffectsTarget())
                currentTurnHistory.add(String.format("%s\n", event.getEnemy().getConsumable().getEnemyConsumableDescription()));                


            enemyIsCharging = false; 

        }
        
    }


    //Display Environment effect's result
    private void environmentTurnResult(){

        //If condition to check if environment has effects. Display result depending on condition.
        if(event.getEnvironment().getHasEffect()){

            currentTurnHistory.add(String.format("%s Has Affected %s and %s!\n", event.getEnvironment().getName(), event.getPlayer().getName(), event.getEnemy().getName())); 

            //Check if positive or negative values of playerStatEffect
            if(event.getEnvironment().getPlayerStatEffect() > 0)
                currentTurnHistory.add(String.format("%s's %s Is Increased by %d!\n", event.getPlayer().getName(), event.getEnvironment().getPlayerStatToEffect(), event.getEnvironment().getPlayerStatEffect())); 
            else
                currentTurnHistory.add(String.format("%s's %s Is Decreased By %d!\n", event.getPlayer().getName(), event.getEnvironment().getPlayerStatToEffect(), -event.getEnvironment().getPlayerStatEffect()));            

            //Check if positive or negative values of enemyStatEffect
            if(event.getEnvironment().getEnemyStatEffect() > 0)
                currentTurnHistory.add(String.format("%s's %s Is Increased by %d!\n", event.getEnemy().getName(), event.getEnvironment().getEnemyStatToEffect(), event.getEnvironment().getEnemyStatEffect())); 
            else
                currentTurnHistory.add(String.format("%s's %s Is Decreased By %d!\n", event.getEnvironment().getName(), event.getEnvironment().getEnemyStatToEffect(), -event.getEnvironment().getEnemyStatEffect()));  

        }
        else
            currentTurnHistory.add(String.format("%s Has No Effect!\n", event.getEnvironment().getName()));         

    }


    //Display the total turn result of both Player, Enemy, and Environment

    /**
     * Method for getting String equivalents for the outcome of a given turn
     */
    public void turnResult(){

        if(event.getPlayer().getWeapon() != null && event.getPlayer().getWeapon().getAbility() != null)
            playerPassiveActive = event.getPlayer().getWeapon().getAbility().getHasMetConditions();
        
        if(event.getEnemy().getWeapon() != null && event.getEnemy().getWeapon().getAbility() != null)
            enemyPassiveActive = event.getEnemy().getWeapon().getAbility().getHasMetConditions();


        environmentTurnResult(); //Geet the effects of the environment
        
        //If condition checks for who goes first(Same conditions for turnSystem)
        if((event.getEnemy().getSpeed() < event.getPlayer().getSpeed() || event.getPlayer().getIsDefending() || event.getPlayer().getTurnInputAction() == 'C') 
                && !event.getPlayer().getIsDefending()){
            
            currentTurnHistory.add(String.format("%s performs their turn first!\n\n", event.getPlayer().getName()));
            playerTurnResult();
            enemyTurnResult();

        }
        else if((event.getEnemy().getSpeed() > event.getPlayer().getSpeed() || event.getEnemy().getIsDefending() || event.getPlayer().getTurnInputAction() == 'C') 
                && !event.getPlayer().getIsDefending()){

            currentTurnHistory.add(String.format("%s performs their turn first!\n\n", event.getEnemy().getName()));
            enemyTurnResult();
            playerTurnResult();

        }
        else{

              currentTurnHistory.add(String.format("%s and %s performs their turn first!\n\n", event.getPlayer().getName(), event.getEnemy().getName()));
            playerTurnResult();
            enemyTurnResult();

        }

    }
    
}
