import java.util.Scanner;

public class Driver {
    
    public static void main(String[] args){

        EventController event = new EventController(); //Creates the event controller (Does most functions of the game)
        Scanner input = new Scanner(System.in);

        char choiceInput = '\u0000'; //Sets choiceInput to null
        
        createAndStoreInstances(event); //Method to call to create object instances and store them for the game
    
        choiceInput = event.mainMenu(input); //Starts the main menu

        //Do-While loop that checks if the Player wishes to quit or not
        do{

            //While loop controls the game as long isRunning is true
            while(event.getIsRunning()){

                event.getDisplay().displayGameBar(); //Displays Game Stats

                choiceInput = event.playerChoice(input); //Asks for Player choice for their turn

                event.turnSystem(choiceInput); //Controls the turn system and the results

            }

            //Checks if player didn't choose Q (Quit Game)
            if(choiceInput != 'Q')
                choiceInput = event.retry(input); //Asks user if they wish to retry or quit

        }while(choiceInput != 'Q');

        input.close(); //Closes scanner
            
    }


    //Static methods creates the objects and stores them in their corresponding ArrayList in EventController instance
    public static void createAndStoreInstances(EventController event){

        //Create Weapon instances
        Weapon weapon1 = new Weapon("Common Dagger", "Dagger", 20, 0);
        Weapon weapon2 = new Weapon("Common Sword", "Sword", 30, 10);
        Weapon weapon3 = new Weapon("Common Axe", "Battle Axe", 40, 20);
        Weapon weapon4 = new Weapon("Buster Sword", "Greatsword", 30, 20);
        Weapon weapon5 = new Weapon("Frayed Blade", "Katana", 35, 10);
        Weapon weapon7 = new Weapon("Brass Knuckles", "Fists", 20, 1);
        Weapon weapon6 = new Weapon("Pure Nail", "Rapier", 25, 5);


        //Create Armor instances
        Armor armor1 = new Armor("Leather Armor", "Light", 20, 5);
        Armor armor2 = new Armor("Chainmail Armor", "Medium", 30, 15);
        Armor armor3 = new Armor("Iron Armor", "Heavy", 40, 25);
        Armor armor4 = new Armor("Rusted Steel Armor", "Medium", 35, 10);
        Armor armor5 = new Armor("Frost Iron Armor", "Heavy", 50, 30);
        Armor armor6 = new Armor("Baldur Shell", "Heavy", 60, 40);
        Armor armor7 = new Armor("Sprintmaster Armor", "Light", 10, 5);

        
        //Create Enemy instances
        Enemy enemy1 = new Enemy("Bandit", "Thief", 150, 20, 20, 40);
        Enemy enemy2 = new Enemy("Haldor", "Viking", 250, 30, 30, 30);
        Enemy enemy3 = new Enemy("Taur", "Minotaur", 350, 40, 40, 20);
        Enemy enemy4 = new Enemy("Isshin", "Samurai", 200, 35, 20, 40);
        Enemy enemy5 = new Enemy("Ornstein", "Knight", 300, 40, 35, 10);
        Enemy enemy6 = new Enemy("Hornet", "Knight", 250, 30, 10, 55);
        Enemy enemy7 = new Enemy("Radiance", "Deity", 700, 70, 30, 25);


        //Create Environment instances
        Environment environment1 = new Environment("Arena", false);
        Environment environment2 = new Environment("Swamp", true, -1, "HitPoints", 1, "Attack");
        Environment environment3 = new Environment("Colosseum", true, 1, "Attack", -1, "Defense");
        Environment environment4 = new Environment("Castle Cainhurst", true, -2, "Speed", -1, "Speed");
        Environment environment5 = new Environment("Blight Town", true, -1, "Defense", -3, "HitPoints");
        Environment environment6 = new Environment("Fungal Wastes", true, -10, "HitPoints", -10, "HitPoints");
        Environment environment7 = new Environment("Black Egg Temple", true, -1, "Attack", 5, "HitPoints");

        //Bonus - Create Consumable instances
        String[] playerEffects1 = {"HitPoints"}; //Each Consumable requires an array of Strings corresponding to stats to affect
        int[] playerValues1 = {35}; //Each Consumable requires an array of ints corresponding to the values that will affect the stats
        Consumable consumable1 = new Consumable("Estus Flask", "Healing", 2, true, false, playerEffects1, playerValues1, 0);

        String[] playerEffects2 = {"Attack", "Defense"}; 
        int[] playerValues2 = {20, -10}; 
        Consumable consumable2 = new Consumable("Rage Elixir", "Buff", 1, true, false, playerEffects2, playerValues2, 3);

        String[] playerEffects3 = {"Speed"}; 
        int[] playerValues3 = {25};
        String[] enemyEffects1 = {"Speed"};
        int[] enemyValues1 = {-15};
        Consumable consumable3 = new Consumable("Windstorm Flask", "Bomb", 1, playerEffects3, playerValues3, enemyEffects1, enemyValues1, 3);

        String[] playerEffects4 = {"Speed"};
        int[] playerValues4 = {50};
        String[] enemyEffects2 = {"Attack"};
        int[] enemyValues2 = {30};
        Consumable consumable4 = new Consumable("Reckless Gamble", "Bomb", 1, playerEffects4, playerValues4, enemyEffects2, enemyValues2, 1);

        String[] playerEffects5 = {"Defense", "Speed"};
        int[] playerValues5 = {20, -20};
        Consumable consumable5 = new Consumable("Equivalent Exchange", "Buff", 1,true, false, playerEffects5, playerValues5, 3);

        String[] playerEffects6 = {"Defense", "Speed", "Attack"};
        int[] playerValues6 = {-10, -10, -10};
        String[] enemyEffects3 = {"Defense", "Speed", "Attack"};
        int[] enemyValues3 = {10, 10, 10};
        Consumable consumable6 = new Consumable("Red Potion", "Debuff", 1, playerEffects6, playerValues6, enemyEffects3, enemyValues3 , 10);

        //Store Weapon instances to Weapon ArrayList
        event.getSelectionController().addWeapon(weapon1);
        event.getSelectionController().addWeapon(weapon2);
        event.getSelectionController().addWeapon(weapon3);
        event.getSelectionController().addWeapon(weapon4);
        event.getSelectionController().addWeapon(weapon5);
        event.getSelectionController().addWeapon(weapon6);
        event.getSelectionController().addWeapon(weapon7);

        //Store Armor instances to Armor ArrayList
        event.getSelectionController().addArmor(armor1);
        event.getSelectionController().addArmor(armor2);
        event.getSelectionController().addArmor(armor3);
        event.getSelectionController().addArmor(armor4);
        event.getSelectionController().addArmor(armor5);
        event.getSelectionController().addArmor(armor6);
        event.getSelectionController().addArmor(armor7);

        //Store Enemy instances to Enemy ArrayList
        event.getSelectionController().addEnemy(enemy1);
        event.getSelectionController().addEnemy(enemy2);
        event.getSelectionController().addEnemy(enemy3);
        event.getSelectionController().addEnemy(enemy4);
        event.getSelectionController().addEnemy(enemy5);
        event.getSelectionController().addEnemy(enemy6);
        event.getSelectionController().addEnemy(enemy7);

        //Store Environment instances to Environment ArrayList
        event.getSelectionController().addEnvironment(environment1);
        event.getSelectionController().addEnvironment(environment2);
        event.getSelectionController().addEnvironment(environment3);
        event.getSelectionController().addEnvironment(environment4);
        event.getSelectionController().addEnvironment(environment5);
        event.getSelectionController().addEnvironment(environment6);
        event.getSelectionController().addEnvironment(environment7);

        //Store Consumable instances to Consumable ArrayList
        event.getSelectionController().addConsumable(consumable1);
        event.getSelectionController().addConsumable(consumable2);
        event.getSelectionController().addConsumable(consumable3);
        event.getSelectionController().addConsumable(consumable4);
        event.getSelectionController().addConsumable(consumable5);
        event.getSelectionController().addConsumable(consumable6);
    }
}
