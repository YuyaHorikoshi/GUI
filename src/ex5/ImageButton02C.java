package ex5;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImageButton02C extends JFrame {

  public static void main(String[] args) {
    ImageButton02C w = new ImageButton02C( "ImageButton02" );
    w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    w.setSize( 200, 150 );
    w.setVisible( true );
  }
  public ImageButton02C( String title ){
    super( title );

    JPanel pane = (JPanel)getContentPane();

    JButton item = new JButton();
    item.setIcon( new TestIcon()  );
    item.setPressedIcon( new TestIcon3() );
    item.setRolloverIcon( new TestIcon2() );
    item.setRolloverEnabled(true);
    item.setToolTipText( "Test" );
    pane.add( item );

  }

  class TestIcon implements Icon {
    static final int width  = 100;
    static final int height = 100;
    Color color;

    public TestIcon() { 
      color = Color.green;
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
  
  class TestIcon2 implements Icon{
	  static final int width  = 100;
	    static final int height = 100;
	    Color color;

	    public TestIcon2() { 
	      color = Color.green;
	    }
	    public TestIcon2( Color c ) { 
	      color = c;
	    }
	    public void paintIcon( Component c, Graphics g, int x, int y ) {
	      g.setColor( this.color );
	      g.fillRoundRect(x, y, width, height, 30, 30);
	    }
	    public int getIconWidth() { 
	      return this.width;
	    }
	    public int getIconHeight() {
	      return this.height;
	    }
  }
  class TestIcon3 implements Icon{
	  static final int width  = 100;
	    static final int height = 100;
	    Color color;

	    public TestIcon3() { 
	      color = Color.green;
	    }
	    public TestIcon3( Color c ) { 
	      color = c;
	    }
	    public void paintIcon( Component c, Graphics g, int x, int y ) {
	      g.setColor( this.color );
	      g.fillRect(x, y, width, height);
	    }
	    public int getIconWidth() { 
	      return this.width;
	    }
	    public int getIconHeight() {
	      return this.height;
	    }
  }

}
