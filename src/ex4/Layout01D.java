package ex4;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Layout01D extends JFrame {

  public static void main(String[] args) {
    Layout01D w = new Layout01D( "Layout01D" );
    w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    w.setSize( 350, 200 );
    w.setVisible( true );
  }
  public Layout01D( String title ){
    super( title );

    JPanel pane = new JPanel();
    pane.setLayout(new BorderLayout());

    

    JButton buttonNorth = new JButton( "North" );
    pane.add( buttonNorth, BorderLayout.NORTH );
    
    JButton buttonCenter  = new JButton( "Center" );
    pane.add( buttonCenter, BorderLayout.CENTER );
    
    JButton buttonSouth = new JButton( "South" );
    pane.add( buttonSouth, BorderLayout.SOUTH );
    
    JButton buttonWest  = new JButton( "West" );
    pane.add( buttonWest, BorderLayout.WEST );
    
    JButton buttonEast  = new JButton( "East" );
    pane.add( buttonEast, BorderLayout.EAST );
    
    add(new JButton("North"),BorderLayout.NORTH);
    add(pane,BorderLayout.CENTER);
    add(new JButton("South"),BorderLayout.SOUTH);
    add(new JButton("West"),BorderLayout.WEST);
    add(new JButton("East"),BorderLayout.EAST);

  }
}
