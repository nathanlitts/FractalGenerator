import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * 
 * @author Nathan Litts
 *
 */
public class MousePanel extends JPanel implements MouseListener
{
    private Mandelbrot set;
    private View view;
    private int midX;
    private int midY;

    public MousePanel(Mandelbrot set, View view) {
        this.set = set;
        this.view = view;
        midX = set.getWidth() / 2;
        midY = set.getHeight() / 2;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {       
    }

    @Override
    public void mouseExited(MouseEvent e)
    {        
    }

    @Override
    public void mousePressed(MouseEvent e)
    {      
        if (SwingUtilities.isLeftMouseButton(e)) { //left click

            int x = e.getX();
            int y = e.getY();
            int offX = x - midX;
            int offY = midY - y;
            set.shift(offX, offY); //shifts according to mouse's distance from center
            view.update();  
        }

        else if (SwingUtilities.isRightMouseButton(e)){ //right click
            set.zoom(1.5); //zoom in
            view.update();
            
        }
        else { //middle click
            set.increaseMaxIter(50);
            view.update();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {     
    }


}
