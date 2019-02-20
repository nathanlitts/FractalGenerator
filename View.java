import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 * Contains main method.
 * @author Nathan Litts
 *
 */
public class View extends JFrame
{
    private JLabel label;
    private Mandelbrot set;
    private MousePanel mouseP;

    public View()
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int)screenSize.getHeight();
        int width = (int)screenSize.getWidth();
        set = new Mandelbrot(width, height);
        BufferedImage img = set.getImage();
        label = new JLabel();
        mouseP = new MousePanel(set, this);
        label.addMouseListener(mouseP);
        add(label);
        label.setIcon(new ImageIcon(img));

    }
    
    public void update() {
        BufferedImage img = set.getImage();
        label.setIcon(new ImageIcon(img));
    }


    public static void main(String[] args)
    {

        JFrame frame = new View();
        frame.setTitle("FractalViewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setUndecorated(true);
        frame.setVisible(true);
    }
}
