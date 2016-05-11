package Lab5;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * @author Tim Haskins & Fred Frey
 * @created 4/20/16
 */
public class Company {
    private TreeBag<Employee> employeeTreeBag;

    public Company() {
        employeeTreeBag = new TreeBag<Employee>();
    }

    /**
     * Take commands as line of String
     *
     * @param st
     */
    public void menu(String st) {
        System.out.println(st);
        String input[] = st.split("\\s?-\\s?");
        String args = null, tmp_str_array[] = null;
        Employee temp_employee;
        int cmd = Integer.parseInt(input[0]);

        if (input.length > 1) {
            args = input[1];
            tmp_str_array = args.split("\\s+"); // Probably better to do this out here.
        }
        try{switch (cmd) {
                case 1:
                    loadFromFile(args);
                    break;
                case 2:
                    employeeTreeBag.add(new Employee(
                            Integer.parseInt(tmp_str_array[0]), tmp_str_array[1],
                            tmp_str_array[2], Double.parseDouble(tmp_str_array[3])));
                    break;
                case 3:
                	employeeTreeBag.remove(new Employee(Integer.parseInt(tmp_str_array[0])));
                    break;
                case 4:
                	temp_employee = (Employee) employeeTreeBag.retrieve(new Employee(Integer.parseInt(tmp_str_array[0])));
                	System.out.println(temp_employee);
                	System.out.println(employeeTreeBag.display());
                    break;
                case 5:
                    temp_employee = (Employee) employeeTreeBag.retrieve(new Employee(Integer.parseInt(tmp_str_array[0])));
                    temp_employee.setSalary(Double.parseDouble(tmp_str_array[1])); // don't have to add, because this changes the *actual* node object.
                    System.out.println(employeeTreeBag.display());
                    break;
                case 6:
                	System.out.println(employeeTreeBag.display());
                    break;
                case 7:
                	writeOutput();
                	System.exit(0);
                	break;
                default:
                	System.out.println("Unrecognized command: '" + cmd + "'.");
            }
        }catch (Exception e){
        	e.printStackTrace();
        }
    }

    public void loadFromFile(String file_name) {
        FileReader file_in;
        Scanner file_scan;
        String line, line_split[];
        Employee tmp_employee;

        try {
            file_in = new FileReader(file_name);
            file_scan = new Scanner(file_in);

            while (file_scan.hasNextLine()) {
                line = file_scan.nextLine();
                line_split = line.split("\\s+");
                tmp_employee = new Employee(
                        Integer.parseInt(line_split[0]), line_split[1],
                        line_split[2], Double.parseDouble(line_split[3]));
                employeeTreeBag.add(tmp_employee);
            }
        } catch (Exception e) {
        	System.out.println("Failed to load file.");
            e.printStackTrace();
        }
    }
    
    public void writeOutput(){
    	File file;
    	FileWriter file_out;
    	try{
    		file = new File("outEmployee.txt");
    		file.createNewFile();
    		file_out = new FileWriter(file);
    		file_out.write(employeeTreeBag.display_preorder());
    		file_out.close();
    		
    	} catch (Exception e){
    		e.printStackTrace();
    	}
    }
    
}
