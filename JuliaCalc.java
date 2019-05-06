
public class JuliaCalc implements Calculator {

	/**
     * All complex numbers are initialized with a value of -2.
     * For each complex number in the plane, this method calls
     * calculateValue() to change this value to a number -1-100
     */
	public void make(ComplexNumber[][] plane, int height, int width, int maxIter, int exponent, ComplexNumber constant) 
	{
		for (int row = 0; row < height; row++) 
            for (int col = 0; col < width; col++) 
                if (plane[row][col].getValue() == -2) 
                    calculateValue(plane[row][col], maxIter, exponent, constant);
	}
	
	/**
     * Takes a complex number as a parameter and sets its value field.
     * Repeated multiplication is done on the complex number. If the
     * number begins to trend to infinity, its value is set 0-100.
     * If we multiply maxIter number of times and the number
     * does not trend toward infinity, then its value is set to -1.
     */
    public void calculateValue(ComplexNumber c, int maxIter, int exponent, ComplexNumber constant) {
    	ComplexNumber z = new ComplexNumber(c.getReal(), c.getImaginary());
        int i = 0;
        while (i < maxIter && z.abs() <= 2.0)
        {
            z.fastPow(exponent);
            z.add(constant);
            i++;
        }
        
        if (i == 1) i = 2;
        if (i == maxIter) {
            c.setValue(-1);
        }
        else {
            double percentage = ((double)i * (100.0 / maxIter));
            c.setValue(percentage);
        }
    }
	
	
}
