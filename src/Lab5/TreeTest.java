package Lab5;

/**
 * @author Tim Haskins
 * @created 4/20/16
 */
public class TreeTest {
    public static void main(String[] args) {
        Company company = new Company();
        System.out.println("create the tree from an input file Employee.txt");
        System.out.println("-----------------");
        company.menu("1 - Employee.txt");
        System.out.println();
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("add an employee to the tree");
        System.out.println("---------------------------------");
        company.menu("2 - 5290 George Truman 16110.68");
        System.out.println();
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("remove an employee from the tree");
        System.out.println("---------------------------------------");
        company.menu("3 - 4892");
        System.out.println();
        System.out.println("----------------------------------------------------------------------------");
        System.out.println();
        System.out.println("retrieve an employee from the tree and print the employee record");
        System.out.println("-------------------------------------------------------------------------------");
        company.menu("4 - 3924");
        System.out.println();
        System.out.println("----------------------------------------------------------------------------");
        System.out.println();
        System.out.println("update an employee from the tree and print the new ");
        System.out.println("---------------------------------------------------------------");
        company.menu("5 - 3924 20000.00");
        System.out.println();
        System.out.println("----------------------------------------------------------------------------");
        System.out.println();
        System.out.println("display the tree ");
        System.out.println("--------------------");
        company.menu("6");
        System.out.println("----------------------------------------------------------------------------");
        System.out.println();
        System.out.println();
    }
}