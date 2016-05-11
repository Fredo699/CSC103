package Lab5;

/**
 * @author Tim Haskins & Fred Frey
 * @created 4/20/16
 */
@SuppressWarnings("rawtypes")
public class Employee implements Cloneable, Comparable {
    private int acctID;
    private String firstName;
    private String lastName;
    private double salary;

    public Employee(int id, String f_name, String l_name, double amount) {
        acctID = id;
        lastName = l_name;
        firstName = f_name;
        salary = amount;
    }

    public Employee(int id) {
        acctID = id;
    }

    public int getId() {
        return acctID;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double amount) {
        salary = amount;
    }

    // a method to print information about the employee
    public String toString() {
        return String.format("%-4d\t%-9s\t%-9s\t%6.2f",acctID,lastName,firstName,salary);
    }

    // a method to compare if object that invokes this method is greater then, equal or less
    // then obj.
    // the method returns an integer that is less than, equal to, or greater then zero if the
    //executing object is less than, equal to, or greater than the parameter, respectively.
    public int compareTo(Object obj) {
        Employee temp = (Employee) obj;
        if(temp.getId() < acctID) return 1;
        else if(temp.getId() > acctID) return -1;
        return 0;
    }
}
