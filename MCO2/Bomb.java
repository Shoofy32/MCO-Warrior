import javax.swing.ImageIcon;

/**
 * Class file for the Bomb class, this is a subclass of the abstract Consumable class
 * <p>
 *     Bomb type consumables affect the stat values of both the holder and target
 * </p>
 * @see Consumable
 * @author Stefan_Martin
 */
public class Bomb extends Consumable{

    /**
     * Constructor for the bomb class
     * @param name name of the bomb, assumes all names are valid
     * @param image image of the bomb
     * @param maxCharges amount of times the bomb can be used in a battle, all values are assumed valid
     * @param statsToEffectPlayer list of stats to change for the holder
     * @param statValuesToEffectPlayer list of values to apply to the holder's stats, all values are assumed valid
     * @param statsToEffectEnemy list of stats to change for the target
     * @param statValuesToEffectEnemy list of values to apply to the target's stats, all values are assumed valid
     * @param affectingTurns amount of times the stat values will remain active, all values are assumed valid
     */
    public Bomb(String name, ImageIcon image, int maxCharges, String[] statsToEffectPlayer, int[] statValuesToEffectPlayer, 
                String[] statsToEffectEnemy, int[] statValuesToEffectEnemy,int affectingTurns){

        super(name, "Bomb", image, maxCharges, statsToEffectPlayer, statValuesToEffectPlayer, statsToEffectEnemy, statValuesToEffectEnemy, affectingTurns);

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
        
        if(getAffectsHolder()){

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
        
        if(getAffectsTarget()){

            size = getStatsToAffectTarget().length; //Gets size of Target Array

            //For loop will iterate through the array to check the Stats stored in each index
            for(int i = 0; i < size; i++){

                //Switch statement checks the Stat stored in each index and updates the values of the corresponding Stat 
                switch(getStatsToAffectTarget()[i]){

                    case "HitPoints":

                        target.setHitPoints(target.getHitPoints() + getStatValuesToAffectTarget()[i]); //Affect HitPoints
                        break; 

                    case "Attack":

                        target.setAttack(target.getAttack() + getStatValuesToAffectTarget()[i]); //Affect Attack
                        break; 

                    case "Defense":

                        target.setDefense(target.getDefense() + getStatValuesToAffectTarget()[i]); //Affect Defense
                        break; 

                    case "Speed":

                        target.setSpeed(target.getSpeed() + getStatValuesToAffectTarget()[i]); //Affect Speed

                }

            }

        }

    }

}
