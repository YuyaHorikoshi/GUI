package ex10;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

public class JListTest01B extends JFrame {
  JPanel pane;
  public static void main(String[] args) {
    JListTest01B w = new JListTest01B( "JListTest01B" );
    w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    w.setSize( 300, 200 );
    w.setVisible( true );
  }
  public JListTest01B( String title ) {  
    super( title );
    pane = (JPanel)getContentPane();
    
    String[] choice = { "0", "1", "2", "3", "4", "5", "6", "7","8","9" };
    JList list = new JList( choice );
    pane.add(list);
  }
 
  }

