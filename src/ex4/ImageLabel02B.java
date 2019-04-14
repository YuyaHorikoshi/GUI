package ex4;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ImageLabel02B extends JFrame {

  public static void main(String[] args) {
    ImageLabel02B w = new ImageLabel02B( "ImageLabel02" );
    w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    w.setSize(800, 600 );
    w.setVisible( true );
  }
  public ImageLabel02B( String title ){
    super( title );
    JLabel panel = new JLabel( new DrawIcon() );
    getContentPane().add( panel );
  }
  class DrawIcon implements Icon {
    static final int width  = 300;
    static final int height = 300;

    public void paintIcon( Component c, Graphics g, int x, int y ) {
      g.setColor( Color.black);
      g.drawOval( x, y, width, height );
      g.fillOval(x+60, y+100, 50, 50);
      g.fillOval(x+190, y+100, 50, 50);
      g.drawArc(290, 200, 200, 200, 200, 140);
      
    }
    public int getIconWidth() { 
      return this.width;
    }
    public int getIconHeight() {
      return this.height;
    }
  }
}
