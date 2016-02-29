package csc103s16.lab1;

/**
 * CSC103 Lab1 Quadratic
 * 2/10/2016
 *
 * Quadratic Class
 *
 * @author Timothy Haskins
 */
public class Quadratic implements Cloneable{
    /**
     * Quadratic Data members
     * Coefficients, roots, and number of roots
     */
    private double coef_a, coef_b, coef_c, root_1, root_2;
    private int num_roots;

    /**
     * Default Constructor
     */
    public Quadratic(){
        this(0,0,0);
    }

    /**
     * Constructor with Coefficients
     * @param ca
     * @param cb
     * @param cc
     */
    public Quadratic(double ca, double cb, double cc){
        coef_a = ca;
        coef_b = cb;
        coef_c = cc;
        calculateRoots();
    }

    /**
     * Get the coefficient of A
     * @return double coef_a
     */
    public double getCoefA(){
        return coef_a;
    }

    /**
     * Get the coefficient of B
     * @return double coef_b
     */
    public double getCoefB(){
        return coef_b;
    }

    /**
     * Get the coefficient of C
     * @return double coef_c
     */
    public double getCoefC(){
        return coef_c;
    }

    /**
     * Get the number of real roots
     * @return int
     */
    public int getRootNum(){
        int return_val = -1;
        double temp4ac = 4 * coef_a * coef_c;

        if(coef_a == 0){
            if(coef_b == 0) return_val = (coef_c == 0)?3:0;
            else return_val = 1;
        }else{
            if(Math.pow(coef_b, 2) < temp4ac) return_val = 0;
            if(Math.pow(coef_b, 2) == temp4ac) return_val = 1;
            if(Math.pow(coef_b, 2) > temp4ac) return_val = 2;
        }

        setNumRoots(return_val);
        return return_val;
    }

    /**
     * Get the first root
     * @return double
     */
    public double getRootOne(){
        return root_1;
    }

    /**
     * Get the second root
     * @return double
     */
    public double getRootTwo(){
        return root_1;
    }

    /**
     * Set coef_a
     * @param coef double
     */
    public void setCoefA(double coef){
        coef_a = coef;
        getRootNum();
    }

    /**
     * Set coef_b
     * @param coef double
     */
    public void setCoefB(double coef){
        coef_b = coef;
        getRootNum();
    }

    /**
     * Set coef_c
     * @param coef double
     */
    public void setCoefC(double coef){
        coef_c = coef;
        getRootNum();
    }

    /**
     * With a given x value, return evaluated value from expression
     * @param x_val double
     * @return double
     */
    public double evalExpression(double x_val){
        return ((Math.pow(x_val, 2)*coef_a)+(x_val*coef_b)+(coef_c));
    }

    /**
     * Determine if another Quadratic is equal to calling Quadratic
     * @param obj
     * @return boolean
     */
    public boolean equals(Object obj){
        if(obj instanceof Quadratic){
            Quadratic temp = (Quadratic) obj;
            return ((coef_a == temp.coef_a) && (coef_b == temp.coef_b) && (coef_c == temp.coef_c));
        }else return false;
    }

    /**
     * Create replica of calling Quadratic
     * @return Quadratic
     */
    public Quadratic clone(){
        try{
            return (Quadratic) super.clone();
        }catch(CloneNotSupportedException e){
            throw new RuntimeException("This class does not implement Cloneable ... Even though it does.");
        }
    }

    /**
     * Returns the string value of the quadratic prior to evaluation
     * @return String
     */
    public String toString(){
        return coef_a + "x^2 + " + coef_b + "x + " + coef_c;
    }

    /**
     * Set coef_c
     * @param num int
     */
    private void setNumRoots(int num){
        num_roots = num;
        calculateRoots();
    }

    /**
     * Calculate and set root data members
     */
    private void calculateRoots(){
        switch(num_roots){
            case 1:
                root_1 = (coef_a == 0)?((-1 * coef_c)/coef_b):((-1 * coef_b)/(2 * coef_as));
                break;
            case 2:
                root_1 = ((-1 * coef_b)-(Math.sqrt(Math.pow(coef_b, 2) - (4 * coef_a * coef_c))))/(2 * coef_a);
                root_2 = ((-1 * coef_b)+(Math.sqrt(Math.pow(coef_b, 2) - (4 * coef_a * coef_c))))/(2 * coef_a);
                break;
        }
    }

    /**
     * Return the Quadratic resulting from the addition of two other Quadratics
     * @param q1
     * @param q2
     * @return Quadratic
     */
    public static Quadratic sum(Quadratic q1, Quadratic q2){
        return new Quadratic(q1.coef_a + q2.coef_a, q1.coef_b + q2.coef_b, q1.coef_c + q2.coef_c);
    }

    /**
     * Return the resulting Quadratic of given Quadratic scaled by given scale
     * @param r
     * @param q
     * @return Quadratic
     */
    public static Quadratic scale(double r, Quadratic q){
        return new Quadratic(q.coef_a * r, q.coef_b * r, q.coef_c * r);
    }
}
