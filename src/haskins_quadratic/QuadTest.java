package haskins_quadratic;

/**
 * CSC103 Lab1 Quadratic
 * 2/10/2016
 *
 * QuadTest Class
 *
 * @author Timothy Haskins
 */
public class QuadTest {
    /**
     * QuadTest data members, 2 Quadratics and their sum
     */
    Quadratic q1, q2, q_sum;

    /**
     * Default Constructor
     */
    public QuadTest(){
        this(new Quadratic(), new Quadratic());
    }

    /**
     * Constructor with two given Quadratics
     * @param q_1 Quadratic
     * @param q_2 Quadratic
     */
    public QuadTest(Quadratic q_1, Quadratic q_2){
        q1 = q_1;
        q2 = q_2;
    }

    /**
     * Return first Quadratic
     * @return Quadratic
     */
    public Quadratic getQ1(){
        return q1;
    }

    /**
     * Return second Quadratic
     * @return Quadratic
     */
    public Quadratic getQ2(){
        return q2;
    }

    /**
     * Return sum of Quadratic data members
     * @return Quadratic
     */
    public Quadratic getQSum(){
        return q_sum;
    }

    /**
     * This one was confusing, your instructions were not clear enough
     * Regardless, it is apparent that the Quadratics need to be added together and stored in q_sum
     */
    public void caclulations(){
        q_sum = Quadratic.sum(q1, q2);
    }

    /**
     * Return Quadratics of QuadTest and their sum
     * @return String
     */
    public String output(){
        return "The addition of given quadratics;  '" + q1.toString() + "' and '" + q2.toString() + "', is '" + q_sum.toString() + "'.\n";
    }

    /**
     * Set first Quadratic
     * @param q Quadratic
     */
    private void setQ1(Quadratic q){
        q1 = q;
    }

    /**
     * Set second Quadratic
     * @param q Quadratic
     */
    private void setQ2(Quadratic q){
        q2 = q;
    }

    /**
     * Return Intro String
     * @return String
     */
    public static String intro(){
        return "This program introduces the class structure of quadratic equations and their functionality alone and with others.\n";
    }

    /**
     * Retrieve quadratic, scale, x value, and second quadratic from String
     * @param input String
     */
    public static void parse(String input){
        String[] input_parts = input.split("(\\s*?)");
        if(input_parts.length == 8){

        }
    }
}
