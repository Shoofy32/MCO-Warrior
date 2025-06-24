public class Enemy {
  
    //String attributes
    private String name = "Enemy";
    private String type;

    //Stat attributes
    private int hitPoints;
    private int attack;
    private int defense;
    private int speed;

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
        
        //Checks whether hitpoints provided is greater than 0
        if(hitPoints >= 0)
            this.hitPoints = hitPoints;
        else
            this.hitPoints = 0; //Forces it to be 0 to avoid any negative values        

    }

    public void setAttack(int attack){

        this.attack = attack;

    }

    public void settSpeed(int speed){

        this.speed = speed;

    }

    public void setDefense(int defense){

        this.defense = defense;
        
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

    public int obtainLastAttackDealt(CLIViewer display){

        //If condition to check whether display is an instance of CLIViewer
        if(display instanceof CLIViewer)
            return getLastAttackDone;
        else 
            return 0;

    }

}
