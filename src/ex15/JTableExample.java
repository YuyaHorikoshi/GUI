package ex15;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;


class JTableExample extends JFrame {
	  String[] columnNames = { 
	    "品目コード", "品目名", "販売単価", "仕入単価", "メーカコード" 
	    };
	  String[][] data = {
	    { "CP001",     "コピー用紙A4",  "600", "300", "aaaa"},
	    { "CP002",     "コピー用紙B5",  "500", "300", ".aaaa"},
	    { "LL001",     "ペン",  "100", "50", ".bbb"},
	    { "LL002",     "ペン",  "100", "50", ".bbb"},
	    { "NT001",     "ペン",  "100", "50", ".ccc"}
	  };
	        
	  JTableExample( String title ) {
	    super( title );
	    JTable table = new JTable( data, columnNames );
	    JScrollPane scroll = new JScrollPane( table );
	    getContentPane().add( scroll );
	    setSize( 600, 200 );
	    setVisible( true );
	  }        

	  public static void main(String[] args) {
	    JFrame frame = new JTableExample( "商品情報" );
	    frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	  }
	}
