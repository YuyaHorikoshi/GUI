package ex8;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class KeyEventTest02D extends JFrame {
    Color color1=Color.blue; // 現在の座標を保持する
    Color color2=Color.blue;
    Color color3=Color.blue;
    Color color4=Color.blue;
    TestPanel testPanel; // 作成したパネルを入れる

    public static void main(String[] args) {
	KeyEventTest02D w = new KeyEventTest02D( "KeyEventTest02C" );
	w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	w.setSize( 400, 300 );
	w.setVisible( true );
    }
    public KeyEventTest02D( String title ){
	super( title );
	JPanel panel = (JPanel)getContentPane();

	testPanel = new TestPanel(); // 描画用パネルの生成
	testPanel.setFocusable(true); // パネルでキーボード入力を扱う場合に必要
	testPanel.addKeyListener( new KeyTest() ); // リスナの設定

	panel.add(testPanel);
    }
    class TestPanel extends JPanel { // 独自のパネルを定義
	public void paintComponent(Graphics g) { // ここに描画したい内容を書く
	    // super.paintComponent(g); // 背景を再描画するかどうか
		
	    g.setColor(color1);
	    int x0[]={200,250,150};
	    int y0[]={20,50,50};
	    g.fillPolygon(x0, y0, 3); // 塗りつぶしの矩形を描く
	    
	    g.setColor(color2);
	    int x1[]={150,150,120};
	    int y1[]={50,150,100};
	    g.fillPolygon(x1,y1,3);
	    
	    g.setColor(color3);
	    int x2[]={250,250,280};
	    int y2[]={50,150,100};
	    g.fillPolygon(x2,y2,3);
	    
	    g.setColor(color4);
	    int x3[]={200,250,150};
	    int y3[]={180,150,150};
	    g.fillPolygon(x3,y3,3);
	}
    }
    class KeyTest extends KeyAdapter {
	public void keyPressed( KeyEvent e ) {
	    int keyCode = e.getKeyCode();
	    switch (keyCode) { // キーによって
	    case KeyEvent.VK_UP: // ↑
		color1=Color.red;
		break;
	    case KeyEvent.VK_DOWN: // ↓
		color4=Color.red;
		break;
	    case KeyEvent.VK_LEFT: // ←
		color2=Color.red;
		break;
	    case KeyEvent.VK_RIGHT: // →
		color3=Color.red;
		break;
	    default:
		break;
	    }
	    testPanel.repaint();
	    }
	    
	    public void keyReleased( KeyEvent e ) {
		    int keyCode = e.getKeyCode();
		    switch (keyCode) { // キーによって
		    case KeyEvent.VK_UP: // ↑
			color1=Color.blue;
			break;
		    case KeyEvent.VK_DOWN: // ↓
			color4=Color.blue;
			break;
		    case KeyEvent.VK_LEFT: // ←
			color2=Color.blue;
			break;
		    case KeyEvent.VK_RIGHT: // →
			color3=Color.blue;
			break;
		    default:
			break;
		    }
	    testPanel.repaint(); // パネルを再描画
	}

	private void KeyPressed() {
		// TODO 自動生成されたメソッド・スタブ
		
	}
    }
}
