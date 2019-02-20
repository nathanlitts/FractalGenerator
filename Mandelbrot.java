import java.awt.image.BufferedImage;

/**
 * 
 * @author Nathan Litts
 *
 */
public class Mandelbrot
{
    private ComplexNumber[][] plane; //2D array of complex numbers
    private int maxIter; //Maximum iterations used in isInSet()
    private double step; //difference in value between adjacent complex numbers
    private int width;
    private int height;
    private double[] center = {0, 0};

    /**
     * constructor:
     * sets width and height according to parameters.
     * initializes fields
     * makes plane then makes set 
     * 
     */
    public Mandelbrot(int width, int height) {
        this.width = width;
        this.height = height;
        this.maxIter = 66;
        step = 4 * (1.0 / width);
        plane = new ComplexNumber[height][width];
        makePlane();
        makeSet();
    }
    
    /**
     * Sets all the complex numbers in the plane.
     * Uses center as origin point, then repeatedly 
     * adds step to make the complex numbers different.
     */
    public void makePlane() {  
        double imaginary = center[1] + step * (height / 2);
        double real = center[0] - step * (width / 2);
        
        for (int row = 0; row < height; row++) {      
            for (int col = 0; col < width; col++) { 
                    plane[row][col] = new ComplexNumber(real, imaginary);
                real += step;
            }
            imaginary -= step;
            real = center[0] - step * (width / 2);
        }
    }


    /**
     * All complex numbers are initialized with a value of -2.
     * For each complex number in the plane, this method calls
     * isInSet() to change this value to a number -1-100
     */
    public void makeSet() {
        for (int row = 0; row < height; row++) {      
            for (int col = 0; col < width; col++) {
                if (plane[row][col].getValue() == -2) {
                    isInSet(plane[row][col]);
                }
            }
        }
    }

    
    /**
     * Takes a complex number as a parameter and sets its value field. 
     * Repeated multiplication is done on the complex number. If the
     * number begins to trend to infinity, its value is set 0-100. 
     * If we multiply maxIter number of times and the number 
     * does not trend toward infinity, then its value is set to -1.
     */
    public void isInSet(ComplexNumber c) {
        ComplexNumber z = new ComplexNumber(0, 0);
        int i = 0;
        while (i < maxIter && z.getReal() > -2 && z.getImaginary() > -2
            && z.getReal() < 2 && z.getImaginary() < 2) 
        {                
            z.exp(2);
            z.add(c);
           
            i++;
        }
        if (i == 1) i = 2;
        if (i == maxIter) {
            c.setValue(-1);
        }
        else {
            int percentage = (int)((double)i * (100.0 / maxIter));
            c.setValue(percentage);
        }
    }
  
    /**
     * Creates a graphical representation of the set.
     * The value field of each complex number in the plane
     * is used to determine the color of each pixel.
     * Returns a buffered image.
     */
    public BufferedImage getImage() {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int row = 0; row < height; row++) {      
            for (int col = 0; col < width; col++) {
                int value = plane[row][col].getValue(); 
                int p = getPixel(value);       
                img.setRGB(col, row, p);
            }
        }   
        return img;
    }


    /**
     * Assigns a color based on the passed value.
     * If value is -1, then the color is black.
     * If the value is 0-100, a color is assigned
     * based off of a gradual color shift.
     * returns an integer pixel value.
     */
    public int getPixel(int value) { //gradual color shift
        int a = 255;
        int r;
        int g;
        int b;
        double value2 = (double)value / 10.0;

        if (value < 0) {
            r = 0;
            g = 0;
            b = 0;
        }

        else {

            r = (int)(Math.sin(value2) * 127 + 128);
            g = (int)(Math.sin(value2 + 2) * 127 + 128);
            b = (int)(Math.sin(value2 + 4) * 127 + 128);
        }

        int p = (a << 24) | (r << 16) | (g << 8) | b;

        return p;

    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public int getMonoPixel(int value) { //two tone
        int a = 255;
        int r;
        int g;
        int b;

        if (value < 0) {
            r = 0;
            g = 0;
            b = 0;
        }

        else {
            r = 250;
            g = 250;
            b = 250;
        }

        int p = (a << 24) | (r << 16) | (g << 8) | b;

        return p;

    }
    

    /**
     * This method makes the image zoom in and
     * out. The amount of zoom is based off
     * the amount parameter. An amount < 0 zooms out.
     * > 0 zooms in. The step value is changed, and
     * the plane and set are recalculated.
     */
    public void zoom(double amount)
    {
        step = step / amount;         
        makePlane();      
        makeSet();
    }
    
    public void recenter(int xs, int ys) {
        center[0] += xs * step;
        center[1] += ys * step;
    }
    
    public void increaseMaxIter(int x) {
        maxIter += x;
        makePlane();      
        makeSet();
    }
  
    /**
     * Shifts all the complex numbers in plane. 
     * xs = shift in horizontal direction. negative
     * numbers imply a left shift.
     * ys = shift in vertical direction. negative
     * numbers imply a downward shift.
     * 
     * If a complex number has already been tested, 
     * its value field is preserved. If a new 
     * complex number is introduced, its value is 
     * set to -2 and is immediately reevaluated
     * in makeSet()
     */
    public void shift(int xs, int ys) {
        center[0] = center[0] + xs * step;
        center[1] = center[1] + ys * step;

        ComplexNumber [][] planeCopy = new ComplexNumber[height][];
        for(int row = 0; row < height; row++)
            planeCopy[row] = plane[row].clone();

        double imaginary = center[1] + step * (height / 2);
        double real = center[0] - step * (width / 2);

        for (int row = 0; row < height; row++) {      
            for (int col = 0; col < width; col++) { 
                int row2 = row - ys;
                int col2 = col + xs;
                if (row2 >= 0 && row2 < height
                    && col2 >=0 && col2 < width) {
                    planeCopy[row][col] = plane[row2][col2];
                }
                else {
                    planeCopy[row][col] = new ComplexNumber(real, imaginary);
                }

                real += step;
            }
            imaginary -= step;
            real = center[0] - step * (width / 2);
        }

        plane = planeCopy;
        makeSet();
    }

   

}