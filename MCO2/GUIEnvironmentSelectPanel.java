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
 * Class file for managing the environment selection panel
 * @author Martin
 */
public class GUIEnvironmentSelectPanel extends JFrame{

    private boolean firstUpdate = false;

    private JPanel environmentSelection;
    private JPanel environmentStatsPanel;
    private JLabel environmentName;
    private JLabel environmentImage;
    private JTextArea environmentPlayerDescription;
    private JTextArea environmentEnemyDescription;

    JPanel nameWrapper;

    //For use
    private Border padding = BorderFactory.createEmptyBorder(15, 15, 15, 15);
    private Font largeText = new Font("SansSerif", Font.BOLD , 40);    
    private Font smallText = new Font("SansSerif", Font.BOLD , 25);

    /**
     * Constructor for the GUIEnvironmentSelectPanel class
     */
    public GUIEnvironmentSelectPanel(){

        environmentStatsPanel = new JPanel();
        environmentStatsPanel.setLayout(new BoxLayout(environmentStatsPanel, BoxLayout.Y_AXIS));
        environmentStatsPanel.setPreferredSize(new Dimension(700, 700));


        environmentName = new JLabel();
        environmentImage = new JLabel();
        environmentPlayerDescription = new JTextArea();
        environmentEnemyDescription = new JTextArea();

        environmentName.setFont(largeText);

        environmentName.setAlignmentX(CENTER_ALIGNMENT);
        environmentImage.setAlignmentX(CENTER_ALIGNMENT);

    }

    /**
     * Getter to retrieve the environment stats panel
     * @return environment stats panel
     */
    public JPanel getEnvironmentStatsPanel(){

        return environmentStatsPanel;

    }

    /**
     * Initializes the consumable selection
     * @param buttons Array of buttons
     * @param buttonWrapper JPanel to wrap around buttons
     * @param wrapper stores the stats panel
     * @param mainPanel Panel to display selection
     */
    public void initEnvironmentSelection(JButton[] buttons, JPanel buttonWrapper, JPanel wrapper, JPanel mainPanel){

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 8));

        JPanel environmentChoices = new JPanel();
        environmentChoices.setLayout(new BoxLayout(environmentChoices, BoxLayout.Y_AXIS));
        environmentChoices.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10)); 

        JLabel environmentSelectLabel = new JLabel("Environment Selection");
        environmentSelectLabel.setFont(largeText);

        JPanel textWrapper = new JPanel();
        textWrapper.setLayout(new BoxLayout(textWrapper, BoxLayout.Y_AXIS));
        textWrapper.setPreferredSize(new Dimension(450, 100));  
        textWrapper.setMaximumSize(new Dimension(450, 100));   
        textWrapper.setMinimumSize(new Dimension(450, 100)); 
        textWrapper.setAlignmentX(RIGHT_ALIGNMENT);
        textWrapper.setAlignmentY(CENTER_ALIGNMENT);  
        textWrapper.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 15));
        textWrapper.add(environmentSelectLabel);

        topPanel.add(buttonWrapper, BorderLayout.WEST);
        topPanel.add(textWrapper, BorderLayout.EAST);

        for(int i = 0; i < buttons.length; i++)
            createEnvironmentButtons(environmentChoices, buttons[i]);

        JScrollPane vertScroll = new JScrollPane(environmentChoices);
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

        environmentSelection = new JPanel(new BorderLayout());
        environmentSelection.add(westWrapper, BorderLayout.WEST);
        environmentSelection.add(topPanel, BorderLayout.NORTH);
        environmentSelection.add(wrapper, BorderLayout.CENTER);

        mainPanel.add(environmentSelection, "environmentSelect");
    }


    private void createEnvironmentButtons(JPanel panel, JButton button){

        JPanel weaponPanel = new JPanel(new BorderLayout());
        weaponPanel.setPreferredSize(new Dimension(300, 150));
        weaponPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 8));

        button.setBorderPainted(false);
        button.setContentAreaFilled(false);

        JPanel buttonWrapper = new JPanel(new GridBagLayout());
        buttonWrapper.setOpaque(false);
        buttonWrapper.add(button);    

        weaponPanel.add(buttonWrapper, BorderLayout.CENTER);


        JPanel border = new JPanel(new FlowLayout());
        border.setOpaque(false);
        border.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        border.add(weaponPanel);

        panel.add(border);


    }


    private void createInfoSection(String typeOfInfo, JTextArea info){

        JPanel typeWrapper = new JPanel();
        typeWrapper.setLayout(new BoxLayout(typeWrapper, BoxLayout.Y_AXIS));

        info.setFont(smallText);
        info.setLineWrap(true);
        info.setWrapStyleWord(true);
        info.setOpaque(false);
        info.setEditable(false);
        info.setFocusable(false);

        if(typeOfInfo.equals("PLAYER"))
            info.setForeground(Color.BLUE);
        else if(typeOfInfo.equals("ENEMY"))
            info.setForeground(Color.RED);

        typeWrapper.add(info);

        environmentStatsPanel.add(typeWrapper);

    }

    /**
     * Displays an environment's relevant data
     * @param name name of the environment
     * @param image image of the environment
     * @param playerDescription environment's effect on the player
     * @param enemyDescription environment's effect on the enemy
     */
    public void updateView(String name, ImageIcon image, String playerDescription, String enemyDescription){

        environmentName.setText(name);
        Image scaledImage = image.getImage().getScaledInstance(350, 275, Image.SCALE_SMOOTH);
        environmentImage.setIcon(new ImageIcon(scaledImage));

        environmentPlayerDescription.setText(playerDescription);
        environmentEnemyDescription.setText(enemyDescription);

        environmentStatsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 8));

        if(!firstUpdate){

            firstUpdate = true;

            environmentStatsPanel.add(Box.createVerticalStrut(20));
            environmentStatsPanel.add(environmentName);
            environmentStatsPanel.add(Box.createVerticalStrut(20));
            environmentStatsPanel.add(environmentImage);        
            environmentStatsPanel.add(Box.createVerticalStrut(20));
            createInfoSection("PLAYER", environmentPlayerDescription);
            createInfoSection("ENEMY", environmentEnemyDescription);
            environmentStatsPanel.add(Box.createVerticalGlue());

        }

    }

}

