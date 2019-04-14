package ex15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

class AddressTable extends JTable {

  BufferedReader reader;
  protected DefaultTableModel model;

  protected String[] columnNames = 
    { "名前", "住所", "電話", "メール"};
  
  class TableModel1 extends DefaultTableModel {
    TableModel1( String[] columnNames, int rowNum ){
       super( columnNames, rowNum );
    }
    public Class getColumnClass( int col ) {
      return getValueAt( 0, col ).getClass();
    }
    public boolean isCellEditable( int row, int col ) {
      return true;
    }
  }

  AddressTable() {
    model =  new TableModel1( columnNames, 0 );
    setModel( model );

    addData( model, "address.txt" );
  }

  void addData( DefaultTableModel model, String dataFileName ){
    try {
      reader = new BufferedReader( new FileReader( dataFileName ) );
      String line;
      while((line=reader.readLine())!=null){
    	  String[] s=line.split(",");
        model.addRow( s );
      }
    }
    catch ( IOException e ) {
       System.out.println( e );
    }
  } 

  Vector<Object> readLine( String line ){       
    StringTokenizer st = new StringTokenizer( line );
    Vector<Object> row = new Vector<Object>();
    for( int i = 0 ; i < 6 ; i++ ){
      String data = st.nextToken();
      row.addElement( data );
    }
    return( row );
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame( "個人情報" );
    frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

    JTable table = new AddressTable();
    JScrollPane scroll = new JScrollPane( table );
    frame.getContentPane().add( scroll );
    frame.setSize( 600, 420 );
    frame.setVisible( true );
  }
}