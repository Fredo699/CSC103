import java.io.IOException;
import BooleanSource;
import Averager;
import LinkedQueue;

class RunwaySimulation{
   public static void main(String[] args){
      final int TAKEOFFTIME = 2;
      final int LANDINGTIME = 3;
      final double ARRIVALPROB = 0.0025;
      final int TOTALTIME = 30;
      
      runwaySimulate(TAKEOFFTIME,LANDINGTIME,ARRIVALPROB,TOTALTIME);
   }
   
   public static void runwaySimulate(int to_time,int l_time,double prob,int t_time){
      int next;
      int currentSecond;
      
      LinkedQueue<int> a_times = new LinkedQueue<int>();  
      BooleanSource a = new BooleanSource(prob);
      Runway rw = new Runway(to_time, l_time);
      Averager w_times = new Averager();
      
      System.out.println("The time of simulation is:\t" + t_time + " minutes");
      System.out.println(
         "The amount of time that is needed for one plane to take off is:\t"
         + to_time + " minutes");
      System.out.println(
         "The amount of time that is needed for one plane to land is :\t"
         + l_time + " minutes");
   }
}