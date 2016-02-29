package quadratic;

public class Quadratic 
	{
	double a, b, c; // as in ax²+bx+c
	
	public Quadratic()
		{
		a = 0;
		b = 0;
		c = 0;
		}
	
	public Quadratic(double a, double b, double c)
		{
		this.a = a;
		this.b = b;
		this.c = c;
		}
	
	/**
	 * 
	 * @param q1
	 * @param q2
	 * @return the polynomial obtained by summing q1 and q2.
	 */
	public static Quadratic sum(Quadratic q1, Quadratic q2)
		{
		return new Quadratic(q1.a + q2.a, q1.b + q2.b, q1.c + q2.c);
		}
	
	/**
	 * 
	 * @param r
	 * @param q
	 * @return the polynomial obtained by scaling all of q's coefficients by constant r.
	 */
	public static Quadratic scale(double r, Quadratic q)
		{
		return new Quadratic(q.a * r, q.b * r, q.c * r);
		}
	
	/**
	 * 
	 * @param q
	 * @return the number of roots of the polynomial q.
	 */
	public static int num_roots(Quadratic q)
		{
		if (q.a ==0) // if a = 0, this is a first-order polynomial (not quadratic)
			{
			if (q.b != 0) // if b isn't zero, we have a line with a non-zero slope, so there is exactly one root.
				return 1;
			else if (q.c != 0) // if b is zero, but c isn't, this doesn't touch the line at all.
				return 0;
			else // if all three coefficients are zero, the polynomial is just the line y=0, and has infinite roots.
				return 3;
			}
		
		double disc = (q.b * q.b) - (4 * q.a * q.c);
		if (disc < 0) // the square root of the discriminant is imaginary, so there are no roots
			return 0;
		if (disc == 0) // the discriminant is 0, so there is no difference between the two roots.
			return 1;
		if (disc > 0)
			return 2;
		
		return -1; // -1 for error in checking.
		}
	}
