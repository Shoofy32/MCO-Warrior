import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class to display currently the currently selected armor, weapon, consumable, and enemy before a battle starts
 */
public class GUICurrentChoices extends JFrame{
    
    private boolean firstUpdate = false;

    private JPanel topPanel;
    private JPanel leftPanel;
    private JPanel centerPanel;
    private JPanel rightPanel;
    private JPanel displayPanel;
    private JPanel textWrapper;
    private JLabel finalChoicesLabel;

    private JLabel playerImage;
    private JLabel weaponImage;
    private JLabel armorImage;
    private JLabel consumableImage;
    private JLabel enemyImage;

    private JLabel playerName;
    private JLabel weaponName;
    private JLabel armorName;
    private JLabel consumableName;
    private JLabel enemyName;
    private JLabel environmentName;

    //For use
    private Font largeText = new Font("SansSerif", Font.BOLD , 40);    
    private Font smallText = new Font("SansSerif", Font.BOLD , 25);

    /**
     * Constructor for the GUICurrentChoices class
     */
    public GUICurrentChoices(){

        topPanel = new JPanel();
        textWrapper = new JPanel();
        finalChoicesLabel = new JLabel();

        playerName = new JLabel();
        playerName.setFont(smallText);
        playerName.setForeground(Color.WHITE);

        weaponName = new JLabel();
        weaponName.setFont(smallText);
        weaponName.setForeground(Color.WHITE);

        armorName = new JLabel();
        armorName.setFont(smallText);
        armorName.setForeground(Color.WHITE);

        consumableName = new JLabel();
        consumableName.setFont(smallText);
        consumableName.setForeground(Color.WHITE);

        enemyName = new JLabel();
        enemyName.setFont(smallText);
        enemyName.setForeground(Color.WHITE);

        environmentName = new JLabel();
        environmentName.setFont(largeText);
        environmentName.setForeground(Color.WHITE);

    }

    /**
     * Initializes the "Start" and "Back" buttons
     * "Start" button will proceed with the battle using the currently selected options
     * "Back" button will redo the selection process starting back to weapon selection
     * @param buttonWrapper button wrappers for "Start" and "Back"
     * @param mainPanel panel to display on
     */
    public void initSelectedChoices(JPanel buttonWrapper, JPanel mainPanel){

        playerImage = new JLabel();
        weaponImage = new JLabel();
        armorImage = new JLabel();
        consumableImage = new JLabel();
        enemyImage = new JLabel();
        
        //TOP PART
        topPanel.setLayout(new BorderLayout());
        topPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 8));

        finalChoicesLabel.setText("Final Choices");
        finalChoicesLabel.setFont(largeText);

        textWrapper.setLayout(new BoxLayout(textWrapper, BoxLayout.Y_AXIS));
        textWrapper.setPreferredSize(new Dimension(450, 100));  
        textWrapper.setMaximumSize(new Dimension(450, 100));   
        textWrapper.setMinimumSize(new Dimension(450, 100)); 
        textWrapper.setAlignmentX(RIGHT_ALIGNMENT);
        textWrapper.setAlignmentY(CENTER_ALIGNMENT);  
        textWrapper.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 15));
        textWrapper.add(finalChoicesLabel);

        topPanel.add(buttonWrapper, BorderLayout.WEST);
        topPanel.add(textWrapper, BorderLayout.EAST);

        displayPanel = new JPanel(new BorderLayout());
        displayPanel.add(topPanel, BorderLayout.NORTH);

        mainPanel.add(displayPanel, "currentChoices");

    }

    /**
     * Displays the selected weapon, armor, consumable, environment, and enemy
     * @param names Array of names of the selected options
     * @param images Array of images of the selected options
     */
    public void updateView(String[] names, ImageIcon[] images){

        Image scaledImage;

        playerName.setText(names[0]);
        scaledImage = images[0].getImage().getScaledInstance(400, 500, Image.SCALE_SMOOTH);
        playerImage.setIcon(new ImageIcon(scaledImage));
        playerName.setAlignmentX(CENTER_ALIGNMENT);
        playerImage.setAlignmentX(CENTER_ALIGNMENT);
        playerImage.setBorder(BorderFactory.createLineBorder(Color.BLACK, 8));

        weaponName.setText(names[1]);
        scaledImage = images[1].getImage().getScaledInstance(175, 150, Image.SCALE_SMOOTH);
        weaponImage.setIcon(new ImageIcon(scaledImage));
        weaponName.setAlignmentX(CENTER_ALIGNMENT);
        weaponImage.setAlignmentX(CENTER_ALIGNMENT);
        weaponImage.setBorder(BorderFactory.createLineBorder(Color.BLACK, 8));


        armorName.setText(names[2]);
        scaledImage = images[2].getImage().getScaledInstance(175, 150, Image.SCALE_SMOOTH);
        armorImage.setIcon(new ImageIcon(scaledImage));
        armorName.setAlignmentX(CENTER_ALIGNMENT);
        armorImage.setAlignmentX(CENTER_ALIGNMENT);
        armorImage.setBorder(BorderFactory.createLineBorder(Color.BLACK, 8));


        consumableName.setText(names[3]);
        scaledImage = images[3].getImage().getScaledInstance(175, 150, Image.SCALE_SMOOTH);
        consumableImage.setIcon(new ImageIcon(scaledImage));
        consumableName.setAlignmentX(CENTER_ALIGNMENT);
        consumableImage.setAlignmentX(CENTER_ALIGNMENT);
        consumableImage.setBorder(BorderFactory.createLineBorder(Color.BLACK, 8));


        enemyName.setText(names[4]);
        scaledImage = images[4].getImage().getScaledInstance(400, 500, Image.SCALE_SMOOTH);
        enemyImage.setIcon(new ImageIcon(scaledImage));
        enemyName.setAlignmentX(CENTER_ALIGNMENT);
        enemyImage.setAlignmentX(CENTER_ALIGNMENT);
        enemyImage.setBorder(BorderFactory.createLineBorder(Color.BLACK, 8));

        environmentName.setText(names[5]);
        environmentName.setAlignmentX(CENTER_ALIGNMENT);

        if(!firstUpdate){

            firstUpdate = true;

            //LEFT PART
            leftPanel = new JPanel();
            leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
            leftPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 100));
            leftPanel.setOpaque(false);
            leftPanel.add(playerName);
            leftPanel.add(playerImage);

            //CENTER PART
            centerPanel = new JPanel();
            centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
            centerPanel.setOpaque(false);
            centerPanel.add(environmentName);
            centerPanel.add(Box.createVerticalStrut(20));
            centerPanel.add(weaponName);
            centerPanel.add(weaponImage);
            centerPanel.add(armorName);
            centerPanel.add(armorImage);
            centerPanel.add(consumableName);
            centerPanel.add(consumableImage);
            
            //RIGHT PART
            rightPanel = new JPanel();
            rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
            rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 50));
            rightPanel.setOpaque(false);
            rightPanel.add(enemyName);
            rightPanel.add(enemyImage);

            //COMBINE ALL TO ONE PANEL
            JPanel showSelectionPanel = new GUIBackgroundPanel(images[5].getImage());
            showSelectionPanel.setLayout(new BorderLayout());
            showSelectionPanel.add(leftPanel, BorderLayout.WEST);
            showSelectionPanel.add(centerPanel, BorderLayout.CENTER);
            showSelectionPanel.add(rightPanel, BorderLayout.EAST);
            displayPanel.add(showSelectionPanel, BorderLayout.CENTER);
 
        }

    }

}
