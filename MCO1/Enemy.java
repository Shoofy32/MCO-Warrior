public class Enemy {
  
    //String attributes
    private String name = "Enemy";
    private String type;

    //Stat attributes
    private int hitPoints; //Health of the Player
    private int attack; //How much damage they will deal
    private int defense; //How much damage can be resisted
    private int speed; //How fast they will attack (Determines who goes first)

    //Static attributes
    private static int totalEnemies = 0; //Stores the amount of Enemy instances

    //Attributes for display
    private int getLastAttackDone; //Stores the amount of damage that was last done

    //Constructor
    public Enemy(String name, String type, int hitPoints, int attack, int defense, int speed){

        this.name = name;
        this.type = type;
        this.hitPoints = hitPoints;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        totalEnemies++; //Increment counter

    }


    //Getters
    public String getName(){

        return name;

    }

    public String getType(){

        return type;

    }

    public int getHitPoints(){
        
        return hitPoints;

    }

    public int getAttack(){

        return attack;

    }

    public int getSpeed(){

        return speed;

    }

    public int getDefense(){

        return defense;
        
    }

    //Returns total amount of Enemy instances 
    public static int getTotalEnemies(){

        return totalEnemies;

    }


    //Setters
    public void setHitPoints(int hitPoints){
        
        this.hitPoints = hitPoints;

        //Checks if hitPoints has turned into negative values
        if(this.hitPoints < 0)
            this.hitPoints = 0; //Forces it to be 0 to avoid any negative values   

    }

    public void setAttack(int attack){

        //Checks whether attack provided is greater or equal than 0
        if(attack >= 0)
            this.attack = attack;
        else
            this.attack = 0; //Forces it to be 0 to avoid any negative values   

    }

    public void setSpeed(int speed){

        if(speed >= 0)
            this.speed = speed;
        else
            this.speed = 0; //Forces it to be 0 to avoid any negative values  

    }

    public void setDefense(int defense){

        //Checks whether defense provided is greater or equal than 0
        if(defense >= 0)
            this.defense = defense;
        else
            this.speed = 0; //Forces it to be 0 to avoid any negative values  
        
    }


    //Turn-based Methods
    public void attack(Player target){

        int damage; 

        //If condition to check whether target was defending at the time of the attack
        if(target.getIsDefending()){

            //Ternary Operator to calculate damage wether damage was dealt or not with defending target (Half Damage)
            damage = ((attack - target.getDefense()) / 2 > 0) ? ((attack - target.getDefense()) / 2) : 0;
            target.setHitPoints(target.getHitPoints() - damage); //Updates the targets hitpoints after taking damag
            target.stopDefending(); 

            getLastAttackDone = damage; //Stores the damage that was done by the Player for display 

        }
        else{

            //Ternary Operator to calculate damage wether damage was dealt or not
            damage = (attack - target.getDefense() > 0) ? attack - target.getDefense() : 0;
            target.setHitPoints(target.getHitPoints() - damage); //Updates the targets hitpoints after taking damag
            
            getLastAttackDone = damage; //Stores the damage that was done by the Player for display

        }


    }


    //Method allows display to get the last attack's amount
    public int obtainLastAttackDealt(CLIViewer display){

        //If condition to check whether display is an instance of CLIViewer
        if(display instanceof CLIViewer)
            return getLastAttackDone;
        else 
            return 0;

    }

}
