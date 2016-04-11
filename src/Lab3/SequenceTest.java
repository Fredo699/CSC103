package Lab3;
/**
 * CSC103 SequenceTest
 * 3/20/2016
 *
 * SequenceTest Class
 *
 * @author Fred Frey & Timothy Haskins
 */

import java.io.IOException;

public class SequenceTest {
    public DoubleLinkedSeq sq[] = new DoubleLinkedSeq[3];

    /**
     * Default Constructor
     */
    public SequenceTest(){
        super();
    }

    /**
     * Take commands as line of String
     * @param st
     */
    public void menu(String st){
        System.out.println(st);

        String input[] = st.split("([\\s]*-[\\s]*)");
        String args[] = null;
        int cmd = 0;
        int sq_used = 0;
        if(input.length >= 1){
            if(input.length > 1) args = input[1].split("(,)");
            cmd = Integer.parseInt(input[0]);
        }


        try{
            switch(cmd){
                case 1:
                    System.out.println("Create a Sequence\n" +
                            "-------------------------");
                    if(null != sq[sq_used]) sq_used = 1;
                    createSequence(args, sq_used);
                    break;
                case 2:
                    System.out.println("Delete a Number\n" +
                            "-------------------------");
                    sq_used = 0;
                    if(findInList(Double.parseDouble(args[0]), sq[sq_used]) > 0) sq[0].removeCurrent();
                    break;
                case 3:
                    System.out.println("Delete the first number from the sequence\n" +
                            "-------------------------");
                    sq_used = 0;
                    sq[sq_used].removeFront();
                    break;
                case 4:
                    System.out.println("Add a number before another number\n" +
                            "-------------------------");
                    sq_used = 0;
                    sq[sq_used].setCurrent(Integer.parseInt(args[0])-1);
                    sq[sq_used].addBefore(Double.parseDouble(args[1]));
                    break;
                case 5:
                    System.out.println("Add a number after a number\n" +
                            "-------------------------");
                    sq_used = 0;
                    sq[sq_used].setCurrent(Integer.parseInt(args[0])-1);
                    sq[sq_used].addAfter(Double.parseDouble(args[1]));
                    break;
                case 6:
                    System.out.println("Add a number to the end of the sequence\n" +
                            "-------------------------");
                    sq_used = 0;
                    sq[sq_used].addEnd(Double.parseDouble(args[0]));
                    break;
                case 7:
                    System.out.println("Display a number at a certain index\n" +
                            "-------------------------");
                    sq_used = 0;
                    sq[sq_used].setCurrent(Integer.parseInt(args[0]) - 1);
                    break;
                case 8:
                    System.out.println("Display the last element in the sequence\n" +
                            "-------------------------");
                    sq_used = 0;
                    sq[sq_used].currentLast();
                    break;
                case 9:
                    System.out.println("Replace a number with another number\n" +
                            "-------------------------");
                    sq_used = 0;
                    Node temp = sq[sq_used].retrieveElement(0);
                    while(temp != null){
                        if (temp.getData() == Double.parseDouble(args[0]))
                        	{
                        	temp.setData(Double.parseDouble(args[1]));
                        	//break;
                        	}
                        temp = temp.getLink();
                    }
                    break;
                case 10:
                    System.out.println("Append another sequence to the first sequence\n" +
                            "-------------------------");
                    sq_used = 0;
                    sq[0].addAll(sq[1]);
                    break;
                case 11:
                    System.out.println("Create a clone sequence\n" +
                            "-------------------------");
                    sq_used = 0;
                    sq[2] = (DoubleLinkedSeq) sq[sq_used].clone();
                    break;
                case 12:
                    System.out.println("Print the sequence\n" +
                            "-------------------------");
                    sq_used = Integer.parseInt(args[0]) - 1;
                    break;
                case 13:
                    System.out.println("Quit\n" +
                            "-------------------------");
                    break;
                default:
                    System.out.println("CommandIndex Error: " + cmd);
            }
            System.out.println(listToString(sq[sq_used]));
        }catch(IOException e){
            System.out.println(e.getMessage() + "\n");
        }
    }

    /**
     * Populate Sequence with given set
     * @param args
     *  arguments
     * @param list
     *  DoubleLinkedSeq to be created from arguments
     * @throws IOException
     *  if the arguments list is empty
     */
    public void createSequence(String[] args, int list_index) throws IOException{
        if(null != args){
            sq[list_index] = new DoubleLinkedSeq();
            sq[list_index].addFront(Double.parseDouble(args[0]));
            for(int i = 1; i < args.length; ++i) sq[list_index].addAfter(Double.parseDouble(args[i]));
        }else throw new IOException("Exception - no data was created");
    }

    /**
     * Return the i-th position in list where element is if it exists, -1 if not
     * @param element
     *  Value to look for
     * @param list
     *  List to look in
     * @return
     *  Position of element in list or -1 if it doesn't exist
     */
    public int findInList(double element, DoubleLinkedSeq list){
        if(null == list) return -1;
        int position = 1,
            n = list.size();
        try{
            list.start();
            while(list.getCurrent() != element && list.isCurrent() && n > position++) list.advance();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return (list.getCurrent() == element)?position:-1;
    }

    /**
     * Returns the details of the list
     * @param list
     *  List from which to get details
     * @return
     *  Details of List
     */
    public String listToString(DoubleLinkedSeq list){
        String desc;
        if(null != list){
            double temp = list.getCurrent();
            int n = list.size();
            desc = "The Sequence:\t";

            list.start();

            if(list.isCurrent()){
                while(0 < n){
                    desc += list.getCurrent();
                    desc += (1 == n--)?"\n":",";
                    list.advance();
                }
                list.setCurrent(findInList(temp, list));
                desc += "Cursor on: " + list.getCurrent() + "\n";
            }else desc += "Blank sequence\n";
        }else desc = "";
        return desc;
    }
}
