package ex4;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuTest01B extends JFrame {

  public static void main(String[] args) {
    MenuTest01B w = new MenuTest01B( "MenuTest01" );
    w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    w.setSize( 350, 250 );
    w.setVisible( true );
  }
  public MenuTest01B( String title ){
    super( title );

    JMenuBar menuBar = new JMenuBar();
    setJMenuBar( menuBar );

    JMenu file = new JMenu( "ファイル(F)" );
    menuBar.add( file );
    JMenu edit=new JMenu("編集(E)");
    menuBar.add(edit);
    JMenu view=new JMenu("表示(E)");
    menuBar.add(view);
    JMenu hist=new JMenu("履歴(S)");
    menuBar.add(hist);

    JMenuItem item;    
    item = new JMenuItem( "新しいウィンドウ(N)　　　　　Ctrl+N" );
    file.add( item );
    item = new JMenuItem( "新しいタブ(T)　　　　　　　　Ctrl+T" );
    file.add( item );
    item = new JMenuItem( "URLを開く(L)　　　　　　　　 Ctrl+L" );
    file.add( item );
    item = new JMenuItem( "ファイルを開く(O)　　　　　　Ctrl+O" );
    file.add( item );
    item = new JMenuItem( "ウィンドウを閉じる　　　　　 Ctrl+Shift+W" );
    file.add( item );
    item = new JMenuItem( "タブを閉じる(C)　　　　　　　Ctrl+W" );
    file.add( item );
    file.addSeparator();
    item = new JMenuItem( "名前を付けてページを保存(A)　Ctrl+S" );
    file.add( item );
    item = new JMenuItem( "ページをPDFで保存(F)" );
    file.add( item );
    item = new JMenuItem( "ページのURLをメールで送信(E)" );
    file.add( item );
    file.addSeparator();
    item = new JMenuItem( "ページ設定(U)" );
    file.add( item );
    item = new JMenuItem( "印刷プレビュー(V)" );
    file.add( item );
    item = new JMenuItem( "印刷(P)　　　　　　　　　　　Ctrl+P" );
    file.add( item );
    file.addSeparator();
    item = new JMenuItem( "設定とデータのインポート(I)" );
    file.add( item );
    file.addSeparator();
    item = new JMenuItem( "オフライン作業(W)" );
    file.add( item );
    item = new JMenuItem( "終了(Q)" );
    file.add( item );
  }
}
