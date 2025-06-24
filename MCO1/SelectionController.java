import java.util.Scanner;
import java.util.ArrayList;

public class SelectionController {
    
    //Private Attributes
    private CLIViewer display; //For CLI display

    /*
     * Arraylists for classes that are instanced for the player to select their choice.
     * Due to the use of Arraylists, it allows for any number of choices to be added or removed for the player.
     */
    private static ArrayList<Weapon> weapons; //Arraylist for Weapon Class
    private static ArrayList<Armor> armors; //Arraylist for Armor Class
    private static ArrayList<Enemy> enemies; //Arraylist for Enemy Class
    private static ArrayList<Environment> environments; //Arraylist for Environment Class


    //Constructor
    public SelectionController(CLIViewer display){

        this.display = display;

        //ArrayLists runtime dependent on SelectionController
        weapons = new ArrayList<>();
        armors = new ArrayList<>();
        enemies = new ArrayList<>();
        environments = new ArrayList<>();

    }


    //Getters
    public ArrayList<Weapon> getWeapons(){

        return weapons;

    }

    public ArrayList<Armor> getArmors(){

        return armors;

    }

    public ArrayList<Enemy> getEnemies(){

        return enemies;

    }

    public ArrayList<Environment> getEnvironments(){

        return environments;

    }


    //Methods to add selections to ArrayList

    public void addWeapon(Weapon weapon){

        getWeapons().add(weapon); //Add Weapon instance to weapons Array List

    }


    public void addArmor(Armor armor){

        getArmors().add(armor); //Add Armor instance to armor Array List
        
    }


    public void addEnemy(Enemy enemy){
 
        getEnemies().add(enemy); //Add Enemy instance to enemy Array List

    }


    public void addEnvironment(Environment environment){

        getEnvironments().add(environment); //Add Environment instance to environment Array List

    }


    //Methods for Player to select choices of Weapon, Armor, Enemy, and Environmentfrom ArrayLists

    public Weapon selectWeapon(Scanner input){
 
        int listSize = getWeapons().size(); //Gets the size of the Weapon ArrayList
        String weaponSelect; //String for player input
        boolean hasWeapon = false; //Checks whether Player has selected a weapon or not


        //Do-While loop in case Player has made invalid selection
        do{

            display.displaySelection("Weapon", this); //Display Weapon choices

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
            if(!hasWeapon && !weaponSelect.equals("No Weapon") && !weaponSelect.equals("0"))
                System.out.printf("\nPlease select a weapon among the choices!\n");

        }while(!hasWeapon && !weaponSelect.equals("No Weapon") && !weaponSelect.equals("0"));

        return null; //Return null here to satisfy Java compiler code path

    }


    public Armor selectArmor(Scanner input){

        int listSize = getArmors().size(); //Gets size of the ArrayList
        String armorSelect; //String for player input
        boolean hasArmor = false; //Checks whether Player has selected a armor or not


        //Do-While loop in case Player has made invalid selection
        do{

            display.displaySelection("Armor", this); //Display Armor choices

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
            if(!hasArmor && !armorSelect.equals("No Armor") && !armorSelect.equals("0"))
                System.out.printf("\nPlease select a armor among the choices!\n");

        }while(!hasArmor && armorSelect.equals("No Armor") && !armorSelect.equals("0"));
        



             

        return null; //Return null here to satisfy Java compiler code path

    }


    public Enemy selectEnemy(Scanner input){

        int listSize = getEnemies().size(); //Gets size of the ArrayList
        String enemySelect; //String for player input
        boolean selectedEnemy = false; //Checks whether Player has selected a enemy or not


        //While loop in case Player has made invalid selection
        while(!selectedEnemy){

            display.displaySelection("Enemy", this); //Display Enemy choices

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
            System.out.printf("\nPlease select a enemy among the choices!\n");

        }     

        return null; //Return null here to satisfy Java compiler code path

    }


    public Environment selectEnvironment(Scanner input){

        int listSize = getEnvironments().size(); //Gets size of the ArrayList
        String environmentSelect; //String for player input
        boolean selectedEnvironment = false; //Checks whether Player has selected a enemy or not


        //While loop in case Player has made invalid selection
        while(!selectedEnvironment){

            display.displaySelection("Environment", this); //Display Environment choices

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
            System.out.printf("\nPlease select a environment among the choices!\n");

        }     

        return null; //Return null here to satisfy Java compiler code path
    }

}
