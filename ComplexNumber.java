
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
    
    public void pow(int x) {
    	ComplexNumber copy = new ComplexNumber(real, imaginary);
        for (int i = 1; i < x; i++) {
            multiply(copy);
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
