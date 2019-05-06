
public class Rainbow implements ColorPicker {
	
	private double frequency;
    private double phase;
    private int width;
    private int center;
    
    public Rainbow() {
    	frequency = 0.08;
        phase = 2;
        width = 127;
        center = 128;
    }
	
	public int getPixel(double value) { //gradual color shift
        int r;
        int g;
        int b;       

        if (value < 0) {
            r = 0;
            g = 0;
            b = 0;
        }

        else {

            r = (int)(Math.sin(value * frequency + 0) * width + center);
            g = (int)(Math.sin(value * frequency + phase) * width + center);
            b = (int)(Math.sin(value * frequency + phase * 2) * width + center);
        }

        int p = (r << 16) | (g << 8) | b;

        return p;

    }
	
	public void setFrequency(int f)
	{
		double f2 = f;
		if (f < 95)
		{
			f2 = f / 4;
		}
		frequency = f2 / 100.0;
	}


	public void setPhase(int f) {
		
		phase = f / 100.0;
		phase = phase * 5;
		
	}
}
