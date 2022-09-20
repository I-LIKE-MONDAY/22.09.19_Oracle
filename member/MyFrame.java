package member;


import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class MyFrame extends JFrame {
	
	int w = 300, h = 300 , x= 0, y =0;
	
	public MyFrame(int w, int h) {
		this.w = w;
		this.h = h;
		setFrame();
	}

	public void setFrame(){
		//setSize(w, h);
		setBounds(x, y, w, h);
		setVisible(true);
		validate();
	}
}
