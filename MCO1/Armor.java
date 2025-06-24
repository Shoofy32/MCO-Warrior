public class Armor {

    //Private Attributes

    //String attributes
    private String name;
    private String type;

    //Stat attributes
    private int defense; //Amount of defense the weapon can protect against
    private int speedPenalty; //Speed decrease when using the weapon

    //Static Attriburtes
    private static int totalArmor  = 0; //Stores the amount of Armor instances

    
    //Method Constructor
    public Armor(String name, String type, int defense, int speedPenalty){

        this.name = name;
        this.type = type;
        this.defense = defense;
        this.speedPenalty = speedPenalty;
        totalArmor++; //Increment counter

    }


    //Getters
    public String getName(){

        return name;

    }

    public String getType(){

        return type;

    }

    public int getDefense(){

        return defense;

    }

    public int getSpeedPenalty(){

        return speedPenalty;

    }

    //Returns total amount of Armor instances 
    public static int getTotalArmor(){

        return totalArmor;
        
    }
}
