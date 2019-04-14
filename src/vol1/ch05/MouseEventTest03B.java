package vol1.ch05;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MouseEventTest03B extends JFrame {
  JPanel panel;
  JButton bt1;
  JButton bt2;

  public static void main(String[] args) {
    MouseEventTest03B w = new MouseEventTest03B( "MouseEventTest03B" );
    w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    w.setSize( 350, 250 );
    w.setVisible( true );
  }
  public MouseEventTest03B( String title ){
    super( title );

    panel = (JPanel)getContentPane();

    bt1 = new JButton( "ボタン１" );
    bt1.addMouseListener( new MouseCheck() );
    panel.add( bt1, BorderLayout.CENTER );

    bt2 = new JButton( "ボタン２" );
    bt2.addMouseListener( new MouseCheck() );
    panel.add( bt2, BorderLayout.SOUTH );

  }

  class MouseCheck extends MouseAdapter {
    public void mouseClicked( MouseEvent e ){
      JComponent source = (JComponent)e.getSource();
      if( source == bt1 ){
        System.out.print( "マウスイベントの発生場所は ボタン１：" );
      }
      if( source == bt2 ){
        System.out.print( "マウスイベントの発生場所は ボタン２：" );
      }
      System.out.println( "---------\nマウスがクリックされました" );

      Point point1 = e.getPoint();
      SwingUtilities.convertPointToScreen( point1, source );
      System.out.println( "マウスの位置は メインウインドウの座標系で X=" + point1.getX() +" Y="+ point1.getY() );
    }
  }
}
