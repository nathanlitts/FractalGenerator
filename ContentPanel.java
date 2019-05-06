
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public class ContentPanel extends JLabel
implements KeyListener, MouseListener {

	private Mandelbrot model;

	/**
	 * Constructor
	 */
	public ContentPanel(Mandelbrot m) {

		model = m;
		setIcon(new ImageIcon(model.getImage()));

		addKeyListener(this);     // Set up event listening.
		addMouseListener(this);

	} 

	public void updateImage() {
		setIcon(new ImageIcon(model.getImage()));
	}


	// ------------------- Event handling methods ----------------------     

	/**
	 * This is called each time the user presses a key while the panel has
	 * the input focus.  
	 */
	public void keyPressed(KeyEvent evt) { 

		//if (!model.isReady()) return; // Wait for the model to finish processing
				
		int key = evt.getKeyCode();  // keyboard code for the pressed key

		if (key == KeyEvent.VK_LEFT) {  // left arrow key
			model.shift(-30, 0);
			updateImage();
		}
		else if (key == KeyEvent.VK_RIGHT) {  // right arrow key
			model.shift(30, 0);
			updateImage();
		}
		else if (key == KeyEvent.VK_UP) {  // up arrow key
			model.shift(0, 30);
			updateImage();
		}
		else if (key == KeyEvent.VK_DOWN) {  // down arrow key
			model.shift(0, -30);
			updateImage();
		}
		else if (key == KeyEvent.VK_Z) {  // z key zooms in
			model.zoom(1.5);
			updateImage();
		}
		else if (key == KeyEvent.VK_X) {  // x key zooms out
			model.zoom(0.666);
			updateImage();
		}

	}  


	public void keyReleased(KeyEvent evt) { } // Required by the
	public void keyTyped(KeyEvent evt) { }    // KeyListener interface.


	/**
	 * This is called when the user clicks the panel with the mouse.
	 * It just requests that the input focus be given to this panel.
	 */
	public void mousePressed(MouseEvent evt) {
		requestFocus();
	}   


	public void mouseEntered(MouseEvent evt) { }  // Required by the
	public void mouseExited(MouseEvent evt) { }   //    MouseListener
	public void mouseReleased(MouseEvent evt) { } //       interface.
	public void mouseClicked(MouseEvent evt) { }

}