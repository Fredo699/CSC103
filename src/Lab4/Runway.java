package Lab4;

class Runway{
   private int timeForLanding;
   private int timeForTakeoff;
   private int runwayTimeLeft;
   private char operation; // operation can be: I � Idle, L-Landing, T-takeoff
   
   //set the time for landing, time for takeoff, and the operation to idle.
   public Runway(int time_takeoff, int time_landing){
      timeForLanding = time_landing;
      timeForTakeoff = time_takeoff;
      operation = 'I';
      runwayTimeLeft = 0;
   }
   
   public boolean isBusy(){
      return (operation != 'I');
   }
   
   public void reduceRemainingTime(){
      if(runwayTimeLeft > 0) 
         --runwayTimeLeft;
      if(runwayTimeLeft == 0)
    	  operation = 'I';
   }
   
   // if typeOfUse is 'T' - then the operation is take off and set the runway time left
   // to the time it takes for take off.
   // if typeOfUse is 'L' - then the operation is landing and set the runway time left
   // to the time it takes for landing
   // if typrofUse is �I� � then the runway is idle, set the runway time left to zero
   public void startUsingRunway(char typeOfUse){
      switch(typeOfUse){
         case 'T':
            runwayTimeLeft = timeForTakeoff;
            break;
         case 'L':
            runwayTimeLeft = timeForLanding;
            break;
         case 'I':
            runwayTimeLeft = 0;
            break;
      }
      operation = typeOfUse;
   }
   
   // returns the type of operation the runway is used for.
   // returns 'L' if the runway is used for is landing.
   // returns 'T' if the runway is used for taking off.
   // returns �I, if the runway is idle
   public char kindOfOperation(){
      return operation;
   }
}