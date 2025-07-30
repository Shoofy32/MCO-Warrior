import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
;

/**
 * Class file for the GUIEventController class
 * <p>
 *     This class serves a similar purpose to the EventController class, handling all the running events/processes of the program.
 * </p>
 * <p>
 *     Unlike Eventcontroller, this class makes use of images and graphics.
 * </p>
 * @see EventController
 * @author Martin
 */
public class GUIEventController implements ActionListener{
    
    //Main Attributes
    private Player player; //Stores player that will be used in the program
    private GUISelectionController selection; //Stores controller dealing with selection
    private GUIGameController gameSystem;
    private GUITurnHistoryController turnHistory;
    private GUIViewer guiView;
    private Enemy enemy; //Stores enemy that will be used in the program
    private Environment environment; //Stores the environment that will be used in the program

    //Constructor

    /**
     * Constructor for the GUIEventController Class
     */
    public GUIEventController(){

        //Create GUI and Controller objects
        selection = new GUISelectionController(this);
        gameSystem = new GUIGameController();
        turnHistory = new GUITurnHistoryController(this);
        guiView = new GUIViewer(gameSystem, turnHistory, selection.getWeapons().size(), selection.getArmors().size(), selection.getConsumables().size(),
                    selection.getEnemies().size(), selection.getEnvironments().size());

        //Condition to check if guiView exists.
        if(guiView != null){

            selection.setGUIView(guiView);
            selection.createSelectionButtons();
            guiView.setActionListener(this);
            gameSystem.setGUIView(guiView);

        }



    }


    //Setters

    /**
     * Sets the enemy object for the player to battle
     * @param enemy user's chosen enemy
     */
    public void setEnemy(Enemy enemy){

        this.enemy = enemy;

    }

    /**
     * Sets the environment where the battle will take place
     * @param environment user's chosen environment
     */
    public void setEnvironment(Environment environment){

        this.environment = environment;

    }


    //Getters

    /**
     * Getter to retrieve the player object
     * @return player object
     */
    public Player getPlayer(){

        //Checks if player exists
        if(player != null)
            return player;
        else
            return null;

    }

    /**
     * Getter to retrieve the enemy object
     * @return enemy object
     */
    public Enemy getEnemy(){

        if(enemy != null)
            return enemy;
        else
            return null;

    }

    /**
     * Getter to retrieve the environment object
     * @return current environment
     */
    public Environment getEnvironment(){

        ////Checks if environment exists
        if(environment != null)
            return environment;
        else
            return null;

    }


    //Controller methods

    /**
     * Starts the actual game after selection is performed
     */
    public void startGame(){

        //Gives the corresponding selection choices to game system controller
        gameSystem.setPlayer(player);
        gameSystem.setEnemy(enemy);
        gameSystem.setEnvironment(environment);

        initGameSystem(); //Creates the game panel
        guiView.showPanel("Game"); //Shows the game panel

    }

    /**
     * Creates the game panel by getting different values
     */
    public void initGameSystem(){

        //Arrays that store the information for game display
        int[] playerStats;
        int[] enemyStats;
        ImageIcon[] statImages;
        ImageIcon[] uiImages;
        ImageIcon[] equipmentImages;
        String[] equipmentNames;

        //Attributes that store the health and playerSkillCooldowns of player and enemy
        int playerHitPoints = player.getHitPoints();
        int enemyHitPoints = enemy.getHitPoints();
        int playerSkillCooldown = 0;
        int enemySkillCooldown = 0;   

        //Attributes that store the names and images of the equipment used by the Player and Enemy
        String pWeaponName = "NONE";
        ImageIcon pWeaponImage = new ImageIcon("Assets/UI/None_Temp.png");
        String pArmorName = "NONE";
        ImageIcon pArmorImage = new ImageIcon("Assets/UI/None_Temp.png");
        String pConsumableName = "NONE";
        ImageIcon pConsumableImage = new ImageIcon("Assets/UI/None_Temp.png");
        String eWeaponName = "NONE";
        ImageIcon eWeaponImage = new ImageIcon("Assets/UI/None_Temp.png");
        String eArmorName = "NONE";
        ImageIcon eArmorImage = new ImageIcon("Assets/UI/None_Temp.png");
        String eConsumableName = "NONE";
        ImageIcon eConsumableImage = new ImageIcon("Assets/UI/None_Temp.png");


        //Checks if player weapon has an enchanted weapon and gets the required condition counter of the weapon
        if(player.getWeapon() instanceof EnchantedWeapon)
            playerSkillCooldown = ((EnchantedWeapon) player.getWeapon()).getWeaponSkill().getRequiredConditionCounter();

        //Checks if enemy weapon is an enchanted weapon and gets the required condition counter of the weapon
        if(enemy.getWeapon() instanceof EnchantedWeapon)
            enemySkillCooldown = ((EnchantedWeapon) enemy.getWeapon()).getWeaponSkill().getRequiredConditionCounter();


        //Update the Arrays that store the different values to be used to create the game Panel
        playerStats = new int[]{playerHitPoints, playerSkillCooldown, player.getAttack(), player.getDefense(), player.getSpeed()};
        enemyStats = new int[]{enemyHitPoints, enemySkillCooldown, enemy.getAttack(), enemy.getDefense(), enemy.getSpeed()};
        statImages = new ImageIcon[]{new ImageIcon("Assets/UI/AttackIcon_Temp.png"), new ImageIcon("Assets/UI/DefenseIcon_Temp.png"),
                new ImageIcon("Assets/UI/SpeedIcon_Temp.png")};
        uiImages = new ImageIcon[]{environment.getImage(), player.getImage(), enemy.getImage(), 
        new ImageIcon("Assets/UI/HealthIcon_Temp.png"), new ImageIcon("Assets/UI/SkillIcon_Temp.png"), new ImageIcon("Assets/UI/InformationIcon_Temp.png")};

        
        //Checks if player weapon exists
        if(player.getWeapon() != null){

            pWeaponImage = player.getWeapon().getImage();
            pWeaponName = player.getWeapon().getName();

        }

        //Checks if player armor exists
        if(player.getArmor() != null){

            pArmorImage = player.getArmor().getImage();
            pArmorName = player.getArmor().getName();

        }

        //Checks if player consumable exists
        if(player.getConsumable() != null){

            pConsumableImage = player.getConsumable().getImage();
            pConsumableName = player.getConsumable().getName();

        }

        //Checks if enemy weapon exists
        if(enemy.getWeapon() != null){

            eWeaponImage = enemy.getWeapon().getImage();
            eWeaponName = enemy.getWeapon().getName();

        }

        //Checks if enemy armor exists
        if(enemy.getArmor() != null){

            eArmorImage = enemy.getArmor().getImage();
            eArmorName = enemy.getArmor().getName();

        }

        //Checks if enemy consumable exists
        if(enemy.getConsumable() != null){

            eConsumableImage = enemy.getConsumable().getImage();
            eConsumableName = enemy.getConsumable().getName();

        }

        //Update the arrays that store the equipmentImages and equipmentNames
        equipmentImages = new ImageIcon[]{pWeaponImage, pArmorImage, pConsumableImage, eWeaponImage, eArmorImage, eConsumableImage};
        equipmentNames = new String[]{pWeaponName, pArmorName, pConsumableName, eWeaponName, eArmorName, eConsumableName};

        //Call Method for game display
        guiView.initGame(uiImages, equipmentImages, equipmentNames, playerStats, enemyStats, statImages);

    }


    //Checks for valid name input
    private boolean nameValidation(String name){

        //Ternary operator that checks if name is not empty or greater than 15 characters
        return (name.length() > 15 || name.length() <= 0) ? false : true;

    }


    /**
     * Method for button input
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand(); //Get String equivalent of the action command


        //Switch statement to check the command and do the corresponding action
        switch(command){

            case "Start":
                
                guiView.showPanel("Enter Name"); //Shows panel to enter name
                break;

            case "Quit":

                System.exit(0); //Closes program
                break;

            case "Enter Name":

                //Validates if name input is valid
                if(nameValidation(guiView.getNameInput())){
                    
                    //Creates player object with name and image parameter
                    player = new Player(guiView.getNameInput(), "Assets/Player/Player.png");

                    guiView.clearInput(); //Clear JTextArea input
                    selection.startSelection(); //Starts the game selection
        
                }
                else
                    guiView.showErrorMessage("Invalid Name"); //Invalid name error message

                break;

            case "Restart":

                gameSystem.resetValues(); //Reset the values to original
                guiView.showPanel("Select Weapon"); //Shows panel to the beginning of selection 
                break;

            case "Main Menu":

                guiView.showPanel("Start"); //Shows panel for main menu


        }

    }

}
