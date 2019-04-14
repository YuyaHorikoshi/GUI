package ex14;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MouseInputAdapter;

public class work extends JFrame {
	Point startPoint = new Point(-1, -1); // 直線の始点
	Point endPoint = new Point(-1, -1); // 直線の終点
	Point previousPoint = new Point(-1, -1); // 一つ前の座標

	JPanel pane;
	JPanel sub;
	JPanel button;
	JPanel subPane;
	TestPanel testPanel; // 描画用パネルを入れる

	String toolOptionName = "ペンの太さ";

	Dimension dim = null; // ウィンドウサイズを入れる
	Image buffer = null; // オフラインイメージを入れる
	Graphics bufferContext = null; // オフラインイメージのグラフィックスを入れる
	JTextField sizeField;
	JTextField extensionField;
	boolean defSize = false;// ペンの初期サイズの設定

	int penSize = 1;
	float rotation;

	JColorChooser colorChooser;
	JLabel colorlabel;
	JTextField redText;
	JTextField greenText;
	JTextField blueText;
	boolean defColor = true;// 色の初期値の設定

	JButton rubberButton;
	JButton penButton;
	JButton dottedLineButton;
	JButton eraserButton;
	JButton transformButton;

	BufferedImage readImage = null;

	boolean penFlag = true;
	boolean rubberFlag = false;
	boolean dottedLineFlag = false;
	boolean eraseFlag = false;
	boolean transformFlag = false;

	public static void main(String[] args) {
		work w = new work("work");
		w.setBackground(Color.white); // 背景を白に
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		w.setSize(screenSize.width, screenSize.height);
		w.setVisible(true);
	}

	public work(String title) {
		super(title);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("ファイル");
		menuBar.add(fileMenu);
		JMenuItem item;
		item = new JMenuItem(new OpenAction());
		fileMenu.add(item);
		item = new JMenuItem(new SaveAction());
		fileMenu.add(item);
		fileMenu.addSeparator();
		item = new JMenuItem(new ExitAction());
		fileMenu.add(item);

		pane = (JPanel) getContentPane();
		testPanel = new TestPanel(); // 描画用パネルを生成
		testPanel.addMouseListener(new MouseCheck()); // リスナを設定
		testPanel.addMouseMotionListener(new MouseCheck()); // リスナを設定
		testPanel.addKeyListener(new KeyCheck());

		sub = new JPanel(new FlowLayout(FlowLayout.LEFT));
		sizeField = new JTextField(10);
		sizeField.setBorder(new TitledBorder(toolOptionName));
		extensionField = new JTextField(10);
		extensionField.setBorder(new TitledBorder("拡張機能"));

		colorChooser = new JColorChooser();
		colorChooser.getSelectionModel().addChangeListener(new ColorChooserAction());
		colorlabel = new JLabel("");
		colorlabel.setOpaque(true);

		redText = new JTextField("", 2);
		greenText = new JTextField("", 2);
		blueText = new JTextField("", 2);

		sub.setLayout(new BoxLayout(sub, BoxLayout.Y_AXIS));
		sub.add(sizeField);
		sub.add(extensionField);
		sub.add(colorChooser);

		button = new JPanel(new FlowLayout(FlowLayout.LEFT));
		button.setLayout(new BoxLayout(button, BoxLayout.Y_AXIS));

		penButton = new JButton(new PenFlag());
		rubberButton = new JButton(new StraightLineFlag());
		dottedLineButton = new JButton(new DottedLineFlag());
		transformButton = new JButton(new TranslateFlag());
		eraserButton = new JButton(new EraseFlag());

		button.add(penButton);
		button.add(rubberButton);
		button.add(dottedLineButton);
		// button.add(transformButton);
		button.add(eraserButton);

		subPane = new JPanel();
		subPane.setLayout(new BoxLayout(subPane, BoxLayout.Y_AXIS));
		pane.add(getContentPane().add(testPanel), BorderLayout.CENTER);
		subPane.add(sub);
		subPane.add(button);
		pane.add(subPane, BorderLayout.WEST);
	}

	class TestPanel extends JPanel { // 描画用パネルの定義
		public void paintComponent(Graphics g) { // ここに描画したい内容を書く
			if (buffer == null) { // 一度だけ初期化
				dim = getSize(); // パネルのサイズを得る
				buffer = createImage(1000, 800); // オフラインイメージを生成
				bufferContext = buffer.getGraphics(); // オフラインイメージのグラフィックスを得る
			}
			if (defSize == false) {
				sizeField.setText("1");
				defSize = true;
			}
			if (Integer.parseInt(sizeField.getText()) <= 0) {
				sizeField.setText("1");
			}

			if (defColor == true) {
				bufferContext.setColor(Color.BLACK);
			}
			if (eraseFlag) {
				bufferContext.setColor(Color.WHITE);
			}
			if (!eraseFlag && !defColor) {
				bufferContext.setColor(colorChooser.getColor());
			}
			// System.out.println(colorChooser.getColor());
			// System.out.println(defColor);
			penSize = Integer.parseInt(sizeField.getText());
			BasicStroke wideStroke = new BasicStroke(penSize, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
			((Graphics2D) bufferContext).setStroke(wideStroke);
			if (penFlag || rubberFlag || eraseFlag) {
				bufferContext.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y); // オフラインイメージに直線を描く
				g.drawImage(buffer, 0, 0, this); // オフラインイメージをパネルに描く
			} else if (dottedLineFlag) {
				float[] dash = { 3.0f * penSize };
				((Graphics2D) bufferContext).setStroke(
						new BasicStroke(penSize, BasicStroke.JOIN_ROUND, BasicStroke.CAP_BUTT, 1.0f, dash, 0.0f));
				Line2D line = new Line2D.Double(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
				((Graphics2D) bufferContext).draw(line);
				g.drawImage(buffer, 0, 0, this);
			}
			if (transformFlag) {
				AffineTransform at = new AffineTransform();
				if (extensionField.getText().equals("")) {
					extensionField.setText("0");
				}
				Graphics2D g2 = (Graphics2D) g;
				rotation = Integer.parseInt(extensionField.getText());
				System.out.println(rotation + "p");
				at.rotate(Math.toRadians(rotation));
				g2.setTransform(at);
				// ((Graphics2D) g).setTransform(at);
				System.out.println(KeyEvent.VK_ENTER + "p");
				// ((Graphics2D) bufferContext).create();
				// ((Graphics2D)bufferContext)
				g.drawImage(buffer, 0, 0, this);
				transformFlag = false;

			}

			// ((Graphics2D)g).drawImage(readImage,0, 0,this);

			if (penFlag || eraseFlag) {
				startPoint = endPoint;
			}
			System.out.println(transformFlag);

			// System.out.println(transformFlag);

		}
	}

	class OpenAction extends AbstractAction {
		OpenAction() {
			putValue(Action.NAME, "開く");
			putValue(Action.SHORT_DESCRIPTION, "開く");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			JFileChooser fileChooser = new JFileChooser(".");
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileChooser.setDialogTitle("ファイルを開く");
			int ret = fileChooser.showOpenDialog(pane);
			if (ret != JFileChooser.APPROVE_OPTION)
				return;
			String filename = fileChooser.getSelectedFile().getAbsolutePath();
			// System.out.println(filename);
			// Graphics2D g3=(Graphics2D)g;
			try {
				readImage = ImageIO.read(new File(filename));
				pane.repaint();
			} catch (Exception e1) {
				e1.printStackTrace();
				readImage = null;
			}

		}

	}

	class SaveAction extends AbstractAction {
		SaveAction() {
			putValue(Action.NAME, "保存");
			putValue(Action.SHORT_DESCRIPTION, "保存");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			JFileChooser fileDialog = new JFileChooser();
			int state = fileDialog.showSaveDialog(pane);
			if (state != JFileChooser.APPROVE_OPTION) {
				return;
			}
			File file = fileDialog.getSelectedFile();
			String fileName = file.getName();
			if (!fileName.endsWith("png")) {
				file = new File(file.getParent(), fileName + ".png");
			}
			try {
				ImageIO.write((RenderedImage) buffer, "png", file);

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	class ExitAction extends AbstractAction {
		ExitAction(){
			putValue(Action.NAME,"閉じる");
			putValue(Action.SHORT_DESCRIPTION,"閉じる");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			Object[] msg = { "保存されていないデータは削除されますが", "よろしいですか？" };
			int ans = JOptionPane.showConfirmDialog(pane, msg, "ペイントツールの終了", JOptionPane.YES_NO_OPTION);
			if(ans==0){
				System.exit(0);
			}
		}

	}

	class KeyCheck extends KeyAdapter {
		public void keyPressed() {
			if (transformFlag) {

			}
		}
	}

	class MouseCheck extends MouseInputAdapter {
		public void mousePressed(MouseEvent e) {
			if (rubberFlag || dottedLineFlag) {
				startPoint = e.getPoint(); // 始点の設定
				previousPoint = startPoint; // 最初は一つ前の点も同じ
			} else if (penFlag || eraseFlag) {
				startPoint = e.getPoint(); // 始点の設定
			}
		}

		public void mouseReleased(MouseEvent e) {
			if (rubberFlag || dottedLineFlag || transformFlag) {
				endPoint = e.getPoint(); // 終点の設定
				pane.repaint(); // パネルを再描画
			}
			// System.out.println(penFlag);
		}

		public void mouseDragged(MouseEvent e) {
			if (rubberFlag || dottedLineFlag) {
				Point currentPoint = e.getPoint();
				Graphics2D g = (Graphics2D) testPanel.getGraphics(); // ラバーバンドはイメージに描く必要はない
				penSize = Integer.parseInt(sizeField.getText());
				BasicStroke wideStroke = new BasicStroke(penSize, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
				g.setStroke(wideStroke);
				g.setXORMode(Color.yellow); // XORモードで描画する
				g.drawLine(startPoint.x, startPoint.y, previousPoint.x, previousPoint.y); // 以前に描いた直線が消える
				g.drawLine(startPoint.x, startPoint.y, currentPoint.x, currentPoint.y); // 新しく直線を描く
				previousPoint = currentPoint; // 現在の点を一つ前の点に設定
			} else if (penFlag || eraseFlag) {
				endPoint = e.getPoint(); // 終点の設定
				pane.repaint(); // パネルを再描画
			}
		}
	}

	class PenFlag extends AbstractAction {
		PenFlag() {
			putValue(Action.NAME, "ペン");
			putValue(Action.SHORT_DESCRIPTION, "ペン");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			penFlag = true;
			rubberFlag = false;
			eraseFlag = false;
			dottedLineFlag = false;
			transformFlag = false;
			toolOptionName = "ペンの太さ";
			sizeField.setBorder(new TitledBorder(toolOptionName));
			extensionField.setBorder(new TitledBorder("拡張機能"));
		}
	}

	class StraightLineFlag extends AbstractAction {
		StraightLineFlag() {
			putValue(Action.NAME, "直線");
			putValue(Action.SHORT_DESCRIPTION, "直線");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			penFlag = false;
			rubberFlag = true;
			eraseFlag = false;
			dottedLineFlag = false;
			transformFlag = false;
			toolOptionName = "直線の太さ";
			sizeField.setBorder(new TitledBorder(toolOptionName));
			extensionField.setBorder(new TitledBorder("拡張機能"));
		}
	}

	class DottedLineFlag extends AbstractAction {
		DottedLineFlag() {
			putValue(Action.NAME, "破線");
			putValue(Action.SHORT_DESCRIPTION, "破線");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			penFlag = false;
			rubberFlag = false;
			eraseFlag = false;
			dottedLineFlag = true;
			transformFlag = false;
			toolOptionName = "破線の太さ";
			sizeField.setBorder(new TitledBorder(toolOptionName));
			extensionField.setBorder(new TitledBorder("拡張機能"));
		}
	}

	class TranslateFlag extends AbstractAction {
		TranslateFlag() {
			putValue(Action.NAME, "回転");
			putValue(Action.SHORT_DESCRIPTION, "回転");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			penFlag = false;
			rubberFlag = false;
			eraseFlag = false;
			dottedLineFlag = false;
			transformFlag = true;
			toolOptionName = "回転の角度(角度を入力して決定キーで右回転)";
			extensionField.setBorder(new TitledBorder(toolOptionName));
			// System.out.println(transformFlag);
		}

	}

	class EraseFlag extends AbstractAction {
		EraseFlag() {
			putValue(Action.NAME, "消しゴム");
			putValue(Action.SHORT_DESCRIPTION, "消しゴム");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			penFlag = false;
			rubberFlag = false;
			eraseFlag = true;
			dottedLineFlag = false;
			transformFlag = false;
			toolOptionName = "消しゴムの大きさ";
			sizeField.setBorder(new TitledBorder(toolOptionName));
			extensionField.setBorder(new TitledBorder("拡張機能"));
		}

	}

	class ColorChooserAction extends JFrame implements ChangeListener, ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			int red = Integer.parseInt(redText.getText());
			int green = Integer.parseInt(greenText.getText());
			int blue = Integer.parseInt(blueText.getText());
			colorChooser.setColor(red, green, blue);
		}

		@Override
		public void stateChanged(ChangeEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			defColor = false;
			Color color = colorChooser.getColor();
			colorlabel.getBackground();
		}

	}

}