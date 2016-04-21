package Lab5;

import java.io.FileReader;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * @author Tim Haskins
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
        String input[] = st.split("\\s?(-|–)\\s?");
        String args, tmp_str_array[];
        Employee temp_employee;
        int cmd = Integer.parseInt(input[0]);

        if (input.length > 1) {
            args = input[1];
            switch (cmd) {
                case 1:
                    loadFromFile(args);
                    break;
                case 2:
                    tmp_str_array = args.split("\\s+");
                    employeeTreeBag.add(new Employee(
                            Integer.parseInt(tmp_str_array[0]), tmp_str_array[1],
                            tmp_str_array[2], Double.parseDouble(tmp_str_array[3])));
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    tmp_str_array = args.split("\\s+");
                    for (String param : tmp_str_array)
                        System.out.println(param);
                    break;
                case 6:
                    break;
                default:
            }
            System.out.println("employeeTreeBag" + employeeTreeBag);
        }
    }

    public void loadFromFile(String file_name) {
        FileReader file_in;
        Scanner file_scan;
        String line;
        Employee tmp_employee;
        String[] tmp_str_array;

        try {
            file_in = new FileReader(file_name);
            file_scan = new Scanner(file_in);

            while (file_scan.hasNextLine()) {
                line = file_scan.nextLine();
                tmp_str_array = line.split("\\s+");
                tmp_employee = new Employee(
                        Integer.parseInt(tmp_str_array[0]), tmp_str_array[1],
                        tmp_str_array[2], Double.parseDouble(tmp_str_array[3]));
                System.out.println(tmp_employee.toString()+"\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
