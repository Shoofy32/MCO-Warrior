import java.util.Scanner;
import java.util.ArrayList;

/**
 * Class file to control selections of weapon, armor, enemy, consumable, and environment objects.
 * <p>
 *     This class stores objects in ArrayLists for easy access and organization.
 * </p>
 * @author Stefan_Martin
 */
public class SelectionController {
    
    //Private Attributes
    private CLIViewer display; //For CLI display

    /*
     * Arraylists for classes that are instanced for the player to select their choice.
     * Due to the use of Arraylists, it allows for any number of choices to be added or removed for the player.
     */
    private static ArrayList<Weapon> weapons; //ArrayList for Weapon Class
    private static ArrayList<Armor> armors; //ArrayList for Armor Class
    private static ArrayList<Enemy> enemies; //ArrayList for Enemy Class
    private static ArrayList<Environment> environments; //ArrayList for Environment Class
    private static ArrayList<Consumable> consumables; //ArrayList for Consumable Class


    //Constructor

    /**
     * Constructor method for the SelectionController
     * @param display CLIViewer object to be passed
     * @see CLIViewer
     */
    public SelectionController(CLIViewer display){

        this.display = display;

        //ArrayLists runtime dependent on SelectionController
        weapons = new ArrayList<>();
        armors = new ArrayList<>();
        enemies = new ArrayList<>();
        environments = new ArrayList<>();
        consumables = new ArrayList<>();

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

    /**
     * Getter method to retrieve the consumable objects stored.
     * @return ArrayList of consumable objects
     */
    public ArrayList<Consumable> getConsumables(){

        return consumables;

    }

    //Methods to add selections to ArrayList

    /**
     * Setter method to add a weapon object to the corresponding ArrayList
     * @param weapon weapon object to be added
     */
    public void addWeapon(Weapon weapon){

        getWeapons().add(weapon); //Add Weapon instance to weapons Array List

    }

    /**
     * Setter method to add an armor object to the corresponding ArrayList
     * @param armor armor object to be added
     */
    public void addArmor(Armor armor){

        getArmors().add(armor); //Add Armor instance to armor Array List
        
    }

    /**
     * Setter method to add an enemy object to the corresponding ArrayList
     * @param enemy enemy object to be added
     */
    public void addEnemy(Enemy enemy){
 
        getEnemies().add(enemy); //Add Enemy instance to enemy Array List

    }

    /**
     * Setter method to add an environment object to the corresponding ArrayList
     * @param environment environment object to be added
     */
    public void addEnvironment(Environment environment){

        getEnvironments().add(environment); //Add Environment instance to environment Array List

    }

    /**
     * Setter method to add a consumable object to the corresponding ArrayList
     * @param consumable consumable object to be added
     */
    public void addConsumable(Consumable consumable){

        getConsumables().add(consumable); //Add Consumable instance to consumable Array List

    }

    //Methods for Player to select choices of Weapon, Armor, Enemy, and Environment from ArrayLists

    /**
     * Method for displaying the possible weapons the player can equip
     * @param input the user's input for the weapon they want to equip
     * @return the chosen weapon to be equipped
     */
    public Weapon selectWeapon(Scanner input){
 
        int listSize = getWeapons().size(); //Gets the size of the Weapon ArrayList
        String weaponSelect; //String for player input
        boolean hasWeapon = false; //Checks whether Player has selected a weapon or not

        display.displaySelection("Weapon", this); //Display Weapon choices

        //Do-While loop in case Player has made invalid selection
        do{

            System.out.printf("Select Weapon [Name/Number]: "); //Asks for name or number input
            weaponSelect = input.nextLine();

            
            //Checks whether the player picked an object or not. If not, then player equips nothing
            if(!weaponSelect.equals("No Weapon") && !weaponSelect.equals("0")){

                //For loop to go through the Weapon instances in the ArrayList to get the weapon the player chose
                for(int i  = 0; i < listSize && !hasWeapon; i++){

                    //If conditon checks if the Weapon object in that index has the same name or number as the Player input
                    if(getWeapons().get(i).getName().equals(weaponSelect) || String.valueOf(i+1).equals(weaponSelect)){

                        hasWeapon = true; //Turns true to avoid loop
                        return getWeapons().get(i); //Return weapon choice

                    }

                }

            }

            //Error to display if Player input is not within the ArrayList
            if(!hasWeapon && !weaponSelect.equals("No Weapon") && !weaponSelect.equals("0")){

                display.displaySelection("Weapon", this); //Display Weapon choices
                System.out.printf("\nPlease select a weapon among the choices!\n");

            }


        }while(!hasWeapon && !weaponSelect.equals("No Weapon") && !weaponSelect.equals("0"));

        return null; //Return null here to satisfy Java compiler code path

    }

    /**
     * Method for displaying the possible armors the player can equip
     * @param input the user's input for the armor they want to equip
     * @return the chosen armor to be equipped
     */
    public Armor selectArmor(Scanner input){

        int listSize = getArmors().size(); //Gets size of the ArrayList
        String armorSelect; //String for player input
        boolean hasArmor = false; //Checks whether Player has selected a armor or not

        display.displaySelection("Armor", this); //Display Armor choices

        //Do-While loop in case Player has made invalid selection
        do{

            System.out.printf("Select Armor [Name/Number]: "); //Asks for name or number input
            armorSelect = input.nextLine();


            //Checks whether the player picked an object or not. If not, then player equips nothing
            if(!armorSelect.equals("No Armor") && !armorSelect.equals("0")){

                //For loop to go through the Armor instances in the ArrayList to get the armor the player chose
                for(int i  = 0; i < listSize && !hasArmor; i++){

                    //If conditon checks if the Armor object in that index has the same name or number as the Player input
                    if(getArmors().get(i).getName().equals(armorSelect) || String.valueOf(i+1).equals(armorSelect)){

                        hasArmor = true;
                        return getArmors().get(i); //Return armor choice

                    }

                }

            }

            //Error to display if Player input is not within the ArrayList
            if(!hasArmor && !armorSelect.equals("No Armor") && !armorSelect.equals("0")){

                display.displaySelection("Armor", this); //Display Armor choices
                System.out.printf("\nPlease select a armor among the choices!\n");


            }


        }while(!hasArmor && !armorSelect.equals("No Armor") && !armorSelect.equals("0"));
        



             

        return null; //Return null here to satisfy Java compiler code path

    }

    /**
     * Method for displaying the possible enemies the player can fight
     * @param input the user's input for the enemy they want to fight
     * @return the chosen enemy to be fought
     */
    public Enemy selectEnemy(Scanner input){

        int listSize = getEnemies().size(); //Gets size of the ArrayList
        String enemySelect; //String for player input
        boolean selectedEnemy = false; //Checks whether Player has selected a enemy or not

        display.displaySelection("Enemy", this); //Display Enemy choices

        //While loop in case Player has made invalid selection
        while(!selectedEnemy){

            System.out.printf("Select Enemy [Name/Number]: "); //Asks for name or number input
            enemySelect = input.nextLine();


            //For loop to go through the Enemy instances in the ArrayList to get the enemy the player chose
            for(int i  = 0; i < listSize && !selectedEnemy; i++){

                //If conditon checks if the Enemy object in that index has the same name or number as the Player input 
                if(getEnemies().get(i).getName().equals(enemySelect) || String.valueOf(i+1).equals(enemySelect)){

                    selectedEnemy = true; 
                    return getEnemies().get(i); //Returns the selected enemy

                }

            }

            //Error to display if Player input is not within the ArrayList
            display.displaySelection("Enemy", this); //Display Enemy choices
            System.out.printf("\nPlease select a enemy among the choices!\n");

        }     

        return null; //Return null here to satisfy Java compiler code path

    }

    /**
     * Method for displaying the possible environments to battle in
     * @param input the user's input for the environment they want
     * @return the chosen environment
     */
    public Environment selectEnvironment(Scanner input){

        int listSize = getEnvironments().size(); //Gets size of the ArrayList
        String environmentSelect; //String for player input
        boolean selectedEnvironment = false; //Checks whether Player has selected a environment or not

        display.displaySelection("Environment", this); //Display Environment choices

        //While loop in case Player has made invalid selection
        while(!selectedEnvironment){

            System.out.printf("Select Environment [Name/Number]: "); //Asks for name or number input
                environmentSelect = input.nextLine();

            //For loop to go through the Enemy instances in the ArrayList to get the enemy the player chose
            for(int i  = 0; i < listSize && !selectedEnvironment; i++){

                //If conditon checks if the Enemy object in that index has the same name or number as the Player input
                if(getEnvironments().get(i).getName().equals(environmentSelect)|| String.valueOf(i+1).equals(environmentSelect)){

                    selectedEnvironment = true; 
                    return getEnvironments().get(i); //Returns the selected enemy

                }

            }

            //Error to display if Player input is not within the ArrayList
            display.displaySelection("Environment", this); //Display Environment choices
            System.out.printf("\nPlease select a environment among the choices!\n");

        }     

        return null; //Return null here to satisfy Java compiler code path
    }

    /**
     * Method for displaying the possible consumables the player can equip
     * @param input the user's input for the consumable they want to equip
     * @return the chosen consumable to be equipped
     */
    public Consumable selectConsumable(Scanner input){

        int listSize = getConsumables().size(); //Gets size of the ArrayList
        String consumableSelect; //String for player input
        boolean selectedConsumable = false; //Checks whether Player has selected a consumable or not

        display.displaySelection("Consumable", this); //Display Consumable choices

        //Do-while loop in case Player has made invalid selection
        do{

            System.out.printf("Select Consumable [Name/Number]: "); //Asks for name or number input
                consumableSelect = input.nextLine();

            //Checks whether the player picked an object or not. If not, then player equips nothing
            if(!consumableSelect.equals("No Consumable") && !consumableSelect.equals("0")){

                //For loop to go through the Enemy instances in the ArrayList to get the enemy the player chose
                for(int i  = 0; i < listSize && !selectedConsumable; i++){

                    //If conditon checks if the Enemy object in that index has the same name or number as the Player input
                    if(getEnvironments().get(i).getName().equals(consumableSelect)|| String.valueOf(i+1).equals(consumableSelect)){

                        selectedConsumable = true; 
                        return getConsumables().get(i); //Returns the selected enemy

                    }

                }

            }

            //Error to display if Player input is not within the ArrayList
            if(!selectedConsumable && !consumableSelect.equals("No Armor") && !consumableSelect.equals("0")){

                display.displaySelection("Consumable", this); //Display Consumable choices
                System.out.printf("\nPlease select a consumable among the choices!\n");

            }

        }while(!selectedConsumable && !consumableSelect.equals("No Consumable") && !consumableSelect.equals("0"));
        
        
        return null; //Return null here to satisfy Java compiler code path
    }

}
