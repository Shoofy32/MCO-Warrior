import javax.swing.ImageIcon;

/**
 * Class file for the Buff class, this is a subclass of the abstract Consumable class
 * <p>
 *     Buff type consumables affect the stat values of only the holder
 * </p>
 * @see Consumable
 * @author Stefan_Martin
 */
public class Buff extends Consumable{

    /**
     * Constructor for the buff class
     * @param name name of the buff, assumes all names are valid
     * @param image image of the bomb
     * @param maxCharges amount of times the buff can be used in a battle, all values are assumed valid
     * @param statsToEffect list of stats to change for the holder
     * @param statValuesToEffect list of values to apply to the holder's stats, all values are assumed valid
     * @param affectingTurns amount of times the stat values will remain active, all values are assumed valid
     */
    public Buff(String name, ImageIcon image, int maxCharges, String[] statsToEffect, int[] statValuesToEffect, int affectingTurns){

        super(name, "Buff", image, maxCharges, true, false, statsToEffect, statValuesToEffect, affectingTurns);

    }

    /**
     * Concrete implementation of the <i>useConsumable</i> method
     * Every time a consumable is used, its remaining charges are decremented
     * If a consumable with no remaining charges is used, nothing happens
     * @param holder entity object using the consumable
     * @param target entity object targeted by the consumable
     */
    public void useConsumable(Entity holder, Entity target){

        int size = 0;

        size = getStatsToAffectHolder().length; //Gets size of Holder Array

        //For loop will iterate through the array to check the Stats stored in each index
        for(int i = 0; i < size; i++){

            //Switch statement checks the Stat stored in each index and updates the values of the corresponding Stat 
            switch(getStatsToAffectHolder()[i]){

                case "HitPoints":

                    holder.setHitPoints(holder.getHitPoints() + getStatValuesToAffectHolder()[i]); //Affect HitPoints
                    break; 

                case "Attack":

                    holder.setAttack(holder.getAttack() + getStatValuesToAffectHolder()[i]); //Affect Attack
                    break; 

                case "Defense":

                    holder.setDefense(holder.getDefense() + getStatValuesToAffectHolder()[i]); //Affect Defense
                    break; 

                case "Speed":

                    holder.setSpeed(holder.getSpeed() + getStatValuesToAffectHolder()[i]); //Affect Speed

            }

        }

    }

}
