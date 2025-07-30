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
 * Class file for managing the consumable selection panel
 * @author Martin
 */
public class GUIConsumableSelectPanel extends JPanel{

    private boolean firstUpdate = false;

    private JPanel consumableSelection;
    private JPanel consumableStatsPanel;
    private JLabel consumableName;
    private JLabel consumableImage;
    private JLabel consumableType;
    private JLabel consumableMaxCharge;
    private JTextArea consumableHolderDesc;
    private JTextArea consumableTargetDesc;

    //Wrappers
    JPanel nameWrapper;

    //For use
    private Border padding = BorderFactory.createEmptyBorder(15, 15, 15, 15);

    /**
     * Constructor for the GUIConsumableSelectPanel class
     */
    public GUIConsumableSelectPanel(){

        consumableStatsPanel = new JPanel();
        consumableStatsPanel.setLayout(new BoxLayout(consumableStatsPanel, BoxLayout.Y_AXIS));
        consumableStatsPanel.setPreferredSize(new Dimension(700, 700));


        consumableName = new JLabel();
        consumableImage = new JLabel();
        consumableType = new JLabel();
        consumableMaxCharge = new JLabel();
        consumableHolderDesc = new JTextArea();
        consumableTargetDesc = new JTextArea();

        consumableName.setFont(new Font("FFF Forward", Font.PLAIN, 40));

        consumableName.setAlignmentX(CENTER_ALIGNMENT);
        consumableImage.setAlignmentX(CENTER_ALIGNMENT);

    }

    /**
     * Getter to retrieve the consumable stats panel
     * @return consumable stats panel
     */
    public JPanel getConsumableStatsPanel(){

        return consumableStatsPanel;

    }

    /**
     * Initializes the consumable selection
     * @param buttons Array of buttons
     * @param buttonWrapper JPanel to wrap around buttons
     * @param wrapper stores the stats panel
     * @param mainPanel Panel to display selection
     */
    public void initConsumableSelection(JButton[] buttons, JPanel buttonWrapper, JPanel wrapper, JPanel mainPanel){

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 8));


        JPanel consumableChoices = new JPanel();
        consumableChoices.setLayout(new BoxLayout(consumableChoices, BoxLayout.Y_AXIS));
        consumableChoices.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10)); 

        JLabel weaponSelectLabel = new JLabel("Consumable Selection");
        weaponSelectLabel.setFont(new Font("FFF Forward", Font.PLAIN, 40));

        JPanel textWrapper = new JPanel();
        textWrapper.setLayout(new BoxLayout(textWrapper, BoxLayout.Y_AXIS));
        textWrapper.setPreferredSize(new Dimension(600, 100));  
        textWrapper.setMaximumSize(new Dimension(600, 100));   
        textWrapper.setMinimumSize(new Dimension(600, 100)); 
        textWrapper.setAlignmentX(RIGHT_ALIGNMENT);
        textWrapper.setAlignmentY(CENTER_ALIGNMENT);  
        textWrapper.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 15));
        textWrapper.add(Box.createVerticalGlue());
        textWrapper.add(weaponSelectLabel);
        textWrapper.add(Box.createVerticalGlue());

        topPanel.add(buttonWrapper, BorderLayout.WEST);
        topPanel.add(textWrapper, BorderLayout.EAST);

        for(int i = 0; i < buttons.length; i++)
            createConsumableButtons(consumableChoices, buttons[i]);

        JScrollPane vertScroll = new JScrollPane(consumableChoices);
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



        consumableSelection = new JPanel(new BorderLayout());
        consumableSelection.add(westWrapper, BorderLayout.WEST);
        consumableSelection.add(topPanel, BorderLayout.NORTH);
        consumableSelection.add(wrapper, BorderLayout.CENTER);

        mainPanel.add(consumableSelection, "consumableSelect");
    }

    private void createConsumableButtons(JPanel panel, JButton button){

        JPanel consumablePanel = new JPanel(new BorderLayout());
        consumablePanel.setPreferredSize(new Dimension(300, 250));
        consumablePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 8));

        button.putClientProperty("panel", consumablePanel);

        button.setBorderPainted(false);
        button.setContentAreaFilled(false);

        JPanel buttonWrapper = new JPanel(new GridBagLayout());
        buttonWrapper.setOpaque(false);
        buttonWrapper.add(button);    

        consumablePanel.add(buttonWrapper, BorderLayout.CENTER);


        JPanel border = new JPanel(new FlowLayout());
        border.setOpaque(false);
        border.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        border.add(consumablePanel);

        panel.add(border);


    }

    private void createInfoSection(String typeOfInfo, JLabel info){

        JPanel typeWrapper = new JPanel(new BorderLayout());
        JLabel infoType = new JLabel(typeOfInfo);

        infoType.setFont(new Font("Medodica Regular", Font.PLAIN, 40));
        info.setFont(new Font("Medodica Regular", Font.PLAIN, 40));

        typeWrapper.setPreferredSize(new Dimension(400, 40));
        typeWrapper.setMaximumSize(new Dimension(400, 40));
        typeWrapper.setMinimumSize(new Dimension(400, 40));

        typeWrapper.add(infoType, BorderLayout.WEST); 
        typeWrapper.add(info, BorderLayout.EAST);

        consumableStatsPanel.add(typeWrapper);  
     

    }

    private void createInfoSection(String typeOfInfo, JTextArea info){

        JPanel typeWrapper = new JPanel();
        typeWrapper.setLayout(new BoxLayout(typeWrapper, BoxLayout.Y_AXIS));

        info.setFont(new Font("Medodica Regular", Font.PLAIN, 40));
        info.setLineWrap(true);
        info.setWrapStyleWord(true);
        info.setOpaque(false);
        info.setEditable(false);
        info.setFocusable(false);

        if(typeOfInfo.equals("HOLDER"))
            info.setForeground(Color.BLUE);
        else if(typeOfInfo.equals("TARGET"))
            info.setForeground(Color.RED);

        typeWrapper.add(info);

        consumableStatsPanel.add(typeWrapper);

    }

    /**
     * Displays a consumable's relevant data
     * @param name name of the consumable
     * @param type type of the consumable
     * @param image image of the consumable
     * @param maxCharge charges of the consumable
     * @param holderDescription description of consumable's effects on the holder
     * @param targetDescription description of consumable's effects on the target
     */
    public void updateView(String name, String type, ImageIcon image, int maxCharge, String holderDescription, String targetDescription){

        consumableName.setText(name);
        consumableType.setText(type);
        Image scaledImage = image.getImage().getScaledInstance(275, 275, Image.SCALE_SMOOTH);
        consumableImage.setIcon(new ImageIcon(scaledImage));

        consumableMaxCharge.setText("" + maxCharge);

        consumableHolderDesc.setText(holderDescription);
        if(targetDescription != null)
            consumableTargetDesc.setText(targetDescription);

        consumableStatsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 8));

        if(!firstUpdate){

            firstUpdate = true;

            consumableStatsPanel.add(Box.createVerticalStrut(20));
            consumableStatsPanel.add(consumableName);
            consumableStatsPanel.add(Box.createVerticalStrut(20));
            consumableStatsPanel.add(consumableImage);        
            consumableStatsPanel.add(Box.createVerticalStrut(20));
            createInfoSection("TYPE:", consumableType);
            createInfoSection("MAX CHARGES:", consumableMaxCharge);
            consumableStatsPanel.add(Box.createVerticalGlue());
            createInfoSection("HOLDER", consumableHolderDesc);
            createInfoSection("TARGET", consumableTargetDesc);

        }

        
    }

}
