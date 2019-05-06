
public class Main 
{
	public static void main(String[] args)
	{
	    Mandelbrot model = new Mandelbrot(900, 600);
	    View view = new View(model);
	    Controller controller = new Controller(model, view);
	    view.setVisible(true);
	    view.resetFocus();
	}
}
