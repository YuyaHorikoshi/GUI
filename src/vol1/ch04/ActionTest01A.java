package vol1.ch04;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ActionTest01A extends JFrame {
    int yValue = 0; // バーの高さ
    int direction = 1; // 増減の方向
    TestPanel testPanel; // 作成したパネルを入れる
    Timer timer; // タイマーを入れる

    public static void main(String[] args) {
	ActionTest01A w = new ActionTest01A( "ActionTest01A" );
	w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	w.setSize( 400, 300 );
	w.setVisible( true );
    }
    public ActionTest01A( String title ){
	super( title );
	JPanel panel = (JPanel)getContentPane();

	testPanel = new TestPanel(); // 描画用パネルの生成

	panel.add(testPanel);
	
	timer = new Timer( 100, new Tick() ); // タイマを生成
	timer.start(); // タイマ起動
    }
    class TestPanel extends JPanel {
	public void paintComponent(Graphics g) { // ここに描画したい内容を書く
	    super.paintComponent(g); // 背景を再描画する
	    g.setColor(Color.red);
	    g.fillRect(100, 10, 200, yValue); // 塗りつぶしの矩形を描く
	}
    }
    class Tick extends AbstractAction {
	public void actionPerformed( ActionEvent e ){ // 一定時間ごとに呼び出される
	    if (direction > 0) { // 方向によって増減を決める
		yValue += 10;
	    } else {
		yValue -= 10;
	    }
	    if (yValue >= 150 || yValue <= 0) { // 上限下限で反転する
		direction *= -1;
	    }
	    testPanel.repaint(); // 再描画
	}
    }
}