package Lab3;
/**
 * CSC103 Lab3
 * 3/20/2016
 *
 * Lab2 Class (Main)
 *
 * @author Fred Frey & Timothy Haskins
 */

import java.io.FileReader;
import java.util.Scanner;

public class Lab3{
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