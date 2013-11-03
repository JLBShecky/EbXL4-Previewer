import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class main extends JFrame implements KeyListener {
  
  IterativeLandLayerGenerator layer;
  int size = 200;
  main frameInstance;

  public main() {
    super("My Frame");
    this.addKeyListener(this);
    frameInstance = this;
    
    layer = new IterativeLandLayerGenerator(new Random());

    // you can set the content pane of the frame
    // to your custom class.

    setContentPane(new DrawPane());

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setSize(size + size + 1, size + size + 1);

    setVisible(true);
  }

  // create a component that you can actually draw on.
  class DrawPane extends JPanel implements ComponentListener {
    
    int width;
    int height;
    
    int offsetX;
    int offsetZ;
    
    public DrawPane(){
      this.addComponentListener(this);
      
      Dimension mySize = this.getSize();
      frameInstance.setTitle("Preview (" + width + "," + height + ")");
      
      width = mySize.width;
      height = mySize.height;
      
      offsetX = width / 2;
      offsetZ = height / 2;
    }
    
    public void paintComponent(Graphics g) {
      // draw on g here e.g.
      for(int z = 0; z <= height; z++) {
        for(int x = 0; x <= width; x++) {
          if(layer.Generate(x - offsetX, z - offsetZ)) {
            g.setColor(new Color(0,127,0));
          } else {
            g.setColor(new Color(0,0,127));
          }
          g.fillRect(x, z, 1, 1);
        }
      }
      
      //g.fillRect(20, 20, 100, 200);
    }

    @Override
    public void componentHidden(ComponentEvent arg0) {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void componentMoved(ComponentEvent arg0) {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void componentResized(ComponentEvent arg0) {
      // TODO Auto-generated method stub
      
      Dimension mySize = this.getSize();
      frameInstance.setTitle("Preview (" + width + "," + height + ")");

      width = mySize.width;
      height = mySize.height;
      
      offsetX = width / 2;
      offsetZ = height / 2;
      
    }

    @Override
    public void componentShown(ComponentEvent arg0) {
      // TODO Auto-generated method stub
      
    }
  }

  public static void main(String[] args) {
    new main();
  }

  @Override
  public void keyPressed(KeyEvent e) {
    // TODO Auto-generated method stub
    if(e.getKeyChar() == 'r') {
      layer = new IterativeLandLayerGenerator(new Random());
      this.repaint();
    }
    
  }

  @Override
  public void keyReleased(KeyEvent e) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void keyTyped(KeyEvent e) {
    // TODO Auto-generated method stub
    
  }

}
