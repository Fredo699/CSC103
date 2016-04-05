package Lab3;
/**
 * CSC103 Lab2 SequenceTest
 * 2/10/2016
 *
 * SequenceTest Class
 *
 * @author Timothy Haskins
 */

import double_sequence.DoubleArraySeq;

import java.io.IOException;

public class SequenceTest {
    public DoubleLinkedSeq sq[] = {
            new DoubleLinkedSeq(), new DoubleLinkedSeq()
    };

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
        int cmd = 0, sq_used = 0;
        if(input.length >= 1){
            if(input.length > 1) args = input[1].split("(,)");
            cmd = Integer.parseInt(input[0]);
        }


        try{
            switch(cmd){
                case 1:
                    System.out.println("Create a Sequence\n" +
                            "-------------------------");
                    if(sq[0].size() == 0){
                        createSequence(args, 0);
                        sq_used = 0;
                    }else{
                        createSequence(args, 1);
                        sq_used = 1;
                    }
                    break;
                case 2:
                    System.out.println("Delete a Number\n" +
                            "-------------------------");
                    sq[0].setCurrent(Integer.parseInt(args[0]));
                    sq[0].removeCurrent();
                    sq_used = 0;
                    break;
                case 3:
                    System.out.println("Delete the first number from the sequence\n" +
                            "-------------------------");
                    sq[0].removeFront();
                    sq_used = 0;
                    break;
                case 4:
                    System.out.println("Add a number before another number\n" +
                            "-------------------------");
                    sq[0].setCurrent(Integer.parseInt(args[0])-1);
                    sq[0].addBefore(Double.parseDouble(args[1]));
                    sq_used = 0;
                    break;
                case 5:
                    System.out.println("Add a number after a number\n" +
                            "-------------------------");
                    sq[0].setCurrent(Integer.parseInt(args[0])-1);
                    sq[0].addAfter(Double.parseDouble(args[1]));
                    sq_used = 0;
                    break;
                case 6:
                    System.out.println("Add a number to the end of the sequence\n" +
                            "-------------------------");
                    sq[0].addEnd(Double.parseDouble(args[0]));
                    sq_used = 0;
                    break;
                case 7:
                    System.out.println("Display a number at a certain index\n" +
                            "-------------------------");
                    sq[0].setCurrent(Integer.parseInt(args[0]) - 1);
                    sq_used = 0;
                    break;
                case 8:
                    System.out.println("Display the last element in the sequence\n" +
                            "-------------------------");
                    sq[0].currentLast();
                    sq_used = 0;
                    break;
                case 9:
                    System.out.println("Replace a number with another number\n" +
                            "-------------------------");
                    sq[0].setCurrent(Integer.parseInt(args[0]));
                    sq[0].addBefore(Double.parseDouble(args[1]));
                    sq[0].advance();
                    sq[0].removeCurrent();
                    sq_used = 0;
                    break;
                case 10:
                    System.out.println("Append another sequence to the first sequence\n" +
                            "-------------------------");
                    sq[0].addAll(sq[1]);
                    sq_used = 0;
                    break;
                case 11:
                    System.out.println("Create a clone sequence\n" +
                            "-------------------------");
                    sq[1] = sq[0].clone();
                    sq_used = 1;
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
            System.out.println(sq[sq_used].toString());
        }catch(IOException e){
            System.out.println(e.getMessage() + "\n");
        }
    }

    /**
     * Populate Sequence with given set
     * @param args
     * @param data_member
     * @throws IOException
     */
    public void createSequence(String[] args, int data_member) throws IOException{
        if(args == null) throw new IOException("Exception - no data was created");
        double temp[] = new double[args.length];
        for(int i = 0; i < args.length; i++) temp[i] = Double.parseDouble(args[i]);
        sq[data_member] = new DoubleLinkedSeq(temp);
    }
}
