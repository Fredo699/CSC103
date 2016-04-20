package Lab4;
/* CSC103 Plane Fred Frey & TImothy Haskihns 4/20/16 */
class Plane{
   // the plane number arrived to the queue
   // should be in incrementing order
   static private int planeCount = 0; 
   // the time the plane arrived to the queue
   private int time;
   // the kind of operation the plane is doing 'L"
   // is for landing 'T' is for taking off
   private char operation;
   // plane number
   private int planeNo;
   
   // operation is the type of operation the plane is doing. If landingOrTakeOff is 'L' // it means the plane is landing, if landingOrTakeOff is 'T' it means the plane is
   // Takingoff.
   public Plane(int aTime, char landingOrTakeOff){
      time = aTime;
      operation = landingOrTakeOff;
      planeNo = ++planeCount;
   }
   
   public int getTime(){
      return time;
   }
   
   public int getPlaneNo (){
      return planeNo;
   }
   
   public char getOperation(){
      return operation;
   }
   
   private static int getPlaneCount(){
      return planeCount;
   }
}