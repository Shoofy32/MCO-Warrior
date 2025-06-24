public class Weapon {
    
    //Private Attributes

    //String attributes
    private String name;
    private String type;

    //Stat attributes
    private int attack; //Amount of damage the weapon can deal
    private int speedPenalty; //Speed decrease when using the weapon

    //Static attributes
    private static int totalWeapons = 0; //Stores the amount of Weapon instances

    
    //Method Constructor
    public Weapon(String name, String type, int attack, int speedPenalty){

        this.name = name;
        this.type = type;
        this.attack = attack;
        this.speedPenalty = speedPenalty;
        totalWeapons++; //Increment counter

    }


    //Getters
    public String getName(){

        return name;

    }

    public String getType(){

        return type;

    }

    public int getAttack(){

        return attack;

    }

    public int getSpeedPenalty(){

        return speedPenalty;

    }

    //Returns total amount of Weapon instances 
    public static int getTotalWeapons(){

        return totalWeapons;
        
    }
}
