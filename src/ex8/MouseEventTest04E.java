package ex8;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MouseEventTest04E extends JFrame {
	int n, m = 0;
	TestPanel testPanel;

	public static void main(String[] args) {
		MouseEventTest04E w = new MouseEventTest04E("MouseEventTest02D");
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.setSize(350, 250);
		w.setVisible(true);

	}

	public MouseEventTest04E(String title) {
		super(title);
		testPanel = new TestPanel(); // 描画用パネルの生成
		testPanel.addMouseListener(new MouseCheck()); // パネルでキーボード入力を扱う場合に必要
		testPanel.addMouseMotionListener(new MouseCheck()); // リスナの設定
		getContentPane().add(testPanel);

	}

	class TestPanel extends JPanel {
		public void paintComponent(Graphics g) {
			g.setColor(Color.black);
			for (int j = 0; j < m; j++) {
				g.drawString("楽単", 250, 50 + j * 10);
				if (n > 10) {
					break;
				}
			}
			for (int i = 0; i < n; i++) {
				g.drawString("落単", 50, 50 + i * 10);
				if (n > 10) {
					Font f = new Font("Dialog", Font.BOLD, 150);
					g.setFont(f);
					g.drawString("留年", 10, 150);
					break;

				}
			}

		}

	}

	class MouseCheck extends MouseAdapter {
		public void mousePressed(MouseEvent e) {

			int button = e.getModifiersEx();

			if ((button & MouseEvent.BUTTON1_DOWN_MASK) != 0) {
				n += 1;
			}

			if ((button & MouseEvent.BUTTON3_DOWN_MASK) != 0) {
				m += 1;
			}
			testPanel.repaint();
		}
	}
}
