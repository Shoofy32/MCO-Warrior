import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Class file for the GUIViewer class, this class serves a similar purpose to CLIViewer.
 * <p>
 *     Much like CLIViewer, this class is responsible for displaying information about the running program.
 * </p>
 * <p>
 *     This information is displayed using visuals and graphics as opposed to purely text based displays on the command line.
 * </p>
 * @see CLIViewer
 * @author Martin
 */
public class GUIViewer extends JFrame{

    //Components for Main Menu
    private JPanel menuPanel;
    private JPanel panelCenter;
    private JPanel panelNorth;
    private JLabel title;
    private JButton startButton;
    private JButton quitButton;

    //Components for Selection
    private JPanel enterNamePanel;

    private JButton[] weaponButtons;
    private GUIWeaponSelectPanel weaponSelectPanel;

    private JButton[] armorButtons;
    private GUIArmorSelectPanel armorSelectPanel;

    private JButton[] consumableButtons;
    private GUIConsumableSelectPanel consumableSelectPanel;

    private JButton[] enemyButtons;
    private GUIEnemySelectPanel enemySelectPanel;

    private JButton[] environmentButtons;
    private GUIEnvironmentSelectPanel environmentSelectPanel;

    private GUICurrentChoices currentChoices;

    private JButton backButton;
    private JButton selectButton;
    private String currentPanel;
    private JTextField tfName;
    private JButton enterNameButton;

    //Components for Game Panel
    private GUIGameController gameSystem;
    private GUITurnHistoryController turnHistory;
    private GUIGamePanel gamePanel;
    private JButton attackButton;
    private JButton defendButton;
    private JButton chargeButton;
    private JButton consumeButton;
    private JButton skillButton;
    private JButton informationButton;

    //Components for Game Over Screen
    private JButton resetButton;
    private JButton mainMenuButton;
    private JPanel gameOverScreen;
    private JLabel gameOverLabel;

    //Main Layout that combines everything
    private CardLayout cardLayout;
    private JPanel mainPanel;
    
    //For use
    private Border padding = BorderFactory.createEmptyBorder(15, 15, 15, 15);
    private Font pixelFont;
    private Font boldPixelFont;

    /**
     * Constructor for the GUIViewer class
     * @param gameSystem object responsible for managing a battle
     * @param turnHistory object responsible for keeping track of actions in a battle
     * @param numWeapons number of weapon objects to be displayed
     * @param numArmors number of armor objects to be displayed
     * @param numConsumables number of consumable objects to be displayed
     * @param numEnemies number of enemy objects to be displayed
     * @param numEnvironments number of environment objects to be displayed
     */
    public GUIViewer(GUIGameController gameSystem, GUITurnHistoryController turnHistory,
            int numWeapons, int numArmors, int numConsumables, int numEnemies, int numEnvironments){

        super("Warrior"); //Title
        setLayout(new BorderLayout()); //Layout
        setPreferredSize(new Dimension(1500, 920));
        setMaximumSize(new Dimension(1500, 920));
        setMinimumSize(new Dimension(1500, 920));
        pack(); // sizes the frame to fit preferred sizes of components
        setLocationRelativeTo(null); // centers on screen


        this.gameSystem = gameSystem;
        this.turnHistory = turnHistory;
        gamePanel = new GUIGamePanel();

        setResizable(false); //Prevent resizing to avoid breaking any components
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Allows to close the program when clicking the X in the program window


        //Uploading custom font 1 with additional Exception Handling for IOExceptions
        try{
            
            pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("Assets/Font/MedodicaRegular.otf")).deriveFont(30f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(pixelFont);
            
        }catch (IOException | FontFormatException e){

            e.printStackTrace();
        }

        //Uploading custom font 2 with additional Exception Handling for IOExceptions
        try{
            
            boldPixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("Assets/Font/FFFFORWA.TTF")).deriveFont(30f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(boldPixelFont);
            
        }catch (IOException | FontFormatException e){

            e.printStackTrace();
        }


        initialization(numWeapons, numArmors, numConsumables, numEnemies, numEnvironments); //Initializes the basic components

        setVisible(true); //Makes the JFrame visible
                

    }


    //Setters

    /**
     * Sets the action listener for the buttons
     * @param listener ActionListener object to interact with the buttons
     */
    public void setActionListener (ActionListener listener){

        startButton.addActionListener(listener);
        quitButton.addActionListener(listener);
        enterNameButton.addActionListener(listener);
        resetButton.addActionListener(listener);
        mainMenuButton.addActionListener(listener);

    }

    /**
     * Sets the command a button will perform when clicked
     * @param listener ActionListener object to interact with the button
     * @param button button to receive action
     * @param command command to be executed when button is clicked
     */
    public void setActionCommand(ActionListener listener, JButton button, String command){

        button.addActionListener(listener);
        button.setActionCommand(command);

    }

    /**
     * Method that creates the selection buttons and sets their action command and action listener
     * @param listener ActionListener object to interact with the button
     * @param index index of button, these buttons are stored in an ArrayList so we need their indexes to access them
     * @param type type of button to be created, assume all types are valid
     * @param name name of the button
     * @param image image of the button
     */
    public void setSelectionActionListener(ActionListener listener, int index, String type, String name, ImageIcon image){

        //Scale the image stored ImageIcon
        Image scaledImage = image.getImage().getScaledInstance(300, 250, Image.SCALE_SMOOTH);;

        //Switch statement that creates the button for that corresponding type
        switch(type){

            case "Weapon":

                weaponButtons[index] = new JButton(new ImageIcon(scaledImage));
                weaponButtons[index].setActionCommand(name);
                weaponButtons[index].addActionListener(listener);
                break;

             case "Armor":

                armorButtons[index] = new JButton(new ImageIcon(scaledImage));
                armorButtons[index].setActionCommand(name);
                armorButtons[index].addActionListener(listener);
                break;       

            case "Consumable":

                consumableButtons[index] = new JButton(new ImageIcon(scaledImage));
                consumableButtons[index].setActionCommand(name);
                consumableButtons[index].addActionListener(listener);
                break;

            case "Enemy":

                enemyButtons[index] = new JButton(new ImageIcon(scaledImage));
                enemyButtons[index].setActionCommand(name);
                enemyButtons[index].addActionListener(listener);        
                break;

            case "Environment":

                environmentButtons[index] = new JButton(new ImageIcon(scaledImage));
                environmentButtons[index].setActionCommand(name);
                environmentButtons[index].addActionListener(listener);    
                break;

        }
        
    }

    /**
     * Sets the text to be displayed when a game has ended (Win, Loss, Tie)
     * @param text text to be displaye when a game has ended
     */
    public void setGameOverLabel(String text){
 
        gameOverLabel.setText(text);

        //Switch statement to change the background of the panel depending on the winning condiiton
        switch(text){

            case "YOU WIN!":
           
            gameOverScreen.setBackground(Color.YELLOW);
            break;

            case "YOU LOST!":

            gameOverScreen.setBackground(Color.RED);
            break;

            case "TIE!":

            gameOverScreen.setBackground(Color.GRAY);

        }
        
    }

    //Getters

    /**
     * Getter method to retrieve the Back Button
     * @return back button
     */
    public JButton getBackButton(){

        return backButton;

    }

    /**
     * Getter method to retrieve the Select Button
     * @return select button
     */
    public JButton getSelectButton(){

        return selectButton;

    }

    /**
     * Getter method to retrieve the inputted name for the player
     * @return the inputted player name
     */
    public String getNameInput(){

        return tfName.getText();

    }

    /**
     * Getter to retrieve the current panel that is being displayed
     * @return name of the currently displayed panel
     */
    public String getCurrentPanel(){

        return currentPanel;

    }

    /**
     * Getter to retrieve the game panel object
     * @return game panel object
     */
    public GUIGamePanel getGamePanel(){

        return gamePanel;

    }


    //GUI Methods

    //Initializes the base components for the program
    private void initialization(int numWeapons, int numArmors, int numConsumables, int numEnemies, int numEnvironments){

        //Creates the main panel for all of the parts of the game with a card layout to switch panel display
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);


        //Create the panels for main menu and game over screen
        panelCenter = new JPanel(new FlowLayout());
        panelNorth = new JPanel(new FlowLayout());
        gameOverScreen = new JPanel(new BorderLayout());

        //Property for Game Over Screen
        gameOverScreen.setOpaque(true);

        //Title 
        title = new JLabel("Warrior");
        title.setFont(boldPixelFont.deriveFont(150f));
        panelNorth.add(title);

        //Start Button
        startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(400, 100));
        startButton.setFont(pixelFont.deriveFont(75f));
        startButton.setFocusPainted(false);


        //Quit Button
        quitButton = new JButton("Quit");
        quitButton.setPreferredSize(new Dimension(400, 100));
        quitButton.setFont(pixelFont.deriveFont(75f));
        quitButton.setFocusPainted(false);

        //Combine to a center panel
        panelCenter.add(startButton);
        panelCenter.add(quitButton);

        //Create the menu panel and add both the title and names into it.
        menuPanel = new JPanel(new BorderLayout());
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.add(panelNorth);
        menuPanel.add(panelCenter);


        //Add menuPanel to main
        mainPanel.add(menuPanel, "menu");


        //Create the selection buttons (those with +1 have a none option)
        weaponButtons = new JButton[numWeapons + 1];
        armorButtons = new JButton[numArmors + 1];
        consumableButtons = new JButton[numConsumables + 1];
        enemyButtons = new JButton[numEnemies];
        environmentButtons = new JButton[numEnvironments];

        //Create the player action buttons for the game
        attackButton = new JButton();
        defendButton = new JButton();
        chargeButton = new JButton();
        consumeButton = new JButton();
        skillButton = new JButton();
        informationButton = new JButton();

        //Set the action command for the action buttons
        setActionCommand(gameSystem, attackButton, "Attack");
        setActionCommand(gameSystem, defendButton, "Defend");
        setActionCommand(gameSystem, chargeButton, "Charge");
        setActionCommand(gameSystem, consumeButton, "Consume");
        setActionCommand(gameSystem, skillButton, "Skill");
        setActionCommand(gameSystem, informationButton, "Information");

        //Create resstart and return to main menu buttins
        resetButton = new JButton("Restart");
        quitButton.setPreferredSize(new Dimension(400, 100));
        quitButton.setFont(pixelFont.deriveFont(75f));
        
        mainMenuButton = new JButton("Main Menu");
        quitButton.setPreferredSize(new Dimension(400, 100));
        quitButton.setFont(pixelFont.deriveFont(75f));
        
        //Label for game over screen
        gameOverLabel = new JLabel();
        
        initName(); //Initializes the enter name panel
        initGameOver(); //Initializes the game over panel

        //Adds mainPanel to this JFrame
        this.add(mainPanel, BorderLayout.CENTER);

        //Show the main menu panel
        cardLayout.show(mainPanel, "menu");

    }

    //Initialzes the enter name panel
    private void initName(){

        //Panel to wrap all contents
        JPanel centerWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //Enter Player Name label
        JLabel enterNameDisplay = new JLabel("Enter Player Name");
        enterNameDisplay.setFont(boldPixelFont.deriveFont(75f));
        enterNameDisplay.setHorizontalAlignment(SwingConstants.CENTER);

        //Text field to enter player name
        tfName = new JTextField();
        tfName.setPreferredSize(new Dimension(600, 50)); 
        tfName.setFont(pixelFont.deriveFont(50f));
        centerWrapper.add(tfName);

        //Button to enter the name typed
        enterNameButton = new JButton("Enter Name");
        enterNameButton.setPreferredSize(new Dimension(250, 100));
        enterNameButton.setFont(pixelFont.deriveFont(40f));
        centerWrapper.add(enterNameButton);

        //The actual panel where all contents will be stored
        enterNamePanel = new JPanel(new BorderLayout());
        enterNamePanel.add(enterNameDisplay, BorderLayout.NORTH);
        enterNamePanel.add(centerWrapper, BorderLayout.CENTER);

        //Add enterNamePanel to main
        mainPanel.add(enterNamePanel, "name");

    }

    //Initialzes the game over panel
    private void initGameOver(){

        //Change Properties of gameOverLabel
        gameOverLabel.setFont(boldPixelFont.deriveFont(150f));
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameOverLabel.setBorder(new EmptyBorder(100, 0, 0, 0));

        //Change/Add Properties of reset and main menu button
        resetButton.setPreferredSize(new Dimension(400, 100));
        resetButton.setMaximumSize(new Dimension(400, 100));
        resetButton.setMinimumSize(new Dimension(400, 100));
        resetButton.setFont(pixelFont.deriveFont(75f));
        resetButton.setAlignmentX(CENTER_ALIGNMENT);
        
        mainMenuButton.setPreferredSize(new Dimension(400, 100));
        mainMenuButton.setMaximumSize(new Dimension(400, 100));
        mainMenuButton.setMinimumSize(new Dimension(400, 100));
        mainMenuButton.setFont(pixelFont.deriveFont(75f));
        mainMenuButton.setAlignmentX(CENTER_ALIGNMENT);

        //Center Panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBounds(0, 0, 800, 400);
        centerPanel.setOpaque(false);

        //Add Buttons to Center Panel
        centerPanel.add(resetButton);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(mainMenuButton);  
  
        //Add Components to the actual game over panel
        gameOverScreen.add(gameOverLabel, BorderLayout.NORTH);
        gameOverScreen.add(centerPanel, BorderLayout.CENTER);
        
        //Add gameOverScreen to main
        mainPanel.add(gameOverScreen, "gameOver");

    }

    /**
     * Initializes the selection panel of the selected type stored in the parameter
     * @param type Type of selection panel to display
     */
    public void initSelection(String type){

        //Change/Add Properties of back and main menu button 
        backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(180, 60));  
        backButton.setMaximumSize(new Dimension(180, 60));   
        backButton.setMinimumSize(new Dimension(180, 60));    
        backButton.setFont(pixelFont.deriveFont(55f));
        backButton.setBorder(padding);

        if(type.equals("Final"))
            selectButton = new JButton("Start");
        else
            selectButton = new JButton("Next");            
        selectButton.setPreferredSize(new Dimension(180, 60));  
        selectButton.setMaximumSize(new Dimension(180, 60));   
        selectButton.setMinimumSize(new Dimension(180, 60));    
        selectButton.setFont(pixelFont.deriveFont(55f));
        selectButton.setBorder(padding);

        //Button wrapper and properties
        JPanel buttonWrapper = new JPanel();
        buttonWrapper.setLayout(new BoxLayout(buttonWrapper, BoxLayout.X_AXIS));
        buttonWrapper.setPreferredSize(new Dimension(400, 100));  
        buttonWrapper.setMaximumSize(new Dimension(400, 100));   
        buttonWrapper.setMinimumSize(new Dimension(400, 100));   
        buttonWrapper.setAlignmentX(LEFT_ALIGNMENT);
        buttonWrapper.setAlignmentY(CENTER_ALIGNMENT);
        buttonWrapper.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 0));

        //Add back and select to button wrapper
        buttonWrapper.add(backButton);
        buttonWrapper.add(Box.createHorizontalStrut(15));
        buttonWrapper.add(selectButton);
        
        //Switch statement that will initalizes the selected case
        switch(type){

            case "Weapon":

                //Initialize weapon select panel and add to a wrapper 
                weaponSelectPanel = new GUIWeaponSelectPanel();
                JPanel weaponWrapper = new JPanel(new FlowLayout());
                weaponSelectPanel.initWeaponSelection(weaponButtons, buttonWrapper, weaponWrapper, mainPanel);
                weaponWrapper.setMaximumSize(new Dimension(1000, 800));
                weaponWrapper.add(weaponSelectPanel.getWeaponStatsPanel());
                break;

            case "Armor":

                //Initialize armor select panel and add to a wrapper 
                armorSelectPanel = new GUIArmorSelectPanel();
                JPanel armorWrapper = new JPanel(new FlowLayout());
                armorSelectPanel.initArmorSelection(armorButtons, buttonWrapper, armorWrapper, mainPanel);
                armorWrapper.setMaximumSize(new Dimension(1000, 800));
                armorWrapper.add(armorSelectPanel.getArmorStatsPanel());
                break;

            case "Consumable":

                //Initialize consumable select panel and add to a wrapper 
                consumableSelectPanel = new GUIConsumableSelectPanel();
                JPanel consumableWrapper = new JPanel(new FlowLayout());
                consumableSelectPanel.initConsumableSelection(consumableButtons, buttonWrapper, consumableWrapper, mainPanel);
                consumableWrapper.setMaximumSize(new Dimension(1000, 800));
                consumableWrapper.add(consumableSelectPanel.getConsumableStatsPanel());
                break;

            case "Enemy":

                //Initialize enemy select panel and add to a wrapper 
                enemySelectPanel = new GUIEnemySelectPanel();
                JPanel enemyWrapper = new JPanel(new FlowLayout());
                enemySelectPanel.initEnemySelection(enemyButtons, buttonWrapper, enemyWrapper, mainPanel);
                enemyWrapper.setMaximumSize(new Dimension(1000, 800));
                enemyWrapper.add(enemySelectPanel.getEnemyStatsPanel());
                break;

            case "Environment":
    
                //Initialize environment select panel and add to a wrapper 
                environmentSelectPanel = new GUIEnvironmentSelectPanel();
                JPanel environmentWrapper = new JPanel(new FlowLayout());
                environmentSelectPanel.initEnvironmentSelection(environmentButtons, buttonWrapper, environmentWrapper, mainPanel);
                environmentWrapper.setMaximumSize(new Dimension(1000, 800));
                environmentWrapper.add(environmentSelectPanel.getEnvironmentStatsPanel());
                break;

            case "Final":

                //Initializes the selected choices
                currentChoices = new GUICurrentChoices();
                currentChoices.initSelectedChoices(buttonWrapper, mainPanel);

        }

    }


    /**
     * Initializes the game panel
     * @param UIimages Array of UI image assets
     * @param equipmentImages Array of equipment image assets
     * @param equipmentNames Array of correspond equipment names
     * @param playerStats Array of the player object's stats
     * @param enemyStats Array of the enemy object's stats
     * @param statImages Array of stat image assets
     */
    public void initGame(ImageIcon[] UIimages, ImageIcon[] equipmentImages, String[] equipmentNames, int[] playerStats,
             int[] enemyStats, ImageIcon[] statImages){

        //JButtons array that stores buttons to be used for the panel
        JButton[] buttons = new JButton[]{attackButton, defendButton, chargeButton, consumeButton, skillButton, informationButton};

        //For loop that changes/add properties of the buttons in the arary
        for(int i = 0; i < buttons.length; i++){
          
            buttons[i].setPreferredSize(new Dimension(600, 60));  
            buttons[i].setMaximumSize(new Dimension(600, 60));   
            buttons[i].setMinimumSize(new Dimension(600, 60));    
            buttons[i].setFont(pixelFont.deriveFont(50f));
            buttons[i].setBorder(padding);

        }

        //Calls game panel to initialize the game
        gamePanel.initGamePanel(UIimages, buttons, equipmentImages, equipmentNames, mainPanel, playerStats, enemyStats, statImages);

    }


    /**
     * Updates view of the weapon selection panel
     * @param name name of the weapon object
     * @param type type of the weapon object
     * @param image image of the weapon object
     * @param damage atk of the weapon object
     * @param speedPenalty weapon object's speed penalty
     * @param ability weapon object's corresponding ability
     * @param skill weapon object's skill if it possesses any
     * @param condition weapon object's condition if it possesses any
     */
    public void updateWeaponSelectPanel(String name, String type, ImageIcon image, int damage, int speedPenalty, String ability, String skill, String condition){

        weaponSelectPanel.updateView(name, type, image, damage, speedPenalty, ability, skill, condition);
        
    }


    /**
     * Updates view of the armor selection panel
     * @param name name of the armor object
     * @param type type of the armor object
     * @param image image of the armor object
     * @param defense def value of the armor object
     * @param speedPenalty armor object's speed penalty
     */
    public void updateArmorSelectPanel(String name, String type, ImageIcon image, int defense, int speedPenalty){

        armorSelectPanel.updateView(name, type, image, defense, speedPenalty);
        
    }


    /**
     * Updates view of the consumable selection panel
     * @param name name of the consumable object
     * @param type type of the consumable object
     * @param image image of the consumable object
     * @param maxCharge initial charges of the consumable object
     * @param holderDescription text describing consumable's effect on the holder
     * @param targetDescription text describing consumable's effect on the target
     */
    public void updateConsumableSelectPanel(String name, String type, ImageIcon image, int maxCharge, String holderDescription, String targetDescription){

        consumableSelectPanel.updateView(name, type, image, maxCharge, holderDescription, targetDescription);
        
    }


    /**
     * Updates view of the enemy selection panel
     * @param name name of the enemy object
     * @param type type of the enemy object
     * @param image image of the enemy object
     * @param hitPoints Base HP value of the enemy object
     * @param attack base atk value of the enemy object
     * @param defense base def value of the enemy object
     * @param speed base spd value of the enemy object
     * @param weaponName equipped weapon of the enemy object if they have one
     * @param consumableName equipped consumable of the enemy object if they have one
     */
    public void updateEnemySelectPanel(String name, String type, ImageIcon image, int hitPoints, 
            int attack, int defense, int speed, String weaponName, String consumableName){

        enemySelectPanel.updateView(name, type, image, hitPoints, attack, defense, speed, weaponName, consumableName);
        
    }


    /**
     * Updates view of the environment selection panel
     * @param name name of the environment object
     * @param image image of the environment object
     * @param playerDescription environment's effects on the player object
     * @param enemyDescription environment's effects on the enemy object
     */
    public void updateEnvironmentSelectPanel(String name, ImageIcon image, String playerDescription, String enemyDescription){

        environmentSelectPanel.updateView(name, image, playerDescription, enemyDescription);
        
    }


    /**
     * Updates view of the weapon selection panel
     * @param name array of weapon names
     * @param images array of weapon images
     */
    public void updateCurrentSelection(String[] name, ImageIcon[] images){

        currentChoices.updateView(name, images);
        
    }


    /**
     * Updates view and the stats of both player and enemy in the game panel
     * @param playerStats array of player object's current stats
     * @param enemyStats array of enemy object's current stats
     */
    public void updateGamePanel(int[] playerStats, int[] enemyStats){

        gamePanel.updateView(playerStats, enemyStats);

    }


    /**
     * Updates the turn history in the game panel
     */
    public void updateTurnHistory(){

        turnHistory.updateTurnHistory(gamePanel);

    }

    /**
     * Shows the corresponding panel in the mainPanel depending on parameter
     * @param panelType type of the panel to be displayed
     */
    public void showPanel(String panelType){

        //Switch statement that will show the panel and store the name of that panel in an attribute for future logic
        switch(panelType){

            case "Start":

                cardLayout.show(mainPanel, "menu");
                currentPanel = "menu";
                break;

            case "Enter Name":

                cardLayout.show(mainPanel, "name");
                currentPanel = "name";
                break;

            case "Select Weapon":

                cardLayout.show(mainPanel, "weaponSelect");
                currentPanel = "weaponSelect";
                break;

            case "Select Armor":

                cardLayout.show(mainPanel, "armorSelect");      
                currentPanel = "armorSelect";
                break;    

            case "Select Consumable":

                cardLayout.show(mainPanel, "consumableSelect");
                currentPanel = "consumableSelect";
                break;

            case "Select Enemy":

                cardLayout.show(mainPanel, "enemySelect");      
                currentPanel = "enemySelect";
                break;   
                
            case "Select Environment":

                cardLayout.show(mainPanel, "environmentSelect");      
                currentPanel = "environmentSelect";
                break; 
                
            case "Current Choices":

                cardLayout.show(mainPanel, "currentChoices");
                currentPanel = "currentChoices";         
                break;

            case "Game":

                cardLayout.show(mainPanel, "game");
                currentPanel = "game";         
                break;

            case "Game Over":

                cardLayout.show(mainPanel, "gameOver");
                currentPanel = "gameOver";

        }

    }


    /**
     * Shows error and warning messages for any wrong inputs and choices
     * @param errorType type of error encountered
     */
    public void showErrorMessage(String errorType){

        //Switch statement that will activate the corresponde JOptionPane which will show a ERROR_MESSAGE
        switch(errorType){

            //No input for name or greater than 15 characters
            case "Invalid Name":

                JOptionPane.showMessageDialog(null, "Name can't be blank or greater than 15 characters!", 
                        "Validation Result", JOptionPane.ERROR_MESSAGE);
                break;

            //Clicked the next button without selecting a choice
            case "No Selection":

                JOptionPane.showMessageDialog(null, "Please select a choice!", 
                        "Validation Result", JOptionPane.ERROR_MESSAGE);
                break;

            //Clicked the consume button but has no consumable equipped
            case "No Consumable":

                JOptionPane.showMessageDialog(null, "No Consumable equipped!", 
                        "Validation Result", JOptionPane.WARNING_MESSAGE);
                break;

            //Clicked the consume button but has no charges left for equipped consumable
            case "No Charges":

                JOptionPane.showMessageDialog(null, "You have run out of conusmable charges!", 
                        "Validation Result", JOptionPane.WARNING_MESSAGE);
                break;

            //Clicked the skill button but has no weapon with skill
            case "No Skill":

                JOptionPane.showMessageDialog(null, "No Weapon with a skill equipped!", 
                        "Validation Result", JOptionPane.WARNING_MESSAGE);
                break;

            //Clicked the skill button but skill condition has not been met
            case "Skill Condition False":

                JOptionPane.showMessageDialog(null, "Skill condition has not been met!", 
                        "Validation Result", JOptionPane.WARNING_MESSAGE);
                break;


        }
    }


    /**
     * Clears the text stored in tfName
     */
    public void clearInput(){

        tfName.setText("");

    }





}
