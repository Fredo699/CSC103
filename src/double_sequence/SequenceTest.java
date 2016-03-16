/**
 * CSC103 Lab2 SequenceTest
 * 2/10/2016
 *
 * SequenceTest Class
 *
 * @author Timothy Haskins
 */

import java.io.IOException;

public class SequenceTest {
    public DoubleArraySeq sq1, sq2, sq3;

    /**
     * Default Constructor
     */
    public SequenceTest(){
        super();
    }

    /**
     * 
     * @param st
     */
    public void menu(String st){
        String input[] = st.split("(\\s-\\s|\\s-|-\\s|-|\\s|,)");
        String args[] = new String[input.length-1];
        int cmd = Integer.parseInt(input[0]);

        System.out.print("Input line:  " + cmd + " - ");

        for(int i = 0; i < args.length; i++){
            args[i] = input[i+1];
            System.out.print(args[i]);
            if(i < args.length - 1) System.out.print(",");
        }
        System.out.println();

        try{
            switch(cmd){
                case 1:
                    System.out.println("Create a Sequence\n" +
                            "-------------------------");
                    createSequence(args, sq1);
                    break;
                case 2:
                    System.out.println("Delete a Number\n" +
                            "-------------------------");
                    sq1.setCurrent(sq1.retrieveElement(Double.parseDouble(args[0])));
                    sq1.removeCurrent();
                    break;
                case 3:
                    System.out.println("Delete the first number from the sequence\n" +
                            "-------------------------");
                    sq1.removeFront();
                    break;
                case 4:
                    System.out.println("Add a number before another number\n" +
                            "-------------------------");
                    sq1.setCurrent(Integer.parseInt(args[1]));
                    sq1.addBefore(Double.parseDouble(args[1]));
                    break;
                case 5:
                    System.out.println("Add a number after a number\n" +
                            "-------------------------");
                    sq1.setCurrent(Integer.parseInt(args[1]));
                    sq1.addAfter(Double.parseDouble(args[1]));
                    break;
                case 6:
                    System.out.println("Add a number to the end of the sequence\n" +
                            "-------------------------");
                    sq1.addEnd(Double.parseDouble(args[0]));
                    break;
                case 7:
                    System.out.println("Display a number at a certain index\n" +
                            "-------------------------");
                        sq1.setCurrent(sq1.retrieveElement(Double.parseDouble(args[0])));
                    break;
                case 8:
                    System.out.println("Display the last element in the sequence\n" +
                            "-------------------------");
                    sq1.currentLast();
                    break;
                case 9:
                    System.out.println("Replace a number with another number\n" +
                            "-------------------------");
                    sq1.setCurrent(sq1.retrieveElement(Double.parseDouble(args[0])));
                    sq1.addBefore(Double.parseDouble(args[1]));
                    sq1.advance();
                    sq1.removeCurrent();
                    break;
                case 10:
                    System.out.println("Append another sequence to the first sequence\n" +
                            "-------------------------");
                    sq2 = new DoubleArraySeq(args.length);
                    createSequence(args, sq2);
                    sq1.addAll(sq2);
                    break;
                case 11:
                    System.out.println("Create a clone sequence\n" +
                            "-------------------------");
                    sq2 = sq1.clone();
                    break;
                case 12:
                    System.out.println("Print the sequence\n" +
                            "-------------------------");
                    break;
                case 13:
                    System.out.println("Quit\n" +
                            "-------------------------");
                    break;
            }
            System.out.println(sq1.toString());
        }catch(IOException e){
            System.out.println(e.getMessage() + "\n");
        }
    }

    public void createSequence(String[] args, DoubleArraySeq data_member) throws IOException{
        if(args.length <= 0) throw new IOException("Exception - no data was created");
        data_member = new DoubleArraySeq(args.length);
        for(String arg : args){
            System.out.println(arg);
            data_member.addAfter(Double.parseDouble(arg));
        }
    }
}
