public class Healing extends Consumable{
    
    public Healing(String name, int maxCharges, int healingValue){

        super(name, "Healing", maxCharges, true, false, new String[]{"Healing"}, new int[]{healingValue}, 0);

    }


    public void useConsumable(Character holder, Character target){

        holder.setHitPoints(holder.getHitPoints() + getStatValuesToAffectHolder()[0]);

    }

}
