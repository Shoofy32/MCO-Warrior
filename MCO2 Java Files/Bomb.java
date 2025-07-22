public class Bomb extends Consumable{
    
    public Bomb(String name, int maxCharges, String[] statsToEffectPlayer, int[] statValuesToEffectPlayer, 
                String[] statsToEffectEnemy, int[] statValuesToEffectEnemy,int affectingTurns){

        super(name, "Bomb", maxCharges, statsToEffectPlayer, statValuesToEffectPlayer, statsToEffectEnemy, statValuesToEffectEnemy, affectingTurns);

    }

    public void useConsumable(Character holder, Character target){

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
