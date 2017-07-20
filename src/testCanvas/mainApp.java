package testCanvas;

import javax.swing.JFrame;

public class mainApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimulateData.setMapping();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(30, 30, 1000, 1000);
		frame.getContentPane().add(new MyCanvas());
		frame.setVisible(true);
	}

}
