package ex8;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ActionTest01B extends JFrame {
    float rad=0;
    TestPanel testPanel; // 作成したパネルを入れる
    Timer timer; // タイマーを入れる

    public static void main(String[] args) {
	ActionTest01B w = new ActionTest01B( "ActionTest01A" );
	w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	w.setSize( 400, 300 );
	w.setVisible( true );
    }
    public ActionTest01B( String title ){
	super( title );
	JPanel panel = (JPanel)getContentPane();

	testPanel = new TestPanel(); // 描画用パネルの生成

	panel.add(testPanel);
	
	timer = new Timer( 0, new Tick() ); // タイマを生成
	timer.start(); // タイマ起動
    }
    class TestPanel extends JPanel {
	public void paintComponent(Graphics g) { // ここに描画したい内容を書く
	    super.paintComponent(g); // 背景を再描画する
	    g.setColor(Color.red);
	    g.fillOval(200+(int)(-100*Math.cos(Math.toRadians(0.8*rad+0.2))), 150+(int)(100*Math.sin(Math.toRadians(0.8*rad))), 50, 50); // 塗りつぶしの矩形を描く
	    g.setColor(Color.blue);
	    g.fillOval(150+(int)(100*Math.cos(Math.toRadians(rad+0.5))), 100+(int)(100*Math.sin(Math.toRadians(rad))), 50, 50);
	    g.setColor(Color.green);
	    g.fillOval(100+(int)(-100*Math.cos(Math.toRadians(1.2*rad-0.6))), 80+(int)(100*Math.sin(Math.toRadians(1.2*rad))), 50, 50); // 塗りつぶしの矩形を描く
	    g.setColor(Color.yellow);
	    g.fillOval(170+(int)(100*Math.cos(Math.toRadians(0.5*rad-1.2))), 120+(int)(100*Math.sin(Math.toRadians(0.5*rad))), 50, 50);

	}
    }
    class Tick extends AbstractAction {
	public void actionPerformed( ActionEvent e ){ // 一定時間ごとに呼び出される
	    rad+=0.1;
	    testPanel.repaint(); // 再描画
	}
    }
}