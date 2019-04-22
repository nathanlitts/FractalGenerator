import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
	
	private Mandelbrot model;
	private View view;
	
	/*
	 * Constructor
	 */
	public Controller(Mandelbrot m, View v)
	{
		model = m;
		view = v;

        //add listeners to view
        view.addIterationsListener(new IterationsListener());
        view.addResetListener(new resetListener());
        view.addExpListener(new ExpListener());
	}
	
	private class IterationsListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String userInput = view.getIterations();
			int number;
			try
			{
			    number = Integer.parseInt(userInput);
			    if(number >= 3 && number <= 100000)
			    {
			    	model.setIterations(number);
			    	view.updateImage();
			    }
			    else
			    {
			    	System.out.println("That number is not in range 3 - 100000");
			    }
			} catch (NumberFormatException ex)
			{
			     System.out.println("That is not a number!");
			}
			view.resetFocus();
		}
	}
	
	private class ExpListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String userInput = view.getExponent();
			int number;
			try
			{
			    number = Integer.parseInt(userInput);
			    if(number >= 1 && number <= 100)
			    {
			    	model.setExponent(number);
			    	view.updateImage();
			    }
			    else
			    {
			    	System.out.println("That number is not in range 1 - 100");
			    }
			} catch (NumberFormatException ex)
			{
			     System.out.println("That is not a number!");
			}
			view.resetFocus();
		}
	}
	
	private class resetListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			model.reset();
			view.reset();
			view.updateImage();
		}
		
	}
	
	
	
	
}
