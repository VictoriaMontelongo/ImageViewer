package imageviewer.app.swing;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import model.Image;
import view.ImageDisplay;

public class ImagePanel extends JPanel implements ImageDisplay{

    private BufferedImage data;
    private Image image;
    
    
    public ImagePanel(){
        
    }
    
   public void paint(Graphics g){
       Box box = new Box(data.getWidth(), data.getHeight(), this.getWidth(), this.getHeight());
       g.drawImage(data, box.x, box.y, box.width, box.height, null);
   }

    @Override
    public void display(Image image) {
        this.image = image;
        this.data = read(new File(image.getName()));
        repaint();
    }

    @Override
    public Image currentImage() {
        return this.image;
    }

    private static BufferedImage read(File file) {
        try {
            return ImageIO.read(file);
        } catch (IOException ex) {
            return null;
        }
    }

    private static class Box {
        final int x;
        final int y;
        final int width;
        final int height;
                
        private Box(double imageWidth, double imageHeight, double panelWidth, double panelHeight) {
            double imageRatio = imageWidth /imageHeight ;
            double panelRatio = panelWidth / panelHeight ;
            this.width = (int) panelWidth;
            this.height =(int) panelHeight;
            this.x = (int) ((panelWidth - this.width)/2);
            this.y = (int) ((panelHeight - this.height)/2) ;
        }
    }

    
}
