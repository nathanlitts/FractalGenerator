
public class ComplexNumber
{
    private double real;
    private double imaginary;
    private double value;
    
    public ComplexNumber(double r, double i) {
        real = r;
        imaginary = i;
        value = -2;
    }
    
    public void setValue(double x) {
        value = x;
    }
    
    public double getValue() {
        return value;
    }
    
    public void add(ComplexNumber x) {
        real += x.real;
        imaginary += x.imaginary;
    }
    
    public void add(double x) {
    	real += x;
    	imaginary += x;
    }
     
    public void multiply(ComplexNumber x) {
        double tempReal = (real * x.real) - (imaginary * x.imaginary);
        imaginary = (real * x.imaginary) + (imaginary * x.real);
        real = tempReal;
    }
    
    public void multiply(double x) {
    	real *= x;
    	imaginary *= x;
    }
    
    public double abs() {
        if (Math.abs(real) < Math.abs(imaginary)) {
            if (imaginary == 0.0) {
                return Math.abs(real);
            }
            double q = real / imaginary;
            return Math.abs(imaginary) * Math.sqrt(1 + q * q);
        } else {
            if (real == 0.0) {
                return Math.abs(imaginary);
            }
            double q = imaginary / real;
            return Math.abs(real) * Math.sqrt(1 + q * q);
        }
    }
    
    public void log() {
    	double r = real;
    	real = Math.log(abs());
        imaginary = Math.atan2(imaginary, r);
    }
    
    public void exp() {
    	double expReal = Math.exp(real);
        real = expReal *  Math.cos(imaginary);
        imaginary = expReal * Math.sin(imaginary);
    	
    }
    
    public void pow(double x) {
         log();
         multiply(x);
         exp();
    }
    
    public void fastPow(int x) {
    	ComplexNumber copy = new ComplexNumber(real, imaginary);
    		for (int i = 1; i < x; i++) 
    			multiply(copy);
    }
    
    public void divide(ComplexNumber divisor) {
    	 double c = divisor.getReal();
         double d = divisor.getImaginary();
         double oldReal = real;
         double oldImaginary = imaginary;
         
        if (c == 0.0 && d == 0.0) {
            return;
        }

        if (Math.abs(c) < Math.abs(d)) {
            double q = c / d;
            double denominator = c * q + d;
            real = (oldReal * q + oldImaginary) / denominator;
            imaginary = (oldImaginary * q - oldReal) / denominator;
        } else {
            double q = d / c;
            double denominator = d * q + c;
            real = (oldImaginary * q + oldReal) / denominator;
            imaginary = (oldImaginary - oldReal * q) / denominator;
        }
    }
    
    public double getReal() {
        return real;
    }
    
    public double getImaginary() {
        return imaginary;
    }
    
    public String toString() {
        return real + " " + imaginary + "i";
    }
    
}
