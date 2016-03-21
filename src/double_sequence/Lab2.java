package double_sequence;

/**
 * CSC103 Lab2 SequenceTest
 * 2/10/2016
 *
 * Lab2 Class (Main)
 *
 * @author Timothy Haskins
 */

import java.io.FileReader;
import java.util.Scanner;

public class Lab2{
    /**
     * Read in commands from input.txt, feed lines to SequenceTest
     * @param args
     */
    public static void main(String[] args){
        FileReader file_in;
        Scanner file_scan;
        SequenceTest test = new SequenceTest();
        String line = "";

        try{
            file_in = new FileReader("input.txt");
            file_scan = new Scanner(file_in);

            while(file_scan.hasNextLine()){
                line = file_scan.nextLine();
                test.menu(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}