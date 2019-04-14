package ex11;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class JTextFieldTest3A extends JFrame {

  ActionListener actionListener = new TextFieldTestAction();
  JTextField tf1, tf2;

  public static void main( String[] args ){
    JFrame w = new JTextFieldTest3A( "JTextFieldTest3A" );
    w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    w.setSize( 150, 120 );
    w.setVisible( true );
  }

  public JTextFieldTest3A( String title ){
    super( title );
    JPanel pane = (JPanel)getContentPane();
    pane.setLayout( new BoxLayout( pane, BoxLayout.Y_AXIS ) );
    
    tf1 = new JTextField();
    tf1.setBorder( new TitledBorder( "コピー元" ) );
    tf1.addActionListener( actionListener );
    pane.add( tf1 );

    tf2 = new JTextField();
    tf2.setBorder( new TitledBorder( "コピー先" ) );
    pane.add( tf2 );
  }

  class TextFieldTestAction implements ActionListener {
    public void actionPerformed( ActionEvent e ){
      JTextField source = (JTextField)e.getSource();
      String string = source.getText();
      if( source == tf1 ){
        tf2.setText(string);
        tf1.setText(null);
      
      }
      
    }
  }
}
