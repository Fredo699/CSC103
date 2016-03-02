package haskins_quadratic;

/**
 * CSC103 Lab1 Quadratic
 * 3/2/2016
 *
 * Lab1 class
 *
 * @author Fred Frey
 */

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Lab1 {
	public static void main(String[] args) throws IOException{
		FileReader file_in = null;
		Scanner file_scan = null;
		Scanner input = new Scanner(System.in);
		
		ArrayList<double[]> input_coef = new ArrayList<double[]>(); // Every element is a line, every element of that is a number from the input file
		String next_line = ""; // This has to be non-zero length for the while loop to work.
		
		System.out.println("Filepath: ");
		String filepath = input.nextLine();
		try{
			file_in = new FileReader(filepath);
			file_scan = new Scanner(file_in);
			
			String[] next_line_string_array;
			double[] next_line_array;
			
			while(file_scan.hasNextLine()){
				next_line = file_scan.nextLine();
				next_line = next_line.replaceAll("\\s+", " "); // Collapse consecutive spaces...
				next_line_string_array = next_line.split(" ");
				next_line_array = new double[next_line_string_array.length];
				for (int i = 0; i < next_line_array.length; i++)
					next_line_array[i] = Double.parseDouble(next_line_string_array[i]);
				if (next_line_array.length == 8)
					input_coef.add(next_line_array);
				}
		}catch (Exception e){
			System.out.println("there was an error reading the file.");
			e.printStackTrace();
		}
		
		QuadTest tester;
		Quadratic q1, q2;
		
		/*
		 * I found the instructions confusing...
		 * "Test your program with the following data:( the data will check for no real roots, every value of X is a real root, one root, two roots )..."
		 * What is meant by "Test your program"? I hope this is satisfactory...*/
		for (int i = 0; i < input_coef.size(); i++){
			System.out.println("\nStarting tests for line " + i + "...\n");
			q1 = new Quadratic(input_coef.get(i)[0], input_coef.get(i)[1], input_coef.get(i)[2]);
			q2 = new Quadratic(input_coef.get(i)[5], input_coef.get(i)[6], input_coef.get(i)[7]);
			
			System.out.println("q1 = " + q1);
			System.out.println("q2 = " + q2);
			
			tester = new QuadTest(q1, q2);
			System.out.println("At " + input_coef.get(i)[3] + " q1 is " + q1.evalExpression(input_coef.get(i)[3]) + " and q2 is " + 
					q2.evalExpression(input_coef.get(i)[3]));
			
			System.out.println("\nScaling by " + input_coef.get(i)[4] + "...");
			System.out.println("q1 yields: " + Quadratic.scale(input_coef.get(i)[4], q1));
			System.out.println("q2 yields: " + Quadratic.scale(input_coef.get(i)[4], q2));
		}
	}

}
