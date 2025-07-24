/**
 * Class file for the Buff class, this is a subclass of the abstract Consumable class
 * <p>
 *     Healing type consumables restore a set HP value to the holder.
 * </p>
 * <p>
 *     This restored HP value can exceed the holder's maximum HP e.g. healing a 98 HP player for 20 HP  will set their current HP to 118 exceeding the maximum 100 HP
 * </p>
 * @see Consumable
 * @author Stefan_Martin
 */
public class Healing extends Consumable{

    /**
     * Constructor for the healing class
     * @param name name of the healing iterm, all names are assumed valid
     * @param maxCharges amount of times the healing item can be used in a battle, all values are assumed valid
     * @param healingValue amount of HP restored by the healing item, all values are assumed valid
     */
    public Healing(String name, int maxCharges, int healingValue){

        super(name, "Healing", maxCharges, true, false, new String[]{"Healing"}, new int[]{healingValue}, 0);

    }

    /**
     * Concrete implementation of the <i>useConsumable</i> method
     * Every time a consumable is used, its remaining charges are decremented
     * If a consumable with no remaining charges is used, nothing happens
     * @param holder Character object using the consumable
     * @param target Character object targeted by the consumable
     */
    public void useConsumable(Character holder, Character target){

        holder.setHitPoints(holder.getHitPoints() + getStatValuesToAffectHolder()[0]);

    }

}
