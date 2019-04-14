package ex2;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

class JFrame09A {
  public static void main(String[] args) {
    JFrame frame = new JFrame( "JFrame09" );
    frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); //***
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int w = screenSize.width;
    int h = screenSize.height;
    
    frame.setBounds(-10,0, w/2, h/2 );
    frame.setMinimumSize(new Dimension(500, 400));
    frame.setVisible(true);
  }
}

