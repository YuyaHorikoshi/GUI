package vol1.ch02;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImageButton02 extends JFrame {

  public static void main(String[] args) {
    ImageButton02 w = new ImageButton02( "ImageButton02" );
    w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    w.setSize( 200, 150 );
    w.setVisible( true );
  }
  public ImageButton02( String title ){
    super( title );

    JPanel pane = (JPanel)getContentPane();

    JButton item = new JButton();
    item.setIcon( new TestIcon()  );
    item.setPressedIcon( new TestIcon( Color.blue ) );
    item.setRolloverIcon( new TestIcon( Color.green ) );
    item.setRolloverEnabled(true);
    item.setToolTipText( "Test" );
    pane.add( item );

  }

  class TestIcon implements Icon {
    static final int width  = 100;
    static final int height = 100;
    Color color;

    public TestIcon() { 
      color = Color.white;
    }
    public TestIcon( Color c ) { 
      color = c;
    }
    public void paintIcon( Component c, Graphics g, int x, int y ) {
      g.setColor( this.color );
      g.fillOval( x, y, width, height );
    }
    public int getIconWidth() { 
      return this.width;
    }
    public int getIconHeight() {
      return this.height;
    }
  }

}
