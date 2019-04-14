package ex10;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class JListTest01D extends JFrame {
  JPanel pane;
  JTextField tf;
  public static void main(String[] args) {
    JListTest01D w = new JListTest01D( "JListTest01D" );
    w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    w.setSize( 300, 200 );
    w.setVisible( true );
  }
  public JListTest01D( String title ) {  
    super( title );
    pane = (JPanel)getContentPane();
    
    tf=new JTextField();
    
    String[] choice = { "0", "1", "2", "3", "4", "5", "6", "7","8","9" };
    DefaultListModel listModel = new DefaultListModel();
    for( int i=0 ; i< choice.length ; i++ ){
      listModel.addElement( choice[i] );
    }

    JList list = new JList( listModel );
    list.addListSelectionListener(new MyListSelect());
    
    pane.add(list, BorderLayout.CENTER);
    list.setBorder(new TitledBorder("選択リスト"));
    //pane.add(tf,BorderLayout.SOUTH);
    
    //tf.setBorder(new TitledBorder("選択された項目"));
    list.setBorder(new LineBorder(Color.black,2));
    JScrollPane scr=new JScrollPane(list);
    pane.add(scr);
  }
  class MyListSelect implements ListSelectionListener{
	  public void valueChanged(ListSelectionEvent e){
		  JList li=(JList)e.getSource();
		  if( e.getValueIsAdjusting()==false ){ 
		        

		        DefaultListModel model = (DefaultListModel)li.getModel(); //***

		        int index =  li.getSelectedIndex();
		        String select = (String)model.get(index);
		        JTextField tx = new JTextField( select );
		        Object[]  obj = { tx };
		        System.out.println(select+"が選択されました。");
		      }
	  }
  }
 
  }