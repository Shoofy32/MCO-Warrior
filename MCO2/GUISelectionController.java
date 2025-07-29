import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;

/**
 * Class file for the GUISelectionController class
 * <p>
 *     This class serves a similar purpose to the SelectionController but this time providing visuals for selection
 * </p>
 * @see SelectionController
 * @author Martin
 */
public class GUISelectionController implements ActionListener{
    
    //Viwer and controllers
    private GUIViewer guiView;
    private GUIEventController event;

    /*
     * Arraylists for classes that are instanced for the player to select their choice.
     * Due to the use of Arraylists, it allows for any number of choices to be added or removed for the player.
     */
    private static ArrayList<Weapon> weapons; //ArrayList for Weapon Class
    private static ArrayList<Armor> armors; //ArrayList for Armor Class
    private static ArrayList<Enemy> enemies; //ArrayList for Enemy Class
    private static ArrayList<Environment> environments; //ArrayList for Environment Class
    private static ArrayList<Consumable> consumables; //ArrayList for Consumable Class

    //Attributes that store the selected choices the player chose
    private Weapon selectedWeapon;
    private Armor selectedArmor;
    private Enemy selectedEnemy;
    private Consumable selectedConsumable; 
    private Environment selectedEnvironment; 

    //Logic for controller
    String previousButtonPressed = null; //Stores the commandName of the button click to perform double click validation
    boolean selectedChoice = false; //Checks if player has selected a choice

   //Constructor

    /**
     * Constructor method for the GUISelectionController
     * @param event GUIEventcontroller object to manage this event
     */
    public GUISelectionController(GUIEventController event){
        
        this.event = event;

        //ArrayLists runtime dependent on SelectionController
        weapons = new ArrayList<>();
        armors = new ArrayList<>();
        enemies = new ArrayList<>();
        environments = new ArrayList<>();
        consumables = new ArrayList<>();

        //Creates the main objects such as Weapons, Enemies, etc.
        initilizeSelections();

    }


    //Setters

    /**
     * Sets the GUIViewer to handle this class' display
     * @param guiView GUIViewer class
     * @see GUIViewer
     */
    public void setGUIView(GUIViewer guiView){

        this.guiView = guiView;
        
    }


    //Getters

    /**
     * Getter method to retrieve the weapon objects stored.
     * @return ArrayList of weapon objects
     */
    public ArrayList<Weapon> getWeapons(){

        return weapons;

    }

    /**
     * Getter method to retrieve the armor objects stored.
     * @return ArrayList of armor objects
     */
    public ArrayList<Armor> getArmors(){

        return armors;

    }

    /**
     * Getter method to retrieve the enemy objects stored.
     * @return ArrayList of enemy objects
     */
    public ArrayList<Enemy> getEnemies(){

        return enemies;

    }

    /**
     * Getter method to retrieve the environment objects stored.
     * @return ArrayList of environment objects
     */
    public ArrayList<Environment> getEnvironments(){

        return environments;

    }
    
    //Getters

    /**
     * Getter method to retrieve the consumable objects stored.
     * @return ArrayList of consumable objects
     */
    public ArrayList<Consumable> getConsumables(){

        return consumables;

    }

    //Controller methods

    //Methods to add selections to ArrayList
    private void initilizeSelections(){

        //Create Weapon instances
        Dagger dagger = new Dagger("Common Dagger", 20, 0, new ImageIcon("Assets/Weapons/Dagger_Weapon.png"));
        Sword sword = new Sword("Common Sword", 30, 10, new ImageIcon("Assets/Weapons/Sword_Weapon.png"));
        BattleAxe battleAxe = new BattleAxe("Common Axe", 40, 20, new ImageIcon("Assets/Weapons/BattleAxe_Weapon.png"));
        EnchantedWeapon busterSword = new EnchantedWeapon("Buster Sword", "Greatsword", 30, 20, new ImageIcon("Assets/Weapons/BusterSword_Weapon.png"), "OmniSlash");
        EnchantedWeapon katana = new EnchantedWeapon("Frayed Blade", "Katana", 35, 10, new ImageIcon("Assets/Weapons/FrayedBlade_Weapon.png"), "Parry");
        EnchantedWeapon brassKnuckles = new EnchantedWeapon("Brass Knuckles", "Fists", 20, 1, new ImageIcon("Assets/Weapons/BrassKnucles_Weapon.png"), "AdrenalineRush");
        EnchantedWeapon pureNail = new EnchantedWeapon("Pure Nail", "Sword", 35, 5, new ImageIcon("Assets/Weapons/PureNail_Weapon.png"), "DreamSlash");


        //Create Armor instances
        Armor armor1 = new Armor("Leather Armor", "Light", new ImageIcon("Assets/Armor/Leather_Armor.png"), 20, 5);
        Armor armor2 = new Armor("Chainmail Armor", "Medium", new ImageIcon("Assets/Armor/Chaimail_Armor.png"), 30, 15);
        Armor armor3 = new Armor("Iron Armor", "Heavy", new ImageIcon("Assets/Armor/Iron_Armor.png"), 40, 25);
        Armor armor4 = new Armor("Rusted Steel Armor", "Medium", new ImageIcon("Assets/Armor/RustedSteel_Armor.png"), 35, 10);
        Armor armor5 = new Armor("Frost Iron Armor", "Heavy", new ImageIcon("Assets/Armor/FrostIron_armor.png"), 50, 30);
        
        //Create Enemy instances
        Thief bandit = new Thief("Bandit", "Thief", new ImageIcon("Assets/Enemy/Enemy_Temp.png"), 150, 20, 20, 40);
        Viking haldor = new Viking("Haldor", "Viking", new ImageIcon("Assets/Enemy/Enemy_Temp.png"), 250, 30, 30, 30);
        Minotaur taur = new Minotaur("Taur", "Minotaur", new ImageIcon("Assets/Enemy/Enemy_Temp.png"), 350, 40, 40, 20);

        Unique_Enemy isshin = new Unique_Enemy("Isshin", "Samurai", new ImageIcon("Assets/Enemy/Enemy_Temp.png"), 200, 0, 20, 50, "AAFU", 
        new EnchantedWeapon("Frayed Blade", "Katana", 35, 10, new ImageIcon("Assets/Weapons/Weapon_Temp.png"),  "Parry"),
        new Bomb("Reckless Gamble", new ImageIcon("Assets/Consumable/Consumable_Temp.png"), 1, new String[]{"Speed"}, new int[]{50}, new String[]{"Attack"}, new int[]{30}, 3));
        Unique_Enemy ornstein = new Unique_Enemy("Ornstein", "Knight", new ImageIcon("Assets/Enemy/Enemy_Temp.png"), 300, 0, 35, 30, "AUCF",
        new EnchantedWeapon("Buster Sword", "Greatsword", 30, 20, new ImageIcon("Assets/Weapons/Weapon_Temp.png"),  "OmniSlash"),
        new Buff("Rage Elixer", new ImageIcon("Assets/Consumable/Consumable_Temp.png"), 1, new String[]{"Attack", "Defense"}, new int[]{20, -10}, 3));
        Unique_Enemy hornet = new Unique_Enemy("Hornet", "Knight", new ImageIcon("Assets/Enemy/Enemy_Temp.png"), 250, 0, 10, 60, "CAAUF",
        new EnchantedWeapon("Pure Nail", "Sword", 35, 5, new ImageIcon("Assets/Weapons/Weapon_Temp.png"),  "DreamSlash"),
        new Healing("Estus Flask", new ImageIcon("Assets/Consumable/Consumable_Temp.png"), 2, 45));
        Unique_Enemy radiance = new Unique_Enemy("Radiance", "Deity", new ImageIcon("Assets/Enemy/Enemy_Temp.png"), 800, 70, 30, 25, "AAAAAAACA", null, null);


        //Create Environment instances
        Environment environment1 = new Environment("Arena", false, new ImageIcon("Assets/Environment/Environment_Temp.png"));
        Environment environment2 = new Environment("Swamp", true, new ImageIcon("Assets/Environment/Environment_Temp.png"), -1, "HitPoints", 1, "Attack");
        Environment environment3 = new Environment("Colosseum", true, new ImageIcon("Assets/Environment/Environment_Temp.png"), 1, "Attack", -1, "Defense");
        Environment environment4 = new Environment("Painted Ariamis", true, new ImageIcon("Assets/Environment/Environment_Temp.png"), -2, "Speed", -1, "Speed");
        Environment environment5 = new Environment("Blight Town", true, new ImageIcon("Assets/Environment/Environment_Temp.png"), -1, "Defense", -3, "HitPoints");
        Environment environment6 = new Environment("Fungal Wastes", true, new ImageIcon("Assets/Environment/Environment_Temp.png"), -10, "HitPoints", -10, "HitPoints");
        Environment environment7 = new Environment("Black Egg Temple", true, new ImageIcon("Assets/Environment/Environment_Temp.png"), -1, "Attack", 5, "HitPoints");

        //Bonus - Create Consumable instances
        Consumable estusFlask = new Healing("Estus Flask", new ImageIcon("Assets/Consumable/Consumable_Temp.png"), 2, 45);
        Consumable rageElixer = new Buff("Rage Elixer", new ImageIcon("Assets/Consumable/Consumable_Temp.png"), 1, new String[]{"Attack", "Defense"}, new int[]{20, -10}, 3);
        Consumable windstormFlask = new Bomb("Windstorm Flask", new ImageIcon("Assets/Consumable/Consumable_Temp.png"), 1, new String[]{"Speed"}, new int[]{25}, new String[]{"Speed"}, new int[]{-15}, 3);
        Consumable recklessGamble = new Bomb("Reckless Gamble", new ImageIcon("Assets/Consumable/Consumable_Temp.png"),1, new String[]{"Speed"}, new int[]{50}, new String[]{"Attack"}, new int[]{30}, 3);
        Consumable equivalentExchange = new Buff("Equivalent Exchange", new ImageIcon("Assets/Consumable/Consumable_Temp.png"), 1, new String[]{"Defense", "Speed"}, new int[]{20, -20}, 3);
        Consumable redPotion = new Bomb("Red Potion", new ImageIcon("Assets/Consumable/Consumable_Temp.png"), 1, new String[]{"Defense", "Speed", "Attack"}, new int[]{-10, -10, -10}, new String[]{"Defense", "Speed", "Attack"}, new int[]{10, 10, 10}, 10);

        //Store Weapon instances to Weapon ArrayList
        weapons.add(dagger);
        weapons.add(sword);
        weapons.add(battleAxe);
        weapons.add(busterSword);
        weapons.add(katana);
        weapons.add(brassKnuckles);
        weapons.add(pureNail);

        //Store Armor instances to Armor ArrayList
        armors.add(armor1);
        armors.add(armor2);
        armors.add(armor3);
        armors.add(armor4);
        armors.add(armor5);

        //Store Enemy instances to Enemy ArrayList
        enemies.add(bandit);
        enemies.add(haldor);
        enemies.add(taur);
        enemies.add(isshin);
        enemies.add(ornstein);
        enemies.add(hornet);
        enemies.add(radiance);

        //Store Environment instances to Environment ArrayList
        environments.add(environment1);
        environments.add(environment2);
        environments.add(environment3);
        environments.add(environment4);
        environments.add(environment5);
        environments.add(environment6);
        environments.add(environment7);

        //Store Consumable instances to Consumable ArrayList
        consumables.add(estusFlask);
        consumables.add(rageElixer);
        consumables.add(windstormFlask);
        consumables.add(recklessGamble);
        consumables.add(equivalentExchange);
        consumables.add(redPotion);

    }


    /**
     * Creates the selection buttons to be used in selection the choices.
     */
    public void createSelectionButtons(){

        //Create buttons for selecting None
        guiView.setSelectionActionListener(this, 0, "Weapon", "None", new ImageIcon("Assets/UI/None_Temp.png"));
        guiView.setSelectionActionListener(this, 0, "Armor", "None", new ImageIcon("Assets/UI/None_Temp.png"));
        guiView.setSelectionActionListener(this, 0, "Consumable", "None", new ImageIcon("Assets/UI/None_Temp.png"));

        /* Each for loop corresponds to a different type that the button will be tied to.
         * It will get the name and weapon of the object indexed at that array and make that object tied to the button.
         * Weapons, Armors, and Consumables start at index 1 because of the option of choosing none.
         */
        for(int i = 1; i <= weapons.size(); i++)
            guiView.setSelectionActionListener(this, i, "Weapon", weapons.get(i-1).getName(), 
                    weapons.get(i-1).getImage());

        for(int i = 1; i <= armors.size(); i++)
            guiView.setSelectionActionListener(this, i, "Armor", armors.get(i-1).getName(), 
                    armors.get(i-1).getImage());

        for(int i = 1; i <= consumables.size(); i++)
            guiView.setSelectionActionListener(this, i, "Consumable", consumables.get(i-1).getName(), 
                    consumables.get(i-1).getImage());    
                    
        for(int i = 0; i < enemies.size(); i++)
            guiView.setSelectionActionListener(this, i, "Enemy", enemies.get(i).getName(), 
                    enemies.get(i).getImage());

        for(int i = 0; i < environments.size(); i++)
            guiView.setSelectionActionListener(this, i, "Environment", environments.get(i).getName(), 
                    environments.get(i).getImage());  

    }


    /**
     * Starts the player selection.
     */
    public void startSelection(){

        guiView.initSelection("Weapon");
        guiView.showPanel("Select Weapon");

        //Sets the action command of the back and select buttons
        guiView.setActionCommand(this, guiView.getBackButton(), "Back");
        guiView.setActionCommand(this, guiView.getSelectButton(), "Select");      

    }


    /**
     * Updates the view of the clicked button depending on the type and index stored in the parameters
     * @param type type of selection to be displayed
     * @param index index of the object in their respective array
     */
    public void updateSelectionView(String type, int index){

        //Switch statement that checks the type stored in the String
        switch(type){

            case "Weapon":
                Weapon weaponTemp = weapons.get(index);
                String skillName;
    
                //Checks if the weapon in the index is an enchanted weapon and gets the description
                if(weaponTemp instanceof EnchantedWeapon)
                    skillName = ((EnchantedWeapon) weaponTemp).getWeaponSkill().getskillDescription();
                else
                    skillName = null;

                //Updates the weapon selection view
                guiView.updateWeaponSelectPanel(weaponTemp.getName(), weaponTemp.getType(), weaponTemp.getImage(), weaponTemp.getAttack(),
                        weaponTemp.getSpeedPenalty(), weaponTemp.getAbility().getAbilityDecsription(), skillName);
                break;

            case "Armor":

                Armor armorTemp = armors.get(index);

                //Updates the armor selection view
                guiView.updateArmorSelectPanel(armorTemp.getName(), armorTemp.getType(), armorTemp.getImage(), armorTemp.getDefense(),
                        armorTemp.getSpeedPenalty());
                break;

            case "Consumable":

                Consumable consumableTemp = consumables.get(index);

                //Updates the consumable selection view
                guiView.updateConsumableSelectPanel(consumableTemp.getName(), consumableTemp.getType(), consumableTemp.getImage(), consumableTemp.getMaxCharges(),
                        consumableTemp.getPlayerConsumableDescription(), consumableTemp.getEnemyConsumableDescription());
                break;

            case "Enemy":

                Enemy enemyTemp = enemies.get(index);
                String enemyWeapon = null;
                String enemyConsumable = null;

                //Checks if the enemy in the index is a unique weapon and gets both the weapon and consumable (if not null)
                if(enemyTemp instanceof Unique_Enemy && ((Unique_Enemy) enemyTemp).getWeapon() != null)
                    enemyWeapon = ((Unique_Enemy) enemyTemp).getWeapon().getName();
                if(enemyTemp instanceof Unique_Enemy && ((Unique_Enemy) enemyTemp).getConsumable() != null)
                    enemyConsumable = ((Unique_Enemy) enemyTemp).getConsumable().getName();

                //Updates the enemy selection view
                guiView.updateEnemySelectPanel(enemyTemp.getName(), enemyTemp.getType(), enemyTemp.getImage(), enemyTemp.getHitPoints(), enemyTemp.getAttack(),
                        enemyTemp.getDefense(), enemyTemp.getSpeed(), enemyWeapon, enemyConsumable);
                break;

            case "Environment":

                Environment environmentTemp = environments.get(index);

                //Updates the environment selection view
                guiView.updateEnvironmentSelectPanel(environmentTemp.getName(), environmentTemp.getImage(), environmentTemp.getPlayerEffectDescription(),
                        environmentTemp.getEnemyEffectDescription());

                break;
        }
    }


    /**
     * Updates the panel where all the choices of the player will be shown
     */
    public void updateCurrentSelection(){

        //Each String and ImageIcon attribute here check if the corresponding weapon, armor, or consumable exists via ternary operator.
        String weaponName = (selectedWeapon != null) ? selectedWeapon.getName() : "None";
        ImageIcon weaponImage= (selectedWeapon != null) ? selectedWeapon.getImage() : new ImageIcon("Assets/UI/None_Temp.png");
        String armorName = (selectedArmor != null) ? selectedArmor.getName() : "None";
        ImageIcon armorIamge= (selectedArmor != null) ? selectedArmor.getImage() : new ImageIcon("Assets/UI/None_Temp.png");
        String consumableName = (selectedConsumable != null) ? selectedConsumable.getName() : "None";
        ImageIcon consumableImage = (selectedConsumable != null) ? selectedConsumable.getImage() : new ImageIcon("Assets/UI/None_Temp.png");

        //Stores the needed values in a String and ImageIcon array
        String[] selectedNames = new String[]{event.getPlayer().getName(), weaponName, armorName, consumableName, 
                selectedEnemy.getName(), selectedEnvironment.getName()};
        ImageIcon[] selectedImages = new ImageIcon[]{event.getPlayer().getImage(), weaponImage, armorIamge, consumableImage, 
                selectedEnemy.getImage(), selectedEnvironment.getImage()};

        //Call update current selection method
        guiView.updateCurrentSelection(selectedNames, selectedImages);

    }


    /**
     * Method for button input
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();

        /*
        * For confirming button inputs in the selection panel, we iterate through the array of objects and compare
        * if the command is equal to the name of an object as we set the object name as the action command of the button.
        * To confirm a selection, a button needs to be pressed twice which is done by storing the previous command in previousButtonPressed
        * and checking if the 2nd time a button is clicked, the object index name is the same as the one stored.
        */
        
        //Checks if current panel is not currentChoices
        if(!guiView.getCurrentPanel().equals("currentChoices")){

            
            //Iterate through weapon array
            for(int i = 0; i < weapons.size(); i++){

                //Checks if command is equals to the weapon name at that index
                if(command.equals(weapons.get(i).getName())){
            
                    updateSelectionView("Weapon", i); //Update view

                    if(previousButtonPressed == weapons.get(i).getName()){

                        System.out.println("DOUBLE CLICK");

                        //Update selection and update logic
                        selectedWeapon = weapons.get(i); 
                        selectedChoice = true;
                        previousButtonPressed = null;

                    }

                    else
                        previousButtonPressed = weapons.get(i).getName();
                    
                }
                
            }

            //Iterate through armor array            
            for(int i = 0; i < armors.size(); i++){

                //Checks if command is equals to the armor name at that index
                if(command.equals(armors.get(i).getName())){
            
                    updateSelectionView("Armor", i); //Update view

                    if(previousButtonPressed == armors.get(i).getName()){

                        System.out.println("DOUBLE CLICK");

                        //Update selection and update logic
                        selectedArmor = armors.get(i);
                        selectedChoice = true;
                        previousButtonPressed = null;

                    }
                    else
                        previousButtonPressed = armors.get(i).getName();
                    
                }
                
            }

            //Iterate through consumable array
            for(int i = 0; i < consumables.size(); i++){

                //Checks if command is equals to the consumable name at that index
                if(command.equals(consumables.get(i).getName())){
            
                    updateSelectionView("Consumable", i); //Update view

                    if(previousButtonPressed == consumables.get(i).getName()){

                        System.out.println("DOUBLE CLICK");

                        //Update selection and update logic
                        selectedConsumable = consumables.get(i);
                        selectedChoice = true;
                        previousButtonPressed = null;

                    }
                    else
                        previousButtonPressed = consumables.get(i).getName();
                    
                }
                
            }

            //Iterate through enemy array
            for(int i = 0; i < enemies.size(); i++){

                //Checks if command is equals to the enemy name at that index
                if(command.equals(enemies.get(i).getName())){
            
                    updateSelectionView("Enemy", i); //Update view

                    if(previousButtonPressed == enemies.get(i).getName()){

                        System.out.println("DOUBLE CLICK");
        
                        //Update selection and update logic
                        selectedEnemy = enemies.get(i);
                        selectedChoice = true;
                        previousButtonPressed = null;

                    }
                    else
                        previousButtonPressed = enemies.get(i).getName();
                    
                }
                
            }

            //Iterate through environment array
            for(int i = 0; i < environments.size(); i++){

                //Checks if command is equals to the environment name at that index
                if(command.equals(environments.get(i).getName())){
            
                    updateSelectionView("Environment", i); //Update view

                    if(previousButtonPressed == environments.get(i).getName()){
                        System.out.println("DOUBLE CLICK");
            
                        //Update selection and update logic
                        selectedEnvironment = environments.get(i);
                        selectedChoice = true;
                        previousButtonPressed = null;

                    }
                    else
                        previousButtonPressed = environments.get(i).getName();
                    
                }
                
            }
            
        }


        //Switch statement to check the command and do the corresponding action
        switch(command){

            case "Select":

                //Checks if the player has selected a choice or we are at the last panel for selection
                if(selectedChoice || guiView.getCurrentPanel().equals("currentChoices")){

                    /*
                     * Switch statement that gets the current panel.
                     * If a condition is met, it will initialize the next selection and show that panel.
                     * if the currentPanel is in currentChoices, then we start the game instead.
                     */
                    switch(guiView.getCurrentPanel()){

                        case "weaponSelect":

                            guiView.initSelection("Armor");
                            guiView.showPanel("Select Armor");
                            break;

                        case "armorSelect":   

                            guiView.initSelection("Consumable");
                            guiView.showPanel("Select Consumable");
                            break;

                        case "consumableSelect":   

                            guiView.initSelection("Enemy");
                            guiView.showPanel("Select Enemy");
                            break;

                        case "enemySelect":   

                            guiView.initSelection("Environment");
                            guiView.showPanel("Select Environment");
                            break;

                        case "environmentSelect":   

                            guiView.initSelection("Final");
                            updateCurrentSelection();
                            guiView.showPanel("Current Choices");
                            break;

                        case "currentChoices":   

                            //Equip choices and set the selected enemy and environment
                            event.getPlayer().equipWeapon(selectedWeapon);
                            event.getPlayer().equipArmor(selectedArmor);
                            event.getPlayer().equipConsumable(selectedConsumable);        
                            event.setEnemy(selectedEnemy);
                            event.setEnvironment(selectedEnvironment); 

                            selectedWeapon = null;
                            selectedArmor = null;
                            selectedConsumable = null;
                            selectedEnemy = null;
                            selectedEnvironment = null;
                            
                            event.startGame(); //Starts the actual game.
 
                    }

                    //Resets the actionCommand and turn selectedChoice false
                    guiView.setActionCommand(this, guiView.getBackButton(), "Back");
                    guiView.setActionCommand(this, guiView.getSelectButton(), "Select");   
                    selectedChoice = false;

                }
                else
                    if(!guiView.getCurrentPanel().equals("currentChoices"))
                        guiView.showErrorMessage("No Selection"); //No Selection Error

                break;

            case "Back":

                /*
                * Switch statement that gets the current panel.
                * If a condition is met, it will move back to the previous selection and show that panel.
                * if the currentPanel is in weaponSelect, then we go back to the start panel.
                */
                switch(guiView.getCurrentPanel()){

                    case "weaponSelect": guiView.showPanel("Start"); break;

                    case "armorSelect": 

                        guiView.showPanel("Select Weapon"); 
                        selectedWeapon = null;

                        break;            

                    case "consumableSelect": 

                        guiView.showPanel("Select Armor"); 
                        selectedArmor = null;
                        break;

                    case "enemySelect": 
                        guiView.showPanel("Select Consumable"); 
                        selectedConsumable = null;
                        break;      

                    case "environmentSelect": guiView.showPanel("Select Enemy"); break;
                    case "currentChoices": guiView.showPanel("Select Environment"); break;
            
                }
                
                break;

            /*
             * Special case where for weapon, armor, and consumable where they have a none button
             */

            case "None":

                if(previousButtonPressed == "NONE"){

                        System.out.println("DOUBLE CLICK");

                        //Update logic without actually selecting anything
                        selectedChoice = true;
                        previousButtonPressed = null;
                    
                }
                else
                    previousButtonPressed = "NONE";

        }

    }

}
