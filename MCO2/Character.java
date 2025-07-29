import javax.swing.ImageIcon;

public abstract class Character {
    
        //String attributes
    private final String name; //Name of Player
    private ImageIcon image;

    //Stat attributes
    private int hitPoints; //Health of the Player
    private int attack; //How much damage they will deal
    private int defense; //How much damage can be resisted
    private int speed; //How fast they will attack (Determines who goes first)
    private int chargeMultiplier = 3; //Multiplier for attack when Character is charging

    //Weapon and Armor attributes
    private Armor armor = null; //Armor that the player can equip
    private Weapon weapon = null; //Weapon that the player can equip
    private Consumable consumable = null; //Consumable that the player can use

    //State attributes
    private boolean isCharging = false; //Boolean check to see whether Player is charging
    private boolean isDefending = false; //Boolean check to see whether Player is defending
    private boolean hasConsumeTemp = false; //Boolean check to see whether Player used a consumable that is temporary
    private boolean isInvulnerable = false; //Boolean check to see whether Player is immune to damage
    private int chargeBase; //Int to store attack value at time of charging to isolate it from environment effects
    private char turnInputAction; //Stores the input of the character
    private String lastCharacterAction; //Stores a String equivalent of the latest action of the character
    private int timesToAttack = 1; //Stores how many times the action attack with be performed
    
    //Attributes for display (CHANGE)
    private int getLastAttackDone; //Stores the amount of damage that was last done

    
    //Constructor

    /**
     * Constructor for the player object
     * @param name a unique name the player will be identified with
     */
    public Character(String name, ImageIcon image, int hitPoints, int attack, int defense, int speed){

       this.name = name;
       this.image = image;
       this.hitPoints = hitPoints;
       this.attack = attack;
       this.defense = defense;
       this.speed = speed;
;
    }


    //Setters

    /**
     * Updates the player's health value
     * @param hitPoints new health value
     */
    public void setHitPoints(int hitPoints){
        
        this.hitPoints = hitPoints;

        //Checks if hitPoints has turned into negative values
        if(this.hitPoints < 0)
            this.hitPoints = 0; //Forces it to be 0 to avoid any negative values   

    }

    /**
     * Updates the player's atk value
     * @param attack new atk value
     */
    public void setAttack(int attack){

        //Checks whether attack provided is greater or equal than 0
        if(attack >= 0)
            this.attack = attack;
        else
            this.attack = 0; //Forces it to be 0 to avoid any negative values   

    }

    /**
     * Updates the player's spd value
     * @param speed new spd value
     */
    public void setSpeed(int speed){

        if(speed >= 0)
            this.speed = speed;
        else
            this.speed = 0; //Forces it to be 0 to avoid any negative values  

    }

    /**
     * Updates the player's def value
     * @param defense new def value
     */
    public void setDefense(int defense){

        //Checks whether defense provided is greater or equal than 0
        if(defense >= 0)
            this.defense = defense;
        else
            this.defense = 0; //Forces it to be 0 to avoid any negative values  
        
    }


    public void setChargeMultipler(int multiplier){


        //Checks whether defense provided is greater or equal than 0
        if(chargeMultiplier >= 3)
            chargeMultiplier = multiplier;
        else
            chargeMultiplier = 3; //Forces it to be 0 to avoid any negative values  


    }

    /**
     * Updates the player's current temporary consumable usage
     * @param hasConsumeTemp new state of consumable usage (true or false)
     */
    public void setHasConsumeTemp(boolean hasConsumeTemp){

        this.hasConsumeTemp = hasConsumeTemp;

    }


    /**
     * Updates the state to determine if the player is immune to damage or not
     * @param isInvulnerable new state of player immunity (true or false)
     */
    public void setIsInvulnerable(boolean isInvulnerable){
        
        this.isInvulnerable = isInvulnerable;

    }

    /**
     * Snapshots the player's current atk for charging to isolate it from any atk boosting effects in the turn
     * @param attack the player's atk before any external factors are applied in the turn
     */
    public void setChargeBase(int attack) {
        this.chargeBase = attack;
    }


    /**
     * Updates and stores a char equivalent of what the Character will do this turn
     * @param turnAction the char of what the Character will do
     */
    public void setTurnInputAction(char turnInputAction){

        this.turnInputAction = turnInputAction;

    }


    /**
     * Updates and stores a String equivalent of what the Character's last action was
     * @param turnAction the String of Character's last action
     */
    public void setLastCharacterAction(String lastCharacterAction){

        this.lastCharacterAction = lastCharacterAction;

    }


    /**
     * Updates and stores how many times the Character will perform the Attack action
     * @param turnAction the number of times to attack
     */
    public void setTimesToAttack(int timesToAttack){

        if(timesToAttack > 0)
            this.timesToAttack = timesToAttack;
        else
            timesToAttack = 1;

    }


    //Getters

    /**
     * Getter method to retrieve the player's name
     * @return the player's name
     */
    public String getName(){

        return name;

    }

    public ImageIcon getImage(){

        return image;
        
    }

    /**
     * Getter method to retrieve the player's current HP value
     * @return the player's current HP
     */
    public int getHitPoints(){
        
        return hitPoints;

    }

    /**
     * Getter method to retrieve the player's current atk value
     * @return the player's current atk
     */
    public int getAttack(){

        return attack;

    }

    /**
     * Getter method to retrieve the player's current spd value
     * @return the player's current spd
     */
    public int getSpeed(){

        return speed;

    }

    /**
     * Getter method to retrieve the player's current def value
     * @return the player's current def
     */
    public int getDefense(){

        return defense;
        
    }

    /**
     * Getter method to retrieve the character's current charge multiplier
     * @return the character's current charge multiplier
     */
    public int getChargeMultiplier(){

        return chargeMultiplier;

    }

    /**
     * Getter method to retrieve the player's currently equipped armor object
     * @return the player's current armor
     */
    public Armor getArmor(){

        return armor;

    }

    /**
     * Getter method to retrieve the player's currently equipped weapon object
     * @return the player's current weapon
     */
    public Weapon getWeapon(){

        return weapon;

    }

    /**
     * Getter method to retrieve the player's currently equipped consumable object
     * @return the player's current consumable
     */
    public Consumable getConsumable(){

        return consumable;

    }

    /**
     * Getter method to determine if the player is currently charging their attack
     * @return player's current state of charging (true or false)
     */
    public boolean getIsCharging(){

        return isCharging;

    }

    /**
     * Getter method to determine if the player is currently defending
     * @return player's current state of defending (true or false)
     */
    public boolean getIsDefending(){

        return isDefending;

    }

    /**
     * Getter method to determine if the player has consumed a consumable with temporary effects
     * @return player's current state of consumable use (true or false)
     */
    public boolean getHasConsumeTemp(){

        return hasConsumeTemp;

    }

    /**
     * Getter method to determine if the player is immune to damage or not
     * @return player's current state invulnerability (true or false)
     */
    public boolean getIsInvulnerable(){
        
        return isInvulnerable;

    }


    /**
     * Getter method to share the Character's input
     * @return Character's current input this turn
     */
    public char getTurnInputAction(){

        return turnInputAction;

    }

    /**
     * Getter method to share the Character's last action done
     * @return player's last action done
     */
    public String getLastCharacterAction(){

        return lastCharacterAction;

    }


    /**
     * Getter method to share the Character's times to attack
     * @return player's times to attack.
     */
    public int getTimesToAttack(){

        return timesToAttack;

    }


    //Methods for Equipping Gear

    /**
     * Equips the chosen armor object
     * @param armor armor to be equipped
     */
    public void equipArmor(Armor armor){

        //If condition to check whether the parameter object it not null
        if(armor != null){

            //Equip the armor and set new stats of the player
            this.armor = armor;
            defense += armor.getDefense();
            speed -= armor.getSpeedPenalty();

        }

    }

    /**
     * Equips the chosen weapon object
     * @param weapon weapon to be equipped
     */
    public void equipWeapon(Weapon weapon){

        //If condition to check whether the parameter object it not null
        if(weapon != null){

            //Equip the armor and set new stats of the player
            this.weapon = weapon;
            attack += weapon.getAttack();
            speed -= weapon.getSpeedPenalty();

        }

    }

    /**
     * Equips the chosen consumable object
     * @param consumable consumable to be equipped
     */
    public void equipConsumable(Consumable consumable){

        //If condition to check whether the parameter object it not null
        if(consumable != null)
            this.consumable = consumable; //Equip the consumable and set new stats of the player

    }

    /**
     * Unequips the currently equipped armor
     */
    public void unequipArmor(){

        defense = 1;
        speed += armor.getSpeedPenalty();
        armor = null;

    }

    /**
     * Unequips the currently equipped weapon
     */
    public void unequipWeapon(){

        attack = 1;
        speed += weapon.getSpeedPenalty();
        weapon = null;

    }

    /**
     * Unequips the currently equipped consumable
     */
    public void unequipConsumable(){

        consumable = null;

    }


    //Turn-based Methods

    protected abstract void think(Character target);


    protected void attack(Character target){

        for(int i = 0; i < timesToAttack; i++){

            int damage = 0; //Stores the amount of damage dont to the enemy

            if(!target.getIsInvulnerable()){

                //If condition to check whether the Character was charging at the time of their attack
                if(isCharging)
                    damage = chargeBase * chargeMultiplier;
                else
                    damage = attack;

                if(target.getIsDefending()){

                    //Ternary Operator to calculate damage whether damage was dealt or not with target defending
                    damage = (damage / 2 > 0) ? damage / 2 : 0; 
                    target.stopDefending();

                }

            }

            //Ternary Operator to calculate damage whether damage was dealt or not with target defending
            damage = (damage - target.getDefense() > 0) ? damage - target.getDefense() : 0; 
            target.setHitPoints(target.getHitPoints() - damage); //Updates the targets hitpoints after taking damage
            getLastAttackDone = damage; //Stores the damage that was done by the Character for display


        }

        if(isCharging)
            stopCharging();

    }

    protected void defend(){

        isDefending = true;

        if(isCharging)
            stopCharging();

    }

    /**
     * Method for setting the player's defending state to false
     */
    public void stopDefending(){

        //If condition checks whether Player was defending
        if(isDefending)
            isDefending = false;
        else
            System.out.printf("\nERROR: %s is already not defending!\n", name); //Error display if false

    }

    protected void charge(){

        isCharging = true;

    }

    /**
     * Method for setting the player's charging state to false
     */
    public void stopCharging(){

        //If condition checks whether Player was charging
        if(isCharging)
            isCharging = false;
        else
            System.out.printf("\nERROR: %s is already not charging!\n", name); //Error display if false

    }


    //Method allows display to get the last attack's amount
    /**
     * Method for displaying the last attack's value
     * @return the value of the latest attack
     */
    public int getLastAttackDealt(){

        return getLastAttackDone;

    }


    //Method uses the consumable equipped and gets corresponding effects for both Player and Enemy
    protected void consume(Character target){

        //Checks if there are any charges left
        if(consumable.getChargesLeft() > 0){

            consumable.useConsumable(this, target);

            consumable.useCharges(); //Decrement charge

            //Checks if Consumable is Temporary
            if(consumable.getIsTemporary())
                hasConsumeTemp = true;

        }

    }

    protected void useSkill(Character target){

        if(getWeapon() != null && getWeapon() instanceof EnchantedWeapon && 
                   ((EnchantedWeapon) getWeapon()).getWeaponSkill().getHasMetConditions())
            ((EnchantedWeapon) weapon).getWeaponSkill().activateSkill(this, target);

    }

}
