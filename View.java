import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeListener;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;



@SuppressWarnings("serial")
public class View extends JFrame {

	private JTextField iterationsField;
	private JTextField expField;
	private ContentPanel content;
	private Mandelbrot model;
	private JComboBox<String> colorBox;
	private JComboBox<String> fractalBox;
	private JSlider colorSpeed;
	private JSlider phaseSlider;
	private JButton reset;
	private JButton saveButton;
    private JFileChooser fc;


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
		panel.setBounds(6, 6, 228, 280);
		getContentPane().add(panel);
		
		
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
		
		
		/*--- Color Theme ___*/
		JLabel lblTheme = new JLabel("Theme:");
		panel.add(lblTheme);
		
		String[] colorOptions = {"Rainbow     ", "Grayscale"};
		colorBox = new JComboBox<String>(colorOptions);
		panel.add(colorBox);
		
		
		/*--- Fractal Type ___*/
		JLabel lblFractal = new JLabel("Fractal:");
		panel.add(lblFractal);
		
		String[] fractalOptions = {"Mandelbrot", "Julia"};
		fractalBox = new JComboBox<String>(fractalOptions);
		panel.add(fractalBox);
		
		
		/*--- Color Frequency ---*/
		JLabel lblColorSpeed = new JLabel("Color Frequency", JLabel.CENTER);
        lblColorSpeed.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblColorSpeed);

		colorSpeed = new JSlider(JSlider.HORIZONTAL, 1, 100, 5);
		panel.add(colorSpeed);
		
		
		/*--- Color Phase ---*/
		JLabel lblPhase = new JLabel("Color Phase", JLabel.CENTER);
        lblColorSpeed.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblPhase);

		phaseSlider = new JSlider(JSlider.HORIZONTAL, 1, 100, 5);
		panel.add(phaseSlider);
		
		
		/*--- Reset ---*/
		reset = new JButton("Reset");
		panel.add(reset);
		
		
		/*--- Save ---*/
		fc = new JFileChooser();
		saveButton = new JButton("Save");
		panel.add(saveButton);
		

	}


	/*--- add Action Listeners ---*/

	public void addIterationsListener(ActionListener listener) {
		iterationsField.addActionListener(listener);
	}
	
	public void addExpListener(ActionListener listener) {
		expField.addActionListener(listener);
	}
	
	public void addThemeListener(ActionListener listener) {
		colorBox.addActionListener(listener);
	}
	
	public void addFractalListener(ActionListener listener) {
		fractalBox.addActionListener(listener);
	}
	
	public void addFrequencyListener(ChangeListener listener) {
		colorSpeed.addChangeListener(listener);
	}
	
	public void addPhaseListener(ChangeListener listener) {
		phaseSlider.addChangeListener(listener);
		
	}
	
	public void addResetListener(ActionListener listener) {
		reset.addActionListener(listener);
	}
	
	public void addSaveListener(ActionListener listener) {
		saveButton.addActionListener(listener);
	}
	
	
	/*--- Get Field Values ---*/ 
	
	public String getIterations() {
		return iterationsField.getText();
	}
	
	public String getExponent() {
		return expField.getText();
	}
	
	
	/*--- Set View Properties ---*/
	
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
