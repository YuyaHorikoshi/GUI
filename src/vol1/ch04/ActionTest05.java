package vol1.ch04;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class ActionTest05 extends JFrame {

  public static void main(String[] args) {
    ActionTest05 w = new ActionTest05( "ActionTest05" );
    w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    w.setSize( 350, 200 );
    w.setVisible( true );
  }
  public ActionTest05( String title ){
    super( title );

    Action action = new A();

    JPanel pane = (JPanel)getContentPane();

    JButton button = new JButton( action );

    KeyStroke keyStroke = KeyStroke.getKeyStroke( "A" );
    button.getInputMap().put( keyStroke, action );
    button.getActionMap().put( action, action );

    pane.add( button, BorderLayout.NORTH );
    JTextArea ta = new JTextArea(); // キー入力を扱うコンポーネント
    pane.add( ta, BorderLayout.CENTER ); // この2行を追加しよう

  }

  class A extends AbstractAction {
    A(){
      putValue( Action.NAME, "A" );
    }
    public void actionPerformed( ActionEvent e ){
      System.out.println( "A が押されました" );
    }
  }
}
