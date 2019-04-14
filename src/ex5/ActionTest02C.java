package ex5;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class ActionTest02C extends JFrame {

  public static void main(String[] args) {
    ActionTest02C w = new ActionTest02C( "ActionTest02" );
    w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    w.setSize( 300, 200 );
    w.setVisible( true );
    System.out.println( "初期値は0" );
  }
  public ActionTest02C( String title ){
    super( title );
    JButton button = new JButton( new Action02() );
    getContentPane().add( button, BorderLayout.NORTH );
  }
  class Action02 extends AbstractAction{
    Action02(){
      putValue( Action.NAME, "増やす" );
      putValue( Action.SMALL_ICON, new ImageIcon( "open.gif" ) );
      putValue( Action.SHORT_DESCRIPTION, "ツールチップ" );
    }
    int count=0;

    public void actionPerformed( ActionEvent e ){
    	count++;
      System.out.println( "現在値は"+count );
    }
  }
}
