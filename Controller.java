import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
        view.addThemeListener(new ThemeListener());
        view.addFractalListener(new FractalListener());
        view.addFrequencyListener(new FrequencyListener());
        view.addPhaseListener(new PhaseListener());
        view.addSaveListener(new SaveListener());

        
        
        view.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            	// This gets called way too many times while user is
            	// resizing by dragging the window's edge. Needs a timer.
            	
            	int height = e.getComponent().getHeight();
            	int width = e.getComponent().getWidth();
                
                model.setHeight(height);
                model.setWidth(width);
                model.update();
                view.updateImage();
                 
            }
        });
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
			    if(number >= 1 && number <= 100000)
			    {
			    	model.setIterations(number);
			    	view.updateImage();
			    }
			    else
			    {
			    	System.out.println("That number is not in range 1 - 100000");
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
	
	private class ThemeListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
	        JComboBox cb = (JComboBox)e.getSource();
	        String name = (String)cb.getSelectedItem();
	        ColorPicker x;
	        
	        switch (name) {
	        	case "Rainbow":
	        		x = new Rainbow();
	        		break;
	        	 
	        	case "Grayscale":
	        		x = new Grayscale();
	        		break;
	        	
	        	default:
	        		x = new Rainbow();
	        }	
	     		
	        model.updateCP(x);
	        view.updateImage();
	        view.resetFocus();
	    }
	}
	
	private class FractalListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
	        JComboBox cb = (JComboBox)e.getSource();
	        String name = (String)cb.getSelectedItem();
	        Calculator x;
	        
	        switch (name) {
	        	case "Mandelbrot":
	        		x = new MandelbrotCalc();
	        		break;
	        	 
	        	case "Julia":
	        		x = new JuliaCalc();
	        		break;
	        	
	        	default:
	        		x = new MandelbrotCalc();
	        }	
	     		
	        model.updateCalc(x);
	        view.updateImage();
	        view.resetFocus();
	    }
	}
	
	private class FrequencyListener implements ChangeListener
	{

		public void stateChanged(ChangeEvent e) {
	        JSlider source = (JSlider)e.getSource();
	        if (!source.getValueIsAdjusting()) {
	            int value = (int)source.getValue();
	            model.setFrequency(value);
	            view.updateImage();
	            view.resetFocus();
	        }
	    }
		
	}
	
	private class PhaseListener implements ChangeListener
	{

		public void stateChanged(ChangeEvent e) {
	        JSlider source = (JSlider)e.getSource();
	        if (!source.getValueIsAdjusting()) {
	            int value = (int)source.getValue();
	            model.setPhase(value);
	            view.updateImage();
	            view.resetFocus();
	        }
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
	
	private class SaveListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {            
		    JFileChooser chooser = new JFileChooser(); 
		    chooser.setDialogTitle("Save Image");
		    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
		    int returnValue = chooser.showSaveDialog(null);
		    
		    if (returnValue == JFileChooser.APPROVE_OPTION)
		    {
		    	String directory = chooser.getCurrentDirectory().getPath();
		    	String name = chooser.getSelectedFile().getName();
		    	
		    	try {
					model.saveImage(directory,  name);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }

		}
	}
	
	
	
	
}
