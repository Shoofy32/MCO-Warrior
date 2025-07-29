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
 * Class file for managing the armor selection panel
 * @author Martin
 */
public class GUIArmorSelectPanel extends JFrame{

    private boolean firstUpdate = false;

    private JPanel weaponSelection;
    private JPanel armorStatsPanel;
    private JLabel weaponName;
    private JLabel weaponImage;
    private JLabel weaponType;
    private JLabel weaponAttack;
    private JLabel weaponSpeedPenalty;

    //Wrappers
    JPanel nameWrapper;

    //For use
    private Border padding = BorderFactory.createEmptyBorder(15, 15, 15, 15);
    private Font largeText = new Font("SansSerif", Font.BOLD , 40);    
    private Font smallText = new Font("SansSerif", Font.BOLD , 25);

    /**
     * Constructor for the GUIArmorSelectPanel class
     */
    public GUIArmorSelectPanel(){

        armorStatsPanel = new JPanel();
        armorStatsPanel.setLayout(new BoxLayout(armorStatsPanel, BoxLayout.Y_AXIS));
        armorStatsPanel.setPreferredSize(new Dimension(700, 700));


        weaponName = new JLabel();
        weaponImage = new JLabel();
        weaponType = new JLabel();
        weaponAttack = new JLabel();
        weaponSpeedPenalty = new JLabel();

        weaponName.setFont(largeText);

        weaponName.setAlignmentX(CENTER_ALIGNMENT);
        weaponImage.setAlignmentX(CENTER_ALIGNMENT);

    }

    /**
     * Getter to retrieve the armor stats panel
     * @return armor stats panel
     */
    public JPanel getArmorStatsPanel(){

        return armorStatsPanel;

    }

    /**
     * Initializes the armor selection
     * @param buttons Array of buttons
     * @param buttonWrapper JPanel to wrap around buttons
     * @param wrapper stores the stats panel
     * @param mainPanel Panel to display selection
     */
    public void initArmorSelection(JButton[] buttons, JPanel buttonWrapper, JPanel wrapper, JPanel mainPanel){

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 8));


        JPanel armorChoices = new JPanel();
        armorChoices.setLayout(new BoxLayout(armorChoices, BoxLayout.Y_AXIS));
        armorChoices.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10)); 

        JLabel weaponSelectLabel = new JLabel("Armor Selection");
        weaponSelectLabel.setFont(largeText);

        JPanel textWrapper = new JPanel();
        textWrapper.setLayout(new BoxLayout(textWrapper, BoxLayout.Y_AXIS));
        textWrapper.setPreferredSize(new Dimension(450, 100));  
        textWrapper.setMaximumSize(new Dimension(450, 100));   
        textWrapper.setMinimumSize(new Dimension(450, 100)); 
        textWrapper.setAlignmentX(RIGHT_ALIGNMENT);
        textWrapper.setAlignmentY(CENTER_ALIGNMENT);  
        textWrapper.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 15));
        textWrapper.add(weaponSelectLabel);

        topPanel.add(buttonWrapper, BorderLayout.WEST);
        topPanel.add(textWrapper, BorderLayout.EAST);

        for(int i = 0; i < buttons.length; i++)
            createArmorButtons(armorChoices, buttons[i]);

        JScrollPane vertScroll = new JScrollPane(armorChoices);
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



        weaponSelection = new JPanel(new BorderLayout());
        weaponSelection.add(westWrapper, BorderLayout.WEST);
        weaponSelection.add(topPanel, BorderLayout.NORTH);
        weaponSelection.add(wrapper, BorderLayout.CENTER);

        mainPanel.add(weaponSelection, "armorSelect");
    }


    private void createArmorButtons(JPanel panel, JButton button){

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


    private void createInfoSection(String typeOfInfo, JLabel info){

        JPanel typeWrapper = new JPanel(new BorderLayout());
        JLabel infoType = new JLabel(typeOfInfo);

        infoType.setFont(smallText);
        info.setFont(smallText);

        typeWrapper.setPreferredSize(new Dimension(400, 40));
        typeWrapper.setMaximumSize(new Dimension(400, 40));
        typeWrapper.setMinimumSize(new Dimension(400, 40));

        typeWrapper.add(infoType, BorderLayout.WEST); 
        typeWrapper.add(info, BorderLayout.EAST);

        armorStatsPanel.add(typeWrapper);  
     

    }

    /**
     * Displays an armor's relevant data
     * @param name name of the armor
     * @param type type of the armor
     * @param image image of the armor
     * @param defense def value of the armor
     * @param speedPenalty speed penalty of the armor
     */
    public void updateView(String name, String type, ImageIcon image, int defense, int speedPenalty){

        weaponName.setText(name);
        weaponType.setText(type);
        Image scaledImage = image.getImage().getScaledInstance(275, 275, Image.SCALE_SMOOTH);
        weaponImage.setIcon(new ImageIcon(scaledImage));

        weaponAttack.setText("" + defense);
        weaponSpeedPenalty.setText("" + -speedPenalty);

        armorStatsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 8));

        if(!firstUpdate){

            firstUpdate = true;

            armorStatsPanel.add(Box.createVerticalStrut(20));
            armorStatsPanel.add(weaponName);
            armorStatsPanel.add(Box.createVerticalStrut(20));
            armorStatsPanel.add(weaponImage);        
            armorStatsPanel.add(Box.createVerticalStrut(20));
            createInfoSection("TYPE:", weaponType);
            createInfoSection("DEFENSE:", weaponAttack);
            createInfoSection("SPEED PENALTY:", weaponSpeedPenalty);
            armorStatsPanel.add(Box.createVerticalGlue());

        }

    }

}
