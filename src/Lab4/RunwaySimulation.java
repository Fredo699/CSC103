package Lab4;

import java.io.IOException;
import java.util.ArrayList;

class RunwaySimulation{
   public static void main(String[] args){
      final int TAKEOFFTIME = 20;
      final int LANDINGTIME = 3;
      final int AVGLAND = 5;
      final int AVGTAKEOFF = 7;
      final int MAXLANDTIME = 9;
      final int TOTALTIME = 300;
      
      runwaySimulate(LANDINGTIME, TAKEOFFTIME, AVGTAKEOFF, AVGLAND, MAXLANDTIME, TOTALTIME);
   }
   
   /**
    * 
    * @param landing_time the time it takes to land a plane
    * @param takeoff_time the time it takes to launch a plane
    * @param average_takeoff_time The average amount of time between planes arriving for a landing
    * @param average_landing_time The average amount of time between planes having to take off
    * @param max_landtime The maximum amount of time a plane can stay in the air without crashing
    * @param total_time The total runtime of the simulation
    */
   
   public static void runwaySimulate(int landing_time, int takeoff_time, int average_takeoff_time, int average_landing_time, int max_landtime, int total_time){      
      LinkedQueue<Plane> landings = new LinkedQueue<Plane>();
      LinkedQueue<Plane> takeoffs = new LinkedQueue<Plane>();
	  LinkedStack<Plane> crashed = new LinkedStack<Plane>();
	  
	  LinkedQueue<Plane> not_crashed; // Initialized and used inside of for-loop.
	  Plane next_lander;
	  
      int number_to_takeoff = 0, number_to_land = 0;
      
      ArrayList<Plane> landed = new ArrayList<Plane>();
      ArrayList<Plane> tookoff = new ArrayList<Plane>();
      
      Runway rw = new Runway(takeoff_time, landing_time);
      Averager takeoff_queue_time = new Averager();
      Averager landing_queue_time = new Averager();
      
      System.out.println("The time of simulation is:\t" + total_time + " minutes");
      System.out.println(
         "The amount of time that is needed for one plane to take off is:\t"
         + takeoff_time + " minutes");
      System.out.println(
         "The amount of time that is needed for one plane to land is :\t"
         + landing_time + " minutes");
      System.out.println("The average time between takeoffs is:\t" + average_takeoff_time + " minutes.");
      System.out.println("The average time between landings is:\t" + average_landing_time + " minutes.");

      int time_to_next_landing = (int) (average_landing_time + (Math.random() * average_landing_time) - (average_landing_time / 2)); // Generate a time to next takeoff/landing +/- 2 min.
      int time_to_next_takeoff = (int) (average_takeoff_time + (Math.random() * average_takeoff_time) - (average_takeoff_time / 2));

      for(int min = 1; min <= total_time; min++){
    	  
    	  // Handle new planes coming in/going out:
    	  if(time_to_next_landing == 0){
    		if (!rw.isBusy()){
    			rw.startUsingRunway('L'); // If the runway isn't busy, land immediately.
    			landed.add(new Plane(min, 'L'));
    		}
    		else
    			landings.add(new Plane(min, 'L')); // Add a new plane to the queue.
    		number_to_land += 1;
    		time_to_next_landing = (int) (average_landing_time + (Math.random() * average_takeoff_time) - (average_takeoff_time / 2)); // Regenerate landing time.
    	  }
    	  
    	  if(time_to_next_takeoff == 0){
      		if (!rw.isBusy() && landings.isEmpty()){
          		rw.startUsingRunway('T'); // If the runway isn't busy, and there are no planes waiting to land, takeoff immediately
          		tookoff.add(new Plane(min, 'T'));
      		}
      		else
      			takeoffs.add(new Plane(min, 'T')); // Add a new plane to the queue.
      		number_to_takeoff += 1;
      		time_to_next_takeoff = (int) (average_takeoff_time + (Math.random() * average_takeoff_time) - (average_takeoff_time / 2)); // Regenerate takeoff time.
      	  }
    	  time_to_next_landing -= 1;
    	  time_to_next_takeoff -= 1;
    	  
    	  // Handle crashing planes
    	  not_crashed = new LinkedQueue<Plane>();
    	  while(!landings.isEmpty()){
    		  next_lander = landings.remove();
    		  if ((min - next_lander.getTime()) > max_landtime)
    			  crashed.push(next_lander);
    		  else
    			  not_crashed.add(next_lander);
    	  }
    	  
    	  landings = not_crashed;
    	  
    	  // Handle planes waiting to land/takeoff:
    	  if (!rw.isBusy()){
    		  if (!landings.isEmpty()){
    			  rw.startUsingRunway('L');
    			  Plane now_landing = landings.remove();
    			  
    			  landing_queue_time.addNumber(min - now_landing.getTime());
    			  
    			  landed.add(now_landing);
    		  }
    		  else if (!takeoffs.isEmpty()){
    			  rw.startUsingRunway('T');
    			  Plane now_taking_off = takeoffs.remove();
    			  takeoff_queue_time.addNumber(min - now_taking_off.getTime());
    			  tookoff.add(now_taking_off);
    		  }
    	  }
    	  
    	  rw.reduceRemainingTime();
      }
      
      System.out.println("\n\n---Results---\nNumber of planes that came to take off: " + number_to_takeoff);
	  System.out.println("Number of planes that came to land: " + number_to_land);
	  System.out.println("Number of planes that crashed: " + crashed.size());
	  System.out.println("Number of planes that successfully took off: " + tookoff.size());
	  System.out.println("Planes that successfully landed: " + landed.size());
	  System.out.println("Number lost to sky pirates: " + ((number_to_land + number_to_takeoff) - (tookoff.size() + landed.size() + crashed.size())));
	  System.out.println("Average time spent in takeoff queue: " + takeoff_queue_time.average() + " minutes.");
	  System.out.println("Average time spent in landing queue: " + landing_queue_time.average() + " minutes.");
   }
}