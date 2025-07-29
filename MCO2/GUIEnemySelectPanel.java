import javax.swing.*;
import javax.swing.border.Border;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;

/**
 * Class file for managing the enemy selection panel
 * @author Martin
 */
public class GUIEnemySelectPanel extends JFrame{

    private boolean firstUpdate = false;

    private JPanel enemySelection;
    private JPanel enemyStatsPanel;
    private JLabel enemyName;
    private JLabel enemyImage;
    private JLabel enemyType;
    private JLabel enemyHitPoints;
    private JLabel enemyAttack;
    private JLabel enemyDefense;
    private JLabel enemySpeed;
    private JLabel enemyWeapon;
    private JLabel enemyConsumable;

    //Wrappers
    JPanel nameWrapper;

    //For use
    private Border padding = BorderFactory.createEmptyBorder(15, 15, 15, 15);
    private Font largeText = new Font("SansSerif", Font.BOLD , 40);    
    private Font smallText = new Font("SansSerif", Font.BOLD , 20);

    /**
     * Constructor for the GUIEnemySelectPanel class
     */
    public GUIEnemySelectPanel(){

        enemyStatsPanel = new JPanel();
        enemyStatsPanel.setLayout(new BoxLayout(enemyStatsPanel, BoxLayout.Y_AXIS));
        enemyStatsPanel.setPreferredSize(new Dimension(700, 700));


        enemyName = new JLabel();
        enemyImage = new JLabel();
        enemyType = new JLabel();
        enemyHitPoints = new JLabel();
        enemyAttack = new JLabel();
        enemyDefense = new JLabel();
        enemySpeed = new JLabel();
        enemyWeapon = new JLabel();
        enemyConsumable = new JLabel();

        enemyName.setFont(largeText);

        enemyName.setAlignmentX(CENTER_ALIGNMENT);
        enemyImage.setAlignmentX(CENTER_ALIGNMENT);

    }

    /**
     * Getter to retrieve the enemy stats panel
     * @return enemy stats panel
     */
    public JPanel getEnemyStatsPanel(){

        return enemyStatsPanel;

    }

    /**
     * Initializes the consumable selection
     * @param buttons Array of buttons
     * @param buttonWrapper JPanel to wrap around buttons
     * @param wrapper stores the stats panel
     * @param mainPanel Panel to display selection
     */
    public void initEnemySelection(JButton[] buttons, JPanel buttonWrapper, JPanel wrapper, JPanel mainPanel){

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 8));


        JPanel enemyChoices = new JPanel();
        enemyChoices.setLayout(new BoxLayout(enemyChoices, BoxLayout.Y_AXIS));
        enemyChoices.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10)); 

        JLabel enemyponSelectLabel = new JLabel("Enemy Selection");
        enemyponSelectLabel.setFont(largeText);

        JPanel textWrapper = new JPanel();
        textWrapper.setLayout(new BoxLayout(textWrapper, BoxLayout.Y_AXIS));
        textWrapper.setPreferredSize(new Dimension(450, 100));  
        textWrapper.setMaximumSize(new Dimension(450, 100));   
        textWrapper.setMinimumSize(new Dimension(450, 100)); 
        textWrapper.setAlignmentX(RIGHT_ALIGNMENT);
        textWrapper.setAlignmentY(CENTER_ALIGNMENT);  
        textWrapper.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 15));
        textWrapper.add(enemyponSelectLabel);

        topPanel.add(buttonWrapper, BorderLayout.WEST);
        topPanel.add(textWrapper, BorderLayout.EAST);

        for(int i = 0; i < buttons.length; i++)
            createEnemyButtons(enemyChoices, buttons[i]);

        JScrollPane vertScroll = new JScrollPane(enemyChoices);
        vertScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        vertScroll.getVerticalScrollBar().setUnitIncrement(8);
        vertScroll.setBorder(padding);

        JPanel vertLine = new JPanel();
        vertLine.setPreferredSize(new Dimension(8, 800));
        vertLine.setBorder(BorderFactory.createLineBorder(Color.BLACK, 8));


        JPanel westWrapper = new JPanel();
        westWrapper.setLayout(new BoxLayout(westWrapper, BoxLayout.X_AXIS));
        westWrapper.add(vertScroll);
        westWrapper.add(Box.createHorizontalStrut(75));
        westWrapper.add(vertLine);



        enemySelection = new JPanel(new BorderLayout());
        enemySelection.add(westWrapper, BorderLayout.WEST);
        enemySelection.add(topPanel, BorderLayout.NORTH);
        enemySelection.add(wrapper, BorderLayout.CENTER);

        mainPanel.add(enemySelection, "enemySelect");
    }


    private void createEnemyButtons(JPanel panel, JButton button){

        JPanel enemyPanel = new JPanel(new BorderLayout());
        enemyPanel.setPreferredSize(new Dimension(300, 150));
        enemyPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 8));

        button.setBorderPainted(false);
        button.setContentAreaFilled(false);

        JPanel buttonWrapper = new JPanel(new GridBagLayout());
        buttonWrapper.setOpaque(false);
        buttonWrapper.add(button);    

        enemyPanel.add(buttonWrapper, BorderLayout.CENTER);


        JPanel border = new JPanel(new FlowLayout());
        border.setOpaque(false);
        border.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        border.add(enemyPanel);

        panel.add(border);

    }


    private void createInfoSection(String typeOfInfo, JLabel info){

        JPanel typeWrapper = new JPanel(new BorderLayout());
        JLabel infoType = new JLabel(typeOfInfo);

        infoType.setFont(smallText);
        info.setFont(smallText);

        typeWrapper.setPreferredSize(new Dimension(400, 30));
        typeWrapper.setMaximumSize(new Dimension(400, 30));
        typeWrapper.setMinimumSize(new Dimension(400, 30));

        typeWrapper.add(infoType, BorderLayout.WEST); 

        if(info.getText().length() == 0)
            info.setText("None");
        
        typeWrapper.add(info, BorderLayout.EAST);            
        enemyStatsPanel.add(typeWrapper); 

        
    }

    /**
     * Displays an enemy's relevant data
     * @param name name of the enemy
     * @param type type of the enemy
     * @param image image of the enemy
     * @param hitPoints base HP of the enemy
     * @param attack base atk of the enemy
     * @param defense base def of the enemy
     * @param speed base spd of the enemy
     * @param weapon equipped weapon of the enemy if it has one
     * @param consumable equipped consumable of the enemy if it has one
     */
    public void updateView(String name, String type, ImageIcon image, int hitPoints, int attack, int defense, int speed, 
            String weapon, String consumable){

        enemyName.setText(name);
        enemyType.setText(type);
        Image scaledImage = image.getImage().getScaledInstance(275, 275, Image.SCALE_SMOOTH);
        enemyImage.setIcon(new ImageIcon(scaledImage));

        enemyHitPoints.setText("" + hitPoints);
        enemyAttack.setText("" + attack);
        enemyDefense.setText("" + defense);
        enemySpeed.setText("" + speed);

        if(weapon != null)
            enemyWeapon.setText(weapon);

        if(consumable != null)
            enemyConsumable.setText(consumable);

        enemyStatsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 8));

        if(!firstUpdate){

            firstUpdate = true;

            enemyStatsPanel.add(Box.createVerticalStrut(20));
            enemyStatsPanel.add(enemyName);
            enemyStatsPanel.add(Box.createVerticalStrut(20));
            enemyStatsPanel.add(enemyImage);        
            enemyStatsPanel.add(Box.createVerticalStrut(20));
            createInfoSection("TYPE:", enemyType);
            createInfoSection("HITPOINTS:", enemyHitPoints);
            createInfoSection("ATTACK:", enemyAttack);
            createInfoSection("DEFENSE:", enemyDefense);
            createInfoSection("SPEED:", enemySpeed);
            createInfoSection("WEAPON:", enemyWeapon);
            createInfoSection("CONSUMABLE:", enemyConsumable);
            enemyStatsPanel.add(Box.createVerticalGlue());

        }

    }

}

