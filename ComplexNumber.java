

public class ComplexNumber
{
    private double real;
    private double imaginary;
    private int value;
    
    public ComplexNumber(double r, double i) {
        real = r;
        imaginary = i;
        value = -2;
    }
    
    public void setValue(int x) {
        value = x;
    }
    
    public int getValue() {
        return value;
    }
    
    public void add(ComplexNumber x) {
        real += x.real;
        imaginary += x.imaginary;
    }
    
    
    public void multiply(ComplexNumber x) {
        double tempReal = (real * x.real) - (imaginary * x.imaginary);
        imaginary = (real * x.imaginary) + (imaginary * x.real);
        real = tempReal;
    }
    
    public void exp(int x) {
        for (int i = 1; i < x; i++) {
            double tempReal = (real * real) - (imaginary * imaginary);
            imaginary = (real * imaginary) + (imaginary * real);
            real = tempReal;
        }
    }
    
    public void burningShip(double x, double y) {
        double tempReal = (real * real) - (imaginary * imaginary) + x;
        imaginary = Math.abs(2 * real * imaginary) + y;
        real = Math.abs(tempReal);
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
