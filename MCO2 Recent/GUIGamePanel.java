import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Class file for the GUIGamePanel
 *<p>
 *     The Game Panel displays all the relevant information of the ongoing battle keeping track of stat changes to both the player and enemy
 *</p>
 * @author Martin
 */
public class GUIGamePanel extends JFrame{
    
    private JPanel displayPanel;
    private JPanel informationPanel;
    private JPanel historyPanel;

    private JLayeredPane gameLayers;
    private Image scaledImage;
    private JLabel playerImage;
    private JLabel enemyImage;

    private JLabel playerName;
    private JLabel weaponName;
    private JLabel armorName;
    private JLabel consumableName;
    private JLabel enemyName;
    private JLabel environmentName;
    private JLabel playerAttackLabel;
    private JLabel playerDefenseLabel;
    private JLabel playerSpeedLabel;
    private JLabel enemyAttackLabel;
    private JLabel enemyDefenseLabel;
    private JLabel enemySpeedLabel;


    private JProgressBar playerHealthBar;
    private JProgressBar playerSkillBar;
    private JProgressBar enemyHealthBar;
    private JProgressBar enemySkillBar;

    private JLabel historyTitle;
    private JTextArea turnHistory;
    private JTextArea recentTurnHistory;
    private JScrollPane turnHistoryScroll;


    /**
     * Constructor for the GUIGamePanel class
     */
    public GUIGamePanel(){

        playerImage = new JLabel();
        enemyImage = new JLabel();

        playerName = new JLabel();
        playerName.setFont(new Font("Medodica Regular", Font.PLAIN, 40));

        weaponName = new JLabel();
        weaponName.setFont(new Font("Medodica Regular", Font.PLAIN, 40));
        armorName = new JLabel();
        armorName.setFont(new Font("Medodica Regular", Font.PLAIN, 40));
        consumableName = new JLabel();
        consumableName.setFont(new Font("Medodica Regular", Font.PLAIN, 40));
        enemyName = new JLabel();
        enemyName.setFont(new Font("Medodica Regular", Font.PLAIN, 40));
        environmentName = new JLabel();
        environmentName.setFont(new Font("FFF Forward", Font.PLAIN, 40));
    
        gameLayers = new JLayeredPane();
        playerAttackLabel = new JLabel();
        playerAttackLabel.setFont(new Font("Medodica Regular", Font.PLAIN, 40));
        playerDefenseLabel = new JLabel();
        playerDefenseLabel.setFont(new Font("Medodica Regular", Font.PLAIN, 40));
        playerSpeedLabel = new JLabel();
        playerSpeedLabel.setFont(new Font("Medodica Regular", Font.PLAIN, 40));
        enemyAttackLabel = new JLabel();
        enemyAttackLabel.setFont(new Font("Medodica Regular", Font.PLAIN, 40));
        enemyDefenseLabel = new JLabel();
        enemyDefenseLabel.setFont(new Font("Medodica Regular", Font.PLAIN, 40));
        enemySpeedLabel = new JLabel();
        enemySpeedLabel.setFont(new Font("Medodica Regular", Font.PLAIN, 40));

        playerImage = new JLabel();
        historyPanel = new JPanel();

        turnHistory = new JTextArea();
        turnHistory.setFont(new Font("Medodica Regular", Font.PLAIN, 25));
        turnHistory.setEditable(false);
        turnHistory.setLineWrap(true);
        turnHistory.setWrapStyleWord(true);
        turnHistory.setBackground(Color.WHITE);

        recentTurnHistory = new JTextArea();
        recentTurnHistory.setBounds(50, 200, 600, 300);
        recentTurnHistory.setFont(new Font("Medodica Regular", Font.PLAIN, 25));
        recentTurnHistory.setForeground(Color.BLUE);
        recentTurnHistory.setEditable(false);
        recentTurnHistory.setLineWrap(true);
        recentTurnHistory.setWrapStyleWord(true);
        recentTurnHistory.setOpaque(false);

        historyTitle = new JLabel("Turn History");
        historyTitle.setFont(new Font("FFF Forward", Font.PLAIN, 40));

        turnHistoryScroll = new JScrollPane(turnHistory);
        turnHistoryScroll.setBounds(0, 0, 450, 700);
        turnHistoryScroll.setBorder(BorderFactory.createLineBorder(Color.BLACK, 8));
        
    }

    /**
     * Getter to retrieve the information panel
     * @return information panel
     */
    public JPanel getInformationPanel(){

        return informationPanel;

    }

    /**
     * Initializes the game panel
     * @param uiImages Array of ui assets
     * @param buttons Array of buttons
     * @param equipmentImages Array of equipment image assets
     * @param equipmentNames Array of corresponding equipment names
     * @param mainPanel main panel to display the Game Panel on
     * @param playerStats Array containing a list of the player's stat values
     * @param enemyStats Array containing a list of the enemy's stat values
     * @param statImages Array of stat image assets
     */
    public void initGamePanel(ImageIcon[] uiImages, JButton[] buttons, ImageIcon[] equipmentImages, String[] equipmentNames, JPanel mainPanel, int[] playerStats,
             int[] enemyStats, ImageIcon[] statImages){

        //GAME DISPLAY PANEL
        informationPanel = new JPanel();
        gameLayers = new JLayeredPane();
        gameLayers.setPreferredSize(new Dimension( 1500,1000)); 

        JPanel backgroundPanel = new GUIBackgroundPanel(uiImages[0].getImage());
        backgroundPanel.setBounds(0, 0, 1500,1000);


        //Buffered Image for empty display
        BufferedImage emptyImage = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);

        //Weapon Image Overlay
        if(!equipmentNames[0].equals("NONE"))
            scaledImage =  equipmentImages[0].getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        else
            scaledImage = emptyImage;

        JLabel playerWeaponOverlay = new JLabel(new ImageIcon(scaledImage));
        playerWeaponOverlay.setBounds(470, 445, 150, 150);

        //Armor Image Overlay
        if(!equipmentNames[1].equals("NONE"))
            scaledImage =  equipmentImages[1].getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        else
            scaledImage = emptyImage;

        JLabel playerArmorOverlay = new JLabel(new ImageIcon(scaledImage));
        playerArmorOverlay.setBounds(360, 530, 150, 150);

        //Consumable Image Overlay
        if(!equipmentNames[2].equals("NONE"))
            scaledImage =  equipmentImages[2].getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        else
            scaledImage = emptyImage;

        JLabel playerConsumableOverlay = new JLabel(new ImageIcon(scaledImage));
        playerConsumableOverlay.setBounds(350, 640, 75, 75);

        //Player Image        
        scaledImage = uiImages[1].getImage().getScaledInstance(450, 500, Image.SCALE_SMOOTH);
        playerImage.setIcon(new ImageIcon(scaledImage));
        playerImage.setBounds(200, 350, 450, 500);

        //Enemy Image
        scaledImage = uiImages[2].getImage().getScaledInstance(450, 500, Image.SCALE_SMOOTH);
        enemyImage.setIcon(new ImageIcon(scaledImage));
        enemyImage.setBounds(800, 350, 450, 500);


        //BUTTONS
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setAlignmentX(RIGHT_ALIGNMENT);
        buttonsPanel.setAlignmentY(BOTTOM_ALIGNMENT);
        buttons[0].setText("Attack");
        buttons[1].setText("Defend");
        buttons[2].setText("Charge");
        buttons[3].setText("Consume");
        buttons[4].setText("Skill");
        buttonsPanel.setBounds(50, 500, 200, 900);
        buttonsPanel.setOpaque(false); 

        //Creates a struct for each button added
        for(int i = 0; i < buttons.length; i++){

            buttonsPanel.add(buttons[i]);
            buttonsPanel.add(Box.createVerticalStrut(10));

        }

        //Function to create information panel
        initInformationPanel(uiImages[5], buttons[5], equipmentImages, equipmentNames);

        //Function to  create progress bars 
        initProgressBars(playerStats, enemyStats, statImages, uiImages[3], uiImages[4]);

        //CREATE MENU DISPLAY
        buttons[5].setBounds(20, 20, 50, 50);


        //Combines all of the panels into one main panel
        gameLayers.add(backgroundPanel, Integer.valueOf(0));
        gameLayers.add(playerImage, Integer.valueOf(1));
        gameLayers.add(playerWeaponOverlay, Integer.valueOf(3));
        gameLayers.add(playerArmorOverlay, Integer.valueOf(3));
        gameLayers.add(playerConsumableOverlay, Integer.valueOf(3));
        gameLayers.add(enemyImage, Integer.valueOf(1));
        gameLayers.add(buttonsPanel, Integer.valueOf(2));
        gameLayers.add(buttons[5], Integer.valueOf(3));
        gameLayers.add(recentTurnHistory, Integer.valueOf(3));
        gameLayers.add(informationPanel, Integer.valueOf(4));


        //Create display panel and add to main panel
        displayPanel = new JPanel(new BorderLayout());
        displayPanel.add(gameLayers, BorderLayout.CENTER);
        mainPanel.add(displayPanel, "game");

    }

    private void initInformationPanel(ImageIcon buttonImage, JButton button, ImageIcon[] equipmentImages, String[] equipmentNames){

        //INFORMATION SECTION
        scaledImage =  buttonImage.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaledImage));

        informationPanel = new JPanel();
        informationPanel.setLayout(new BoxLayout(informationPanel, BoxLayout.X_AXIS));
        informationPanel.setBackground(Color.GRAY);
        informationPanel.setBounds(100, 20, 1200, 800);
        informationPanel.setVisible(false);

        JPanel infoLeftColumn = new JPanel();
        infoLeftColumn.setLayout(new BoxLayout(infoLeftColumn, BoxLayout.Y_AXIS));
        infoLeftColumn.setBounds(0, 0, 700, 800);
        infoLeftColumn.setOpaque(false);

        //Create the panel for player info
        JPanel playerEquipInfo = new JPanel();
        playerEquipInfo.setOpaque(false);
        playerEquipInfo.setLayout(new BoxLayout(playerEquipInfo, BoxLayout.X_AXIS));

        //Create the player info and set their properties
        JPanel playerWeaponInfo = new JPanel();
        JPanel playerArmorInfo = new JPanel();
        JPanel playerConsumableInfo = new JPanel();
        playerWeaponInfo.setLayout(new BoxLayout(playerWeaponInfo, BoxLayout.Y_AXIS));
        playerArmorInfo.setLayout(new BoxLayout(playerArmorInfo, BoxLayout.Y_AXIS));
        playerConsumableInfo.setLayout(new BoxLayout(playerConsumableInfo, BoxLayout.Y_AXIS));
        playerWeaponInfo.setOpaque(false);
        playerArmorInfo.setOpaque(false);
        playerConsumableInfo.setOpaque(false);

        //Update the player weapon name and image
        JLabel playerWeaponName = new JLabel(equipmentNames[0]);
        playerWeaponName.setFont(new Font("Medodica Regular", Font.PLAIN, 40));
        playerWeaponName.setAlignmentX(CENTER_ALIGNMENT);
        scaledImage =  equipmentImages[0].getImage().getScaledInstance(225, 225, Image.SCALE_SMOOTH);
        JLabel playerWeaponImage = new JLabel(new ImageIcon(scaledImage));
        playerWeaponImage.setAlignmentX(CENTER_ALIGNMENT);
        playerWeaponInfo.add(playerWeaponImage);
        playerWeaponInfo.add(playerWeaponName);

        //Update the player armor name and image
        JLabel playerArmorName = new JLabel(equipmentNames[1]);
        playerArmorName.setFont(new Font("Medodica Regular", Font.PLAIN, 40));
        playerArmorName.setAlignmentX(CENTER_ALIGNMENT);
        scaledImage =  equipmentImages[1].getImage().getScaledInstance(225, 225, Image.SCALE_SMOOTH);
        JLabel playerArmorImage = new JLabel(new ImageIcon(scaledImage));
        playerArmorImage.setAlignmentX(CENTER_ALIGNMENT);
        playerArmorInfo.add(playerArmorImage);
        playerArmorInfo.add(playerArmorName);

        //Update the player consumable name and image
        JLabel playerConsumableName = new JLabel(equipmentNames[2]);
        playerConsumableName.setFont(new Font("Medodica Regular", Font.PLAIN, 40));
        playerConsumableName.setAlignmentX(CENTER_ALIGNMENT);
        scaledImage =  equipmentImages[2].getImage().getScaledInstance(225, 225, Image.SCALE_SMOOTH);
        JLabel playerConsumableImage = new JLabel(new ImageIcon(scaledImage));
        playerConsumableImage.setAlignmentX(CENTER_ALIGNMENT);
        playerConsumableInfo.add(playerConsumableImage);
        playerConsumableInfo.add(playerConsumableName);

        //Add all player components to playerEquipInfo
        playerEquipInfo.add(Box.createHorizontalStrut(10));
        playerEquipInfo.add(playerWeaponInfo);
        playerEquipInfo.add(Box.createHorizontalStrut(20));
        playerEquipInfo.add(playerArmorInfo);
        playerEquipInfo.add(Box.createHorizontalStrut(20));
        playerEquipInfo.add(playerConsumableInfo);
        playerEquipInfo.add(Box.createHorizontalStrut(10));

        //Create the panel for enemy info
        JPanel enemyEquipInfo = new JPanel();    
        enemyEquipInfo.setOpaque(false);
        enemyEquipInfo.setLayout(new BoxLayout(enemyEquipInfo, BoxLayout.X_AXIS)); 

        //Create the enemy info and set their properties
        JPanel enemyWeaponInfo = new JPanel();
        JPanel enemyArmorInfo = new JPanel();
        JPanel enemyConsumableInfo = new JPanel();
        enemyWeaponInfo.setLayout(new BoxLayout(enemyWeaponInfo, BoxLayout.Y_AXIS));
        enemyArmorInfo.setLayout(new BoxLayout(enemyArmorInfo, BoxLayout.Y_AXIS));
        enemyConsumableInfo.setLayout(new BoxLayout(enemyConsumableInfo, BoxLayout.Y_AXIS));
        enemyWeaponInfo.setOpaque(false);
        enemyArmorInfo.setOpaque(false);
        enemyConsumableInfo.setOpaque(false);

        //Update the enemy weapon name and image
        JLabel enemyWeaponName = new JLabel(equipmentNames[3]);
        enemyWeaponName.setFont(new Font("Medodica Regular", Font.PLAIN, 40));
        enemyWeaponName.setAlignmentX(CENTER_ALIGNMENT);
        scaledImage =  equipmentImages[3].getImage().getScaledInstance(225, 225, Image.SCALE_SMOOTH);
        JLabel enemyWeaponImage = new JLabel(new ImageIcon(scaledImage));
        enemyWeaponImage.setAlignmentX(CENTER_ALIGNMENT);
        enemyWeaponInfo.add(enemyWeaponImage);
        enemyWeaponInfo.add(enemyWeaponName);

        //Update the enemy armor name and image
        JLabel enemyArmorName = new JLabel(equipmentNames[4]);
        enemyArmorName.setFont(new Font("Medodica Regular", Font.PLAIN, 40));
        enemyArmorName.setAlignmentX(CENTER_ALIGNMENT);
        scaledImage =  equipmentImages[4].getImage().getScaledInstance(225, 225, Image.SCALE_SMOOTH);
        JLabel enemyArmorImage = new JLabel(new ImageIcon(scaledImage));
        enemyArmorImage.setAlignmentX(CENTER_ALIGNMENT);
        enemyArmorInfo.add(enemyArmorImage);
        enemyArmorInfo.add(enemyArmorName);

        //Update the enemy consumable name and image
        JLabel enemyConsumableName = new JLabel(equipmentNames[5]);
        enemyConsumableName.setFont(new Font("Medodica Regular", Font.PLAIN, 40));
        enemyConsumableName.setAlignmentX(CENTER_ALIGNMENT);
        scaledImage =  equipmentImages[5].getImage().getScaledInstance(225, 225, Image.SCALE_SMOOTH);
        JLabel enemyConsumableImage = new JLabel(new ImageIcon(scaledImage));
        enemyConsumableImage.setAlignmentX(CENTER_ALIGNMENT);
        enemyConsumableInfo.add(enemyConsumableImage);
        enemyConsumableInfo.add(enemyConsumableName);

        //Add all enemy components to enemyEquipInfo
        enemyEquipInfo.add(Box.createHorizontalStrut(10));
        enemyEquipInfo.add(enemyWeaponInfo);
        enemyEquipInfo.add(Box.createHorizontalStrut(20));
        enemyEquipInfo.add(enemyArmorInfo);
        enemyEquipInfo.add(Box.createHorizontalStrut(20));
        enemyEquipInfo.add(enemyConsumableInfo);
        enemyEquipInfo.add(Box.createHorizontalStrut(10));


        //Creating player and enemy equipment labels and their properties 
        JLabel playerInfoTitle = new JLabel("Player Equipment");
        playerInfoTitle.setFont(new Font("FFF Forward", Font.PLAIN, 40));
        playerInfoTitle.setAlignmentX(CENTER_ALIGNMENT);

        JLabel enemyInfoTitle = new JLabel("Enemy Equipment");
        enemyInfoTitle.setFont(new Font("FFF Forward", Font.PLAIN, 40));
        enemyInfoTitle.setAlignmentX(CENTER_ALIGNMENT);


        //Set the turnHistory Panel and thei properties
        turnHistory.setText("");
        recentTurnHistory.setText("");
        historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.Y_AXIS));
        historyPanel.setBounds(0, 0, 450, 800);
        historyPanel.setBackground(Color.GRAY);

        //Add components to historyPanel
        historyPanel.add(historyTitle);
        historyPanel.add(Box.createVerticalStrut(10));
        historyPanel.add(turnHistoryScroll);        


        //Comnbining player and enemy components to the leftColmn
        infoLeftColumn.add(Box.createHorizontalStrut(5));
        infoLeftColumn.add(playerInfoTitle);
        infoLeftColumn.add(Box.createHorizontalStrut(5));
        infoLeftColumn.add(playerEquipInfo);
        infoLeftColumn.add(Box.createHorizontalStrut(10));
        infoLeftColumn.add(enemyInfoTitle);
        infoLeftColumn.add(Box.createHorizontalStrut(5));
        infoLeftColumn.add(enemyEquipInfo);

        //Add both column and historyPanel to informationPanel
        informationPanel.add(infoLeftColumn);
        informationPanel.add(historyPanel);

    }


    //Initializes the progress bars
    private void initProgressBars(int[] playerStats, int[] enemyStats, ImageIcon[] statImages, ImageIcon healthIcon, ImageIcon skillIcon){;
        
        int playerHealth = playerStats[0];
        int playerSkillCooldown = playerStats[1];
        int enemyHealth = enemyStats[0];
        int enemySkillCooldown = enemyStats[1];

        int playerAttack = playerStats[2];
        int playerDefense = playerStats[3];
        int playerSpeed = playerStats[4];
        int enemyAttack = enemyStats[2];
        int enemyDefense = enemyStats[3];
        int enemySpeed = enemyStats[4];

        ImageIcon attackImage = statImages[0];
        ImageIcon defenseImage = statImages[1];
        ImageIcon speedImage = statImages[2];

        Dimension healthBarDimensions = new Dimension(500, 50);
        Dimension skillBarDimensions = new Dimension(400, 50);

        playerHealthBar = new JProgressBar(0, playerHealth);
        playerSkillBar = new JProgressBar(0, playerSkillCooldown);
        enemyHealthBar = new JProgressBar(0, enemyHealth);
        enemySkillBar = new JProgressBar(0, enemySkillCooldown);

        playerHealthBar.setValue(playerStats[0]);
        playerSkillBar.setValue(0);
        enemyHealthBar.setValue(enemyStats[0]);
        enemySkillBar.setValue(0);

        playerHealthBar.setString("" + playerHealthBar.getValue() + " / " + playerHealthBar.getMaximum());
        playerSkillBar.setString("" + playerSkillBar.getValue() + " / " + playerSkillBar.getMaximum());
        enemyHealthBar.setString("" +enemyHealthBar.getValue() + " / " + enemyHealthBar.getMaximum());
        enemySkillBar.setString("" +enemySkillBar.getValue() + " / " + enemySkillBar.getMaximum());

        playerHealthBar.setFont(new Font("FFF Forward", Font.PLAIN, 20));
        playerSkillBar.setFont(new Font("FFF Forward", Font.PLAIN, 20));
        enemyHealthBar.setFont(new Font("FFF Forward", Font.PLAIN, 20));
        enemySkillBar.setFont(new Font("FFF Forward", Font.PLAIN, 20));

        playerHealthBar.setPreferredSize(healthBarDimensions);
        playerSkillBar.setPreferredSize(skillBarDimensions);
        enemyHealthBar.setPreferredSize(healthBarDimensions);
        enemySkillBar.setPreferredSize(skillBarDimensions);

        playerHealthBar.setMaximumSize(healthBarDimensions);
        playerSkillBar.setMaximumSize(skillBarDimensions);
        enemyHealthBar.setMaximumSize(healthBarDimensions);
        enemySkillBar.setMaximumSize(skillBarDimensions);

        playerHealthBar.setMinimumSize(healthBarDimensions);
        playerSkillBar.setMinimumSize(skillBarDimensions);
        enemyHealthBar.setMinimumSize(healthBarDimensions);
        enemySkillBar.setMinimumSize(skillBarDimensions);

        playerHealthBar.setStringPainted(true);
        enemyHealthBar.setStringPainted(true);
        playerHealthBar.setStringPainted(true);
        enemyHealthBar.setStringPainted(true);

        playerHealthBar.setForeground(Color.RED);
        playerSkillBar.setForeground(Color.YELLOW);      
        enemyHealthBar.setForeground(Color.RED);
        playerSkillBar.setForeground(Color.YELLOW);

        playerHealthBar.setBackground(Color.GRAY);
        playerSkillBar.setBackground(Color.GRAY);      
        enemyHealthBar.setBackground(Color.GRAY);
        enemySkillBar.setBackground(Color.GRAY);


        JLabel playerHealthIcon = new JLabel();
        playerHealthIcon.setIcon(new ImageIcon(healthIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        JLabel playerSkillIcon = new JLabel();
        playerSkillIcon.setIcon(new ImageIcon(skillIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        JLabel enemyHealthIcon = new JLabel();
        enemyHealthIcon.setIcon(new ImageIcon(healthIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        JLabel enemySkillIcon = new JLabel();
        enemySkillIcon.setIcon(new ImageIcon(skillIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));


        JPanel playerHealthWrapper = new JPanel();
        playerHealthWrapper.setLayout(new BoxLayout(playerHealthWrapper, BoxLayout.X_AXIS));
        playerHealthWrapper.setMaximumSize(new Dimension(600, 50));
        playerHealthWrapper.setPreferredSize(new Dimension(600, 50));
        playerHealthWrapper.setMinimumSize(new Dimension(600, 50));
        playerHealthWrapper.setOpaque(false);
        playerHealthWrapper.add(playerHealthIcon);
        playerHealthWrapper.add(Box.createHorizontalStrut(10));
        playerHealthWrapper.add(playerHealthBar);        


        JPanel playerSkillWrapper = new JPanel();
        playerSkillWrapper.setLayout(new BoxLayout(playerSkillWrapper, BoxLayout.X_AXIS));
        playerSkillWrapper.setMaximumSize(new Dimension(600, 50));
        playerSkillWrapper.setPreferredSize(new Dimension(600, 50));
        playerSkillWrapper.setMinimumSize(new Dimension(600, 50));
        playerSkillWrapper.setOpaque(false);
        playerSkillWrapper.add(playerSkillIcon);
        playerSkillWrapper.add(Box.createHorizontalStrut(10));
        playerSkillWrapper.add(playerSkillBar); 

    
        JPanel enemyHealthWrapper = new JPanel();
        enemyHealthWrapper.setLayout(new BoxLayout(enemyHealthWrapper, BoxLayout.X_AXIS));
        enemyHealthWrapper.setMaximumSize(new Dimension(600, 50));
        enemyHealthWrapper.setPreferredSize(new Dimension(600, 50));
        enemyHealthWrapper.setMinimumSize(new Dimension(600, 50));
        enemyHealthWrapper.setOpaque(false);
        enemyHealthWrapper.add(enemyHealthIcon);
        enemyHealthWrapper.add(Box.createHorizontalStrut(10));
        enemyHealthWrapper.add(enemyHealthBar);        


        JPanel enemySkillWrapper = new JPanel();
        enemySkillWrapper.setLayout(new BoxLayout(enemySkillWrapper, BoxLayout.X_AXIS));
        enemySkillWrapper.setMaximumSize(new Dimension(600, 50));
        enemySkillWrapper.setPreferredSize(new Dimension(600, 50));
        enemySkillWrapper.setMinimumSize(new Dimension(600, 50));
        enemySkillWrapper.setOpaque(false);
        enemySkillWrapper.add(enemySkillIcon);
        enemySkillWrapper.add(Box.createHorizontalStrut(10));
        enemySkillWrapper.add(enemySkillBar);

        JPanel playerStatsPanel = initStatBars(playerAttack, playerDefense, playerSpeed, attackImage, defenseImage, speedImage,
        playerAttackLabel, playerDefenseLabel, playerSpeedLabel);
        playerStatsPanel.setAlignmentX(CENTER_ALIGNMENT);
        playerStatsPanel.setMaximumSize(new Dimension(430, 35));


        JPanel playerBarsWrapper = new JPanel();
        playerBarsWrapper.setLayout(new BoxLayout(playerBarsWrapper, BoxLayout.Y_AXIS));
        playerBarsWrapper.setBounds(50, 50, 650, 200);
        playerBarsWrapper.setOpaque(false);
        playerBarsWrapper.add(playerStatsPanel);
        playerBarsWrapper.add(playerHealthWrapper);
        playerBarsWrapper.add(Box.createVerticalStrut(5));
        if(playerSkillCooldown != 0)
            playerBarsWrapper.add(playerSkillWrapper); 
            
        JPanel enemyStatsPanel = initStatBars(enemyAttack, enemyDefense, enemySpeed, attackImage, defenseImage, speedImage,
            enemyAttackLabel, enemyDefenseLabel, enemySpeedLabel);
        enemyStatsPanel.setMaximumSize(new Dimension(430, 35));

        JPanel enemyBarsWrapper = new JPanel();
        enemyBarsWrapper.setLayout(new BoxLayout(enemyBarsWrapper, BoxLayout.Y_AXIS));
        enemyBarsWrapper.setBounds(830, 50, 650, 200);
        enemyBarsWrapper.setOpaque(false);

        enemyBarsWrapper.add(enemyStatsPanel);
        enemyBarsWrapper.add(enemyHealthWrapper);
        enemyBarsWrapper.add(Box.createVerticalStrut(5));
        if(enemySkillCooldown != 0)
            enemyBarsWrapper.add(enemySkillWrapper);  


        gameLayers.add(playerBarsWrapper, Integer.valueOf(2));
        gameLayers.add(enemyBarsWrapper, Integer.valueOf(2));

    }


    private JPanel initStatBars(int attack, int defense, int speed, ImageIcon attackImage, ImageIcon defenseImage, ImageIcon speedImage,
            JLabel attackLabel, JLabel defenseLabel, JLabel speedLabel){

        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.X_AXIS));
        statsPanel.setOpaque(false);
        statsPanel.setMaximumSize(new Dimension(650, 35));
        statsPanel.setPreferredSize(new Dimension(650, 35));
        statsPanel.setMinimumSize(new Dimension(650, 35));
                
        JLabel attackIcon = new JLabel();
        attackIcon.setIcon(new ImageIcon (attackImage.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH)));
        JLabel defenseIcon = new JLabel();
        defenseIcon.setIcon(new ImageIcon (defenseImage.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH)));
        JLabel speedIcon = new JLabel();
        speedIcon.setIcon(new ImageIcon (speedImage.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH)));

        attackLabel.setText("" + attack);
        defenseLabel.setText("" + defense);
        speedLabel.setText("" + speed);

        statsPanel.add(Box.createHorizontalStrut(20));
        statsPanel.add(attackIcon);
        statsPanel.add(Box.createHorizontalStrut(20));
        statsPanel.add(attackLabel);

        statsPanel.add(Box.createHorizontalStrut(70));
        statsPanel.add(defenseIcon);
        statsPanel.add(Box.createHorizontalStrut(20));
        statsPanel.add(defenseLabel);

        statsPanel.add(Box.createHorizontalStrut(70));
        statsPanel.add(speedIcon);
        statsPanel.add(Box.createHorizontalStrut(20));
        statsPanel.add(speedLabel);

        return statsPanel;

    }

    /**
     * Updates the stat display of both the player and enemy
     * @param playerStats Array containing a list of the player's current stat values
     * @param enemyStats Array containing a list of the enemy's current stat values
     */
    public void updateView(int[] playerStats, int[] enemyStats){

        int playerHealth = playerStats[0];
        int playerSkillCooldown = playerStats[1];
        int playerAttack = playerStats[2];
        int playerDefense = playerStats[3];
        int playerSpeed = playerStats[4]; 

        int enemyHealth = enemyStats[0];
        int enemySkillCooldown = enemyStats[1];
        int enemyAttack = enemyStats[2];
        int enemyDefense = enemyStats[3];
        int enemySpeed = enemyStats[4]; 

        if(playerHealth > playerHealthBar.getMaximum())
            playerHealthBar.setMaximum(playerHealth);

        playerHealthBar.setValue(playerHealth); 


        playerSkillBar.setValue(playerSkillCooldown);
        enemyHealthBar.setValue(enemyHealth);
        enemySkillBar.setValue(enemySkillCooldown);


        playerHealthBar.setString("" + playerHealthBar.getValue() + " / " + playerHealthBar.getMaximum());
        playerSkillBar.setString("" + playerSkillBar.getValue() + " / " + playerSkillBar.getMaximum());
        enemyHealthBar.setString("" +enemyHealthBar.getValue() + " / " + enemyHealthBar.getMaximum());
        enemySkillBar.setString("" +enemySkillBar.getValue() + " / " + enemySkillBar.getMaximum());

        playerAttackLabel.setText("" + playerAttack);
        playerDefenseLabel.setText("" + playerDefense);
        playerSpeedLabel.setText("" + playerSpeed);

        enemyAttackLabel.setText("" + enemyAttack);
        enemyDefenseLabel.setText("" + enemyDefense);
        enemySpeedLabel.setText("" + enemySpeed);
    }

    /**
     * Updates the turnHistory with the most recent actions performed in the battle
     * @param turnHistoryList list of actions performed in the most recent turn
     */
    public void updateTurnHistory(ArrayList<String> turnHistoryList){

        String turnHistoryLine;
        int size = turnHistoryList.size();

        recentTurnHistory.setText("");

        for(int i = 0; i < size; i++){

            if(i == size - 1)
                turnHistoryLine = turnHistoryList.get(i) + '\n';
            else
                turnHistoryLine = turnHistoryList.get(i);

            turnHistory.append(turnHistoryLine);
            recentTurnHistory.append(turnHistoryLine);

        }

        turnHistoryList.clear();

    }



}
