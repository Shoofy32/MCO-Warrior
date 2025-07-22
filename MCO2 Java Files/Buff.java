public class Buff extends Consumable{
    


    public Buff(String name, int maxCharges, String[] statsToEffect, int[] statValuesToEffect, int affectingTurns){

        super(name, "Buff", maxCharges, true, false, statsToEffect, statValuesToEffect, affectingTurns);

    }

    public void useConsumable(Character holder, Character target){

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
