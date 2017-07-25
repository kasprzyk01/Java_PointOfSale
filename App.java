package pos;

import java.awt.EventQueue;

import pos.Frame;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run(){
				new Frame();
			}
		});
		

	}

}