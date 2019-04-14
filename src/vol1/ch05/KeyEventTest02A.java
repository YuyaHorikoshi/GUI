package vol1.ch05;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class KeyEventTest02A extends JFrame {

  public static void main(String[] args) {
    KeyEventTest02A w = new KeyEventTest02A( "KeyEventTest02A" );
    w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    w.setSize( 300, 120 );
    w.setVisible( true );
  }
  public KeyEventTest02A( String title ){
    super( title );
    JPanel panel = (JPanel)getContentPane();
    panel.setLayout( new GridLayout( 3, 2 ) );

    JTextField tf1 = new JTextField();
    tf1.setName( "TextField 1" );
    JTextField tf2 = new JTextField();
    tf2.setName( "TextField 2" );
    JTextField tf3 = new JTextField();
    tf3.setName( "TextField 3" );

    tf1.addKeyListener( new KeyCheck() );
    tf2.addKeyListener( new KeyCheck() );
    tf3.addKeyListener( new KeyCheck() );

    panel.add( new JLabel( "TextField 1" ) );
    panel.add( tf1 );
    panel.add( new JLabel( "TextField 2" ) );
    panel.add( tf2 );
    panel.add( new JLabel( "TextField 3" ) );
    panel.add( tf3 );

  }
  class KeyCheck implements KeyListener {
    public void keyTyped( KeyEvent e ){
      JTextField com = (JTextField)e.getSource();
      System.out.println( com.getName() );
    }
    public void keyPressed( KeyEvent e ){
    }
    public void keyReleased( KeyEvent e ){
    }
  }
}
