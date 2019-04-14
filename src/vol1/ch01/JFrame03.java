package vol1.ch01;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

class JFrame03 {
  public static void main(String[] args) {
    JFrame frame = new JFrame( "JFrame03" );
    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    frame.setSize(400, 300);
    frame.setVisible(true);
  }
}
