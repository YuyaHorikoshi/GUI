package vol2.ch05;
import javax.swing.*;
import javax.swing.table.*;

class JTableTest09 extends JFrame {
        
  String[] columnNames = { 
    "チーム", "icon", "勝数", "負数", "分数", "勝率", "ゲーム差" 
  };
  Object[][] data = {
    { "阪神",     new ImageIcon( "./image/tigers.gif" ),  
      "11",  "2", "0", ".846", "-" },
    { "広島",     new ImageIcon( "./image/carp.gif" ),    
      " 8",  "5", "0", ".615", "3.0" },
    { "巨人",     new ImageIcon( "./image/giants.gif" ),  
      " 7",  "6", "0", ".538", "1.0" },
    { "ヤクルト", new ImageIcon( "./image/swallows.gif" ),
      " 6",  "8", "0", ".428", "1.5" },
    { "中日",     new ImageIcon( "./image/dragons.gif" ), 
      " 5",  "8", "0", ".384", "0.5" },
    { "横浜",     new ImageIcon( "./image/baystars.gif" ),
      " 3", "11", "0", ".214", "2.5" },
  };

  class TableModel1 extends DefaultTableModel {
    TableModel1( String[] columnNames, int rowNum ){
       super( columnNames, rowNum );
    }
	@Override
    public Class getColumnClass( int col ) { //このメソッドをオーバーライドする
      return getValueAt( 0, col ).getClass();
    }
  }


  JTableTest09( String title ) {
    super( title );

    DefaultTableModel model =  new TableModel1( columnNames, 0 );

    JTable table = new JTable();
    table.setModel( model );
    table.setRowHeight( 60 );

    for( int row = 0 ; row < 6 ; row++ ){
      model.addRow( data[row] );
      /* 今回は DefaultTableModel を継承して、getColumnClass() を正しく */
      /* 再定義しているのでうまくいく                                   */
    }

    JScrollPane scroll = new JScrollPane( table );
    getContentPane().add( scroll );
    setSize( 600, 420 );
    setVisible( true );
  }        

  public static void main(String[] args) {
    JFrame frame = new JTableTest09( "セリーグ順位表" );
    frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
  }
}
