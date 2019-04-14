package ex11;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class JTextFieldTest6 extends JFrame {
	JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;

    JTextField tf1;
    JTextField tf2;
    JTextField tf3;


	  public static void main( String[] args ){
	    JFrame w = new JTextFieldTest6( "JTextFieldTest6" );
	    w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	    w.setSize( 200, 150 );
	    w.setVisible( true );

	  }

	  public JTextFieldTest6( String title ){
	    super( title );
	    JPanel pane = (JPanel)getContentPane();
	    pane.setLayout(new BorderLayout());

	    tf1 = new JTextField(5);
	    tf1.setDocument( new NumericDocument() );
	    tf1.setBorder( new TitledBorder( "整数1" ) );
	    pane.add( tf1,BorderLayout.WEST );

	    tf2 = new JTextField(5);
	    tf2.setDocument( new NumericDocument() );
	    tf2.setBorder( new TitledBorder( "整数2" ) );
	    pane.add( tf2,BorderLayout.EAST );

	    tf3=new JTextField("");
	    //tf3.setDocument(new ???);
	    tf3.setBorder(new TitledBorder("答"));
	    tf3.setDocument(new NumericDocument());
	    pane.add(tf3,BorderLayout.SOUTH);

	    JPanel panel=new JPanel();
	    panel.setLayout(new GridLayout(4,1));
	    JButton button1 = new JButton("+");
	    JButton button2=new JButton("-");
	    JButton button3=new JButton("*");
	    JButton button4=new JButton("/");
	    button1.addActionListener(new Action_a());
	    button2.addActionListener(new Action_b());
	    button3.addActionListener(new Action_c());
	    button4.addActionListener(new Action_d());
	    panel.add(button1);
	    panel.add(button2);
	    panel.add(button3);
	    panel.add(button4);
	    pane.add(panel,BorderLayout.CENTER);
	  }

	  class NumericDocument extends PlainDocument {
	    String validValues = "0123456789.+-";

		@Override
	    public void insertString( int offset, String str, AttributeSet a ) {
	      if( validValues.indexOf( str ) == -1 ){
	         return;
	      }
	      try{
	        super.insertString( offset, str, a );
	      }
	      catch( BadLocationException e ) {
	        System.out.println( e );
	      }
	    }
	  }

	  class TextFieldAction implements ActionListener{
		  public void actionPerformed(ActionEvent e){
			  boolean flag=!("".equals(tf1.getText()) || "".equals(tf2.getText()));
			  button1.setEnabled(flag);
			  button2.setEnabled(flag);
			  button3.setEnabled(flag);
			  button4.setEnabled(flag);
		  }
	  }

	  class AbstractAction implements ActionListener {
		    public void actionPerformed( ActionEvent e ){

		      }
		}

class Action_a extends AbstractAction{
public void actionPerformed( ActionEvent e ){
	int a=Integer.parseInt(tf1.getText());
	int b=Integer.parseInt(tf2.getText());
	tf3.setText(String.valueOf(a+b));

}
}

class Action_b extends AbstractAction{
	 public void actionPerformed( ActionEvent e ){
		 int a=Integer.parseInt(tf1.getText());
		 int b=Integer.parseInt(tf2.getText());
		 tf3.setText(String.valueOf(a-b));

	    }
	  }
class Action_c extends AbstractAction{
public void actionPerformed( ActionEvent e ){
	int a=Integer.parseInt(tf1.getText());
	int b=Integer.parseInt(tf2.getText());
	tf3.setText(String.valueOf(a*b));

}
}

class Action_d extends AbstractAction{
	 public void actionPerformed( ActionEvent e ){
		 float a=Integer.parseInt(tf1.getText());
		 float b=Integer.parseInt(tf2.getText());
		 if(b!=0){
		 tf3.setText(String.valueOf(a/b));
		 }
		 if(b==0){
			 System.out.println("0では割れません");
		 }
	    }
	  }
}
