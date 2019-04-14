package ex5;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class ActionTest02E extends JFrame {
	public int count=0;
  public static void main(String[] args) {
	  
    ActionTest02E w = new ActionTest02E( "ActionTest02" );
    w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    w.setSize( 300, 200 );
    w.setVisible( true );
    System.out.println( "初期値は0" );
  }
  
  public ActionTest02E( String title ){
    super( title );
    JButton button = new JButton( new Action02() );
    JButton button2=new JButton(new Action02b());
    JButton button3=new JButton(new Action02c());
    getContentPane().add( button, BorderLayout.NORTH );
    getContentPane().add( button2,BorderLayout.SOUTH);
    getContentPane().add( button3,BorderLayout.CENTER);
    
  }
  class Action02 extends AbstractAction{
    Action02(){
    	
      putValue( Action.NAME, "増やす" );
      putValue( Action.SMALL_ICON, new ImageIcon( "open.gif" ) );
      putValue( Action.SHORT_DESCRIPTION, "ツールチップ" );
    }
    
    public void actionPerformed( ActionEvent e ){
    	count++;
      System.out.println( "現在値は"+count );
    }
  }
    
    class Action02b extends AbstractAction{
    	Action02b(){
    		putValue( Action.NAME, "減らす" );
    	      putValue( Action.SMALL_ICON, new ImageIcon( "open.gif" ) );
    	      putValue( Action.SHORT_DESCRIPTION, "ツールチップ" );
    	}
    	 public void actionPerformed( ActionEvent e ){
    	    	count--;
    	      System.out.println( "現在値は"+count );
    	    }
    	  }
    
    class Action02c extends AbstractAction{
    	Action02c(){
    		putValue( Action.NAME, "クリア" );
    	      putValue( Action.SMALL_ICON, new ImageIcon( "open.gif" ) );
    	      putValue( Action.SHORT_DESCRIPTION, "ツールチップ" );
    	}
    	 public void actionPerformed( ActionEvent e ){
    	    	count=0;
    	      System.out.println( "現在値は"+count );
    	    }
    	  }
    
    }