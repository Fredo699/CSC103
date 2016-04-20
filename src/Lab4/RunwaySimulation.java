package Lab4;

import java.io.IOException;
import java.util.ArrayList;

class RunwaySimulation {
    public static void main(String[] args) {
        final int TAKEOFFTIME = 40;
        final int LANDINGTIME = 3;
        final int AVGLAND = 5;
        final int AVGTAKEOFF = 7;
        final int MAXLANDTIME = 9;
        final int TOTALTIME = 30;

        runwaySimulate(LANDINGTIME, TAKEOFFTIME, AVGTAKEOFF, AVGLAND, MAXLANDTIME, TOTALTIME);
    }

    /**
     * @param landing_time         the time it takes to land a plane
     * @param takeoff_time         the time it takes to launch a plane
     * @param average_takeoff_time The average amount of time between planes arriving for a landing
     * @param average_landing_time The average amount of time between planes having to take off
     * @param max_landtime         The maximum amount of time a plane can stay in the air without crashing
     * @param total_time           The total runtime of the simulation
     */

    public static void runwaySimulate(int landing_time, int takeoff_time, int average_takeoff_time, int average_landing_time, int max_landtime, int total_time) {
        LinkedQueue<Plane> landings = new LinkedQueue<Plane>();
        LinkedQueue<Plane> takeoffs = new LinkedQueue<Plane>();
        LinkedStack<String> crashed_data = new LinkedStack<String>();
        Plane temp;
        String crashed = "";

        int number_to_takeoff = 0, number_to_land = 0;

        ArrayList<Plane> landed = new ArrayList<Plane>();
        ArrayList<Plane> tookoff = new ArrayList<Plane>();

        Runway rw = new Runway(takeoff_time, landing_time);
        Averager takeoff_queue_time = new Averager();
        Averager landing_queue_time = new Averager();

        System.out.println("The time of simulation is:\t\t\t\t\t\t\t\t\t\t\t\t\t" + total_time + " minutes");
        System.out.println("The amount of time that is needed for one plane to take off is:\t\t\t\t"
                + takeoff_time + " minutes");
        System.out.println("The amount of time that is needed for one plane to land is :\t\t\t\t"
                + landing_time + " minutes");
        System.out.println("The average time between takeoffs is:\t\t\t\t\t\t\t\t\t\t" + average_takeoff_time + " minutes.");
        System.out.println("The average time between landings is:\t\t\t\t\t\t\t\t\t\t" + average_landing_time + " minutes.");
        System.out.println("The maximum time a plane can stay in the landing queue before crashing is:\t"
                + max_landtime + " minutes.");

        int time_to_next_landing = (int) (average_landing_time + (Math.random() * average_landing_time) - (average_landing_time / 2)); // Generate a time to next takeoff/landing +/- 2 min.
        int time_to_next_takeoff = (int) (average_takeoff_time + (Math.random() * average_takeoff_time) - (average_takeoff_time / 2));

        for (int min = 1; min <= total_time; min++) {
            // Handle new planes coming in/going out:
            if (time_to_next_landing == 0) {
                landings.add(new Plane(min, 'L')); // Add a new plane to the queue.
                number_to_land += 1;
                time_to_next_landing = (int) (average_landing_time + (Math.random() * average_takeoff_time) - (average_takeoff_time / 2)); // Regenerate landing time.
            }
            if (time_to_next_takeoff == 0) {
                takeoffs.add(new Plane(min, 'T')); // Add a new plane to the queue.
                number_to_takeoff += 1;
                time_to_next_takeoff = (int) (average_takeoff_time + (Math.random() * average_takeoff_time) - (average_takeoff_time / 2)); // Regenerate takeoff time.
            }

            // Handle crashing planes
            while (!rw.isBusy() && !landings.isEmpty()) {
                temp = landings.remove();
                if ((min - temp.getTime()) > max_landtime){
                    crashed_data.push("Plane " + temp.getPlaneNo() + " crashed at minute " + min);
                    System.out.println("Crashed");
                }else{
                    rw.startUsingRunway('L');
                    landing_queue_time.addNumber(min - temp.getTime());
                    landed.add(temp);
                }
            }

            // Handle planes waiting to land/takeoff:
            if (!rw.isBusy() && !takeoffs.isEmpty()) {
                rw.startUsingRunway('T');
                temp = takeoffs.remove();
                takeoff_queue_time.addNumber(min - temp.getTime());
                tookoff.add(temp);
            }

            time_to_next_landing -= 1;
            time_to_next_takeoff -= 1;
            rw.reduceRemainingTime();
        }

        System.out.println("\n\n---Results---\nNumber of planes that came to take off:\t\t\t\t" + number_to_takeoff);
        System.out.println("Number of planes that came to land:\t\t\t\t\t" + number_to_land);
        System.out.println("Number of planes that crashed:\t\t\t\t\t\t" + crashed_data.size());
        System.out.println("Number of planes that successfully took off:\t\t" + tookoff.size());
        System.out.println("Planes that successfully landed:\t\t\t\t\t" + landed.size());
        System.out.println("Planes still in queues:\t\t\t\t\t\t\t\t" + ((number_to_land + number_to_takeoff) - (tookoff.size() + landed.size() + crashed_data.size())));
        System.out.println("Average time spent in takeoff queue:\t\t\t\t" + takeoff_queue_time.average() + " minutes.");
        System.out.println("Average time spent in landing queue:\t\t\t\t" + landing_queue_time.average() + " minutes.");
        System.out.println("\nCrash Data");
        for(int i = 0; i < crashed_data.size();++i)
            System.out.println(crashed_data.pop());
    }
}