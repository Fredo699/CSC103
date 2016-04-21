package Lab5;

/**
 * @author Tim Haskins
 * @created 4/20/16
 */
public class Employee implements Cloneable, Comparable {
    private int acctID;
    private String firstName;
    private String lastName;
    private double salary;

    public Employee(int id, String l_name, String f_name, double amount) {
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
        return "ID:\t\t" + acctID + "\n" +
                "Name:\t" + lastName + ", " + firstName + "\n" +
                "Salary:\t$" + salary;
    }

    // a method to compare if object that invokes this method is greater then, equal or less
    // then obj.
    // the method returns an integer that is less than, equal to, or greater then zero if the
    //executing object is less than, equal to, or greater than the parameter, respectively.
    public int compareTo(Object obj) {
        return 0;
    }
}
