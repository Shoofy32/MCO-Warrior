import java.util.Scanner;

public class Driver {
    
    public static void main(String[] args){

        CLIViewer cliView = new CLIViewer();
        EventController event = new EventController(cliView); //Creates the event controller (Does most functions of the game)
        Scanner input = new Scanner(System.in);

        createAndStoreInstances(event); //Method to call to create object instances and store them for the game

        event.start(input);

        input.close(); //Closes scanner
            
    }


    //Static methods creates the objects and stores them in their corresponding ArrayList in EventController instance
    public static void createAndStoreInstances(EventController event){

        //Create Weapon instances
        Dagger dagger = new Dagger("Common Dagger", 20, 0);
        Sword sword = new Sword("Common Sword", 30, 10);
        BattleAxe battleAxe = new BattleAxe("Common Axe", 40, 20);
        EnchantedWeapon busterSword = new EnchantedWeapon("Buster Sword", "Greatsword", 30, 20, "OmniSlash");
        EnchantedWeapon katana = new EnchantedWeapon("Frayed Blade", "Katana", 35, 10, "Parry");
        EnchantedWeapon brassKnuckles = new EnchantedWeapon("Brass Knuckles", "Fists", 20, 1, "AdrenalineRush");
        EnchantedWeapon pureNail = new EnchantedWeapon("Pure Nail", "Sword", 35, 5, "DreamSlash");


        //Create Armor instances
        Armor armor1 = new Armor("Leather Armor", "Light", 20, 5);
        Armor armor2 = new Armor("Chainmail Armor", "Medium", 30, 15);
        Armor armor3 = new Armor("Iron Armor", "Heavy", 40, 25);
        Armor armor4 = new Armor("Rusted Steel Armor", "Medium", 35, 10);
        Armor armor5 = new Armor("Frost Iron Armor", "Heavy", 50, 30);
        Armor armor6 = new Armor("Baldur Shell", "Heavy", 60, 40);
        Armor armor7 = new Armor("Sprintmaster Armor", "Light", 10, 5);

        
        //Create Enemy instances
        Thief bandit = new Thief("Bandit", "Thief", 150, 20, 20, 40);
        Viking haldor = new Viking("Haldor", "Viking", 250, 30, 30, 30);
        Minotaur taur = new Minotaur("Taur", "Minotaur", 350, 40, 40, 20);

        Unique_Enemy isshin = new Unique_Enemy("Isshin", "Samurai", 200, 0, 20, 50, "AAFU", 
        new EnchantedWeapon("Frayed Blade", "Katana", 35, 10, "Parry"),
        new Bomb("Reckless Gamble", 1, new String[]{"Speed"}, new int[]{50}, new String[]{"Attack"}, new int[]{30}, 3));
        Unique_Enemy ornstein = new Unique_Enemy("Ornstein", "Knight", 300, 0, 35, 30, "AUCF",
        new EnchantedWeapon("Buster Sword", "Greatsword", 30, 20, "OmniSlash"),
        new Buff("Rage Elixer", 1, new String[]{"Attack", "Defense"}, new int[]{20, -10}, 3));
        Unique_Enemy hornet = new Unique_Enemy("Hornet", "Knight", 250, 0, 10, 60, "CAAUF",
        new EnchantedWeapon("Pure Nail", "Sword", 35, 5, "DreamSlash"),
        new Healing("Estus Flask", 2, 45));
        Unique_Enemy radiance = new Unique_Enemy("Radiance", "Deity", 800, 70, 30, 25, "AAAAAAACA", null, null);


        //Create Environment instances
        Environment environment1 = new Environment("Arena", false);
        Environment environment2 = new Environment("Swamp", true, -1, "HitPoints", 1, "Attack");
        Environment environment3 = new Environment("Colosseum", true, 1, "Attack", -1, "Defense");
        Environment environment4 = new Environment("Painted Ariamis", true, -2, "Speed", -1, "Speed");
        Environment environment5 = new Environment("Blight Town", true, -1, "Defense", -3, "HitPoints");
        Environment environment6 = new Environment("Fungal Wastes", true, -10, "HitPoints", -10, "HitPoints");
        Environment environment7 = new Environment("Black Egg Temple", true, -1, "Attack", 5, "HitPoints");

        //Bonus - Create Consumable instances
        Consumable estusFlask = new Healing("Estus Flask", 2, 45);
        Consumable rageElixer = new Buff("Rage Elixer", 1, new String[]{"Attack", "Defense"}, new int[]{20, -10}, 3);
        Consumable windstormFlask = new Bomb("Windstorm Flask", 1, new String[]{"Speed"}, new int[]{25}, new String[]{"Speed"}, new int[]{-15}, 3);
        Consumable recklessGamble = new Bomb("Reckless Gamble", 1, new String[]{"Speed"}, new int[]{50}, new String[]{"Attack"}, new int[]{30}, 3);
        Consumable equivalentExchange = new Buff("Equivalent Exchange", 1, new String[]{"Defense", "Speed"}, new int[]{20, -20}, 3);
        Consumable redPotion = new Bomb("Red Potion", 1, new String[]{"Defense", "Speed", "Attack"}, new int[]{-10, -10, -10}, new String[]{"Defense", "Speed", "Attack"}, new int[]{10, 10, 10}, 10);

        //Store Weapon instances to Weapon ArrayList
        event.getSelectionController().addWeapon(dagger);
        event.getSelectionController().addWeapon(sword);
        event.getSelectionController().addWeapon(battleAxe);
        event.getSelectionController().addWeapon(busterSword);
        event.getSelectionController().addWeapon(katana);
        event.getSelectionController().addWeapon(brassKnuckles);
        event.getSelectionController().addWeapon(pureNail);

        //Store Armor instances to Armor ArrayList
        event.getSelectionController().addArmor(armor1);
        event.getSelectionController().addArmor(armor2);
        event.getSelectionController().addArmor(armor3);
        event.getSelectionController().addArmor(armor4);
        event.getSelectionController().addArmor(armor5);
        event.getSelectionController().addArmor(armor6);
        event.getSelectionController().addArmor(armor7);

        //Store Enemy instances to Enemy ArrayList
        event.getSelectionController().addEnemy(bandit);
        event.getSelectionController().addEnemy(haldor);
        event.getSelectionController().addEnemy(taur);
        event.getSelectionController().addEnemy(isshin);
        event.getSelectionController().addEnemy(ornstein);
        event.getSelectionController().addEnemy(hornet);
        event.getSelectionController().addEnemy(radiance);

        //Store Environment instances to Environment ArrayList
        event.getSelectionController().addEnvironment(environment1);
        event.getSelectionController().addEnvironment(environment2);
        event.getSelectionController().addEnvironment(environment3);
        event.getSelectionController().addEnvironment(environment4);
        event.getSelectionController().addEnvironment(environment5);
        event.getSelectionController().addEnvironment(environment6);
        event.getSelectionController().addEnvironment(environment7);

        //Store Consumable instances to Consumable ArrayList
        event.getSelectionController().addConsumable(estusFlask);
        event.getSelectionController().addConsumable(rageElixer);
        event.getSelectionController().addConsumable(windstormFlask);
        event.getSelectionController().addConsumable(recklessGamble);
        event.getSelectionController().addConsumable(equivalentExchange);
        event.getSelectionController().addConsumable(redPotion);
    }

}
