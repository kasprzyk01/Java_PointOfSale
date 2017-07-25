package pos;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Frame extends JFrame{
	private static final long serialVersionUID = 1L;
	int width=800;
	int height=600;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	double screenWidth = screenSize.getWidth();
	double screenHeight = screenSize.getHeight();
	int screenWidth1=(int)screenWidth;
	int screenHeight1=(int)screenHeight;
	
	   
	public Frame(){
		super("PointOfSale");
		makeFrame();
		
		

	}
	public void makeFrame(){
		
		setSize(850, 650);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("PointOfSale");
		setLocation(screenWidth1/2-width/2,screenHeight1/2-height/2);
		setResizable(false);
		//setLayout(new FlowLayout(FlowLayout.RIGHT,0,0));
		JPanel panel;
		panel = new Buttons();
		panel.setPreferredSize(new Dimension(800, 650));
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT,10,10));
		add(panel);
		setVisible(true);
		panel.setBackground(Color.blue);
	}
	
	
}
