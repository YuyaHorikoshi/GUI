package vol1.ch05;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class KeyEventTest02C extends JFrame {
    int currentX = 200, currentY = 150; // 現在の座標を保持する
    TestPanel testPanel; // 作成したパネルを入れる

    public static void main(String[] args) {
	KeyEventTest02C w = new KeyEventTest02C( "KeyEventTest02C" );
	w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	w.setSize( 400, 300 );
	w.setVisible( true );
    }
    public KeyEventTest02C( String title ){
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
	    g.setColor(Color.red);
	    g.fillRect(currentX, currentY, 10, 10); // 塗りつぶしの矩形を描く
	}
    }
    class KeyTest extends KeyAdapter {
	public void keyPressed( KeyEvent e ) {
	    int keyCode = e.getKeyCode();
	    switch (keyCode) { // キーによって
	    case KeyEvent.VK_UP: // ↑
		currentY -= 2;
		break;
	    case KeyEvent.VK_DOWN: // ↓
		currentY += 2;
		break;
	    case KeyEvent.VK_LEFT: // ←
		currentX -= 2;
		break;
	    case KeyEvent.VK_RIGHT: // →
		currentX += 2;
		break;
	    default:
		break;
	    }
	    testPanel.repaint(); // パネルを再描画
	}
    }
}
