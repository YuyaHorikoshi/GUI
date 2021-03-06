package vol1.ch07;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

public class JListTest08 extends JFrame {
  JPanel pane;

  public static void main(String[] args) {
    JListTest08 w = new JListTest08( "JListTest08" );
    w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    w.setSize( 300, 240 );
    w.setVisible( true );
  }
  public JListTest08( String title ) {  
    super( title );
    pane = (JPanel)getContentPane();

    DefaultListModel model = new DefaultListModel(); //**

    model.addElement( new MyAction( "New", new ImageIcon( "new.gif" ) ) );
    model.addElement( new MyAction( "Open", new ImageIcon( "open.gif" ) ) );
    model.addElement( new MyAction( "Save", new ImageIcon( "save.gif" ) ) );
    model.addElement( new MyAction( "Exit", new ImageIcon( "exit.gif" ) ) );

    JList list = new JList( model );
    list.addListSelectionListener( new MyListSelect() );

    list.setCellRenderer( new MyCellRenderer() ); //****

    pane.add( list, BorderLayout.CENTER );
    list.setBorder( new TitledBorder( "選択リスト" ) );
  }

  class MyAction extends AbstractAction {
    MyAction( String text, Icon icon ){ super( text, icon ); }
    public void actionPerformed( ActionEvent e ){}
  }

  class MyCellRenderer extends DefaultListCellRenderer{
    public Component getListCellRendererComponent(
        JList list, Object value, int index, 
        boolean isSelected, boolean cellHasFocus)
    {
      MyAction action = (MyAction)value;

      if( isSelected ){
        setOpaque(true); // これがないと選択時の背景色がセットされない
        setForeground( list.getSelectionForeground() );
        setBackground( list.getSelectionBackground() );
      }
      else{
        setOpaque(false); // これがないと非選択時に背景色がリセットされない
      }

      setText( (String)action.getValue( Action.NAME ) );
      setIcon( (Icon)action.getValue( Action.SMALL_ICON ) );

      return this;
    }
  }

  class MyListSelect implements ListSelectionListener {
    public void valueChanged( ListSelectionEvent e ){

      if( e.getValueIsAdjusting()==false ){ // 1つの項目が選択された

        JList list = (JList)e.getSource();
        int index = list.getSelectedIndex();
        DefaultListModel model = (DefaultListModel)list.getModel();
        MyAction select = (MyAction)model.elementAt( index );
        System.out.println( select.getValue( Action.NAME ) );
      }
    }
  }
}
