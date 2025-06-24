import java.util.Scanner;

public class Driver {
    
    public static void main(String[] args){

        EventController event = new EventController();
        Scanner input = new Scanner(System.in);

        char choiceInput = '\u0000';

        Weapon weapon1 = new Weapon("Common Dagger", "Dagger", 20, 0);
        Weapon weapon2 = new Weapon("Common Sword", "Sword", 30, 10);
        Weapon weapon3 = new Weapon("Common Axe", "Battle Axe", 40, 20);
        Weapon weapon4 = new Weapon("Buster Sword", "Greatsword", 30, 20);
        Weapon weapon5 = new Weapon("Frayed Blade", "Katana", 35, 10);

        Armor armor1 = new Armor("Leather Armor", "Light", 20, 5);
        Armor armor2 = new Armor("Chainmail Armor", "Medium", 30, 15);
        Armor armor3 = new Armor("Iron Armor", "Heavy", 40, 25);
        Armor armor4 = new Armor("Rusted Steel Armor", "Medium", 35, 10);
        Armor armor5 = new Armor("Frost Iron Armor", "Heavy", 50, 30);
        
        Enemy enemy1 = new Enemy("Bandit", "Thief", 150, 20, 20, 40);
        Enemy enemy2 = new Enemy("Haldor", "Viking", 250, 30, 30, 30);
        Enemy enemy3 = new Enemy("Taur", "Minotaur", 350, 40, 40, 20);
        Enemy enemy4 = new Enemy("Isshin", "Samurai", 200, 35, 20, 40);
        Enemy enemy5 = new Enemy("Ornstein", "Knight", 300, 40, 35, 10);

        Environment environment1 = new Environment("Arena", false);
        Environment environment2 = new Environment("Swamp", true, -1, "HitPoints", 1, "Attack");
        Environment environment3 = new Environment("Colesseum", true, 1, "Attack", -1, "Defense");
        Environment environment4 = new Environment("Castle Cainhurst", true, -2, "Speed", -1, "Speed");
        Environment environment5 = new Environment("Blight Town", true, -1, "Defense", -3, "HitPoints");

        event.getSelectionController().addWeapon(weapon1);
        event.getSelectionController().addWeapon(weapon2);
        event.getSelectionController().addWeapon(weapon3);
        event.getSelectionController().addWeapon(weapon4);
        event.getSelectionController().addWeapon(weapon5);

        event.getSelectionController().addArmor(armor1);
        event.getSelectionController().addArmor(armor2);
        event.getSelectionController().addArmor(armor3);
        event.getSelectionController().addArmor(armor4);
        event.getSelectionController().addArmor(armor5);

        event.getSelectionController().addEnemy(enemy1);
        event.getSelectionController().addEnemy(enemy2);
        event.getSelectionController().addEnemy(enemy3);
        event.getSelectionController().addEnemy(enemy4);
        event.getSelectionController().addEnemy(enemy5);

        event.getSelectionController().addEnvironment(environment1);
        event.getSelectionController().addEnvironment(environment2);
        event.getSelectionController().addEnvironment(environment3);
        event.getSelectionController().addEnvironment(environment4);
        event.getSelectionController().addEnvironment(environment5);

    

        choiceInput = event.mainMenu(input);

        //Do-While loop that checks if the Player wishes to quit or not
        do{

            //While loop controls the game as long isRunning is true
            while(event.getIsRunning()){

                event.getDisplay().displayGameBar();

                choiceInput = event.playerChoice(input);
                choiceInput = Character.toUpperCase(choiceInput);

                event.turnSystem(choiceInput);

            }

            if(choiceInput != 'Q')
                choiceInput = event.retry(input);

        }while(choiceInput != 'Q');

        input.close(); //Closes scanner
            
    }
}
