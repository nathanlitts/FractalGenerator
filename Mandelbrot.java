import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
	private int exponent;
	private ComplexNumber constant = new ComplexNumber(0, 0);
	
	private BufferedImage image;
	
	private ColorPicker cp;
	private Calculator calc;

    
    /**
     * constructor:
     * sets width and height according to parameters.
     * initializes fields
     * makes plane then makes set
     *
     */
    public Mandelbrot(int w, int h) {
        width = w;
        height = h;
    	exponent = 2;
        maxIter = 50;
        step = 4 * (1.0 / width);
        plane = new ComplexNumber[height][width];
        
        cp = new Rainbow();
        calc = new MandelbrotCalc();
        makePlane();
        makeSet();

    }
    
    /**
     * Resets the model.
     */
    public void reset() {
        maxIter = 50;
        step = 4 * (1.0 / width);
        center[0] = 0;
        center [1] = 0;
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
     * Uses the calculator to set the value field of each 
     * complex number in the plane
     */
    public void makeSet() {
    	calc.make(plane, height, width, maxIter, exponent, constant);
    }

    
    /**
     * Creates a graphical representation of the set.
     * The value field of each complex number in the plane
     * is used to determine the color of each pixel.
     * Returns a buffered image.
     */
    public BufferedImage getImage() {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                double value = plane[row][col].getValue();
                int p = cp.getPixel(value);
                img.setRGB(col, row, p);
            }
        }
        
        image = img;
        return image;
    }
    
    public void saveImage(String dir, String name) throws IOException {

    	if (!name.endsWith(".png"))
    		name += ".png";
    	
    	File outputfile = new File(dir + '/' + name);
    	ImageIO.write(image, "png", outputfile);

    }


    /**
     * This method makes the image zoom in and
     * out. The amount of zoom is based off
     * the amount parameter. An amount < 1 zooms out.
     * > 1 zooms in. The step value is changed, and
     * the plane and set are recalculated.
     */
    public void zoom(double amount)
    {
        step = step / amount;
        makePlane();
        makeSet();
    }

    
    public void setIterations(int x) {
        maxIter = x;
        makePlane();
        makeSet();
    }
    
    public void setExponent(int number) {
		exponent = number;
		makePlane();
        makeSet();
		
	}
    
    public void setHeight(int h) {
    	height = h;
    }
    
    public void setWidth(int w) {
    	width = w;
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

	public void updateCP(ColorPicker x) {
		cp = x;
		makePlane();
        makeSet();
		
	}
	
	public void updateCalc(Calculator x ) {
		constant = new ComplexNumber(center[0], center[1]);
		calc = x;
		makePlane();
		makeSet();
	}
	
	public void update() {
		plane = new ComplexNumber[height][width];
		makePlane();
		makeSet();
		
	}
	
	public void setFrequency(int f) {
		cp.setFrequency(f);
	}
	
	public void setPhase(int f) {
		cp.setPhase(f);
	}

	


}
