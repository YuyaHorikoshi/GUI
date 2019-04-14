package ex10;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class JListTest01C extends JFrame {
  JPanel pane;
  JTextField tf;
  public static void main(String[] args) {
    JListTest01C w = new JListTest01C( "JListTest01C" );
    w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    w.setSize( 300, 200 );
    w.setVisible( true );
  }
  public JListTest01C( String title ) {  
    super( title );
    pane = (JPanel)getContentPane();
    
    
    String[] choice = { "0", "1", "2", "3", "4", "5", "6", "7","8","9" };
    JList list = new JList( choice );
    list.addListSelectionListener(new MyListSelect());
    pane.add(list, BorderLayout.CENTER);
    
    list.setBorder(new LineBorder(Color.black,2));
    JScrollPane scr=new JScrollPane(list);
    pane.add(scr);
  }
  class MyListSelect implements ListSelectionListener{
	  public void valueChanged(ListSelectionEvent e){
		  JList li=(JList)e.getSource();
		  if(e.getValueIsAdjusting()==false){
			  String select=(String)li.getSelectedValue();
			  System.out.println(select+"選択されました。");
		  }
	  }
  }
 
  }

