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
 * Class file for managing the weapon selection panel
 * @author Martin
 */
public class GUIWeaponSelectPanel extends JPanel{

    private boolean firstUpdate = false;

    private JPanel weaponSelection;
    private JPanel weaponStatsPanel;
    private JLabel weaponName;
    private JLabel weaponImage;
    private JLabel weaponType;
    private JLabel weaponAttack;
    private JLabel weaponSpeedPenalty;
    private JTextArea weaponAbility;
    private JTextArea weaponSkill;

    //Wrappers
    JPanel nameWrapper;

    //For use
    private Border padding = BorderFactory.createEmptyBorder(15, 15, 15, 15);
    private Font largeText = new Font("SansSerif", Font.BOLD , 40);    
    private Font smallText = new Font("SansSerif", Font.BOLD , 25);

    /**
     * Constructor for the GUIWeaponSelectPanel class
     */
    public GUIWeaponSelectPanel(){

        weaponStatsPanel = new JPanel();
        weaponStatsPanel.setLayout(new BoxLayout(weaponStatsPanel, BoxLayout.Y_AXIS));
        weaponStatsPanel.setPreferredSize(new Dimension(700, 700));


        weaponName = new JLabel();
        weaponImage = new JLabel();
        weaponType = new JLabel();
        weaponAttack = new JLabel();
        weaponSpeedPenalty = new JLabel();
        weaponAbility = new JTextArea();
        weaponSkill = new JTextArea();

        weaponName.setFont(largeText);

        weaponName.setAlignmentX(CENTER_ALIGNMENT);
        weaponImage.setAlignmentX(CENTER_ALIGNMENT);

    }

    /**
     * Getter to retrieve the weapon stats panel
     * @return weapon stats panel
     */
    public JPanel getWeaponStatsPanel(){

        return weaponStatsPanel;

    }

    /**
     * Initializes the weapon selection
     * @param buttons Array of buttons
     * @param buttonWrapper JPanel to wrap around buttons
     * @param wrapper stores the stats panel
     * @param mainPanel Panel to display selection
     */
    public void initWeaponSelection(JButton[] buttons, JPanel buttonWrapper, JPanel wrapper, JPanel mainPanel){

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 8));


        JPanel weaponChoices = new JPanel();
        weaponChoices.setLayout(new BoxLayout(weaponChoices, BoxLayout.Y_AXIS));
        weaponChoices.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

        JLabel weaponSelectLabel = new JLabel("Weapon Selection");
        weaponSelectLabel.setFont(largeText);

        JPanel textWrapper = new JPanel();
        textWrapper.setLayout(new BoxLayout(textWrapper, BoxLayout.Y_AXIS));
        textWrapper.setPreferredSize(new Dimension(450, 150));
        textWrapper.setMaximumSize(new Dimension(450, 150));
        textWrapper.setMinimumSize(new Dimension(450, 150));
        textWrapper.setAlignmentX(RIGHT_ALIGNMENT);
        textWrapper.setAlignmentY(CENTER_ALIGNMENT);
        textWrapper.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 15));
        textWrapper.add(weaponSelectLabel);

        topPanel.add(buttonWrapper, BorderLayout.WEST);
        topPanel.add(textWrapper, BorderLayout.EAST);

        for(int i = 0; i < buttons.length; i++)
            createWeaponButtons(weaponChoices, buttons[i]);

        JScrollPane vertScroll = new JScrollPane(weaponChoices);
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

        mainPanel.add(weaponSelection, "weaponSelect");
    }

    private void createWeaponButtons(JPanel panel, JButton button){

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

        weaponStatsPanel.add(typeWrapper);  
     

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

        if(typeOfInfo.equals("ABILITY"))
            info.setForeground(Color.BLUE);
        else if(typeOfInfo.equals("SKILL"))
            info.setForeground(Color.RED);

        typeWrapper.add(info);

        weaponStatsPanel.add(typeWrapper);

        

    }

    /**
     * Displays a weapon's relevant data
     * @param name name of the weapon
     * @param type type of the weapon
     * @param image image of the weapon
     * @param damage atk value of the weapon
     * @param speedPenalty speed penalty of the weapon
     * @param ability ability of the weapon
     * @param skill skill of the weapon if it has one
     */
    public void updateView(String name, String type, ImageIcon image, int damage, int speedPenalty, String ability, String skill){

        weaponName.setText(name);
        weaponType.setText(type);
        Image scaledImage = image.getImage().getScaledInstance(275, 275, Image.SCALE_SMOOTH);
        weaponImage.setIcon(new ImageIcon(scaledImage));

        weaponAttack.setText("" + damage);
        weaponSpeedPenalty.setText("" + -speedPenalty);
        weaponAbility.setText(ability);

        if(skill != null)
            weaponSkill.setText(skill);
        else
            weaponSkill.setText(null);
        

        weaponStatsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 8));

        if(!firstUpdate){

            firstUpdate = true;

            weaponStatsPanel.add(Box.createVerticalStrut(20));
            weaponStatsPanel.add(weaponName);
            weaponStatsPanel.add(Box.createVerticalStrut(20));
            weaponStatsPanel.add(weaponImage);        
            weaponStatsPanel.add(Box.createVerticalStrut(20));
            createInfoSection("TYPE:", weaponType);
            createInfoSection("ATTACK:", weaponAttack);
            createInfoSection("SPEED PENALTY:", weaponSpeedPenalty);
            weaponStatsPanel.add(Box.createVerticalGlue());
            createInfoSection("ABILITY", weaponAbility);
            createInfoSection("SKILL", weaponSkill);

        }

    }

}
