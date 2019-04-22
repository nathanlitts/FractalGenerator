import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;


@SuppressWarnings("serial")
public class View extends JFrame{

	private JLabel equation;
	private JTextField iterationsField;
	private JTextField expField;
	private ContentPanel content;
	private Mandelbrot model;
	private JSlider colorSpeed;
	private JSlider zoomSpeed;
	private JSlider panSpeed;
	private JButton reset;


	/**
	 * Create the view.
	 */
	public View(Mandelbrot model) {
		this.model = model;
		initialize();
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*--- Set up panels ---*/
		content = new ContentPanel(model);
		setContentPane(content);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(6, 6, 228, 500);
		getContentPane().add(panel);
		
		
		/*--- Equation ---*/
		equation = new JLabel(model.getEquation());
		panel.add(equation);
		
		
		
		/*--- Iterations ---*/
		JLabel lblIterations = new JLabel("Iterations:");
		panel.add(lblIterations);

		iterationsField = new JTextField();
		iterationsField.setColumns(10);
		iterationsField.setText("50");
		panel.add(iterationsField);
		
		
		/*--- Exponent ---*/
		JLabel lblExp = new JLabel("Exponent:");
		panel.add(lblExp);

		expField = new JTextField();
		expField.setColumns(10);
		expField.setText("2");
		panel.add(expField);
		
		
		/*--- Color Frequency ---*/
		JLabel lblColorSpeed = new JLabel("Color Frequency", JLabel.CENTER);
        lblColorSpeed.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblColorSpeed);

		colorSpeed = new JSlider(JSlider.HORIZONTAL, 1, 11, 5);
		panel.add(colorSpeed);
		
		
		/*--- Zoom ---*/
		JLabel lblZoomSpeed = new JLabel("Zoom Speed", JLabel.CENTER);
        lblZoomSpeed.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblZoomSpeed);

		zoomSpeed = new JSlider(JSlider.HORIZONTAL, 1, 11, 5);
		panel.add(zoomSpeed);
		
		
		/*--- Pan ---*/
		JLabel lblPanSpeed = new JLabel("Pan Speed", JLabel.CENTER);
        lblPanSpeed.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblPanSpeed);

		panSpeed = new JSlider(JSlider.HORIZONTAL, 1, 11, 5);
		panel.add(panSpeed);
		
		
		/*--- Reset ---*/
		reset = new JButton("Reset");
		panel.add(reset);
		

	}


	public String getIterations() {
		return iterationsField.getText();
	}
	
	public String getExponent() {
		return expField.getText();
	}

	public void addIterationsListener(ActionListener listener) {
		iterationsField.addActionListener(listener);
	}
	
	public void addExpListener(ActionListener listener) {
		expField.addActionListener(listener);
	}
	
	public void addResetListener(ActionListener listener) {
		reset.addActionListener(listener);
	}

	public void updateImage() {
		content.updateImage();
	}

	public void resetFocus() {
		content.requestFocus();
	}

	public void reset() {
		iterationsField.setText("50");
		resetFocus();	
	}


	
}
